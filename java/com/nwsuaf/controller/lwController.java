package com.nwsuaf.controller;

import com.nwsuaf.entity.*;
import com.nwsuaf.service.LWService;
import com.nwsuaf.service.ReaderService;
import com.nwsuaf.service.UserService;
import com.nwsuaf.utils.Word2VEC;
import com.nwsuaf.utils.test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class lwController {
	int pagenum = 0;
	String inputname;
	@Resource
	LWService lwservice;
	@Resource
	UserService userservice;
	@Autowired
	ReaderService readerService;

	String guanjianci;
	ArrayList<LW> tuijians = new ArrayList<LW>();
	@RequestMapping("center-lwinquiry")
	public String center_lwinquiry(HttpServletRequest request, Model model) {
		pagenum =0;
		ArrayList<LW> LWinquiry = lwservice.LWInquiry(pagenum);
		model.addAttribute("lwinquiry", LWinquiry);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", lwservice.LWcount1() / 15);
		model.addAttribute("jian", 1);
		return "center-lwinquiry";
	}

	@RequestMapping("center-lwinquirydn")
	public String center_lwinquirydn(HttpServletRequest request, Model model) {
		pagenum += 15;
		ArrayList<LW> LWinquiry = lwservice.LWInquiry(pagenum);
		model.addAttribute("lwinquiry", LWinquiry);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", lwservice.LWcount1() / 15);
		model.addAttribute("jian", 1);
		return "center-lwinquiry";
	}

	@RequestMapping("center-lwinquiryup")
	public String center_lwinquiryup(HttpServletRequest request, Model model) {
		pagenum -= 15;
		ArrayList<LW> LWinquiry = lwservice.LWInquiry(pagenum);
		model.addAttribute("lwinquiry", LWinquiry);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", lwservice.LWcount1() / 15);
		model.addAttribute("jian", 1);
		return "center-lwinquiry";
	}

	@RequestMapping("center-lwinquirynum")
	public String center_lwinquirynum(HttpServletRequest request, Model model) {
		String num;
		num = request.getParameter("pagenum");
		if (num != null) {
			pagenum = (Integer.valueOf(num) - 1) * 15;
		} else {
			num = request.getParameter("pn");
			pagenum = (Integer.valueOf(num) - 1) * 15;
		}
		ArrayList<LW> LWinquiry = lwservice.LWInquiry(pagenum);
		model.addAttribute("lwinquiry", LWinquiry);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", lwservice.LWcount1() / 15);
		model.addAttribute("jian", 1);
		return "center-lwinquiry";
	}

	@RequestMapping("center-lwjiansuo")
	public String lwjiansuo(HttpServletRequest request, Model model) {
		pagenum = 0;
		inputname = String.valueOf(request.getParameter("bookname"));
		ArrayList<LW> LWinquiry = lwservice.LWjiansuo(inputname,pagenum);
		model.addAttribute("lwinquiry", LWinquiry);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", lwservice.bookjiancount(inputname) / 15);
		model.addAttribute("jian", 1);
		return "center-lwjiansuo";
	}

	@RequestMapping("center-lwjiansuodn")
	public String lwjiansuodn(HttpServletRequest request, Model model) {
		pagenum += 15;
		ArrayList<LW> LWinquiry = lwservice.LWjiansuo(inputname,pagenum);
		model.addAttribute("lwinquiry", LWinquiry);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", lwservice.bookjiancount(inputname) / 15);
		model.addAttribute("jian", 1);
		return "center-lwjiansuo";
	}

	@RequestMapping("center-lwjiansuoup")
	public String lwjiansuoup(HttpServletRequest request, Model model) {
		pagenum -= 15;
		ArrayList<LW> LWinquiry = lwservice.LWjiansuo(inputname,pagenum);
		model.addAttribute("lwinquiry", LWinquiry);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", lwservice.bookjiancount(inputname) / 15);
		model.addAttribute("jian", 1);
		return "center-lwjiansuo";
	}

	@RequestMapping("center-lwjiansuonum")
	public String lwjiansuonum(HttpServletRequest request, Model model) {
		String num;
		num = request.getParameter("pagenum");
		if (num != null) {
			pagenum = (Integer.valueOf(num) - 1) * 15;
		}else {
			num = request.getParameter("pn");
			pagenum = (Integer.valueOf(num) - 1) * 15;
		}
		ArrayList<LW> LWinquiry = lwservice.LWjiansuo(inputname,pagenum);
		model.addAttribute("lwinquiry", LWinquiry);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", lwservice.bookjiancount(inputname) / 15);
		model.addAttribute("jian", 1);
		return "center-lwjiansuo";
	}

	@RequestMapping("lwtuijian")
	public String LWtuijian(HttpServletRequest request, Model model) throws IOException {

		String id = String.valueOf(request.getSession().getAttribute("session"));
		System.out.println("id:"+id);
		book_bq bb = userservice.findwx_bq(id).get(0);

		guanjianci+= bb.getWords();
		System.out.println("guanjianci:"+guanjianci);
		tuijians.clear();
		if (guanjianci!= null) {
			Word2VEC vec = new Word2VEC();
			vec.loadJavaModel("c:/file/library_lw/" + id + "javaSkip1");
			Iterator it = vec.distance(guanjianci).iterator();
			String x = "";
			String q = "";

			while (it.hasNext()) {
				x = it.next().toString();
				String x1 = x.split("	")[0];
				ArrayList<LW> tuijian= lwservice.lwtuijian(x1);
				System.err.println("lwtuijian:"+tuijian);
				tuijians.addAll(tuijian);
				System.out.println("循环！！");
				q = (x.split("	"))[0];
				if (q.length() > 1)
					//System.out.println(x);
					if(tuijians.size()>50) {
						break;
					}
			}

			pagenum = 0;
			ArrayList<LW> tuijian = new ArrayList<LW>();
			for(int i=pagenum;i<pagenum+15&&i<tuijians.size();i++) {
				tuijian.add(tuijians.get(i));
			}
			System.out.println("论文推荐："+tuijians);
			model.addAttribute("tuijians",tuijian);
			model.addAttribute("pagenum", pagenum / 15 + 1);
			model.addAttribute("pagetotal", tuijians.size()/15);
			model.addAttribute("jian", 1);

		}
		else {
			model.addAttribute("jian", 0);
		}
		if (userservice.findxyByID(id).equals("学生")) {
			return "user-lwtuijian";
		} else if (userservice.findxyByID(id).equals("教师")) {
			return "center-lwtuijian";
		}
		return "error";
	}

	@RequestMapping("lwtuijiandn")
	public String lwtuijiandn(HttpServletRequest request, Model model) throws IOException {
		String id = String.valueOf(request.getSession().getAttribute("session"));
		if (guanjianci!= null) {

			pagenum += 15;
			ArrayList<LW> tuijian = new ArrayList<LW>();
			for(int i=pagenum;i<pagenum+15&&i<tuijians.size();i++) {
				tuijian.add(tuijians.get(i));
			}
			model.addAttribute("tuijians",tuijian);
			model.addAttribute("pagenum", pagenum / 15 + 1);
			model.addAttribute("pagetotal", tuijians.size()/15);
			model.addAttribute("jian", 1);

		}
		else {
			model.addAttribute("jian", 0);
		}
		if (userservice.findxyByID(id).equals("学生")) {
			return "user-lwtuijian";
		} else if (userservice.findxyByID(id).equals("教师")) {
			return "center-lwtuijian";
		}
		return "error";
	}

	@RequestMapping("lwtuijianup")
	public String lwtuijianup(HttpServletRequest request, Model model) throws IOException {
		String id = String.valueOf(request.getSession().getAttribute("session"));
		if (guanjianci!= null) {

			pagenum -= 15;
			ArrayList<LW> tuijian = new ArrayList<LW>();
			for(int i=pagenum;i<pagenum+15&&i<tuijians.size();i++) {
				tuijian.add(tuijians.get(i));
			}
			model.addAttribute("tuijians",tuijian);
			model.addAttribute("pagenum", pagenum / 15 + 1);
			model.addAttribute("pagetotal", tuijians.size()/15);
			model.addAttribute("jian", 1);

		}
		else {
			model.addAttribute("jian", 0);
		}
		if (userservice.findxyByID(id).equals("学生")) {
			return "user-lwtuijian";
		} else if (userservice.findxyByID(id).equals("教师")) {
			return "center-lwtuijian";
		}
		return "error";
	}

	@RequestMapping("lwtuijiannum")
	public String lwtuijiannum(HttpServletRequest request, Model model) throws IOException {
		String id = String.valueOf(request.getSession().getAttribute("session"));
		String num;
		num = request.getParameter("pagenum");
		if (num != null) {
			pagenum = (Integer.valueOf(num) - 1) * 15;
		} else if (request.getParameter("jiansuo") != null) {
//			System.out.println(String.valueOf(request.getParameter("bookname")));

			this.lwjiansuo(request, model);
		} else {
			num = request.getParameter("pn");
			pagenum = (Integer.valueOf(num) - 1) * 15;
		}
		if (guanjianci!= null) {

			ArrayList<LW> tuijian = new ArrayList<>();
			for(int i=pagenum;i<pagenum+15&&i<tuijians.size();i++) {
				tuijian.add(tuijians.get(i));
			}
			model.addAttribute("tuijians",tuijian);
			model.addAttribute("pagenum", pagenum / 15 + 1);
			model.addAttribute("pagetotal", tuijians.size()/15);
			model.addAttribute("jian", 1);

		}
		else {
			model.addAttribute("jian", 0);
		}
		if (userservice.findxyByID(id).equals("学生")) {
			return "user-lwtuijian";
		} else if (userservice.findxyByID(id).equals("教师")) {
			return "center-lwtuijian";
		}
		return "error";
	}

	@RequestMapping("center-mlwinquiry")
	public String mlwinquiry(HttpServletRequest request, Model model) {
		ArrayList<LW> LWinquiry = lwservice.LWjiansuo(userservice.findnamebyid(
				String.valueOf(request.getSession().getAttribute("session"))),0);
		model.addAttribute("lwinquiry", LWinquiry);
		return "center-mlwinquiry";
	}


}
