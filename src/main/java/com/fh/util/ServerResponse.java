/** 
 * <pre>项目名称:shop-admin 
 * 文件名称:ServerResponse.java 
 * 包名:com.fh.shop.admin.common 
 * 创建日期:2018年10月18日下午6:59:19 
 * Copyright (c) 2018, ---li_li_h@163.comAll Rights Reserved.</pre> 
 */  
package com.fh.util;

import java.io.Serializable;

/** 
 * <pre>项目名称：shop-admin    
 * 类名称：ServerResponse    
 * 类描述：    
 * 创建人：li_hh ---li_li_h@163.com
 * 创建时间：2018年10月18日 下午6:59:19    
 * 修改人：li_hh ---li_li_h@163.com
 * 修改时间：2018年10月18日 下午6:59:19    
 * 修改备注：       
 * @version </pre>    
 */
public class ServerResponse implements Serializable{
	
	private static final long serialVersionUID = 3177830195134708701L;

	private  int	  code;
	
	private  String   message;
	
	private  Object	  data;
	
	public static ServerResponse  success(Object  data) {
		return  new ServerResponse(SystemEnum.SERVER_SUCCESS.getCode(), SystemEnum.SERVER_SUCCESS.getMessage(), data);
	}
	
	public static ServerResponse  success() {
		return  new ServerResponse(SystemEnum.SERVER_SUCCESS.getCode(), SystemEnum.SERVER_SUCCESS.getMessage(), null);
	}

	/**
	 * 调用网易云失败
	 */
	public static ServerResponse  error(int code , String message) {
		return  new ServerResponse(code, message,null);
	}

	/**
	 * 服务器异常
	 */
	public static ServerResponse  error() {
		return  new ServerResponse(SystemEnum.SERVER_FAIL.getCode(), SystemEnum.SERVER_FAIL.getMessage(),null);
	}
	
	/**
	 * 登录
	 */
	public static ServerResponse  error(SystemEnum  systemEnum) {
		return  new ServerResponse(systemEnum.getCode(), systemEnum.getMessage(),null);
	}
	
	public ServerResponse(int  code , String  message, Object  data) {
		this.code 	 = code;
		this.message = message;
		this.data	 = data;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}
	
}
