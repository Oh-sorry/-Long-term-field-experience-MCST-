/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.example.sample.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @Class Name : SampleDAO.java
 * @Description : Sample DAO Class
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

@Repository("sampleDAO")
public class SampleDAO extends EgovAbstractDAO {
	
	/** ver.2 testList **/
	public List<?> testList(SampleDefaultVO searchVO) throws Exception{
		return list("sampleDAO.testList", searchVO);
	}
	//paging cnt
	public int testListCnt(SampleDefaultVO searchVO) {
		return (Integer) select("sampleDAO.testListCnt", searchVO);
	}
	//testList detail
	public List<SampleDefaultVO> testListDetail(SampleDefaultVO searchVO) throws Exception {
		return (List<SampleDefaultVO>) list("sampleDAO.testListDetail", searchVO);
	}
	//testList Insert page
	public String testListInsert(SampleDefaultVO searchVO) throws Exception {
		return (String) insert("sampleDAO.testListInsert", searchVO);
	}
	//insert test
	public void insertTest(SampleDefaultVO searchVO) throws Exception{
		insert("sampleDAO.insertTest", searchVO);
	}

	//update page 
	public List<SampleDefaultVO> testListUpdate(SampleDefaultVO searchVO) throws Exception {
		return (List<SampleDefaultVO>) list("sampleDAO.testListUpdate", searchVO);
	}
	//update test
	public void updateTest(SampleDefaultVO searchVO) throws Exception{
		update("sampleDAO.updateTest", searchVO);
	}
	//delete test
	public void deleteTest(SampleDefaultVO searchVO) throws Exception {
		delete("sampleDAO.deleteTest", searchVO);
	}

	/** 첩부 파일 **/
	//첨부파일 업로드
	public void insertFile(SampleDefaultVO searchVO, MultipartFile[] file) throws Exception {
		insert("sampleDAO.insertFile", searchVO);
		
		/*
		 * System.out.println("================  DAO     ====================");
		 * System.out.println("VO 파일 이름: " + searchVO.getOrgFileName());
		 * System.out.println("VO 파일 실제 이름: " + searchVO.getSaveFileName());
		 * System.out.println("===============================================");
		 */
		 
	}
	//첨부파일 조회
	public List<SampleDefaultVO> fileList(SampleDefaultVO searchVO) throws Exception {
		return (List<SampleDefaultVO>) list("sampleDAO.fileList", searchVO);
	}
	//첨부파일 수정
	public void updateFile(SampleDefaultVO searchVO, MultipartFile[] file) throws Exception {
		update("sampleDAO.updateFile", searchVO);
	}
	//첨부파일 삭제
	public void deleteFile(Integer fileId) throws Exception {
		delete("sampleDAO.deleteFile", fileId);
		/*
		 * System.out.println("====================deleteDAO=================");
		 * System.out.println("DAO 삭제파일번호 :" + fileId);
		 */
	}
	
	
	//첨부파일 삭제
	
}