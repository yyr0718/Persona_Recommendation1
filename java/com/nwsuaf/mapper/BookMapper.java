package com.nwsuaf.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nwsuaf.entity.Book;
import com.nwsuaf.entity.Lend;
import com.nwsuaf.entity.book_bq;

@Mapper     //声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可
@Repository
public interface BookMapper {
	void deleteBookByID(String id);
	void dongjieBook(String id);
	void activeBook(String id);
	ArrayList<Lend> lendInquiry(String id);
	ArrayList<Book> bookInquiry(int pagenum);
	ArrayList<Book> bookjiansuo(@Param("name")String name,@Param("pagenum")int pagenum);
	int bookcount();
	void booklend(String CERT_ID,String PROP_NO_F,String LEND_DATE,
			String RET_DATE,String MARC_REC_NO_F);
	String findbooknobyname(String bookname);
	void updateflag(String flag,String bookid);
	void addBook(String cert_id,String name,String sex,String birthday,String dept,String occupation,String password,String usr_state);
	ArrayList<Lend> sysLendInquiry(int pagenum);
	int syscount();
	void deleteLend(String parameter);
	int bookjiancount(String name);
	book_bq getbook_bq(String valueOf);
	String findsuo(String marc_REC_NO_F);
	String findlei(String s1);
	void setleixing(String id,String leixing);
	ArrayList<HashMap<String, String>>  findleixing(String id);
	ArrayList<Book> booktuijian(String x);
	void addlend(String cert_ID_F, String prop_NO_F, String lend_DATE, String ret_DATE, String renew_TIMES,
			String marc_REC_NO_F);
}
