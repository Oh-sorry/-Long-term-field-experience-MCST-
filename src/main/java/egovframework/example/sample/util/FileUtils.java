
package egovframework.example.sample.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
  
@Component("fileUtils")
public class FileUtils {
	/* @Resource(name="uploadPath") */
	  String uploadPath;
	  public List<Map<String, Object>> parseFileInfo(Map<String,Object> map,MultipartFile[]file) throws Exception {
		  String boardIDX = String.valueOf(map.get("code"));

		  List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();

		  File target = new File(uploadPath);
		  if(!target.exists()) target.mkdirs();
		  
		  for(int i=0; i<file.length; i++) {
			  String orgFileName = file[i].getOriginalFilename();
			  String orgFileExtension = orgFileName.substring(orgFileName.lastIndexOf("."));
			  String saveFileName = UUID.randomUUID().toString().replaceAll("-", "") + orgFileExtension;
			  Long saveFileSize = file[i].getSize();
			  
			  System.out.println("================== file start ==================");
			  System.out.println("파일 실제 이름: "+orgFileName);
			  System.out.println("파일 저장 이름: "+saveFileName);
			  System.out.println("파일 크기: "+saveFileSize);
			  System.out.println("content type: "+file[i].getContentType());
			  System.out.println("================== file   END ==================");
			  
			  target = new File(uploadPath, saveFileName);
			  file[i].transferTo(target);
			  
			  Map<String, Object> fileInfo = new HashMap<String, Object>();
			  
			  fileInfo.put("boarIdx",boardIDX);
			  fileInfo.put("orgFileName",orgFileName);
			  fileInfo.put("saveFileName",saveFileName);
			  fileInfo.put("fileSize",saveFileSize);
			  fileList.add(fileInfo);
		}
	return fileList;
	}
}
