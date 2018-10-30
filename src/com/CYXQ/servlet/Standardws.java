package com.CYXQ.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//规范化要显示的语义类
public class Standardws {
	String temp = null;
	String wcode = null;//存储词义编码
	String wtype = null;//存储特定编码下语义类
	List res = new ArrayList();//返回拆分好的词语
	int wnum = 0;
	int windex = 0;
	public List settle(int num , String words){
		temp = words;
		while((windex = temp.indexOf('(')) != -1){
			Map map = new HashMap();
			wtype = temp.substring(0,windex);
			temp = temp.substring(windex+1);
			wcode = temp.substring(0,temp.indexOf(')'));
			temp = temp.substring(temp.indexOf(' ')+1);
//			System.out.println(wcode+"___"+wtype);
			wtype = wtype.replaceAll("_", "&nbsp;&nbsp;&nbsp;");
			map.put("num", num);
			map.put("wcode", wcode);
			map.put("wtype", wtype);
			res.add(map);
			wnum++;
		}
//		temp = temp.substring(1);
//		temp = temp.replace('_', '/');
//		temp = temp.replaceAll("\\([^)]*\\)","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
//		System.out.println("Standardws"+res);
		return res;
	}
	public int getNum(){
		return wnum;
	}
	public void setNum(){
		wnum=0;
	}
}
