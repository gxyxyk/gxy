package com;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Student {
	public static void main(String[] args) {
//		Scanner input = new Scanner(System.in);
//		System.out.print("请输入一个字符串：");
//		String result = input.next();
//		List<String> list = new ArrayList<String>();
//		for (int i = 0; i < result.length(); i++) {
//			if(result.charAt(i)=='0'){
//				list.add("零");
//			}else if(result.charAt(i)=='1'){
//				list.add("一");
//			}else if(result.charAt(i)=='2'){
//				list.add("二");
//			}else if(result.charAt(i)=='3'){
//				list.add("三");
//			}else if(result.charAt(i)=='4'){
//				list.add("四");
//			}else if(result.charAt(i)=='5'){
//				list.add("五");
//			}else if(result.charAt(i)=='6'){
//				list.add("六");
//			}else if(result.charAt(i)=='7'){
//				list.add("七");
//			}else if(result.charAt(i)=='8'){
//				list.add("八");
//			}else if(result.charAt(i)=='9'){
//				list.add("九");
//			}else{
//				System.out.println("异常！");
//				return;
//			}
//		}
//		StringBuilder stringBuilder = new StringBuilder();
//		for (String r:list) {
//			stringBuilder.append(r);
//		}
//		System.out.println("result的结果："+stringBuilder);
//		System.out.println("求第1！+2！到20！的和");
//		int count = 0;
//		for (int i = 1; i <= 20; i++) {
//			int zhongjian = 1;
//			for (int j = 1; j <=i; j++) {
//				zhongjian = zhongjian*j;
//			}
//			count = zhongjian+count;
//		}
//		System.out.println("求得值是"+count);
//		java中获取明天此时刻的时间关键字JDK 8前 Calendar。add（当前时间）方法句获取
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		System.out.println(calendar.getTime());
//		jDk8获取明天时间的方法有两种
		System.out.println("方法一：");
		System.out.println("");
	}
}
