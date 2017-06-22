package com.lee.builder;

import com.lee.builder.model.Database;
import com.lee.builder.model.Table;
import com.lee.builder.service.IDatabaseService;
import com.lee.builder.service.IGengerateService;
import com.lee.builder.service.impl.DatabaseServiceImpl;
import com.lee.builder.service.impl.GenerateServiceImpl;
import com.lee.builder.utils.DBUtils;
import org.junit.Test;

import java.util.Map;

/**
 * Created by lee on 2017/6/22.
 */
public class GenerateServiceTest {
	
	@Test
	public void testConvertColumnType(){
		
		IDatabaseService databaseService = new DatabaseServiceImpl();
		IGengerateService gengerateService = new GenerateServiceImpl();
		
		Database mysqlDB = new Database("localhost", 3306, "lweb", DBUtils.DB_TYPE_MYSQL, "root", "root");
		Table table = databaseService.getTableByName("sys_menu", mysqlDB);
		Map<String, Object> resultMap = gengerateService.convertColumnType(table);
	}
	
	@Test
	public void testGenerateModelClass(){
		
		IDatabaseService databaseService = new DatabaseServiceImpl();
		IGengerateService gengerateService = new GenerateServiceImpl();
		
		Database mysqlDB = new Database("localhost", 3306, "hn-sms", DBUtils.DB_TYPE_MYSQL, "root", "root");
		Table table = databaseService.getTableByName("sms_pub_key_dic", mysqlDB);
		boolean flag = gengerateService.generateModelClass("ModelTemplete.java", "com.lee.coderepo.model", "/Users/lee/Downloads/test/SmsPubKeyDic.java", table);
		System.out.println(flag);
	}
	
}
