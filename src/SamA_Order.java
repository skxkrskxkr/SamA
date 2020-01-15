import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

//for xls file
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;


//for xlsx file
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SamA_Order {
	
	private String[] production_code;
	private boolean[] width_combination;  // 폭조합
	private String[] thickness;   
	private String[] width;		  
	private String[] length;
	private String[] alloy;
	private String[] temper;
	private String[] gonchi; // 권치?
	private String[] core;
	private String[] material_company; // 원자재 제조사
	
	public SamA_Order(){
		
	}
	
	public void read_Order(String path) {
		try {
			FileInputStream file = new FileInputStream(path);
			HSSFWorkbook excel_file = new HSSFWorkbook(file); 
			
			HSSFSheet sheet= excel_file.getSheetAt(0); //read first sheet
			int row_number=sheet.getPhysicalNumberOfRows(); //check row count
			
			//check excel file
			for(int row_index = 0; row_index < 5; row_index++) {
				
				HSSFRow row_cur = sheet.getRow(row_index); //read row_line at row_index
				if(row_cur != null) { //not end of file
					int col_number = row_cur.getPhysicalNumberOfCells(); //check col count
					
					for(int col_index = 0; col_index < col_number; col_index++) {
						HSSFCell col_cur = row_cur.getCell(col_index);
						String temp = "";
						
						if(col_cur != null) {
		                     switch (col_cur.getCellType()){
	                            case HSSFCell.CELL_TYPE_FORMULA:
	                            	temp=col_cur.getCellFormula();
	                                break;
	                            case HSSFCell.CELL_TYPE_NUMERIC:
	                            	if(!DateUtil.isCellDateFormatted(col_cur)) {
	                            	temp=col_cur.getNumericCellValue()+"";
	                            	}else {
	                            		Date date = col_cur.getDateCellValue();
	                            		temp = new SimpleDateFormat("yyyy-MM-dd").format(date);
	                            	}
	                                break;
	                            case HSSFCell.CELL_TYPE_STRING:
	                            	temp=col_cur.getStringCellValue()+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_BLANK:
	                            	temp=col_cur.getBooleanCellValue()+"";
	                                break;
	                            case HSSFCell.CELL_TYPE_ERROR:
	                            	temp=col_cur.getErrorCellValue()+"";
	                                break;
		                     }
						}
						System.out.println(row_index + "번 행 : " + col_index + "번 열 값은: " + temp);
					}
				}
				
				
			}
			excel_file.close();
			file.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
}
