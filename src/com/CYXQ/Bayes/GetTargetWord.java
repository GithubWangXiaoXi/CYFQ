package com.CYXQ.Bayes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * 
 *
 */
public class GetTargetWord {
	
//	static String fileNameTHES = "THES.TXT";		//同义词词林
//	//-------------------菜----------------------
//	static String fileNameTrainCai = "D:/DDSC_Beat2.0/菜/菜.txt";	//初始数据集-训练语料-菜
//	static String fileNameTestCai = "D:/DDSC_Beat2.0/菜/菜test.txt";	//初始数据集-测试语料-菜
//	static String targetWordCai = "菜/ng";	//目标词 歧义词
//	
//	//------------------儿女---------------------
//	static String fileNameTrainErNv = "D:/DDSC_Beat2.0/儿女/儿女.txt";	//初始数据集-训练语料-儿女
//	static String fileNameTestErNv = "D:/DDSC_Beat2.0/儿女/儿女test.txt"; //初始数据集-测试语料-儿女
//	static String targetWordErNv = "儿女/ng"; //目标词 歧义词
//	
//	//------------------中医---------------------
//	static String fileNameTrainZhongYi = "D:/DDSC_Beat2.0/中医/中医.txt";	//初始数据集-训练语料-儿女
//	static String fileNameTestZhongYi = "D:/DDSC_Beat2.0/中医/中医test.txt"; //初始数据集-测试语料-儿女
//	static String targetWordZhongYi = "中医/ng"; //目标词 歧义词
//	static int first_size_ZhongYi = 29;	//前一个词个数
//	static int second_size_ZhongYi = 43;	//后一个词个数
//	static double firstPercentZhongYi = round((double)first_size_ZhongYi/second_size_ZhongYi,4,BigDecimal.ROUND_CEILING);	//先验概率
//	static double secondPercentZhongYi = round((double)(second_size_ZhongYi-first_size_ZhongYi)/second_size_ZhongYi,4,BigDecimal.ROUND_CEILING);
//	
//	//------------------使---------------------
//	static String fileNameTrainShi = "D:/DDSC_Beat2.0/使/使.txt";	//初始数据集-训练语料-儿女
//	static String fileNameTestShi = "D:/DDSC_Beat2.0/使/使test.txt"; //初始数据集-测试语料-儿女
//	static String targetWordShi = "使/v"; //目标词 歧义词
//	static int first_size_Shi = 16;	//前一个词个数
//	static int second_size_Shi = 46;	//后一个词个数
//	static double firstPercentShi = round((double)first_size_Shi/second_size_Shi,4,BigDecimal.ROUND_CEILING);	//先验概率
//	static double secondPercentShi = round((double)(second_size_Shi-first_size_Shi)/second_size_Shi,4,BigDecimal.ROUND_CEILING);
//	
//	//------------------动摇---------------------
//	static String fileNameTrainDongYao = "D:/DDSC_Beat2.0/动摇/动摇.txt";	//初始数据集-训练语料-儿女
//	static String fileNameTestDongYao = "D:/DDSC_Beat2.0/动摇/动摇test.txt"; //初始数据集-测试语料-儿女
//	static String targetWordDongYao = "动摇/v"; //目标词 歧义词
//	static int first_size_DongYao = 30;	//前一个词个数
//	static int second_size_DongYao = 47;	//后一个词个数
//	static double firstPercentDongYao = round((double)first_size_DongYao/second_size_DongYao,4,BigDecimal.ROUND_CEILING);	//先验概率
//	static double secondPercentDongYao = round((double)(second_size_DongYao-first_size_DongYao)/second_size_DongYao,4,BigDecimal.ROUND_CEILING);
//	
//	//------------------单位---------------------
//	static String fileNameTrainDanWei = "D:/DDSC_Beat2.0/单位/单位.txt";	//初始数据集-训练语料-儿女
//	static String fileNameTestDanWei = "D:/DDSC_Beat2.0/单位/单位test.txt"; //初始数据集-测试语料-儿女
//	static String targetWordDanWei = "单位/n"; //目标词 歧义词
//	static int first_size_DanWei = 30;	//前一个词个数
//	static int second_size_DanWei = 50;	//后一个词个数
//	static double firstPercentDanWei = round((double)first_size_DanWei/second_size_DanWei,4,BigDecimal.ROUND_CEILING);	//先验概率
//	static double secondPercentDanWei = round((double)(second_size_DanWei-first_size_DanWei)/second_size_DanWei,4,BigDecimal.ROUND_CEILING);
//	
//	//------------------开通---------------------
//	static String fileNameTrainKaiTong = "D:/DDSC_Beat2.0/开通/开通.txt";	//初始数据集-训练语料-儿女
//	static String fileNameTestKaiTong = "D:/DDSC_Beat2.0/开通/开通test.txt"; //初始数据集-测试语料-儿女
//	static String targetWordKaiTong = "开通/v"; //目标词 歧义词
//	static int first_size_KaiTong = 29;	//前一个词个数
//	static int second_size_KaiTong = 56;	//后一个词个数
//	static double firstPercentKaiTong = round((double)first_size_KaiTong/second_size_KaiTong,4,BigDecimal.ROUND_CEILING);	//先验概率
//	static double secondPercentKaiTong = round((double)(second_size_KaiTong-first_size_KaiTong)/second_size_KaiTong,4,BigDecimal.ROUND_CEILING);
//	
//	//------------------望---------------------
//	static String fileNameTrainWang = "D:/DDSC_Beat2.0/望/望.txt";	//初始数据集-训练语料-儿女
//	static String fileNameTestWang = "D:/DDSC_Beat2.0/望/望test.txt"; //初始数据集-测试语料-儿女
//	static String targetWordWang = "望/v"; //目标词 歧义词
//	static int first_size_Wang = 29;	//前一个词个数
//	static int second_size_Wang = 37;	//后一个词个数
//	static double firstPercentWang = round((double)first_size_Wang/second_size_Wang,4,BigDecimal.ROUND_CEILING);	//先验概率
//	static double secondPercentWang = round((double)(second_size_Wang-first_size_Wang)/second_size_Wang,4,BigDecimal.ROUND_CEILING);
//	
//	//------------------机组---------------------
//	static String fileNameTrainJiZu = "D:/DDSC_Beat2.0/机组/机组.txt";	//初始数据集-训练语料-儿女
//	static String fileNameTestJiZu = "D:/DDSC_Beat2.0/机组/机组test.txt"; //初始数据集-测试语料-儿女
//	static String targetWordJiZu = "机组/n"; //目标词 歧义词
//	static int first_size_JiZu = 8;	//前一个词个数
//	static int second_size_JiZu = 38;	//后一个词个数
//	static double firstPercentJiZu = round((double)first_size_JiZu/second_size_JiZu,4,BigDecimal.ROUND_CEILING);	//先验概率
//	static double secondPercentJiZu = round((double)(second_size_JiZu-first_size_JiZu)/second_size_JiZu,4,BigDecimal.ROUND_CEILING);
//	
//	//------------------气息---------------------
//	static String fileNameTrainQiXi = "D:/DDSC_Beat2.0/气息/气息.txt";	//初始数据集-训练语料-儿女
//	static String fileNameTestQiXi = "D:/DDSC_Beat2.0/气息/气息test.txt"; //初始数据集-测试语料-儿女
//	static String targetWordQiXi = "气息/n"; //目标词 歧义词
//	static int first_size_QiXi = 30;	//前一个词个数
//	static int second_size_QiXi = 39;	//后一个词个数
//	static double firstPercentQiXi = round((double)first_size_QiXi/second_size_QiXi,4,BigDecimal.ROUND_CEILING);	//先验概率
//	static double secondPercentQiXi = round((double)(second_size_QiXi-first_size_QiXi)/second_size_QiXi,4,BigDecimal.ROUND_CEILING);
//	
//	//------------------推翻---------------------
//	static String fileNameTrainTuiFan = "D:/DDSC_Beat2.0/推翻/推翻.txt";
//	static String fileNameTestTuiFan = "D:/DDSC_Beat2.0/推翻/推翻test.txt";
//	static String targetWordTuiFan = "推翻/v";
//	static int first_size_TuiFan = 16;
//	static int second_size_TuiFan = 29;
//	static double firstPercentTuiFan = round((double)first_size_TuiFan/second_size_TuiFan,4,BigDecimal.ROUND_CEILING);	//先验概率
//	static double secondPercentTuiFan = round((double)(second_size_TuiFan-first_size_TuiFan)/second_size_TuiFan,4,BigDecimal.ROUND_CEILING);
//	
//	//------------------气象---------------------
//	static String fileNameTrainQiXiang = "D:/DDSC_Beat2.0/气象/气象.txt";	//初始数据集-训练语料-儿女
//	static String fileNameTestQiXiang = "D:/DDSC_Beat2.0/气象/气象test.txt"; //初始数据集-测试语料-儿女
//	static String targetWordQiXiang = "气象/n"; //目标词 歧义词
//	static int first_size_QiXiang = 30;	//前一个词个数
//	static int second_size_QiXiang = 47;	//后一个词个数
//	static double firstPercentQiXiang = round((double)first_size_QiXiang/second_size_QiXiang,4,BigDecimal.ROUND_CEILING);	//先验概率
//	static double secondPercentQiXiang = round((double)(second_size_QiXiang-first_size_QiXiang)/second_size_QiXiang,4,BigDecimal.ROUND_CEILING);
//	
//	//------------------牌子---------------------
//	static String fileNameTrainPaiZi = "D:/DDSC_Beat2.0/牌子/牌子.txt";	//初始数据集-训练语料-儿女
//	static String fileNameTestPaiZi = "D:/DDSC_Beat2.0/牌子/牌子test.txt"; //初始数据集-测试语料-儿女
//	static String targetWordPaiZi = "牌子/n"; //目标词 歧义词
//	static int first_size_PaiZi = 21;	//前一个词个数
//	static int second_size_PaiZi = 44;	//后一个词个数
//	static double firstPercentPaiZi = round((double)first_size_PaiZi/second_size_PaiZi,4,BigDecimal.ROUND_CEILING);	//先验概率
//	static double secondPercentPaiZi = round((double)(second_size_PaiZi-first_size_PaiZi)/second_size_PaiZi,4,BigDecimal.ROUND_CEILING);
//	
//	//------------------眼光---------------------
//	static String fileNameTrainYanGuang = "D:/DDSC_Beat2.0/眼光/眼光.txt";	//初始数据集-训练语料-儿女
//	static String fileNameTestYanGuang = "D:/DDSC_Beat2.0/眼光/眼光test.txt"; //初始数据集-测试语料-儿女
//	static String targetWordYanGuang = "眼光/n"; //目标词 歧义词
//	static int first_size_YanGuang = 30;	//前一个词个数
//	static int second_size_YanGuang = 41;	//后一个词个数
//	static double firstPercentYanGuang = round((double)first_size_YanGuang/second_size_YanGuang,4,BigDecimal.ROUND_CEILING);	//先验概率
//	static double secondPercentYanGuang = round((double)(second_size_YanGuang-first_size_YanGuang)/second_size_YanGuang,4,BigDecimal.ROUND_CEILING);
//	
//	//------------------表面---------------------
//	static String fileNameTrainBiaoMian = "D:/DDSC_Beat2.0/表面/表面.txt";	//初始数据集-训练语料-儿女
//	static String fileNameTestBiaoMian = "D:/DDSC_Beat2.0/表面/表面test.txt"; //初始数据集-测试语料-儿女
//	static String targetWordBiaoMian = "表面/n"; //目标词 歧义词
//	static int first_size_BiaoMian = 24;	//前一个词个数
//	static int second_size_BiaoMian = 53;	//后一个词个数
//	static double firstPercentBiaoMian = round((double)first_size_BiaoMian/second_size_BiaoMian,4,BigDecimal.ROUND_CEILING);	//先验概率
//	static double secondPercentBiaoMian = round((double)(second_size_BiaoMian-first_size_BiaoMian)/second_size_BiaoMian,4,BigDecimal.ROUND_CEILING);
//	
//	//------------------镜头---------------------
//	static String fileNameTrainJingTou = "D:/DDSC_Beat2.0/镜头/镜头.txt";	//初始数据集-训练语料-儿女
//	static String fileNameTestJingTou = "D:/DDSC_Beat2.0/镜头/镜头test.txt"; //初始数据集-测试语料-儿女
//	static String targetWordJingTou = "镜头/n"; //目标词 歧义词
//	static int first_size_JingTou = 25;	//前一个词个数
//	static int second_size_JingTou = 45;	//后一个词个数
//	static double firstPercentJingTou = round((double)first_size_JingTou/second_size_JingTou,4,BigDecimal.ROUND_CEILING);	//先验概率
//	static double secondPercentJingTou = round((double)(second_size_JingTou-first_size_JingTou)/second_size_JingTou,4,BigDecimal.ROUND_CEILING);
//	
//	//------------------震惊---------------------
//	static String fileNameTrainZhenJing = "D:/DDSC_Beat2.0/震惊/震惊.txt";	//初始数据集-训练语料-儿女
//	static String fileNameTestZhenJing = "D:/DDSC_Beat2.0/震惊/震惊test.txt"; //初始数据集-测试语料-儿女
//	static String targetWordZhenJing = "震惊/v"; //目标词 歧义词
//	static int first_size_ZhenJing = 9;	//前一个词个数
//	static int second_size_ZhenJing = 38;	//后一个词个数
//	static double firstPercentZhenJing = round((double)first_size_ZhenJing/second_size_ZhenJing,4,BigDecimal.ROUND_CEILING);	//先验概率
//	static double secondPercentZhenJing = round((double)(second_size_ZhenJing-first_size_ZhenJing)/second_size_ZhenJing,4,BigDecimal.ROUND_CEILING);
//	
//	//----------------------三分-----------------------------
//	//-------------------天地----------------------
//	
//	static String fileNameTrainTianDi = "D:/DDSC_Beat2.0/天地/天地.txt"; //初始数据集-训练语料-动
//	static String fileNameTestTianDi = "D:/DDSC_Beat2.0/天地/天地test.txt";	//初始数据集-测试语料-动
//	static String targetWordTianDi = "天地/n"; //目标词 歧义词
//	static int first_size_TianDi = 30; //第一个词个数
//	static int second_size_TianDi = 50;
//	static int third_size_TianDi = 65;
//	static double firstPercentTianDi = round((double)first_size_TianDi/third_size_TianDi,4,BigDecimal.ROUND_CEILING);	//先验概率
//	static double secondPercentTianDi = round((double)(second_size_TianDi-first_size_TianDi)/third_size_TianDi,4,BigDecimal.ROUND_CEILING);
//	static double thirdPercentTianDi = round((double)(third_size_TianDi-second_size_TianDi)/third_size_TianDi,4,BigDecimal.ROUND_CEILING);
//	
//	//------------------成立----------------------
//	static String fileNameTrainChengLi = "D:/DDSC_Beat2.0/成立/成立.txt"; //初始数据集-训练语料-出
//	static String fileNameTestChengLi = "D:/DDSC_Beat2.0/成立/成立test.txt";	//初始数据集-测试语料-出
//	static String targetWordChengLi = "成立/v"; //目标词 歧义词
//	static int first_size_ChengLi =	30; //第一个词个数
//	static int second_size_ChengLi = 60;
//	static int third_size_ChengLi = 73;
//	
//	//------------------挑----------------------
//	static String fileNameTrainTiao = "D:/DDSC_Beat2.0/挑/挑.txt"; //初始数据集-训练语料-出
//	static String fileNameTestTiao = "D:/DDSC_Beat2.0/挑/挑test.txt";	//初始数据集-测试语料-出
//	static String targetWordTiao = "挑/v"; //目标词 歧义词
//	static int first_size_Tiao = 20; //第一个词个数
//	static int second_size_Tiao = 34;
//	static int third_size_Tiao = 40;
//	
//	//------------------旗帜----------------------
//	static String fileNameTrainQiZhi = "D:/DDSC_Beat2.0/旗帜/旗帜.txt"; //初始数据集-训练语料-出
//	static String fileNameTestQiZhi = "D:/DDSC_Beat2.0/旗帜/旗帜test.txt";	//初始数据集-测试语料-出
//	static String targetWordQiZhi = "旗帜/n"; //目标词 歧义词
//	static int first_size_QiZhi = 30; //第一个词个数
//	static int second_size_QiZhi = 39;
//	static int third_size_QiZhi = 50;
//	
//	
//	//------------------日子----------------------
//	static String fileNameTrainRiZi = "D:/DDSC_Beat2.0/日子/日子.txt"; //初始数据集-训练语料-出
//	static String fileNameTestRiZi = "D:/DDSC_Beat2.0/日子/日子test.txt";	//初始数据集-测试语料-出
//	static String targetWordRiZi = "日子/n"; //目标词 歧义词
//	static int first_size_RiZi = 30; //第一个词个数
//	static int second_size_RiZi = 59;
//	static int third_size_RiZi = 88;
//	
//	//------------------本----------------------
//	static String fileNameTrainBen = "D:/DDSC_Beat2.0/本/本.txt"; //初始数据集-训练语料-出
//	static String fileNameTestBen = "D:/DDSC_Beat2.0/本/本test.txt";	//初始数据集-测试语料-出
//	static String targetWordBen = "本/n"; //目标词 歧义词
//	static int first_size_Ben = 30; //第一个词个数
//	static int second_size_Ben = 59;
//	static int third_size_Ben = 68;
//	
//	//------------------补----------------------
//	static String fileNameTrainBu = "D:/DDSC_Beat2.0/补/补.txt"; //初始数据集-训练语料-出
//	static String fileNameTestBu = "D:/DDSC_Beat2.0/补/补test.txt";	//初始数据集-测试语料-出
//	static String targetWordBu = "补/v"; //目标词 歧义词
//	static int first_size_Bu = 30; //第一个词个数
//	static int second_size_Bu = 39;
//	static int third_size_Bu = 63;
//	
//	//------------------赶----------------------
//	static String fileNameTrainGan = "D:/DDSC_Beat2.0/赶/赶.txt"; //初始数据集-训练语料-出
//	static String fileNameTestGan = "D:/DDSC_Beat2.0/赶/赶test.txt";	//初始数据集-测试语料-出
//	static String targetWordGan = "赶/v"; //目标词 歧义词
//	static int first_size_Gan = 30; //第一个词个数
//	static int second_size_Gan = 48;
//	static int third_size_Gan = 56;
//	
//	//------------------长城----------------------
//	static String fileNameTrainChangCheng = "D:/DDSC_Beat2.0/长城/长城.txt"; //初始数据集-训练语料-出
//	static String fileNameTestChangCheng = "D:/DDSC_Beat2.0/长城/长城test.txt";	//初始数据集-测试语料-出
//	static String targetWordChangCheng = "长城/ns"; //目标词 歧义词
//	static int first_size_ChangCheng = 28; //第一个词个数
//	static int second_size_ChangCheng = 43;
//	static int third_size_ChangCheng = 48;
//	
//	//------------------队伍---------------------
//	static String fileNameTrainDuiWu = "D:/DDSC_Beat2.0/队伍/队伍.txt";	//初始数据集-训练语料-气息
//	static String fileNameTestDuiWu = "D:/DDSC_Beat2.0/队伍/队伍test.txt"; //初始数据集-测试语料-气息
//	static String targetWordDuiWu = "队伍/n"; //目标词 歧义词
//	static int first_size_DuiWu = 30;	//前一个词个数
//	static int second_size_DuiWu = 54;	//后一个词个数
//	static int third_size_DuiWu = 64;
//	
//	static int first_size = first_size_TianDi;
//	static int second_size = second_size_TianDi;
//	static int third_size = third_size_TianDi;
//	
//	static String fileNameTrain = fileNameTrainTianDi;
//	static String fileNameTest = fileNameTestTianDi;
	static String targetWord ;
	
	/**
	 *
	 * @param fileName重置目标词
	 * @return
	 */
	public static void setTargetWord(String tw){
		targetWord = tw;
	}

	/*
	 * 得到文本文件
	 */
	public static List<String> readLinesFromFile(String fileName) {
		List<String> list = new ArrayList<String>(); // 存储从文件中按行读取的数据
		BufferedReader br = null; // 声明BufferedReader对象
		try {
			br = new BufferedReader(new FileReader(fileName)); // 创建BufferedReader对象
			String line = null; // 创建中间变量 存储文本中每行的数据
			while ((line = br.readLine()) != null) // 判断取得的数据是否为空
			{
				list.add(line); // 将非空数据添加到list中
//				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list; // 返回list
	}

	/*
	 * 根据“空格“和”/”切分分词    将分词存储到List<Word>中
	 * 
	 * @param args
	 */
	public static Word getFenCiBySpace(String key,String[] sentence) {
		
		Word word = new Word();
		String[] wordTemp = sentence;	//根据空格切分每行数据
		System.out.println(key);
		String targetWord = "";		//中间变量  存储目标词
		String targetLeft = "";		//中间变量  存储左分词
		String targetRight = "";	//中间变量  存储右分词
		for(int j = 0 ; j < wordTemp.length ; j++) {
			System.out.println(wordTemp[j]);
			if (key.equals(wordTemp[j])) {		//判断目标词位置
				if(j >0 && j < wordTemp.length - 1){
					targetWord = wordTemp[j];		//根据“/” 切分分词    目标词key在sentence位置不在首位 或者 不在尾位
					targetLeft = wordTemp[j-1];
					targetRight = wordTemp[j+1];
//					System.out.println(targetRight);
				} else if( j == 0) {
					targetWord = wordTemp[j];		//根据“/” 切分分词    目标词key在sentence位置在首位，没有左分词
					targetLeft = "null";
					targetRight = wordTemp[j+1];
				} else {										//根据“/” 切分分词    目标词key在sentence位置在尾位，没有右分词
					targetWord = wordTemp[j];
					targetLeft = wordTemp[j-1];
					targetRight = "null";
				}
			}
			//如果目标词，左分词，右分词是标点符号，置为null
			if (targetWord.equals("、") || targetWord.equals("，") || targetWord.equals("”") || targetWord.equals("：") || targetWord.equals("“")|| targetWord.equals("。")){
				targetWord = "null";	//如果分词为符号  则用"null"替换
			} word.setWord(targetWord);
			if (targetLeft.equals("、") || targetLeft.equals("，") || targetLeft.equals("”") || targetLeft.equals("：") || targetLeft.equals("“")|| targetLeft.equals("。") || targetLeft.equals("（") || targetLeft.equals("《") || targetLeft.equals("‘") || targetLeft.equals("；") || targetLeft.equals("！") || targetLeft.equals("》")) {
				targetLeft = "null";
			} word.setLeft(targetLeft);
			if (targetRight.equals("、") || targetRight.equals("，") || targetRight.equals("”") || targetRight.equals("：") || targetRight.equals("“")|| targetRight.equals("。") || targetRight.equals("（") || targetRight.equals("《") || targetRight.equals("‘") || targetRight.equals("；") || targetRight.equals("！") || targetRight.equals("》")){
				targetRight = "null";
			} word.setRight(targetRight);
		}
		return word;
	}

	/*
	 * 从同义词词林中提取词义  将“目标词”，“左分词”，“右分词	”，“左分词在同义词词林中的词义以及词义代码”，“右分词在同义词词林中的词义以及词义代码”存储在返回值中
	 * @param fileName
	 * @return
	 * @throws IOException 
	 */
	public static Word getSenceFromList(String target , String[] sentence)
	{
		Word word = getFenCiBySpace(target,sentence);		//得到包含“目标词”，“左分词”，“右分词”的数据集				--修改targetWordCai
		List<String> list = readLinesFromFile("D:/THES.TXT");	//得到同义词词林数据集
		for(int i = 0 ; i < list.size() ; i++)//在同义词词林中搜索左右分词   并将左右分词词义 词义代码（三层）存储到word中
		{
			String temp = list.get(i);
//			System.out.println(temp);
			/* 词语/语义 */
			int tempNum = temp.indexOf("/");	//根据“/”切分同义词词林中语句

			/*遍历的词语是否与left,right匹配，匹配则将词义放入词中。时间复杂度为O(N)*/
			if(tempNum > 0)
			{
				if(word.getLeft().equals(temp.substring(1,temp.indexOf("/"))))	{
					word.setLeftMeaning(temp.substring(temp.indexOf("/")+1));
				}
				if(word.getRight().equals(temp.substring(1,temp.indexOf("/")))) {
					word.setRightMeaning(temp.substring(temp.indexOf("/")+1));
				}
			}
		}
		return word;
	}
	
	/*
	 * 根据可能性最大原则 提取出左右分词    
	 * @param args
	 */
	public static Word getLeftAndRightByPercent(String target ,String[] sentence){
		Word word = getSenceFromList(target,sentence);

		//当左分词为“标点符号”或者为null时 存储左分词词语、词义代码（单层）、词义代码（3层）
		if("null".equals(word.getLeftMeaning()) || word.getLeftMeaning() == null){
			word.setLeftMeaningTrue("null");	//左分词
			word.setLeftMeaningTrueCwn("null");	//左分词 词语	
			word.setLeftMeaningTrueEng_1("null");//左分词词义代码（单层）
			word.setLeftMeaningTrueEng_3("null");//左分词词义代码（ 3层）
		} else {
			//当左分词的语义仅有一个时
			if(word.getLeftMeaning().indexOf(")") == word.getLeftMeaning().length()-1){
				word.setLeftMeaningTrue(word.getLeftMeaning()); //左分词
				word.setLeftMeaningTrueCwn(word.getLeftMeaning().substring(0, word.getLeftMeaning().indexOf("("))); //左分词 词语	
				word.setLeftMeaningTrueEng_1(word.getLeftMeaning().substring(word.getLeftMeaning().indexOf("(")+1, word.getLeftMeaning().indexOf("(")+2)); //左分词词义代码（单层
				word.setLeftMeaningTrueEng_3(word.getLeftMeaning().substring(word.getLeftMeaning().indexOf("(")+1, word.getLeftMeaning().indexOf(")"))); //左分词词义代码（ 3层）
			} else {
				//按“空格”切分字符串，“_”表示同个词义的不同解释
				String[] targetLeftTemp = word.getLeftMeaning().split(" ");

				//将首个值赋给中间变量targetLeft
				String targetLeft = targetLeftTemp[0];
				
				//将首个切分出的词义、词义代码赋给word
				word.setLeftMeaningTrue(targetLeftTemp[0]); //左分词
				word.setLeftMeaningTrueCwn(targetLeftTemp[0].substring(0, targetLeftTemp[0].indexOf("("))); //左分词 词语	
				word.setLeftMeaningTrueEng_1(targetLeftTemp[0].substring(targetLeftTemp[0].indexOf("(")+1, targetLeftTemp[0].indexOf("(")+2)); //左分词词义代码（单层）
				word.setLeftMeaningTrueEng_3(targetLeftTemp[0].substring(targetLeftTemp[0].indexOf("(")+1, targetLeftTemp[0].indexOf(")"))); //左分词词义代码（ 3层）

				//用来比较概率大小的中间变量
				double resultLeftSum  = 0.0;
				//targetLeftTemp为左分词的所有词义
				for(int j = 0 ; j <targetLeftTemp.length ; j++) {
					int numLeft = targetLeftTemp[j].indexOf("(");
					//词义存在多个才用比较
					if(targetLeftTemp.length > 1) {
						String[] targetLeftCwn = targetLeftTemp[j].substring(0,numLeft).split("_");	//按"_"切分字符串
						for(int k = 0 ; k < targetLeftCwn.length ; k++) {
							//计算L_Word在L_Sence中存在的概率，同一个词义下的词被分成多个
							//左分词的词义的切片中是否包含左分词词语
							if(targetLeftCwn[k].indexOf(word.getLeft()) != -1) {
								//double result = (double)(L_Word.length()*2)/(L_Word.length()+L_Sence_CWN[j].length());	//计算L_Word在L_Sence中存在的概率
								//计算Word.getLeft在LeftMeaning中存在的概率，使用dice距离来求字符串之间的相似性
								double resultLeft = (double)(word.getLeft().length() * 2) / (word.getLeft().length() + targetLeftCwn[k].length());
								if(resultLeft > resultLeftSum) {
									//将dice距离计算的最大的字符串相似度resultLeft 赋值给 resultLeftSum，并修改左分词的词义
									resultLeftSum = resultLeft;
									word.setLeftMeaningTrue(targetLeftTemp[j]); //左分词
									word.setLeftMeaningTrueCwn(targetLeftTemp[j].substring(0, targetLeftTemp[j].indexOf("("))); //左分词 词语	
									word.setLeftMeaningTrueEng_1(targetLeftTemp[j].substring(targetLeftTemp[j].indexOf("(")+1, targetLeftTemp[j].indexOf("(")+2)); //左分词词义代码（单层）
									word.setLeftMeaningTrueEng_3(targetLeftTemp[j].substring(targetLeftTemp[j].indexOf("(")+1, targetLeftTemp[j].indexOf(")"))); //左分词词义代码（ 3层）
								}
							}
						}
					}
				}
			}
		}
		//右分词词义的提取，相似于左分词的词义的最大可能性提取。
		if("null".equals(word.getRightMeaning()) || word.getRightMeaning() == null){ 	//当右分词为“null”或者为null时 存储左分词词语、词义代码（单层）、词义代码（3层）
			word.setRightMeaningTrue("null"); //右分词
			word.setRightMeaningTrueCwn("null"); //右分词 词语	
			word.setRightMeaningTrueEng_1("null"); //右分词词义代码（单层）
			word.setRightMeaningTrueEng_3("null"); //右分词词义代码（ 3层）
		} else {
			if(word.getRightMeaning().indexOf(")") == word.getRightMeaning().length()-1){ //当右分词仅有一个时  
				word.setRightMeaningTrue(word.getRightMeaning()); //右分词
				word.setRightMeaningTrueCwn(word.getRightMeaning().substring(0, word.getRightMeaning().indexOf("("))); //右分词 词语
				word.setRightMeaningTrueEng_1(word.getRightMeaning().substring(word.getRightMeaning().indexOf("(")+1, word.getRightMeaning().indexOf("(")+2)); //右分词词义代码（单层）
				word.setRightMeaningTrueEng_3(word.getRightMeaning().substring(word.getRightMeaning().indexOf("(")+1, word.getRightMeaning().indexOf(")"))); //右分词词义代码（ 3层）
			} else {
				String[] targetRightTemp = word.getRightMeaning().split(" ");
				String targetRight = targetRightTemp[0];
				
				//将首个切分出的词义、词义代码赋给word
				word.setRightMeaningTrue(targetRightTemp[0]); //右分词
				word.setRightMeaningTrueCwn(targetRightTemp[0].substring(0, targetRightTemp[0].indexOf("(")));  //右分词 词语
				word.setRightMeaningTrueEng_1(targetRightTemp[0].substring(targetRightTemp[0].indexOf("(")+1, targetRightTemp[0].indexOf("(")+2)); //右分词词义代码（单层）
				word.setRightMeaningTrueEng_3(targetRightTemp[0].substring(targetRightTemp[0].indexOf("(")+1, targetRightTemp[0].indexOf(")"))); //右分词词义代码（ 3层）
			
				double resultRightSum = 0.0;
				for(int j = 0 ; j <targetRightTemp.length ; j++) {
					int numRight = targetRightTemp[j].indexOf("(");
					if(targetRightTemp.length > 1) {
						String[] targetRightCwn = targetRightTemp[j].substring(0,numRight).split("_");	//按"_"切分字符串
						for(int k = 0 ; k < targetRightCwn.length ; k++) {
							if(targetRightCwn[k].indexOf(word.getRight()) != -1) {
								//double result = (double)(L_Word.length()*2)/(L_Word.length()+L_Sence_CWN[j].length());	//计算L_Word在L_Sence中存在的概率
								double resultRight = (double)(word.getRight().length() * 2) / (word.getRight().length() + targetRightCwn[k].length());  //计算Word.getLeft在LeftMeaning中存在的概率
								if(resultRight > resultRightSum) {
									resultRightSum = resultRight;
									word.setRightMeaningTrue(targetRightTemp[j]); //右分词
									word.setRightMeaningTrueCwn(targetRightTemp[j].substring(0, targetRightTemp[j].indexOf("(")));  //右分词 词语
									word.setRightMeaningTrueEng_1(targetRightTemp[j].substring(targetRightTemp[j].indexOf("(")+1, targetRightTemp[j].indexOf("(")+2)); //右分词词义代码（单层）
									word.setRightMeaningTrueEng_3(targetRightTemp[j].substring(targetRightTemp[j].indexOf("(")+1, targetRightTemp[j].indexOf(")"))); //右分词词义代码（ 3层）
								}
							}
						}
					}
				}
			}
		}
		return word;
	}
	/**  
     * 对double数据进行取精度.  
     * @param value  double数据.  
     * @param scale  精度位数(保留的小数位数).  
     * @param roundingMode  精度取值方式.  
     * @return 精度计算后的数据.  
     */  
	public static double round(double value, int scale, int roundingMode) {   
       BigDecimal bd = new BigDecimal(value);   
       bd = bd.setScale(scale, roundingMode);   
       double d = bd.doubleValue();   
       bd = null;   
       return d;   
	} 
}
