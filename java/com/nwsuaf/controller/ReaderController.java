package com.nwsuaf.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.nwsuaf.entity.*;
import com.nwsuaf.service.LWService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nwsuaf.service.BookService;
import com.nwsuaf.service.ReaderService;
import com.nwsuaf.service.UserService;
import com.nwsuaf.utils.App;
import com.nwsuaf.utils.ReadFiles;
import com.nwsuaf.utils.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Controller
public class ReaderController {
	@Autowired
	ReaderService readerService;
	@Autowired
	UserService userservice;
	@Autowired
	BookService bookservice;
	@Autowired
	LWService lwService;
	List<TuiJianBook> bookLists1, bookLists2, bookLists3, bookLists4, bookLists5, bookLists6;
	List<TuiJianLW> lwLists1, lwLists2, lwLists3, lwLists4, lwLists5, lwLists1_1,lwLists2_2,lwLists3_3,lwLists4_4,lwLists5_5,lwListSearch;
	Reader reader = null;
	ReadFiles readFilesBook, readFilesLW;
	int pagenum = 0;

	@RequestMapping("goLogin")
	public String goLogin() {
		return "login";
	}

	@RequestMapping("goLWTuijian")
	String LWtuijian(HttpServletRequest request, Model model) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		if (reader.getKind_lw()==null) {
			lwLists1=readerService.getTuiJianLWByKind("1",reader.getName());
		} else {
			lwLists1=readerService.getTuiJianLWByKind(reader.getKind_lw(),reader.getName());
		}
		lwLists2 = readerService.getTopTuiJianLW(reader.getName());
		lwLists3 = readerService.getNewTuiJianLW(reader.getName());
		lwLists4 = new ArrayList<TuiJianLW>();//标签推荐
		Class<?> biaoqianClass = Class.forName("com.nwsuaf.entity.BiaoQianEntity");
		Method getKey, getValue;
		BiaoQianEntity bq = readerService.getLWBQ(reader.getCert_id());//（用户标签）
		int num;
		String key;
		Double value;
		int temp = 0;

		while (lwLists4.size() < 10) {
			for (int i = 1; i <= 10; i++) {
				try {
					getKey = biaoqianClass.getMethod("getKey" + i);// 得到方法对象

					getValue = biaoqianClass.getMethod("getValue" + i);

					key = (String) getKey.invoke(bq);
					value = (Double) getValue.invoke(bq);
					num = (int) Math.ceil(value * 15 / bq.getSum()) + temp;// 向上取整
					System.out.println("key:" + key + ",value:" + value + ",num:" + num);
					if (key != null) {
						if (!key.equals("")) {
							lwLists4.addAll(readerService.getTuiJianLWByBq("%" + key + "%", num,reader.getName()));
						}

					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for (int i = 0; i < lwLists4.size() - 1; i++) {//剔除重复的
				for (int j = lwLists4.size() - 1; j > i; j--) {
					if (lwLists4.get(j).getSubject().equals(lwLists4.get(i).getSubject())) {
						lwLists4.remove(j);
					}
				}

			}
			temp++;
		}
		lwLists5=readerService.getTuiJianByInfor_Lw(reader.getDept(),reader.getName());
		if(lwLists1.size()>10){
			lwLists1_1 = lwLists1.subList(0,10);
		}
		if(lwLists2.size()>10){
			lwLists2_2 = lwLists2.subList(0,10);
		}
		if(lwLists3.size()>10){
			lwLists3_3 = lwLists3.subList(0,10);
		}
		if(lwLists4.size()>10){
			lwLists4_4 = lwLists4.subList(0,10);
		}
		if(lwLists5.size()>10){
			lwLists5_5 = lwLists5.subList(0,10);
		}
		model.addAttribute("resultlwBykind", lwLists1_1);//
		model.addAttribute("resultlwTop", lwLists2_2);
		model.addAttribute("resultlwNew", lwLists3_3);
		model.addAttribute("resultlwByBQ", lwLists4_4);
		model.addAttribute("resultlwByInfor", lwLists5_5);
		System.out.println("lwlist1" + lwLists1_1);
		System.out.println("lwlist2" + lwLists2_2);
		System.out.println("lwlist3" + lwLists3_3);
		System.out.println("lwlist4" + lwLists4_4);
		System.out.println("lwlist5" + lwLists5_5);
		System.out.println("用户学院：" +reader.getDept());
		System.out.println("用户姓名：" +reader.getName());
		return "lwindex";
	}

	@RequestMapping("qun")
	public String goProfile(HttpServletRequest request, HttpSession session, Model model)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {

		Class<?> biaoqianClass = Class.forName("com.nwsuaf.entity.BiaoQianEntity");
		BiaoQianEntity biaoqian = (BiaoQianEntity) biaoqianClass.newInstance();

		String cert_id = (String) session.getAttribute("cert_id");

		System.out.println("cert_id:" + cert_id);

		Method getKey, getValue;
		// 利用反射调用get、set方法
		BiaoQianEntity bq = readerService.getBQ(cert_id);
		System.out.println("bq:" + bq);

		List<BiaoQianEntity> bqList = readerService.getBQByKind(reader.getKind(),reader.getDept());
		System.out.println("bqList:" + bqList);

		String key;
		Double value, oldValue;


		System.out.println("群体标签：");
		Map<String, Double> map = new HashMap<String, Double>();
		for (BiaoQianEntity b : bqList) {
			for (int i = 1; i <= 10; i++) {
				try {
					getKey = biaoqianClass.getMethod("getKey" + i);// 得到方法对象

					getValue = biaoqianClass.getMethod("getValue" + i);

					key = (String) getKey.invoke(b);
					value = (Double) getValue.invoke(b);

					key = key.replaceAll("\r|\n|\t", "");

					if (key == null || key.equals("")) {

						break;
					}

					System.out.println("key:" + key + "value:" + value);
					if (map.containsKey(key)) {
						oldValue = map.get(key);
						map.put(key, oldValue + value);
					} else {
						map.put(key, value);
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println("map:" + map);

		List<List<Object>> list = new ArrayList<List<Object>>();
		for (Entry<String, Double> entry : map.entrySet()) {
			// System.out.println("key= "+ key + " and value= " + map.get(key));
			List<Object> l = new ArrayList<>();
			list.add(l);
			l.add("'" + entry.getKey() + "'");
			l.add(entry.getValue());

			System.out.println("l:" + l);
			// l.clear();
		}
		

		List<Map<String, String>> sexList = readerService.getSexInfo(reader.getKind(),reader.getDept());
		List<Map<String, String>> occupationList = readerService.getOccInfo(reader.getKind(),reader.getDept());
		
		System.out.println(sexList);
		System.out.println(occupationList);
		
		if(sexList.size() == 1) {
			Map<String,String> sexInfo = new HashMap<String, String>();
			if(sexList.get(0).get("sex").equals("M")) {
				sexInfo.put("sex", "F");
				sexInfo.put("num", "0");
			}else {
				sexInfo.put("sex", "M");
				sexInfo.put("num", "0");
			}
			sexList.add(sexInfo);
		}else if(sexList.size() == 0) {
			Map<String,String> sexInfo1 = new HashMap<String, String>();
			Map<String,String> sexInfo2 = new HashMap<String, String>();
			sexInfo1.put("sex", "F");
			sexInfo1.put("num", "0");
			sexInfo2.put("sex", "M");
			sexInfo2.put("num", "0");
			sexList.add(sexInfo1);
			sexList.add(sexInfo2);
		}
		
		if(occupationList.size() == 1) {
			Map<String,String> occInfo = new HashMap<String, String>();
			if(occupationList.get(0).get("occupation").equals("教师")) {
				occInfo.put("occupation", "学生");
				occInfo.put("num", "0");
			}else {
				occInfo.put("occupation", "教师");
				occInfo.put("num", "0");
			}
			occupationList.add(occInfo);
		}else if(occupationList.size() == 0) {
			Map<String,String> occInfo1 = new HashMap<String, String>();
			Map<String,String> occInfo2 = new HashMap<String, String>();
			occInfo1.put("occupation", "教师");
			occInfo1.put("num", "0");
			occInfo2.put("occupation", "学生");
			occInfo2.put("num", "0");
			occupationList.add(occInfo1);
			occupationList.add(occInfo2);
		}
		
		System.out.println(sexList);
		System.out.println(occupationList);

		int a =Integer.valueOf(String.valueOf(sexList.get(0).get("num"))).intValue();
		int b =Integer.valueOf(String.valueOf(sexList.get(1).get("num"))).intValue();

		model.addAttribute("allNum",a+b);
		model.addAttribute(""+sexList.get(0).get("sex"), sexList.get(0).get("num"));
		model.addAttribute(""+sexList.get(1).get("sex"), sexList.get(1).get("num"));
		
		model.addAttribute(""+occupationList.get(0).get("occupation"), occupationList.get(0).get("num"));
		model.addAttribute(""+occupationList.get(1).get("occupation"), occupationList.get(1).get("num"));

		System.out.println("list:" + list);
		// System.out.println("list1:"+list.get(1));
		// model.addAttribute("bq", bq);
		model.addAttribute("list", list);
		model.addAttribute("reader", reader);
		if (reader.getOccupation().equals("学生")) {
			return "qun";
		} else if (reader.getOccupation().equals("教师")) {
			return "center-qun";
		}
		return "qun";
	}

	@RequestMapping("/")
	public String index(HttpSession session, Model model) {
		bookLists3 = readerService.getTopTuiJian();
		bookLists4 = readerService.getNewTuiJian();
		model.addAttribute("result3", bookLists3);
		model.addAttribute("result4", bookLists4);
		return "index-nologin";
	}

	@RequestMapping("login")
	public String login(HttpServletRequest request, HttpSession session, Model model, String cert_id, String passwd)
			throws Exception {

		Class<?> biaoqianClass = Class.forName("com.nwsuaf.entity.BiaoQianEntity");
		BiaoQianEntity biaoqian = (BiaoQianEntity) biaoqianClass.newInstance();
		reader = readerService.getReaderById(cert_id);
		System.out.println("用户id:"+cert_id);

		if (reader == null || !reader.getPassword().equals(passwd)) {
			model.addAttribute("errMsg", "用户名或者密码错误！");
			return "login";
		}
		if (!reader.getUsr_state().equals("1")) {
			model.addAttribute("errMsg", "用户已冻结，请联系管理员解冻！");
			return "login";
		}
		System.out.println(reader);
		
		if (reader.getOccupation().equals("管理员")) {

			session.setAttribute("reader", reader);
			session.setAttribute("session", reader.getCert_id());
			return "sysAdmi";
		}

		String id = String.valueOf(request.getParameter("cert_id"));
		System.out.println("cert_id:"+cert_id);


		request.getSession().setAttribute("reader", reader);
		request.getSession().setAttribute("cert_id", id);

		
		Date now = new Date();
		DateFormat t = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
		String time = new test().time(t.format(now));
		JSONObject ipjson = new JSONObject(new test().getPublicIp(request.getParameter("usr_ip")));
		String ip = ipjson.getString("ip");
		String weizhi = ipjson.getString("pro") + ipjson.getString("city");

		String zd = request.getParameter("zd");
//			System.err.println(id+" "+time+" "+ip+" "+zd+" "+weizhi+" ");
		userservice.insertPH(id, time, ip, zd, weizhi);
		InputStream inputbook = null, inputLW = null;
		OutputStream outputbook = null,outputLW = null;
		try {

			File f = new File("c:/file/word/" + id);
			if (f.exists()) {
			} else {
				f.mkdir();
			}
			inputbook = new FileInputStream("C:/file/a.txt");
			outputbook = new FileOutputStream("C:/file/word/" + id + "/a.txt");
			byte[] buf = new byte[1024];
			int bytesRead = 0;
			while ((bytesRead = inputbook.read(buf)) != -1) {
				outputbook.write(buf, 0, bytesRead);
			}
		} finally {
			inputbook.close();
			outputbook.close();
		}
		try {

			File f = new File("c:/file/word_lw/" + id);
			if (f.exists()) {
			} else {
				f.mkdir();
			}
			inputLW = new FileInputStream("C:/file/a.txt");
			outputLW = new FileOutputStream("C:/file/word_lw/" + id + "/a.txt");
			byte[] buf = new byte[1024];
			int bytesRead = 0;
			while ((bytesRead = inputLW.read(buf)) != -1) {
				outputLW.write(buf, 0, bytesRead);
			}
		} finally {
			inputLW.close();
			outputLW.close();
		}
		String[] books = readFilesBook.c;
		String[] aticles = readFilesLW.c;

		for (int i = 0; i < books.length; i++) {
			readFilesBook.c[i] = "";
		}
		for (int i = 0; i < 10; i++) {
			readFilesLW.s[i] = "";
			readFilesLW.v[i] = "";
		}
		for (int i = 0; i <aticles.length; i++) {
			readFilesLW.c[i] = "";
		}
		for (int i = 0; i < 10; i++) {
			readFilesLW.s[i] = "";
			readFilesLW.v[i] = "";
		}
		ArrayList<String> getbooks = userservice.getbooks(id);//登陆用户借阅的图书title
		ArrayList<String> getlws = userservice.getlws(reader.getName());//含有用户创作的论文标题
		System.out.println("getlws:"+getlws);
		App.startR(String.valueOf(request.getSession().getAttribute("session")), getbooks);
		App.startR_lw(String.valueOf(request.getSession().getAttribute("session")), getlws);

		readFilesBook.biaoqian(id);
		readFilesLW.biaoqian_lw(id);
		//存入（用户标签）
		userservice.insertKey(id, readFilesBook.s[0], readFilesBook.s[1], readFilesBook.s[2], readFilesBook.s[3], readFilesBook.s[4],
				readFilesBook.s[5], readFilesBook.s[6], readFilesBook.s[7], readFilesBook.s[8], readFilesBook.s[9], readFilesBook.v[0],
				readFilesBook.v[1], readFilesBook.v[2], readFilesBook.v[3], readFilesBook.v[4], readFilesBook.v[5], readFilesBook.v[6],
				readFilesBook.v[7], readFilesBook.v[8], readFilesBook.v[9]);
		userservice.insertLWKey(id, readFilesLW.s[0], readFilesLW.s[1], readFilesLW.s[2], readFilesLW.s[3], readFilesLW.s[4],
				readFilesLW.s[5], readFilesLW.s[6], readFilesLW.s[7], readFilesLW.s[8], readFilesLW.s[9], readFilesLW.v[0],
				readFilesLW.v[1], readFilesLW.v[2], readFilesLW.v[3], readFilesLW.v[4], readFilesLW.v[5], readFilesLW.v[6],
				readFilesLW.v[7], readFilesLW.v[8], readFilesLW.v[9]);
		ArrayList<User> user = userservice.findUser(String.valueOf(request.getParameter("usr_id")), 0);
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
		book_bq bb_book = bookservice.getbook_bq(String.valueOf(request.getSession().getAttribute("session")));
		model.addAttribute("map", bb_book);
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

		

		bookLists1 = readerService.getTuiJianByKind(reader.getKind());//群体推荐
		if (reader.getOccupation().equals("学生")) {
			bookLists2 = readerService.getTuiJianByInfor(reader.getSex(), reader.getDept(),
					reader.getCert_id().substring(0, 4) + "%");//信息推荐
		} else {
			bookLists2 = readerService.getTuiJianByTeacherInfor(reader.getSex(), reader.getDept(),
					reader.getOccupation());
		}

		bookLists3 = readerService.getTopTuiJian();//top推荐
		bookLists4 = readerService.getNewTuiJian();//最新推荐
		bookLists5 = new ArrayList<TuiJianBook>();//标签推荐
		System.out.println("booklist3-4完毕！！！");
		Method getKey, getValue;
		// 利用反射调用get、set方法
		BiaoQianEntity bq = readerService.getBQ(reader.getCert_id());//图书标签（用户标签）
		System.out.println("bq:" + bq);
		int num;
		String key;
		Double value;
		int temp=0;
//		List<TuiJianBook> tempLists5 = new ArrayList<TuiJianBook>();
		while (bookLists5.size() < 10) {
//			bookLists5.clear();
			for (int i = 1; i <= 10; i++) {
				try {
					getKey = biaoqianClass.getMethod("getKey" + i);// 得到方法对象

					getValue = biaoqianClass.getMethod("getValue" + i);

					key = (String) getKey.invoke(bq);
					value = (Double) getValue.invoke(bq);

					num = (int) Math.ceil(value * 15 / bq.getSum()) + temp;// 向上取整
					//System.out.println("key:" + key + "value:" + value + "num" + num);
					if (key != null) {
						if (!key.equals("")) {
							bookLists5.addAll(readerService.getTuiJianByBq("%" + key + "%", num));
						}

					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("key-value 循环完毕！！！！");
			for(int i=0;i<bookLists5.size()-1;i++) {//剔除重复的
				for(int j=bookLists5.size()-1;j>i;j--) {
					if(bookLists5.get(j).getBOOK_TITLE().equals(bookLists5.get(i).getBOOK_TITLE())) {
						bookLists5.remove(j);
					}
				}
				
			}
			System.out.println("剔除重复完毕！！！！");
			temp++;
//			tempLists5.clear();
		}

		model.addAttribute("result1", bookLists1);
		model.addAttribute("result2", bookLists2);
		model.addAttribute("result3", bookLists3);
		model.addAttribute("result4", bookLists4);
		model.addAttribute("result5", bookLists5);
		System.out.println("群体list1" + bookLists1);
		System.out.println("信息list2" + bookLists2);
		System.out.println("top_list3" + bookLists3);
		System.out.println("最新list4" + bookLists4);
		System.out.println("标签list5" + bookLists5);

		// for(int i=0; i<5; i++) {
		// ret = ret +(i+1);
		// bookList = bookList.get(i);
		// session.setAttribute(ret, );
		// }
		session.setAttribute("reader", reader);
		session.setAttribute("session", reader.getCert_id());

		return "index";

	}

	@RequestMapping("search")
	public String search(HttpSession session, Model model, String search) {
		System.out.println(search);

		bookLists6 = readerService.searchBook("%" + search + "%");
		System.out.println("list6" + bookLists6);

		model.addAttribute("result1", bookLists1);
		model.addAttribute("result2", bookLists2);
		model.addAttribute("result3", bookLists3);
		model.addAttribute("result4", bookLists4);
		model.addAttribute("result5", bookLists5);
		model.addAttribute("result6", bookLists6);
		Object reader = session.getAttribute("reader");
		if(reader != null) {
			return "index";
		}
		return "index-nologin";
	}

	@RequestMapping("lwinquiry")
	public String lwinquiry(HttpServletRequest request, Model model) {
		pagenum =0;
		ArrayList<LW> LWinquiry = lwService.LWInquiry(pagenum);
		model.addAttribute("resultlwBykind", lwLists1_1);//
		model.addAttribute("resultlwTop", lwLists2_2);
		model.addAttribute("resultlwNew", lwLists3_3);
		model.addAttribute("resultlwByBQ", lwLists4_4);
		model.addAttribute("resultlwByInfor", lwLists5_5);
		model.addAttribute("lwinquiry", LWinquiry);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", lwService.LWcount1() / 15);
		model.addAttribute("jian", 1);
		return "lwindex";
	}

	@RequestMapping("lwinquirydn")
	public String center_lwinquirydn(HttpServletRequest request, Model model) {
		pagenum += 15;
		ArrayList<LW> LWinquiry = lwService.LWInquiry(pagenum);
		model.addAttribute("resultlwBykind", lwLists1_1);//
		model.addAttribute("resultlwTop", lwLists2_2);
		model.addAttribute("resultlwNew", lwLists3_3);
		model.addAttribute("resultlwByBQ", lwLists4_4);
		model.addAttribute("resultlwByInfor", lwLists5_5);
		model.addAttribute("lwinquiry", LWinquiry);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", lwService.LWcount1() / 15);
		model.addAttribute("jian", 1);
		return "lwinquiry";
	}

	@RequestMapping("lwinquiryup")
	public String center_lwinquiryup(HttpServletRequest request, Model model) {
		pagenum -= 15;
		ArrayList<LW> LWinquiry = lwService.LWInquiry(pagenum);
		model.addAttribute("resultlwBykind", lwLists1_1);//
		model.addAttribute("resultlwTop", lwLists2_2);
		model.addAttribute("resultlwNew", lwLists3_3);
		model.addAttribute("resultlwByBQ", lwLists4_4);
		model.addAttribute("resultlwByInfor", lwLists5_5);
		model.addAttribute("lwinquiry", LWinquiry);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", lwService.LWcount1() / 15);
		model.addAttribute("jian", 1);
		return "lwindex";
	}

	@RequestMapping("lwinquirynum")
	public String center_lwinquirynum(HttpServletRequest request, Model model) {
		String num;
		num = request.getParameter("pagenum");
		if (num != null) {
			pagenum = (Integer.valueOf(num) - 1) * 15;
		} else {
			num = request.getParameter("pn");
			pagenum = (Integer.valueOf(num) - 1) * 15;
		}
		ArrayList<LW> LWinquiry = lwService.LWInquiry(pagenum);
		model.addAttribute("resultlwBykind", lwLists1_1);//
		model.addAttribute("resultlwTop", lwLists2_2);
		model.addAttribute("resultlwNew", lwLists3_3);
		model.addAttribute("resultlwByBQ", lwLists4_4);
		model.addAttribute("resultlwByInfor", lwLists5_5);
		model.addAttribute("lwinquiry", LWinquiry);
		model.addAttribute("pagenum", pagenum / 15 + 1);
		model.addAttribute("pagetotal", lwService.LWcount1() / 15);
		model.addAttribute("jian", 1);
		return "lwindex";
	}

	@RequestMapping("checkout")
	public String goLogin1() {
		return "checkout";
	}

	@RequestMapping("detail")
	public String godetail(HttpSession session, Model model, String id) {
		TuiJianBook detail = readerService.getBookById(id);
		model.addAttribute("detail", detail);
		System.out.println("detail" + detail);
		return "detail";
	}

	@RequestMapping("admin")
	public String goAdmin() {
		return "sys-usr-find";
	}

	@RequestMapping("index")
	public String goLogin5(HttpSession session, Model model) {
		model.addAttribute("result1", bookLists1);
		model.addAttribute("result2", bookLists2);
		model.addAttribute("result3", bookLists3);
		model.addAttribute("result4", bookLists4);
		model.addAttribute("result5", bookLists5);
		model.addAttribute("result6", bookLists6);
		return "index";
	}

}
