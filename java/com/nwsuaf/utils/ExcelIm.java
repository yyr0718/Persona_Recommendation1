package com.nwsuaf.utils;

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

import com.nwsuaf.entity.Book;
import com.nwsuaf.entity.LW;
import com.nwsuaf.entity.Lend;
import com.nwsuaf.entity.User;
import com.nwsuaf.entity.ZL;
import com.nwsuaf.service.UserService;

public class ExcelIm {
	public static void main(String []args) throws Exception {
		
		//new ExcelIm().readExcel("C:/reader.xlsx");
	}
	public List<User> readExcel(File file,InputStream is) throws Exception {
		Workbook hssfWorkbook = null;
		
		if (file.getName().endsWith("xlsx")) {
			
			hssfWorkbook = new XSSFWorkbook(is);// Excel 2007
		} else if (file.getName().endsWith("xls")) {
			hssfWorkbook = new HSSFWorkbook(is);// Excel 2003
		}
		// HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		// XSSFWorkbook hssfWorkbook = new XSSFWorkbook(is);
		
		// 循环工作表Sheet
		List<User> list = new ArrayList<User>();
		
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
					User user = new User();
					user.setCert_id(x1.toString());
					user.setName(x2.toString());
					user.setSex(x3.toString());
					user.setBirthday(x4.toString());
					user.setDept(x5.toString());
					user.setOccupation(x6.toString());
					user.setPassword(x7.toString());
					user.setUsr_state(x8.toString());
					user.setPhone("");
					list.add(user);
					
				}
			}
		}
		System.err.println(list);
		return list;
	}
	
	public List<Book> readExcel_book(File file,InputStream is) throws Exception {
		Workbook hssfWorkbook = null;
		if (file.getName().endsWith("xlsx")) {
			
			hssfWorkbook = new XSSFWorkbook(is);// Excel 2007
		} else if (file.getName().endsWith("xls")) {
			hssfWorkbook = new HSSFWorkbook(is);// Excel 2003
		}
		// HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		// XSSFWorkbook hssfWorkbook = new XSSFWorkbook(is);
		
		// 循环工作表Sheet
		List<Book> list = new ArrayList<Book>();
		
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
					Book Book = new Book();
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
	
	public List<LW> readExcel_lw(File file,InputStream is) throws Exception {
		Workbook hssfWorkbook = null;
		if (file.getName().endsWith("xlsx")) {
			
			hssfWorkbook = new XSSFWorkbook(is);// Excel 2007
		} else if (file.getName().endsWith("xls")) {
			hssfWorkbook = new HSSFWorkbook(is);// Excel 2003
		}
		// HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		// XSSFWorkbook hssfWorkbook = new XSSFWorkbook(is);
		
		// 循环工作表Sheet
		List<LW> list = new ArrayList<LW>();
		
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
					Cell x9 = hssfRow.getCell(8);
					Cell x10 = hssfRow.getCell(9);
					Cell x11 = hssfRow.getCell(10);
					Cell x12 = hssfRow.getCell(11);
					Cell x13 = hssfRow.getCell(12);
					Cell x14 = hssfRow.getCell(13);
					Cell x15 = hssfRow.getCell(14);
					Cell x16 = hssfRow.getCell(15);
					Cell x17 = hssfRow.getCell(16);
					Cell x18 = hssfRow.getCell(17);
					
					LW LW = new LW();
					LW.setSubject(x1.toString());
					LW.setAuthor(x2.toString());
					LW.setType(x3.toString());
					LW.setAbstract1(x4.toString());
					LW.setDownload_num(x5.toString());
					LW.setQuote_num(x6.toString());
					LW.setPeriodical(x7.toString());
					LW.setPublish_time(x8.toString());
					
					LW.setFund(x17.toString());
					LW.setUnit(x18.toString());
					if(x9==null) {
						LW.setKeyWord("");
					}
					else {
						LW.setKeyWord(x9.toString());
					}
					if(x10==null) {
						LW.setKey1("");
					}
					else {
						LW.setKey1(x10.toString());
					}
					if(x11==null) {
						LW.setKey2("");
					}
					else {
						LW.setKey2(x11.toString());
					}
					if(x12==null) {
						LW.setKey3("");
					}
					else {
						LW.setKey3(x12.toString());
					}
					if(x13==null) {
						LW.setKey4("");
					}
					else {
						LW.setKey4(x13.toString());
					}
					if(x14==null) {
						LW.setKey5("");
					}
					else {
						LW.setKey5(x14.toString());
					}
					if(x15==null) {
						LW.setKey6("");
					}
					else {
						LW.setKey6(x15.toString());
					}
					if(x16==null) {
						LW.setKey7("");
					}
					else {
						LW.setKey7(x16.toString());
					}
					list.add(LW);	
				}
			}
		}
		return list;
	}
	
	public List<ZL> readExcel_zl(File file,InputStream is) throws Exception {
		Workbook hssfWorkbook = null;
		if (file.getName().endsWith("xlsx")) {
			
			hssfWorkbook = new XSSFWorkbook(is);// Excel 2007
		} else if (file.getName().endsWith("xls")) {
			hssfWorkbook = new HSSFWorkbook(is);// Excel 2003
		}
		// HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		// XSSFWorkbook hssfWorkbook = new XSSFWorkbook(is);
		
		// 循环工作表Sheet
		List<ZL> list = new ArrayList<ZL>();
		
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
					Cell x9 = hssfRow.getCell(8);
					Cell x10 = hssfRow.getCell(9);
					ZL ZL = new ZL();
					ZL.setSqnum(x1.toString());
					ZL.setTitle(x2.toString());
					ZL.setSq_day(x3.toString());
					ZL.setGknum(x4.toString());
					ZL.setGk_day(x5.toString());
					ZL.setWriter(x6.toString());
					if(x7==null) {
						ZL.setDljg("");
					}
					else {
						ZL.setDljg(x7.toString());
					}
					if(x8==null) {
						ZL.setDlr("");
					}
					else {
						ZL.setDlr(x8.toString());
					}
					ZL.setSummary(x9.toString());
					ZL.setZqx(x10.toString());
					list.add(ZL);	
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
