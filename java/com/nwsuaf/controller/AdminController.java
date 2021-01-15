package com.nwsuaf.controller;

import com.nwsuaf.entity.LW;
import com.nwsuaf.entity.TuiJianBook;
import com.nwsuaf.entity.TuiJianLW;
import com.nwsuaf.service.BookService;
import com.nwsuaf.service.LWService;
import com.nwsuaf.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;


@Controller
public class AdminController {
	//@Autowired
	//AdminService adminService;
	String usr_id;
	String book_id,article_id;
	int pagenumBook=0, pagenumArticle=0;
	int pagenum=0;
	@Autowired
	ReaderService readerService;
	@Autowired
	BookService bookservice;

	@Autowired
	LWService lwService;

	@RequestMapping("findAllReader.do")
	public String index(HttpServletRequest request, ModelMap model) {
		return "sys-usr-find";
	}

	@RequestMapping("findAllBook.do")
	public String findbook(HttpServletRequest request, ModelMap model) {
		return "sys-book-find";
	}

	@RequestMapping("findAllArticle.do")
	public String findarticle(HttpServletRequest request, ModelMap model) {
		return "sys-article-find";
	}


	@RequestMapping("sys-updateInform")
	public String sysupdateReader(String usr_passwd, String usr_passwd1, String age, String xy, String sex,String occupation, Model model,
								  HttpServletRequest request, HttpServletResponse response) throws IOException {
		String usr_id = (String) request.getParameter("usr_id");
		readerService.sys_updateReader(usr_passwd, age, xy, sex, usr_id,occupation);
		request.getSession().removeAttribute("code");
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		PrintWriter writer = response.getWriter();
		String msg = null;
		msg = "alert( '信息修改成功。' );location.href='findAllReader.do'";
		writer.print("<script type='text/javascript'>" + msg + "</script>");
		writer.flush();
		writer.close();

		return "sys-usr-find";

	}

	@RequestMapping("sys-updateBookInform")
	public String sysupdateBook(String book_id, String book_name, String book_kind, String author, String publisher,String year,String image, Model model,
								HttpServletRequest request, HttpServletResponse response) throws IOException {

		readerService.sys_updateBook(book_id,  book_name,  book_kind,  author,  publisher, year, image);
		request.getSession().removeAttribute("code");
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		PrintWriter writer = response.getWriter();
		String msg = null;
		msg = "alert( '信息修改成功。' );location.href='findBookById.do'";
		writer.print("<script type='text/javascript'>" + msg + "</script>");
		writer.flush();
		writer.close();

		return "sys-book-find";

	}


	//图书管理操作
	@RequestMapping("findBookById.do")
	public String findBook(HttpServletRequest request, ModelMap model) {
		book_id = String.valueOf(request.getParameter("book_id"));
		System.out.println("book_id:"+book_id);
		pagenumBook = 0;
		ArrayList<TuiJianBook> bookList = readerService.findBook(book_id, pagenumBook);
		System.err.println("bookList \n"+bookList);
		model.addAttribute("bookList", bookList);
		model.addAttribute("pagenum", pagenumBook / 15 + 1);
		model.addAttribute("pagetotal", readerService.bookcount(book_id) / 15);
		model.addAttribute("jian", 1);
		return "sys-book-find";
	}

	@RequestMapping("activeBook")
	public String activeBook(HttpServletRequest request, ModelMap model) {
		book_id = String.valueOf(request.getParameter("book_id"));
		bookservice.activeBook(book_id);
		//pagenumBook = 0;
		ArrayList<TuiJianBook> bookList = readerService.findBook(book_id, pagenumBook);
		model.addAttribute("bookList", bookList);
		model.addAttribute("pagenum", pagenumBook / 15 + 1);
		model.addAttribute("pagetotal", readerService.bookcount(book_id) / 15);
		model.addAttribute("jian", 1);
		return "sys-book-find";
	}

	@RequestMapping("dongjieBook")
	public String dongjieBook(HttpServletRequest request, ModelMap model) {
		book_id = String.valueOf(request.getParameter("book_id"));
		bookservice.dongjieBook(book_id);
		//pagenumBook = 0;
		ArrayList<TuiJianBook> bookList = readerService.findBook(book_id, pagenumBook);
		model.addAttribute("bookList", bookList);
		model.addAttribute("pagenum", pagenumBook / 15 + 1);
		model.addAttribute("pagetotal", readerService.bookcount(book_id) / 15);
		model.addAttribute("jian", 1);
		return "sys-book-find";
	}

//	@RequestMapping("updateUser")
//	public String updateReader(HttpServletRequest request, ModelMap model) {
//		Reader findReader = readerService.findReader(request.getParameter("usr_id"),0).get(0);
//		model.addAttribute("user",findReader);
//		return "sys-usr-edit";
//	}
//	@RequestMapping("sys-updateInform")
//	public String sysupdateReader(String usr_passwd, String usr_passwd1, String age, String xy, String sex,String occupation,String phone, Model model,
//			HttpServletRequest request, HttpServletResponse response) throws IOException {
//			String usr_id = (String) request.getParameter("usr_id");
//			readerService.sys_updateReader(usr_passwd, age, xy, sex, usr_id,occupation,phone);
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
//			return "findAllReader.do";
//
//	}

	@RequestMapping("juLei")
	public String juLei(HttpServletRequest request, HttpServletResponse response,ModelMap model) throws IOException{
		//Process proc;
		//proc = Runtime.getRuntime().exec("python C:\\Users\\liwen\\Desktop\\julei.py");// 执行py文件

		try {
			String exe = "python";
			String command = "C:\\julei.py";
			String[] cmdArr = new String[] { exe, command };
			Process process = Runtime.getRuntime().exec(cmdArr);
			BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while( ( line = in.readLine() ) != null ) {
				System.out.println(line);
			}
			in.close();
			int result = process.waitFor();
			System.out.println("执行结果:" + result);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("调用python");

		response.setContentType("text/html;charset=gb2312");

		PrintWriter writer = response.getWriter();
		String msg = null;
		msg = "alert( '聚类成功成功。' );location.href='sysAdmi'";
		writer.print("<script type='text/javascript'>" + msg + "</script>");
		writer.flush();
		writer.close();
		return "sysAdmi";
	}

	@RequestMapping("deleteBook")
	public String deleteBook(HttpServletRequest request, HttpServletResponse response,ModelMap model) throws IOException{
		book_id = String.valueOf(request.getParameter("book_id"));
		bookservice.deleteBookByID(book_id);
		request.getSession().removeAttribute("code");
		response.setContentType("text/html;charset=gb2312");

		PrintWriter writer = response.getWriter();
		String msg = null;
		msg = "alert( '删除图书成功。' );location.href='findAllReader.do'";
		writer.print("<script type='text/javascript'>" + msg + "</script>");
		writer.flush();
		writer.close();
		return "sys-book-find";
	}


	@RequestMapping("findBookByIddn")
	public String findBookdn(HttpServletRequest request, Model model) {
		pagenumBook += 15;
		ArrayList<TuiJianBook> bookList = readerService.findBook(book_id, pagenumBook);
		model.addAttribute("bookList", bookList);
		model.addAttribute("pagenum", pagenumBook / 15 + 1);
		model.addAttribute("pagetotal", readerService.bookcount(book_id) / 15);
		model.addAttribute("jian", 1);
		return "sys-book-find";
	}

	@RequestMapping("findBookByIdup")
	public String findBookup(HttpServletRequest request, Model model) {
		pagenumBook -= 15;
		ArrayList<TuiJianBook> bookList = readerService.findBook(book_id, pagenumBook);
		model.addAttribute("bookList", bookList);
		model.addAttribute("pagenum", pagenumBook / 15 + 1);
		model.addAttribute("pagetotal", readerService.bookcount(book_id) / 15);
		model.addAttribute("jian", 1);
		return "sys-book-find";
	}

	@RequestMapping("findBookByIdnum")
	public String findBooknum(HttpServletRequest request, Model model) {
		String num;
		num = request.getParameter("pagenum");
		if (num != null) {
			pagenumBook = (Integer.valueOf(num) - 1) * 15;
		} else {
			num = request.getParameter("pn");
			pagenumBook = (Integer.valueOf(num) - 1) * 15;
		}
		ArrayList<TuiJianBook> bookList = readerService.findBook(book_id, pagenumBook);
		model.addAttribute("bookList", bookList);
		model.addAttribute("pagenum", pagenumBook / 15 + 1);
		model.addAttribute("pagetotal", readerService.bookcount(book_id) / 15);
		model.addAttribute("jian", 1);
		return "sys-book-find";
	}

	@RequestMapping("updateBook")
	public String updateBook(HttpServletRequest request, ModelMap model) {
		TuiJianBook book = readerService.getBookById(request.getParameter("book_id"));
		model.addAttribute("book",book);
		return "sys-book-edit";
	}

	//文献操作
	@RequestMapping("sys-updateArticleInform")
	public String sysupdateArticle(int article_id, String subject, String author, String type, String abstract1,Integer download_num,Integer quote_num,String periodical,
								   String publish_time, String key,String fund, String unit,Model model,
								HttpServletRequest request, HttpServletResponse response) throws IOException {

		readerService.sys_updateArticle(article_id,subject, author, type,  abstract1, download_num ,quote_num,periodical, publish_time,  key,fund,  unit);
		request.getSession().removeAttribute("code");
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		PrintWriter writer = response.getWriter();
		String msg = null;
		msg = "alert( '信息修改成功。' );location.href='findArticleById.do'";
		writer.print("<script type='text/javascript'>" + msg + "</script>");
		writer.flush();
		writer.close();

		return "sys-article-find";

	}

	@RequestMapping("findArticleById.do")
	public String findArticle(HttpServletRequest request, ModelMap model) {
		article_id = String.valueOf(request.getParameter("article_id"));
		System.out.println("article_id :"+article_id);
		pagenumArticle = 0;
		ArrayList<TuiJianLW> articleList = readerService.findArticle(article_id, pagenumArticle);
		System.err.println("articleList \n"+articleList);
		model.addAttribute("articleList", articleList);
		model.addAttribute("pagenum", pagenumArticle / 15 + 1);
		model.addAttribute("pagetotal", readerService.articlecount(article_id) / 15);
		model.addAttribute("jian", 1);
		return "sys-article-find";
	}

	@RequestMapping("deleteArticle")
	public String deleteArticle(HttpServletRequest request, HttpServletResponse response,ModelMap model) throws IOException{
		article_id = String.valueOf(request.getParameter("article_id"));
		lwService.delete(article_id);//deleteBookByID(book_id);
		request.getSession().removeAttribute("code");
		response.setContentType("text/html;charset=gb2312");

		PrintWriter writer = response.getWriter();
		String msg = null;
		msg = "alert( '删除文献成功。' );location.href='findAllReader.do'";
		writer.print("<script type='text/javascript'>" + msg + "</script>");
		writer.flush();
		writer.close();
		return "sys-article-find";
	}


	@RequestMapping("findArticleByIddn")
	public String findArticledn(HttpServletRequest request, Model model) {
		pagenumArticle += 15;
		ArrayList<TuiJianLW> articleList = readerService.findArticle(article_id, pagenumArticle);
		model.addAttribute("articleList", articleList);
		model.addAttribute("pagenum", pagenumArticle / 15 + 1);
		model.addAttribute("pagetotal", readerService.articlecount(article_id) / 15);
		model.addAttribute("jian", 1);
		return "sys-article-find";
	}

	@RequestMapping("findArticleByIdup")
	public String findArticleup(HttpServletRequest request, Model model) {
		pagenumArticle -= 15;
		ArrayList<TuiJianLW> articleList = readerService.findArticle(article_id, pagenumArticle);
		model.addAttribute("articleList", articleList);
		model.addAttribute("pagenum", pagenumArticle / 15 + 1);
		model.addAttribute("pagetotal", readerService.articlecount(article_id) / 15);
		model.addAttribute("jian", 1);
		return "sys-article-find";
	}

	@RequestMapping("findArticleByIdnum")
	public String findArticlenum(HttpServletRequest request, Model model) {
		String num;
		num = request.getParameter("pagenum");
		if (num != null) {
			pagenumArticle = (Integer.valueOf(num) - 1) * 15;
		} else {
			num = request.getParameter("pn");
			pagenumArticle = (Integer.valueOf(num) - 1) * 15;
		}
		ArrayList<TuiJianLW> articleList = readerService.findArticle(article_id, pagenumArticle);
		model.addAttribute("articleList", articleList);
		model.addAttribute("pagenum", pagenumArticle / 15 + 1);
		model.addAttribute("pagetotal", readerService.articlecount(article_id) / 15);
		model.addAttribute("jian", 1);
		return "sys-article-find";
	}

	@RequestMapping("updateArticle")
	public String updateArticle(HttpServletRequest request, ModelMap model) {
		TuiJianLW lw = readerService.getArticleById(request.getParameter("article_id"));
		model.addAttribute("article",lw);
		return "sys-article-edit";
	}

}
