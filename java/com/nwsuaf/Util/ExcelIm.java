package com.nwsuaf.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.nwsuaf.entity.Lend;
import com.nwsuaf.entity.Reader;
import com.nwsuaf.entity.TuiJianBook;

public class ExcelIm {
	public static void main(String []args) throws Exception {
		
		//new ExcelIm().readExcel("C:/reader.xlsx");
	}
	public List<Reader> readExcel(File file,InputStream is) throws Exception {
		Workbook hssfWorkbook = null;
		
		if (file.getName().endsWith("xlsx")) {
			
			hssfWorkbook = new XSSFWorkbook(is);// Excel 2007
		} else if (file.getName().endsWith("xls")) {
			hssfWorkbook = new HSSFWorkbook(is);// Excel 2003
		}
		// HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		// XSSFWorkbook hssfWorkbook = new XSSFWorkbook(is);
		
		// 循环工作表Sheet
		List<Reader> list = new ArrayList<Reader>();
		
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			// HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			Sheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// 循环行Row
			
			
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				// HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				Row hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow != null) {
					Cell x1 = hssfRow.getCell(0);
					Cell x2 = hssfRow.getCell(1);
					Cell x3 = hssfRow.getCell(2);
					Cell x4 = hssfRow.getCell(3);
					Cell x5 = hssfRow.getCell(4);
					Cell x6 = hssfRow.getCell(5);
					Cell x7 = hssfRow.getCell(6);
					Cell x8 = hssfRow.getCell(7);
					Reader user = new Reader();
					user.setCert_id(x1.toString());
					user.setName(x2.toString());
					user.setSex(x3.toString());
					user.setBirthday(x4.toString());
					user.setDept(x5.toString());
					user.setOccupation(x6.toString());
					user.setPassword(x7.toString());
					user.setUsr_state(x8.toString());
					
					list.add(user);
					
				}
			}
		}
		System.err.println(list);
		return list;
	}
	
	public List<TuiJianBook> readExcel_book(File file,InputStream is) throws Exception {
		Workbook hssfWorkbook = null;
		if (file.getName().endsWith("xlsx")) {
			
			hssfWorkbook = new XSSFWorkbook(is);// Excel 2007
		} else if (file.getName().endsWith("xls")) {
			hssfWorkbook = new HSSFWorkbook(is);// Excel 2003
		}
		// HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		// XSSFWorkbook hssfWorkbook = new XSSFWorkbook(is);
		
		// 循环工作表Sheet
		List<TuiJianBook> list = new ArrayList<TuiJianBook>();
		
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			// HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			Sheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// 循环行Row
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				// HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				Row hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow != null) {
					Cell x1 = hssfRow.getCell(0);
					Cell x2 = hssfRow.getCell(1);
					Cell x3 = hssfRow.getCell(2);
					Cell x4 = hssfRow.getCell(3);
					Cell x5 = hssfRow.getCell(4);
					Cell x6 = hssfRow.getCell(5);
					Cell x7 = hssfRow.getCell(6);
					Cell x8 = hssfRow.getCell(7);
					TuiJianBook Book = new TuiJianBook();
					Book.setMARC_REC_NO(x1.toString());
					if(x2==null) {
						Book.setBOOK_CALL_NO("");
					}
					else {
						Book.setBOOK_CALL_NO(x2.toString());
					}
					if(x3==null) {
						Book.setBOOK_TITLE("");
					}
					else {
						Book.setBOOK_TITLE(x3.toString());
					}
					if(x4==null) {
						Book.setBOOK_AUTHOR("");
					}
					else {
						Book.setBOOK_AUTHOR(x4.toString());
					}
					if(x5==null) {
						Book.setBOOK_PUBLISHER("");
					}
					else {
						Book.setBOOK_PUBLISHER(x5.toString());
					}
					if(x6==null) {
						Book.setBOOK_PUB_YEAR("");
					}
					else {
						Book.setBOOK_PUB_YEAR(x6.toString());
					}
					
					if(x7==null) {
						Book.setCREATE_DATE("");
					}
					else {
						Book.setCREATE_DATE(x7.toString());
					}
					Book.setMARC_USE_FLAG(x8.toString());
					list.add(Book);	
				}
			}
		}
		return list;
	}
	public List<Lend> readExcel_lend(File file,InputStream is) throws Exception {
		Workbook hssfWorkbook = null;
		if (file.getName().endsWith("xlsx")) {
			
			hssfWorkbook = new XSSFWorkbook(is);// Excel 2007
		} else if (file.getName().endsWith("xls")) {
			hssfWorkbook = new HSSFWorkbook(is);// Excel 2003
		}
		// HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		// XSSFWorkbook hssfWorkbook = new XSSFWorkbook(is);
		
		// 循环工作表Sheet
		List<Lend> list = new ArrayList<Lend>();
		
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			// HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			Sheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// 循环行Row
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				// HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				Row hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow != null) {
					Cell x1 = hssfRow.getCell(0);
					Cell x2 = hssfRow.getCell(1);
					Cell x3 = hssfRow.getCell(2);
					Cell x4 = hssfRow.getCell(3);
					Cell x5 = hssfRow.getCell(4);
					Cell x6 = hssfRow.getCell(5);

					Lend Lend = new Lend();
					Lend.setCERT_ID_F(x1.toString());
					Lend.setPROP_NO_F(x2.toString());
					Lend.setLEND_DATE(x3.toString());
					Lend.setRET_DATE(x4.toString());
					Lend.setRENEW_TIMES(x5.toString());
					Lend.setMARC_REC_NO_F(x6.toString());
					list.add(Lend);	
				}
			}
		}
		return list;
	}
	
}
