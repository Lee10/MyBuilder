package com.lee.builder.service.impl;

import com.lee.builder.model.Column;
import com.lee.builder.model.Table;
import com.lee.builder.service.IGengerateService;
import com.lee.builder.utils.FreemarkerUtils;
import org.apache.commons.lang3.StringUtils;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by lee on 2017/6/22.
 */
public class GenerateServiceImpl implements IGengerateService {

	@Override
	public boolean generateModelClass(String templeteName, String packageName, String path, Table table, List<String> packageList) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("packageName", packageName);
		params.put("tableComment", table.getTableComment() + "(" + table.getTableName() + ")");
		params.put("packageList", packageList);
		params.put("modelName", capColumnName(table.getTableName()));
		params.put("columns", table.getColumns());
		return FreemarkerUtils.fprint(templeteName, params, path);
	}
	
	@Override
	public boolean generateDao(String templeteName, String packageName, String path, Table table) {
		
		String modelName = StringUtils.capitalize(capColumnName(table.getTableName()));
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("packageName", packageName);
		params.put("modelName", modelName);
		return FreemarkerUtils.fprint(templeteName, params, path);
	}
	
	@Override
	public boolean generateMapper(String templeteName, String packageName, String path, Table table, String dbType) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		String modelName = StringUtils.capitalize(capColumnName(table.getTableName()));
		params.put("packageName", packageName);
		params.put("modelName", modelName);
		params.put("dbType", dbType);
		params.put("tableName", table.getTableName());
		params.put("columns", table.getColumns());
		return FreemarkerUtils.fprint(templeteName, params, path);
	}
	
	@Override
	public boolean generateService(String templeteName, String packageName, String path, Table table) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		String modelName = StringUtils.capitalize(capColumnName(table.getTableName()));
		params.put("packageName", packageName);
		params.put("modelName", modelName);
		return FreemarkerUtils.fprint(templeteName, params, path);
	}
	
	@Override
	public Map<String, Object> convertColumnType(Table table) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("table", table);
		
		List<String> packageList = new ArrayList<String>();
		
		if (table != null && table.getColumns() != null && table.getColumns().size() > 0) {
			
			List<Column> newColumns = new ArrayList<Column>();
			for (Column column : table.getColumns()) {
				
				column.setPropertyName(capColumnName(column.getColumnName()));
				
				switch (Integer.parseInt(column.getColumnType())) {
					
					case Types.CHAR:
						column.setColumnType("CHAR");
						column.setJavaType("String");
						break;
					case Types.VARCHAR:
						column.setColumnType("VARCHAR");
						column.setJavaType("String");
						break;
					case Types.LONGNVARCHAR:
						column.setColumnType("LONGNVARCHAR");
						column.setJavaType("String");
						break;
					case Types.INTEGER:
						column.setColumnType("INTEGER");
						column.setJavaType("Integer");
						break;
					case Types.BIGINT:
						column.setColumnType("BIGINT");
						column.setJavaType("Long");
						break;
					case Types.REAL:
						column.setColumnType("REAL");
						column.setJavaType("Float");
						break;
					case Types.FLOAT:
						column.setColumnType("FLOAT");
						column.setJavaType("Float");
						break;
					case Types.DOUBLE:
						column.setColumnType("DOUBLE");
						column.setJavaType("Double");
						break;
					case Types.NUMERIC:
						column.setColumnType("NUMERIC");
						column.setJavaType("BigDecimal");
						packageList.add("java.math.BigDecimal");
						break;
					case Types.DECIMAL:
						column.setColumnType("DECIMAL");
						column.setJavaType("BigDecimal");
						packageList.add("java.math.BigDecimal");
						break;
					case Types.BIT:
						column.setColumnType("BIT");
						column.setJavaType("Boolean");
						break;
					case Types.BOOLEAN:
						column.setColumnType("BOOLEAN");
						column.setJavaType("Boolean");
						break;
					case Types.TINYINT:
						column.setColumnType("TINYINT");
						column.setJavaType("byte");
						break;
					case Types.BINARY:
						column.setColumnType("TINYINT");
						column.setJavaType("byte");
						break;
					case Types.VARBINARY:
						column.setColumnType("VARBINARY");
						column.setJavaType("byte[]");
						break;
					case Types.LONGVARBINARY:
						column.setColumnType("LONGVARBINARY");
						column.setJavaType("byte[]");
						break;
					case Types.DATE:
						column.setColumnType("DATE");
						column.setJavaType("Date");
						packageList.add("java.util.Date");
						break;
					case Types.TIME:
						column.setColumnType("TIME");
						column.setJavaType("Date");
						packageList.add("java.util.Date");
						break;
					case Types.TIMESTAMP:
						column.setColumnType("TIMESTAMP");
						column.setJavaType("Date");
						packageList.add("java.util.Date");
						break;
					default:
						column.setColumnType("VARCHAR");
						column.setJavaType("String");
				}
				newColumns.add(column);
			}
			table.setColumns(newColumns);
		}
		resultMap.put("packageList", new ArrayList<String>(new HashSet<String>(packageList)));
		return resultMap;
	}
	
	private String capColumnName(String columnName){
		
		String[] arr = StringUtils.split(columnName, "_");
		if(arr != null && arr.length > 0) {
			StringBuilder strBuilder = new StringBuilder(arr[0]);
			for (int i = 1; i < arr.length; i++) {
				strBuilder.append(StringUtils.capitalize(arr[i]));
			}
			return strBuilder.toString();
		}
		return columnName;
	}
}
