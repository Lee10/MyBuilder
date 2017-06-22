package com.lee.builder.model;

/**
 * Created by lee on 2017/6/16.
 */
public class Column {
	
	private String columnName;
	private String columnComment;
	private String columnType;
	private String propertyName;
	private String javaType;
	
	public Column(String columnName, String columnComment, String columnType){
		this.columnName = columnName;
		this.columnComment = columnComment;
		this.columnType = columnType;
	}
	
	public String getColumnName() {
		return columnName;
	}
	
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	public String getColumnComment() {
		return columnComment;
	}
	
	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}
	
	public String getColumnType() {
		return columnType;
	}
	
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	
	public String getPropertyName() {
		return propertyName;
	}
	
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	
	public String getJavaType() {
		return javaType;
	}
	
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
}