package egovframework.example.sample.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.activation.CommandMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @Class Name : EgovSampleServiceImpl.java
 * @Description : Sample Business Implement Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

@Service("sampleService")
public class EgovSampleServiceImpl extends EgovAbstractServiceImpl implements EgovSampleService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSampleServiceImpl.class);

	/** SampleDAO */
	// TODO ibatis 사용
	@Resource(name = "sampleDAO")
	private SampleDAO sampleDAO;

	/** ID Generation */
	@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;

	/** ver.2 testList **/
	@Override
	public List<SampleDefaultVO> testList(SampleDefaultVO searchVO) throws Exception {
		return sampleDAO.testList(searchVO);
	}
	//paging test cnt
	@Override
	public int testListCnt(SampleDefaultVO searchVO) throws Exception {
		return sampleDAO.testListCnt(searchVO);
	}
	//testlist detail
	@Override
	public List<SampleDefaultVO> testListDetail(SampleDefaultVO searchVO) throws Exception {
		return sampleDAO.testListDetail(searchVO);
	}
	
	//testList Insert page
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
	public void deleteTest(SampleDefaultVO searchVO) throws Exception{
		sampleDAO.deleteTest(searchVO);
	}
	
	//insert file
	@Override
	public void insertFile(SampleDefaultVO searchVO, MultipartFile[] file) throws Exception {
		sampleDAO.insertFile(searchVO, file);
	}
	
	//detail file
	@Override
	public List<SampleDefaultVO> fileList(SampleDefaultVO searchVO) throws Exception {
		return sampleDAO.fileList(searchVO);
	}
	
	//update file
	@Override
	public void updateFile(SampleDefaultVO searchVO, MultipartFile[] file) throws Exception {
		sampleDAO.updateFile(searchVO, file);
	}
	
	//delete file
	@Override
	public void deleteFile(Integer fileId) throws Exception{
		sampleDAO.deleteFile(fileId);
	}
	//delete all file
	@Override
	public void deleteFileAll(SampleDefaultVO searchVO) throws Exception {
		sampleDAO.deleteFileAll(searchVO);
	}
	
	//excel
	@Override
	public List<SampleDefaultVO> excelDownload(SampleDefaultVO searchVO) throws Exception {
		System.out.println("===================EXCEL IMPL START=================");
		return sampleDAO.excelDownload(searchVO);
	}
	
}
