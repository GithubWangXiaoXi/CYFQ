package com.wangxiaoxi;

import java.text.DecimalFormat;
import java.util.*;

public class code_0002_utils {

    public static boolean isNumeric(String content){
        for(int i = 0;i<content.length();i++){
            if(!Character.isDigit(content.charAt(i))){
                return false;
            }
        }
        return true;
    }

    //计算分词，三层，单层出现的概率(给总数和各自单词的出现次数)
    public static <T> Map<T,Double> getRate(Map<T,Integer> words,int size){
        Set<T> keys = words.keySet();
        Map<T,Double> wordsRateMap = new HashMap<>();
        double total = size-1;

        for(T key:keys){
            double numPerWord = (double)words.get(key);
            double rate = numPerWord/total;
            //先将double格式化成字符串
            DecimalFormat pattern = new DecimalFormat("#.000000");
            rate = Double.parseDouble(pattern.format(rate));
            wordsRateMap.put(key,rate);
        }
        return wordsRateMap;
    }

    public static void showContent(String[] content){
        for(String part : content){
            System.out.print(part+"|");
        }
        System.out.println();
    }

    public static void showList(List<String> content){
        for(String part : content){
            System.out.print(part+"|");
        }
        System.out.println();
    }

    public static void showList1(List<String> content){

        int times = 50;
        for(String part : content){
            if(times<0){
                break;
            }
            System.out.print(part+"|");
            times--;
        }
        System.out.println();
    }

    public static void showMap(Map<String,String> content){
        Set<String> keys = content.keySet();
        int times = 50;

        for(String key : keys){
            if(times<0){
                break;
            }
            System.out.print(key+"|");
            times--;
        }
        System.out.println();
    }

    public static void showMap1(Map<String,String> content,List<String> list){

        for(String key:list){
            System.out.println(key+"|"+content.get(key));
        }

        System.out.println();
    }

    public static void showMap2(Map<String,String> content){

        Set<String> keyset = content.keySet();
        for(String key:keyset){
            System.out.println(key+"|"+content.get(key));
        }

        System.out.println();
    }

    public static void showfenciInfoByListOrder(Map<String,Integer> words,List<String> list){

        System.out.println("-=-=-=-=-=-=-=-==-=-=-=-==-=-=-=-=-=-==-=-=-=-分词");
        int result = 0;

        Map<String,Double> ratesMap = code_0002_utils.getRate(words,words.size());

        for(String key:list){
            System.out.println(key+"|"+words.get(key)+"|"+ratesMap.get(key));
            result+=words.get(key);
        }

        System.out.println(words.size());
        System.out.println("result:"+result);
    }

    public static void showSanCengInfoByListOrder(Map<String,Integer> codeMap,List<String> list,List<String> wordsListRemovedDuplicate){
        System.out.println("-=-=-=-=-=-=-=-==-=-=-=-==-=-=-=-=-=-==-=-=-=-三层");
        int result = 0;

        Map<String,Double> ratesMap = code_0002_utils.getRate(codeMap,wordsListRemovedDuplicate.size());

        for(String key:list){
            System.out.println(key+"|"+codeMap.get(key)+"|"+ratesMap.get(key));
            result+=codeMap.get(key);
        }

        System.out.println("codeMap size:"+codeMap.size());
        System.out.println("result:"+result);
    }

    public static void showDanCengInfoByListOrder(Map<String,Integer> monoCodeMap,List<String> list,List<String> wordsListRemovedDuplicate){
        System.out.println("-=-=-=-=-=-=-=-==-=-=-=-==-=-=-=-=-=-==-=-=-=-单层");

        int result = 0;
        Map<String,Double> ratesMap = code_0002_utils.getRate(monoCodeMap,wordsListRemovedDuplicate.size());


        for(String character : list){
            System.out.println(character+"|"+monoCodeMap.get(character)+"|"+ratesMap.get(character));
            result+=monoCodeMap.get(character);
        }

        System.out.println("monoCodeMap size:"+monoCodeMap.size());
        System.out.println("result:"+result);
    }

    public static List<String> getSancengCodeListRemovedDuplicate(Map<String,String> map,List<String> wordListRemovedDuplicate){
        List<String> list = new ArrayList<>();
        for(String str:wordListRemovedDuplicate){
            String code = map.get(str);
            if(code == null){
                code = "null";
            }
            if(!list.contains(code)){
                list.add(code);
            }
        }
        return list;
    }

    public static void showTriAndMono(List<String>sancengCodeListRemovedDuplicate,Map<String,Integer> monoCodeMap){
        for(String triCode:sancengCodeListRemovedDuplicate){
            String character = String.valueOf(triCode.charAt(0));
            if(triCode.equals("null")){
                character = "null";
            }
            System.out.println(triCode+"|"+character+"|"+monoCodeMap.get(character));
        }
        System.out.println("--------------------------------------");
    }
}
