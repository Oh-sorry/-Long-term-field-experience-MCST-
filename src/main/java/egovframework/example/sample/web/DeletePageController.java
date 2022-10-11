package egovframework.example.sample.web;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.SampleDefaultVO;

@Controller
public class DeletePageController {

	/** EgovSampleService */
	@Resource(name = "sampleService")
	private EgovSampleService sampleService;
	
	// 글 삭제
		@RequestMapping(value = "/deleteTest.do")
		public String deleteTest(@ModelAttribute("searchVO") SampleDefaultVO searchVO) throws Exception {
			System.out.println("********DELETE BOARD**********");

			// 글 정보 삭제
			sampleService.deleteTest(searchVO);
			// DB 전체 파일 삭제
			sampleService.deleteFileAll(searchVO);
			System.out.println("********DELETE ALL FILE**********");
			// server(local) 전체 파일 삭제
			String path = "C:\\Users\\aug2322\\eclipse-workspace\\board_project\\file\\";
			File deleteFile = new File(path);
			File[] fileList = deleteFile.listFiles();

			if (deleteFile.exists()) {
				for (int i = 0; i < fileList.length; i++) {
					fileList[i].delete();
				}
			}
			// 댓글 전체 삭제
			sampleService.replyDeleteAll(searchVO);
			return "forward:/testList.do";
		}
}
