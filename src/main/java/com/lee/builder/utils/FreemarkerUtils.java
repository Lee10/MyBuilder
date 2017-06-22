package com.lee.builder.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by lee on 2017/5/11.
 */
public class FreemarkerUtils {

	public static Template getTemplate(String name){
		
		try {
			Configuration conf = new Configuration();
			conf.setClassForTemplateLoading(FreemarkerUtils.class, "/templete");
			return conf.getTemplate(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void print(String name, Map<String, Object> root){
	
		try {
			Template template = getTemplate(name);
			template.process(root, new PrintWriter(new OutputStreamWriter(System.out, "utf-8")));
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static void fprint(String name, Map<String, Object> root, String outFilePath){
		BufferedWriter writer = null;
		try{
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFilePath), "utf-8"));
			Template template = getTemplate(name);
			template.process(root, writer);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (writer != null) writer.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}

}
