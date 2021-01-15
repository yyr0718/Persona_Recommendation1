package com.nwsuaf.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nwsuaf.entity.Book;
import com.nwsuaf.entity.Lend;
import com.nwsuaf.service.BookService;
import com.nwsuaf.service.UserService;
@Controller
public class CenterController {
	int pagenum = 0;
	@Resource
	BookService bookservice;
	@Resource
	UserService centerservice;

	String inputname;
	@RequestMapping("/center-information")
	public String cenformation(HttpServletRequest request, Model model) {
		return "center-information";
	}
	@RequestMapping("center-lendinquiry")
	public String LendInquiry(HttpServletRequest request, Model model) {

		ArrayList<Lend> lendinquiry = bookservice
				.lendInquiry(String.valueOf(request.getSession().getAttribute("session")));
		model.addAttribute("lendinquiry", lendinquiry);

		return "center-lendinquiry";
	}
	@RequestMapping("center-index")
	public String index(HttpServletRequest request, ModelMap model) {
		// 调用dao层
		return "center-index";
	}
	
	@RequestMapping("center-bookinquiry")
	public String BookInquiry(HttpServletRequest request, Model model) {
		ArrayList<Book> bookinquiry = bookservice.bookInquiry(pagenum);
		model.addAttribute("bookinquiry", bookinquiry);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", bookservice.bookcount() / 15);
		model.addAttribute("jian", 1);
		return "center-bookinquiry";
	}

	@RequestMapping("center-bookinquirydn")
	public String BookInquirydn(HttpServletRequest request, Model model) {
		pagenum += 15;
		ArrayList<Book> bookinquiry = bookservice.bookInquiry(pagenum);
		model.addAttribute("bookinquiry", bookinquiry);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", bookservice.bookcount() / 15);
		model.addAttribute("jian", 1);
		return "center-bookinquiry";
	}

	@RequestMapping("center-bookinquiryup")
	public String BookInquiryup(HttpServletRequest request, Model model) {
		pagenum -= 15;
		ArrayList<Book> bookinquiry = bookservice.bookInquiry(pagenum);
		model.addAttribute("bookinquiry", bookinquiry);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", bookservice.bookcount() / 15);
		model.addAttribute("jian", 1);
		return "center-bookinquiry";
	}

	@RequestMapping("center-bookinquirynum")
	public String BookInquirynum(HttpServletRequest request, Model model) {
		String num;
		num = request.getParameter("pagenum");
		if (num != null) {
			pagenum = (Integer.valueOf(num) - 1) * 15;
		} else {
			num = request.getParameter("pn");
			pagenum = (Integer.valueOf(num) - 1) * 15;
		}
		ArrayList<Book> bookinquiry = bookservice.bookInquiry(pagenum);
		model.addAttribute("bookinquiry", bookinquiry);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", bookservice.bookcount() / 15);
		model.addAttribute("jian", 1);
		return "center-bookinquiry";
	}
	
	@RequestMapping("center-bookjiansuo")
	public String Bookjiansuo(HttpServletRequest request, Model model) {
		pagenum = 0;
		inputname = String.valueOf(request.getParameter("bookname"));
		ArrayList<Book> bookinquiry = bookservice.bookjiansuo(inputname,pagenum);
		model.addAttribute("bookinquiry", bookinquiry);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", bookservice.bookjiancount(inputname) / 15);
		model.addAttribute("jian", 1);
		return "center-bookjiansuo";
	}

	@RequestMapping("center-bookjiansuodn")
	public String Bookjiansuodn(HttpServletRequest request, Model model) {
		pagenum += 15;
		ArrayList<Book> bookinquiry = bookservice.bookjiansuo(inputname,pagenum);
		model.addAttribute("bookinquiry", bookinquiry);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", bookservice.bookjiancount(inputname) / 15);
		model.addAttribute("jian", 1);
		return "center-bookjiansuo";
	}

	@RequestMapping("center-bookjiansuoup")
	public String Bookjiansuoup(HttpServletRequest request, Model model) {
		pagenum -= 15;
		ArrayList<Book> bookinquiry = bookservice.bookjiansuo(inputname,pagenum);
		model.addAttribute("bookinquiry", bookinquiry);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", bookservice.bookjiancount(inputname) / 15);
		model.addAttribute("jian", 1);
		return "center-bookjiansuo";
	}

	@RequestMapping("center-bookjiansuonum")
	public String Bookjiansuonum(HttpServletRequest request, Model model) {
		String num;
		num = request.getParameter("pagenum");
		if (num != null) {
			pagenum = (Integer.valueOf(num) - 1) * 15;
		}else {
			num = request.getParameter("pn");
			pagenum = (Integer.valueOf(num) - 1) * 15;
		}
		ArrayList<Book> bookinquiry = bookservice.bookjiansuo(inputname,pagenum);
		model.addAttribute("bookinquiry", bookinquiry);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", bookservice.bookjiancount(inputname) / 15);
		model.addAttribute("jian", 1);
		return "center-bookjiansuo";
	}

	@RequestMapping("center-booklend")
	public String Booklend(HttpServletRequest request, Model model) throws IOException {

		String LEND_DATE;
		String PROP_NO_F;
		String CERT_ID;
		String RET_DATE;
		String MARC_REC_NO_F;
		String name;
		String BOOK_TITLE;
		CERT_ID = String.valueOf(request.getSession().getAttribute("session"));

		int random = (int) (Math.random() * 1000000) + 999999;
		PROP_NO_F = String.valueOf(random);
		Date now = new Date();
		DateFormat t = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
		LEND_DATE = t.format(now);
		String bookname = String.valueOf(request.getParameter("bookname"));
		MARC_REC_NO_F = bookservice.findbooknobyname(bookname);
		name = centerservice.findnamebyid(CERT_ID);
		bookservice.booklend(CERT_ID, PROP_NO_F, LEND_DATE, "", MARC_REC_NO_F);
		bookservice.updateflag("0", MARC_REC_NO_F);
		ArrayList<Book> bookinquiry = bookservice.bookjiansuo(inputname,pagenum);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", bookservice.bookjiancount(inputname) / 15);
		model.addAttribute("bookinquiry", bookinquiry);
		model.addAttribute("jian", 1);
		File f = new File("c:/file/library/" + CERT_ID + ".txt");
		if (f.exists()) {
		} else {
			f.createNewFile();
		}
	
		BufferedWriter output = new BufferedWriter(new FileWriter(f, true));// true,则追加写入text文本
		output.write(bookname+"\r\n");
		output.flush(); 
		output.close();
		return "center-bookinquiry";
	}
}
