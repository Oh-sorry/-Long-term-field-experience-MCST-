package egovframework.example.sample.web;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class RegisterController {

	/** EgovSampleService */
	@Resource(name = "sampleService")
	private EgovSampleService sampleService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	// 등록 페이지
	@RequestMapping(value = "/testListInsert.do")
	public String testListInsert(@ModelAttribute("searchVO") SampleDefaultVO searchVO, ModelMap model)
			throws Exception {
		model.addAttribute("sampleVO", new SampleVO());

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

		System.out.println("*************** UPDATE BOARD ******************");
		System.out.println("Title : " + testListUpdate.get(0).getTitle());
		System.out.println("Writer : " + testListUpdate.get(0).getWriter());
		System.out.println("ID : " + testListUpdate.get(0).getIdx());
		System.out.println("code : " + testListUpdate.get(0).getCode());
		System.out.println("*************** UPDATE BOARD ******************");

		// 페이지 기억
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		model.addAttribute("paginationInfo", paginationInfo);

		Map result = new ObjectMapper().convertValue(searchVO, Map.class);
		model.addAttribute("searchFormData", result);
		
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
		String uploadPath = propertiesService.getString("filePath");

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

			sampleService.insertFile(searchVO, file);
		}
		return "redirect:testList.do";
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

		// 게시글 정보 update
		sampleService.updateTest(searchVO);

		// file update
		String filePath = propertiesService.getString("filePath");

		String uploadPath = filePath;

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

		return "forward:/testListDetail.do?code=searchVO.getCode()";
	}
	// 수정 페이지
	/*
	 * @GetMapping(value = "/testListUpdate.do") public String
	 * testListUpdate(@ModelAttribute("searchVO") SampleDefaultVO searchVO, ModelMap
	 * model) throws Exception {
	 * 
	 * List<SampleDefaultVO> testListUpdate =
	 * sampleService.testListUpdate(searchVO);
	 * 
	 * model.addAttribute("title", testListUpdate.get(0).getTitle());
	 * model.addAttribute("writer", testListUpdate.get(0).getWriter());
	 * model.addAttribute("content", testListUpdate.get(0).getContent());
	 * model.addAttribute("idx", testListUpdate.get(0).getIdx());
	 * model.addAttribute("code", testListUpdate.get(0).getCode());
	 * model.addAttribute("regDate", testListUpdate.get(0).getRegDate());
	 * model.addAttribute("fileId", testListUpdate.get(0).getFileId());
	 * model.addAttribute("code", testListUpdate.get(0).getCode()); // 파일 조회
	 * List<SampleDefaultVO> fileList = sampleService.fileList(searchVO);
	 * model.addAttribute("orgFileName", fileList);
	 * 
	 * System.out.println("*************** UPDATE BOARD ******************");
	 * System.out.println("Title : " + testListUpdate.get(0).getTitle());
	 * System.out.println("Writer : " + testListUpdate.get(0).getWriter());
	 * System.out.println("ID : " + testListUpdate.get(0).getIdx());
	 * System.out.println("code : " + testListUpdate.get(0).getCode());
	 * System.out.println("*************** UPDATE BOARD ******************");
	 * 
	 * return "sample/testListUpdate"; }
	 */

}
