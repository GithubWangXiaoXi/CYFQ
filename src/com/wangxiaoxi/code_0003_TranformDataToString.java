package com.wangxiaoxi;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class code_0003_TranformDataToString {

    //要有顺序的输出map信息，需要有序的list
    public static String tranformFenCiToString(Map<String,Integer> wordsMap, List<String> wordListRemovedDuplicate){

        StringBuffer stringBuffer = new StringBuffer();
        Map<String,Double> ratesMap = code_0002_utils.getRate(wordsMap,wordListRemovedDuplicate.size());

        for(String word:wordListRemovedDuplicate){

            String str = word+"|"+wordsMap.get(word)+"|"+ratesMap.get(word);
            stringBuffer.append(str).append("\n");
        }
        return stringBuffer.toString();
    }

    //wordListRemovedDuplicate只是用来求概率用
    public static String tranformSanCengToString(Map<String,Integer> sancengCodeMap, List<String>sancengCodeListRemovedDuplicate,
                                                 List<String> wordListRemovedDuplicate){

        StringBuffer stringBuffer = new StringBuffer();
        Map<String,Double> ratesMap = code_0002_utils.getRate(sancengCodeMap,wordListRemovedDuplicate.size());

        for(String word:sancengCodeListRemovedDuplicate){

            String str = word+"|"+sancengCodeMap.get(word)+"|"+ratesMap.get(word);
            stringBuffer.append(str).append("\n");
        }
        return stringBuffer.toString();
    }

    public static String tranformDanCengToString(Map<String,Integer> dancengCodeMap, List<String> dancengCodeListRemovedDuplicate,
                                                 List<String> wordListRemovedDuplicate){

        StringBuffer stringBuffer = new StringBuffer();
        Map<String,Double> ratesMap = code_0002_utils.getRate(dancengCodeMap,wordListRemovedDuplicate.size());

        for(String word:dancengCodeListRemovedDuplicate){

            String str = word+"|"+dancengCodeMap.get(word)+"|"+ratesMap.get(word);
            stringBuffer.append(str).append("\n");
        }
        return stringBuffer.toString();
    }

    public static void writeInfoIntoFile(String fileInfo,String filename){
        String fileDirPath = "D:/test/resPrio/"+filename+"/";

        //先创建文件夹
        File file = new File(fileDirPath);
        if(!file.exists()){
            file.mkdirs();
        }

        String filePath = "D:/test/resPrio/"+filename+"/"+filename+".txt";
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(filePath);
            IOUtils.write(fileInfo,output,"utf-8");
            System.out.println("文件创建成功");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<String> getListFiles(String dirPath){

        File fileDir = new File(dirPath);
        File[] files = fileDir.listFiles();

        List<String> fileList = new ArrayList<>();

        for(int i = 0;i<files.length;i++){
            File file = files[i];
            if(file.isDirectory()){
                //将文件夹下的文件名添加到fileList列表中
                fileList.add(file.getName());
            }
        }

        return fileList;
    }
}
