package egovframework.example.sample.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.SampleDefaultVO;

@Controller
public class ReplyController {

	/** EgovSampleService */
	@Resource(name = "sampleService")
	private EgovSampleService sampleService;
	
	//댓글 입력
	@RequestMapping(value="/replyInsert.do", method=RequestMethod.POST)
	public String replyInsert(SampleDefaultVO searchVO, ModelMap model) throws Exception {
		System.out.println("========-replyinsert==========");
		sampleService.replyInsert(searchVO);
		System.out.println("========-replyinsert2==========");

		return "redirect:testListDetail.do?code=" + searchVO.getCode();
	}
	
	//댓글 수정
	
	//댓글 삭제
	@RequestMapping(value="/replyDelete.do")
	public String replyDelete(@RequestParam("rno") Integer rno, SampleDefaultVO searchVO, HttpServletRequest request) throws Exception {
		searchVO.setRno(rno);
		System.out.println("========-delete Reply==========");
		System.out.println("삭제 댓글 아이디 :" + rno);
		System.out.println("========-delete Reply==========");
		sampleService.replyDelete(rno);
		
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
}
