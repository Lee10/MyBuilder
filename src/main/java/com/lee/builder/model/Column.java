package com.lee.builder.model;

import javafx.scene.control.CheckBox;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Column column = (Column) o;

		if (columnName != null ? !columnName.equals(column.columnName) : column.columnName != null) return false;
		if (columnComment != null ? !columnComment.equals(column.columnComment) : column.columnComment != null)
			return false;
		if (columnType != null ? !columnType.equals(column.columnType) : column.columnType != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = columnName != null ? columnName.hashCode() : 0;
		result = 31 * result + (columnComment != null ? columnComment.hashCode() : 0);
		result = 31 * result + (columnType != null ? columnType.hashCode() : 0);
		return result;
	}
}
