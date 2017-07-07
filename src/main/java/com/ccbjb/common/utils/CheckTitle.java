package com.ccbjb.common.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 用来在对象的get方法上加入的annotation，通过该annotation说明某个属性所对应的标题
 * @author LiuJunpeng 
 * @version v1.0 
 * @date 2016年9月26日 下午2:06:51 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckTitle {
	 

	/**
	 * 是否验证
	 */
	boolean validate();
}

