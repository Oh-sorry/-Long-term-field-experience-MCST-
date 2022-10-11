package egovframework.example.sample.web;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;

@Controller
public class InsertPageController {
	
	/** EgovSampleService */
	@Resource(name = "sampleService")
	private EgovSampleService sampleService;

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

}
