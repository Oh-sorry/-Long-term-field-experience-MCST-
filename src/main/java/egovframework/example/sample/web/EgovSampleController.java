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

	/**
	 * 글 목록을 조회한다. (pageing)
	 * 
	 * @param searchVO - 조회할 정보가 담긴 SampleDefaultVO
	 * @param model
	 * @return "egovSampleList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/egovSampleList.do")
	public String selectSampleList(@ModelAttribute("searchVO") SampleDefaultVO searchVO, ModelMap model)
			throws Exception {

		/** EgovPropertyService.sample */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> sampleList = sampleService.selectSampleList(searchVO);
		model.addAttribute("resultList", sampleList);

		int totCnt = sampleService.selectSampleListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("totCnt", totCnt);

		return "sample/egovSampleList";
	}

	/**
	 * 글 등록 화면을 조회한다.
	 * 
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "egovSampleRegister"
	 * @exception Exception
	 */
	@RequestMapping(value = "/addSample.do", method = RequestMethod.GET)
	public String addSampleView(@ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		model.addAttribute("sampleVO", new SampleVO());
		return "sample/egovSampleRegister";
	}

	/**
	 * 글을 등록한다.
	 * 
	 * @param sampleVO - 등록할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping(value = "/addSample.do", method = RequestMethod.POST)
	public String addSample(@ModelAttribute("searchVO") SampleDefaultVO searchVO, SampleVO sampleVO,
			BindingResult bindingResult, Model model, SessionStatus status) throws Exception {

		// Server-Side Validation
		beanValidator.validate(sampleVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("sampleVO", sampleVO);
			return "sample/egovSampleRegister";
		}

		sampleService.insertSample(sampleVO);
		status.setComplete();
		return "forward:/egovSampleList.do";
	}

	/**
	 * 글 수정화면을 조회한다.
	 * 
	 * @param id       - 수정할 글 id
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "egovSampleRegister"
	 * @exception Exception
	 */
	@RequestMapping("/updateSampleView.do")
	public String updateSampleView(@RequestParam("selectedId") String id,
			@ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		SampleVO sampleVO = new SampleVO();
		sampleVO.setId(id);
		// 변수명은 CoC 에 따라 sampleVO
		model.addAttribute(selectSample(sampleVO, searchVO));
		return "sample/egovSampleRegister";
	}

	/**
	 * 글을 조회한다.
	 * 
	 * @param sampleVO - 조회할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return @ModelAttribute("sampleVO") - 조회한 정보
	 * @exception Exception
	 */
	public SampleVO selectSample(SampleVO sampleVO, @ModelAttribute("searchVO") SampleDefaultVO searchVO)
			throws Exception {
		return sampleService.selectSample(sampleVO);
	}

	/**
	 * 글을 수정한다.
	 * 
	 * @param sampleVO - 수정할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping("/updateSample.do")
	public String updateSample(@ModelAttribute("searchVO") SampleDefaultVO searchVO, SampleVO sampleVO,
			BindingResult bindingResult, Model model, SessionStatus status) throws Exception {

		beanValidator.validate(sampleVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("sampleVO", sampleVO);
			return "sample/egovSampleRegister";
		}

		sampleService.updateSample(sampleVO);
		status.setComplete();
		return "forward:/egovSampleList.do";
	}

	/**
	 * 글을 삭제한다.
	 * 
	 * @param sampleVO - 삭제할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping("/deleteSample.do")
	public String deleteSample(SampleVO sampleVO, @ModelAttribute("searchVO") SampleDefaultVO searchVO,
			SessionStatus status) throws Exception {
		sampleService.deleteSample(sampleVO);
		status.setComplete();
		return "forward:/egovSampleList.do";
	}

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
		
		model.addAttribute("orgFileName", fileList.get(0).getOrgFileName());
		System.out.println("파일 이름 : " + fileList.get(0).getOrgFileName());
		
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
	public String insertTest(@ModelAttribute("searchVO") SampleDefaultVO searchVO, ModelMap model, MultipartFile[] file) throws Exception {
		sampleService.insertTest(searchVO);
		System.out.println("================== board start ==================");
		System.out.println("제목 : " + searchVO.getTitle());
		System.out.println("작성자 : " + searchVO.getWriter());
		System.out.println("아이디 : " + searchVO.getIdx());
		System.out.println("================== board   end ==================");

		// 파일 업로드
		String uploadPath = "C:\\Users\\aug2322\\eclipse-workspace\\board_project\\file";
		String boardIDX = String.valueOf(searchVO.getCode());

		for (MultipartFile multipartFile : file) {
			String orgFileName = multipartFile.getOriginalFilename();
			String orgFileExtension = orgFileName.substring(orgFileName.lastIndexOf("."));
			String saveFileName = UUID.randomUUID().toString().replaceAll("-", "") + orgFileExtension;
			Long saveFileSize = multipartFile.getSize();

			System.out.println("================== file start ==================");
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
		
		// 파일 조회
		List<SampleDefaultVO> fileList = sampleService.fileList(searchVO);
		model.addAttribute("orgFileName", fileList.get(0).getOrgFileName());
		
		return "sample/testListUpdate";
	}

	// 글 수정
	@PostMapping(value = "/updateTest.do")
	public String updateTest(@ModelAttribute("searchVO") SampleDefaultVO searchVO, ModelMap model, MultipartFile[] file) throws Exception {
		System.out.println("********UPDATE TEST**********");
		System.out.println("제목 : " + searchVO.getTitle());
		System.out.println("작성자 : " + searchVO.getWriter());
		System.out.println("내용 : " + searchVO.getContent());
		System.out.println("아이디 : " + searchVO.getIdx());
		System.out.println("code" + searchVO.getCode());
		System.out.println("날짜 : " + searchVO.getRegDate());
		System.out.println("********UPDATE END **********");
		
		sampleService.updateTest(searchVO);
		
		return "redirect:/testList.do";

	}

	// 글 삭제
	@RequestMapping(value = "/deleteTest.do")
	public String deleteTest(SampleVO sampleVO, @ModelAttribute("searchVO") SampleDefaultVO searchVO) throws Exception {
		System.out.println("********DELETE TEST**********");

		sampleService.deleteTest(searchVO);
		return "forward:/testList.do";
	}


}
