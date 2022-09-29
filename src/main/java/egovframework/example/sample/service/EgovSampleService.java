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
package egovframework.example.sample.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Class Name : EgovSampleService.java
 * @Description : EgovSampleService Class
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
public interface EgovSampleService {

	/** ver.2 testList **/
	List<?> testList(SampleDefaultVO searchVO) throws Exception;
	
	//paging
	int testListCnt(SampleDefaultVO searchVO) throws Exception;
	
	//detail
	List<SampleDefaultVO> testListDetail(SampleDefaultVO searchVO) throws Exception;
	
	//insert page
	String testListInsert(SampleDefaultVO searchVO) throws Exception;
	
	//insert
	void insertTest(SampleDefaultVO searchVO) throws Exception;
	
	//update page
	List<SampleDefaultVO> testListUpdate(SampleDefaultVO searchVO) throws Exception;
	
	//update
	void updateTest(SampleDefaultVO searchVO) throws Exception;
	
	//delete
	void deleteTest(SampleDefaultVO searchVO) throws Exception;

	//insert file
	void insertFile(SampleDefaultVO searchVO, MultipartFile[] file) throws Exception;

	//detail file
	List<SampleDefaultVO> fileList(SampleDefaultVO searchVO) throws Exception;

	//update file
	void updateFile(SampleDefaultVO searchVO, MultipartFile[] file) throws Exception;

	//delete file
	void deleteFile(Integer fileId) throws Exception;
	//delete all file
	void deleteFileAll(SampleDefaultVO searchVO) throws Exception;

}
