package com.nwsuaf.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.nwsuaf.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper     //声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可
@Repository
public interface UserMapper {
	String findPasswdByID(String name);
	String findnamebyid(String id);
	void insertKey(String id,String key1,String key2,String key3,String key4,String key5,
			String key6,String key7,String key8,String key9,String key10,String y1,String y2,String y3,String y4,String y5,
			String y6,String y7,String y8,String y9,String y10);
	void insertLWKey(String id, String s, String s1, String s2, String s3, String s4, String s5,
					 String s6, String s7, String s8, String s9, String s10, String s11, String s12,
					 String s13, String s14, String s15, String s16, String s17, String s18, String s19);

	void updateUser(String usr_passwd,String age,String xy,String sex,String usr_id);
	void sys_updateUser(String usr_passwd,String age,String xy,String sex,String usr_id,String occupation, String phone);
	String findxyByID(String id);
	ArrayList<User>findUser(@Param("id")String id,@Param("num")int num);
	int usercount(String id);
	ArrayList<Book> userjiansuo(String id);
	void insertUser(String cert_id,String name,String sex,String birthday,String dept,String occupation,String password,String usr_state);
	void deleteByID(String id);
	void dongjie(String id);
	void activeUser(String id);
	String findAge(String id);
	ArrayList<String> getbooks(String id);
	ArrayList<String> getlws(String name);
	void insertPH(String id, String time, String ip, String zd, String weizhi);
	ph findip(String id);
	ph findtime(String id);
	ph findzd(String id);
	ph findweizhi(String id);
	String [] findAllid();
	ArrayList<HashMap<String,String>> findSimilar(@Param("book_bq")String book_bq, @Param("id")String id);
	Integer getlendc(String cert_id);
	ArrayList<HashMap<String, String>> finddongtai(String cert_id);
	void dingup(Integer valueOf);
	void caiup(Integer valueOf);
	void insertDT(String cert_id, String text, String ti);
	ArrayList<HashMap<String, String>> findLY(String valueOf);
	void insertLY(String cert_id, String text, String usr_id);
	Integer findtotlesize(String cert_id);
	Integer findfabiaoc(String cert_id);
	Integer findqushi(String string, String id, String string2);
	ArrayList<book_bq> findbook_bq(String id);
	ArrayList<book_bq> findwx_bq(String id);
	ArrayList<String> findSimilar_wx(String string);
	ArrayList<String> findSimilar_lw(String string);
	User finduserbyname(String string);
	Reader finduserbyname1(String string);
	ArrayList<String> findxinxi(String xueyuan);
	ArrayList<String> findlendxinxi(String string);
}
