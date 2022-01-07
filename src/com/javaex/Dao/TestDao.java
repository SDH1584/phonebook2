package com.javaex.Dao;

import java.util.List;

import com.javaex.vo.PersonVo;

public class TestDao {

	public static void main(String[] args) {
		
		PhoneDao phoneDao=new PhoneDao();
		List <PersonVo>personList=	phoneDao.getPersonList();
	
		System.out.println(personList.toString());
	}

}
