package com.lee.builder.service;

import com.lee.builder.model.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lee on 2017/6/22.
 */
public interface IGengerateService {
	
	List<String> strList = new ArrayList<String>();
	boolean generateModelClass(String templeteName, String packageName, String path, Table table, List<String> packageList);
	boolean generateDao(String templeteName, String packageName, String path, Table table);
	boolean generateMapper(String templeteName, String packageName, String path, Table table, String dbType);
	boolean generateService(String templeteName, String packageName, String path, Table table);
	Map<String, Object> convertColumnType(Table table);
}
