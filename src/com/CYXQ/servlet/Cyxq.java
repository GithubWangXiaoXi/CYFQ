package com.CYXQ.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.CYXQ.Participle.core.*;
import com.CYXQ.Bayes.*;
import com.CYXQ.TreeShow.*;

import static com.wangxiaoxi1.code_0004_cupusFileHandler.getWsdTemp;
import static com.wangxiaoxi1.code_0004_cupusFileHandler.wsdFeit;

public class Cyxq extends HttpServlet {

	String Sentence = null;

	List fcresultlist = new  ArrayList();//存放最终结果集
	List yylresultlist = new ArrayList();//存放最终语义类结果
	List fclist = new ArrayList();//存放分完词后的词语
	private static List<String> cilinList = new ArrayList<String>();//以行为单位读取词林
	private List<String> threeCode = new ArrayList<String>();
	private String threeCodeTemp = "";

	/**
	 * Constructor of the object.
	 */
	public Cyxq() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 *
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 *
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		if(cilinList==null || cilinList.size() <= 0){
			/*************************同义词词林读取***************/

			String temp = null;//存放每行数据
//			File file = new File("D:/THES.TXT");
			String fileName = "D:/THES.TXT";
			System.out.println(System.getProperty("THES.TXT"));
			BufferedReader  reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"gbk"));
			int count = 100;
			while((temp = reader.readLine()) != null){
//				 temp = new String(temp.getBytes(),0,temp.length(),"gbk");
				 if(count > 0){
					 System.out.println(temp);
					 count--;
				 }
				 cilinList.add(temp);
			}
		}

		//消歧模块处理
		if(request.getParameter("XQ1") == null && request.getParameter("XQ2") != null){
			List wsdRes = new ArrayList();
			String wsdTemp = getWsdTemp();
			System.out.println("第二张表");
			String[] values = request.getParameterValues("Ambiguity");
			TreeItemNormal tree = new TreeItemNormal();
			if(fclist != null && fclist.size() > 0){
				String[] fcTemp = new String[fclist.size()];
				for(int j = 0;j < fclist.size(); j++){
					Map fcMap = new HashMap();
					fcMap = (Map)fclist.get(j);
					fcTemp[j] = fcMap.get("text").toString();
				}
				threeCode.clear();
				for(int i = 0;i < values.length;i++){
					Map wsdMap = new HashMap();
					if(wsdTemp.contains(values[i])){//传入参数包含在语料库中的歧义词中
						wsdMap.put("word", values[i]);
						System.out.println(values[i] + fcTemp);
//						threeCodeTemp = Wsd.wsd(values[i],fcTemp);

						String str = wsdFeit(values[i],fcTemp);
						threeCodeTemp = str;

						threeCodeTemp = threeCodeTemp.substring(threeCodeTemp.indexOf("(")+1, threeCodeTemp.indexOf(")"));
//						System.out.println("three:"+threeCode);
						threeCode.add(threeCodeTemp);

						//通过将目标值和句子传到wsd消歧的方法中，得到最终消歧的字符串
//						wsdMap.put("trueEman", Wsd.wsd(values[i],fcTemp));

						System.out.println(str);
						wsdMap.put("trueEman", str);
					}else{//传入参数未包含在语料库中的歧义词中
						wsdMap.put("word", values[i]);
						wsdMap.put("trueEman", "无");
					}
					System.out.println(values[i]);
					wsdRes.add(wsdMap);
				}
				//输出所有目标词的最终词义的三层代码
				System.out.println("芮姐："+threeCode);
				System.out.println(tree.normal(cilinList, threeCode));
				if(threeCode != null && threeCode.size() > 0){
				    request.setAttribute("treeList",tree.normal(cilinList, threeCode));
				}
				request.setAttribute("wsd",wsdRes);
			}
			System.out.println(Sentence);
		}
		//分词模块处理
		else if(request.getParameter("XQ2")==null&&request.getParameter("XQ1")!=null){

			Sentence = null;
			fcresultlist.clear();
			yylresultlist.clear();
			fclist.clear();

			Sentence = request.getParameter("word");

			System.out.println(Sentence);

			int cllistlength = 0;//词林list长度
			int fclistlength = 0;//分词后词语长度
//			String resultList[] = null;//存放同义词结果集


		    cllistlength = cilinList.size();

		    /******************分词*********************/
		    StringReader re = new StringReader(Sentence);
			IKSegmenter ik = new IKSegmenter(re,true);
			Lexeme lex = null;
			while((lex=ik.next())!=null){
				Map mapfc = new HashMap();
				mapfc.put("text", lex.getLexemeText());
				fclist.add(mapfc);
//			   System.out.print(lex.getLexemeText()+"|");
			}
			fclistlength = fclist.size();
//			resultList = new String[fclistlength];
			/*******************去重***********/
			//有待完善

			/**************进行词林查询并将查询结果规格化****************/
			int lose = 0;//当词林中不存在该词时标记
			String tempCLC = null;//处理词林串
			Standardws sws = new Standardws();//规范化语义类字符串

			for(int i=0;i<fclistlength;i++){
				lose = 0;
				String temp1 = ((Map)fclist.get(i)).get("text").toString();
				for(int j = 0; j < cllistlength; j++){
					String temp2 = cilinList.get(j);
					if(temp2.startsWith("#"+temp1)){
						List wordsType = new ArrayList();//存储特定词性编码下的词语
						Map res1 = new HashMap();
						res1.put("diffword", temp1);
						tempCLC = temp2.substring(temp2.indexOf("/")+1);
						wordsType = sws.settle(i+1,tempCLC);
						res1.put("num", sws.getNum());
						sws.setNum();
						fcresultlist.add(res1);
						yylresultlist.addAll(wordsType);
						wordsType.clear();
						lose = 1;
						break;
					}
				}
				if(lose == 0){
					Map res2 = new HashMap();
					res2.put("diffword", temp1);
					res2.put("num",0);
					fcresultlist.add(res2);
//					resultList[i] = "#"+temp1+"/";
				}
//				System.out.println(resultList[i]);
			}

			System.out.println("res:"+fcresultlist);
			System.out.println("res:"+yylresultlist);
//			cilinList.clear();

			System.out.println("第一张表");
		}

		request.setAttribute("word", Sentence);
		request.setAttribute("fclist", fcresultlist);
		request.setAttribute("yyllist", yylresultlist);

		request.getRequestDispatcher("/index.jsp").forward(request, response);


	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
