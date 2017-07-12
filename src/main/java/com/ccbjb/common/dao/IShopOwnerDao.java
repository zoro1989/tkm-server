/**
 * 
 */
package com.ccbjb.common.dao;

import com.ccbjb.common.entity.ShopOwner;

import java.util.Map;


/**
 * TODO 此处输入类或接口的概要说明
 * <p>
 * TODO  此处输入类或接口的详细说明（可省略） 此处输入参数或返回值的相应说明
 * @version 1.0
 * @since 1.0
 * @author CJB-国内开发组
 */
public interface IShopOwnerDao {

	ShopOwner login(Map<String, Object> map);
}
