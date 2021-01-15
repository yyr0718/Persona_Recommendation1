package com.nwsuaf.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.WordDictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class test {
	public static void main(String[] args) throws Exception {
		new test().julei("java");
		
	}

	public void julei(String name) throws IOException {
		JiebaSegmenter segmenter = new JiebaSegmenter();
		File f1 = new File("c:/file/book_title.txt");
		ArrayList<String> list = new ArrayList<String>();
		 try {
	            File file = new File("c:/file/book_title.txt");
	            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
	            String strLine = null;
	            int lineCount = 1;
	            while(null != (strLine = bufferedReader.readLine())){
	            	list.add(strLine);
	            }
	        }catch(Exception e){
	            e.printStackTrace();
	        }
		// 先读取十个字符试试

		OutputStream out = null;
		BufferedWriter bw = null;
		WordDictionary.getInstance().init(Paths.get("src/main/resources/conf"));
		File f3 = new File("c:/file/book_title_word.txt");
		OutputStreamWriter out2 = new OutputStreamWriter(new FileOutputStream("c:/file/book_title_word.txt"), "UTF-8");
		
			for (int j = 0; j < list.size(); j++) {
				List<String> s = segmenter.sentenceProcess(list.get(j));
				for (int i=s.size()-1;i>=0;i--) {
					String s1=s.get(i);
					if (!s1.equals("，") && !s1.equals(";") && !s1.equals("。") && !s1.equals("？") && !s1.equals(")》")
							&& !s1.equals("一种") && !s1.equals("应用") && !s1.equals(",") && !s1.equals("的") && !s1.equals("我们")
							&& !s1.equals("方法") && !s1.equals("自己") && !s1.equals("之") && !s1.equals("将") && !s1.equals("我")
							&& !s1.equals("我们") && !s1.equals("你们") && !s1.equals("、") && !s1.equals("（") && !s1.equals("）")
							&& !s1.equals("关于") && !s1.equals(" &") && !s1.equals(".(") && !s1.equals("——") && !s1.equals("]")
							&& !s1.equals("基于") && !s1.equals("——")) {
						if(s1.length()>1) {
							out2.write(s1 + " ");
						}
					}
				}
				
			}

		out2.flush();
		out2.close();
		Learn learn = new Learn();
		learn.learnFile(new File("c:/file/book_title_word.txt"));
		learn.saveModel(new File("c:/file/javaSkip1"));
		Word2VEC vec = new Word2VEC();
		vec.loadJavaModel("c:/file/javaSkip1");
		
	}

	public String getPublicIp(String usr_ip) {
		try {
			String path = "http://whois.pconline.com.cn/ipJson.jsp?ip=" + usr_ip;// 要获得html页面内容的地址

			URL url = new URL(path);// 创建url对象

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();// 打开连接

			conn.setRequestProperty("contentType", "GBK"); // 设置url中文参数编码

			conn.setConnectTimeout(5 * 1000);// 请求的时间

			conn.setRequestMethod("GET");// 请求方式

			InputStream inStream = conn.getInputStream();
			// readLesoSysXML(inStream);

			BufferedReader in = new BufferedReader(new InputStreamReader(inStream, "GBK"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			// 读取获取到内容的最后一行,写入
			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}
			String str = buffer.toString();
			char c[] = str.toCharArray();
			char x[] = new char[1024];
			int y = 0;
			for (int k = 0; k < str.length(); k++) {
				if (k >= 34 && k <= str.length() - 4) {
					x[y] = c[k];
					y++;
				}
			}
			return String.valueOf(x);

		} catch (Exception e) {
			System.out.println("获取公网IP连接超时");
			return "连接超时";
		}
	}

	public void diannao() {
		Properties p = System.getProperties();// 获取当前的系统属性
		System.out.println("操作系统的名称：" + p.getProperty("os.name"));
		System.out.println("操作系统的版本：" + p.getProperty("os.version"));

	}

	public String time(String time) {
		int hour = Integer.valueOf(time.split(" ")[1].split(":")[0]);
		if (hour >= 0 && hour < 2) {
			time = "上午0-2时";
		} else if (hour >= 2 && hour < 4) {
			time = "上午2-4时";
		} else if (hour >= 4 && hour < 6) {
			time = "上午4-6时";
		} else if (hour >= 6 && hour < 8) {
			time = "上午6-8时";
		} else if (hour >= 8 && hour < 10) {
			time = "上午8-10时";
		} else if (hour >= 10 && hour < 12) {
			time = "上午10-12时";
		} else if (hour >= 12 && hour < 14) {
			time = "下午0-2时";
		} else if (hour >= 14 && hour < 16) {
			time = "下午2-4时";
		} else if (hour >= 16 && hour < 18) {
			time = "下午4-6时";
		} else if (hour >= 18 && hour < 20) {
			time = "下午6-8时";
		} else if (hour >= 20 && hour < 22) {
			time = "下午8-10时";
		} else if (hour >= 22 && hour < 24) {
			time = "下午10-12时";
		}
		return time;
	}
}
