package egovframework.example.sample.service.impl;

import java.util.List;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.SampleDefaultVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("sampleService")
public class EgovSampleServiceImpl extends EgovAbstractServiceImpl implements EgovSampleService {

	/** SampleDAO */
	// TODO ibatis 사용
	@Resource(name = "sampleDAO")
	private SampleDAO sampleDAO;

	/** ID Generation */
	@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;

	/** 메인 페이지 **/
	@Override
	public List<SampleDefaultVO> testList(SampleDefaultVO searchVO) throws Exception {
		return sampleDAO.testList(searchVO);
	}

	// paging test cnt
	@Override
	public int testListCnt(SampleDefaultVO searchVO) throws Exception {
		return sampleDAO.testListCnt(searchVO);
	}

	// testList detail
	@Override
	public List<SampleDefaultVO> testListDetail(SampleDefaultVO searchVO) throws Exception {
		return sampleDAO.testListDetail(searchVO);
	}

	// testList Insert page
	@Override
	public String testListInsert(SampleDefaultVO searchVO) throws Exception {
		return sampleDAO.testListInsert(searchVO);
	}

	// insert test
	@Override
	public void insertTest(SampleDefaultVO searchVO) throws Exception {
		sampleDAO.insertTest(searchVO);
	}

	// update page
	@Override
	public List<SampleDefaultVO> testListUpdate(SampleDefaultVO searchVO) throws Exception {
		return sampleDAO.testListUpdate(searchVO);
	}

	// update
	@Override
	public void updateTest(SampleDefaultVO searchVO) throws Exception {
		sampleDAO.updateTest(searchVO);
	}

	// delete
	@Override
	public void deleteTest(SampleDefaultVO searchVO) throws Exception {
		sampleDAO.deleteTest(searchVO);
	}

	// insert file
	@Override
	public void insertFile(SampleDefaultVO searchVO, MultipartFile[] file) throws Exception {
		sampleDAO.insertFile(searchVO, file);
	}

	// detail file
	@Override
	public List<SampleDefaultVO> fileList(SampleDefaultVO searchVO) throws Exception {
		return sampleDAO.fileList(searchVO);
	}

	// update file
	@Override
	public void updateFile(SampleDefaultVO searchVO, MultipartFile[] file) throws Exception {
		sampleDAO.updateFile(searchVO, file);
	}

	// delete file
	@Override
	public void deleteFile(Integer fileId) throws Exception {
		sampleDAO.deleteFile(fileId);
	}

	// delete all file
	@Override
	public void deleteFileAll(SampleDefaultVO searchVO) throws Exception {
		sampleDAO.deleteFileAll(searchVO);
	}

	// excel
	@Override
	public List<SampleDefaultVO> excelDownload(SampleDefaultVO searchVO) throws Exception {
		return sampleDAO.excelDownload(searchVO);
	}

	// 댓글 관리
	// 댓글 조회
	@Override
	public List<SampleDefaultVO> replyList(SampleDefaultVO searchVO) throws Exception {
		return sampleDAO.replyList(searchVO);
	} 
	
	// 댓글 작성
	@Override
	public void replyInsert(SampleDefaultVO searchVO) throws Exception {
		sampleDAO.replyInsert(searchVO); 
	}
	
	// 댓글 수정 
	//댓글 삭제 
	@Override
	public void replyDelete(Integer rno) throws Exception {
		System.out.println("---------replydelimpl----------");
		sampleDAO.replyDelete(rno); 
	}
	//글 삭제 시 댓글 전체 삭제
	@Override
	public void replyDeleteAll(SampleDefaultVO searchVO) {
		sampleDAO.replyDeleteAll(searchVO);
	}
	
	//회원 관리
	// 회원 로그인 체크
	@Override
	public boolean loginCheck(SampleDefaultVO searchVO, HttpSession session) throws Exception {
		boolean result = sampleDAO.loginCheck(searchVO);
		if(result) {
			SampleDefaultVO searchVO2 = viewMember(searchVO);
			session.setAttribute("userId", searchVO2.getUserId());
			session.setAttribute("userName", searchVO2.getUserName());
		}
		return result;
	}

	// 회원 로그인 정보
	@Override
	public SampleDefaultVO viewMember(SampleDefaultVO searchVO) {
		return sampleDAO.viewMember(searchVO);
	}

	// 회원 로그 아웃
	@Override
	public void logout(HttpSession session) {
		session.invalidate();
	}

	//회원 등록
	@Override
	public void memberInsert(SampleDefaultVO searchVO) {
		sampleDAO.memberInsert(searchVO);
	}
	
	//회원 수정
	@Override
	public void memberUpdate(SampleDefaultVO searchVO) {
		sampleDAO.memberUpdate(searchVO);
	}
	//회원 정보 조회
	@Override
	public SampleDefaultVO viewUpdate(String userId) {
		return sampleDAO.viewUpdate(userId);
	}
	
	//회원 정보 삭제
	@Override
	public void memberDelete(String userId) {
		sampleDAO.memberDelete(userId);
	}
	
	//회원 정보 수정 / 삭제 시 비밀번호 체크
	@Override
	public boolean checkPw(String userId, String userPw) {
		return sampleDAO.checkPw(userId, userPw);
	}
	
	@Override
	public SampleDefaultVO downloadFile(Integer fileId) {
		return (SampleDefaultVO) sampleDAO.downloadFile(fileId);
	}
}
