/** * DateUtil.java 
* Created on 2009-7-8 ����04:50:59 
*/

package utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * 
 * <p>Project: crm</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2013 Wilmar Technology (Shanghai) Co., Ltd.</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:sunsuling@wcs-global.com">Sun Suling</a>
 */
public class DateUtil {
    /**
     * String日期转化为Date
     * @param timeStr
     * @param df
     * @return
     */
    public static Date string2Date(String timeStr, String formatStr) {
        try {
            if("" == timeStr || timeStr == null){
                return null;
            }
            SimpleDateFormat df = new SimpleDateFormat(formatStr);
            return df.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * date 转换成String
     * @param date
     * @return
     */
    public static String Date2String(Date date, String formatStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(date);
    }
	

	/**
	 * String日期转化为TimeStamp
	 * @param timeStr
	 * @param df
	 * @return
	 */
	public static Timestamp string2TimeStamp(String timeStr, String formatStr) {
		DateFormat format = new SimpleDateFormat(formatStr);
		format.setLenient(false);
		if(timeStr == null || "".equals(timeStr)){
			return null;
		}
		Timestamp ts = null;
		try {
			ts = new Timestamp(format.parse(timeStr).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ts;
	}

   

    /**
     *  timestamp转String 
     * @param arg
     * @return
     */
    public static String timestamp2String1(Timestamp arg) {
        if (arg == null) { return null; }
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(arg);
    }

    /**
     *  timestamp转String 
     * @param arg
     * @return
     */
    public static String timestamp2String(Timestamp arg) {
        if (arg == null) { return null; }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(arg);
    }
    /**
     *  timestamp转String 
     * @param arg
     * @return
     */
    public static String timestamp2String9(Timestamp arg) {
        if (arg == null) { return null; }
        SimpleDateFormat formatter = new SimpleDateFormat("yy-MM");
        return formatter.format(arg);
    }
    
    /**
     *  timestamp转String 
     * @param arg
     * @return
     */
    public static String timestamp2String18(Timestamp arg) {
        if (arg == null) { return null; }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月");
        return formatter.format(arg);
    }
    
    /**
     *  timestamp转String 
     * @param arg
     * @return
     */
    public static String timestamp2String19(Timestamp arg) {
        if (arg == null) { return null; }
        SimpleDateFormat formatter = new SimpleDateFormat("MM月");
        return formatter.format(arg);
    }
    
    /**
     *  timestamp转String 
     * @param arg
     * @return
     */
    public static String timestamp2String2(Timestamp arg) {
        if (arg == null) { return null; }
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.format(arg);
    }
    
    /**
     *  timestamp转String(yyyy-MM-dd HH:mm) 
     * @param arg
     * @return
     */
    public static String timestamp2String3(Timestamp arg) {
        if (arg == null) { return null; }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatter.format(arg);
    }
    
    /**
     *  timestamp转String 
     * @param arg
     * @return
     */
    public static String timestamp2String4(Timestamp arg) {
        if (arg == null) { return null; }
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
        return formatter.format(arg);
    }
    
    /**
     *  timestamp转String(MM-dd HH:mm)
     * @param arg
     * @return
     */
    public static String timestamp2String10(Timestamp arg) {
        if (arg == null) { return null; }
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");
        return formatter.format(arg);
    }
    
    
    /**
     *  timestamp转String 
     * @param arg
     * @return
     */
    public static String timestamp2String5(Timestamp arg) {
        if (arg == null) { return null; }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        return formatter.format(arg);
    }
    
    /**
     *  timestamp转String 
     * @param arg
     * @return
     */
    public static String timestamp2String6(Timestamp arg) {
        if (arg == null) { return null; }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(arg);
    }

    /**
     *  timestamp转String  结束时间 23:59:59
     * @param arg
     * @return
     */
    public static String timestamp2String6to(Timestamp arg) {
        if (arg == null) { return null; }
        arg.setHours(23);
        arg.setSeconds(59);
        arg.setMinutes(59);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(arg);
    }
   
    
    /**
     *  timestamp转String - HH:mm:ss
     * @param arg
     * @return
     */
    public static String timestamp2String7(Timestamp arg) {
        if (arg == null) { return null; }
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(arg);
    }

	public static Timestamp string2TimestampAuto(String arg) {
		if (arg == null) {
			return null;
		}

		try {
			int length = arg.length();
			if (length == 5) {
				return Timestamp.valueOf("1901-01-01 " + arg + ":00");
			} else if (length == 10) {
				return Timestamp.valueOf(arg + " 00:00:00");
			} else if (length == 13) {
				return Timestamp.valueOf(arg + ":00:00");
			} else if (length == 16) {
				return Timestamp.valueOf(arg + ":00");
			} else if (length >= 19) {
				return Timestamp.valueOf(arg);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	 public static String timestamp2String8(Timestamp arg) {
	        if (arg == null) { return null; }
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	        return formatter.format(arg);
	    }
    /**
     * String 转timestamp
     * @param arg
     * @return
     */
    public static Timestamp string2Timestamp(String arg) {
        if (arg == null) { return null; }
        try {
            return Timestamp.valueOf("1901-01-01 " + arg + ":00");
        } catch (RuntimeException e) {
            return null;
        }
    }

    /**
     * <p>Description: This method is to convert a
     * string(like yyyy-MM-dd) to a timestamp.</p>
     * @param arg
     * @return
     */
    public static Timestamp string2Timestamp2(String arg) {
        if (arg == null) return null;
        try {
            return Timestamp.valueOf(arg + " 00:00:00");
        } catch (RuntimeException e) {
            return null;
        }
    }

    /**
     * String 转timestamp
     * @param arg
     * @return
     */
    public static Timestamp string2Timestamp3(String arg) {
        if (arg == null) { return null; }
        try {
            return Timestamp.valueOf(arg + ":00");
        } catch (RuntimeException e) {
            return null;
        }
    }
    
    public static Timestamp string2Timestamp4(String arg) {
        if (arg == null) return null;
        try {
            return Timestamp.valueOf(arg + " 23:59:59");
        } catch (RuntimeException e) {
            return null;
        }
    }
    
    /**
     * 将timestamp转换成date
     * @param tt
     * @return
     */
    public static Date timestampToDate(Timestamp tt) {
        return new Date(tt.getTime());
    }

	/**
	 * <p>Description: 依据出生日期，计算年龄 </p>
	 * @param birthDate
	 * @return
	 */
	public static String getAgeDisplay(Timestamp birthDate) {
		if (birthDate == null) {
			return null;
		}
		Timestamp now = new Timestamp(System.currentTimeMillis());
		return getAgeDisplay(birthDate, now);
	}

	/**
	 * <p>Description: 依据出生日期，计算年龄 </p>
	 * @param birthDate
	 * @return
	 */
	public static String getAgeDisplay(Timestamp birthDate, Timestamp endDate) {
		if (birthDate == null) {
			return null;
		}
		if (endDate == null) {
			endDate = new Timestamp(System.currentTimeMillis());
		}

		String YEAR = "岁";
		String MONTH = "月";
		String DAY = "天";

		Calendar birthCalendar = Calendar.getInstance();
		birthCalendar.setTime(birthDate);
		int birthYear = birthCalendar.get(Calendar.YEAR);
		int birthMonth = birthCalendar.get(Calendar.MONTH) + 1;
		int birthDay = birthCalendar.get(Calendar.DAY_OF_MONTH);

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);
		int endYear = endCalendar.get(Calendar.YEAR);
		int endMonth = endCalendar.get(Calendar.MONTH) + 1;
		int endDay = endCalendar.get(Calendar.DAY_OF_MONTH);

		int days = Long.valueOf(
				(endCalendar.getTimeInMillis() - birthCalendar.getTimeInMillis()) / (24 * 60 * 60 * 1000)).intValue();
		if (days > 365) {
			if (endMonth < birthMonth) {
				return (endYear - birthYear - 1) + YEAR;
			} else if (endMonth == birthMonth && endDay < birthDay) {
				return (endYear - birthYear - 1) + YEAR;
			} else {
				return (endYear - birthYear) + YEAR;
			}

		} else if (days > 30) {
			if (days % 30 == 0) {
				return (days / 30) + MONTH;
			} else {
				return (days / 30 + 1) + MONTH;
			}

		} else {
			return days + DAY;
		}
	}
	
	/**
	 * 获得当前时间
	 * */
	public static Timestamp getCurrent(){
		return new Timestamp(System.currentTimeMillis());
	}
	
	/**
	 * 两个时间拼接(t1的日期，t2的时间)
	 * @param t1,t2
	 * */
	public static String spliceTimestamp(Timestamp t1, Timestamp t2) {
		if(t1 == null || t2 == null){
			return null;
		}
		String date = timestamp2String(t1);
		String time = timestamp2String1(t2);
		String dateTime = date + " " + time;
		return dateTime;
	}
	
	/**
	 * 两个时间拼接(t1的日期，t2的时间[String])
	 * @param t1,t2
	 * */
	public static String spliceTimestamp2(Timestamp t1, String t2) {
		if(t1 == null || ValueHelper.isEmpty(t2)){
			return null;
		}
		String date = timestamp2String(t1);
		String dateTime = date + " " + t2;
		return dateTime;
	}
	
	/**
	 * date转换为 Timestamp
	 * */
	public static Timestamp date2Timestamp(Date date) {
		if (date == null) {
			return null;
		}
		Timestamp ts = new Timestamp(date.getTime());
		return ts;
	}
	
	/** 
	* 根据日期获得星期 
	* @param date 
	*/
	public static String getWeekOfDate(Date date) {
		if (date == null) {
			return null;
		}
		String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		return weekDaysCode[intWeek];
	}
	
	/** 
	 * 根据日期获得所在周的日期  
	 * @param mdate 
	 * @return 
	 */
	public static List<Date> dateGetWeek(Date date) {
		if (date == null) {
			return null;
		}
		int weekday = Integer.parseInt(getWeekOfDate(date));
		if (weekday == 0) {
			weekday = 7;
		}
		Date fdate;
		List<Date> list = new ArrayList<Date>();
		Long fTime = date.getTime() - weekday * 24 * 3600000;
		for (int i = 1; i <= 7; i++) {
			fdate = new Date();
			fdate.setTime(fTime + (long) (i * 24 * 3600000));
			list.add(i - 1, fdate);
		}
		return list;
	}
	
	/** 
	 * 判断两个日期是否在同一周(比较所在周的第一天是否相同)
	 * @param mdate 
	 * @return 
	 */
	public static boolean compare2dateIn1week(Date d1, Date d2) {
		if (d1 == null || d2 == null) {
			return false;
		}
		d1 = getFistWeekDay(d1) == null ? null : getFistWeekDay(d1);
		d2 = getFistWeekDay(d2) == null ? null : getFistWeekDay(d2);
		if (d1 != null && d2 != null) {
			if (DateUtil.Date2String(d1, "yyyy-MM-dd").equals(DateUtil.Date2String(d2, "yyyy-MM-dd"))) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/** 
	 * 根据日期获得下一天  
	 * @param mdate 
	 * @return 
	 */
	public static Timestamp getNextDay(Date date) {
		if (date == null) {
			return null;
		}
		Long fTime = date.getTime() + (long) 1 * 24 * 3600000;
		return new Timestamp(fTime);
	}
	
	/** 
     * 根据日期获得前一天  
     * @param mdate 
     * @return 
     */
	public static Timestamp getPreDay(Date date) {
        if (date == null) {
            return null;
        }
        Long fTime = date.getTime() - (long) 1 * 24 * 3600000;
        return new Timestamp(fTime);
    }
	
	/** 
	 * 根据时间加法minute按分钟计算，不含日期
	 * @param minute 
	 * @return 
	 */
	public static String datePlus(Date date,int minute) {
		if (date == null) {
			return "";
		}
		Long fTime = date.getTime() + (long) 1 * minute * 60000;
		return timestamp2String1(new Timestamp(fTime));
	}
	
	/** 
	 * 根据日期获得一个月的第一天
	 * @param mdate 
	 * @return 
	 */
	public static Timestamp getMonthFirstDay(Timestamp now) {
		if (now == null) {
			return null;
		}
		Calendar monthCalendar = Calendar.getInstance();
		monthCalendar.setTime(now);
		int year = monthCalendar.get(Calendar.YEAR);
		int month = monthCalendar.get(Calendar.MONTH) + 1;
		String monthFirstDay = year +"-" + month +"-1";
		return string2TimeStamp(monthFirstDay,"yyyy-M-d");
	}
	
	/** 
	 * 根据日期获得下一个月的第-天
	 * @param mdate 
	 * @return 
	 */
	public static Timestamp getNextMonthFirstDay(Timestamp now) {
		if (now == null) {
			return null;
		}
		Calendar monthCalendar = Calendar.getInstance();
		monthCalendar.setTime(now);
		int year = monthCalendar.get(Calendar.YEAR);
		int month = monthCalendar.get(Calendar.MONTH) + 1;
		if(month == 12){
			year ++;
			month = 1;
		}else{
			month ++;
		}
		String monthFirstDay = year +"-" + month +"-1";
		return string2TimeStamp(monthFirstDay,"yyyy-M-d");
	}
	
	/** 
	 * 根据日期获得所在周的第一天比如2013-4-10 周三  得到2013-4-8（周一）
	 * @param mdate 
	 * @return 
	 */
	public static Timestamp getFistWeekDay(Date date) {
		if (date == null) {
			return null;
		}
		int weekday = Integer.parseInt(getWeekOfDate(date));
		if (weekday == 0) {
			weekday = 7;
		}
		Long fTime = date.getTime() - (long) (weekday - 1) * 24 * 3600000;
		return new Timestamp(fTime);
	}

	/** 
	 * 根据日期获得所在周的第一天比如2013-4-10 周三  得到2013-4-15（下周一）
	 * @param mdate 
	 * @return 
	 */
	public static Timestamp getLastWeekDay(Date date) {
		if (date == null) {
			return null;
		}
		int weekday = Integer.parseInt(getWeekOfDate(date));
		if (weekday == 0) {
			weekday = 7;
		}
		Long fTime = date.getTime() - (long) (weekday - 8) * 24 * 3600000;
		return new Timestamp(fTime);
	}

	public static Timestamp getNHour(Date date, int n) {
		if (date == null) {
			return null;
		}
		long fTime = date.getTime() + (long) n * 3600000;
		return new Timestamp(fTime);
	}

	public static Timestamp getNDay(Date date, int n) {
		if (date == null) {
			return null;
		}
		long fTime = date.getTime() + (long) n * 24 * 3600000;
		return new Timestamp(fTime);
	}

	public static Timestamp preNDay(Date date, int n) {
        if (date == null) {
            return null;
        }
        long fTime = date.getTime() - (long) n * 24 * 3600000;
        return new Timestamp(fTime);
    }
	
	public static Timestamp getZero(Timestamp datetime) {
		if (datetime == null) {
			return null;
		}
		return DateUtil.string2Timestamp2(DateUtil.timestamp2String(datetime));
	}

	/**
	 * <p>Description: 计算间隔天数（直接使用time计算） </p>
	 * @return
	 */
	public static int internal(Timestamp a, Timestamp b) {
		if (a == null || b == null) {
			return -1;
		}
		return Long.valueOf((a.getTime() - b.getTime()) / (24 * 3600000)).intValue();
	}

	/**
	 * <p>Description: 计算间隔天数（比如住院天数） </p>
	 * @return
	 */
	public static int internalDays(Timestamp a, Timestamp b) {
		if (a == null || b == null) {
			return -1;
		}

		Timestamp aOfZero = DateUtil.getZero(a);
		Timestamp bOfZero = DateUtil.getZero(b);

		int days = internal(aOfZero, bOfZero)+1;
		if (days == 0) {
			days = 1;
		}
		return days;
	}

	/**
	 * <p>Description: 以友好的方式显示时间 </p>
	 */
	public static String friendlyTime(Timestamp time, Timestamp now) {
		if (time == null || now == null) {
			return "";
		}

		Calendar timeCalendar = Calendar.getInstance();
		timeCalendar.setTime(time);

		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(now);

		int timeYear = timeCalendar.get(Calendar.YEAR);
		int timeMonth = timeCalendar.get(Calendar.MONTH) + 1;
		int timeDay = timeCalendar.get(Calendar.DAY_OF_MONTH);
		int timeWeekofMonth = timeCalendar.get(Calendar.WEEK_OF_MONTH);

		int nowYear = nowCalendar.get(Calendar.YEAR);
		int nowMonth = nowCalendar.get(Calendar.MONTH) + 1;
		int nowDay = nowCalendar.get(Calendar.DAY_OF_MONTH);
		int nowWeekofMonth = nowCalendar.get(Calendar.WEEK_OF_MONTH);

		if (timeYear == nowYear && timeMonth == nowMonth && timeDay == nowDay) {
			return "今天 " + Date2String(time, "HH:mm");
		}
		if (timeYear == nowYear && timeMonth == nowMonth && timeDay + 1 == nowDay) {
			return "昨天 " + Date2String(time, "HH:mm");
		}
		if (timeYear == nowYear && timeMonth == nowMonth && timeWeekofMonth == nowWeekofMonth) {
			return "本周 " + Date2String(time, "MM-dd HH:mm");
		}
		if (timeYear == nowYear && timeMonth == nowMonth && timeWeekofMonth != nowWeekofMonth) {
			return (nowWeekofMonth - timeWeekofMonth) + "周前 " + Date2String(time, "MM-dd HH:mm");
		}
		if ((timeYear == nowYear && timeMonth + 1 == nowMonth)
				|| (timeYear + 1 == nowYear && timeMonth - 11 == nowMonth)) {
			return "上个月 " + Date2String(time, "MM-dd HH:mm");
		}
		if (timeYear == nowYear) {
			return "更早 " + Date2String(time, "MM-dd HH:mm");
		}

		return "更早 " + Date2String(time, "yyyy-MM-dd HH:mm");			
	}
	
    /**
     *  timestamp转String (yy-MM)
     * @param arg
     * @return
     */
    public static String timestampStringYM(Timestamp arg) {
        if (arg == null) { return null; }
        SimpleDateFormat formatter = new SimpleDateFormat("yy-MM");
        return formatter.format(arg);
    }
    
    /**
     *  timestamp转String (yyyy)
     * @param arg
     * @return
     */
    public static String timestampStringY(Timestamp arg) {
        if (arg == null) { return null; }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        return formatter.format(arg);
    }
    
    /**
     *  timestamp转String (yy-q)
     * @param arg
     * @return
     */
    public static String timestampStringQ(Timestamp arg) {
        if (arg == null) { return null; }
        SimpleDateFormat formatter = new SimpleDateFormat("yy-q");
        return formatter.format(arg);
    }
    
    public static boolean isCurrentYear(Timestamp time, Timestamp now){
    	Calendar timeCalendar = Calendar.getInstance();
		timeCalendar.setTime(time);

		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(now);
		

		int timeYear = timeCalendar.get(Calendar.YEAR);
		int nowYear = nowCalendar.get(Calendar.YEAR);
		
		if (timeYear == nowYear) {
			return true;
		} else {
			return false;
		}
    }
    
    /**
     * Description:获得当前日期n个月前的日期
     * @param num
     * @return
     */
    public static Timestamp preMonthsTime(int num){
    	// 当前系统时间
    	Calendar timeCalendar = Calendar.getInstance();
		timeCalendar.setTime(new Date(System.currentTimeMillis()));
		timeCalendar.add(Calendar.MONTH,num);
		
		return new Timestamp(timeCalendar.getTimeInMillis());
    }
    
    
	public static String friendlyDate(Timestamp time, Timestamp now) {
		if (DateUtil.isCurrentYear(time, now)) {
			return DateUtil.Date2String(time, "MM-dd");
		} else {
			return DateUtil.Date2String(time, "yyyy-MM-dd");
		}
	}

	public static String friendlyDatetime(Timestamp time, Timestamp now) {
		if (DateUtil.isCurrentYear(time, now)) {
			return DateUtil.Date2String(time, "MM-dd HH:mm");
		} else {
			return DateUtil.Date2String(time, "yyyy-MM-dd HH:mm");
		}
	}
	
	/**
     *  timestamp转String 
     * @param arg
     * @return
     */
    public static String timestamp2String12(Timestamp arg) {
        if (arg == null) { return null; }
        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        return formatter.format(arg);
    }
	
	public static boolean isCurrentMonth(Timestamp time, Timestamp now){
    	Calendar timeCalendar = Calendar.getInstance();
		timeCalendar.setTime(time);

		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(now);
		

		int timeMonth = timeCalendar.get(Calendar.MONTH);
		int nowMonth = nowCalendar.get(Calendar.MONTH);
		
		if (timeMonth == nowMonth) {
			return true;
		} else {
			return false;
		}
    }
	
	/**
     * 得到n秒后的时间
     * @param date
     * @param second
     * @return
     */
    public static String getNSecond(Date date, int second) {
        if (date == null) {
            return null;
        }
        Long fTime = date.getTime() + (long) 1 * second * 1000;
        return timestamp2String4(new Timestamp(fTime));
    }
}
