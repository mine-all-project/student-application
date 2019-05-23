package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.controller.IndexController;
import com.mapper.IndexMapping;
import com.service.IndexService;

public class TestClass {
	@Test
	public void testHSSF() throws Exception {
	    //  创建一个工作簿
	    HSSFWorkbook wb = new HSSFWorkbook();
	    //  创建一个工作表
	    HSSFSheet sheet = wb.createSheet();
	    //  创建字体
	    HSSFFont font1 = wb.createFont();
	    HSSFFont font2 = wb.createFont();
	    font1.setFontHeightInPoints((short) 14);
	    font1.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
	    font2.setFontHeightInPoints((short) 12);
	    font2.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
	    //  创建单元格样式
	    HSSFCellStyle css1 = wb.createCellStyle();
	    HSSFCellStyle css2 = wb.createCellStyle();
	    HSSFDataFormat df = wb.createDataFormat();
	    //  设置单元格字体及格式
	    css1.setFont(font1);
	    css1.setDataFormat(df.getFormat("#,##0.0"));
	    css2.setFont(font2);
	    css2.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));

	    //  创建行
	    for (int i = 0; i < 20; i++) {
	        HSSFRow row = sheet.createRow(i);
	        for (int j = 0; j < 10; j = j + 2) {
	            HSSFCell cell = row.createCell(j);
	            cell.setCellValue("Spring");
	            cell.setCellStyle(css1);

	            HSSFCell cell2 = row.createCell(j+1);
	            cell2.setCellValue(new HSSFRichTextString("Hello! " + j));
	            cell2.setCellStyle(css2);
	        }
	    }

	    //  写文件
	    FileOutputStream fos = new FileOutputStream("d:/1.xls");
	    wb.write(fos);
	    fos.close();
	}

	@Test
	public void testSS() throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:/1"));
		List<Map<String,Object>> moneys = (List<Map<String, Object>>) ois.readObject();
		if(moneys.size() > 0) {
			Workbook[] wbs = {new HSSFWorkbook(), new XSSFWorkbook()};
		    for (int i = 0; i < wbs.length; i++) {
		        Workbook wb = wbs[i];
		        CreationHelper creationHelper = wb.getCreationHelper();
		        Sheet sheet = wb.createSheet();
		        Row firstLine = sheet.createRow(0);
		        Set<String> keySet = moneys.get(0).keySet();
		        int j = 0;
		        for (String key : keySet) {
					Cell cell = firstLine.createCell(j);
					cell.setCellValue(creationHelper.createRichTextString(key));
					j++;
		        }
				for (int data = 0; data < moneys.size(); data++) {
					Row line = sheet.createRow(data+1);
					int n = 0;
					for (Map<String, Object> money : moneys) {
						 for (String key : keySet) {
								Cell cell = line.createCell(n);
								cell.setCellValue(creationHelper.createRichTextString(money.get(key).toString()));
								n++;
					        }
					}
				}
		        String filename = "d:/2.xlsx";
		        wb.write(new FileOutputStream(filename));
		        wb.close();
		    }
		}
	}
}
