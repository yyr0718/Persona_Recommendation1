package com.nwsuaf.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nwsuaf.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper     //声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可
@Repository
public interface ReaderMapper {

	List<BiaoQianEntity> getBQByKind(String kind,String dept);

	List<Map<String, String>> getSexInfo(String kind,String dept);
	List<Map<String, String>> getOccInfo(String kind,String dept);

	Reader getReaderById(String cert_id);

	List<TuiJianBook> getTuiJianByKind(String kind);

	TuiJianBook getBookById(String bookid);
	TuiJianLW getArticleById(String lwid);

	List<TuiJianBook> getTopTuiJian();

	List<TuiJianBook> getNewTuiJian();

	BiaoQianEntity getBQ(String cert_id);

	List<TuiJianBook> searchBook(String search);

	List<TuiJianBook> getTuiJianByTeacherInfor(@Param(value = "sex")String sex, @Param(value = "dept")String dept, @Param(value = "occupation")String occupation);

	List<TuiJianBook> getTuiJianByInfor(@Param(value = "sex")String sex, @Param(value = "dept")String dept, @Param(value = "id")String id);

	List<TuiJianBook> getTuiJianByBq(@Param(value = "biaoqian")String biaoqian, @Param(value = "num")int num);

	String findPasswdByID(String name);
	String findnamebyid(String id);
	void insertKey(String id,String key1,String key2,String key3,String key4,String key5,
				   String key6,String key7,String key8,String key9,String key10,String y1,String y2,String y3,String y4,String y5,
				   String y6,String y7,String y8,String y9,String y10);
	void updateReader(String usr_passwd,String age,String xy,String sex,String usr_id, String phone);
	void sys_updateReader(String usr_passwd,String age,String xy,String sex,String usr_id,String occupation);
	String findxyByID(String id);
	ArrayList<Reader>findReader(@Param("id")String id,@Param("num")int num);
	int usercount(String id);
	ArrayList<TuiJianBook> userjiansuo(String id);
	void insertReader(String cert_id,String name,String sex,String birthday,String dept,String occupation,String password,String usr_state);
	void deleteByID(String id);
	void dongjie(String id);
	void activeReader(String id);
	String findAge(String id);
	ArrayList<String> getbooks(String id);
	void insertPH(String id, String time, String ip, String zd, String weizhi);

	String [] findAllid();
	ArrayList<HashMap<String,String>> findSimilar(@Param("BiaoQianEntity")String BiaoQianEntity, @Param("id")String id);
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
	ArrayList<BiaoQianEntity> findBiaoQianEntity(String id);
	ArrayList<BiaoQianEntity> findwx_bq(String id);
	ArrayList<String> findSimilar_wx(String string);
	ArrayList<String> findSimilar_lw(String string);
	Reader finduserbyname(String string);
	ArrayList<String> findxinxi(String xueyuan);

	ArrayList<TuiJianBook>findBook(@Param("id")String id,@Param("num")int num);
	ArrayList<TuiJianLW>findArticle(@Param("id")String id, @Param("num")int num);
	ArrayList<TuiJianLW>findAllArticle( @Param("num")int num);
	int bookcount(String id);
	int articlecount(String id);

	void sys_updateBook(String book_id, String book_name, String book_kind, String author, String publisher,String year,String image);

	void sys_updateArticle(int article_id, String subject, String author, String type, String abstract1, Integer download_num,Integer quote_num, String periodical, String publish_time, String key, String fund, String unit);

	List<TuiJianLW> getTuiJianLWByKind(String kind_lw, String author);

	List<TuiJianLW> getTuiJianByInfor_Lw(String dept,String author );

	List<TuiJianLW> getTopTuiJianLW(String name);

	List<TuiJianLW> getNewTuiJianLW(String name);

	List<TuiJianLW> getTuiJianLWByBq(@Param("s") String s, @Param("num") int num, @Param("name") String name);

	List<TuiJianLW> searchLW(String s);

	BiaoQianEntity getLWBQ(String cert_id);
}
