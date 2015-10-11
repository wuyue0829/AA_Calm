package com.calm.calm.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * ʱ�䴦����
 * @author jerry
 *
 */
public class DateUtil {
	//һ�� ,  һ����
	public static long ONE_DAY = 24 * 60 * 60 * 1000 , ONE_MINITE =  60 * 1000; 

	/**
	 * ��ȡ��ǰʱ��
	 */
	public static Date getCurrent()
	{
		Date currentDate = new Date();
		return currentDate;
	}
	/**
	 * ��ȡ������ʼʱ��
	 */  
	public static Date getCurrentdate()
	{
		String currentDate = getFormatDate("yyyyMMdd", 
				new Date());
		Date date = getDate("yyyyMMddHHmmss", currentDate + 
				"000000");
		return date;
	}
	/**
	 * ��ȡ�������ʱ��
	 */ 
	public static Date getCurrentdateEnd()
	{
		String currentDate = getFormatDate("yyyyMMdd", 
				new Date());
		Date date = getDate("yyyyMMddHHmmss", currentDate + 
				"235959");
		return date;
	}

	/**
	 * ��ȡdate���ڵ��µ�������
	 * @param date ���� 
	 */ 
	public static int getMonthWeekCount(Date date)
	{
		Date monthenddate = getMonthEndDay(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(monthenddate);
		return cal.get(4);
	}

	/**
	 * ��ȡ��ǰ�·�
	 * @param date ���� 
	 */ 
	public static Date getCurrentMonth()
	{
		return getMonthFirstDay(new Date());
	}
	/**
	 * ��ȡdate���ڵ��µĵ�һ�������
	 * @param date ���� 
	 */ 
	public static Date getMonthFirstDay(Date date)
	{
		String month = getFormatDate("yyyyMM", date) + "01";
		Date firstday = null;

		firstday = getDate("yyyyMMdd", month);

		return firstday;
	}
	/**
	 * ��ȡdate���ڵ����ڵ�����һ������
	 * @param date ���� 
	 */
	public static Date getFirstDayOfWeek(Date date)
	{
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(2);
		c.setTime(date);
		c.set(7, c.getFirstDayOfWeek());
		return c.getTime();
	}
	/**
	 * ��ȡdate���ڵ����ڵ������������
	 * @param date ���� 
	 */
	public static Date getFirstDayOfWeekSunDay(Date date)
	{
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(1);
		c.setTime(date);
		c.set(7, c.getFirstDayOfWeek());
		return c.getTime();
	}
	/**
	 * ��ȡdate���ڵ����ڵ���ʼʱ���ַ���,��ʽΪyyyy-MM-dd 00:00:00
	 * @param date ���� 
	 */
	public static String getWeekFirstDayStr(Date date)
	{
		String str = getFormatDate("yyyy/MM/dd", getFirstDayOfWeek(date));
		String strArr = str.replace("/", "-");
		return strArr + " 00:00:00";
	}
	/**
	 * ��ȡdate�յ���ʼʱ���ַ���,��ʽΪyyyy/MM/dd 00:00:00
	 * @param date ���� 
	 */
	public static String getDayStartStr(Date date)
	{
		String str = getFormatDate("yyyy/MM/dd", date);
		return str + " 00:00:00";
	}
	/**
	 * ��ȡdate�յĽ���ʱ���ַ���,��ʽΪyyyy/MM/dd 00:00:00
	 * @param date ���� 
	 */
	public static String getDayEndStr(Date date)
	{
		String str = getFormatDate("yyyy/MM/dd", date);
		return str + " 23:59:59";
	}
	/**
	 * ��ȡdate���ڵ����ڵ������������
	 * @param date ���� 
	 */
	public static Date getLastDayOfWeek(Date date)
	{
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(2);
		c.setTime(date);
		c.set(7, c.getFirstDayOfWeek() + 6);
		return c.getTime();
	}

	/**
	 * ��ȡdate���ڵ����ڽ���ʱ���ַ���,��ʽΪyyyy-MM-dd 23:59:59
	 * @param date ���� 
	 */
	public static String getWeekEndDayStr(Date date)
	{
		String str = getFormatDate("yyyy/MM/dd", getFirstDayOfWeek(date));
		String strArr = str.replace("/", "-");
		return strArr + " 23:59:59";
	}
	/**
	 * ��ȡdate���ڵ��µ����һ�������
	 * @param date ���� 
	 */
	public static Date getMonthEndDay(Date date)
	{
		Date endday = dateAdd(3, -1, dateAdd(
				2, 1, getMonthFirstDay(date)));
		return endday;
	}

	/**
	 * ��ȡ���һ������� ϵͳ�趨Ϊ29991231
	 */
	public static Date getEndDate()
	{
		return getDate("yyyyMMdd", "29991231");
	}

	/**
	 * ��ȡ��������һ������� ϵͳ�趨Ϊ99991231
	 */
	public static Date getServiceEndDate()
	{
		return getDate("yyyyMMdd", "99991231");
	}
	/**
	 * ��ȡ��ʼ������� ϵͳ�趨Ϊ19900101
	 */
	public static Date getStartDate()
	{
		return getDate("yyyyMMdd", "19900101");
	}
	/**
	 * ��ȡָ���·ݵ���ʼʱ��
	 * @param month �·�
	 */
	public static Date getSelectMonth(String month)
	{
		Date time = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String date = sdf.format(time);
		date = date + "-" + month + "-01 00:00:00.0";
		sdf.applyPattern("yyyy-MM");
		Date curDate = null;
		try {
			curDate = sdf.parse(date);
		}
		catch (Exception e) {
		}
		return curDate;
	}

	/**
	 * �������ջ�ȡ����
	 * @param birthday ���������ַ���
	 */
	public static String getAge(String birthday)
	{
		if ((birthday == null) || ("".equals(birthday)))
			return "0";
		Date timenow = new Date();
		Date birth = null;

		birth = getDate("yyyyMMdd", birthday);
		int byear = Integer.parseInt(getFormatDate("yyyy", birth));
		int nyear = Integer.parseInt(getFormatDate("yyyy", timenow));
		int bmonth = Integer.parseInt(getFormatDate("MM", birth));
		int nmonth = Integer.parseInt(getFormatDate("MM", timenow));
		int age = nyear - byear;
		if (age < 0)
			return "0";
		if (nmonth < bmonth)
			--age;
		return String.valueOf(age);
	}
	/**
	 * �������ջ�ȡ����
	 * @param birthday ��������
	 */
	public static String getAge(Date birthday)
	{
		if (birthday == null)
			return "0";
		Date timenow = new Date();
		int byear = Integer.parseInt(getFormatDate("yyyy", birthday));
		int nyear = Integer.parseInt(getFormatDate("yyyy", timenow));
		int bmonth = Integer.parseInt(getFormatDate("MM", birthday));
		int nmonth = Integer.parseInt(getFormatDate("MM", timenow));
		int age = nyear - byear;
		if (age < 0)
			return "0";
		if (nmonth < bmonth)
			--age;
		return String.valueOf(age);
	}
	/**
	 * ���������ȡ�������
	 * @param age ����
	 */
	public static String getBirthday(String age)
	{
		if (age == null)
			return null;

		String nowyear = getFormatDate("yyyy", new Date());

		Long birthday = Long.parseLong(nowyear) - Long.parseLong(age);

		return birthday.toString();
	}
	/**
	 * ȡ������
	 */
	public static String getToday()
	{
		return getFormatDate("yyyy-MM-dd",new Date());
	}
	public static String getNow()
	{
		return getFormatDate("yyyy-MM-dd HH:mm:ss",new Date());
	}
	public static String getNowTime()
	{
		return new Date().getTime()+"";
	}
	public static String getYesterday()
	{
		return getFormatDate("yyyy-MM-dd",dateAdd(3, -1, new Date()));
	}
	public static String getBeforeYesterday()
	{
		return getFormatDate("yyyy-MM-dd",dateAdd(3, -2, new Date()));
	}
	public static String getTomorrow()
	{
		return getFormatDate("yyyy-MM-dd",dateAdd(3, 1, new Date()));
	}
	/**
	 * ȡ������
	 */
	public static String getWelcome()
	{
		Date date = new Date();
		int hour = date.getHours();
		if(hour>=1&&hour<=8){
			return "���Ϻã�";
		}
		if(hour>=9&&hour<=10){
			return "����ã�";
		}
		if(hour>=11&&hour<=13){
			return "����ã�";
		}
		if(hour>=14&&hour<=18){
			return "����ã�";
		}
		if(hour>=19||hour<1){
			return "���Ϻã�";
		}
		return "���Ϻã�";
	}
	/**
	 * �������������ַ�����ָ�����ڻ�ȡָ������ʱ������
	 * @param birthday ���������ַ���
	 * @param curDate  ָ������
	 */
	public static String getAge(Date birthday, Date curDate)
	{
		if (birthday == null)
			return "0";
		Date timenow = curDate;
		int byear = Integer.parseInt(getFormatDate("yyyy", birthday));
		int nyear = Integer.parseInt(getFormatDate("yyyy", timenow));
		int bmonth = Integer.parseInt(getFormatDate("MM", birthday));
		int nmonth = Integer.parseInt(getFormatDate("MM", timenow));
		int age = nyear - byear;
		if (age < 0)
			return "0";
		if (nmonth < bmonth)
			--age;
		return String.valueOf(age);
	}

	/**
	 * ���ݸ�ʽ�����ڻ�ȡ����ʽ��ʾ�������ַ���
	 * @param sFormat ��ʽ
	 * @param date    ����
	 */
	public static String getFormatDate(String sFormat, Date date)
	{
		if (date == null)
			return null;

		if ((sFormat == "yy") || (sFormat == "yyyy") || (sFormat == "yyyy-MM")||(sFormat == "yy.MM.dd")||(sFormat == "yyyy.MM.dd")||
				(sFormat == "MM") || (sFormat == "dd") || (sFormat == "MM-dd") || 
				(sFormat == "MM.dd")||(sFormat == "M/dd")||(sFormat == "MM/dd") || (sFormat == "yyyyMM") || 
				(sFormat == "yyyyMMdd") || (sFormat == "yyyy/MM") || 
				(sFormat == "yy/MM/dd") || 
				(sFormat == "yyyy/MM/dd") || 
				(sFormat == "yyyy-MM-dd") || (sFormat == "yyyy-MM-dd HH:mm:ss") || 
				(sFormat == "yyyy/MM/dd HH:mm:ss") || 
				(sFormat == "yyyyMMddHHmmss") || (sFormat == "yyyyMMddHHmmssSSS") ||
				(sFormat == "yyyy��MM��dd��") ||(sFormat == "yyyy��M��d��") || (sFormat == "yyyy��") || 
				(sFormat == "yyyy��MM��") || (sFormat == "MM��dd��") ||(sFormat == "M��d��") ||  
				(sFormat == "dd��") || (sFormat == "HH") || (sFormat == "H") || 
				(sFormat == "mm") || (sFormat == "ss")|| (sFormat == "SSS") || 
				(sFormat == "yyyy/MM/dd HH:mm")||(sFormat == "yyyy.MM.dd HH:mm")||(sFormat == "yyyy��M��d�� HH:mm")) {
			SimpleDateFormat formatter = new SimpleDateFormat(sFormat);
			return formatter.format(date);
		}else{
			if ((sFormat == "HH:mm")) {
				sFormat = "yyyy-MM-dd HH:mm:ss";
				SimpleDateFormat formatter = new SimpleDateFormat(sFormat);
				return formatter.format(date).substring(11, 16);
			}
		}
		return null;
	}
	/**
	 * ���ݸ�ʽ�����ڻ�ȡ����ʽ��ʾ�������ַ���
	 * @param sFormat ��ʽ
	 * @param date    ����
	 */
	public static String getFormatDate(String sFormat, Timestamp timestamp)
	{
		if (timestamp == null)
			return null;

		if ((sFormat == "yy") || (sFormat == "yyyy") || (sFormat == "yyyy-MM")||
				(sFormat == "MM") || (sFormat == "dd") || (sFormat == "MM-dd") || 
				(sFormat == "MM.dd")||(sFormat == "MM/dd") || (sFormat == "yyyyMM") || 
				(sFormat == "yyyyMMdd") || (sFormat == "yyyy/MM") || 
				(sFormat == "yy/MM/dd") || 
				(sFormat == "yyyy/MM/dd") || 
				(sFormat == "yyyy-MM-dd") || (sFormat == "yyyy-MM-dd HH:mm:ss") || 
				(sFormat == "yyyy/MM/dd HH:mm:ss") || 
				(sFormat == "yyyyMMddHHmmss") || (sFormat == "yyyyMMddHHmmssSSS") ||
				(sFormat == "yyyy��MM��dd��") ||(sFormat == "yyyy��M��d��") || (sFormat == "yyyy��") || 
				(sFormat == "yyyy��MM��") || (sFormat == "MM��dd��") ||(sFormat == "M��d��") ||  
				(sFormat == "dd��") || (sFormat == "HH") || (sFormat == "H") || 
				(sFormat == "mm") || (sFormat == "ss")|| (sFormat == "SSS") || 
				(sFormat == "yyyy/MM/dd HH:mm")) {
			SimpleDateFormat formatter = new SimpleDateFormat(sFormat);
			return formatter.format(timestamp);
		}else{
			if ((sFormat == "HH:mm")) {
				sFormat = "yyyy-MM-dd HH:mm:ss";
				SimpleDateFormat formatter = new SimpleDateFormat(sFormat);
				return formatter.format(timestamp).substring(11, 16);
			}
		}
		return null;
	}
	/**
	 * ���ݸ�ʽ�����ڻ�ȡ����ʽ��ʾ������
	 * @param sFormat ��ʽ
	 * @param date    ����
	 */
	public static Date getDate(String sFormat, String date)
	{
		if ((date == null) || ("".equals(date)))
			return null;

		if ((sFormat == "yy") || (sFormat == "yyyy") || 
				(sFormat == "MM") || (sFormat == "dd") || 
				(sFormat == "MM/dd") || (sFormat == "yyyyMM") || 
				(sFormat == "yyyyMMdd") || (sFormat == "yyyy/MM") || 
				(sFormat == "yy/MM/dd") || 
				(sFormat == "yyyy/MM/dd") || 
				(sFormat == "yyyy-MM-dd") || 
				(sFormat == "yyyy/MM/dd HH:mm:ss") || (sFormat == "yyyy-MM-dd HH:mm:ss") ||
				(sFormat == "yyyyMMddHHmm") || 
				(sFormat == "yyyyMMddHHmmss") || 
				(sFormat == "yyyyMMdd-HHmmss") || 
				(sFormat == "yyyy��MM��dd��") || 
				(sFormat == "yyyy��MM��") || (sFormat == "MM��dd��") || 
				(sFormat == "dd��") || (sFormat == "HH") ||
				(sFormat == "mm")) {
			SimpleDateFormat formatter = new SimpleDateFormat(sFormat);
			try {
				return formatter.parse(date);
			}
			catch (Exception e) {
			}
		}else{
				if (sFormat == "HH:mm") {
					sFormat = "yyyy-MM-dd HH:mm:ss";
					SimpleDateFormat formatter = new SimpleDateFormat(sFormat);
					try {
						return formatter.parse("1970-01-01 "+date+":00");
					}
					catch (Exception e) {
					}
				}
		}
		return null;
	}
	/**
	 * ���ݸ�ʽ��ȡ��һ���µĵ�ǰʱ���ǰһ��ʱ��
	 * @param sFormat ��ʽ
	 */
	public static Date getLastDay(Date date)
	{
		if (date == null)
			return null;

		return dateAdd(3, -1, dateAdd(2, 1, date));
	}

	/**
	 * ���ݸ�ʽ��ȡ����ʽ��ʾ�ĵ�ǰ����
	 * @param sFormat ��ʽ
	 */
	public static String getFormatDate(String sFormat)
	{
		return getFormatDate(sFormat, getCurrent());
	}
	/**
	 * Ϊ������������ӻ��ȥָ����ʱ����
	 * @param iField �����Ĺ���(1Ϊ������,2Ϊ������,3Ϊ������,10Ϊ����Сʱ,11Ϊһ���е�Сʱ,12Ϊ��,13Ϊ��)
	 * @param iValue ʱ����
	 * @param date ����
	 */
	public static Date dateAdd(int iField, int iValue, Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		switch (iField) { case 1:
			cal.add(cal.YEAR, iValue);
			break;
		case 2:
			cal.add(cal.MONTH, iValue);
			break;
		case 3:
			cal.add(cal.DATE, iValue);
			break;
		case 10:
			cal.add(cal.HOUR, iValue);
			break;
		case 11:
			cal.add(cal.HOUR_OF_DAY, iValue);
			break;
		case 12:
			cal.add(cal.MINUTE, iValue);
			break;
		case 13:
			cal.add(cal.SECOND, iValue);
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9: } return cal.getTime();
	}
	/**
	 * ���ݹ���õ�������������֮ǰ����ʱ��
	 * @param iField �����Ĺ���(1Ϊ������,2Ϊ������,3Ϊ������,10Ϊ����Сʱ,11Ϊһ���е�Сʱ,12Ϊ��,13Ϊ��)
	 * @param startDate ��ʼʱ��
	 * @param endDate ����ʱ��
	 */
	public static long dateDiff(int iField, Date startDate, Date endDate)
	{
		if(null==startDate||null==endDate){
			return 0;
		}
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();

		int startYear = Integer.parseInt(getFormatDate("yyyy", startDate));
		int endYear = Integer.parseInt(getFormatDate("yyyy", endDate));
		int startMonth = Integer.parseInt(getFormatDate("MM", startDate)) - 1;
		int endMonth = Integer.parseInt(getFormatDate("MM", endDate)) - 1;
		int startDay = Integer.parseInt(getFormatDate("dd", startDate));
		int endDay = Integer.parseInt(getFormatDate("dd", endDate));
		int startHour = Integer.parseInt(getFormatDate("HH", startDate));
		int endHour = Integer.parseInt(getFormatDate("HH", endDate));
		int startMinute = Integer.parseInt(getFormatDate("mm", startDate));
		int endMinute = Integer.parseInt(getFormatDate("mm", endDate));
		int startSecond = Integer.parseInt(getFormatDate("ss", startDate));
		int endSecond = Integer.parseInt(getFormatDate("ss", endDate));
		switch (iField) { case 1:
			return (endYear - startYear);
		case 2:
			long yearDiff = endYear - startYear;
			long monthDiff = endMonth - startMonth;
			return (yearDiff * 12L + monthDiff);
		case 3:
			start.set(startYear, startMonth, startDay, 0, 0, 0);
			end.set(endYear, endMonth, endDay, 0, 0, 0);
			return ((end.getTimeInMillis() - start.getTimeInMillis()) / 
					86400000L);
		case 10:
			start.set(startYear, startMonth, startDay, startHour, 0, 0);
			end.set(endYear, endMonth, endDay, endHour, 0, 0);
			return ((end.getTimeInMillis() - start.getTimeInMillis()) / 
					3600000L);
		case 11:
			start.set(startYear, startMonth, startDay, startHour, 0, 0);
			end.set(endYear, endMonth, endDay, endHour, 0, 0);
			return ((end.getTimeInMillis() - start.getTimeInMillis()) / 
					3600000L);
		case 12:
			start.set(startYear, startMonth, startDay, startHour, startMinute, 
					0);
			end.set(endYear, endMonth, endDay, endHour, endMinute, 0);
			return ((end.getTimeInMillis() - start.getTimeInMillis()) / 
					60000L);
		case 13:
			start.set(startYear, startMonth, startDay, startHour, startMinute, 
					startSecond);
			end.set(endYear, endMonth, endDay, endHour, endMinute, endSecond);
			return ((end.getTimeInMillis() - start.getTimeInMillis()) / 
					1000L);
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9: } return (end.getTimeInMillis() - start.getTimeInMillis());
	}
	/**
	 * Ϊ��ǰ������ӻ��ȥָ����ʱ����
	 * @param iField �����Ĺ���(1Ϊ������,2Ϊ������,3Ϊ������,10Ϊ����Сʱ,11Ϊһ���е�Сʱ,12Ϊ��,13Ϊ��)
	 * @param iValue ʱ����
	 */
	public static Date dateAdd(int iField, int iValue)
	{
		return dateAdd(iField, iValue, getCurrent());
	}

	/**
	 * �������ڻ�ȡ��yyyyMMdd��ʽ��ʾ������
	 * @param date    ����
	 */
	public static Date dateTrunc(Date date)
	{
		return getDate("yyyyMMdd", getFormatDate(
				"yyyyMMdd", date));
	}
	/**
	 * �����������ڵ����ж�����
	 * @param date    ����
	 */
	public static long getMonthDayCount(Date date)
	{
		Date start = getMonthFirstDay(date);
		Date end = getMonthEndDay(date);
		return (dateDiff(3, start, end) + 1L);
	}
	/**
	 * �������ͺ����ڻ�ȡ�����ǵڼ�������
	 * @param type    ����(1Ϊ����������ݵĵڼ�������,2Ϊ���������·ݵĵڼ�������)
	 * @param date    ����
	 */
	public static int getWeekNum(int type, Date date)
	{
		Date monthenddate = getMonthEndDay(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(monthenddate);

		if (type == 1)
		{
			return cal.get(3);
		}
		if (type == 2)
		{
			return cal.get(4);
		}

		return 0;
	}
	/**
	 * ����Сʱ,����,��,��,��,���ȡ��ǰ����
	 * @param hour Сʱ
	 * @param minute ����
	 * @param second ��
	 * @param month ��
	 * @param day ��
	 * @param year ��
	 */
	public static Date mktime(int hour, int minute, int second, int month, int day, int year)
	{
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day, hour, minute, second);
		return cal.getTime();
	}
	/**
	 * ��ȡָ�����ڵ�ʱ���
	 * @param date    ����
	 */
	public static Timestamp getTimestamp(Date date)
	{
		return new Timestamp(date.getTime());
	}
	/**
	 * ��ȡָ�����ڵ�ʱ���
	 * @param date    ����
	 */
	public static Date getDateFormTimestamp(Timestamp timestamp)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return df.parse(df.format(timestamp));
		} catch (Exception e) {
		}
		return null;
	}
	/**
	 * ��ȡ��ǰ���ڵ�ʱ���
	 */
	public static Timestamp getCurrentDateTimestamp()
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp createDttm = Timestamp.valueOf(df
				.format(new Date()));
		return createDttm;
	}

	/**
	 * ��ȡ������������֮ǰ��������,Сʱ,����
	 * @param startDate ��ʼʱ��
	 * @param endDate ����ʱ��
	 */
	public static long[] dateDiffEx(Date startDate, Date endDate)
	{
		long[] diffTime = new long[3];

		long minuteDiff = 0L;
		long hourDiff = 0L;
		long dayDiff = 0L;

		long diff = dateDiff(12, startDate, endDate);

		if (diff > 0L)
		{
			minuteDiff = diff % 60L;
			diff /= 60L;
		}

		if (diff > 0L)
		{
			hourDiff = diff % 24L;
			diff /= 24L;
		}

		if (diff > 0L)
		{
			dayDiff = diff;
		}

		diffTime[0] = dayDiff;
		diffTime[1] = hourDiff;
		diffTime[2] = minuteDiff;

		return diffTime;
	}

	/**
	 * ��ȡ������������֮ǰ������,��,��,ʱ,��,��
	 * @param startDate ��ʼʱ��
	 * @param endDate ����ʱ��
	 */
	public static int[] getTimeDiff(Date startTime, Date endTime)
	{
		int[] ret = new int[6];
		if ((startTime == null) || (endTime == null))
			return null;
		int syear = 0;
		if (getFormatDate("yyyy", startTime) != null)
			syear = Integer.parseInt(getFormatDate("yyyy", 
					startTime));
		int eyear = 0;
		if (getFormatDate("yyyy", endTime) != null)
			eyear = Integer.parseInt(getFormatDate("yyyy", 
					endTime));

		int smonth = 0;
		if (getFormatDate("MM", startTime) != null)
			smonth = Integer.parseInt(getFormatDate("MM", 
					startTime));
		int emonth = 0;
		if (getFormatDate("MM", endTime) != null)
			emonth = Integer.parseInt(getFormatDate("MM", 
					endTime));
		int sday = 0;
		if (getFormatDate("dd", startTime) != null)
			sday = Integer.parseInt(getFormatDate("dd", 
					startTime));
		int eday = 0;
		if (getFormatDate("dd", endTime) != null)
			eday = Integer.parseInt(
					getFormatDate("dd", endTime));
		int shour = 0;
		if (getFormatDate("HH", startTime) != null)
			shour = Integer.parseInt(getFormatDate("HH", 
					startTime));
		int ehour = 0;
		if (getFormatDate("HH", endTime) != null)
			ehour = Integer.parseInt(getFormatDate("HH", 
					endTime));
		int sminute = 0;
		if (getFormatDate("mm", startTime) != null)
			sminute = Integer.parseInt(getFormatDate("mm", startTime));
		int eminute = 0;
		if (getFormatDate("mm", endTime) != null)
			eminute = Integer.parseInt(getFormatDate("mm", endTime));

		int ssecond = 0;
		if (getFormatDate("ss", endTime) != null)
			ssecond = Integer.parseInt(getFormatDate("ss", startTime));
		int esecond = 0;
		if (getFormatDate("ss", endTime) != null)
			esecond = Integer.parseInt(getFormatDate("ss", endTime));

		int secondDif = esecond - ssecond;

		int minuteDif = eminute - sminute;

		int hourDif = ehour - shour;

		int dayDif = eday - sday;

		int monthDif = emonth - smonth;

		int yearDif = eyear - syear;

		if (secondDif < 0) {
			secondDif += 60;
			--minuteDif;
		}
		if (minuteDif < 0) {
			minuteDif += 60;
			--hourDif;
		}
		if (hourDif < 0) {
			hourDif += 24;
			--dayDif;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endTime);
		int days = calendar.getMaximum(5);

		if (dayDif < 0) {
			dayDif += days;
			--monthDif;
		}
		if (monthDif < 0) {
			monthDif += 12;
			--yearDif;
		}

		ret[0] = yearDif;
		ret[1] = monthDif;
		ret[2] = dayDif;
		ret[3] = hourDif;
		ret[4] = minuteDif;
		ret[5] = secondDif;

		return ret;
	}
	/**
	 * ���ݸ�����ʽ�ͻ�ȡ�������ڵ�����
	 * @param sFormat ��ʽ
	 * @param date    ����
	 */
	public static String getWeekday(String sFormat, String date)
	{
		Date datetime = getDate(sFormat, date);

		return getWeekday(datetime);
	}
	/**
	 * ��ȡ�������ڵ�����
	 * @param date    ����
	 */
	public static String getWeekday(Date date)
	{
		String week = "";

		if (date != null) {
			int day = date.getDay();
			switch (day)
			{
			case 1:
				week = "����һ";
				break;
			case 2:
				week = "���ڶ�";
				break;
			case 3:
				week = "������";
				break;
			case 4:
				week = "������";
				break;
			case 5:
				week = "������";
				break;
			case 6:
				week = "������";
				break;
			case 0:
				week = "������";
			}

		}

		return week;
	}
	
	/**
	 * �ж��Ƿ�Ϊ������������
	 * @param date
	 * @return
	 */
	public static boolean isWeekDay(Date date){
		Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)
	    {
	    	return true;
	    }
	    return false;
	}
	
	
	/**
	 * �ж��Ƿ�Ϊ����
	 * @param date
	 * @return
	 */
	public static boolean isSaturday(Date date){
		Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY)
	    {
	    	return true;
	    }
	    return false;
	}
	
	/**
	 * �ж��Ƿ�Ϊ����
	 * @param date
	 * @return
	 */
	public static boolean isSunday(Date date){
		Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)
	    {
	    	return true;
	    }
	    return false;
	}
	
	
	/**
	 * ���ݸ�����ʽ�����ڻ�ȡ����ʽ��ʾ�������ַ���
	 * @param sFormat ��ʽ
	 * @param date    ����
	 */
	public static String getStringDate(String sFormat, Date date)
	{
		if ((date == null)) {
			return null;
		}

		String year = getFormatDate("yyyy", date);

		String month = getFormatDate("MM", date);

		String day = getFormatDate("dd", date);

		if ("DYYYYMMDD".equals(sFormat))
			return year + "." + month + "." + day;

		if ("DYYYYMM".equals(sFormat))
			return year + "." + month;

		return "";
	}
	/**
	 * ����ָ����������������
	 * @param month
	 * @return
	 */
	public static String parseMonthNumber(int month){
		switch(month){
		case 1:
			return "һ";
		case 2:
			return "��";
		case 3:
			return "��";
		case 4:
			return "��";
		case 5:
			return "��";
		case 6:
			return "��";
		case 7:
			return "��";
		case 8:
			return "��";
		case 9:
			return "��";
		case 10:
			return "ʮ";
		case 11:
			return "ʮһ";
		case 12:
			return "ʮ��";

		}
		return "";
	}

	/**
	 * �ж������Ƿ���yyyy-MM-dd�ĸ�ʽ
	 * @param dateString
	 * @return
	 */
	public static boolean validate(String dateString){
		//ʹ��������ʽ ���� �ַ� ���� dddd-dd-dd �ĸ�ʽ(d��ʾ����)
		Pattern p = Pattern.compile("\\d{4}+[-]\\d{1,2}+[-]\\d{1,2}+");
		Matcher m = p.matcher(dateString);
		if(!m.matches()){	return false;} 

		//�õ�������
		String[] array = dateString.split("-");
		int year = DoNumberUtil.intNullDowith(array[0]);
		int month = DoNumberUtil.intNullDowith(array[1]);
		int day = DoNumberUtil.intNullDowith(array[2]);

		if(month<1 || month>12){	return false;}
		int[] monthLengths = new int[]{0, 31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		if(isLeapYear(year)){
			monthLengths[2] = 29;
		}else{
			monthLengths[2] = 28;
		}
		int monthLength = monthLengths[month];
		if(day<1 || day>monthLength){
			return false;	
		}
		return true;
	}

	/** �Ƿ������� */
	public static boolean isLeapYear(int year){
		return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) ;
	}
	public static void main(String args[]){
		//for test
	}
	/**
	 * ���ڸ�ʽ��
	 * @param date
	 * @return
	 */
	public static String getDateFormat(Date date){
		long second = dateDiff(13,date,new Date());
		if(second<60){
			if(second <1){
				second =1;
			}
			return second +"��ǰ";
		}else{
			long minute = (long)(second/60);
			if(minute<60){
				return minute +"����ǰ";
			}else{
				if(dateDiff(13,date,getDate("yyyy/MM/dd HH:mm:ss", getDayStartStr(new Date())))<0){
					return "����"+getFormatDate("HH:mm", date);
				}else{
					return getFormatDate("M��d��", date)+" "+getFormatDate("HH:mm", date);
				}
			}
		}
	}
	/**
	 * ���ڸ�ʽ��
	 * @param date
	 * @return
	 */
	public static String getDayFormat(String day){
		int days = 0;
		if(!BaseUtil.isSpace(day)){
			days = DoNumberUtil.intNullDowith(day);
		}
		if(days>=24*60){
			int dayI = days/24/60;
			String returnStr = dayI +"��";
			int diff = days - dayI*24*60;
			if(diff>0){
				int hour = diff/60;
				if(hour>0){
					returnStr+=hour +"Сʱ";
				}
			}
			return returnStr;
		}else{
			if(days>=60){
				int hour = days/60;
				String returnStr = hour +"Сʱ";
				if((days-hour*60)>0){
					returnStr+=(days-hour*60)+"����";
				}
				return returnStr;
			}else{
				return days +"����";
			}
		}
	}
	/**
	 * ���ڸ�ʽ��

		return day;	 * @param date
	 * @return
	 */
	public static String getDateFormat(Timestamp timestamp){
		Date date = getDate("yyyy-MM-dd HH:mm:ss",getFormatDate("yyyy-MM-dd HH:mm:ss",timestamp));
		long second=dateDiff(13,date,new Date());
		if(second<60){
			if(second <1){
				second =1;
			}
			return second +"��ǰ";
		}else{
			long minute = (long)(second/60);
			if(minute<60){
				return minute +"����ǰ";
			}else{
				if(dateDiff(13,date,getDate("yyyy/MM/dd HH:mm:ss", getDayStartStr(new Date())))<0){
					return "����"+getFormatDate("HH:mm", date);
				}else{
					return getFormatDate("M��d��", date)+" "+getFormatDate("HH:mm", date);
				}
			}
		}
	}
	/**
	 * ���ڸ�ʽ��
	 * @param date
	 * @param format ����3������ڸ�ʽ
	 * @return
	 */
	public static String getDateFormatDiary(Date date,String format){
		String dateStr = getFormatDate("yyyy-MM-dd", date);
		long day=dateDiff(3,date,new Date());
		if(dateStr.equals(getToday())){
			return "����";
		}
		if(dateStr.equals(getYesterday())){
			return "����";
		}
		if(dateStr.equals(getBeforeYesterday())){
			return "ǰ��";
		}
		return DateUtil.getFormatDate(format, date);
	}



	/**
	 * ��ȡ�������ڼ�
	 */
	public static String getJinTianWeekCN(){
		Calendar calendar = Calendar.getInstance();
		return getWeekDayCN(calendar);
	}

	//�õ����ڼ�
	private static String getWeekDayCN(Calendar c ){

		int whichDay = c.get(Calendar.DAY_OF_WEEK); 

		if(Calendar.MONDAY == whichDay)		
			return "����һ" ;

		if(Calendar.TUESDAY == whichDay)	
			return "���ڶ�" ;

		if(Calendar.WEDNESDAY == whichDay)	
			return "������" ;

		if(Calendar.THURSDAY == whichDay)	
			return "������" ;

		if(Calendar.FRIDAY == whichDay)		
			return "������";

		if(Calendar.SATURDAY == whichDay)	
			return  "������";

		if(Calendar.SUNDAY == whichDay)		
			return "������";


		return "����һ" ;

	}

	/**��������ܼ�*/
	public static String index2Week(int index){
		switch (index) {
		case 0:
			return "һ";
		case 1:
			return "��";
		case 2:
			return "��";
		case 3:
			return "��";
		case 4:
			return "��";
		case 5:
			return "��";
		case 6:
			return "��";

		default:
			break;
		}

		return "";
	}
//-----------------------�ָ���,�Ժ������ķ�������----by:zzz-------------
	
	/**
	 * ��ȡ����0��0��0���Date,
	 * @return
	 */
	public static Date getTodayDate(){
		return getDate("yyyy-MM-dd", getToday());
	}
	
	/**
	 * ��ȡ����0��0��0���Date
	 * @param date
	 * @return
	 */
	public static Date getYesterdayDate(){
		return getDate("yyyy-MM-dd", getYesterday());
	}
	
	/**
	 * ��ȡǰ��0��0��0���Date
	 * @param date
	 * @return
	 */
	public static Date getQianDate(){
		return getDate("yyyy-MM-dd", getBeforeYesterday());
	}
	
	/**
	 * �жϸ����ǲ�����ĳ
	 * @return
	 */
	public static boolean isTodayWeekend(){ 
		int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		return dayOfWeek == 1 || dayOfWeek == 7;
	}
	
	/*
	 * ���������͵�����ȡ��ʱ���ַ���
	 * ����2012-1-2 12:32:00 ���� 12:32 
	 * */
	public static String getTimeFromDate(Date date)
	{
//		Calendar c = Calendar.getInstance();
//		int year = c.get(Calendar.YEAR);
//		int month = c.get(Calendar.MONTH)+1;
//		int day = c.get(Calendar.DAY_OF_MONTH);
		
		int hour = date.getHours();
		int minute = date.getMinutes();
		
		String currentTime=BaseUtil.getFormatStringFromInt(hour,2)+":"+
		BaseUtil.getFormatStringFromInt(minute,2);
		
		return currentTime;
	}
	
	//����Ϣת���ַ���
			public static String weekInfoIndex2Str(String indexStr){
				if(indexStr == null || "".equals(indexStr)) return ""; //����δ����
				String[] indexs = indexStr.split(",");
				if(indexs.length == 7 ){
					return "ÿ��";
				}
				StringBuffer sb = new StringBuffer();
				for (int i= 0 ;i != indexs.length ;i++) {
					String index = indexs[i];
					int num = DoNumberUtil.intNullDowith(index);
					sb.append(index2Week(num));
					if(i != indexs.length - 1){
						sb.append('��');
					}
				}
				return sb.toString();
			}
	
	
	/**
	 * ���� ����켸��
	 * @param date
	 * @return
	 */
	public static int numTheDate2Today(Date date){
		return (int) ((getTodayDate().getTime() - date.getTime()) / DateUtil.ONE_DAY);
	}
	/**
	 *  ��ȡָ����������ָ��������ݵĵڼ���  ����2013-01-01���صľ���1��ʾ�ǵ�һ��
	 * @param date
	 * @return int �ڼ���    
	 */
	public static int getWeek(Date date) {
		  Calendar g = Calendar.getInstance();
		  g.setTime(date);
		  return g.get(Calendar.WEEK_OF_YEAR);//�������
	}
	/**
	 *  ��ȡָ�����ڵ����
	 * @param date
	 * @return int ����2013   
	 */
	public static int getYear(Date date) {
		  Calendar g = Calendar.getInstance();
		  g.setTime(date);
		  return g.get(Calendar.YEAR);//������
	}
	
	/**
	 * ���ⷽ�� ��Ϊ�ܼ�ֻ������ ���� ��һ��¼  ������¼�ĵ��ܵ�  ���� ��һ��¼�������ܵ�  
	 * ���Ծͽ����ڼ�ȥ4�� 
	 *  ��ȡָ�����ڵ����+��ǰ���ڽ�������� ����2013�ĵ�2�ܾ��� 20132  &&    2013�ĵ�10�ܾ���201310
	 * @param date
	 * @return int ����201310   
	 */
	public static String getYAndW(Date date) {
		date = DateUtil.dateAdd(3, -4, date);
		int year = getYear(date);
		int week = getWeek(date);
		String yandw = DoNumberUtil.IntToStr(year)+DoNumberUtil.IntToStr(week);
		return yandw;
	}
	
	/**
	 *  ��ȡ��ǰ�·�
	 * @param date
	 * @return int ����10  
	 */
	public static int getMonth() {
		  Calendar g = Calendar.getInstance();
		  return g.get(Calendar.MONTH)+1;//��õ�ǰ�·�
	}
	
	/**
	 *  ��ȡ��ǰ����   ���ٺ�  
	 * @param date
	 * @return int ����21  
	 */
	public static int getDay() {
		  Calendar g = Calendar.getInstance();
		  return g.get(Calendar.DAY_OF_MONTH);//��õ�ǰ����
	}
}
