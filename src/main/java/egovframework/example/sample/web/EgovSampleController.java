/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.example.sample.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springmodules.validation.commons.DefaultBeanValidator;

/**
 * @Class Name : EgovSampleController.java
 * @Description : EgovSample Controller Class
 * @Modification Information
 * @ @ 수정일 수정자 수정내용 @ --------- --------- ------------------------------- @
 *   2009.03.16 최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *      Copyright (C) by MOPAS All right reserved.
 */

@Controller
public class EgovSampleController {

	/** EgovSampleService */
	@Resource(name = "sampleService")
	private EgovSampleService sampleService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	/** ver.2 testList **/

	@RequestMapping(value = "/testList.do")
	public String testList(@ModelAttribute("searchVO") SampleDefaultVO searchVO, ModelMap model) throws Exception {

		// 페이징
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex()); // 1
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit()); // 10
		paginationInfo.setPageSize(searchVO.getPageSize());// 10

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		// 총 갯수 testListCnt
		int totCnt = sampleService.testListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("totCnt", totCnt);

		/** 목록 조회 **/
		List<?> testList = sampleService.testList(searchVO);
		model.addAttribute("resultList", testList);
		return "sample/testList";
	}

	// 상세 페이지
	@RequestMapping(value = "/testListDetail.do")
	public String testListDetail(@ModelAttribute("searchVO") SampleDefaultVO searchVO, ModelMap model, MultipartFile[] file)
			throws Exception {
		// 목록 조회
		List<SampleDefaultVO> testListDetail = sampleService.testListDetail(searchVO);

		model.addAttribute("resultList", testListDetail);
		model.addAttribute("title", testListDetail.get(0).getTitle());
		model.addAttribute("writer", testListDetail.get(0).getWriter());
		model.addAttribute("content", testListDetail.get(0).getContent());
		model.addAttribute("idx", testListDetail.get(0).getIdx());
		model.addAttribute("regDate", testListDetail.get(0).getRegDate());
		model.addAttribute("code", testListDetail.get(0).getCode());
		
		// 파일 조회
		List<SampleDefaultVO> fileList = sampleService.fileList(searchVO);
		/* model.addAttribute("orgFileName", fileList.get(0).getOrgFileName()); */
		model.addAttribute("orgFileName", fileList);
		/* System.out.println("파일 이름 : " + fileList.get(0).getOrgFileName()); */
		
		return "sample/testListDetail";
	}

	// 등록 페이지
	@RequestMapping(value = "/testListInsert.do")
	public String testListInsert(@ModelAttribute("searchVO") SampleDefaultVO searchVO, ModelMap model)
			throws Exception {
		model.addAttribute("sampleVO", new SampleVO());
		return "sample/testListInsert";
	}

	// 글 등록
	@RequestMapping(value = "/insertTest.do")
	public String insertTest(@ModelAttribute("searchVO") SampleDefaultVO searchVO, ModelMap model, MultipartFile[] file)
			throws Exception {
		sampleService.insertTest(searchVO);
		System.out.println("================== board start ==================");
		System.out.println("제목 : " + searchVO.getTitle());
		System.out.println("작성자 : " + searchVO.getWriter());
		System.out.println("아이디 : " + searchVO.getIdx());
		System.out.println("================== board   end ==================");

		// 파일 업로드
		String uploadPath = "C:\\Users\\aug2322\\eclipse-workspace\\board_project\\file";
		/* String boardIDX = String.valueOf(searchVO.getCode()); */

		for (MultipartFile multipartFile : file) {
			String orgFileName = multipartFile.getOriginalFilename();
			String orgFileExtension = orgFileName.substring(orgFileName.lastIndexOf("."));
			String saveFileName = UUID.randomUUID().toString().replaceAll("-", "") + orgFileExtension;
			Long saveFileSize = multipartFile.getSize();

			System.out.println("================== file insert ==================");
			System.out.println("파일 이름: " + orgFileName);
			System.out.println("파일 실제 이름: " + saveFileName);
			System.out.println("파일 크기: " + saveFileSize);
			System.out.println("content type: " + multipartFile.getContentType());
			System.out.println("================== file   end ==================");

			File target = new File(uploadPath, saveFileName);
			try {
				multipartFile.transferTo(target);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			searchVO.setOrgFileName(orgFileName);
			searchVO.setSaveFileName(saveFileName);
			searchVO.setFileSize(saveFileSize);

			/*
			 * System.out.println("VO 파일 이름: " + searchVO.getOrgFileName());
			 * System.out.println("VO 파일 실제 이름: " + searchVO.getSaveFileName());
			 * System.out.println("===============================================");
			 */
			sampleService.insertFile(searchVO, file);
		}
		return "redirect:testList.do";
	}

	// 수정 페이지
	@GetMapping(value = "/testListUpdate.do")
	public String testListUpdate(@ModelAttribute("searchVO") SampleDefaultVO searchVO, ModelMap model)
			throws Exception {

		List<SampleDefaultVO> testListUpdate = sampleService.testListUpdate(searchVO);

		model.addAttribute("title", testListUpdate.get(0).getTitle());
		model.addAttribute("writer", testListUpdate.get(0).getWriter());
		model.addAttribute("content", testListUpdate.get(0).getContent());
		model.addAttribute("idx", testListUpdate.get(0).getIdx());
		model.addAttribute("code", testListUpdate.get(0).getCode());
		model.addAttribute("regDate", testListUpdate.get(0).getRegDate());
		model.addAttribute("fileId", testListUpdate.get(0).getFileId());
		model.addAttribute("code", testListUpdate.get(0).getCode());
		// 파일 조회
		List<SampleDefaultVO> fileList = sampleService.fileList(searchVO);
		model.addAttribute("orgFileName", fileList);
		/* model.addAttribute("fileId", fileList.get(0).getFileId()); */
		/* model.addAttribute("code", fileList.get(0).getCode()); */
		
		return "sample/testListUpdate";
	}

	// 글 수정
	@PostMapping(value = "/updateTest.do")
	public String updateTest(@ModelAttribute("searchVO") SampleDefaultVO searchVO, ModelMap model, MultipartFile[] file)
			throws Exception {
		System.out.println("********UPDATE TEST**********");
		System.out.println("제목 : " + searchVO.getTitle());
		System.out.println("작성자 : " + searchVO.getWriter());
		System.out.println("내용 : " + searchVO.getContent());
		System.out.println("아이디 : " + searchVO.getIdx());
		System.out.println("code" + searchVO.getCode());
		System.out.println("날짜 : " + searchVO.getRegDate());
		System.out.println("********UPDATE END **********");
		//게시글 정보 update
		sampleService.updateTest(searchVO);

		
		//file update
		String uploadPath = "C:\\Users\\aug2322\\eclipse-workspace\\board_project\\file";
		/* String boardIDX = String.valueOf(searchVO.getCode()); */

		for (MultipartFile multipartFile : file) {
			String orgFileName = multipartFile.getOriginalFilename();
			String orgFileExtension = orgFileName.substring(orgFileName.lastIndexOf("."));
			String saveFileName = UUID.randomUUID().toString().replaceAll("-", "") + orgFileExtension;
			Long saveFileSize = multipartFile.getSize();

			System.out.println("================== update file ==================");
			System.out.println("파일 이름: " + orgFileName);
			System.out.println("파일 실제 이름: " + saveFileName);
			System.out.println("파일 크기: " + saveFileSize);
			System.out.println("content type: " + multipartFile.getContentType());
			System.out.println("================== update end ==================");

			File target = new File(uploadPath, saveFileName);
			try {
				multipartFile.transferTo(target);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			searchVO.setOrgFileName(orgFileName);
			searchVO.setSaveFileName(saveFileName);
			searchVO.setFileSize(saveFileSize);

			/*
			 * System.out.println("VO 파일 이름: " + searchVO.getOrgFileName());
			 * System.out.println("VO 파일 실제 이름: " + searchVO.getSaveFileName());
			 * System.out.println("===============================================");
			 */
			sampleService.insertFile(searchVO, file);
		}
		return "redirect:/testList.do";

	}

	// 글 삭제
	@RequestMapping(value = "/deleteTest.do")
	public String deleteTest(SampleVO sampleVO, @ModelAttribute("searchVO") SampleDefaultVO searchVO) throws Exception {
		System.out.println("********DELETE BOARD**********");

		sampleService.deleteTest(searchVO);
		return "forward:/testList.do";
	}

	// 파일 다운로드
	@GetMapping("/fileDownload.do")
	public void download(SampleDefaultVO sampleVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
        	String path = "C:\\Users\\aug2322\\eclipse-workspace\\board_project\\file\\"+ sampleVO.getSaveFileName();
        	String orgFileName = sampleVO.getOrgFileName();
        	
        	System.out.println("********DOWNLOAD TEST**********");
        	System.out.println("파일 원본: " + sampleVO.getOrgFileName());
        	System.out.println("글 번호 : " + sampleVO.getCode());
			System.out.println("파일 이름: " + sampleVO.getSaveFileName());
			System.out.println("파일 경로: " + path);
			System.out.println("********DOWNLOAD TEST**********");

        	File file = new File(path);
        	orgFileName = new String(orgFileName.getBytes("UTF-8"), "ISO-8859-1");  //한글 깨짐 인코딩
        	response.setHeader("Content-Disposition", "attachment;filename=" + orgFileName); // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
        	
        	FileInputStream fileInputStream = new FileInputStream(path); // 파일 읽어오기 
        	OutputStream out = response.getOutputStream();
        	
        	int read = 0;
                byte[] buffer = new byte[1024];
                while ((read = fileInputStream.read(buffer)) != -1) { // 1024바이트씩 계속 읽으면서 outputStream에 저장, -1이 나오면 더이상 읽을 파일이 없음
                    out.write(buffer, 0, read);
                }
                
        } catch (Exception e) {
            throw new Exception("download error");
        }
	}

	// 파일 삭제
	@RequestMapping(value = "/deleteFile.do")
	public String deleteFile(@RequestParam("fileId") Integer fileId, HttpServletRequest request, @ModelAttribute("searchVO") SampleDefaultVO searchVO) throws Exception {
		System.out.println("====================deleteCon=================");
		System.out.println("삭제 아이디 : " + fileId);
		System.out.println("====================deleteCon=================");
		searchVO.setFileId(fileId);
		
		sampleService.deleteFile(fileId);
		
		String referer = request.getHeader("Referer");
		return "redirect:"+ referer;
	}

}
