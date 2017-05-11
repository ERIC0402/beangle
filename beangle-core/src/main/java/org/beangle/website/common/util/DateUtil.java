package org.beangle.website.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {
	
	private static final Logger log = LoggerFactory.getLogger(DateUtil.class);
	
	/**
     * Base ISO 8601 Date format yyyyMMdd i.e., 20021225 for the 25th day of December in the year 2002
     */
    public static final String ISO_DATE_FORMAT = "yyyyMMdd";

    /**
     * Expanded ISO 8601 Date format yyyy-MM-dd i.e., 2002-12-25 for the 25th day of December in the year 2002
     */
    public static final String ISO_EXPANDED_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * yyyy-MM-dd hh:mm:ss
     */
    public static String DATETIME_PATTERN = "yyyy-MM-dd hh:mm:ss";

    /**
     * Default lenient setting for getDate.
     */
    private static boolean LENIENT_DATE = false;
    

	public static Date getYearStart() {
		return getYearStart(null);
	}

	public static Date getYearEnd() {
		return getYearEnd(null);
	}

	/**
	 * 一年的开始时间
	 * @param year
	 * @return
	 */
	public static Date getYearStart(Integer year) {
		Calendar c = Calendar.getInstance();
		year = getYear(c, year);
		c.set(year, 0, 1, 0, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	/**
	 * 获取年份
	 * @param year
	 * @return
	 */
	private static Integer getYear(Calendar c, Integer year) {
		if (year == null) {
			year = c.get(Calendar.YEAR);
		}
		return year;
	}
	
	/**
	 * 获取年份
	 * @param year
	 * @return
	 */
	public static Integer getYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return getYear(c, null);
	}
	
	public static Integer getYear(){
		return getYear(new Date());
	}


	/**
	 * 一年的结束时间
	 * @param year
	 * @return
	 */
	public static Date getYearEnd(Integer year) {
		Calendar c = Calendar.getInstance();
		year = getYear(c, year);
		c.set(year, 11, 31, 23, 59, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}

	/**
	 * 一天的开始时间
	 * @param year
	 * @return
	 */
	public static Date getDayStart(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 一天的结束时间
	 * @param year
	 * @return
	 */
	public static Date getDayEnd(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}

	/**
	 * 根据身份证获取出生年月
	 * @param year
	 * @return
	 */
	public static Date getBirthDay(String sfzh) {
		if(sfzh == null || !(sfzh.length() == 18 || sfzh.length() == 15)){
			return null;
		}
		int index = 6;
		String pattern = "yyyyMMdd";
		if(sfzh.length() == 15){
			pattern = "yyMMdd";
		}
		String datestr = sfzh.substring(index, index + pattern.length());
		try {
			Date date = new SimpleDateFormat(pattern).parse(datestr);
			return date;
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 比较2个日期相差天数
	 * @param selDate
	 * @param today
	 * @return
	 */
	public static long dateDistance(Date date1,Date date2){
		  long daysBetween=(date2.getTime()-date1.getTime())/(3600*24*1000);
		  return daysBetween;

	}
	
	/**
	 * 取得n天后的日期
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date getDate(Date date,long n){
		
		long x=n*(3600*24*1000)+date.getTime();
		return new Date(x);
	}

	/**
	 * 一天的开始时间
	 * @param year
	 * @return
	 */
	public static Date getStartTime(Date start){
		if(start == null){
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(start);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 一天的结束时间
	 * @param year
	 * @return
	 */
	public static Date getEndTime(Date end){
		if(end == null){
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(end);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}

	/**
	 * 一月的开始时间
	 * @param year
	 * @return
	 */
	public static Date getMonthStart(Calendar c){
		c.set(Calendar.DAY_OF_MONTH,1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	

	/**
	 * 一月的结束时间
	 * @param year
	 * @return
	 */
	public static Date getMonthEnd(Calendar c){
		c.set(Calendar.DAY_OF_MONTH,c.getActualMaximum(Calendar.DAY_OF_MONTH));
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}

	public static Date clearSecond(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	public static void main(String[] args) {
		System.out.println(getYearStart(2011));
		System.out.println(getYearEnd(2011));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		System.out.println(sdf.format(getBirthDay("330326198412226712")));
		System.out.println(sdf.format(getBirthDay("330326841222671")));
		System.out.println(sdf.format(getDate(new Date(), -7)));
	}

	public static int getMonth() {
		Integer month = Calendar.getInstance().get(Calendar.MONTH);
		return month;
	}public static Date getWeekStart(Date startTime) {
		Calendar c = Calendar.getInstance();
		c.setTime(startTime);
		c.set(Calendar.DAY_OF_WEEK,2);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date getWeekEnd(Date startTime) {
		Calendar c = Calendar.getInstance();
		c.setTime(startTime);
		c.set(Calendar.DAY_OF_WEEK,1);
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 7);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}
	
	 /**
     * 暂时不用
     * @param JD
     * @return
     */
    protected static final float normalizedJulian(float JD)
    {

        float f = Math.round(JD + 0.5f) - 0.5f;

        return f;
    }

    /**
     * 浮点值转换成日期格式<br>
     * 暂时不用
     * Returns the Date from a julian. The Julian date will be converted to noon GMT,
     * such that it matches the nearest half-integer (i.e., a julian date of 1.4 gets
     * changed to 1.5, and 0.9 gets changed to 0.5.)
     *
     * @param JD the Julian date
     * @return the Gregorian date
     */
    public static final Date toDate(float JD)
    {

        /* To convert a Julian Day Number to a Gregorian date, assume that it is for 0 hours, Greenwich time (so
         * that it ends in 0.5). Do the following calculations, again dropping the fractional part of all
         * multiplicatons and divisions. Note: This method will not give dates accurately on the
         * Gregorian Proleptic Calendar, i.e., the calendar you get by extending the Gregorian
         * calendar backwards to years earlier than 1582. using the Gregorian leap year
         * rules. In particular, the method fails if Y<400. */
        float Z = (normalizedJulian(JD)) + 0.5f;
        float W = (int) ( (Z - 1867216.25f) / 36524.25f);
        float X = (int) (W / 4f);
        float A = Z + 1 + W - X;
        float B = A + 1524;
        float C = (int) ( (B - 122.1) / 365.25);
        float D = (int) (365.25f * C);
        float E = (int) ( (B - D) / 30.6001);
        float F = (int) (30.6001f * E);
        int day = (int) (B - D - F);
        int month = (int) (E - 1);

        if (month > 12)
        {
            month = month - 12;
        }

        int year = (int) (C - 4715); //(if Month is January or February) or C-4716 (otherwise)

        if (month > 2)
        {
            year--;
        }

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1); // damn 0 offsets
        c.set(Calendar.DATE, day);

        return c.getTime();
    }

    /**
     * Returns the days between two dates. Positive values indicate that
     * the second date is after the first, and negative values indicate, well,
     * the opposite. Relying on specific times is problematic.
     *
     * @param early the "first date"
     * @param late the "second date"
     * @return the days between the two dates
     */
    public static final int daysBetween(Date early, Date late)
    {

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(early);
        c2.setTime(late);

        return daysBetween(c1, c2);
    }

    /**
     * Returns the days between two dates. Positive values indicate that
     * the second date is after the first, and negative values indicate, well,
     * the opposite.
     *
     * @param early
     * @param late
     * @return the days between two dates.
     */
    public static final int daysBetween(Calendar early, Calendar late)
    {

        return (int) (toJulian(late) - toJulian(early));
    }

    /**
     * Return a Julian date based on the input parameter. This is
     * based from calculations found at
     * <a href="http://quasar.as.utexas.edu/BillInfo/JulianDatesG.html">Julian Day Calculations
     * (Gregorian Calendar)</a>, provided by Bill Jeffrys.
     * @param c a calendar instance
     * @return the julian day number
     */
    public static final float toJulian(Calendar c)
    {

        int Y = c.get(Calendar.YEAR);
        int M = c.get(Calendar.MONTH);
        int D = c.get(Calendar.DATE);
        int A = Y / 100;
        int B = A / 4;
        int C = 2 - A + B;
        float E = (int) (365.25f * (Y + 4716));
        float F = (int) (30.6001f * (M + 1));
        float JD = C + D + E + F - 1524.5f;

        return JD;
    }

    /**
     * 暂时不用
     * Return a Julian date based on the input parameter. This is
     * based from calculations found at
     * <a href="http://quasar.as.utexas.edu/BillInfo/JulianDatesG.html">Julian Day Calculations
     * (Gregorian Calendar)</a>, provided by Bill Jeffrys.
     * @param date
     * @return the julian day number
     */
    public static final float toJulian(Date date)
    {

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return toJulian(c);
    }

    /**
     * 日期增加
     * @param isoString 日期字符串
     * @param fmt 格式
     * @param field 年/月/日 Calendar.YEAR/Calendar.MONTH/Calendar.DATE
     * @param amount 增加数量
     * @return
     * @throws ParseException
     */
    public static final String dateIncrease(String isoString, String fmt, int field, int amount)
    {

        try
        {
            Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone(
                "GMT"));
            cal.setTime(stringToDate(isoString, fmt, true));
            cal.add(field, amount);

            return dateToString(cal.getTime(), fmt);

        }
        catch (Exception ex)
        {
            return null;
        }
    }

    /**
     * Time Field Rolling function.
     * Rolls (up/down) a single unit of time on the given time field.
     *
     * @param isoString
     * @param field the time field.
     * @param up Indicates if rolling up or rolling down the field value.
     * @param expanded use formating char's
     * @exception ParseException if an unknown field value is given.
     */
    public static final String roll(String isoString, String fmt, int field, boolean up)
        throws ParseException
    {

        Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone(
            "GMT"));
        cal.setTime(stringToDate(isoString, fmt));
        cal.roll(field, up);

        return dateToString(cal.getTime(), fmt);
    }

    /**
     * Time Field Rolling function.
     * Rolls (up/down) a single unit of time on the given time field.
     *
     * @param isoString
     * @param field the time field.
     * @param up Indicates if rolling up or rolling down the field value.
     * @exception ParseException if an unknown field value is given.
     */
    public static final String roll(String isoString, int field, boolean up)
        throws ParseException
    {

        return roll(isoString, DATETIME_PATTERN, field, up);
    }

    /**
     * 字符串转换为日期java.util.Date
     * @param dateText 字符串
     * @param format 日期格式
     * @param lenient 日期越界标志
     * @return
     */
    public static Date stringToDate(String dateText, String format, boolean lenient)
    {

        if (dateText == null)
        {

            return null;
        }

        DateFormat df = null;

        try
        {

            if (format == null)
            {
                df = new SimpleDateFormat();
            }
            else
            {
                df = new SimpleDateFormat(format);
            }

            // setLenient avoids allowing dates like 9/32/2001
            // which would otherwise parse to 10/2/2001
            df.setLenient(false);

            return df.parse(dateText);
        }
        catch (ParseException e)
        {

            return null;
        }
    }

    /**
     * 字符串转换为日期java.util.Date
     * @param dateText 字符串
     * @param format 日期格式
     * @return
     */
    public static Date stringToDate(String dateString, String format)
    {

        return stringToDate(dateString, format, LENIENT_DATE);
    }

    /**
     * 字符串转换为日期java.util.Date
     * @param dateText 字符串
     */
    public static Date stringToDate(String dateString)
    {

        return stringToDate(dateString, ISO_EXPANDED_DATE_FORMAT, LENIENT_DATE);
    }

    /** 根据时间变量返回时间字符串
     * @return 返回时间字符串
     * @param pattern 时间字符串样式
     * @param date 时间变量
     */
    public static String dateToString(Date date, String pattern)
    {

        if (date == null)
        {

            return null;
        }

        try
        {

            SimpleDateFormat sfDate = new SimpleDateFormat(pattern);
            sfDate.setLenient(false);

            return sfDate.format(date);
        }
        catch (Exception e)
        {

            return null;
        }
    }

    /**
     * 根据时间变量返回时间字符串 yyyy-MM-dd
     * @param date
     * @return
     */
    public static String dateToString(Date date)
    {
        return dateToString(date, ISO_EXPANDED_DATE_FORMAT);
    }

    /** 返回当前时间
     * @return 返回当前时间
     */
    public static Date getCurrentDateTime()
    {
        java.util.Calendar calNow = java.util.Calendar.getInstance();
        java.util.Date dtNow = calNow.getTime();

        return dtNow;
    }

    /**
     * 返回当前日期字符串
     * @param pattern 日期字符串样式
     * @return
     */
    public static String getCurrentDateString(String pattern)
    {
        return dateToString(getCurrentDateTime(), pattern);
    }

    /**
     * 返回当前日期字符串 yyyy-MM-dd
     * @return
     */
    public static String getCurrentDateString()
    {
        return dateToString(getCurrentDateTime(), ISO_EXPANDED_DATE_FORMAT);
    }

    /**
     * 返回当前日期+时间字符串 yyyy-MM-dd hh:mm:ss
     * @param date
     * @return
     */
    public static String dateToStringWithTime(Date date)
    {

        return dateToString(date, DATETIME_PATTERN);
    }

    /**
     * 日期增加-按日增加
     * @param date
     * @param days
     * @return java.util.Date
     */
    public static Date dateIncreaseByDay(Date date, int days)
    {

        Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone(
            "GMT"));
        cal.setTime(date);
        cal.add(Calendar.DATE, days);

        return cal.getTime();
    }

    /**
     * 日期增加-按月增加
     * @param date
     * @param days
     * @return java.util.Date
     */
    public static Date dateIncreaseByMonth(Date date, int mnt)
    {

        Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone(
            "GMT"));
        cal.setTime(date);
        cal.add(Calendar.MONTH, mnt);

        return cal.getTime();
    }
    /**
     * 日期增加-按年增加
     * @param date
     * @param mnt
     * @return java.util.Date
     */
    public static Date dateIncreaseByYear(Date date, int mnt)
    {

        Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone(
            "GMT"));
        cal.setTime(date);
        cal.add(Calendar.YEAR, mnt);

        return cal.getTime();
    }
    /**
     * 日期增加
     * @param date 日期字符串 yyyy-MM-dd
     * @param days
     * @return 日期字符串 yyyy-MM-dd
     */
    public static String dateIncreaseByDay(String date, int days)
    {
        return dateIncreaseByDay(date, ISO_DATE_FORMAT, days);
    }

    /**
     * 日期增加
     * @param date 日期字符串
     * @param fmt 日期格式
     * @param days
     * @return
     */
    public static String dateIncreaseByDay(String date, String fmt, int days)
    {
        return dateIncrease(date, fmt, Calendar.DATE, days);
    }

    /**
     * 日期字符串格式转换
     * @param src 日期字符串
     * @param srcfmt 源日期格式
     * @param desfmt 目标日期格式
     * @return
     */
    public static String stringToString(String src, String srcfmt, String desfmt)
    {
        return dateToString(stringToDate(src, srcfmt), desfmt);
    }
    
    
    public static int getWeekDay(Date date)
    {
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }
    
    
    /**
     * @param 返回java.sql.Time格式的
     * */
    public static java.sql.Time strToTime(String strDate) {
        String str = strDate;
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Time time = new java.sql.Time(d.getTime());
        return time.valueOf(str);
    }
    
    /**
     * @param 返回Timestamp
     * */
    public static Timestamp strToTimestamp(String timeStr) {
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         java.util.Date d = null;
         try {
             d = format.parse(timeStr);
         } catch (Exception e) {
             e.printStackTrace();
         }
    	
  		Timestamp timestamp=new Timestamp(d.getTime()); 
        return timestamp;
    }
}
