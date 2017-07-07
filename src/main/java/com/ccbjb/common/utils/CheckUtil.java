package com.ccbjb.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ccbjb.common.exception.TKMBaseException;
import com.ccbjb.common.exception.ValidateException;

/**
 * 属性检查功能类
 * <p>
 * 提供各种属性的内容检查功能
 * @author CJB-ChnGroup
 * @version v1.0 
 */
public class CheckUtil {
	/**
	 * 所比较的字符串长度不同
	 */
	private static final String MESSAGE_ID_ERROR_UTILITY_CHECKUTIL = "各参数指定的字符串长度不同。";

	/**
	 * 指示指定的字符串是 null 还是 "" 字符串。
	 * 
	 * @param text
	 *            要测试的字符串
	 * @return 如果 text 参数为 null 或空字符 ("")，则为 true；否则为 false。
	 * @since 1.0
	 */
	public static boolean isNullOrEmpty(String text) {
		return text == null || "".equals(text);
	}

	/**
	 * 指示指定的字符串是 null、""还是仅由全半角空白字符组成。
	 * 
	 * @param text
	 *            要测试的字符串
	 * @return 如果 text 参数为 null 或 空字符 ("")，或者如果 value 仅由全半角空白字符组成，则为 true。
	 * @since 1.0
	 */
	public static boolean isAllSpace(String text) {
		return CheckUtil.isAllPadded(text, ' ') || CheckUtil.isAllPadded(text, '　');
	}
	
	/**
	 * 指示指定的字符串是否仅包含半角0或者为null、空字符 ("")。
	 * 
	 * @param text
	 *            要测试的字符串
	 * @return 如果 text 参数仅包含半角0或者为null、空字符 ("")，则为 true。
	 * @since 1.0
	 */
	public static boolean isAllZero(String text) {
		return CheckUtil.isAllPadded(text, '0');
	}



	/**
	 * 指示指定的字符串是否在要求的字符串范围之内。<br>
	 * 注意：调用此函数前请确认所有参数不为null且长度一致，否则将引发异常。
	 * 
	 * @param text
	 *            要测试的字符串
	 * @param start
	 *            字符串的范围最小值
	 * @param end
	 *            字符串的范围最大值
	 * @return 如果 text在参数start、end 之间，则返回true，反之返回false。
	 * @since 1.0
	 * @throws ValidateException
	 *             如果参数text、start、end的长度不一样 characters outside the bounds of the
	 *             {@code value} array
	 * @throws NullPointerException
	 *             如果参数text、start、end有一个为null
	 */
	public static boolean isBetween(String text, String start, String end) throws ValidateException {
		if (text.length() != start.length() || text.length() != end.length()) {
			throw new ValidateException(MESSAGE_ID_ERROR_UTILITY_CHECKUTIL);
		}
		return text.compareTo(start) >= 0 && text.compareTo(end) <= 0;
	}

	/**
	 * 指示指定的数值（double）是否在要求的范围之内。
	 * 
	 * @param myValue
	 *            要测试的数值（double）
	 * @param start
	 *            范围最小值
	 * @param end
	 *            范围最大值
	 * @return 如果 myValue在参数start、end 之间，则返回true，反之返回false。
	 * @since 1.0
	 */
	public static boolean isBetween(double myValue, double start, double end) {
		return myValue >= start && myValue <= end;
	}

	/**
	 * 指示指定的数值（int）是否在要求的范围之内。
	 * 
	 * @param myValue
	 *            要测试的数值（int）
	 * @param start
	 *            范围最小值
	 * @param end
	 *            范围最大值
	 * @return 如果 myValue在参数start、end 之间，则返回true，反之返回false。
	 * @since 1.0
	 */
	public static boolean isBetween(int myValue, int start, int end) {
		return CheckUtil.isBetween((double) myValue, (double) start, (double) end);
	}

	/**
	 * 指示指定的数值（long）是否在要求的范围之内。
	 * 
	 * @param myValue
	 *            要测试的数值（int）
	 * @param start
	 *            范围最小值
	 * @param end
	 *            范围最大值
	 * @return 如果 myValue在参数start、end 之间，则返回true，反之返回false。
	 * @since 1.0
	 */
	public static boolean isBetween(long myValue, long start, long end) {
		return CheckUtil.isBetween((double) myValue, (double) start, (double) end);
	}

	/**
	 * 指示指定的字符串是否是纯半角数字。
	 * 
	 * @param text
	 *            要测试的字符串
	 * @return 如参数text是纯半角数字或者为空(null，"")，则返回true，反之返回false。
	 * @since 1.0
	 */
	public static boolean isNumeric(String text) {
		return CheckUtil.isRegex(text, "^[0-9]*$");
	}

	/**
	 * 指示指定的字符串是否是带逗号(",")的纯半角数字。
	 * 
	 * @param text
	 *            要测试的字符串）
	 * @return 如参数text是是带逗号(",")的纯半角数字或者为空(null，"")，则返回true，反之返回false。
	 * @since 1.0
	 */
	public static boolean isCommaNumeric(String text) {
		if (CheckUtil.isNullOrEmpty(text)) {
			return true;
		}
		String noComma = text.replaceAll(text, "");
		return CheckUtil.isNumeric(noComma);
	}

	/**
	 * 指示指定的字符串是否仅包含半角英文字母。
	 * 
	 * @param text
	 *            要测试的字符串）
	 * @return 如参数text仅包含英文字母，或者为空(null，"")，则返回true，反之返回false。
	 * @since 1.0
	 */
	public static boolean isAlphabetic(String text) {
		return CheckUtil.isRegex(text, "^[a-zA-Z]*$");
	}

	/**
	 * 指示指定的字符串是否仅包含半角大写英文字母。
	 * 
	 * @param text
	 *            要测试的字符串）
	 * @return 如参数text仅包含半角大写英文字母，或者为空(null，"")，则返回true，反之返回false。
	 * @since 1.0
	 */
	public static boolean isUpperAlphabetic(String text) {
		return CheckUtil.isRegex(text, "^[A-Z]*$");
	}

	/**
	 * 指示指定的字符串是否仅包含半角小写英文字母。
	 * 
	 * @param text
	 *            要测试的字符串）
	 * @return 如参数text仅包含半角小写英文字母，或者为空(null，"")，则返回true，反之返回false。
	 * @since 1.0
	 */
	public static boolean isLowerAlphabetic(String text) {
		return CheckUtil.isRegex(text, "^[a-z]*$");
	}

	/**
	 * 指示指定的字符串是否仅包含半角文字(半角英文字母、数字、符号、日语片假名)。
	 * 
	 * @param text
	 *            要测试的字符串）
	 * @return 
	 *         如参数text仅包含半角文字(半角英文字母、数字、符号、日语片假名)，或者为空(null，"")，则返回true，反之返回false
	 *         。
	 * @since 1.0
	 */
	public static boolean isNarrow(String text) {
		return CheckUtil.isRegex(text, "^[\\u0021-\\u007f\\u00a5\\u203e\\uff61-\\uff9f]*$");
	}

	/**
	 * 指示指定的字符串是否仅包含半角文字(空白、日语片假名以外)。
	 * 
	 * @param text
	 *            要测试的字符串）
	 * @return 如参数text仅包含半角文字(空白、日语片假名以外)，或者为空(null，"")，则返回true，反之返回false。
	 * @since 1.0
	 */
	public static boolean isAscii(String text) {
		return CheckUtil.isRegex(text, "^[\\u0021-\\u007f\\u00a5\\u203e]*$");
	}

	/**
	 * 指示指定的字符串是否仅包含半角日语片假名
	 * 
	 * @param text
	 *            要测试的字符串）
	 * @return 如参数text仅包含半角日语片假名，或者为空(null，"")，则返回true，反之返回false。
	 * @since 1.0
	 */
	public static boolean isNarrowKatakana(String text) {
		return CheckUtil.isRegex(text, "^[\\uff61-\\uff9f]*$");
	}

	/**
	 * 指示指定的字符串是否仅包含全角日语片假名
	 * 
	 * @param text
	 *            要测试的字符串）
	 * @return 如参数text仅包含全角日语片假名，或者为空(null，"")，则返回true，反之返回false。
	 * @since 1.0
	 */
	public static boolean isWideKatakana(String text) {
		return CheckUtil.isRegex(text, "^[\\u309b-\\u30fe]*$");
	}

	/**
	 * 指示指定的字符串是否仅包含日语平假名
	 * 
	 * @param text
	 *            要测试的字符串）
	 * @return 如参数text仅包含日语平假名，或者为空(null，"")，则返回true，反之返回false。
	 * @since 1.0
	 */
	public static boolean isHiragana(String text) {
		return CheckUtil.isRegex(text, "^[\\u3041-\\u3094\\u30fc]*$");
	}

	/**
	 * 指示指定的字符串是否仅包含全角字符
	 * 
	 * @param text
	 *            要测试的字符串）
	 * @return 如参数text仅包含全角字符，或者为空(null，"")，则返回true，反之返回false。
	 * @since 1.0
	 */
	public static boolean isWide(String text) {
		return CheckUtil.isRegex(text, "^[\\u0100-\\u203d\\u203f-\\uff60\\uffa0-\\uffff\\u00a7\\u00a8\\u00b0\\u00b1\\u00b4\\u00b6\\u00d7\\u00f7]*$");
	}

	/**
	 * 指示指定的字符串的长度为指定值
	 * 
	 * @param text
	 *            要测试的字符串
	 * @param length
	 *            长度值
	 * @return 如参数text的长度等于参数length值，则返回true，反之返回false。
	 * @since 1.0
	 */
	public static boolean agreeLength(String text, int length) {
		if (text == null) {
			return false;
		}
		// char[] chars = text.toCharArray();
		// return chars.length == length;
		return text.length() == length;
	}

	/**
	 * 指示指定的字符串是否是数值(正常记数法以及会计记数法（9,999,999）)
	 * 
	 * @param text
	 *            要测试的字符串
	 * @return 如参数text是是数值，则返回true，反之返回false。
	 * @since 1.0
	 */
	public static boolean isNumberFormat(String text) {
		return CheckUtil.isNullOrEmpty(text) || CheckUtil.isRegex(text, "^[+-]?([0-9]+|[0-9]{1,3}(,[0-9]{3})*)(\\.[0-9]+)?$");
	}

	/**
	 * 指示指定的字符串是否是合法的日期格式(yyyyMMdd、yyyy/MM/dd、yyyy-MM-dd)
	 * 
	 * @param text
	 *            要测试的字符串
	 * @return 如参数text是是数值，则返回true，反之返回false。
	 * @since 1.0
	 */
	public static boolean isDateFormat(String text) {
		boolean result = true;
		if (CheckUtil.isNullOrEmpty(text)) {
			return true;
		}
		// char[] separators = "/".toCharArray();
		if (CheckUtil.isRegex(text, ".*[/-].*")) {
			String[] dateElements1 = text.split("/");
			String[] dateElements2 = text.split("-");
			if (dateElements1.length != 3) {
				result = false;
			} else if (dateElements2.length != 3) {
				result = false;
			} else if (dateElements1[0].length() != 4 || dateElements1[1].length() != 2 || dateElements1[2].length() != 2) {
				result = false;
			} else if (dateElements2[0].length() != 4 || dateElements2[1].length() != 2 || dateElements2[2].length() != 2) {
				result = false;
			} else {
				text = text.replaceAll("[/-]", "");
			}
		} else if (text.length() != 8) {
			result = false;
		}

		if (result) {
			try {
				DateUtil.getDateTime(text);
				result = true;
			} catch (TKMBaseException ex) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * 指示指定的字符串是否是合法的日期时间格式<br>
	 * (yyyyMMddHHmmss、yyyy/MM/dd HH:mm:ss、yyyy-MM-dd HH:mm:ss)
	 * @param text
	 *            要测试的字符串
	 * @return 如参数text是合法的日期时间格式，则返回true，反之返回false。
	 * @since 1.0
	 */
	public static boolean isDateTimeFormat(String text) {
		boolean result = true;
		if (CheckUtil.isAllSpace(text)) {
			return false;
		}
		String dateTimeString = "";
		String dateTimeFormat = "yyyyMMddHHmmss";
		char[] SeparatedDateTimeFormat1 = "yyyy/MM/dd HH:mm:ss".toCharArray();
		char[] SeparatedDateTimeFormat2 = "yyyy-MM-dd HH:mm:ss".toCharArray();
		if (text.length() == dateTimeFormat.length()) {
			if (CheckUtil.isNumeric(text)) {
				dateTimeString = text;
			} else {
				result = false;
			}
		} else if (text.length() == SeparatedDateTimeFormat1.length) {
			char[] dateTimeElements = text.toCharArray();
			for (int i = 0; i < dateTimeElements.length; i++) {
				if (CheckUtil.isNumeric(String.valueOf(dateTimeElements[1]))) {
					dateTimeString += String.valueOf(dateTimeElements[i]);
				} else if (dateTimeElements[i] != SeparatedDateTimeFormat1[i] && dateTimeElements[i] != SeparatedDateTimeFormat2[i]) {
					result = false;
				} else if (CheckUtil.isAlphabetic(String.valueOf(dateTimeElements[i]))) {
					result = false;
				}
			}
		} else {
			result = false;
		}

		if (result) {
			try {
				DateUtil.getDateTime(dateTimeString);
				result = true;
			} catch (TKMBaseException ex) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * 指示指定的字符串是否是合法的时间格式<br>
	 * (HH:mm:ss、HH:mm、HH)
	 * @param text
	 *            要测试的字符串
	 * @return 如参数text是合法的时间格式，则返回true，反之返回false。
	 * @since 1.0
	 */
	public static boolean isTimeFormat(String text) {
		boolean result = true;
		if (CheckUtil.isNullOrEmpty(text)) {
			return true;
		}
		String timeStringHMS = "";
		String timeStringHM = "";
		String timeStringH = "";
		char[] SeparatedTimeFormatHMS = "HH:mm:ss".toCharArray();
		char[] SeparatedTimeFormatHM = "HH:mm".toCharArray();
		char[] SeparatedTimeFormatH = "HH".toCharArray();

		if (text.length() == SeparatedTimeFormatHMS.length) {
			char[] timeElementsHMS = text.toCharArray();
			for (int i = 0; i < timeElementsHMS.length; i++) {
				if (CheckUtil.isNumeric(String.valueOf(timeElementsHMS[i]))) {
					timeStringHMS += String.valueOf(timeElementsHMS[i]);
				} else if (timeElementsHMS[i] != SeparatedTimeFormatHMS[i]) {
					result = false;
				}
			}
			if (result) {
				if (timeStringHMS.length() != 6) {
					return false;
				}
				if (timeStringHMS.substring(0, 1).equals("2")) {
					if (!CheckUtil.isRegex(timeStringHMS.substring(0, 2), "^[2][0-3]$")) {
						return false;
					}
				} else if (timeStringHMS.substring(0, 1).compareTo("2") > 0) {
					return false;
				}
				if (!CheckUtil.isRegex(timeStringHMS.substring(2, 4), "^[0-5][0-9]$")) {
					return false;
				}
				if (!CheckUtil.isRegex(timeStringHMS.substring(4, 6), "^[0-5][0-9]$")) {
					return false;
				}
			}
		} else if (text.length() == SeparatedTimeFormatHM.length) {
			char[] timeElementsHM = text.toCharArray();
			for (int j = 0; j < timeElementsHM.length; j++) {
				if (CheckUtil.isNumeric(String.valueOf(timeElementsHM[j]))) {
					timeStringHM += String.valueOf(timeElementsHM[j]);
				} else if (timeElementsHM[j] != SeparatedTimeFormatHM[j]) {
					result = false;
				}
			}
			if (result) {
				if (timeStringHM.length() != 4) {
					return false;
				}
				if (timeStringHM.substring(0, 1).equals("2")) {
					if (!CheckUtil.isRegex(timeStringHM.substring(0, 2), "^[2][0-3]$")) {
						return false;
					}
				} else if (timeStringHM.substring(0, 1).compareTo("2") > 0) {
					return false;
				}
				if (!CheckUtil.isRegex(timeStringHM.substring(2, 4), "^[0-5][0-9]$")) {
					return false;
				}
			}
		} else if (text.length() == SeparatedTimeFormatH.length) {
			char[] timeElementsH = text.toCharArray();
			for (int k = 0; k < timeElementsH.length; k++) {
				if (CheckUtil.isNumeric(String.valueOf(timeElementsH[k]))) {
					timeStringH += String.valueOf(timeElementsH[k]);
				} else if (timeElementsH[k] != SeparatedTimeFormatH[k]) {
					result = false;
				}
			}
			if (result) {
				if (timeStringH.length() != 2) {
					return false;
				}
				if (timeStringH.substring(0, 1).equals("2")) {
					if (!CheckUtil.isRegex(timeStringH.substring(0, 2), "^[2][0-3]$")) {
						return false;
					}
				} else if (timeStringH.substring(0, 1).compareTo("2") > 0) {
					return false;
				}
			}
		} else {
			result = false;
		}

		return result;
	}

	/**
	 * 指示指定的字符串是否是有效的电话号码格式<br>
	 * @param text
	 *            要测试的字符串
	 * @return 如参数text是是有效的电话号码格式，则返回true，反之返回false。
	 * @since 1.0
	 */
	public static boolean isTelNo(String text) {
		return CheckUtil.isRegex(text,
				"(\\d{11}|\\d{3}-\\d{4}-\\d{4})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$");

	}

	/**
	 * 指示指定的字符串是否不包含任何全角或半角空格或Tab。
	 * 
	 * @param text
	 *            要测试的字符串
	 * @return 如果参数 text 不含全角或半角空格或Tab，则返回true，反之返回false。
	 * @since 1.0
	 */
	public static boolean isNoSpace(String text) {
		return CheckUtil.isNullOrEmpty(text) || text.indexOf(' ') < 0 || text.indexOf('　') < 0 || text.indexOf('	') < 0;
	}

	/**
	 * 指示指定的字符串是否满足指定的正则表达式。
	 * 
	 * @param text
	 *            要测试的字符串
	 * @param regString
	 *            正则表达式
	 * @return 如果 text在参数的内容匹配正则表达式或者为空(null，"")，则返回true，反之返回false。
	 * @since 1.0
	 */
	protected static boolean isRegex(String text, String regString) {
		if (CheckUtil.isNullOrEmpty(text)) {
			return true;
		}
		Pattern pattern = Pattern.compile(regString);
		Matcher matcher = pattern.matcher(text);
		return matcher.matches();
	}

	/**
	 * 判断实体bean 是否为空，如果所有属性为空返回true ,只要有一个属性非NULL或非空则返回 false
	 * 实体bean上没有注解或者有@CheckTitle(validate=true)的是需要验证该字段是否为空
	 * @CheckTitle(validate=false)是不需要验证其字段是否为空
	 * @param obj
	 * @return
	 * @date 2016年9月26日 下午1:49:48
	 */
	public static boolean isNull(Object obj) {
		boolean flag = true;
		List<Field> fieldList = new ArrayList<Field>();
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			Field[] fields = superClass.getDeclaredFields();
			if (fields != null) {
				for (Field field : fields) {
					if (field.getAnnotation(CheckTitle.class) == null || field.getAnnotation(CheckTitle.class).validate() == true) {
						fieldList.add(field);
					}
				}
			}
		}
		for (Field field : fieldList) {
			field.setAccessible(true);
			try {
				Object value = field.get(obj);
				if (value != null && !"".equals(value)) {
					flag = false;
					return flag;
				}
			} catch (Exception e) {
				flag = false;
			}
		}
		return flag;
	}
	
	/**
	 * 指示指定的字符串是否仅包含指定的 Unicode 填充字符
	 * 
	 * @param text
	 *            要测试的字符串
	 * @param pad
	 *            指定的 Unicode 填充字符
	 * @return 如果 text 参数仅包含指定的 Unicode 填充字符，则为 true，反之为false。
	 * @since 1.0
	 */
	private static boolean isAllPadded(String text, char pad) {
		if (CheckUtil.isNullOrEmpty(text)) {
			return true;
		}
		char[] array = text.toCharArray();
		for (int i = 0; i < array.length; i++) {
			char c = array[i];
			if (c != pad) {
				return false;
			}
		}
		return true;
	}
}

