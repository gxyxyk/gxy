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
//		System.out.print("������һ���ַ�����");
//		String result = input.next();
//		List<String> list = new ArrayList<String>();
//		for (int i = 0; i < result.length(); i++) {
//			if(result.charAt(i)=='0'){
//				list.add("��");
//			}else if(result.charAt(i)=='1'){
//				list.add("һ");
//			}else if(result.charAt(i)=='2'){
//				list.add("��");
//			}else if(result.charAt(i)=='3'){
//				list.add("��");
//			}else if(result.charAt(i)=='4'){
//				list.add("��");
//			}else if(result.charAt(i)=='5'){
//				list.add("��");
//			}else if(result.charAt(i)=='6'){
//				list.add("��");
//			}else if(result.charAt(i)=='7'){
//				list.add("��");
//			}else if(result.charAt(i)=='8'){
//				list.add("��");
//			}else if(result.charAt(i)=='9'){
//				list.add("��");
//			}else{
//				System.out.println("�쳣��");
//				return;
//			}
//		}
//		StringBuilder stringBuilder = new StringBuilder();
//		for (String r:list) {
//			stringBuilder.append(r);
//		}
//		System.out.println("result�Ľ����"+stringBuilder);
//		System.out.println("���1��+2����20���ĺ�");
//		int count = 0;
//		for (int i = 1; i <= 20; i++) {
//			int zhongjian = 1;
//			for (int j = 1; j <=i; j++) {
//				zhongjian = zhongjian*j;
//			}
//			count = zhongjian+count;
//		}
//		System.out.println("���ֵ��"+count);
//		java�л�ȡ�����ʱ�̵�ʱ��ؼ���JDK 8ǰ Calendar��add����ǰʱ�䣩�������ȡ
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		System.out.println(calendar.getTime());
//		jDk8��ȡ����ʱ��ķ���������
		System.out.println("����һ��");
		System.out.println("");
	}
}
