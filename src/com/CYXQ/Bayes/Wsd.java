package com.CYXQ.Bayes;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过手动单个修改消歧单词名，对测试预料进行消歧
 * @category待消歧项名
 * 需要修改的地方包括环境控制变量和传入的参数两部分
 */
public class Wsd {
	//---------------------环境控制变量---------------------------------
	static String first_emantic;
	static String second_emantic ;
	static String third_emantic ;
	static int test_first_num ;
	static int test_second_num ;
	static int test_third;
	
	static int first_size;
	static int second_size ;
	static int third_size ;
	
	static String fileNameTrain;
	static String fileNameTest;
	static String targetWord ;
	static String fileNameresPrio;
	
	//----------------中间计算变量---------------------------------
	static double fc_1 = (double)1.0;
	static double fc_2 = (double)1.0;
	static double fc_3 = (double)1.0;
	
	static double danceng_1 = (double)1.0;
	static double danceng_2 = (double)1.0;
	static double danceng_3 = (double)1.0;
	
	static double sanceng_1 = (double)1.0;
	static double sanceng_2 = (double)1.0;
	static double sanceng_3 = (double)1.0;
	
	static double notExit_1 = (double)1.0;
	static double notExit_2 = (double)1.0;
	static double notExit_3 = (double)1.0;
	
	static int first_emantic_count = 0;
	static int second_emantic_count = 0;
	static int third_emantic_count = 0;
	
	private static String threeCode;

	public static String wsd(String target ,String[] sentence) throws IOException{
		String res = "";
//		List<String> fenciList = new ArrayList<String>();//用于存储分词的中间结果
//		List<String> dancengList = new ArrayList<String>();//用于存储单层的中间结果
//		List<String> sancengList = new ArrayList<String>();//用于存储三层的中间结果
		List<String> resPrio = new ArrayList<String>();
		Word word = new Word();
		String resPrioTemp = null; 
		int count = 0;
		double nullTemp = (double)1.0;
		boolean leftIsExit = false;
		boolean rightIsExit = false;

		/*EnvironmentVar给目标词的静态的环境变量赋值，得到目标词的所有语义，这时targetWord被赋值*/
		EnvironmentVar.getEnvirVar(target);
		GetTargetWord.setTargetWord(targetWord);

		/*
		这里该函数会直接或间接地依次调用
		     getFenCiBySpace 判断key的位置，给left，right赋值，注意考虑特殊情况，如果key，left，right为标点符号，则置为null    将分词存储到List<Word>中
		得到left,right，存在word对象中，并返回。处理输入的sentence结束。
		     readLinesFromFile 得到THIS.txt词义文件（字典，为了查询左右分词的语义，编码），以list的形式返回，list一共有73464个元素。
		     getSenceFromList 从同义词词林THIS.txt中提取词义，获取左右分词的词义和编码，左右分词有可能存在多个词义
		     getLeftAndRightByPercent 通过dice距离计算字符串的相似度，提取出最大可能性的左右分词
		*/
		word = GetTargetWord.getLeftAndRightByPercent(target,sentence);

		/*
		    加载目标词对应的文件 目标词.txt。resPrio文件夹下的 目标词.txt 是DDSC_Beat2.0文件夹下的 目标词.txt 的统计结果
		    /resPrio/目标词.txt的字段为：目标词的左右分词，次数，在文章中出现的概率（分词和第三层的概率是一致的，单层则对三层的头字母进行统计）
		    没有使用语料corpus文件，目标词.txt中字段组成分别为：左分词/右分词,单数/复数(权值),出现概率
		 */
		resPrio = GetTargetWord.readLinesFromFile(fileNameresPrio);

		/*
		    现在已经提取出目标词，左右分词，以及最大可能性的左右分词的词义，现在需要提取目标词的词义，实现消歧
		 */
		System.out.println(first_size);
		System.out.println(second_size);
		System.out.println(third_size);
		System.out.println("wordLeft " + word.getLeft());
		System.out.println("wordRight " + word.getRight());
		System.out.println("wordLeftMeaningTrueEng_3 " + word.getLeftMeaningTrueEng_3());
		System.out.println("wordRightMeaningTrueEng_3 " + word.getRightMeaningTrueEng_3());
		System.out.println("wordLeftMeaningTrueEng_1 " + word.getLeftMeaningTrueEng_1());
		System.out.println("wordRightMeaningTrueEng_1 " + word.getRightMeaningTrueEng_1());

		fc_1 = (double)1.0;
		fc_2 = (double)1.0;
		fc_3 = (double)1.0;
		
		danceng_1 = (double)1.0;
		danceng_2 = (double)1.0;
		danceng_3 = (double)1.0;
		
		sanceng_1 = (double)1.0;
		sanceng_2 = (double)1.0;
		sanceng_3 = (double)1.0;
		
		notExit_1 = (double)1.0;
		notExit_2 = (double)1.0;
		notExit_3 = (double)1.0;
		first_emantic_count = 0;
		second_emantic_count = 0;
		third_emantic_count = 0;
		/*
		  这里以key为成立为例：
		  private static String targetWordChengLi = "成立/v"; //目标词 歧义词
	      private static int first_size_ChengLi = 30; //第一个词个数
	      private static int second_size_ChengLi = 60;
	      private static int third_size_ChengLi = 73;
		 */

		//这些计算公式是什么意思，first_size,second_size,third_size分别代表什么?
		//难道first_size,second_size,third_size分别代表叶子，第二层，第三层，节点数随层数增多
		fc_1 = fc_1*((double)first_size/third_size);
		fc_2 = fc_2*((double)first_size/third_size);
		fc_3 = fc_3*((double)first_size/third_size);

		sanceng_1 = sanceng_1*((double)(second_size-first_size)/third_size);
		sanceng_2 = sanceng_2*((double)(second_size-first_size)/third_size);
		sanceng_3 = sanceng_3*((double)(second_size-first_size)/third_size);

		danceng_1 = danceng_1*((double)(third_size-second_size)/third_size);
		danceng_2 = danceng_2*((double)(third_size-second_size)/third_size);
		danceng_3 = danceng_3*((double)(third_size-second_size)/third_size);

		//平滑处理，公式λ／频率*kλ 此时k取歧义个数3，λ取1
		double tempnot1 = (double)((double)first_size/third_size);
		double tempnot2 = (double)((double)(second_size-first_size)/third_size);
		double tempnot3 = (double)((double)(third_size-second_size)/third_size);
		notExit_1 = (double)(tempnot1)/(third_size);
		notExit_2 = (double)(tempnot2)/(third_size);
		notExit_3 = (double)(tempnot3)/(third_size);
//		System.out.println(tempnot1);
//		System.out.println(tempnot2);
//		System.out.println(tempnot3);
		System.out.println(notExit_1);
		System.out.println(notExit_2);
		System.out.println(notExit_3);
		
//		System.out.println(" fc_1:"+fc_1);
		count = 0;
		/*遍历目标词对应的文件，目标词word的左分词，右分词，是否与每行的数据匹配，单数是左分词，复数为右分词*/
		for(int j=0;j<resPrio.size();j++){
			resPrioTemp = resPrio.get(j);
//			System.out.println(resPrioTemp);
//			System.out.println("leftword"+word.getLeft());
//			System.out.println("rightword"+word.getRight());

			/*
			  当resPrioTemp不包含'|'，count会自增，由于 目标词.文件 存在多个'-------'边界符，
			  自增的count表示对应的读的区域
			  */
			System.out.println(!resPrioTemp.contains("|"));
			if(!resPrioTemp.contains("|")){
				if(count > 0 ){
//					System.out.println(leftIsExit+"$$$$$$"+rightIsExit);
					if(leftIsExit == false && nullTemp != (double)1.0){
						if(!isPunctuation(word.getLeft())){
							if((count+1)%3 == 1){nullTemp = notExit_1;}
							else if((count+1)%3 == 2){nullTemp = notExit_2;}
							else if((count+1)%3 == 0){nullTemp = notExit_3;}
//							System.out.println("hello");
						}
						if(count == 1){fc_1 = fc_1*nullTemp;/*System.out.println("lefti:"+i+" fc_1:"+fc_1);*/}
						else if(count == 2){fc_2 = fc_2*nullTemp;/*System.out.println("lefti:"+i+" fc_2:"+fc_2);*/}
						else if(count == 3){fc_3 = fc_3*nullTemp;/*System.out.println("lefti:"+i+" fc_3:"+fc_3);*/}
						else if(count == 4){sanceng_1 = sanceng_1*nullTemp;/*System.out.println("lefti:"+i+" sanceng_1:"+sanceng_1);*/}
						else if(count == 5){sanceng_2 = sanceng_2*nullTemp;/*System.out.println("lefti:"+i+" sanceng_2:"+sanceng_2);*/}
						else if(count == 6){sanceng_3 = sanceng_3*nullTemp;/*System.out.println("lefti:"+i+" sanceng_3:"+sanceng_3);*/}
						else if(count == 7){danceng_1 = danceng_1*nullTemp;/*System.out.println("lefti:"+i+" danceng_1:"+danceng_1);*/}
						else if(count == 8){danceng_2 = danceng_2*nullTemp;/*System.out.println("lefti:"+i+" danceng_2:"+danceng_2);*/}
//						System.out.println("lefti:"+i+" count:"+count+"  nullTemp:"+nullTemp);
					}
					if(rightIsExit == false && nullTemp != (double)1.0){
						if(!isPunctuation(word.getRight())){
							if((count+1)%3 == 1){nullTemp = notExit_1;}
							else if((count+1)%3 == 2){nullTemp = notExit_2;}
							else if((count+1)%3 == 0){nullTemp = notExit_3;}
//							System.out.println("hello");
						}
						if(count == 1){fc_1 = fc_1*nullTemp;/*System.out.println("righti:"+i+" fc_1:"+fc_1);*/}
						else if(count == 2){fc_2 = fc_2*nullTemp;/*System.out.println("righti:"+i+" fc_2:"+fc_2);*/}
						else if(count == 3){fc_3 = fc_3*nullTemp;/*System.out.println("righti:"+i+" fc_3:"+fc_3);*/}
						else if(count == 4){sanceng_1 = sanceng_1*nullTemp;/*System.out.println("righti:"+i+" sanceng_1:"+sanceng_1);*/}
						else if(count == 5){sanceng_2 = sanceng_2*nullTemp;/*System.out.println("righti:"+i+" sanceng_2:"+sanceng_2);*/}
						else if(count == 6){sanceng_3 = sanceng_3*nullTemp;/*System.out.println("righti:"+i+" sanceng_3:"+sanceng_3);*/}
						else if(count == 7){danceng_1 = danceng_1*nullTemp;/*System.out.println("righti:"+i+" danceng_1:"+danceng_1);*/}
						else if(count == 8){danceng_2 = danceng_2*nullTemp;/*System.out.println("righti:"+i+" danceng_2:"+danceng_2);*/}
//						System.out.println("righti:"+i+" count:"+count+"  nullTemp:"+nullTemp);
					}
				}
				count++;
				leftIsExit = false;
				rightIsExit = false;
				nullTemp = (double)1.0;
//				System.out.println(resPrioTemp);
			}else if(resPrioTemp.startsWith("null")){
				nullTemp = Double.parseDouble(resPrioTemp.split("\\|")[2]);
			}else{
				if(count == 1){
					if(resPrioTemp.startsWith(word.getLeft())){
						fc_1 = fc_1*Double.parseDouble(resPrioTemp.split("\\|")[2]);
						System.out.println("命中left1 fc_1:"+fc_1);
						leftIsExit = true;
					}
					if(resPrioTemp.startsWith(word.getRight())){
						fc_1 = fc_1*Double.parseDouble(resPrioTemp.split("\\|")[2]);
						System.out.println("命中right1 fc_1:"+fc_1);
						rightIsExit = true;
					}
				}else if(count == 2){
//					System.out.println("hello2");
					if(resPrioTemp.startsWith(word.getLeft())){
						fc_2 = fc_2*Double.parseDouble(resPrioTemp.split("\\|")[2]);
						System.out.println("命中left2 fc_2:"+fc_2);
						leftIsExit = true;
					}
					if(resPrioTemp.startsWith(word.getRight())){
						fc_2 = fc_2*Double.parseDouble(resPrioTemp.split("\\|")[2]);
						System.out.println("命中right2 fc_2:"+fc_2);
						rightIsExit = true;
					}
				}else if(count == 3){
					if(resPrioTemp.startsWith(word.getLeft())){
						fc_3 = fc_3*Double.parseDouble(resPrioTemp.split("\\|")[2]);
						System.out.println("命中left3 fc_3:"+fc_3);
						leftIsExit = true;
					}
					if(resPrioTemp.startsWith(word.getRight())){
						fc_3 = fc_3*Double.parseDouble(resPrioTemp.split("\\|")[2]);
						System.out.println("命中right3 fc_3:"+fc_3);
						rightIsExit = true;
					}
				}else if(count == 4){
					if(resPrioTemp.startsWith(word.getLeftMeaningTrueEng_3())){
						sanceng_1 = sanceng_1*Double.parseDouble(resPrioTemp.split("\\|")[2]);
						System.out.println("命中left4 sanceng_1:"+sanceng_1);
						leftIsExit = true;
					}
					if(resPrioTemp.startsWith(word.getRightMeaningTrueEng_3())){
						sanceng_1 = sanceng_1*Double.parseDouble(resPrioTemp.split("\\|")[2]);
						System.out.println("命中right4 sanceng_1:"+sanceng_1);
						rightIsExit = true;
					}
				}else if(count == 5){
					if(resPrioTemp.startsWith(word.getLeftMeaningTrueEng_3())){
						sanceng_2 = sanceng_2*Double.parseDouble(resPrioTemp.split("\\|")[2]);
						System.out.println("命中left5 sanceng_2:"+sanceng_2);
						leftIsExit = true;
					}
					if(resPrioTemp.startsWith(word.getRightMeaningTrueEng_3())){
						sanceng_2 = sanceng_2*Double.parseDouble(resPrioTemp.split("\\|")[2]);
						System.out.println("命中right5 sanceng_2:"+sanceng_2);
						rightIsExit = true;
					}
				}else if(count == 6){
					if(resPrioTemp.startsWith(word.getLeftMeaningTrueEng_3())){
						sanceng_3 = sanceng_3*Double.parseDouble(resPrioTemp.split("\\|")[2]);
						System.out.println("命中left6 sanceng_3:"+sanceng_3);
						leftIsExit = true;
					}
					if(resPrioTemp.startsWith(word.getRightMeaningTrueEng_3())){
						sanceng_3 = sanceng_3*Double.parseDouble(resPrioTemp.split("\\|")[2]);
						System.out.println("命中right6 sanceng_3:"+sanceng_3);
						rightIsExit = true;
					}
				}else if(count == 7){
					if(resPrioTemp.startsWith(word.getLeftMeaningTrueEng_1())){
						danceng_1 = danceng_1*Double.parseDouble(resPrioTemp.split("\\|")[2]);
						System.out.println("命中left7 danceng_1:"+danceng_1);
						leftIsExit = true;
					}
					if(resPrioTemp.startsWith(word.getRightMeaningTrueEng_1())){
						danceng_1 = danceng_1*Double.parseDouble(resPrioTemp.split("\\|")[2]);
						System.out.println("命中right7 danceng_1:"+danceng_1);
						rightIsExit = true;
					}
				}else if(count == 8){
					if(resPrioTemp.startsWith(word.getLeftMeaningTrueEng_1())){
						danceng_2 = danceng_2*Double.parseDouble(resPrioTemp.split("\\|")[2]);
						System.out.println("命中left8 danceng_2:"+danceng_2);
						leftIsExit = true;
					}
					if(resPrioTemp.startsWith(word.getRightMeaningTrueEng_1())){
						danceng_2 = danceng_2*Double.parseDouble(resPrioTemp.split("\\|")[2]);
						System.out.println("命中right8 danceng_2:"+danceng_2);
						rightIsExit = true;
					}
				}else if(count == 9){
					if(resPrioTemp.startsWith(word.getLeftMeaningTrueEng_1())){
						danceng_3 = danceng_3*Double.parseDouble(resPrioTemp.split("\\|")[2]);
						System.out.println("命中left9 danceng_3:"+danceng_3);
						leftIsExit = true;
					}
					if(resPrioTemp.startsWith(word.getRightMeaningTrueEng_1())){
						danceng_3 = danceng_3*Double.parseDouble(resPrioTemp.split("\\|")[2]);
						System.out.println("命中right9 danceng_3:"+danceng_3);
						rightIsExit = true;
					}
				}
			}
			}
		if(leftIsExit == false){danceng_3 = danceng_3*nullTemp;}
		if(rightIsExit == false){danceng_3 = danceng_3*nullTemp;}
		//分词
		if(fc_1 < fc_2){
			if(fc_2 < fc_3){
				third_emantic_count++;
			}else {second_emantic_count++;}
		}else {
			if(fc_1 < fc_3){
				third_emantic_count++;
			}else {first_emantic_count++;}
		}
		//三层
		if(sanceng_1 < sanceng_2){
			if(sanceng_2 < sanceng_3){
		        third_emantic_count++;
			}else {second_emantic_count++;}
		}else {
			if(sanceng_1 < sanceng_3){
				third_emantic_count++;
		}else {first_emantic_count++;}
		}
		//单层
		if(danceng_1 < danceng_2){
			if(danceng_2 < danceng_3){
				third_emantic_count++;
			}else {second_emantic_count++;}
		}else {
			if(danceng_1 < danceng_3){
				third_emantic_count++;
			}else {first_emantic_count++;}
		}
//		System.out.println(count+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//		System.out.println(fc_1);
//		System.out.println(fc_2);
//		System.out.println(fc_3);
//		System.out.println(sanceng_1);
//		System.out.println(sanceng_2);
//		System.out.println(sanceng_3);
//		System.out.println(danceng_1);
//		System.out.println(danceng_2);
//		System.out.println(danceng_3);
//		System.out.println(count+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//		System.out.println(first_emantic_count);
//		System.out.println(second_emantic_count);
//		System.out.println(third_emantic_count);
		if(first_emantic_count<second_emantic_count){
			if(second_emantic_count<third_emantic_count){res = third_emantic;}
			else{res=second_emantic;}
		}
		else {
			if(first_emantic_count<third_emantic_count){res=third_emantic;}
			else{res=first_emantic;}
		}
		
		return res;
	}

	/**
	 * 判断是否是标点符号
	 * @param arg
	 * @throws IOException
	 */
	public static boolean isPunctuation(String temp){
//		System.out.println(temp);
		boolean res = false; 
		if(temp.equals("null"))
			res = true;
		return res;
	}
	public static void main(String[] arg) throws IOException{
		//在孤苦的日子里     
//		String[] temp = {"本","补","日子","里"};
		String[] temp = {"挑","个","成立","的","日子"};
//		String[] temp = {"叫","个","表面","的","队伍"};
		System.out.println(wsd("成立",temp));
	}

}

