package com.CYXQ.Bayes;

import java.math.BigDecimal;

public class EnvironmentVar {
//	private private static String filePath = "F:/XQ/result/";
	//----------------------三分-----------------------------
	//-------------------天地----------------------
	
	private static String fileNameTrainTianDi = "D:/DDSC_Beat2.0/天地/天地.txt"; //初始数据集-训练语料-动
	private static String fileNameTestTianDi = "D:/DDSC_Beat2.0/天地/天地test.txt";	//初始数据集-测试语料-动
	private static String fileNameresPrioTianDi = "D:/resPrio/天地/天地.txt";
	private static String targetWordTianDi = "天地/n"; //目标词 歧义词
	private static int first_size_TianDi = 30; //第一个词个数
	private static int second_size_TianDi = 50;
	private static int third_size_TianDi = 65;
	private static String first_emantic_TianDi = "范围  方面  环节（Dd05）";
	private static String second_emantic_TianDi = "宇宙  星辰（Bd01）";
	private static String third_emantic_TianDi = "世界  社会  人间（Di01）";
	private static int test_first_num_TianDi = 10;
	private static int test_second_num_TianDi = 10;
	private static int test_third_num_TianDi = 5;
	private static double firstPercentTianDi = GetTargetWord.round((double)first_size_TianDi/third_size_TianDi,4,BigDecimal.ROUND_CEILING);	//先验概率
	private static double secondPercentTianDi = GetTargetWord.round((double)(second_size_TianDi-first_size_TianDi)/third_size_TianDi,4,BigDecimal.ROUND_CEILING);
	private static double thirdPercentTianDi = GetTargetWord.round((double)(third_size_TianDi-second_size_TianDi)/third_size_TianDi,4,BigDecimal.ROUND_CEILING);
	
	//------------------成立----------------------
	private static String fileNameTrainChengLi = "D:/DDSC_Beat2.0/成立/成立.txt"; //初始数据集-训练语料-出
	private static String fileNameTestChengLi = "D:/DDSC_Beat2.0/成立/成立test.txt";	//初始数据集-测试语料-出
	private static String fileNameresPrioChengLi = "D:/resPrio/成立/成立.txt";
	private static String targetWordChengLi = "成立/v"; //目标词 歧义词
	private static int first_size_ChengLi =	30; //第一个词个数
	private static int second_size_ChengLi = 60;
	private static int third_size_ChengLi = 73;
	private static String first_emantic_ChengLi = "建立  设立  创立  命名（Hc05） ";
	private static String second_emantic_ChengLi = "安置  部署  调动 整顿 整编（Hc03）";
	private static String third_emantic_ChengLi = "合理  无理  生硬  牵强 （Ed13）";
	private static int test_first_num_ChengLi = 10;
	private static int test_second_num_ChengLi = 10;
	private static int test_third_num_ChengLi = 7;
	
	//------------------挑----------------------
	private static String fileNameTrainTiao = "D:/DDSC_Beat2.0/挑/挑.txt"; //初始数据集-训练语料-出
	private static String fileNameTestTiao = "D:/DDSC_Beat2.0/挑/挑test.txt";	//初始数据集-测试语料-出
	private static String fileNameresPrioTiao = "D:/resPrio/挑/挑.txt";
	private static String targetWordTiao = "挑/v"; //目标词 歧义词
	private static int first_size_Tiao = 20; //第一个词个数
	private static int second_size_Tiao = 34;
	private static int third_size_Tiao = 40;
	private static String first_emantic_Tiao = "采集  搜罗  挑选  取长补短（Hj25）";
	private static String second_emantic_Tiao = "扛  挑  抬  背（Fa03）";
	private static String third_emantic_Tiao = "挖  剔  钳（Fa10）";
	private static int test_first_num_Tiao = 6;
	private static int test_second_num_Tiao = 5;
	private static int test_third_num_Tiao = 3;
	
	//------------------旗帜----------------------(测试语料语义排列格式不规范)
	private static String fileNameTrainQiZhi = "D:/DDSC_Beat2.0/旗帜/旗帜.txt"; //初始数据集-训练语料-出
	private static String fileNameTestQiZhi = "D:/DDSC_Beat2.0/旗帜/旗帜test.txt";	//初始数据集-测试语料-出
	private static String fileNameresPrioQiZhi = "D:/resPrio/旗帜/旗帜.txt";
	private static String targetWordQiZhi = "旗帜/n"; //目标词 歧义词
	private static int first_size_QiZhi = 30; //第一个词个数
	private static int second_size_QiZhi = 39;
	private static int third_size_QiZhi = 50;
	private static String first_emantic_QiZhi = "学说  学问  知识（Dk02）";
	private static String second_emantic_QiZhi = "标准  榜样（Dd11）";
	private static String third_emantic_QiZhi = "标志  旗帜（Bp20）";
	private static int test_first_num_QiZhi = 10;
	private static int test_second_num_QiZhi = 3;
	private static int test_third_num_QiZhi = 5;
	
	
	//------------------日子----------------------
	private static String fileNameTrainRiZi = "D:/DDSC_Beat2.0/日子/日子.txt"; //初始数据集-训练语料-出
	private static String fileNameTestRiZi = "D:/DDSC_Beat2.0/日子/日子test.txt";	//初始数据集-测试语料-出
	private static String fileNameresPrioRiZi = "D:/resPrio/日子/日子.txt";
	private static String targetWordRiZi = "日子/n"; //目标词 歧义词
	private static int first_size_RiZi = 30; //第一个词个数
	private static int second_size_RiZi = 59;
	private static int third_size_RiZi = 88;
	private static String first_emantic_RiZi = "日（Ca23）";
	private static String second_emantic_RiZi = "时期  时间（Ca03）";
	private static String third_emantic_RiZi = "生命  生活（Da17）";
	private static int test_first_num_RiZi = 10;
	private static int test_second_num_RiZi = 11;
	private static int test_third_num_RiZi = 11;
	
	//------------------本----------------------
	private static String fileNameTrainBen = "D:/DDSC_Beat2.0/本/本.txt"; //初始数据集-训练语料-出
	private static String fileNameTestBen = "D:/DDSC_Beat2.0/本/本test.txt";	//初始数据集-测试语料-出
	private static String fileNameresPrioBen = "D:/resPrio/本/本.txt";
	private static String targetWordBen = "本/n"; //目标词 歧义词
	private static int first_size_Ben = 30; //第一个词个数
	private static int second_size_Ben = 59;
	private static int third_size_Ben = 68;
	private static String first_emantic_Ben = "读物  书籍  版本  页张（Dk20）";
	private static String second_emantic_Ben = "立场  角度（Db08）";
	private static String third_emantic_Ben = "资本  利润  利息  债务（Dj04）";
	private static int test_first_num_Ben = 10;
	private static int test_second_num_Ben = 10;
	private static int test_third_num_Ben = 5;
	
	//------------------补----------------------
	private static String fileNameTrainBu = "D:/DDSC_Beat2.0/补/补.txt"; //初始数据集-训练语料-出
	private static String fileNameTestBu = "D:/DDSC_Beat2.0/补/补test.txt";	//初始数据集-测试语料-出
	private static String fileNameresPrioBu = "D:/resPrio/补/补.txt";
	private static String targetWordBu = "补/v"; //目标词 歧义词
	private static int first_size_Bu = 30; //第一个词个数
	private static int second_size_Bu = 39;
	private static int third_size_Bu = 63;
	private static String first_emantic_Bu = "增多  填补  减少（Ih05）";
	private static String second_emantic_Bu = "缝纫（Hj41）";
	private static String third_emantic_Bu = "疗养  调养（Hj33）";
	private static int test_first_num_Bu = 10;
	private static int test_second_num_Bu = 3;
	private static int test_third_num_Bu = 7;
	
	//------------------赶----------------------
	private static String fileNameTrainGan = "D:/DDSC_Beat2.0/赶/赶.txt"; //初始数据集-训练语料-出
	private static String fileNameTestGan = "D:/DDSC_Beat2.0/赶/赶test.txt";	//初始数据集-测试语料-出
	private static String fileNameresPrioGan = "D:/resPrio/赶/赶.txt";
	private static String targetWordGan = "赶/v"; //目标词 歧义词
	private static int first_size_Gan = 30; //第一个词个数
	private static int second_size_Gan = 48;
	private static int third_size_Gan = 56;
	private static String first_emantic_Gan = "从  到  沿（Kb02）";
	private static String second_emantic_Gan = "等候  驱赶  收留  挽留（Hi07）";
	private static String third_emantic_Gan = "前进  后退  追赶（Hj67）";
	private static int test_first_num_Gan = 9;
	private static int test_second_num_Gan = 6;
	private static int test_third_num_Gan = 3;
	
	//------------------长城----------------------
	private static String fileNameTrainChangCheng = "D:/DDSC_Beat2.0/长城/长城.txt"; //初始数据集-训练语料-出
	private static String fileNameTestChangCheng = "D:/DDSC_Beat2.0/长城/长城test.txt";	//初始数据集-测试语料-出
	private static String fileNameresPrioChangCheng = "D:/resPrio/长城/长城.txt";
	private static String targetWordChangCheng = "长城/ns"; //目标词 歧义词
	private static int first_size_ChangCheng = 28; //第一个词个数
	private static int second_size_ChangCheng = 43;
	private static int third_size_ChangCheng = 48;
	private static String first_emantic_ChangCheng = "建筑  房屋（Bn01）";
	private static String second_emantic_ChangCheng = "标志  旗帜（Bp02）";
	private static String third_emantic_ChangCheng = "阻力  屏障  枷锁（Dg05）";
	private static int test_first_num_ChangCheng = 10;
	private static int test_second_num_ChangCheng = 8;
	private static int test_third_num_ChangCheng = 3;
	
	//------------------队伍---------------------
	private static String fileNameTrainDuiWu = "D:/DDSC_Beat2.0/队伍/队伍.txt";	//初始数据集-训练语料-气息
	private static String fileNameTestDuiWu = "D:/DDSC_Beat2.0/队伍/队伍test.txt"; //初始数据集-测试语料-气息
	private static String fileNameresPrioDuiWu = "D:/resPrio/队伍/队伍.txt";
	private static String targetWordDuiWu = "队伍/n"; //目标词 歧义词
	private static int first_size_DuiWu = 30;	//第一个词个数
	private static int second_size_DuiWu = 54;  //sancengCode（除去null）的个数
	private static int third_size_DuiWu = 64;
	private static String first_emantic_DuiWu = "成员（Aj07）";
	private static String second_emantic_DuiWu = "团体  派别（Di10）";
	private static String third_emantic_DuiWu = "军队  战争（Di11）";
	private static int test_first_num_DuiWu = 10;
	private static int test_second_num_DuiWu = 9;
	private static int test_third_num_DuiWu = 3;

	//------------------表面---------------------
	private static String fileNameTrainBiaomian = "D:/DDSC_Beat2.0/表面/表面.txt";	//初始数据集-训练语料-气息
	private static String fileNameTestBiaomian = "D:/DDSC_Beat2.0/表面/表面test.txt"; //初始数据集-测试语料-气息
	private static String fileNameresPrioBiaomian = "D:/resPrio/表面/表面.txt";
	private static String targetWordBiaomian = "表面/n"; //目标词 歧义词
	private static int first_size_Biaomian = 20;	//第一个词个数
	private static int second_size_Biaomian = 35;  //sancengCode（除去null）的个数
	private static int third_size_Biaomian = 55;
	private static String first_emantic_Biaomian= "表面_实质_根本(Dd08)";
	private static String second_emantic_Biaomian = "钟_表_漏刻(Bp31)";
	private static String third_emantic_Biaomian = "边_角_面_脊(Bc02)";
	private static int test_first_num_Biaomian = 10;
	private static int test_second_num_Biaomian = 9;
	private static int test_third_num_Biaomian = 3;

	//------------------叫---------------------
	private static String fileNameTrainJiao = "D:/DDSC_Beat2.0/叫/叫.txt";	//初始数据集-训练语料-气息
	private static String fileNameTestJiao = "D:/DDSC_Beat2.0/叫/叫test.txt"; //初始数据集-测试语料-气息
	private static String fileNameresPrioJiao = "D:/resPrio/叫/叫.txt";
	private static String targetWordJiao = "叫/n"; //目标词 歧义词
	private static int first_size_Jiao = 45;	//第一个词个数
	private static int second_size_Jiao = 85;  //sancengCode（除去null）的个数
	private static int third_size_Jiao = 124;
	private static String first_emantic_Jiao = "怂恿_指使_强迫_挟制(Hi56)";
	private static String second_emantic_Jiao = "做声_呼唤_吹口哨(Fc09)";
	private static String third_emantic_Jiao = "派遣_支使(Hc04)";
	private static int test_first_num_Jiao = 10;
	private static int test_second_num_Jiao = 9;
	private static int test_third_num_Jiao = 3;

	public static void getEnvirVar(String target){
		if(target.equals("天地")){
			Wsd.first_emantic = first_emantic_TianDi;
			Wsd.second_emantic = second_emantic_TianDi;
			Wsd.third_emantic = third_emantic_TianDi;
			Wsd.test_first_num = test_first_num_TianDi;
			Wsd.test_second_num = test_second_num_TianDi;
			Wsd.test_third = test_third_num_TianDi;
			
			Wsd.first_size = first_size_TianDi;
			Wsd.second_size = second_size_TianDi;
			Wsd.third_size = third_size_TianDi;
			
			Wsd.fileNameTrain = fileNameTrainTianDi;
			Wsd.fileNameTest = fileNameTestTianDi;
			Wsd.targetWord = targetWordTianDi;
			Wsd.fileNameresPrio = fileNameresPrioTianDi;
		}else if(target.equals("成立")){
			Wsd.first_emantic = first_emantic_ChengLi;
			Wsd.second_emantic = second_emantic_ChengLi;
			Wsd.third_emantic = third_emantic_ChengLi;
			Wsd.test_first_num = test_first_num_ChengLi;
			Wsd.test_second_num = test_second_num_ChengLi;
			Wsd.test_third = test_third_num_ChengLi;
			
			Wsd.first_size = first_size_ChengLi;
			Wsd.second_size = second_size_ChengLi;
			Wsd.third_size = third_size_ChengLi;
			
			Wsd.fileNameTrain = fileNameTrainChengLi;
			Wsd.fileNameTest = fileNameTestChengLi;
			Wsd.targetWord = targetWordChengLi;
			Wsd.fileNameresPrio = fileNameresPrioChengLi;
		}else if(target.equals("本")){
			Wsd.first_emantic = first_emantic_Ben;
			Wsd.second_emantic = second_emantic_Ben;
			Wsd.third_emantic = third_emantic_Ben;
			Wsd.test_first_num = test_first_num_Ben;
			Wsd.test_second_num = test_second_num_Ben;
			Wsd.test_third = test_third_num_Ben;
			
			Wsd.first_size = first_size_Ben;
			Wsd.second_size = second_size_Ben;
			Wsd.third_size = third_size_Ben;
			
			Wsd.fileNameTrain = fileNameTrainBen;
			Wsd.fileNameTest = fileNameTestBen;
			Wsd.targetWord = targetWordBen;
			Wsd.fileNameresPrio = fileNameresPrioBen;
		}else if(target.equals("补")){
			Wsd.first_emantic = first_emantic_Bu;
			Wsd.second_emantic = second_emantic_Bu;
			Wsd.third_emantic = third_emantic_Bu;
			Wsd.test_first_num = test_first_num_Bu;
			Wsd.test_second_num = test_second_num_Bu;
			Wsd.test_third = test_third_num_Bu;
			
			Wsd.first_size = first_size_Bu;
			Wsd.second_size = second_size_Bu;
			Wsd.third_size = third_size_Bu;
			
			Wsd.fileNameTrain = fileNameTrainBu;
			Wsd.fileNameTest = fileNameTestBu;
			Wsd.targetWord = targetWordBu;
			Wsd.fileNameresPrio = fileNameresPrioBu;
		}else if(target.equals("旗帜")){
			Wsd.first_emantic = first_emantic_QiZhi;
			Wsd.second_emantic = second_emantic_QiZhi;
			Wsd.third_emantic = third_emantic_QiZhi;
			Wsd.test_first_num = test_first_num_QiZhi;
			Wsd.test_second_num = test_second_num_QiZhi;
			Wsd.test_third = test_third_num_QiZhi;
			
			Wsd.first_size = first_size_QiZhi;
			Wsd.second_size = second_size_QiZhi;
			Wsd.third_size = third_size_QiZhi;
			
			Wsd.fileNameTrain = fileNameTrainQiZhi;
			Wsd.fileNameTest = fileNameTestQiZhi;
			Wsd.targetWord = targetWordQiZhi;
			Wsd.fileNameresPrio = fileNameresPrioQiZhi;
		}else if(target.equals("日子")){
			Wsd.first_emantic = first_emantic_RiZi;
			Wsd.second_emantic = second_emantic_RiZi;
			Wsd.third_emantic = third_emantic_RiZi;
			Wsd.test_first_num = test_first_num_RiZi;
			Wsd.test_second_num = test_second_num_RiZi;
			Wsd.test_third = test_third_num_RiZi;
			
			Wsd.first_size = first_size_RiZi;
			Wsd.second_size = second_size_RiZi;
			Wsd.third_size = third_size_RiZi;
			
			Wsd.fileNameTrain = fileNameTrainRiZi;
			Wsd.fileNameTest = fileNameTestRiZi;
			Wsd.targetWord = targetWordRiZi;
			Wsd.fileNameresPrio = fileNameresPrioRiZi;
		}else if(target.equals("赶")){
			Wsd.first_emantic = first_emantic_Gan;
			Wsd.second_emantic = second_emantic_Gan;
			Wsd.third_emantic = third_emantic_Gan;
			Wsd.test_first_num = test_first_num_Gan;
			Wsd.test_second_num = test_second_num_Gan;
			Wsd.test_third = test_third_num_Gan;
			
			Wsd.first_size = first_size_Gan;
			Wsd.second_size = second_size_Gan;
			Wsd.third_size = third_size_Gan;
			
			Wsd.fileNameTrain = fileNameTrainGan;
			Wsd.fileNameTest = fileNameTestGan;
			Wsd.targetWord = targetWordGan;
			Wsd.fileNameresPrio = fileNameresPrioGan;
		}else if(target.equals("队伍")){
			Wsd.first_emantic = first_emantic_DuiWu;
			Wsd.second_emantic = second_emantic_DuiWu;
			Wsd.third_emantic = third_emantic_DuiWu;
			Wsd.test_first_num = test_first_num_DuiWu;
			Wsd.test_second_num = test_second_num_DuiWu;
			Wsd.test_third = test_third_num_DuiWu;
			
			Wsd.first_size = first_size_DuiWu;
			Wsd.second_size = second_size_DuiWu;
			Wsd.third_size = third_size_DuiWu;
			
			Wsd.fileNameTrain = fileNameTrainDuiWu;
			Wsd.fileNameTest = fileNameTestDuiWu;
			Wsd.targetWord = targetWordDuiWu;
			Wsd.fileNameresPrio = fileNameresPrioDuiWu;
		}else if(target.equals("挑")){
			Wsd.first_emantic = first_emantic_Tiao;
			Wsd.second_emantic = second_emantic_Tiao;
			Wsd.third_emantic = third_emantic_Tiao;
			Wsd.test_first_num = test_first_num_Tiao;
			Wsd.test_second_num = test_second_num_Tiao;
			Wsd.test_third = test_third_num_Tiao;
			
			Wsd.first_size = first_size_Tiao;
			Wsd.second_size = second_size_Tiao;
			Wsd.third_size = third_size_Tiao;
			
			Wsd.fileNameTrain = fileNameTrainTiao;
			Wsd.fileNameTest = fileNameTestTiao;
			Wsd.targetWord = targetWordTiao;
			Wsd.fileNameresPrio = fileNameresPrioTiao;
		}else if(target.equals("长城")){
			Wsd.first_emantic = first_emantic_ChangCheng;
			Wsd.second_emantic = second_emantic_ChangCheng;
			Wsd.third_emantic = third_emantic_ChangCheng;
			Wsd.test_first_num = test_first_num_ChangCheng;
			Wsd.test_second_num = test_second_num_ChangCheng;
			Wsd.test_third = test_third_num_ChangCheng;
			
			Wsd.first_size = first_size_ChangCheng;
			Wsd.second_size = second_size_ChangCheng;
			Wsd.third_size = third_size_ChangCheng;
			
			Wsd.fileNameTrain = fileNameTrainChangCheng;
			Wsd.fileNameTest = fileNameTestChangCheng;
			Wsd.targetWord = targetWordChangCheng;
			Wsd.fileNameresPrio = fileNameresPrioChangCheng;
		}else if(target.equals("叫")){
			Wsd.first_emantic = first_emantic_Jiao;
			Wsd.second_emantic = second_emantic_Jiao;
			Wsd.third_emantic = third_emantic_Jiao;
			Wsd.test_first_num = test_first_num_Jiao;
			Wsd.test_second_num = test_second_num_Jiao;
			Wsd.test_third = test_third_num_Jiao;

			Wsd.first_size = first_size_Jiao;
			Wsd.second_size = second_size_Jiao;
			Wsd.third_size = third_size_Jiao;

			Wsd.fileNameTrain = fileNameTrainJiao;
			Wsd.fileNameTest = fileNameTestJiao;
			Wsd.targetWord = targetWordJiao;
			Wsd.fileNameresPrio = fileNameresPrioJiao;
		}
	}
}
