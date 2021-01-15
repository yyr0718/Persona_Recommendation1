package com.nwsuaf.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.nwsuaf.entity.Book;
import com.nwsuaf.entity.Reader;
import com.nwsuaf.entity.User;
import com.nwsuaf.entity.book_bq;
import com.nwsuaf.entity.dongtai;
import com.nwsuaf.service.BookService;
import com.nwsuaf.service.UserService;
import com.nwsuaf.utils.App;
import com.nwsuaf.utils.ReadFiles;
import com.nwsuaf.utils.similar;
import com.nwsuaf.utils.test;

//证明是controller层并且返回json
@Controller
public class UserController {
	@Resource
	UserService userservice;
	@Resource
	BookService bookservice;
	@RequestMapping("checkLogin2")
	public String cs(HttpServletRequest request, ModelMap model) throws IOException {
		// 调用dao层
		String passwd = userservice.findPasswdByID(request.getParameter("usr_id"));
		String id = String.valueOf(request.getParameter("usr_id"));
		Reader reader = new Reader();
		reader = userservice.finduserbyname1(userservice.findnamebyid(id));
		request.getSession().setAttribute("session", id);
		request.getSession().setAttribute("reader", reader);
		request.getSession().setAttribute("cert_id", id);
		if (passwd.equals(request.getParameter("usr_passwd"))) {
			if (userservice.findxyByID(id).equals("管理员")) {
				return "sys-index";
			}
			Date now = new Date();
			DateFormat t = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
			String time = new test().time(t.format(now));
			JSONObject ipjson = new JSONObject(new test().getPublicIp(request.getParameter("usr_ip")));
			String ip = ipjson.getString("ip");
			String weizhi = ipjson.getString("pro") + ipjson.getString("city");

		
			String zd = request.getParameter("zd");
//			System.err.println(id+" "+time+" "+ip+" "+zd+" "+weizhi+" ");
			userservice.insertPH(id, time, ip, zd, weizhi);
			InputStream input = null;
			OutputStream output = null;
			try {

				File f = new File("c:/file/word/" + id);
				if (f.exists()) {
				} else {
					f.mkdir();
				}
				input = new FileInputStream("C:/file/a.txt");
				output = new FileOutputStream("C:/file/word/" + id + "/a.txt");
				byte[] buf = new byte[1024];
				int bytesRead = 0;
				while ((bytesRead = input.read(buf)) != -1) {
					output.write(buf, 0, bytesRead);
				}
			} finally {
				input.close();
				output.close();
			}
			String[] books = ReadFiles.c;
		
			for (int i = 0; i < books.length; i++) {
				// System.out.println(ReadFiles.c[i]+" "+i);
				ReadFiles.c[i] = "";
			}
			for (int i = 0; i < 10; i++) {
				// System.out.println(ReadFiles.c[i]+" "+i);
				ReadFiles.s[i] = "";
				ReadFiles.v[i] = "";
			}
			ArrayList<String> getbooks = userservice.getbooks(id);
			ArrayList<String> getlws = userservice.getlws(reader.getName());//含有用户创作的论文标题
			App.startR(String.valueOf(request.getSession().getAttribute("session")), getbooks);
			App.startR_lw(String.valueOf(request.getSession().getAttribute("session")), getlws);
			ReadFiles.biaoqian(id);
			userservice.insertKey(id, ReadFiles.s[0], ReadFiles.s[1], ReadFiles.s[2], ReadFiles.s[3], ReadFiles.s[4],
					ReadFiles.s[5], ReadFiles.s[6], ReadFiles.s[7], ReadFiles.s[8], ReadFiles.s[9], ReadFiles.v[0],
					ReadFiles.v[1], ReadFiles.v[2], ReadFiles.v[3], ReadFiles.v[4], ReadFiles.v[5], ReadFiles.v[6],
					ReadFiles.v[7], ReadFiles.v[8], ReadFiles.v[9]);
			ArrayList<User> user = userservice.findUser(String.valueOf(request.getSession().getAttribute("session")), 0);
			User user2 = user.get(0);
			model.addAttribute("user", user2);

			model.addAttribute("age", userservice.findAge(String.valueOf(request.getSession().getAttribute("session"))));
			if (user2.getSex().equals("M")) {
				model.addAttribute("sex", "男");
			} else {
				model.addAttribute("sex", "女");
			}
			char s[] = String.valueOf(request.getSession().getAttribute("session")).toCharArray();
			char[] c = new char[4];
			for (int i = 0; i < 4; i++) {
				c[i] = s[i];
			}
			model.addAttribute("nianji", String.valueOf(c));
			book_bq bb = bookservice.getbook_bq(String.valueOf(request.getSession().getAttribute("session")));
			model.addAttribute("map", bb);

//			FileInputStream in = new FileInputStream(new File("c:/file/word/" + id + "/" + id + ".txt"));
//			byte b[] = new byte[in.available()];
//			in.read(b);
//			in.close();
//			String bq = new String(b);
//			ArrayList<book_bq> bq1 = new ArrayList<book_bq>();
//			for (int i = 0; i < bq.split(" ").length; i++) {
//				book_bq bqx = new book_bq();
//				bqx.setKey1(bq.split(" ")[i]);
//				bq1.add(bqx);
//			}
//			model.addAttribute("bq", bq1);
			ArrayList<book_bq> bq1 = new ArrayList<book_bq>();
			for (String s1 : books) {
				book_bq bqx = new book_bq();
				bqx.setKey1(s1);
				bq1.add(bqx);
			}
			model.addAttribute("bq", bq1);
			model.addAttribute("time", userservice.findtime(id).getTime());
			model.addAttribute("ip", userservice.findip(id).getIp());
			model.addAttribute("zd", userservice.findzd(id).getZd());
			model.addAttribute("weizhi", userservice.findweizhi(id).getWeizhi());
			if (userservice.findxyByID(id).equals("学生")) {
				return "ciyun";
			} else if (userservice.findxyByID(id).equals("教师")) {
				return "center-ciyun";
			}

		} else {
			return "user-login";
		}
		return "user-login";
	}

	@RequestMapping("user-index")
	public String index(HttpServletRequest request, ModelMap model) {
		// 调用dao层
		return "user-index";
	}
	@RequestMapping("user-login")
	public String login(HttpServletRequest request, ModelMap model) {
		// 调用dao层
		return "user-login";
	}

	@RequestMapping("person")
	public String person(HttpServletRequest request, ModelMap model) {
		// 调用dao层
		return "person";
	}

	@RequestMapping("user-tree")
	public String tree(HttpServletRequest request, ModelMap model) {
		// 调用dao层
		model.addAttribute("name", "赵旭");
		return "user-tree";
	}

	@RequestMapping("/updateInform")
	public String updateUser(String oldpwd,String usr_passwd, String usr_passwd1, String age, String xy, String sex, Model model,
			HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {

		Reader reader = (Reader) session.getAttribute("reader");
		if (!oldpwd.equals(reader.getPassword())) {
			request.getSession().removeAttribute("code");
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter();
			PrintWriter writer = response.getWriter();
			String msg = null;
			msg = "alert( '旧密码错误，请重新输入！' );location.href='user-information'";
			writer.print("<script type='text/javascript'>" + msg + "</script>");
			writer.flush();
			writer.close();
		}else if (usr_passwd == null || "".equals(usr_passwd)) {
			request.getSession().removeAttribute("code");
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter();
			PrintWriter writer = response.getWriter();
			String msg = null;
			msg = "alert( '密码不能为空！' );location.href='user-information'";
			writer.print("<script type='text/javascript'>" + msg + "</script>");
			writer.flush();
			writer.close();
		} else if (usr_passwd1 == null || "".equals(usr_passwd1)) {
			request.getSession().removeAttribute("code");
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter();
			PrintWriter writer = response.getWriter();
			String msg = null;
			msg = "alert( '请确认密码！' );location.href='user-information'";
			writer.print("<script type='text/javascript'>" + msg + "</script>");
			writer.flush();
			writer.close();
		} else if (!usr_passwd.equals(usr_passwd1)) {
			request.getSession().removeAttribute("code");
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter();
			PrintWriter writer = response.getWriter();
			String msg = null;
			msg = "alert( '两次输入密码不一致！' );location.href='user-information'";
			writer.print("<script type='text/javascript'>" + msg + "</script>");
			writer.flush();
			writer.close();
		} else {
			String usr_id = (String) request.getSession().getAttribute("session");
			request.getSession().removeAttribute("code");
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter();
			PrintWriter writer = response.getWriter();
			String msg = null;
			msg = "alert( '信息修改成功。' );location.href='user-information'";
			writer.print("<script type='text/javascript'>" + msg + "</script>");
			writer.flush();
			writer.close();
			
			userservice.updateUser(usr_passwd, age, xy, sex, usr_id);
//            US.updateUser(usr_passwd,age,email,usr_id);
			return "user-information";
		}
		return "user-information";

	}

	@RequestMapping("/center-updateInform")
	public String cupdateUser(String oldpwd, String usr_passwd, String usr_passwd1, String age, String xy, String sex,Model model,
			HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		Reader reader = (Reader) session.getAttribute("reader");
		if (!oldpwd.equals(reader.getPassword())) {
			request.getSession().removeAttribute("code");
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter();
			PrintWriter writer = response.getWriter();
			String msg = null;
			msg = "alert( '旧密码错误，请重新输入！' );location.href='center-information'";
			writer.print("<script type='text/javascript'>" + msg + "</script>");
			writer.flush();
			writer.close();
		}else if (usr_passwd == null || "".equals(usr_passwd)) {
			request.getSession().removeAttribute("code");
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter();
			PrintWriter writer = response.getWriter();
			String msg = null;
			msg = "alert( '密码不能为空！' );location.href='center-information'";
			writer.print("<script type='text/javascript'>" + msg + "</script>");
			writer.flush();
			writer.close();
		} else if (usr_passwd1 == null || "".equals(usr_passwd1)) {
			request.getSession().removeAttribute("code");
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter();
			PrintWriter writer = response.getWriter();
			String msg = null;
			msg = "alert( '请确认密码！' );location.href='center-information'";
			writer.print("<script type='text/javascript'>" + msg + "</script>");
			writer.flush();
			writer.close();
		} else if (!usr_passwd.equals(usr_passwd1)) {
			request.getSession().removeAttribute("code");
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter();
			PrintWriter writer = response.getWriter();
			String msg = null;
			msg = "alert( '两次输入密码不一致！' );location.href='center-information'";
			writer.print("<script type='text/javascript'>" + msg + "</script>");
			writer.flush();
			writer.close();
		} else {
			String usr_id = (String) request.getSession().getAttribute("session");
			request.getSession().removeAttribute("code");
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter();
			PrintWriter writer = response.getWriter();
			String msg = null;
			msg = "alert( '信息修改成功。' );location.href='center-information'";
			writer.print("<script type='text/javascript'>" + msg + "</script>");
			writer.flush();
			writer.close();
			userservice.updateUser(usr_passwd, age, xy, sex, usr_id);
//            US.updateUser(usr_passwd,age,email,usr_id);
			return "center-information";
		}
		return "center-information";

	}

	@RequestMapping("/user-information")
	public String displayInform(HttpSession session, Model model) throws ParseException {
		String usr_id = (String) session.getAttribute("session");
		Reader reader = (Reader) session.getAttribute("reader");
//		 if(usr_id.equals(request.getSession().getAttribute("session"))){
//	            Date oldage = userservice.findAge(usr_id);
//	            String oldemail = userservice.findEmail(usr_id);
//	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//	            model.addAttribute("oldage",sdf.format(oldage));
//	            model.addAttribute("oldemail",oldemail);
//	            System.out.println(oldemail);
//	            return "user-myinformation";
//	        }else if(usr_id.equals(request.getSession().getAttribute("session_center"))){
//	            Date oldage = US.findAge(usr_id);
//	            String oldemail = US.findEmail(usr_id);
//	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//	            model.addAttribute("oldage",sdf.format(oldage));
//	            model.addAttribute("oldemail",oldemail);
//	            System.out.println(oldemail);
//	            return "center-myinformation";
//	        }else{
//	            model.addAttribute("msg3","请登录！");
//	            return "user-login";
//	        }
		if (reader.getOccupation().equals("学生")) {
			return "user-information";
		} else if (reader.getOccupation().equals("教师")) {
			return "center-information";
		}
		return "user-information";
	}

	@RequestMapping("reader-zx")
	String readerzx(HttpServletRequest request, Model model) {
		String usr_id = String.valueOf(request.getSession().getAttribute("session"));
		String cert_id = request.getParameter("cert_id");
		if(request.getParameter("name")!=null) {
			cert_id=userservice.finduserbyname(request.getParameter("name")).getCert_id();
		}
		model.addAttribute("cert_id", cert_id);

		if (request.getParameter("ding") != null) {
			userservice.dingup(Integer.valueOf(request.getParameter("id")));
		} else if (request.getParameter("cai") != null) {
			userservice.caiup(Integer.valueOf(request.getParameter("id")));
		}
		if (request.getParameter("sub") != null) {
			Date d = new Date();

			String ti = String.valueOf((d.getYear() + 1900)) + "-" + String.valueOf((d.getMonth() + 1)) + "-"
					+ String.valueOf(d.getDate()) + " " + String.valueOf(d.getHours()) + ":"
					+ String.valueOf(d.getMinutes());

			String text = request.getParameter("saytext");

			userservice.insertDT(cert_id, text, ti);
		}
		if (request.getParameter("ly") != null) {

			String text = request.getParameter("lyb");
			if (text.length() > 5 && text.length() < 20)
				userservice.insertLY(cert_id, text, usr_id);
		}

		File file = new File("C:/file/touxiang/" + cert_id + ".jpg");
		if (file.exists()) {
			model.addAttribute("touxiangid", cert_id);
		} else {
			model.addAttribute("touxiangid", "hu");
		}
		User user = userservice.findUser(cert_id, 0).get(0);
		if (user.getPhone() == null) {
			user.setPhone(" ");
		}
		model.addAttribute("lendc", userservice.getlendc(cert_id));
		model.addAttribute("user", user);
		request.setAttribute("totlesize",userservice.findtotlesize(cert_id));
		model.addAttribute("cert_id", cert_id);
		ArrayList<HashMap<String, String>> dongtais = userservice.finddongtai(cert_id);
//		model.addAttribute("dongtais",dongtais);
		request.setAttribute("totlesize",userservice.findtotlesize(cert_id));
		request.getSession().setAttribute("sf",userservice.findxyByID(usr_id));
		model.addAttribute("fabiaoc",userservice.findfabiaoc(userservice.findnamebyid(cert_id)));
		return "reader-zx";
	}

	@RequestMapping("reader-liuyan")
	@ResponseBody
	public String liuyan(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		ArrayList<HashMap<String, String>> f = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < 10; i++) {
			f = userservice.findLY(String.valueOf(request.getParameter("cert_id")));

		}

		JSONArray json = new JSONArray(f);
		return json.toString();

	}

	@RequestMapping("reader-touxiang")
	@ResponseBody
	public String touxiang(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		String usr_id = String.valueOf(request.getSession().getAttribute("session"));
		new similar().writetouxiang(request.getParameter("image").split(",")[1], usr_id);
		return usr_id;

	}

	@RequestMapping("atest")
	public String test(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		String cert_id = request.getParameter("cert_id");
		request.setAttribute("totlesize",userservice.findtotlesize(cert_id));
		model.addAttribute("cert_id", cert_id);
		return "atest";
	}

	@RequestMapping("dongtai-data")
	@ResponseBody
	public String dongtaidata(HttpServletRequest request, HttpServletResponse response, Model model)
			throws IOException {
		String cert_id = request.getParameter("cert_id");
		ArrayList<HashMap<String, String>> f = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < 10; i++) {
			f = userservice.finddongtai(String.valueOf(request.getParameter("cert_id")));

		}

		JSONArray json = new JSONArray(f);
		return json.toString();
	}

}