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

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

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


	/**
	 * 글을 등록한다.
	 * @param vo - 등록할 정보가 담긴 SampleVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertSample(SampleVO vo) throws Exception {
		return (String) insert("sampleDAO.insertSample", vo);
	}

	/**
	 * 글을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
	public void updateSample(SampleVO vo) throws Exception {
		update("sampleDAO.updateSample", vo);
	}

	/**
	 * 글을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
	public void deleteSample(SampleVO vo) throws Exception {
		delete("sampleDAO.deleteSample", vo);
	}

	/**
	 * 글을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SampleVO
	 * @return 조회한 글
	 * @exception Exception
	 */
	public SampleVO selectSample(SampleVO vo) throws Exception {
		return (SampleVO) select("sampleDAO.selectSample", vo);
	}

	/**
	 * 글 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 글 목록
	 * @exception Exception
	 */
	public List<?> selectSampleList(SampleDefaultVO searchVO) throws Exception {
		return list("sampleDAO.selectSampleList", searchVO);
	}

	/**
	 * 글 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 글 총 갯수
	 * @exception
	 */
	public int selectSampleListTotCnt(SampleDefaultVO searchVO) {
		return (Integer) select("sampleDAO.selectSampleListTotCnt", searchVO);
	}
	
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
		System.out.println("********UPDATE DAO**********");
		System.out.println("제목 : " +  searchVO.getTitle());
		System.out.println("작성자 : " + searchVO.getWriter());
		System.out.println("내용 : " + searchVO.getContent());
		System.out.println("아이디 : " + searchVO.getIdx());
		System.out.println("날짜 : " + searchVO.getRegDate());

		update("sampleDAO.updateTest", searchVO);
	}
	//delete test
	public void deleteTest(SampleDefaultVO searchVO) throws Exception {
		delete("sampleDAO.deleteTest", searchVO);
	}

}