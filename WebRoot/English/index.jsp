<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.CYXQ.TreeShow.TreeData" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>WSD System</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">   
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript">
		function check(){
			var wds = document.getElementsByName("word");
			if(wds==null){
				alert("输入区不能为空值");
				return false;
			}else{
				return true;
			}
		}	
	</script>
	
	<style type="text/css">
#customers
  {
  font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
  width:80%;
  border-collapse:collapse;
  }

#customers td, #customers th 
  {
  font-size:1em;
  border:1px solid #98bf21;
  padding:3px 7px 2px 7px;
  }

#customers th 
  {
  font-size:1.1em;
  text-align:left;
  padding-top:5px;
  padding-bottom:4px;
  background-color:#A7C942;
  color:#ffffff;
  }

#customers tr.alt td 
  {
  color:#000000;
  background-color:#EAF2D3;
  }
</style>
	
  </head>
  
  <body><div align="center"><font size="6"><strong> WSD System Based on Bayesian</strong></font> <br> 
  
   <form action="servlet/Cyxq" method="post" onsubmit="return check()" ><div align="left"> 
    <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
        Input a sentence: <br> <br> 
        <div align="center"><textarea rows="3" cols="55" name="word">${requestScope.word }</textarea>  
        <input type="submit" value="Disambiguate" name="XQ1" > 
        <br><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
                     Word segmentation result: <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                     &nbsp;&nbsp; 
                     <%
                     List list = (List)request.getAttribute("fclist"); 
                     if(list!=null&&list.size()>0){ 
	                      	for(int i=0;i<list.size();i++){
	                      		Map map = (Map)list.get(i);
	                      		%>
	                      		<%=map.get("diffword") %>&nbsp;&nbsp;&nbsp;
	                      		<%
                     }
                     }
                      %>
                 <br></div> 
        <br><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
                     Word segmentation in detail: <br></div> <br>
        </div>
        <table id="customers" align="center">
        <tr>
	       <th  align="center" width="100"><strong>Word segmentation result</strong></th> 
           <th  align="center" width="100"><strong>Semantic categories</strong></th>
           <th  align="center"><strong>Vocabularies in the same semantic category</strong> </th>
        </tr>
                      <%  
                      	List yylList = (List)request.getAttribute("yyllist");
                      	int j = 0;//遍历每个语义类
                      	int k = 0;//用以控制每个分词第一次处理时的页面显示
                      	if(list!=null&&list.size()>0){ 
	                      	for(int i=0;i<list.size();i++){
	                      	    k = 0; 
	                      		Map map = (Map)list.get(i); 
                      %> 
                        	 <tr>
								<td align="left" width="100">
									<%=(i+1)+"."+map.get("diffword")%>
								</td>
						<% 
						        if(Integer.parseInt((map.get("num")).toString()) == 0){
						 %>
						            <td align="left" width="100">
									  NULL
								   </td>
								   <td align="left" >
									  NULL
								   </td>
						<%
						 }        else {
						            if(yylList!=null&&yylList.size()>0){ 
	                      	            for(;j<yylList.size();j++,k++){ 
	                      		            Map yylmap = (Map)yylList.get(j);
	                      		            if( k==0){//每个分词的第一次处理
	                     %>
	                                             <td align="left" width="100">
									                 <%=yylmap.get("wcode") %>
								                 </td>
								                 <td align="left" >
									                 <%=yylmap.get("wtype") %>
								                 </td>
	                      <%
	                      		            }
	                      		            else{
	                      		               if((k=Integer.parseInt((yylmap.get("num")).toString())) == (i+1)){
	                       %>
	                                             <tr>
	                                             <td align="left" width="100">
									                 
								                 </td>
								                 <td align="left" width="100">
									                 <%=yylmap.get("wcode") %>
								                 </td>
								                 <td align="left" >
									                 <%=yylmap.get("wtype") %>
								                 </td>
								                 </tr>
	                       <%
	                      		               }else{break;}
	                      		            }
	                      		        }
						         }   
						 %>
						 <%
						        }
						%>        
	                      <%
	                         }
	                        }
	                      %>                 
	        </table>  
	        
	        
        </div>
   </form >
   
    <br><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
                     Disambiguation result（Select an vocabulary and submit the query）: <br>
         <form method="post" action="servlet/Cyxq" >
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <%
             if(list!=null&&list.size()>0){ 
	             for(int i=0;i<list.size();i++){
	                Map map = (Map)list.get(i);
	                int m = Integer.parseInt((map.get("num")).toString());
	                if(m >= 2){
	     %>
	                    <input type="checkbox" name="Ambiguity" value="<%=map.get("diffword")%>">
	                    <%=map.get("diffword") %>&nbsp;&nbsp;&nbsp;
	     <%
	                }      		
                 }
         %>
                 <input type="submit" value="贝叶斯消歧" name="XQ2" align="right">
         <%
             }
         %>
         
        <table id="customers" align="center">
        <tr>
           <th  align="center" width="200"><strong>Ambiguous words</strong></th>
           <th  align="center"><strong>The result of Disambiguation</strong> </th>
        </tr> 
				<%
                     List wsd = (List)request.getAttribute("wsd");
					 if(wsd!=null&&wsd.size()>0){ 
	                     for(int w=0;w<wsd.size();w++){ 
	                      	Map wsdMap = (Map)wsd.get(w);
	            %>
	                            <tr>
								  <td align="left" width="200">
									 <%=wsdMap.get("word") %>
								  </td>
								  <td align="left" >
									 <%=wsdMap.get("trueEman") %>
								  </td>
								</tr>
				<%
						 }
				%>        
	            <%
	                 }
	                        
	            %>                 
	        </table> 
	        <%
	           if(wsd!=null&&wsd.size()>0){
	                  List treeList = (List)request.getAttribute("treeList");
	                  System.out.println(treeList);
                      int baseX = 150;
                      int showLength = 1050;
                      System.out.println("hello");
                      if(treeList!=null&&treeList.size()>0){ 
                      
	        %>
	                 <br><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
                                                               Semantic Tree: <br>
	                  <svg xmlns="http://www.w3.org/2000/svg" version="1.1" width="1300" height="500">
                         <ellipse cx="675" cy="20" rx="25" ry="15" style="fill:white;text-align:center;stroke:black;stroke-width:2" />
                           <text x='670' y='25'   textLength='20'>Root</text>
           
           <% 
                         //判断传入参数个数，大于5时另起一行
                         if(treeList.size()<=5){
                            int dancengNum = 0;//记录到底有几个字符
                            String temp = "";
                            TreeData[] fl = new TreeData[5];
                            fl[0] = new TreeData(); 
                            fl[1] = new TreeData(); 
                            fl[2] = new TreeData(); 
                            fl[3] = new TreeData(); 
                            fl[4] = new TreeData(); 
                       
                            fl[0].init();
                            fl[1].init();
                            fl[2].init();
                            fl[3].init();
                            fl[4].init();
                            //对一层进行去重，纪录被去重的字母，计算每一字符坐标
                            for(int ti=0;ti<treeList.size();ti++){
                                Map treeMap = (Map)treeList.get(ti);
                                if(!temp.contains(treeMap.get("danceng").toString())){
                                   temp = temp +treeMap.get("danceng").toString() +" ";
                                   fl[dancengNum].setLabel(treeMap.get("danceng").toString());
                                   fl[dancengNum].numAdd();
                                   dancengNum++;
                                }else{
                                   for(int tk=0;tk<dancengNum;tk++){
                                      if(fl[tk].getLabel().equals(treeMap.get("danceng").toString())){
                                         fl[tk].numAdd();
                                      }
                                   }
                                }
                             }
                        
                             //计算一层各点的X坐标
                             int xCountTemp = 0;
                             for(int tm=0;tm<dancengNum;tm++){
                                if(fl[tm].getNum() > 1){
                                   fl[tm].setXCoordinate((int)(baseX+(showLength/(treeList.size()+1))*(xCountTemp+1)+(showLength/(treeList.size()+1))*((float)(fl[tm].getNum()-1)/2)));
                                   //System.out.println((float)fl[m].getNum()/2+(float)xCountTemp);
                                }else if(fl[tm].getNum() == 1){
                                   if(tm == 0){
                                      fl[tm].setXCoordinate((int)(baseX+(showLength/(treeList.size()+1))));
                                   }else {fl[tm].setXCoordinate((int)(baseX+(xCountTemp+1)*(showLength/(treeList.size()+1))));}
                                }
                                xCountTemp = xCountTemp+fl[tm].getNum();
                             }
                             //布置一层字母for循环
                             for(int tj=0;tj<dancengNum;tj++){
                      System.out.println("boy");
            %>                 
                                <text x='<%=fl[tj].getXCoordinate() %>' y='80'   textLength='20'><%=fl[tj].getLabel() %></text>
	                            <line x1='<%=fl[tj].getXCoordinate() %>' y1='68' x2='675' y2='35' style='stroke:rgb(99,99,99);stroke-width:1'/>
            <%               
                             }
                             //根据一层坐标布置二层三层及字义
	                         for(int ti=0;ti<treeList.size();ti++){
	                           	Map treeMap = (Map)treeList.get(ti);
	                           	int xdanceng = 0;
	                           	for(int tv=0;tv<dancengNum;tv++){
	                           	   if(treeMap.get("danceng").equals(fl[tv].getLabel())){
	                           	      xdanceng = fl[tv].getXCoordinate();
	                           	   }
	                           	}
	       %>
	                             <text x='<%=baseX+(showLength/(treeList.size()+1))*(ti+1) %>' y='140'   textLength='20'><%=treeMap.get("erceng") %></text>
	                          
	                               <line x1='<%=xdanceng %>' y1='82' x2='<%=baseX+(showLength/(treeList.size()+1))*(ti+1)-2 %>' y2='130' style='stroke:rgb(99,99,99);stroke-width:1'/>
	                                 <text x='<%=baseX+(showLength/(treeList.size()*3+1))*(3*ti+1) %>' y='200'   textLength='20'><%=treeMap.get("sanceng1").toString().split("#")[0] %></text>
	                                   <line x1='<%=baseX+(showLength/(treeList.size()*3+1))*(3*ti+1)+5 %>' y1='190' x2='<%=baseX+(showLength/(treeList.size()+1))*(ti+1)+5 %>' y2='142' style='stroke:rgb(99,99,99);stroke-width:1'/>
	                                      <rect x="<%=baseX+(showLength/(treeList.size()*3+1))*(3*ti+1)-20 %>" y="220" width="50" height="100"style="fill:white; stroke:black; stroke-width:1; fill-opacity:0.1; stroke-opacity:0.9"/>
	                                      <foreignObject x="<%=baseX+(showLength/(treeList.size()*3+1))*(3*ti+1)-18 %>" y="220" width = '50' height='100' style="font"><%=treeMap.get("sanceng1").toString().split("#")[1] %></foreignObject>
	                                 <text x='<%=baseX+(showLength/(treeList.size()*3+1))*(3*ti+2) %>' y='200'   textLength='20'><%=treeMap.get("sanceng2").toString().split("#")[0] %></text>
	                                   <line x1='<%=baseX+(showLength/(treeList.size()*3+1))*(3*ti+2)+5 %>' y1='190' x2='<%=baseX+(showLength/(treeList.size()+1))*(ti+1)+5 %>' y2='142' style='stroke:rgb(99,99,99);stroke-width:1'/>
	                                      <rect x="<%=baseX+(showLength/(treeList.size()*3+1))*(3*ti+2)-20 %>" y="220" width="50" height="100"style="fill:white; stroke:black; stroke-width:1; fill-opacity:0.1; stroke-opacity:0.9"/>
	                                      <foreignObject x="<%=baseX+(showLength/(treeList.size()*3+1))*(3*ti+2)-18 %>" y="220" width = '50' height='100' style="font"><%=treeMap.get("sanceng2").toString().split("#")[1] %></foreignObject>
	                                 <text x='<%=baseX+(showLength/(treeList.size()*3+1))*(3*ti+3) %>' y='200'   textLength='20'><%=treeMap.get("sanceng3").toString().split("#")[0] %></text>
	                                   <line x1='<%=baseX+(showLength/(treeList.size()*3+1))*(3*ti+3)+5 %>' y1='190' x2='<%=baseX+(showLength/(treeList.size()+1))*(ti+1)+5 %>' y2='142' style='stroke:rgb(99,99,99);stroke-width:1'/>
	                                      <rect x="<%=baseX+(showLength/(treeList.size()*3+1))*(3*ti+3)-20 %>" y="220" width="50" height="100"style="fill:white; stroke:black; stroke-width:1; fill-opacity:0.1; stroke-opacity:0.9"/>
	                                      <foreignObject x="<%=baseX+(showLength/(treeList.size()*3+1))*(3*ti+3)-18 %>" y="220" width = '50' height='100' style="font"><%=treeMap.get("sanceng3").toString().split("#")[1] %></foreignObject>
	       <%
                             }
                          }
                       }
	                }
           %>
	              
         </form>
         </div>
</html>
