package egovframework.example.sample.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class DetailPageController {

	/** EgovSampleService */
	@Resource(name = "sampleService")
	private EgovSampleService sampleService;

	// 상세 페이지
	@RequestMapping(value = "/testListDetail.do")
	public String testListDetail(@ModelAttribute("searchVO") SampleDefaultVO searchVO, ModelMap model,
			MultipartFile[] file) throws Exception {
		// 목록 조회
		List<SampleDefaultVO> testListDetail = sampleService.testListDetail(searchVO);

		model.addAttribute("resultList", testListDetail.get(0));

		System.out.println("=============== 게시글 조회 ====================");
		System.out.println("제목 : " + testListDetail.get(0).getTitle());
		System.out.println("작성자 : " + testListDetail.get(0).getWriter());
		System.out.println("아이디 : " + testListDetail.get(0).getIdx());
		System.out.println("=============== 게시글 조회 ====================");
		
		// 파일 조회
		List<SampleDefaultVO> fileList = sampleService.fileList(searchVO);
		model.addAttribute("orgFileName", fileList);
		
		
		System.out.println("=============== 첨부파일 조회 ===================");
		for(int i=0; i<fileList.size(); i++ ) {
			System.out.println("원본 파일 : " + fileList.get(i).getOrgFileName());
			System.out.println("저장 파일 : " + fileList.get(i).getSaveFileName());
			System.out.println("                                             ");
		}
		System.out.println("=============== 첨부파일 조회 ===================");

		// 댓글 조회
		List<SampleDefaultVO> replyList = sampleService.replyList(searchVO);
		System.out.println("*************** 댓글 갯수 ********************");
		model.addAttribute("reply", replyList);
		
		System.out.println("댓글 갯수 : " + replyList.size());
		
		System.out.println("*************** 댓글 조회 ***************");
		for(int i=0; i<replyList.size(); i++ ) {
			System.out.println("댓글아이디 : " + replyList.get(i).getRno());
			System.out.println("댓글작성자 : " + replyList.get(i).getReplyWriter());
			System.out.println("댓글작성일 : " + replyList.get(i).getReplyDate());
			System.out.println("                                             ");
		}
		System.out.println("*************** 댓글 조회  ******************");
		
		//페이지 기억
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex()); 
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "sample/testListDetail";
	}

}
