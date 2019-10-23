package com.application;

import com.application.utils.DBUtil;

public class MyTest {
	public static void main(String[] args) {

		DBUtil dbUtil= new DBUtil();
		dbUtil.readProps();
		dbUtil.getConnection();
		dbUtil.closeConnection();
	}
}
