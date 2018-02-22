package wusc.edu.pay.common.utils.date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * User: mew <p />
 * Time: 17/7/11 12:54  <p />
 * Version: V1.0  <p />
 * Description: 日期操作工具类 <p />
 */
public final class DateOperator {

    private DateOperator() {
    }

    private static void validateDateNotNull(Date date, String exceptionTip) {
        Validate.notNull(date, exceptionTip);
    }

    private static void validateYear(int year) {
        if (year < 1900 || year > 2099) {
            throw new IllegalArgumentException("year is not avaliable");
        }
    }

    /**
     * 根据传入的Date对象获取不包含时部的Calendar对象
     *
     * @param date
     * @return
     */
    public static Calendar getClearedTimePartCalendar(Date date) {
        validateDateNotNull(date, "date is not avaliable");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        clearTimePart(calendar);
        return calendar;
    }

    /**
     * 获取不包含时部的当前Date对象
     *
     * @return
     */
    public static Date getClearedTimePartCurDate() {
        Calendar now = Calendar.getInstance();
        clearTimePart(now);
        return now.getTime();
    }

    /**
     * 是否是同一天
     *
     * @param date
     * @param dateAnother
     * @return
     */
    public static boolean isSameDay(Date date, Date dateAnother) {
        validateDateNotNull(date, "date is not avaliable");
        validateDateNotNull(date, "dateAnother is not avaliable");

        String dateStr = defaultFormatDate(date);
        String dateAnotherStr = defaultFormatDate(dateAnother);
        return dateStr.equals(dateAnotherStr);
    }

    /**
     * 是否是周末.即是否是周六或周日
     *
     * @param date
     * @return
     */
    public static boolean isWeekend(Date date) {
        validateDateNotNull(date, "date is not avaliable");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return (calendar.get(Calendar.DAY_OF_WEEK) == 7 || calendar.get(Calendar.DAY_OF_WEEK) == 1);
    }

    /**
     * 获取相差的年数
     * <br>
     * yearDiff = endYear - startYear
     *
     * @param startYear
     * @param endYear
     * @return
     */
    public static int getYearDiff(int startYear, int endYear) {
        validateYear(startYear);
        validateYear(endYear);

        return endYear - startYear;
    }

    /**
     * 获取结束日期和开始日期之间相差的年数
     * <br>
     * yearDiff = monthDiff / 12
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getYearDiff(Date startDate, Date endDate) {
        validateDateNotNull(startDate, "startDate is not avaliable");
        validateDateNotNull(endDate, "endDate is not avaliable");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);

        int yearEnd = calendar.get(Calendar.YEAR);
        int monthEnd = calendar.get(Calendar.MONTH);
        int dayEnd = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.setTime(startDate);

        int yearStart = calendar.get(Calendar.YEAR);
        int monthStart = calendar.get(Calendar.MONTH);
        int dayStart = calendar.get(Calendar.DAY_OF_MONTH);

        // 计算
        int yearDiff = yearEnd - yearStart;

        if (monthEnd < monthStart) {
            yearDiff--;
        } else if (monthEnd == monthStart) {
            if (dayEnd < dayStart) {
                yearDiff--;
            }
        } else {
            //do nothing;
        }

//		int monthDiff = getMonthDiff(startDate, endDate);
//		return monthDiff / 12;

        return yearDiff;
    }

    /**
     * 获取结束日期和开始日期之间相差的月数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getMonthDiff(Date startDate, Date endDate) {
        /*
        long minutes = getMinuteDiff(startDate, endDate);
		return (int) (minutes / (60 * 24 * 31));
		*/

        validateDateNotNull(startDate, "startDate is not avaliable");
        validateDateNotNull(endDate, "endDate is not avaliable");

        Calendar startCal = getClearedTimePartCalendar(startDate);
        startCal.set(Calendar.DAY_OF_MONTH, 1); // 重置为当月的第一天
        Calendar endCal = getClearedTimePartCalendar(endDate);
        endCal.set(Calendar.DAY_OF_MONTH, 1); // 重置为当月的第一天

        // 获取相差的年份
        int yearDiff = getYearDiff(startCal.get(Calendar.YEAR), endCal.get(Calendar.YEAR));

        int monthDiff = endCal.get(Calendar.MONTH) - startCal.get(Calendar.MONTH);

        // 同一年份的情形,直接月份相减
        if (yearDiff == 0) {
            return monthDiff;
        } else {
            return monthDiff + (12 * yearDiff);
        }
    }

    /**
     * 获取结束日期和开始日期之间相差的天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getDayDiff(Date startDate, Date endDate) {
        /*
        if (startDate != null && endDate != null) {
			return (int) ((endDate.getTime() - startDate.getTime()) / 1000 / 60 / 60 / 24);
		} else {
			throw new IllegalArgumentException("startDate or endDate parameter is not avaliable");
		}
		*/

        validateDateNotNull(startDate, "startDate is not avaliable");
        validateDateNotNull(endDate, "endDate is not avaliable");

        Calendar startCal = getClearedTimePartCalendar(startDate);
        Calendar endCal = getClearedTimePartCalendar(endDate);

        return (int) ((endCal.getTime().getTime() - startCal.getTime().getTime()) / 1000 / 60 / 60 / 24);
    }

    /**
     * 获取结束日期和开始日期之间相差的小时数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getHourDiff(Date startDate, Date endDate) {
        /*
        if (startDate != null && endDate != null) {
			return ((endDate.getTime() - startDate.getTime()) / 1000 / 60 / 60);
		} else {
			throw new IllegalArgumentException("startDate or endDate parameter is not avaliable");
		}
		*/
        validateDateNotNull(startDate, "startDate is not avaliable");
        validateDateNotNull(endDate, "endDate is not avaliable");

        return ((endDate.getTime() - startDate.getTime()) / 1000 / 60 / 60);
    }

    /**
     * 获取结束日期和开始日期之间相差的分钟数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getMinuteDiff(Date startDate, Date endDate) {
        validateDateNotNull(startDate, "startDate is not avaliable");
        validateDateNotNull(endDate, "endDate is not avaliable");

        return ((endDate.getTime() - startDate.getTime()) / 1000 / 60);
    }

    /**
     * adds the specified (signed) amount of time to the given calendar field, based on the calendar's rules.
     * <p>
     * 给指定的Date实例所代表的Calendar实例的field添加指定的时间量,当时间量为负时即代表减少指定的时间量
     *
     * @param date
     * @param field
     * @param amount
     * @return
     */
    public static Date add(Date date, int field, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("date parameter is not avaliable");
        }
        if (field < 0 || field >= Calendar.ZONE_OFFSET) {
            throw new IllegalArgumentException("field parameter is not avaliable");
        }

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    /**
     * method had been deprecated
     *
     * @param date
     * @param hourAmount
     * @return
     * @see public static Date add(Date date, int field, int amount) {...}
     */
    @Deprecated
    public static Date addHours(Date date, int hourAmount) {
        if (date == null) {
            throw new IllegalArgumentException("date parameter is not avaliable");
        }
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(GregorianCalendar.HOUR_OF_DAY, hourAmount);
        return calendar.getTime();
    }

    /**
     * method had been deprecated
     *
     * @param date
     * @param dayAmount
     * @return
     * @see public static Date add(Date date, int field, int amount) {...}
     */
    @Deprecated
    public static Date addDays(Date date, int dayAmount) {
        if (date == null) {
            throw new IllegalArgumentException("date parameter is not avaliable");
        }
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(GregorianCalendar.DAY_OF_YEAR, dayAmount);
        return calendar.getTime();
    }

    /**
     * method had been deprecated
     *
     * @param date
     * @param monthAmount
     * @return
     * @see public static Date add(Date date, int field, int amount) {...}
     */
    @Deprecated
    public static Date addMonthes(Date date, int monthAmount) {
        if (date == null) {
            throw new IllegalArgumentException("date parameter is not avaliable");
        }
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(GregorianCalendar.MONTH, monthAmount);
        return calendar.getTime();
    }

    /**
     * method had been deprecated
     *
     * @param date
     * @param yearAmount
     * @return
     * @see public static Date add(Date date, int field, int amount) {...}
     */
    @Deprecated
    public static Date addYears(Date date, int yearAmount) {
        if (date == null) {
            throw new IllegalArgumentException("date parameter is not avaliable");
        }
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(GregorianCalendar.YEAR, yearAmount);
        return calendar.getTime();
    }

    /**
     * 获得指定年份下指定月份的最后一天
     *
     * @param year
     * @param monthInRealWorld
     *         现实世界的月份[1-12]
     * @return
     */
    public static Date getLastDayOfMonthInTheSpecifiedYear(int year, int monthInRealWorld) {
        return getLastDayOfCompMonthInTheSpecifiedYear(year, monthInRealWorld - 1);
    }

    /**
     * 获得指定年份下指定计算机月份的最后一天
     *
     * @param year
     * @param monthInComputer
     *         计算机的月份[0-11]
     * @return
     */
    public static Date getLastDayOfCompMonthInTheSpecifiedYear(int year, int monthInComputer) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(year, monthInComputer, 01);
        calendar.set(GregorianCalendar.DAY_OF_MONTH, calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));

        return calendar.getTime();
    }

    /**
     * 获得指定年份下指定月份的第一天
     *
     * @param year
     * @param monthInRealWorld
     *         现实世界的月份[1-12]
     * @return
     */
    public static Date getFirstDayOfMonthInTheSpecifiedYear(int year, int monthInRealWorld) {
        return getFirstDayOfCompMonthInTheSpecifiedYear(year, monthInRealWorld - 1);
    }

    /**
     * 获得指定年份下指定计算机月份的第一天
     *
     * @param year
     * @param monthInComputer
     *         计算机的月份[0-11]
     * @return
     */
    public static Date getFirstDayOfCompMonthInTheSpecifiedYear(int year, int monthInComputer) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(year, monthInComputer, 01);
        return calendar.getTime();
    }

    public static int getYear(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(GregorianCalendar.YEAR);
    }

    public static int getComputerMonth(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(GregorianCalendar.MONTH);
    }

    public static int getRealWorldMonth(Date date) {
        return getComputerMonth(date) + 1;
    }

    public static int getDayForMonth(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(GregorianCalendar.DAY_OF_MONTH);
    }

    public static int getHour(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(GregorianCalendar.HOUR_OF_DAY);
    }

    public static int getMinute(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(GregorianCalendar.MINUTE);
    }

    public static int getSecond(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(GregorianCalendar.SECOND);
    }

    public static final String FORMAT_STR = "yyyy-MM-dd";

    public static final String FORMAT_STR_WITH_TIME = "yyyy-MM-dd HH:mm:ss";

    public static final SimpleDateFormat DEFAULT_DATE_FORMATER_WITH_TIME = new SimpleDateFormat(FORMAT_STR_WITH_TIME);

    public static final SimpleDateFormat DEFAULT_DATE_FORMATTER = new SimpleDateFormat(FORMAT_STR);

	/*----- 日期转换 START -----*/

    /**
     * 将具有缺省日期/时间格式的日期/时间字符串转换为日期对象,可选择在转换时是否加入时部
     * <br>
     * 缺省日期时间格式 格式1:yyyy-MM-dd 格式2:yyyy-MM-dd HH:mm:ss
     * <br>
     *
     * @param dateStr
     * @param withTime
     * @return
     */
    public static Date defaultParse(String dateStr, boolean withTime) {
        try {
            if (withTime) {
                return DEFAULT_DATE_FORMATER_WITH_TIME.parse(dateStr);
            } else {
                return DEFAULT_DATE_FORMATTER.parse(dateStr);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("parameter dateStr is not avaliable");
        }
    }

    /**
     * 将具有指定格式的日期/时间字符串按照指定的描述日期/时间格式的规则转换为日期对象
     *
     * @param dateStr
     * @param formatString
     * @return
     */
    public static Date parse(String dateStr, String formatString) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }

        if (StringUtils.isBlank(formatString)) {
            formatString = FORMAT_STR_WITH_TIME;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(formatString);
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("parameter dateStr is not avaliable");
        }
    }

	/*----- 日期转换 END -----*/

	/*----- 日期格式化 START -----*/

    /**
     * 将指定的日期对象按照缺省格式格式化为指定的日期字符串
     * <br />
     * 缺省格式:yyyy-MM-dd
     * <br />
     * 示例:2015-01-01
     *
     * @param date
     * @return
     */
    public static String defaultFormatDate(Date date) {
        return defaultFormatDate(date, false);
    }

    /**
     * 将指定的日期对象按照缺省格式格式化为指定的日期字符串,可选择在格式化后是否保留时分秒
     * <br />
     * 缺省格式:yyyy-MM-dd 或 yyyy-MM-dd HH:mm:ss
     * <br />
     * 示例:2015-01-01 或 2015-01-01 00:00:00
     *
     * @param date
     * @param withTime
     * @return
     */
    public static String defaultFormatDate(Date date, boolean withTime) {

        validateDateNotNull(date, "date is not avaliable");

        if (withTime) {
            return formatDate(date, DEFAULT_DATE_FORMATER_WITH_TIME);
        } else {
            return formatDate(date, DEFAULT_DATE_FORMATTER);
        }
    }

    /**
     * 将指定的日期对象按照指定的日期时间格式串格式化为指定的日期字符串
     *
     * @param date
     * @param formatString
     * @return
     */
    public static String formatDate(Date date, String formatString) {
        return formatDate(date, new SimpleDateFormat(formatString));
    }

    public static String formatDate(Date date, DateFormat dateFormat) {
        validateDateNotNull(date, "date is not avaliable");

        if (dateFormat == null) {
            throw new IllegalArgumentException("parameter dateFormat is not avaliable");
        }
        return dateFormat.format(date);
    }

	/*----- 日期格式化 END -----*/

    /**
     * 清空Calendar对象的时部.即时,分,秒,毫秒
     *
     * @param calendar
     */
    public static void clearTimePart(Calendar calendar) {
        if (calendar == null) {
            throw new RuntimeException("parameter calendar is not avaliable");
        }

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    /**
     * 清空Date对象的时部.即时,分,秒,毫秒
     *
     * @param date
     * @return
     */
    public static Date clearTimePart(Date date) {
        if (date == null) {
            throw new RuntimeException("parameter date is not avaliable");
        }
        Calendar current = Calendar.getInstance();
        current.setTime(date);

        current.set(Calendar.HOUR_OF_DAY, 0);
        current.set(Calendar.MINUTE, 0);
        current.set(Calendar.SECOND, 0);
        current.set(Calendar.MILLISECOND, 0);

        return current.getTime();
    }

    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();
        //clearTimePart(calendar);

        Calendar otherCalendar = Calendar.getInstance();
        otherCalendar.add(Calendar.MINUTE, 1);
        //clearTimePart(otherCalendar);

        validateDateNotNull(calendar.getTime(), "error");

        System.out.println("getClearedTimePartCalendar:" + defaultFormatDate(getClearedTimePartCalendar(new Date())
                .getTime(), true));

        System.out.println("current date without time:" + defaultFormatDate(getClearedTimePartCurDate(), true));

        System.out.println("isSameDay:" + isSameDay(calendar.getTime(), otherCalendar.getTime()));

        System.out.println("isWeekend:" + isWeekend(calendar.getTime()));
        System.out.println("isWeekend:" + isWeekend(otherCalendar.getTime()));

        Calendar weekendCalendar = Calendar.getInstance();
        clearTimePart(weekendCalendar);
        weekendCalendar.set(Calendar.MONTH, 4);
        weekendCalendar.set(Calendar.DAY_OF_MONTH, 1);
        System.out.println("isWeekend:" + isWeekend(weekendCalendar.getTime()));

        System.out.println("getYearDiff:" + getYearDiff(1900, 1971));
        //System.out.println("getYearDiff:" + getYearDiff(1899,1971));
        System.out.println("getYearDiff:" + getYearDiff(1900, 2099));
        //System.out.println("getYearDiff:" + getYearDiff(1900,3000));

        //otherCalendar.add(Calendar.YEAR, 1);
        //otherCalendar.add(Calendar.MONTH, 1);
        //otherCalendar.add(Calendar.DAY_OF_YEAR, 3);

        System.out.println("hour diff:" + getHourDiff(calendar.getTime(), otherCalendar.getTime()));
        System.out.println("minute diff:" + getMinuteDiff(calendar.getTime(), otherCalendar.getTime()));
        System.out.println("year diff:" + getYearDiff(calendar.getTime(), otherCalendar.getTime()));
        System.out.println("month diff:" + getMonthDiff(calendar.getTime(), otherCalendar.getTime()));
        System.out.println("day diff:" + getDayDiff(calendar.getTime(), otherCalendar.getTime()));


        System.out.println("calendar:" + defaultFormatDate(calendar.getTime(), true));
        System.out.println("otherCalendar:" + defaultFormatDate(otherCalendar.getTime(), true));
    }

}

