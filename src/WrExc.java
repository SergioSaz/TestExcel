import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class WrExc{
	public static void main(String[] args) throws Exception {
		ArrayList<DataModel> dataModel = new ArrayList();
		
		HSSFWorkbook workBook = new HSSFWorkbook(new FileInputStream("C:\\Users\\User\\Desktop\\Сазонов С\\Java\\Test.xls"));
		HSSFSheet sheet = workBook.getSheetAt(0);
		Iterator<Row> rows = sheet.rowIterator();
		if(rows.hasNext())
	       {
	           rows.next();
	       }
		while(rows.hasNext())
        {	
			DataModel vrData = new DataModel();
            HSSFRow row = (HSSFRow) rows.next();
            
            HSSFCell timeCell = row.getCell(0);
            vrData.setDate(timeCell.getStringCellValue());
            
            HSSFCell btcCell = row.getCell(1);
            vrData.setBtc(btcCell.getNumericCellValue());
            
            HSSFCell usdCell = row.getCell(2);
            vrData.setUsd(usdCell.getNumericCellValue());
            
            HSSFCell eurCell = row.getCell(3);
            vrData.setEur(eurCell.getNumericCellValue());
            
            dataModel.add(vrData);
        }
		workBook.close();
		dataModel.add(Start.download());
		
		HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheetWr = workbook.createSheet("Старый лист");
        
        int rowCount = 1;
        Row row = sheetWr.createRow(0);
        
        Cell timeWrText = row.createCell(0);
        timeWrText.setCellValue("Время");
        
        Cell btcWrText = row.createCell(1);
    	btcWrText.setCellValue("BTC");
    
    	Cell usdWrText = row.createCell(2);
    	usdWrText.setCellValue("USD");
    
    	Cell eurWrText = row.createCell(3);
    	eurWrText.setCellValue("EUR");
        
        for(DataModel d : dataModel){
            
        	row = sheetWr.createRow(rowCount);
        	
        	Cell timeWr = row.createCell(0);
        	timeWr.setCellValue(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        	
        	Cell btcWr = row.createCell(1);
        	btcWr.setCellValue(d.getBtc());
        
        	Cell usdWr = row.createCell(2);
        	usdWr.setCellValue(d.getUsd());
        
        	Cell eurWr = row.createCell(3);
        	eurWr.setCellValue(d.getEur());
        	
        	rowCount++;
        }

        workbook.write(new FileOutputStream(new File("C:\\Users\\User\\Desktop\\Сазонов С\\Java\\Test.xls")));
        workbook.close();
		
        System.out.println("Итерация окончена " + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        Thread.sleep(10000);
        main(null);
		
	}
}