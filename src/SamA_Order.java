import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	
	public ArrayList<String> production_code;
	public ArrayList<String> combination;
	public ArrayList<String> thickness;
	public ArrayList<String> width;		  
	public ArrayList<String> length;
	public ArrayList<String> alloy1; //alloy2 라는걸 고려해야되는건지?
	public ArrayList<String> alloy2; //alloy2 라는걸 고려해야되는건지?
	public ArrayList<String> temper;
	public ArrayList<String> gonchi; // 권치?
	public ArrayList<String> inner_diameter; 
	public ArrayList<String> core;
	public ArrayList<String> material_company; // 원자재 제조사(원자재m)
	public ArrayList<String> material_temper; // 
	
	
	public SamA_Order(){
		production_code = new ArrayList<>();
		combination = new ArrayList<>();
		thickness = new ArrayList<>();
		width = new ArrayList<>();
		length = new ArrayList<>();
		alloy1 = new ArrayList<>();
		alloy2 = new ArrayList<>();
		temper = new ArrayList<>();
		gonchi = new ArrayList<>();
		inner_diameter = new ArrayList<>();
		core = new ArrayList<>();
		material_company = new ArrayList<>();
		material_temper = new ArrayList<>();
	}
	
	public void read_Order(String path) {
		try {
			FileInputStream file = new FileInputStream(path);
			HSSFWorkbook excel_file = new HSSFWorkbook(file); 
			
			HSSFSheet sheet= excel_file.getSheetAt(0); //read first sheet
			int row_number=sheet.getPhysicalNumberOfRows(); //check row count
			
			//check excel file
			for(int row_index = 0; row_index < row_number; row_index++) {
				
				HSSFRow row_cur = sheet.getRow(row_index); //read row_line at row_index
				if(row_cur != null) { //not end of file
					int col_number = row_cur.getPhysicalNumberOfCells(); //check col count
					
					if(row_cur.getCell(0).getCellType() == HSSFCell.CELL_TYPE_BLANK) { //line end
						break;
					}
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
						//System.out.println(row_index + "번 행 : " + col_index + "번 열 값은: " + temp);
						System.out.println(col_index);
						System.out.println(temp);				
						switch(col_index) {
						   case 2: //production code
							   production_code.add(temp);break;
						   case 3: //combi
							   combination.add(temp);break;
						   case 4: // thickness
							   thickness.add(temp);break;
						   case 5: // alloy
							   width.add(temp);break;
						   case 6: // temper
							   length.add(temp);break;
						   case 9: // weight
							   alloy1.add(temp);break;
						   case 10: // production date;
							   alloy2.add(temp);break;
						   case 11: // production date;
							   temper.add(temp);break;
						   case 12: // production date;
							   gonchi.add(temp);break;
						   case 13: // production date;
							   inner_diameter.add(temp);break;
						   case 14: // production date;
							   core.add(temp);break;
						   case 15: // production date;
							   material_company.add(temp);break;
						   case 16: // production date;
							   material_temper.add(temp);break;
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
