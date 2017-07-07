package com.ccbjb.common.division;

/**
 * 星期值枚举
 * @author CJB-ChnGroup
 * @version v1.0 
 * @date 2017年4月5日 上午10:17:59 	 
 */
public enum DateTimeDiffType {
	/**
	 * 年数
	 */
	Years(1,"年数"),
	/**
	 * 月数
	 */
	Months(2,"月数"),
	/**
	 * 天数
	 */
	Days(3,"天数"),
	/**
	 * 小时数
	 */
	Hours(4,"小时数"),
	/**
	 * 分钟数
	 */
	Minutes (5,"分钟数"),
	/**
	 * 秒数
	 */
	Seconds(6,"秒数"),
	/**
	 * 毫秒数
	 */
	Millis(7,"毫秒数"),
	/**
	 * 星期数
	 */
	Weeks(8,"星期数");
	
	private int code;
	private String info;

    DateTimeDiffType(int code, 
    		String info) {
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return code;
    }
    public String getInfo() {
        return info;
    }

    public static DateTimeDiffType dateTimeDiffType(int code){
        for (DateTimeDiffType dateTimeDiffType:values()){
            if(dateTimeDiffType.getCode()==code){
                return dateTimeDiffType;
            }
        }
        return null;
    }
}

