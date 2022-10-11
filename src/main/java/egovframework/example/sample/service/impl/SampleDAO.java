package egovframework.example.sample.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("sampleDAO")
public class SampleDAO extends EgovAbstractDAO {

	/** 메인 페이지 **/
	@SuppressWarnings("unchecked")
	public List<SampleDefaultVO> testList(SampleDefaultVO searchVO) throws Exception {
		return (List<SampleDefaultVO>) list("sampleDAO.testList", searchVO);
	}

	// paging cnt
	public int testListCnt(SampleDefaultVO searchVO) {
		return (Integer) select("sampleDAO.testListCnt", searchVO);
	}

	// testList detail
	@SuppressWarnings("unchecked")
	public List<SampleDefaultVO> testListDetail(SampleDefaultVO searchVO) throws Exception {
		return (List<SampleDefaultVO>) list("sampleDAO.testListDetail", searchVO);
	}

	// testList Insert page
	public String testListInsert(SampleDefaultVO searchVO) throws Exception {
		return (String) insert("sampleDAO.testListInsert", searchVO);
	}

	// insert test
	public void insertTest(SampleDefaultVO searchVO) throws Exception {
		insert("sampleDAO.insertTest", searchVO);
	}

	// update page
	@SuppressWarnings("unchecked")
	public List<SampleDefaultVO> testListUpdate(SampleDefaultVO searchVO) throws Exception {
		return (List<SampleDefaultVO>) list("sampleDAO.testListUpdate", searchVO);
	}

	// update test
	public void updateTest(SampleDefaultVO searchVO) throws Exception {
		update("sampleDAO.updateTest", searchVO);
	}

	// delete test
	public void deleteTest(SampleDefaultVO searchVO) throws Exception {
		delete("sampleDAO.deleteTest", searchVO);
	}

	/** 첩부 파일 **/
	// 첨부파일 업로드
	public void insertFile(SampleDefaultVO searchVO, MultipartFile[] file) throws Exception {
		insert("sampleDAO.insertFile", searchVO);
	}

	// 첨부파일 조회
	@SuppressWarnings("unchecked")
	public List<SampleDefaultVO> fileList(SampleDefaultVO searchVO) throws Exception {
		return (List<SampleDefaultVO>) list("sampleDAO.fileList", searchVO);
	}

	// 첨부파일 수정
	public void updateFile(SampleDefaultVO searchVO, MultipartFile[] file) throws Exception {
		update("sampleDAO.updateFile", searchVO);
	}

	// 첨부파일 삭제
	public void deleteFile(Integer fileId) throws Exception {
		delete("sampleDAO.deleteFile", fileId);
	}

	// 글 전체 첨부파일 삭제
	public void deleteFileAll(SampleDefaultVO searchVO) {
		delete("sampleDAO.deleteFileAll", searchVO);

	}

	// excel
	@SuppressWarnings("unchecked")
	public List<SampleDefaultVO> excelDownload(SampleDefaultVO searchVO) throws Exception {
		return (List<SampleDefaultVO>) list("sampleDAO.excelDownload", searchVO);
	}

	// 댓글 관리
	// 댓글 조회
	@SuppressWarnings("unchecked")
	public List<SampleDefaultVO> replyList(SampleDefaultVO searchVO) throws Exception {
		return (List<SampleDefaultVO>) list("sampleDAO.replyList", searchVO);
	} 
	
	// 댓글 작성 
	public void replyInsert(SampleDefaultVO searchVO) throws Exception {
		  insert("sampleDAO.replyInsert", searchVO);
	} 
	
	//댓글 수정 
	//댓글 삭제
	public void replyDelete(Integer rno) throws Exception {
		System.out.println("---------replydeldao----------");
		delete("sampleDAO.replyDelete", rno); 
	}
	// 글 삭제 시 댓글 전체 삭제
	public void replyDeleteAll(SampleDefaultVO searchVO) {
		delete("sampleDAO.replyDeleteAll", searchVO);
	}

}