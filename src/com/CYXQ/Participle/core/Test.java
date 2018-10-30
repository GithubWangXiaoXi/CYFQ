package com.CYXQ.Participle.core;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Test {
	public static void main(String[] arg) throws IOException{
		String Sentence = "我是何山";
		StringReader re = new StringReader(Sentence);
		IKSegmenter ik = new IKSegmenter(re,true);
		Lexeme lex = null;
		List fclist = new ArrayList();//存放分完词后的词语
		String[] temp;
		while((lex=ik.next())!=null){
			Map mapfc = new HashMap();
			System.out.println(lex.getLexemeText());
//			mapfc.put("text", lex.getLexemeText());
//			fclist.add(mapfc);	    
		}
	}

}
