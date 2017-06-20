package com.lee.builder.service.impl;

import com.lee.builder.model.Column;
import com.lee.builder.model.Database;
import com.lee.builder.model.Table;
import com.lee.builder.service.IDatabaseService;
import com.lee.builder.utils.DBUtils;
import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lee on 2017/6/20.
 */
public class DatabaseServiceImpl implements IDatabaseService {
	
	
	@Override
	public List<String> getTableNameList(Database db) {
		
		List<String> tableNameList = new ArrayList<String>();
		
		DatabaseMetaData metaData = getMetaData(db);
		
		try {
		 	if (metaData != null) {
			    ResultSet tableSet = metaData.getTables(null, "%", "%", new String[]{"TABLE"});
		 		while (tableSet.next()){
				    tableNameList.add(tableSet.getString("TABLE_NAME"));
			    }
		    }
		}catch (Exception e){
			e.printStackTrace();
		}
		return tableNameList;
	}
	
	@Override
	public Table getTableByName(String tableName, Database db) {
		
		DatabaseMetaData metaData = getMetaData(db);
		try{
			if (metaData != null) {
				
				ResultSet tableSet = metaData.getTables(null, "%", tableName, new String[]{"TABLE"});
				Table table = null;
				if (tableSet.next()) {
					String tmpTableName = tableSet.getString("TABLE_NAME");
					String tmpTableComment = tableSet.getString("REMARKS");
					if(StringUtils.isNotEmpty(tmpTableName)) table = new Table(tmpTableName, tmpTableComment, null);
				}
				if(table == null) return null;
				
				ResultSet columnSet = metaData.getColumns(null, "%", tableName, "%");
				
				List<Column> columns = new ArrayList<Column>();
				String columnName = null, columnComment = null, columnType = null;
				while(columnSet.next()){
					columnName = columnSet.getString("COLUMN_NAME");
					columnComment = columnSet.getString("REMARKS");
					columnType = columnSet.getString("DATA_TYPE");
					columns.add(new Column(columnName, columnComment, columnType));
				}
				table.setColumns(columns);
				return table;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	private DatabaseMetaData getMetaData(Database db){
		
		Connection conn = null;
		try{
			conn = DBUtils.getConnecttion(db);
			return conn.getMetaData();
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
