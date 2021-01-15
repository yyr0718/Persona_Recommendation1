package com.nwsuaf.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nwsuaf.entity.ZL;

@Mapper     //声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可
@Repository
public interface ZLMapper {

	void addZL(String cert_id,String name,String sex,String birthday,String dept,String occupation,String password,String usr_state,String x1
			,String x11);
	ArrayList<ZL> ZLInquiry(int pagenum);
	int zlcount1();
	ArrayList<ZL> ZLjiansuo(@Param("valueOf")String valueOf,@Param("pagenum")int pagenum);
	int bookjiancount(String inputname);
	ArrayList<String> getfriend_zl(String findnamebyid);
}
