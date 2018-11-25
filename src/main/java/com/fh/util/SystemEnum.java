/** 
 * <pre>项目名称:shop-admin 
 * 文件名称:SystemEnum.java 
 * 包名:com.fh.shop.admin.common 
 * 创建日期:2018年10月19日下午2:02:25 
 * Copyright (c) 2018, ---li_li_h@163.comAll Rights Reserved.</pre> 
 */  
package com.fh.util;

/** 
 * <pre>项目名称：shop-admin    
 * 类名称：SystemEnum    
 * 类描述：    
 * 创建人：li_hh ---li_li_h@163.com
 * 创建时间：2018年10月19日 下午2:02:25    
 * 修改人：li_hh ---li_li_h@163.com
 * 修改时间：2018年10月19日 下午2:02:25    
 * 修改备注：       
 * @version </pre>    
 */
public enum SystemEnum {

	HEADER_IS_MISS(301,"请求头信息缺失"),
	HEADER_TIME_OUT(302,"请求头信息缺失"),
	SIGN_IS_MISS(303,"签名失效"),

	SMS_MOBILE_EMPTY(1000,"手机号为空"),
	SMS_MOBILE_INVALIDATE(1001,"手机号不合法"),
	REGISTER_ERROR_EMPTY(201,"注册信息不完整"),
	REGISTER_ERROR_USERNAME_NULL(202,"注册的用户已存在"),
	REGISTER_REDIS_CODE_EMPTY(203,"验证码已失效,请重新获取"),
	REGISTER_CODE_ERROR(204,"验证码错误"),

	SERVER_SUCCESS(200,"操作成功"),
	APPKKEY_IS_MISS(304,"APPKER失效"),
	SERVER_FAIL(500,"操作失败");


	private  int  code;
	
	private  String  message;

	
	private SystemEnum(int code  ,String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}


	public String getMessage() {
		return message;
	}
	
	
	
	
}
