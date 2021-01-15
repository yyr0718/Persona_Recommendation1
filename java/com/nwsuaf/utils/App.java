package com.nwsuaf.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.WordDictionary;
import com.nwsuaf.mapper.BookMapper;
import com.nwsuaf.mapper.UserMapper;
import com.nwsuaf.service.UserService;

/**
 * Hello world!
 *
 */
public class App {
	public static void startR(String id,ArrayList<String> list) throws IOException {
		JiebaSegmenter segmenter = new JiebaSegmenter();
		File f1 = new File("c:/file/library/" + id + ".txt");
		
		OutputStream out = null;
		BufferedWriter bw = null;
		if (f1.exists()) {
			System.out.println("存在");
		} else {
			out = new FileOutputStream(f1);
			bw = new BufferedWriter(new OutputStreamWriter(out, "utf-8"));
			bw.write("西北农林科技大学欢迎您");
			bw.flush();
			bw.close();
			System.out.println("创建成功");
		}
		WordDictionary.getInstance().init(Paths.get("src/main/resources/conf"));
		File f3 = new File("c:/file/word/" + id);
		if (f3.exists()) {
		} else {
			f3.mkdir();
		}
		OutputStreamWriter out2 = new OutputStreamWriter(new FileOutputStream("c:/file/word/" + id + "/" + id + ".txt"),
				"UTF-8");
		out = new FileOutputStream(f1);
		bw = new BufferedWriter(new OutputStreamWriter(out, "utf-8"));
		for (int j = 0; j < list.size(); j++) {
			List<String> s = segmenter.sentenceProcess(list.get(j));
			for (int i=s.size()-1;i>=0;i--) {
				String s1=s.get(i);
				if (!s1.equals("，") && !s1.equals(";") && !s1.equals("。") && !s1.equals("？") && !s1.equals(")》")
						&& !s1.equals("一种") && !s1.equals("应用") && !s1.equals(",") && !s1.equals("的") && !s1.equals("我们")
						&& !s1.equals("方法") && !s1.equals("自己") && !s1.equals("之") && !s1.equals("将") && !s1.equals("我")
						&& !s1.equals("我们") && !s1.equals("你们") && !s1.equals("、") && !s1.equals("（") && !s1.equals("）")
						&& !s1.equals("关于") && !s1.equals(" &") && !s1.equals(".(") && !s1.equals("——") && !s1.equals("]")
						&& !s1.equals("基于") && !s1.equals("——")&& !s1.equals("——《")&& !s1.equals(")》“")) {
					if(s1.length()>1) {
						out2.write(s1 + " ");
					}
				}
			}
			
		}
		bw.flush();
		bw.close();
		
		out2.flush();
		out2.close();
		Learn learn = new Learn();
		learn.learnFile(new File("c:/file/word/" + id + "/" + id + ".txt"));
		learn.saveModel(new File("c:/file/library/" + id + "javaSkip1"));
		Word2VEC vec = new Word2VEC();
		vec.loadJavaModel("c:/file/library/" + id + "javaSkip1");
		
	}

	public static void startR_lw(String id,ArrayList<String> list) throws IOException {
		JiebaSegmenter segmenter = new JiebaSegmenter();
		String sentences = "";
		File f1 = new File("c:/file/library_lw/" + id + ".txt");
		OutputStream out = null;
		BufferedWriter bw = null;
		if (f1.exists()) {
			System.out.println("存在");
		} else {
			out = new FileOutputStream(f1);
			bw = new BufferedWriter(new OutputStreamWriter(out, "utf-8"));
			bw.write("西北农林科技大学欢迎您");
			bw.flush();
			bw.close();
			System.out.println("创建成功");
		}
		File f3 = new File("c:/file/word_lw/" + id);
		if (f3.exists()) {
		} else {
			f3.mkdir();
		}
		OutputStreamWriter out2 = new OutputStreamWriter(new FileOutputStream("c:/file/word_lw/" + id + "/" + id + ".txt"),
				"UTF-8");
		out = new FileOutputStream(f1);
		bw = new BufferedWriter(new OutputStreamWriter(out, "utf-8"));
		for (int j = 0; j < list.size(); j++) {
			List<String> s = segmenter.sentenceProcess(list.get(j));
			for (int i=0;i<s.size();i++) {
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
			sentences = sentences + list.get(j);
			bw.write(list.get(j) + "\r\n");
		}
		bw.flush();
		bw.close();

		WordDictionary.getInstance().init(Paths.get("src/main/resources/conf"));
		
		
		
		out2.flush();
		out2.close();
		Learn learn = new Learn();
		learn.learnFile(new File("c:/file/word_lw/" + id + "/" + id + ".txt"));
		learn.saveModel(new File("c:/file/library_lw/" + id + "javaSkip1"));
		Word2VEC vec = new Word2VEC();
		vec.loadJavaModel("c:/file/library_lw/" + id + "javaSkip1");
	}
	
	public static void startR_zl(String id,ArrayList<String> list) throws IOException {
		JiebaSegmenter segmenter = new JiebaSegmenter();
		String sentences = "";
		File f1 = new File("c:/file/library_zl/" + id + ".txt");
		OutputStream out = null;
		BufferedWriter bw = null;
		if (f1.exists()) {
			System.out.println("存在");
		} else {
			out = new FileOutputStream(f1);
			bw = new BufferedWriter(new OutputStreamWriter(out, "utf-8"));
			bw.write("西北农林科技大学欢迎您");
			bw.flush();
			bw.close();
			System.out.println("创建成功");
		}
		out = new FileOutputStream(f1);
		bw = new BufferedWriter(new OutputStreamWriter(out, "utf-8"));
		for (int j = 0; j < list.size(); j++) {
			sentences = sentences + list.get(j);
			bw.write(list.get(j) + "\r\n");
		}
		bw.flush();
		bw.close();

		WordDictionary.getInstance().init(Paths.get("src/main/resources/conf"));
		File f3 = new File("c:/file/word_zl/" + id);
		if (f3.exists()) {
		} else {
			f3.mkdir();
		}
		OutputStreamWriter out2 = new OutputStreamWriter(new FileOutputStream("c:/file/word_zl/" + id + "/" + id + ".txt"),
				"UTF-8");
		List<String> s = segmenter.sentenceProcess(sentences);
		for (String s1 : s) {
			if (!s1.equals("，") && !s1.equals(";") && !s1.equals("。") && !s1.equals("？") && !s1.equals(".")
					&& !s1.equals("：") && !s1.equals("“") && !s1.equals(",") && !s1.equals("的") && !s1.equals("我们")
					&& !s1.equals("方法") && !s1.equals("自己") && !s1.equals("之") && !s1.equals("将") && !s1.equals("我")
					&& !s1.equals("我们") && !s1.equals("你们") && !s1.equals("、") && !s1.equals("（") && !s1.equals("）")
					&& !s1.equals("(") && !s1.equals(")") && !s1.equals(")") && !s1.equals("[") && !s1.equals("]")
					&& !s1.equals("《") && !s1.equals("——")) {
				if(s1.length()>1) {
					out2.write(s1 + " ");
				}
				
			}
		}
		out2.flush();
		out2.close();
		Learn learn = new Learn();
		learn.learnFile(new File("c:/file/word_zl/" + id + "/" + id + ".txt"));
		learn.saveModel(new File("c:/file/library_zl/" + id + "javaSkip1"));
		Word2VEC vec = new Word2VEC();
		vec.loadJavaModel("c:/file/library_zl/" + id + "javaSkip1");
	}
}
