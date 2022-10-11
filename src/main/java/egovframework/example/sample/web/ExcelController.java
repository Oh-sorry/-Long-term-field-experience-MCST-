package egovframework.example.sample.web;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.SampleDefaultVO;

@Controller
public class ExcelController {

	/** EgovSampleService */
	@Resource(name = "sampleService")
	private EgovSampleService sampleService;

	// 엑셀 download
	@SuppressWarnings("resource")
	@RequestMapping("/excelDownload.do")
	public void excelDownload(HttpServletResponse response, @ModelAttribute("searchVO") SampleDefaultVO searchVO) throws Exception {
		XSSFWorkbook wb = null;
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		wb = new XSSFWorkbook();
		sheet = wb.createSheet("1page");

		int cellCount = 0;
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		List<SampleDefaultVO> excelDownload = sampleService.excelDownload(searchVO);
		System.out.println("=================== EXCEL DOWNLOAD =================");

		// Header
		row = sheet.createRow(0);
		cell = row.createCell(0);
		cell.setCellValue("번호");
		cell = row.createCell(1);
		cell.setCellValue("제목");
		cell = row.createCell(2);
		cell.setCellValue("작성자");
		cell = row.createCell(3);
		cell.setCellValue("작성일");

		// Body
		System.out.println("게시글 갯수 :" + excelDownload.size());

		for (int i = 0; i < excelDownload.size(); i++) {
			row = sheet.createRow(i + 1);
			cellCount = 0;
			cell = row.createCell(cellCount++);
			cell.setCellValue(i);
			cell = row.createCell(cellCount++);
			cell.setCellValue(excelDownload.get(i).getTitle());
			cell = row.createCell(cellCount++);
			cell.setCellValue(excelDownload.get(i).getWriter());
			cell = row.createCell(cellCount++);
			cell.setCellValue(sdf.format(excelDownload.get(i).getRegDate()));
		}

		// 컨텐츠 타입과 파일명 지정
		response.setContentType("ms-vnd/excel");
		response.setHeader("Content-Disposition",
				"attachment; filename=excelTest (" + sdf1.format(System.currentTimeMillis()) + ").xlsx"); // 파일이름지정.
		// response OutputStream에 엑셀 작성
		wb.write(response.getOutputStream());
	}
}
