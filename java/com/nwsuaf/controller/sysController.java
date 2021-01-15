package com.nwsuaf.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nwsuaf.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nwsuaf.entity.Book;
import com.nwsuaf.entity.LW;
import com.nwsuaf.entity.Lend;
import com.nwsuaf.entity.User;
import com.nwsuaf.entity.ZL;
import com.nwsuaf.utils.App;
import com.nwsuaf.utils.ExcelIm;
import com.nwsuaf.utils.ReadFiles;
import com.nwsuaf.utils.exception;

@Controller
public class sysController {
	ReaderService readerService;
	@Resource
	UserService userservice;
	@Resource
	BookService bookservice;
	@Resource
	LWService lwservice;
	@Resource
	ZLService zlservice;
	int pagenum = 0;

	String usr_id;
	
	@ExceptionHandler({java.lang.NullPointerException.class,java.lang.IllegalStateException.class})
	public String MyException(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		PrintWriter writer = response.getWriter();
		String msg = null;
		msg = "alert( '数据有错误，请重试！' );location.href='sysAdmi'";
		writer.print("<script type='text/javascript'>" + msg + "</script>");
		writer.flush();
		writer.close();
		return "sysAdmi";
	}
	
	@ExceptionHandler({exception.class})
	public String MyException1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		PrintWriter writer = response.getWriter();
		String msg = null;
		msg = "alert( '最大上传20M的数据，请重试！' );location.href='sysAdmi'";
		writer.print("<script type='text/javascript'>" + msg + "</script>");
		writer.flush();
		writer.close();
		return "sysAdmi";
	}

	@RequestMapping("/insertUserJSP.do")
	public String index1(HttpServletRequest request, ModelMap model) {
		return "sys-usr-add";
	}
	
	@RequestMapping("/findAllUser.do")
	public String index(HttpServletRequest request, ModelMap model) {
		return "sys-index";
	}
		
	@RequestMapping("/sysAdmi")
	public String NoticeJSP(HttpServletRequest request, ModelMap model) {
		return "sysAdmi";
	}
	
	@RequestMapping("/addUser")
	public String addU(@RequestParam("fileToUpload") MultipartFile uploadFile, HttpServletRequest request, HttpServletResponse response,ModelMap model) throws Exception {
		
		if(uploadFile.getSize()>20971519) {
			System.err.println(uploadFile.getSize());
			throw new exception("文件过大");
		}
		byte [] byteArr=uploadFile.getBytes();
		InputStream in = new ByteArrayInputStream(byteArr);
		List<User> list = new ExcelIm().readExcel(new File(uploadFile.getOriginalFilename()),in);
		
		for(User u:list) {
			userservice.insertUser(u.getCert_id(),u.getName(),u.getSex(),u.getBirthday(),u.getDept(),u.getOccupation(),u.getPassword(),u.getUsr_state());
		}
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		PrintWriter writer = response.getWriter();
		String msg = null;
		msg = "alert( '用户信息导入成功！' );location.href='sysAdmi'";
		writer.print("<script type='text/javascript'>" + msg + "</script>");
		writer.flush();
		writer.close();
		return "sysAdmi";
	}

	@RequestMapping("/addBooks")
	public String addBooks(@RequestParam("fileToUpload") MultipartFile uploadFile, HttpServletRequest request, HttpServletResponse response,ModelMap model) throws Exception {
		byte [] byteArr=uploadFile.getBytes();
		InputStream in = new ByteArrayInputStream(byteArr);
		List<Book> list = new ExcelIm().readExcel_book(new File(uploadFile.getOriginalFilename()),in);
		
		for(Book u:list) {
			bookservice.addBook(u.getMARC_REC_NO(),u.getBOOK_CALL_NO(),u.getBOOK_TITLE(),u.getBOOK_AUTHOR(),
					u.getBOOK_PUBLISHER(),u.getBOOK_PUB_YEAR(),u.getCREATE_DATE(),u.getMARC_USE_FLAG());
		}
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		PrintWriter writer = response.getWriter();
		String msg = null;
		msg = "alert( '书籍信息导入成功！' );location.href='sysAdmi'";
		writer.print("<script type='text/javascript'>" + msg + "</script>");
		writer.flush();
		writer.close();
		return "sysAdmi";
	}
	
	@RequestMapping("/addLW")
	public String addlw(@RequestParam("fileToUpload") MultipartFile uploadFile, HttpServletRequest request, HttpServletResponse response,ModelMap model) throws Exception {
		byte [] byteArr=uploadFile.getBytes();
		InputStream in = new ByteArrayInputStream(byteArr);
		List<LW> list = new ExcelIm().readExcel_lw(new File(uploadFile.getOriginalFilename()),in);
		
		for(LW u:list) {
			lwservice.addLW(u.getSubject(),u.getAuthor(),u.getType(),u.getAbstract1(),u.getDownload_num()
					,u.getQuote_num(),u.getPeriodical(),u.getPublish_time(),u.getKeyWord(),u.getKey1()
					,u.getKey2(),u.getKey3(),u.getKey4(),u.getKey5(),u.getKey6()
					,u.getKey7(),u.getFund(),u.getUnit(),lwservice.LWcount()+1);
		}
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		PrintWriter writer = response.getWriter();
		String msg = null;
		msg = "alert( '论文信息导入成功！' );location.href='sysAdmi'";
		writer.print("<script type='text/javascript'>" + msg + "</script>");
		writer.flush();
		writer.close();
		return "sysAdmi";
	}
	
	@RequestMapping("/addZL")
	public String addzl(@RequestParam("fileToUpload") MultipartFile uploadFile, HttpServletRequest request, HttpServletResponse response,ModelMap model) throws Exception {
		byte [] byteArr=uploadFile.getBytes();
		InputStream in = new ByteArrayInputStream(byteArr);
		List<ZL> list = new ExcelIm().readExcel_zl(new File(uploadFile.getOriginalFilename()),in);
		
		for(ZL u:list) {
			zlservice.addZL(u.getSqnum(),u.getTitle(),u.getSq_day(),u.getGknum(),u.getGk_day()
					,u.getWriter(),u.getDljg(),u.getDlr(),u.getSummary(),u.getZqx());
		}
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		PrintWriter writer = response.getWriter();
		String msg = null;
		msg = "alert( '专利信息导入成功！' );location.href='sysAdmi'";
		writer.print("<script type='text/javascript'>" + msg + "</script>");
		writer.flush();
		writer.close();
		return "sysAdmi";
	}
	
	@RequestMapping("/addlend")
	public String addlend(@RequestParam("fileToUpload") MultipartFile uploadFile, HttpServletRequest request, HttpServletResponse response,ModelMap model) throws Exception {
		byte [] byteArr=uploadFile.getBytes();
		InputStream in = new ByteArrayInputStream(byteArr);
		List<Lend> list = new ExcelIm().readExcel_lend(new File(uploadFile.getOriginalFilename()),in);
		
		for(Lend u:list) {
			bookservice.addlend(u.getCERT_ID_F(),u.getPROP_NO_F(),u.getLEND_DATE(),u.getRET_DATE(),
					u.getRENEW_TIMES(),u.getMARC_REC_NO_F());
		}
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		PrintWriter writer = response.getWriter();
		String msg = null;
		msg = "alert( '借阅数据导入成功！' );location.href='sysAdmi'";
		writer.print("<script type='text/javascript'>" + msg + "</script>");
		writer.flush();
		writer.close();
		return "sysAdmi";
	}
	
	@RequestMapping("/activeUser")
	public String activeUser(HttpServletRequest request, ModelMap model) {
		usr_id = String.valueOf(request.getParameter("usr_id"));
		userservice.activeUser(usr_id);
		pagenum = 0;
		ArrayList<User> listUser = userservice.findUser("", pagenum);
		model.addAttribute("listUser", listUser);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", userservice.usercount(usr_id) / 15);
		model.addAttribute("jian", 1);
		return "sys-usr-find";
	}

	@RequestMapping("/dongjie")
	public String dongjie(HttpServletRequest request, ModelMap model) {
		usr_id = String.valueOf(request.getParameter("usr_id"));
		userservice.dongjie(usr_id);
		pagenum = 0;
		ArrayList<User> listUser = userservice.findUser("", pagenum);
		model.addAttribute("listUser", listUser);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", userservice.usercount(usr_id) / 15);
		model.addAttribute("jian", 1);
		return "sys-usr-find";
	}
	
	@RequestMapping("/updateUser")
	public String updateUser(HttpServletRequest request, ModelMap model) {
		User findUser = userservice.findUser(request.getParameter("usr_id"),0).get(0);
		model.addAttribute("user",findUser);
		return "sys-usr-edit";
	}
//	@RequestMapping("sys-updateInform")
//	public String sysupdateReader(String usr_id, String usr_passwd, String age, String xy, String sex,String occupation, Model model,
//			HttpServletRequest request, HttpServletResponse response) throws IOException {
//			//String usr_id = (String) request.getParameter("usr_id");
//			readerService.sys_updateReader(usr_passwd, age, xy, sex, usr_id,occupation);
//			request.getSession().removeAttribute("code");
//			response.setContentType("text/html;charset=gb2312");
//			PrintWriter out = response.getWriter();
//			PrintWriter writer = response.getWriter();
//			String msg = null;
//			msg = "alert( '信息修改成功。' );location.href='findAllReader.do'";
//			writer.print("<script type='text/javascript'>" + msg + "</script>");
//			writer.flush();
//			writer.close();
//			
//			return "sys-usr-find";
//
//	}
	
	@RequestMapping("/deleteUser")
	public String deleteUser(HttpServletRequest request, HttpServletResponse response,ModelMap model) throws IOException{
		String usr_id = (String) request.getParameter("usr_id");
		userservice.deleteByID(usr_id);
		request.getSession().removeAttribute("code");
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		PrintWriter writer = response.getWriter();
		String msg = null;
		msg = "alert( '删除用户成功。' );location.href='findAllUser.do'";
		writer.print("<script type='text/javascript'>" + msg + "</script>");
		writer.flush();
		writer.close();
		return "sys-index";
	}

	@RequestMapping("/findUserById.do")
	public String findU(HttpServletRequest request, ModelMap model) {
		usr_id = String.valueOf(request.getParameter("usr_id"));
		pagenum = 0;
		ArrayList<User> listUser = userservice.findUser(usr_id, pagenum);
		model.addAttribute("listUser", listUser);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", userservice.usercount(usr_id) / 15);
		System.err.println(userservice.usercount(usr_id));
		model.addAttribute("jian", 1);
		return "sys-usr-find";
	}

	@RequestMapping("/findUserByIddn")
	public String findUdn(HttpServletRequest request, Model model) {
		pagenum += 15;
		ArrayList<User> listUser = userservice.findUser(usr_id, pagenum);
		model.addAttribute("listUser", listUser);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", userservice.usercount(usr_id) / 15);
		model.addAttribute("jian", 1);
		return "sys-usr-find";
	}

	@RequestMapping("/findUserByIdup")
	public String findUup(HttpServletRequest request, Model model) {
		pagenum -= 15;
		ArrayList<User> listUser = userservice.findUser(usr_id, pagenum);
		model.addAttribute("listUser", listUser);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", userservice.usercount(usr_id) / 15);
		model.addAttribute("jian", 1);
		return "sys-usr-find";
	}

	@RequestMapping("/findUserByIdnum")
	public String findUnum(HttpServletRequest request, Model model) {
		String num;
		num = request.getParameter("pagenum");
		if (num != null) {
			pagenum = (Integer.valueOf(num) - 1) * 15;
		} else if (request.getParameter("jiansuo") != null) {
			ArrayList<Book> bookinquiry = userservice.userjiansuo(usr_id);
			model.addAttribute("bookinquiry", bookinquiry);
			model.addAttribute("jian", 0);
			return "user-bookinquiry";
		} else {
			num = request.getParameter("pn");
			pagenum = (Integer.valueOf(num) - 1) * 15;
		}
		ArrayList<User> listUser = userservice.findUser(usr_id, pagenum);
		model.addAttribute("listUser", listUser);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", userservice.usercount(usr_id) / 15);
		model.addAttribute("jian", 1);
		return "sys-usr-find";
	}
	
	@RequestMapping("/sys-lendinquiry")
	public String sysBookInquiry(HttpServletRequest request, Model model) {
		pagenum =0;
		ArrayList<Lend> bookinquiry = bookservice.sysLendInquiry(pagenum);
		model.addAttribute("bookinquiry", bookinquiry);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", bookservice.syscount() / 15);
		model.addAttribute("jian", 1);
		return "sys-lend";
	}

	@RequestMapping("/sys-lendinquirydn")
	public String sysBookInquirydn(HttpServletRequest request, Model model) {
		pagenum += 15;
		ArrayList<Lend> bookinquiry = bookservice.sysLendInquiry(pagenum);
		model.addAttribute("bookinquiry", bookinquiry);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", bookservice.syscount() / 15);
		model.addAttribute("jian", 1);
		return "sys-lend";
	}

	@RequestMapping("/sys-lendinquiryup")
	public String sysBookInquiryup(HttpServletRequest request, Model model) {
		pagenum -= 15;
		ArrayList<Lend> bookinquiry = bookservice.sysLendInquiry(pagenum);
		model.addAttribute("bookinquiry", bookinquiry);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", bookservice.syscount() / 15);
		model.addAttribute("jian", 1);
		return "sys-lend";
	}

	@RequestMapping("/sys-lendinquirynum")
	public String sysBookInquirynum(HttpServletRequest request, Model model) {
		String num;
		num = request.getParameter("pagenum");
		if (num != null) {
			pagenum = (Integer.valueOf(num) - 1) * 15;
		
		} else {
			num = request.getParameter("pn");
			pagenum = (Integer.valueOf(num) - 1) * 15;
		}
		ArrayList<Lend> bookinquiry = bookservice.sysLendInquiry(pagenum);
		model.addAttribute("bookinquiry", bookinquiry);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", bookservice.syscount() / 15);
		model.addAttribute("jian", 1);
		return "sys-lend";
	}

	@RequestMapping("/deleteLend")
	public String deleteLend(HttpServletRequest request, Model model) {
		bookservice.deleteLend(request.getParameter("id"));
		ArrayList<Lend> bookinquiry = bookservice.sysLendInquiry(pagenum);
		model.addAttribute("bookinquiry", bookinquiry);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", bookservice.syscount() / 15);
		model.addAttribute("jian", 1);
		return "sys-lend";
	}
	
	@RequestMapping("updatebq")
	public String updatebq(HttpServletRequest request,HttpServletResponse response, Model model) throws IOException {
		ArrayList<String> ids = userservice.findlendxinxi("");
		String[] books = ReadFiles.c;
		for(String mid:ids) {
			InputStream input = null;
			OutputStream output = null;
			try {

				File f = new File("c:/file/word/" + mid);
				if (f.exists()) {
				} else {
					f.mkdir();
				}
				input = new FileInputStream("C:/file/a.txt");
				output = new FileOutputStream("C:/file/word/" + mid + "/a.txt");
				byte[] buf = new byte[1024];
				int bytesRead = 0;
				while ((bytesRead = input.read(buf)) != -1) {
					output.write(buf, 0, bytesRead);
				}
			} finally {
				input.close();
				output.close();
			}
			for (int i = 0; i < books.length; i++) {
				// System.out.println(ReadFiles.c[i]+" "+i);
				ReadFiles.c[i] = "";
			}
			for (int i = 0; i < 10; i++) {
				// System.out.println(ReadFiles.c[i]+" "+i);
				ReadFiles.s[i] = "";
				ReadFiles.v[i] = "";
			}
			ArrayList<String> getbooks = userservice.getbooks(mid);
			App.startR(mid, getbooks);
			ReadFiles.biaoqian(mid);
			userservice.insertKey(mid, ReadFiles.s[0], ReadFiles.s[1], ReadFiles.s[2], ReadFiles.s[3], ReadFiles.s[4],
					ReadFiles.s[5], ReadFiles.s[6], ReadFiles.s[7], ReadFiles.s[8], ReadFiles.s[9], ReadFiles.v[0],
					ReadFiles.v[1], ReadFiles.v[2], ReadFiles.v[3], ReadFiles.v[4], ReadFiles.v[5], ReadFiles.v[6],
					ReadFiles.v[7], ReadFiles.v[8], ReadFiles.v[9]);
		}
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		PrintWriter writer = response.getWriter();
		String msg = null;
		msg = "alert( '更新图书标签成功！' );location.href='sysAdmi'";
		writer.print("<script type='text/javascript'>" + msg + "</script>");
		writer.flush();
		writer.close();
		return "sysAdmi";
	}
}
