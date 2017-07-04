package com.lee.builder;

import com.lee.builder.model.Database;
import com.lee.builder.model.Table;
import com.lee.builder.service.IDatabaseService;
import com.lee.builder.service.impl.DatabaseServiceImpl;
import com.lee.builder.utils.DBUtils;
import org.junit.Test;

import java.util.List;

/**
 * Created by lee on 2017/6/20.
 */
public class DatabaseServiceImplTest {
	
	@Test
	public void testTableNameList(){
		
		/*IDatabaseService databaseService = new DatabaseServiceImpl();
		
		Database mysqlDB = new Database("localhost", 3306, "lweb", DBUtils.DB_TYPE_MYSQL, "root", "root");
		Database oracleDB = new Database("192.168.0.249", 1521, "huitone", DBUtils.DB_TYPE_ORACLE, "smp", "smp");
		Database sqliteDB = new Database(null, null, "/Users/lee/code/MyBuilder/src/main/resources/conf.db", DBUtils.DB_TYPE_SQLITE, null, null);
		
		List<String> tableNameList = databaseService.getTableNameList(mysqlDB);
		for (String tableName : tableNameList) {
			System.out.println(tableName);
		}*/
	}
	
	@Test
	public void testTableList(){
		
		/*IDatabaseService databaseService = new DatabaseServiceImpl();
		
		Database mysqlDB = new Database("localhost", 3306, "lweb", DBUtils.DB_TYPE_MYSQL, "root", "root");
		Database oracleDB = new Database("192.168.0.249", 1521, "huitone", DBUtils.DB_TYPE_ORACLE, "smp", "smp");
		Database sqliteDB = new Database(null, null, "/Users/lee/code/MyBuilder/src/main/resources/conf.db", DBUtils.DB_TYPE_SQLITE, null, null);
		
		Table table = databaseService.getTableByName("sys_menu", mysqlDB);*/
	}
	
}
