package com.ccbjb.common.division;

/**
 * 星期值枚举
 * @author CJB-ChnGroup
 * @version v1.0 
 * @date 2017年4月5日 上午10:17:59 	 
 */
public enum WeekDay {
	/**
	 * 星期一
	 */
	Monday(1,"一","周一","星期一","礼拜一","月","月曜日","MON","Monday"),
	/**
	 * 星期二
	 */
	Tuesday(2,"二","周二","星期二","礼拜二","火","火曜日","TUE","Tuesday"),
	/**
	 * 星期三
	 */
	Wednesday(3,"三","周三","星期三","礼拜三","水","水曜日","WED","Wednesday"),
	/**
	 * 星期四
	 */
	Thursday(4,"四","周四","星期四","礼拜四","木","木曜日","THU","Thursday"),
	/**
	 * 星期五
	 */
	Friday (5,"五","周五","星期五","礼拜五","金","金曜日","FRI","Friday"),
	/**
	 * 星期六
	 */
	Saturday(6,"六","周六","星期六","礼拜六","土","土曜日","SAT","Saturday"),
	/**
	 * 星期日
	 */
	Sunday(7,"日","周日","星期日","礼拜天","日","日曜日","SUN","Sunday");
	
	private int code;
	private String nameChn_S;
    private String nameChn_1;
    private String nameChn_2;
    private String nameChn_3;
    private String nameJpn_S;
    private String nameJpn;
    private String nameEng_S;
    private String nameEng;

    WeekDay(int code, 
    		String nameChn_S, 
    		String nameChn_1, 
    		String nameChn_2, 
    		String nameChn_3, 
    		String nameJpn_S, 
    		String nameJpn, 
    		String nameEng_S, 
    		String nameEng) {
        this.code = code;
        this.nameChn_S = nameChn_S;
        this.nameChn_1 = nameChn_1;
        this.nameChn_2 = nameChn_2;
        this.nameChn_3 = nameChn_3;
        this.nameJpn_S = nameJpn_S;
        this.nameJpn = nameJpn;
        this.nameEng_S = nameEng_S;
        this.nameEng = nameEng;
    }

    public int getCode() {
        return code;
    }

    public String getNameChn_S() {
		return nameChn_S;
	}

	public String getNameChn_1() {
		return nameChn_1;
	}

	public String getNameChn_2() {
		return nameChn_2;
	}

	public String getNameChn_3() {
		return nameChn_3;
	}

	public String getNameJpn_S() {
		return nameJpn_S;
	}

	public String getNameJpn() {
		return nameJpn;
	}

	public String getNameEng_S() {
		return nameEng_S;
	}

	public String getNameEng() {
		return nameEng;
	}

    public static WeekDay weekDayOf(int code){
        for (WeekDay weekDay:values()){
            if(weekDay.getCode()==code){
                return weekDay;
            }
        }
        return null;
    }
}

