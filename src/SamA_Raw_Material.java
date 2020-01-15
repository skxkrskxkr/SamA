import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;

public class SamA_Raw_Material {

	public ArrayList<String> production_code;
	public ArrayList<String> thickness;   
	public ArrayList<String> width;
	private ArrayList<String> weight;
	private ArrayList<String> length; // 계산 필요
	private ArrayList<String> alloy;
	private ArrayList<String> temper;
	public ArrayList<String> production_date;
	private ArrayList<String> material_company; 
	
	public SamA_Raw_Material() {
		production_code = new ArrayList<>();
		thickness = new ArrayList<>();
		width = new ArrayList<>();
		weight = new ArrayList<>();
		length = new ArrayList<>();
		alloy = new ArrayList<>();
		temper = new ArrayList<>();
		production_date = new ArrayList<>();
		material_company = new ArrayList<>();
	}
	// 당장은 엑셀형식이 고정되있다는 가정하에 프로그램을 진행.
	// 후에 칼럼 위치가 바뀐다는지 문제가 생기면 데이터 전처리과정 수정이 필요.
	public void read_Material(String path) {
		try {
			FileInputStream file = new FileInputStream(path);
			HSSFWorkbook excel_file = new HSSFWorkbook(file); 
			
			HSSFSheet sheet= excel_file.getSheetAt(0); //read first sheet
			int row_number=sheet.getPhysicalNumberOfRows(); //check row count
			
			//check excel file
			for(int row_index = 1; row_index < row_number; row_index++) {
				
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
						else {
							break;
						}
						//System.out.println(row_index + "번 행 : " + col_index + "번 열 값은: " + temp);
						System.out.println(col_index);
						System.out.println(temp);

						switch(col_index) {
						   case 0: //production code
							   production_code.add(temp);break;
						   case 3: //thickness
							   thickness.add(temp);break;
						   case 4: // width
							   width.add(temp);break;
						   case 5: // alloy
							   alloy.add(temp);break;
						   case 6: // temper
							   temper.add(temp);break;
						   case 7: // weight
							   weight.add(temp);break;
						   case 8: // production date;
							   production_date.add(temp);break;
						   default:
							   break;	   
						}
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
