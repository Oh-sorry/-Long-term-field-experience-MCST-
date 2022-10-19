package egovframework.example.sample.web;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.SampleDefaultVO;

@Controller
public class MemberController {
	
	/** EgovSampleService */
	@Resource(name = "sampleService")
	private EgovSampleService sampleService;

	// login page

	@RequestMapping(value ="login.do")
	public String login() {
		return "sample/login";
	}

	// login

	@RequestMapping(value = "loginCheck.do")
	public ModelAndView loginCheck(@ModelAttribute("searchVO") SampleDefaultVO searchVO, HttpSession session) throws Exception {
		boolean result = sampleService.loginCheck(searchVO, session);
		ModelAndView mav = new ModelAndView();
		if(result==true) {
			mav.setViewName("redirect:testList.do");
			mav.addObject("msg", "success");
		} else {
			mav.setViewName("sample/login");
			mav.addObject("msg", "failure");
		}
		return mav;
	}

	// logout

	@RequestMapping(value = "logout.do")
	public ModelAndView logout(@ModelAttribute("searchVO") SampleDefaultVO searchVO, HttpSession session) {
		sampleService.logout(session);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:testList.do");
		mav.addObject("msg", "logout");
		return mav;
	}

	// 회원 등록 페이지

	@RequestMapping(value = "memberWrite.do")
	public String memberWrite() {
		return "sample/memberWrite";
	}

	// 회원 등록

	@RequestMapping(value = "memberInsert.do")
	public String memberInsert(@ModelAttribute SampleDefaultVO searchVO) throws Exception {
		sampleService.memberInsert(searchVO);
		
		System.out.println("===================================");
		System.out.println(searchVO.getUserId());
		System.out.println(searchVO.getUserPw());
		System.out.println(searchVO.getUserName());
		System.out.println(searchVO.getUserEmail());
		System.out.println("===================================");
		
		return "redirect:testList.do";
	}
	//회원 수정 페이지
	@RequestMapping(value= "memberUpdatePage.do")
	public String memberUpdatePage(String userId, ModelMap model, @ModelAttribute SampleDefaultVO searchVO) throws Exception {
		model.addAttribute("list", sampleService.viewUpdate(userId));
		System.out.println("수정 아이디 : "+searchVO.getUserId());
		return "sample/memberUpdatePage";
	}
	
	// 회원 수정
	@RequestMapping(value="memberUpdate.do")
	public String memberUpdate(@ModelAttribute SampleDefaultVO searchVO, ModelMap model) {
		boolean result = sampleService.checkPw(searchVO.getUserId(), searchVO.getUserPw());
		if(result) {
			sampleService.memberUpdate(searchVO);
			return "redirect:testList.do";
		} else {
			
			model.addAttribute("message", "비밀번호 불일치");
			return "redirect:memberUpdatePage.do?userId="+searchVO.getUserId();
		}
	}
	// 회원 삭제
	@RequestMapping(value="memberDelete.do")
	public String memberDelete(@RequestParam String userId, @RequestParam String userPw, ModelMap model) {
		boolean result = sampleService.checkPw(userId, userPw);
		if(result) {
			sampleService.memberDelete(userId);
			return "redirect:login.do";
		} else {
			model.addAttribute("message", "비밀번호 불일치");
			return "redirect:memberUpdatePage.do?userId="+userId;
		}
	}
}
