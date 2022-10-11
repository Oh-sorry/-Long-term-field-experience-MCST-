package egovframework.example.sample.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.SampleDefaultVO;

@Controller
public class MemberPageController {

	/** EgovSampleService */
	@Resource(name = "sampleService")
	private EgovSampleService sampleService;
	/*
	 * //loginPage
	 * 
	 * @RequestMapping(value="/loginPage.do") public String loginPage() { return
	 * "sample/home"; }
	 * 
	 * //login
	 * 
	 * @RequestMapping(value = "/login.do") public ModelAndView
	 * login(@ModelAttribute SampleDefaultVO searchVO, HttpSession session) throws
	 * Exception { boolean result = sampleService.login(searchVO, session);
	 * ModelAndView mav = new ModelAndView(); //login success if(result == true) {
	 * mav.setViewName("testList"); mav.addObject("msg", "success"); } else {
	 * //login fail mav.setViewName("sample/home"); mav.addObject("msg", "failure");
	 * } return mav; }
	 * 
	 * //logout
	 * 
	 * @RequestMapping(value="/logout.do") public ModelAndView logout(HttpSession
	 * session) throws Exception { sampleService.logout(session); ModelAndView mav =
	 * new ModelAndView(); mav.setViewName("sample/home"); mav.addObject("msg",
	 * "logout");
	 * 
	 * return mav; }
	 */
}
