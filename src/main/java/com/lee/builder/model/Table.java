package com.lee.builder.model;

import java.util.List;

/**
 * Created by lee on 2017/6/20.
 */
public class Table {
	
	private String tableName;
	private String tableComment;
	private List<Column> columns;
	
	public Table(String tableName, String tableComment, List<Column> columns){
		this.tableName = tableName;
		this.tableComment = tableComment;
		this.columns = columns;
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getTableComment() {
		return tableComment;
	}
	
	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}
	
	public List<Column> getColumns() {
		return columns;
	}
	
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
}
