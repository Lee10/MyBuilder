package com.lee.builder.service;

import com.lee.builder.model.Database;
import com.lee.builder.model.Table;

import java.sql.Connection;
import java.util.List;

/**
 * Created by lee on 2017/6/20.
 */
public interface IDatabaseService {
	
	List<String> getTableNameList(Database db);
	
	Table getTableByName(String tableName, Database db);
}
