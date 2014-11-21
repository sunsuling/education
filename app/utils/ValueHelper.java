/**
 * ValueHelper.java
 * Created: 2013-3-20 下午7:47:19
 */
package utils;

import com.fasterxml.jackson.databind.JsonNode;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;



public class ValueHelper {
	/**
	 * <p>Description: 是否为空 </p>
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		} else if (obj instanceof String) {
			return "".equals(((String) obj));
		}
		return false;
	}

	/**
	 * <p>Description: 是否不为空 </p>
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

	public static boolean isEmpty(List list) {
		return list == null || list.isEmpty();
	}

	public static boolean isNotEmpty(List list) {
		return !isEmpty(list);
	}

	/**
	 * <p>Description: 是否有意义 </p>
	 * @param obj
	 * @return
	 */
	public static boolean isMeaningful(Object obj) {
		if (obj == null) {
			return false;
		} else if (obj instanceof String) {
			if (obj.equals("") || obj.equals("null") || obj.equals("undefined")) {
				return false;
			}
		}
		return true;
	}

	public static String ifNullThenEmpty(Object obj) {
		return isEmpty(obj) ? "" : obj.toString();
	}

	public static String ifEmptyThenNull(Object obj) {
		return isEmpty(obj) ? null : obj.toString();
	}

	/**
	 * 是否都空或者相等
	 */
	public static boolean bothNullOrEquals(Object a, Object b) {
		return (a == null && b == null)
				|| (a != null && b != null && a.equals(b));
	}

	/**
	 * 
	 * @param y
	 * @param z
	 * @return
	 */
	 public static String int2Percent(int y,int z){
	        String baifenbi="";//接受百分比的值
	        double baiy=y*1.0;
	        double baiz=z*1.0;
	        double fen=baiy/baiz;
	        DecimalFormat df1 = new DecimalFormat("##%");    //##.00%   百分比格式，后面不足2位的用0补齐
	        baifenbi= df1.format(fen);  
	         return baifenbi;
	     }

	public static String conjunct(Collection list) {
		if (isEmpty(list)) {
			return "";
		} else {
			StringBuffer sb = new StringBuffer("");
			for (Object obj : list) {
				if (obj != null) {
					sb.append(obj.toString());
					sb.append(",");
				}
			}

			String str = sb.toString();
			if (str.length() > 0) {
				str = str.substring(0, str.length() - 1); // 去掉最后的一个,		
			}
			return str;
		}
	}

	/**
	 * <p>Description: 金额格式化 </p>
	 */
	public static String formatMoney(BigDecimal m) {
		if (m == null) {
			m = BigDecimal.ZERO;
		}

		// 精度转换，4位小数
		m = m.setScale(4, BigDecimal.ROUND_HALF_UP);

		// 截取后面无用的0，但保留2位小数
		String str = m.toString();
		if (str.contains(".")) {
			if (str.endsWith("0000")) {
				str = str.substring(0, str.length() - 2);
			} else if (str.endsWith("000")) {
				str = str.substring(0, str.length() - 2);
			} else if (str.endsWith("00")) {
				str = str.substring(0, str.length() - 2);
			} else if (str.endsWith("0")) {
				str = str.substring(0, str.length() - 1);
			}
		}

		return str;
	}

	/**
     *  订金格式化
     * @param 
     * @return
     */
    public static String formatSubscription(String str) {
        if (str == null || "".equals(str)) {
            return "0";
        }

        return formatMoney(new BigDecimal(str));
    }
	
	/**
	 * <p>Description: 金额格式化保留两位小数 </p>
	 * m : 需要换算的金额
	 */
	public static String formatMoneyScale2(BigDecimal m) {
		return formatMoneyScale2(m, null);
	}

	/**
	 * <p>Description: 金额格式化保留两位小数 </p>
	 * m : 需要换算的金额
	 * unit : 换算的单位金额 （例：10000，万元）
	 */
	public static String formatMoneyScale2(BigDecimal m,BigDecimal unit) {
		if (m == null) {
			m = BigDecimal.ZERO;
		}

		// 精度转换，2位小数
		if (unit != null) {
			m = m.divide(unit, 32, BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
		} else {
			m = m.setScale(2, BigDecimal.ROUND_HALF_UP);
		}

		String str = m.toString();
		return str;
	}
	
	/**
	 * <p>Description: 金额格式化 </p>
	 */
	public static String formatMoneyThousand(BigDecimal m) {
		BigDecimal unit = new BigDecimal("100000");// 十万元
		BigDecimal unitWan = new BigDecimal("10000");// 万元
		String str = "";
		BigDecimal temp = m;
		if (m == null) {
			m = BigDecimal.ZERO;
		}
		
		if (m != null && m.compareTo(unit) >= 0) {
			m = m.divide(unitWan, 32, BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
		} else {
			m = m.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		
		DecimalFormat df1 = new DecimalFormat(",##0.00");
		str = df1.format(m);

		if (temp != null && temp.compareTo(unit) >= 0) {
			str = str + "万元";
		} else {
			str = str + "元";
		}
		
		return str;
	}

	/**
	 * <p>Description: 数量格式化 </p>
	 */
	public static String formatQty(BigDecimal m) {
		if (m == null) {
			m = BigDecimal.ZERO;
		}

		// 精度转换，4位小数
		m = m.setScale(4, BigDecimal.ROUND_HALF_UP);

		// 截取后面无用的0
		String str = m.toString();
		if (str.contains(".")) {
			if (str.endsWith("0000")) {
				str = str.substring(0, str.length() - 5);
			} else if (str.endsWith("000")) {
				str = str.substring(0, str.length() - 3);
			} else if (str.endsWith("00")) {
				str = str.substring(0, str.length() - 2);
			} else if (str.endsWith("0")) {
				str = str.substring(0, str.length() - 1);
			}
		}

		return str;
	}

	/**
	 * <p>Description: 折扣 </p>
	 */
	public static String discount(BigDecimal original, BigDecimal after) {
		if (after == null || BigDecimal.ZERO.equals(after)) {
			return "";
		}
		if (original == null || original.equals(after)) {
			return "";
		}
		if (after.compareTo(original) >= 0) { // 现价原价大或等，则没打折扣
			return "";
		}

		BigDecimal discount = after.divide(original, 2,
				BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(10)); // 2位小数

		if (discount.compareTo(BigDecimal.ZERO) == 0) {
			return "";
		}

		// 截取后面无用的0
		String str = discount.toString();
		if (str.contains(".")) {
			if (str.endsWith("00")) {
				str = str.substring(0, str.length() - 3);
			} else if (str.endsWith("0")) {
				str = str.substring(0, str.length() - 1);
			}
		}

		str += "折";

		return str;
	}

	/**
	 * <p>Description: 得到Json上特定节点的值 </p>
	 */
	public static String textValue(JsonNode node, String key) {
		if (node == null) {
			return null;
		}
		JsonNode keyNode = node.get(key);
		if (keyNode != null) {
			String value = keyNode.asText();
			return ValueHelper.ifEmptyThenNull(value);
		}
		return null;
	}
	
	/**
	 * <p>Description: 折扣 </p>
	 */
	public static String v13discount(BigDecimal original, BigDecimal after) {
		if (after == null || BigDecimal.ZERO.equals(after)) {
			return null;
		}
		if (original == null || BigDecimal.ZERO.equals(original)) {
			return null;
		}
		if (after.compareTo(original) >= 0) { // 现价原价大或等，则没打折扣
			return null;
		}

		BigDecimal discount = after.divide(original, 2,
				BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(10)); // 2位小数

		if (discount.compareTo(BigDecimal.ZERO) == 0) {
			return null;
		}

		// 截取后面无用的0
		String str = discount.toString();
		if (str.contains(".")) {
			if (str.endsWith("00")) {
				str = str.substring(0, str.length() - 3);
			} else if (str.endsWith("0")) {
				str = str.substring(0, str.length() - 1);
			}
		}

		str += "折";

		return str;
	}

	/**
	 * 两个数值相加
	 */
	public static BigDecimal add(BigDecimal a, BigDecimal b) {
		if (a == null) {
			return b;
		} else if (b == null) {
			return a;
		} else {
			return a.add(b);
		}
	}

	public static String txt2Html(String txt) {
		if (ValueHelper.isEmpty(txt)) {
			return null;
		}

		txt = txt.replaceAll("\\r\\n", "<br>");
		txt = txt.replaceAll("\\n", "<br>");
		txt = txt.replaceAll("\\s", "&nbsp;");
		return txt;
	}
	
	/**
	 * 中文补空格（半角空格）
	 * str：需要处理的字符串
	 * num：目标中文字数
	 */
	public static String fillSpace(String str,int num) {
		if (str == null || num == 0) {
			return "";
		}
		
		// 字符串长度
		int length = str.length()*2;
		// 需要补的空格数
		int spaceNum = num*2-length;
		
		int leftSpaceNum = (num*2-length)/2;
		int rightSpaceNum = spaceNum - leftSpaceNum;
		
		String leftSpace = "";
		String rightSpace = "";
		
		for (int i = 0; i < leftSpaceNum; i++) {
			leftSpace = leftSpace + " ";
		}
		
		for (int i = 0; i < rightSpaceNum; i++) {
			rightSpace = rightSpace + " ";
		}
		
		StringBuffer sb = new StringBuffer("");
		sb.append(leftSpace);
		sb.append(str);
		sb.append(rightSpace);
		
		return sb.toString();
	}
	
	/**
	 * 去掉参数中的空字段
	 * @param params
	 */
	public static void resetParams(Map<String, String> params) {
		if (params == null) {
			return;
		}

		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			String value = params.get(key);
			if (ValueHelper.isEmpty(value)) {
				params.put(key, null);
			}
		}
	}
}
