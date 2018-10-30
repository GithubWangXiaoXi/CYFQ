package com.CYXQ.TreeShow;

public class TreeData {

	private String label = "";
	private int num = 0;
	private int xCoordinate = 0;
	
	public void init(){
		label = "";
		num=0;
		xCoordinate=0;
	}
	
	public void setLabel(String l){
		label = l;
	}
	
	public void setNum(int n){
		num = n;
	}
	
	public void setXCoordinate(int x){
		xCoordinate = x;
	}
	
	public String getLabel(){
		return label;
	}
	
	public int getNum(){
		return num;
	}
	
	public int getXCoordinate(){
		return xCoordinate;
	}
	
	public void numAdd(){
		this.num++;
	}

}
