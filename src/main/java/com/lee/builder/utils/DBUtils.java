package com.lee.builder.utils;

import com.lee.builder.model.Database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lee on 2017/6/14.
 */
public class DBUtils {

	public static List<Map<String, Object>> executeSelect(Database database, String sql, Map<String, Object> params){
	
	
		
		return null;
	}
	
	
	
	
	public static final String DB_TYPE_MYSQL = "mysql";
	public static final String DB_TYPE_ORACLE = "oracle";
	public static final String DB_TYPE_SQLITE = "sqlite";
	private static final Map<String, String> JdbcDriverMap = new HashMap<String, String>();
	static {
		JdbcDriverMap.put(DB_TYPE_MYSQL, "com.mysql.jdbc.Driver");
		JdbcDriverMap.put(DB_TYPE_ORACLE, "oracle.jdbc.driver.OracleDriver");
		JdbcDriverMap.put(DB_TYPE_SQLITE, "");
	}


}
