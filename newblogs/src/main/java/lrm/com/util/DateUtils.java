package lrm.com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    //日期转换成字符串
    public static String date2String(Date date, String patt) {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        String format = sdf.format(date);
        System.out.println(format);
        return format;
    }

    //字符串转换成日期
    public static Date string2Date(String str, String patt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        Date parse = sdf.parse(str);
        return parse;
    }

    /**s'f'w
     * 获取前多少天的时间
     * @param frontday
     * @return
     */
    public static Date frontDate(int frontday){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, frontday);
        Date date = calendar.getTime();
        return date;
    }
}