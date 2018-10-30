package com.wangxiaoxi1;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class code_0004_cupusFileHandler {

    /**
     * 通过语料cupus文件统计目标词的不同语义所占的概率,以及后验概率
     */

    private static Map<String, Float> senseIfLeftAndRightExistMap = new HashMap<>();  //用来存左右分词存在时，目标词的词义,以及对应的先验概率
    private static List<String> cilinList = null;
    private static String[] keyword_sense = null;  //存储目标词的词义
    private static List<String> cupusWordList = null;  //存储语料文件的所有分词
    private static String[] selectedKeyword_sense = null;  //存储经过左右分词筛选后的目标词包含的词义

    private static String keyword = null;  //目标词
    private static String leftword = null;   //左分词
    private static String rightword = null;  //右分词

    private static StringBuffer wsdTemp = null;

    /**
     * firstStep: 通过THES.txt，获取目标词的语义
     **/
    //1.1读文件，将THES.txt中的信息保存在list中
    public static List<String> getTHES_TXT(String filename) {
        BufferedReader bufferedReader = null;
        List<String> wordAndSenseList = new ArrayList<>();

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "GBK"));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                wordAndSenseList.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return wordAndSenseList;
    }

    //1.2匹配list，得到目标词的语义
    public static String[] getSenseFromKeyword(List<String> list, String keyword) {

        //对分词按'/'进行拆分，用词语部分与keyword匹配，并返回后面的各个语义
        String senses = null;
        String word = null;
        String token[] = null;
        String[] s_senses = null;

        for (String str : list) {
            token = str.split("/");
            word = token[0].replace("#", "");

            //在THES中找到目标词，将语义以String数组形式返回
            if (word.equals(keyword)) {
                senses = token[1];
                s_senses = senses.split(" ");
                break;
            }
        }
        return s_senses;
    }

    /**
     * secondStep: 根据目标词及其各个语义，到cupus语料库中查找目标词出现的次数以及各个含义出现的次数
     **/
    //2.1 先对语料文件进行处理，每个句子作为list的元素，将语料内容以List的形式返回
    public static List<String> getCupusContent(String filename) {

        BufferedReader bufferedReader = null;
        List<String> list = new ArrayList<>();   //用list每次存一个句子

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "GBK"));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    //2.2 对语料内容按照空格切分,词语和编码用'-'连接,将结果以list的形式返回
    public static List<String> getCupusWordBySpace(List<String> content) {

        List<String> list = new ArrayList<>();
        for (String sentence : content) {
            String[] wordsAndCode = sentence.split(" ");
            String[] token = null;
            String code = null;
            String word = null;
            String word_code = null;

            for (String str : wordsAndCode) {
                token = str.split("/");

                if (token.length > 1) {
                    code = token[1];
                    word = token[0];

                    //如果编码是-1，则为无用的词，弃掉；如果是中文的标点符号，弃掉（还是不弃掉好,处理左右分词需要标点符号）
                    word_code = word + "-" + code;

//                    System.out.println(word);
                    list.add(word_code);
                }
            }
        }

        return list;
    }

    //2.3通过目标词，到语料文件中查找目标词的总次数，然后将各个含义出现的次数保存在数组中，并返回
    public static int[] getWordPriorCount(String keyword, String[] keyword_sense, List<String> cupusWordList) {

        int[] codeCount = new int[keyword_sense.length + 1]; //将总次数放在最后一位，其他位置上的数和keyword_sense保持一致
        String token[] = null;
        String code = null;
        String senseCode = null;
        String word = null;

        //先通过目标词获得语料中该词出现的总次数，并通过cupus的编码，统计对应编码出现的次数，将结果添加到数组的对应位置中
        for (int i = 0; i < cupusWordList.size(); i++) {

            token = cupusWordList.get(i).split("-");
            code = token[1];
            word = token[0];

            if (word.equals(keyword)) {
                for (int j = 0; j < keyword_sense.length; j++) {
                    int index = keyword_sense[j].indexOf("(");
                    senseCode = keyword_sense[j].substring(index + 1, index + 5);//获取语义中的代码

                    if (code.equals(senseCode)) {

                        int count = codeCount[j];
                        codeCount[j] = count + 1;

                        System.out.print(senseCode + "  ");

                        //输出一个小短语测试一下
                        if (i - 1 >= 0 && i + 1 <= cupusWordList.size() - 1) {
                            for (int k = i - 1; k <= i + 1; k++) {
                                String temp[] = cupusWordList.get(k).split("-");
                                System.out.print(temp[0]);
                            }
                        }

                        System.out.println();
                    }
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < codeCount.length - 1; i++) {
            sum += codeCount[i];
        }

        codeCount[codeCount.length - 1] = sum; //将总次数放在最后一位
        return codeCount;
    }

    //2.4 计算目标词不同词义出现的先验概率
    public static float[] getWordPriorProbability(int[] wordCount) {
        float[] wordSenseProbe = new float[wordCount.length - 1];

        int sum = wordCount[wordCount.length - 1];

        if (sum != 0) {
            for (int i = 0; i < wordSenseProbe.length; i++) {
                float probe = ((float) wordCount[i] / sum);
                wordSenseProbe[i] = probe;
            }
        }

        return wordSenseProbe;
    }


    /**
     * 计算后验概率- 左分词在左右包含目标词情况下出现的次数(L_count)/目标词的词义(Si) * 右分词在左右包含目标词情况下出现的次数(R_count)/目标词的词义(Si)
     * 注意：目标词的词义(Si) 前后必须包含句子中目标词的左右分词
     **/

    //3.1 统计句子目标词的左右分词分别左右包含目标词的情况下出现的次数,以及将左右分词存在时目标词的词义，以及对应词义的先验概率，保存在成员变量senseIfLeftAndRightExistMap的map<k,v>集合中。
    public static int getLeftAndRightCount(String keyword, String leftOrRightWord, List<String> cupusWordList, String[] keyword_sense, float[] wordSenseProbabilty) {

        int count = 0;
        String leftword = null;
        String rightword = null;

        //左右分词 + keyword
        for (int i = 0; i < cupusWordList.size(); i++) {
            String token[] = cupusWordList.get(i).split("-");
            String word = token[0];

            if (word.equals(keyword)) {
                if (i - 1 >= 0) {
                    String token1[] = cupusWordList.get(i - 1).split("-");
                    leftword = token1[0];
                    if (leftword.equals(leftOrRightWord)) {
                        System.out.print(leftword + "-" + keyword + "   ");

                        //输出一个小短语测试一下
                        for (int j = i - 1; j <= i + 1; j++) {
                            String temp[] = cupusWordList.get(j).split("-");
                            System.out.print(temp[0]);
                        }

                        System.out.print("  目标词:" + token[0] + "  词义:" + token[1]);
                        System.out.println();


                        //将包含左右分词的目标词的词义放在map集合中
                        //如果code为空，则不添加到map中
                        if (!token[1].trim().isEmpty()) {
                            senseIfLeftAndRightExistMap.put(token[1], new Float(0));
                        }

                        count++;
                    }
                }
            }
        }


//        System.out.println("-----------------------------------------");

        //keyword + 左右分词
        for (int i = 0; i < cupusWordList.size(); i++) {
            String token[] = cupusWordList.get(i).split("-");
            String word = token[0];

            if (word.equals(keyword)) {
                if (i + 1 <= cupusWordList.size() - 1) {
                    String token1[] = cupusWordList.get(i + 1).split("-");
                    rightword = token1[0];
                    if (rightword.equals(leftOrRightWord)) {
                        System.out.print(keyword + "-" + rightword + "  ");

                        //输出一个小短语测试一下
                        for (int j = i - 1; j <= i + 1; j++) {
                            String temp[] = cupusWordList.get(j).split("-");
                            System.out.print(temp[0]);
                        }

                        System.out.print("  目标词:" + token[0] + "  词义:" + token[1]);
                        System.out.println();

                        //将包含左右分词的目标词的词义放在map集合中
                        if (!token[1].trim().isEmpty()) {
                            senseIfLeftAndRightExistMap.put(token[1], new Float(0));
                        }
                        count++;
                    }
                }
            }
        }

        int index = 0;
        String code = null;
        //将含义左右分词的目标词的词义的先验概率保存在map的value中
        if (senseIfLeftAndRightExistMap.size() != 0) {
            for (int i = 0; i < keyword_sense.length; i++) {

                index = keyword_sense[i].indexOf("(");
                code = keyword_sense[i].substring(index + 1, index + 5);
                if (senseIfLeftAndRightExistMap.get(code) != null) {

//                    System.out.println("词义:" + keyword_sense[i] + "先验概率:" + wordSenseProbabilty[i]);
                    senseIfLeftAndRightExistMap.put(code, new Float(wordSenseProbabilty[i]));
                }
            }

        }

        return count;
    }

    //3.2 通过左右分词和目标词绑定出现的次数，计算后验概率；
    public static float[] getWordPosteriorProbability(int[] wordCount, int leftCount, int rightCount) {

        float[] wordSensePosteriorProbe = new float[senseIfLeftAndRightExistMap.size()];
        selectedKeyword_sense = new String[senseIfLeftAndRightExistMap.size()];

        //senseIfLeftAndRightExistMap存的是<词义,先验概率>

        int j = 0;
        int k = 0;
        String code = null;
        int index = 0;

        for (int i = 0; i < keyword_sense.length; i++) {

            index = keyword_sense[i].indexOf("(");
            code = keyword_sense[i].substring(index + 1, index + 5);

            if (senseIfLeftAndRightExistMap.get(code) != null) {

                int senseCount = wordCount[i];
                System.out.print("词义:" + code + "  出现的次数:" + senseCount);

                if (senseCount != 0) {

                    float probe = ((float) leftCount / senseCount) * ((float) rightCount / senseCount) / 2;
                    System.out.println("  式子:(" + leftCount + " / " + senseCount + " * " + rightCount + " / " + senseCount + ") / 2");
                    wordSensePosteriorProbe[j++] = probe;
                    selectedKeyword_sense[k++] = keyword_sense[i];

                } else {

                    wordSensePosteriorProbe[j++] = 0;
                    selectedKeyword_sense[k++] = keyword_sense[i];
                }
            }
        }

        return wordSensePosteriorProbe;
    }

    //3.3 后验概率乘上目标词的先验概率，筛选出目标词最大的概率
    public static float[] getUltiProbability(float[] wordSensePosteriorProbe, Map<String, Float> map) {

        float ultiProbabilty[] = new float[map.size()];
        String code = null;
        int index = 0;

        for (int i = 0; i < ultiProbabilty.length; i++) {

            index = selectedKeyword_sense[i].indexOf("(");
            code = selectedKeyword_sense[i].substring(index + 1, index + 5);

            ultiProbabilty[i] = map.get(code) * wordSensePosteriorProbe[i];
        }

        return ultiProbabilty;
    }

    //除了存储THE.txt和cupus.txt的list不清空，其他的成员变量都清空
    public static void clearAll() {
        senseIfLeftAndRightExistMap.clear();
        cleanString(keyword_sense);
        cleanString(selectedKeyword_sense);
    }

    public static void cleanString(String[] str) {

        for (int i = 0; i < str.length; i++) {
            str[i] = null;
        }
    }

    @Test
    //测试目标词获得的语义是否正确
    public void test1() {
        String keyword = "挑";
        List<String> list = getTHES_TXT("D:/THES.TXT");
        String[] keyword_sense = getSenseFromKeyword(list, keyword);
        int count = 1;
        for (String str : keyword_sense) {
            System.out.println("sense" + count + ":" + str);
            count++;
        }
    }

    @Test
    //测试语料是否正确得到"词语-编码"
    public void test2() {
        List<String> content = getCupusContent("D:/WSD_Cupus_M.txt");
        List<String> list = getCupusWordBySpace(content);
        for (String str : list) {
            System.out.println(str);
        }
    }

    @Test
    //测试目标词，以及对应的语义在语料中出现的次数,以及对应词义的先验概率
    public void test3() {
        String keyword = "教育";
        List<String> list = getTHES_TXT("D:/THES.TXT");
        String[] keyword_sense = getSenseFromKeyword(list, keyword);

        List<String> content = getCupusContent("WSD_Cupus_M.txt");
        List<String> cupusWordList = getCupusWordBySpace(content);

        int[] wordCount = getWordPriorCount(keyword, keyword_sense, cupusWordList);
        float[] wordSenseProbabilty = getWordPriorProbability(wordCount);

        for (int i = 0; i < wordCount.length - 1; i++) {
            System.out.print(keyword_sense[i] + "出现的次数:" + wordCount[i]);
            System.out.print("   出现的概率:" + wordSenseProbabilty[i]);
            System.out.println();
        }
        System.out.println("\"" + keyword + "\"出现的总次数:" + wordCount[wordCount.length - 1]);

    }

    @Test
    //测试目标词根据最终最大的概率，得到对应的词义。
    public void test5() {
        String sentence[] = {"我", "要","朋友"};

        String keywords[] = {"要"};
        List<String> list = getTHES_TXT("D:/THES.TXT");
        List<String> content = getCupusContent("D:/WSD_Cupus_M.txt");
        cupusWordList = getCupusWordBySpace(content);

        for (String key : keywords) {

            float[] wordSenseProbabilty;  //先验概率
            float[] posteriorProbabilty;  //后验概率
            float[] ultiProbability;  //最终统计的概率

            keyword = key;

            keyword_sense = getSenseFromKeyword(list, keyword);

            int leftCount = 0;
            int rightCount = 0;

            int[] wordCount = getWordPriorCount(keyword, keyword_sense, cupusWordList);
            wordSenseProbabilty = getWordPriorProbability(wordCount);

            for (int i = 0; i < wordCount.length - 1; i++) {
                System.out.print(keyword_sense[i] + "出现的次数:" + wordCount[i]);
                System.out.print("   出现的概率:" + wordSenseProbabilty[i]);
                System.out.println();
            }
            System.out.println("\"" + keyword + "\"出现的总次数:" + wordCount[wordCount.length - 1]);
            System.out.println();


            for (int i = 0; i < sentence.length; i++) {
                if (keyword.equals(sentence[i])) {
                    if (i - 1 >= 0) {
                        leftword = sentence[i - 1];
                    }
                    if (i + 1 <= sentence.length - 1) {
                        rightword = sentence[i + 1];
                    }
                }
            }

            if (leftword != null) {

                leftCount = getLeftAndRightCount(keyword, leftword, cupusWordList, keyword_sense, wordSenseProbabilty);
                System.out.println("左分词\"" + leftword + "\"绑定目标词\"" + keyword + "\"出现的概率(次数):" + leftCount);
            } else {
                System.out.println("左分词不存在");
            }

            System.out.println();

            if (rightword != null) {

                rightCount = getLeftAndRightCount(keyword, rightword, cupusWordList, keyword_sense, wordSenseProbabilty);
                System.out.println("右分词\"" + rightword + "\"绑定目标词\"" + keyword + "\"出现的概率(次数):" + rightCount);

            } else {
                System.out.println("右分词不存在");
            }

            Set<Map.Entry<String, Float>> entries = senseIfLeftAndRightExistMap.entrySet();
            Iterator iterator = entries.iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                System.out.println("词义:" + entry.getKey() + "  先验概率:" + entry.getValue());
            }


            System.out.println();
            System.out.println("目标词每一个词义对应的后验概率:");
            posteriorProbabilty = getWordPosteriorProbability(wordCount, leftCount, rightCount);

            if (posteriorProbabilty == null) {
                System.out.println("0");
            } else {
                for (int i = 0; i < posteriorProbabilty.length; i++) {
                    System.out.println("sense" + (i + 1) + ":" + selectedKeyword_sense[i] + "  " + posteriorProbabilty[i]);
                }
            }

            ultiProbability = getUltiProbability(posteriorProbabilty, senseIfLeftAndRightExistMap);

            float temp = 10;
            int index = -1;
            for (int i = 0; i < ultiProbability.length; i++) {
                if (ultiProbability[i] > temp) {
                    index = i;
                    temp = ultiProbability[i];
                }
            }

            System.out.print("目标词的词义为:");

            if (index == -1) {

                //如果index = -1，则ultiProbabilty为空，那么将先验概率大的词义赋给目标词的最终词义
                int max = -1;
                int index1 = 0;
                for (int i = 0; i < wordCount.length - 1; i++) {
                    if (max < wordCount[i]) {
                        max = wordCount[i];
                        index1 = i;
                    }
                }

                System.out.println(keyword_sense[index1]);

            } else {
                System.out.println(selectedKeyword_sense[index]);
            }

            System.out.println("*********************************************");
            System.out.println();

            clearAll();
        }
    }


    static{
        cilinList = getTHES_TXT("D:/THES.TXT");
        List<String> content = getCupusContent("D:/WSD_Cupus_M.txt");
        cupusWordList = getCupusWordBySpace(content);
    }

    public static String wsdFeit(String target, String[] sentence) {

        float[] wordSenseProbabilty;  //先验概率
        float[] posteriorProbabilty;  //后验概率
        float[] ultiProbability;  //最终统计的概率

        keyword = target;

        keyword_sense = getSenseFromKeyword(cilinList, keyword);

        int leftCount = 0;
        int rightCount = 0;

        int[] wordCount = getWordPriorCount(keyword, keyword_sense, cupusWordList);
        wordSenseProbabilty = getWordPriorProbability(wordCount);

        for (int i = 0; i < wordCount.length - 1; i++) {
            System.out.print(keyword_sense[i] + "出现的次数:" + wordCount[i]);
            System.out.print("   出现的概率:" + wordSenseProbabilty[i]);
            System.out.println();
        }
        System.out.println("\"" + keyword + "\"出现的总次数:" + wordCount[wordCount.length - 1]);
        System.out.println();


        for (int i = 0; i < sentence.length; i++) {
            if (keyword.equals(sentence[i])) {
                if (i - 1 >= 0) {
                    leftword = sentence[i - 1];
                }
                if (i + 1 <= sentence.length - 1) {
                    rightword = sentence[i + 1];
                }
            }
        }

        if (leftword != null) {

            leftCount = getLeftAndRightCount(keyword, leftword, cupusWordList, keyword_sense, wordSenseProbabilty);
            System.out.println("左分词\"" + leftword + "\"绑定目标词\"" + keyword + "\"出现的概率(次数):" + leftCount);
        } else {
            System.out.println("左分词不存在");
        }

        System.out.println();

        if (rightword != null) {

            rightCount = getLeftAndRightCount(keyword, rightword, cupusWordList, keyword_sense, wordSenseProbabilty);
            System.out.println("右分词\"" + rightword + "\"绑定目标词\"" + keyword + "\"出现的概率(次数):" + rightCount);

        } else {
            System.out.println("右分词不存在");
        }

        Set<Map.Entry<String, Float>> entries = senseIfLeftAndRightExistMap.entrySet();
        Iterator iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.println("词义:" + entry.getKey() + "  先验概率:" + entry.getValue());
        }


        System.out.println();
        System.out.println("目标词每一个词义对应的后验概率:");
        posteriorProbabilty = getWordPosteriorProbability(wordCount, leftCount, rightCount);

        if (posteriorProbabilty == null) {
            System.out.println("0");
        } else {
            for (int i = 0; i < posteriorProbabilty.length; i++) {
                System.out.println("sense" + (i + 1) + ":" + selectedKeyword_sense[i] + "  " + posteriorProbabilty[i]);
            }
        }

        ultiProbability = getUltiProbability(posteriorProbabilty, senseIfLeftAndRightExistMap);

        float temp = 10;
        int index = -1;
        for (int i = 0; i < ultiProbability.length; i++) {
            if (ultiProbability[i] > temp) {
                index = i;
                temp = ultiProbability[i];
            }
        }

        System.out.print("目标词的词义为:");


        String ultimateSense = null;
        if (index == -1) {

            //如果index = -1，则ultiProbabilty为空，那么将先验概率大的词义赋给目标词的最终词义
            int max = -1;
            int index1 = 0;
            for (int i = 0; i < wordCount.length - 1; i++) {
                if (max < wordCount[i]) {
                    max = wordCount[i];
                    index1 = i;
                }
            }

            ultimateSense = keyword_sense[index1];
            System.out.println(ultimateSense);

        }else {
            ultimateSense = selectedKeyword_sense[index];
            System.out.println(ultimateSense);
        }

        System.out.println("*********************************************");
        System.out.println();

        clearAll();

        return ultimateSense;
    }

    //将语料库中的单词以String的形式返回，并且单词唯一
    public static String getWsdTemp(){

        Map<String,Boolean> map = new HashMap<>();
        String[] token = null;
        wsdTemp = new StringBuffer("");

        for(String str : cupusWordList){
              token = str.split("-");
              if(token.length > 1){
                  map.put(token[0],true);
              }
        }

        int count = 100;
        Set<String> keyset = map.keySet();
        for(String key : keyset){
            wsdTemp.append(key).append(" ");
            if(count > 0){
                System.out.println(wsdTemp);
                count--;
            }
        }

        return wsdTemp.toString();
    }
}
