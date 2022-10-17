package egovframework.example.sample.web;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.SampleDefaultVO;

@Controller
public class UpdatePageController {

	/** EgovSampleService */
	@Resource(name = "sampleService")
	private EgovSampleService sampleService;
	
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

			System.out.println("*************** UPDATE BOARD ******************");
			System.out.println("Title : " + testListUpdate.get(0).getTitle());
			System.out.println("Writer : " + testListUpdate.get(0).getWriter());
			System.out.println("ID : " + testListUpdate.get(0).getIdx());
			System.out.println("code : " + testListUpdate.get(0).getCode());
			System.out.println("*************** UPDATE BOARD ******************");

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

			// 게시글 정보 update
			sampleService.updateTest(searchVO);

			// file update
			String uploadPath = "C:\\Users\\aug2322\\eclipse-workspace\\board_project\\file";

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

			/* return "redirect:/testList.do"; */
			return "forward:/testListDetail.do?code=searchVO.getCode()";
		}
}