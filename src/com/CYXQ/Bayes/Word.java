package com.CYXQ.Bayes;

public class Word {
	/**
	 * 目标词|左分词|右分词|左分词词义集合|右分词词义集合|
	   中医|钻研|理论|分析_推敲_研究_考证(Hg14)|争论_辩论_议论(Hi41) 学说_学问_知识(Dk02)|

	   左分词词义以及词义代码|左分词词义|左分词单层|左分词三层|
	   分析_推敲_研究_考证(Hg14)|分析_推敲_研究_考证|H|Hg14|

	   右分词词义以及词义代码|右分词词义|右分词单层|右分词三层
	   争论_辩论_议论(Hi41)|争论_辩论_议论|H|Hi41
	 */

	private String word; //目标词
	private String left; //左分词
	private String right;//右分词
	private String result; //分词结果
	
	private String leftMeaning; //左分词同义词词林词语集合
	private String rightMeaning;//右分词同义词词林词语集合
	
	private String leftMeaningTrue;//左分词同义词词林词语（通过Dice系数取得）
	private String rightMeaningTrue;//右分词同义词词林词语（通过Dice系数取得）
	
	private String leftMeaningTrueCwn; //左分词同义词词林词语（通过Dice系数取得）汉语
	private String rightMeaningTrueCwn;//右分词同义词词林词语（通过Dice系数取得）汉语
	
	private String leftMeaningTrueEng_1; //左分词同义词词林词语（通过Dice系数取得）词义代码单层
	private String rightMeaningTrueEng_1;//右分词同义词词林词语（通过Dice系数取得）词义代码单层
	
	private String leftMeaningTrueEng_3; //左分词同义词词林词语（通过Dice系数取得）词义代码 3层
	private String rightMeaningTrueEng_3;//右分词同义词词林词语（通过Dice系数取得）词义代码 3层
	
	private int num = 1; //个数
	private double percent;	//概率
	/*
	 * get set
	 */
	public String getLeftMeaningTrue() {
		return leftMeaningTrue;
	}
	public void setLeftMeaningTrue(String leftMeaningTrue) {
		this.leftMeaningTrue = leftMeaningTrue;
	}
	public String getRightMeaningTrue() {
		return rightMeaningTrue;
	}
	public void setRightMeaningTrue(String rightMeaningTrue) {
		this.rightMeaningTrue = rightMeaningTrue;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getLeft() {
		return left;
	}
	public void setLeft(String left) {
		this.left = left;
	}
	public String getRight() {
		return right;
	}
	public void setRight(String right) {
		this.right = right;
	}
	public String getLeftMeaning() {
		return leftMeaning;
	}
	public void setLeftMeaning(String leftMeaning) {
		this.leftMeaning = leftMeaning;
	}
	public String getRightMeaning() {
		return rightMeaning;
	}
	public void setRightMeaning(String rightMeaning) {
		this.rightMeaning = rightMeaning;
	}
	public String getLeftMeaningTrueCwn() {
		return leftMeaningTrueCwn;
	}
	public void setLeftMeaningTrueCwn(String leftMeaningTrueCwn) {
		this.leftMeaningTrueCwn = leftMeaningTrueCwn;
	}
	public String getRightMeaningTrueCwn() {
		return rightMeaningTrueCwn;
	}
	public void setRightMeaningTrueCwn(String rightMeaningTrueCwn) {
		this.rightMeaningTrueCwn = rightMeaningTrueCwn;
	}
	public String getLeftMeaningTrueEng_1() {
		return leftMeaningTrueEng_1;
	}
	public void setLeftMeaningTrueEng_1(String leftMeaningTrueEng_1) {
		this.leftMeaningTrueEng_1 = leftMeaningTrueEng_1;
	}
	public String getRightMeaningTrueEng_1() {
		return rightMeaningTrueEng_1;
	}
	public void setRightMeaningTrueEng_1(String rightMeaningTrueEng_1) {
		this.rightMeaningTrueEng_1 = rightMeaningTrueEng_1;
	}
	public String getLeftMeaningTrueEng_3() {
		return leftMeaningTrueEng_3;
	}
	public void setLeftMeaningTrueEng_3(String leftMeaningTrueEng_3) {
		this.leftMeaningTrueEng_3 = leftMeaningTrueEng_3;
	}
	public String getRightMeaningTrueEng_3() {
		return rightMeaningTrueEng_3;
	}
	public void setRightMeaningTrueEng_3(String rightMeaningTrueEng_3) {
		this.rightMeaningTrueEng_3 = rightMeaningTrueEng_3;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
}
