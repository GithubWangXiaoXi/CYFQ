package com.CYXQ.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.CYXQ.Participle.core.*;
import com.CYXQ.Bayes.*;
import com.CYXQ.TreeShow.*;

public class test {
	public static void main(String[] args) throws IOException{
//		Standardws sws = new Standardws();
//		System.out.println(sws.settle("曾祖_祖父_祖母(Ah02) 父_母_父母_父子(Ah08)"));
//		System.out.println(sws.getNum());
//		String threeCodeTemp = "Dj07";
//		String firstLayer;
//		String secondLayer;
//		String thirdLayer;
//		firstLayer = threeCodeTemp.substring(0,1);
//		secondLayer = threeCodeTemp.substring(1, 2);
//		thirdLayer = threeCodeTemp.substring(2, 4);
//		System.out.println(firstLayer);
//		System.out.println(secondLayer);
//		System.out.println(thirdLayer);
		
		String temp = null;//存放每行数据
		int cllistlength = 0;//词林list长度
		int fclistlength = 0;//分词后词语长度
//		String resultList[] = null;//存放同义词结果集
		List<String> cilinList = new ArrayList<String>();//以行为单位读取词林
		List<String> code = new ArrayList<String>();//以行为单位读取词林
		
		
		/*************************同义词词林读取***************/
		File file = new File("D:/THES.TXT");
//		System.out.println(System.getProperty("THES.TXT"));
		BufferedReader  reader = new BufferedReader(new FileReader(file));
		while((temp = reader.readLine()) != null){
			 cilinList.add(temp);
		}
		
		code.add("Dj04");
		code.add("Dk20");
		TreeItemNormal tt = new TreeItemNormal();
		System.out.println(tt.normal(cilinList, code));
	}

}

//public class test {  
//public static void main(String[] args) {  
//// TODO Auto-generated method stub  
//String str = "111.3.22.11";  
//str=str.replaceAll("(^|\\.)(\\d)(\\.|$)","$100$2$3");  
//str=str.replaceAll("(^|\\.)(\\d{2})(\\.|$)","$10$2$3");  
//str=str.replaceAll("(^|\\.)(\\d{2})(\\.|$)","$10$2$3");  
//str=str.replaceAll("(^|\\.)(\\d{1})(\\.|$)","$100$2$3");  
//          
//System.out.println(str);  
//}  
//  
//}