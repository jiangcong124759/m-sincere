package com.sg.zhsd.uav.utils;

import com.alibaba.excel.EasyExcel;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 */
public class ExcelUtils {

	/**
	 * 
	 * @Title: downloadExcel web 
	 * @Description: WEB 后台下载Excel 返回流对象
	 * @param response
	 * @param head 头部信息实体
	 * @param data 数据
	 * @param fileName 文件名称
	 * @param sheetName  sheet名称
	 * @return void
	 */
	public static void downloadExcel(HttpServletResponse response, Class head, List data, String fileName,String sheetName) {
		response.setContentType("application/vnd.ms-excel");
		response.setCharacterEncoding("utf-8");
		// 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
		try {
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.setHeader("Content-disposition", "attachment;filename=" + fileName);
			EasyExcel.write(response.getOutputStream(), head).sheet(sheetName).doWrite(data);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
