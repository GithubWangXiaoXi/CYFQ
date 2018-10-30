package com.wangxiaoxi1;

import com.CYXQ.TreeShow.TreeItemNormal;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class code_0005_test {

    /**得到目标词最终的词义的代码，左右代码，以及左右的代码对应的词义*/
    //其实TreeItemNormal已经将语义树构建好了，包括目标词词义代码的左右代码，以及左右代码是否存在词义

    //测试得到各目标词最终输出的词义
    @Test
    public void test1(){
        String sentence[] = {"十九","中央","第二轮","巡视","将对","26个","地方","和","单位","党组织","开展","脱贫","攻坚","专项","巡视"};
        String keywords[] = {"中央","单位","开展","专项"};

        String[] result = new String[keywords.length];
        String keyword = null;
        for(int i = 0; i < keywords.length; i++){

            keyword = keywords[i];
            result[i] = code_0004_cupusFileHandler.wsdFeit(keyword,sentence);
        }

        showString(keywords,result);
    }

    @Test
    public void test3(){

        String sentence[] = {"十九","届","中央","第二轮","巡视","将对","26个","地方","和","单位","党组织","开展","脱贫","攻坚","专项","巡视"};
        String keywords[] = {"中央","单位","开展","专项"};

        String[] result = new String[keywords.length];
        String keyword = null;
        for(int i = 0; i < keywords.length; i++){

            keyword = keywords[i];
            result[i] = code_0004_cupusFileHandler.wsdFeit(keyword,sentence);
        }

        showString(keywords,result);

        List<String> threeCode = getThreeCode(result);
        System.out.println(threeCode);
        List<String> THES = code_0004_cupusFileHandler.getTHES_TXT("D:/THES.TXT");

        TreeItemNormal treeItemNormal = new TreeItemNormal();
        //得到元素为hashMap的list集合
        List res = treeItemNormal.normal(THES,threeCode);
        System.out.println("**********************************************");
        showList(res);
    }



    public void showString(String[] keywords, String[] strings){

        for(int i = 0; i < keywords.length; i++){
            System.out.print(keywords[i] + " : " + strings[i] +"  ");
            System.out.println();
        }
    }


    public List<String> getThreeCode(String[] result){

        List<String> threeCode = new ArrayList<>();
        int index = 0;
        String code = null;

        for(String str : result){

            index = str.indexOf("(");
            code = str.substring(index+1,index+5);
            threeCode.add(code);

        }

        return threeCode;
    }

    public void showList(List list){

        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }


}
