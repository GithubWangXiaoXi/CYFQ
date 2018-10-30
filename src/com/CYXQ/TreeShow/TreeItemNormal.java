package com.CYXQ.TreeShow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeItemNormal {
	private List treeData = new ArrayList();
	private String threeCodeTemp_1 = "";
	private String threeCodeTemp_2 = "";
	private String threeCodeTemp_3 = "";
	private String firstLayer = "";
	private String secondLayer = "";
	private String thirdLayer = "";
	
	public List normal(List<String> THES ,List<String> threeCode){
		if(threeCode!=null&&threeCode.size()>0){
			treeData.clear();
			String[] cinLinTemp;
			boolean firstIsHandle = false;
			boolean secondIsHandle = false;
			boolean thirdIsHandle = false;
			String thirdLayer_1;
			String thirdLayer_2;
			String cl ;
			String thirdLayerItem = " 空";
			System.out.println("threeCode:"+threeCode);
			
			for(int i=0;i<threeCode.size();i++){

				Map treeItem_1 = new HashMap();
				firstIsHandle = false;
				secondIsHandle = false;
				thirdIsHandle = false;
				threeCodeTemp_1 = "";
				threeCodeTemp_2 = "";
				threeCodeTemp_3 = "";
				threeCodeTemp_1 = threeCode.get(i);
				firstLayer = threeCodeTemp_1.substring(0,1);
				secondLayer = threeCodeTemp_1.substring(1, 2);
				thirdLayer = threeCodeTemp_1.substring(2, 4);
				thirdLayer_1 = thirdLayer.substring(0,1);
				thirdLayer_2 = thirdLayer.substring(1, 2);
				threeCodeTemp_2 = firstLayer+secondLayer+thirdLayer_1+(Integer.parseInt(thirdLayer_2)-1);
				threeCodeTemp_3 = firstLayer+secondLayer+thirdLayer_1+(Integer.parseInt(thirdLayer_2)+1);
				System.out.println(threeCodeTemp_2+"_____"+threeCodeTemp_3);
				
				for(int j=0;j<THES.size();j++){
					cl = THES.get(j);
					if(cl.contains(threeCodeTemp_1) && !firstIsHandle){
						cinLinTemp = cl.substring(cl.indexOf("/")+1).split(" ");
						for(int k=0;k<cinLinTemp.length;k++){
							if(cinLinTemp[k].contains(threeCodeTemp_1)){
								thirdLayerItem = cinLinTemp[k].substring(0, cinLinTemp[k].indexOf("("));
								thirdLayerItem = thirdLayerItem.replace("_", " ");
							}
						}
						treeItem_1.put("danceng", firstLayer);
						treeItem_1.put("erceng", secondLayer);
						treeItem_1.put("sanceng1", thirdLayer+"#"+thirdLayerItem);
						firstIsHandle = true;
					}
					if(cl.contains(threeCodeTemp_2) && !secondIsHandle){
						cinLinTemp = cl.substring(cl.indexOf("/")+1).split(" ");
						for(int k=0;k<cinLinTemp.length;k++){
							if(cinLinTemp[k].contains(threeCodeTemp_2)){
								thirdLayerItem = cinLinTemp[k].substring(0, cinLinTemp[k].indexOf("("));
								thirdLayerItem = thirdLayerItem.replace("_", " ");
							}
						}
						treeItem_1.put("sanceng2",thirdLayer_1+(Integer.parseInt(thirdLayer_2)-1)+"#"+thirdLayerItem);
						secondIsHandle = true;
					}
					if(cl.contains(threeCodeTemp_3) && !thirdIsHandle){
						cinLinTemp = cl.substring(cl.indexOf("/")+1).split(" ");
						for(int k=0;k<cinLinTemp.length;k++){
							if(cinLinTemp[k].contains(threeCodeTemp_3)){
								thirdLayerItem = cinLinTemp[k].substring(0, cinLinTemp[k].indexOf("("));
								thirdLayerItem = thirdLayerItem.replace("_", " ");
							}
						}
						treeItem_1.put("sanceng3",thirdLayer_1+(Integer.parseInt(thirdLayer_2)+1)+"#"+thirdLayerItem);
						thirdIsHandle = true;
					}
					if(firstIsHandle && secondIsHandle && thirdIsHandle){
						treeData.add(treeItem_1);
						break;
					}
				}
				if(!secondIsHandle){
					treeItem_1.put("sanceng2",thirdLayer_1+(Integer.parseInt(thirdLayer_2)-1)+"#"+"无");
				}
				if(!thirdIsHandle){
					treeItem_1.put("sanceng3",thirdLayer_1+(Integer.parseInt(thirdLayer_2)+1)+"#"+"无");
				}
				if(!(firstIsHandle && secondIsHandle && thirdIsHandle)){
					treeData.add(treeItem_1);
				}
			}
			
		}
		return treeData;
	}

}
