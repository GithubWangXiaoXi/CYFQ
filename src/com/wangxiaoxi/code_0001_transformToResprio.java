package com.wangxiaoxi;

import org.junit.Test;

import java.io.*;
import java.util.*;

import static com.wangxiaoxi.code_0002_utils.*;
import static com.wangxiaoxi.code_0003_TranformDataToString.*;

public class code_0001_transformToResprio {

    private static List<String> wordLists = null;  //存储所有分词
    private static List<String> wordListRemovedDuplicate = new ArrayList<>(); //对左右分词去重处理，并按顺序输出。存储分词的出现顺序，map的词语无序
    private static List<String> wordListWithoutRemovedDuplicate = new ArrayList<>(); //不对左右分词去重处理，并按顺序输出，方便三层code的统计
    private static Map<String,Integer> wordsMap = null;    //存储分词的词语，出现次数的信息

    private static List<String> wordsListsInTHES = new ArrayList<>();
    private static List<String> senseListInTHES = new ArrayList<>();
    private static Map<String,String>wordsAndSenseInTHES = new HashMap<>();

    private static Map<String,String> wordAndCodeMap = null;
    private static Map<String,Integer> sancengCodeMap = null;
    private static List<String> sancengCodeListRemovedDuplicate = new ArrayList<>(); //对三层code去重处理，并按顺序输出。
    private static List<String> sancengCodeListWithoutRemovedDuplicate = new ArrayList<>();

    private static Map<String,Integer> dancengCodeMap = null;
    private static List<String> dancengCodeListRemovedDuplicate = new ArrayList<>();
    private static List<String> dancengCodeListWithoutRemovedDuplicate = new ArrayList<>();

    //加载DDSC_beats文件内容到stringbuffer缓冲区中
    public static String loadFile(String fileName) {
        BufferedReader br = null; // 声明BufferedReader对象
        StringBuffer stringBuffer = new StringBuffer(); //存储从文件中按行读取的内容

        try {
            br = new BufferedReader(new FileReader(fileName)); // 创建BufferedReader对象
            String line = null; // 创建中间变量 存储文本中每行的数据
            while ((line = br.readLine()) != null) // 判断取得的数据是否为空
            {
                stringBuffer.append(line); // 将非空数据添加到list中
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
        return stringBuffer.toString(); // 返回list
    }

    //对文本内容进行处理，得到所有的单词，符号则为null
    public static List<String> getWordsfromDDSC_beats(String content){
        //先按空格切分,得到{总之/c, ，/w, 本/r, 丛书/n}
        String[] contentRemovedSpace = content.split(" ");
        showContent(contentRemovedSpace);

        List<String> wordLists = new ArrayList<>();
        //再对所有单词按"/"切分,并按顺序保存在一个新的String[]中，得到{总之,c, ，,w, 本,r, 丛书,n}
        for(String part:contentRemovedSpace){
            String[] partRemovedSlip = part.split("/");

            String targetWord = partRemovedSlip[0];
            //如果分词为符号  则用"null"替换
            if (targetWord.equals("、") || targetWord.equals("，") || targetWord.equals("”")
                    || targetWord.equals("：") || targetWord.equals("“")|| targetWord.equals("。")||targetWord.equals("；")
                    ||targetWord.equals("《") ||targetWord.equals("》")){
                targetWord = "null";
            }
            //如果分词含有"―",  则用"null"替换
            if (targetWord.contains("―")){
                targetWord = "null";
            }
            //如果分词是数字，则用"null"替换
            if(code_0002_utils.isNumeric(targetWord)){
                targetWord = "null";
            }
            wordLists.add(targetWord);
        }
        showList(wordLists);

        return wordLists;
    }


    //在list中获取目标词的左右分词，储存在map<String,Integer>中
    public static Map<String,Integer> getLeftandRightWord(String key, String content){

        wordLists = getWordsfromDDSC_beats(content); //调用getWordsfromDDSC_beats获取储存着文本中的所有单词的list
        Map<String,Integer> leftAndRightWordsMap = new HashMap<>();  //存储左右分词，并统计个数
        //Map<String,Boolean> leftWordsMap = new HashMap<>();   //测试数据（没啥用）
        //Map<String,Boolean> RightWordsMap = new HashMap<>();   //测试数据（没啥用）


        //用indexOf应该无法匹配到所有包含key的位置
        //int index = wordLists.indexOf(key);

        for(int i = 0; i<wordLists.size(); i++){

            if (wordLists.get(i).equals(key)){
               int index = i;
               String leftWord = null;
               String rightWord = null;

               //需要考虑目标词在第一个的情况，和最后一个的情况！！
               if((index-1)>0){
                   leftWord = wordLists.get(index-1);
               }
               if((index+1)<wordLists.size()){
                   rightWord = wordLists.get(index+1);
               }

               int countLeft = 1;
               int countright = 1;

               //如果左分词存在，则对值递增
               if(leftAndRightWordsMap.get(leftWord) != null){
                   countLeft = leftAndRightWordsMap.get(leftWord);
                   countLeft++;

                   leftAndRightWordsMap.put(leftWord,countLeft);

                   //保留左右分词的实际顺序，不做去重处理
                   wordListWithoutRemovedDuplicate.add(leftWord);
               }else if(leftWord != null){
                   //如果该左右分词在map中不出现，则将其加入到list中
                   wordListRemovedDuplicate.add(leftWord);

                   leftAndRightWordsMap.put(leftWord,countLeft);
                   wordListWithoutRemovedDuplicate.add(leftWord);
               }

               if(leftAndRightWordsMap.get(rightWord) != null){
                   countright = leftAndRightWordsMap.get(rightWord);
                   countright++;

                   leftAndRightWordsMap.put(rightWord,countright);

               }else if(rightWord != null){
                   //如果该左右分词在map中不出现，则将其加入到list中
                   wordListRemovedDuplicate.add(rightWord);

                   leftAndRightWordsMap.put(rightWord,countright);
                   wordListWithoutRemovedDuplicate.add(rightWord);
               }
            }
        }

        //System.out.println("leftWordsMap "+leftWordsMap.size()+" "+" RightWordsMap "+RightWordsMap.size());
        return leftAndRightWordsMap;
    }


    /*原本打算读取THES.txt同义词词林，将数据添加到list，map中（边读取，边添加，边匹配），方便和DDSC_beats的词语进行匹配,同时节省空间*/
    //但是这样都要遍历wordlist一遍，无效查询比较多，所耗时间比较多，还是将THES.txt中的info加载到map后，再来查询（用空间换时间）
    public static void loadTHES_txt(String filename){
        BufferedReader bufferedReader = null;
        String[] token = null;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            String line = null;
            String sense = null;
            String word = null;
            while((line = bufferedReader.readLine())!= null){
                token = line.split("/");

                if(token.length > 1){

                    word = token[0].replace("#","");
                    sense = token[1];

                    wordsListsInTHES.add(word); //将词语添加到list中
                    senseListInTHES.add(sense);  //将词义添加到list中
                    wordsAndSenseInTHES.put(word,sense); //将词语，词义添加到map中
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Map<String,String> getWordCode(){

        Map<String,String> wordAndCodeMap1 = new HashMap<>();
        for(int i = 0;i<wordListWithoutRemovedDuplicate.size();i++){
            String wordTmp = wordListWithoutRemovedDuplicate.get(i);
            String code = null;
            String word = null;
            String sense = null;

            //直接比较词义来获取code
            for(int n = 0;n<senseListInTHES.size();n++){
                if(senseListInTHES.get(n).contains(wordTmp)){

                    String[] token = senseListInTHES.get(n).split(" ");

                    //如果词义只有一个
                    if(token.length == 1){
                        int indexStart = token[0].indexOf("(");
                        int indexEnd = token[0].indexOf(")");

                        code = token[0].substring(indexStart+1,indexEnd);
                        wordAndCodeMap1.put(wordListWithoutRemovedDuplicate.get(i),code);

                        //如果找到了code，就不用进行遍历，到另一个单词
                        break;
                    }
                    //如果词义有多个，则用dice距离算法求出，最优的词义
                    else{
                        //用来比较概率大小的中间变量
                        double resultLeftSum  = 0.0;

                        for(int j = 0 ; j <token.length ; j++) {
                            int numstart1 = token[j].indexOf("(");
                            //词义存在多个才用比较
                            String[] token1 = token[j].substring(0,numstart1).split("_");	//按"_"切分字符串
                            for(int k = 0 ; k < token1.length ; k++) {
                                //计算L_Word在L_Sence中存在的概率，同一个词义下的词被分成多个
                                //左分词的词义的切片中是否包含wordlists中的词语
                                if(token1[k].indexOf(wordTmp) != -1) {
                                    //double result = (double)(L_Word.length()*2)/(L_Word.length()+L_Sence_CWN[j].length());	//计算L_Word在L_Sence中存在的概率
                                    //使用dice距离来求字符串之间的相似性
                                    double resultLeft = (double)(wordTmp.length() * 2) / (wordTmp.length() + token1[k].length());
                                    if(resultLeft > resultLeftSum) {
                                        //将dice距离计算的最大的字符串相似度resultLeft 赋值给 resultLeftSum，并修改左分词的词义
                                        resultLeftSum = resultLeft;
                                        int numEnd1 = token[j].indexOf(")");
                                        code = token[j].substring(numstart1+1,numEnd1);
                                        break;
                                    }
                                }
                            }
                        }
                        wordAndCodeMap1.put(wordListWithoutRemovedDuplicate.get(i),code);
                    }
                }
            }

            //如果在THES“词义”中找不到该词，则通过查询THES“词语”是否存在
            if (wordAndCodeMap1.get(wordTmp) == null){
                for(int n = 0;n<wordsListsInTHES.size();n++) {
                    if(wordsListsInTHES.get(n).equals(wordTmp)) {

                        word = wordsListsInTHES.get(n);
                        sense = wordsAndSenseInTHES.get(word);

                        String[] token = sense.split(" ");

                        //如果词义只有一个
                        if (token.length == 1) {
                            int indexStart = token[0].indexOf("(");
                            int indexEnd = token[0].indexOf(")");

                            code = token[0].substring(indexStart + 1, indexEnd);
                            wordAndCodeMap1.put(wordListWithoutRemovedDuplicate.get(i), code);
                        }
                        //如果词义有多个，则用dice距离算法求出，最优的词义
                        else {
                            //用来比较概率大小的中间变量
                            double resultLeftSum = 0.0;

                            //一开始先将第一个语义的编码赋给code
                            code = token[0].substring(token[0].indexOf("(")+1,token[0].indexOf(")"));

                            for (int j = 0; j < token.length; j++) {
                                int numstart1 = token[j].indexOf("(");
                                //词义存在多个才用比较
                                String[] token1 = token[j].substring(0, numstart1).split("_");    //按"_"切分字符串
                                for (int k = 0; k < token1.length; k++) {
                                    //计算L_Word在L_Sence中存在的概率，同一个词义下的词被分成多个
                                    //eg: #建设/建立_设立_创立_命名(Hc05) 进步_退步_堕落(Ie12)，语义中没有"建设"，只有"建立"
                                    //if (token1[k].indexOf(wordTmp) != -1) {
                                    if (-1 != token1[k].indexOf(wordTmp)){
                                        //double result = (double)(L_Word.length()*2)/(L_Word.length()+L_Sence_CWN[j].length());	//计算L_Word在L_Sence中存在的概率
                                        //使用dice距离来求字符串之间的相似性
                                        double resultLeft = (double) (wordTmp.length() * 2) / (wordTmp.length() + token1[k].length());
                                        if (resultLeft > resultLeftSum) {
                                            //将dice距离计算的最大的字符串相似度resultLeft 赋值给 resultLeftSum，并修改左分词的词义
                                            resultLeftSum = resultLeft;
                                            int numEnd1 = token[j].indexOf(")");
                                            code = token[j].substring(numstart1 + 1, numEnd1);
                                            break;
                                        }
                                    }
                                }
                            }
                            wordAndCodeMap1.put(wordListWithoutRemovedDuplicate.get(i), code);
                        }
                    }
                }
            }
        }
        return wordAndCodeMap1;
    }

    //如果wordAndCodeMap中的有些词语在词林中找不到，code为空，则将该词以<词,"null">加入
    public static Map<String,String> getWordCodeReplaceNull(List<String> list){

        for(String str : list){
            if(wordAndCodeMap.get(str) == null){
                wordAndCodeMap.put(str,"null");
            }
        }
        return wordAndCodeMap;
    }

    //获取每个单词的code的数目，这时需要有序的wordlist，wordAndCodeMap无序.并创建无去重的triCodeList
    public static Map<String,Integer> getCountForPerCode(List<String> wordListWithoutRemovedDuplicate){

        Map<String,Integer> triCodeMap = new HashMap<>();

        for(String word:wordListWithoutRemovedDuplicate){
            String code = wordAndCodeMap.get(word);

            if(triCodeMap.get(code) == null){
                triCodeMap.put(code,1);
            }
            else{
                int count = triCodeMap.get(code);
                count++;
                triCodeMap.put(code,count);
            }
            sancengCodeListWithoutRemovedDuplicate.add(code);
        }

        return triCodeMap;
    }

    //从三层code中获取monoCode，并存储在去重,不去重的单层的codeList中
    public static void getMonoCode(){
        for(String triCode:sancengCodeListWithoutRemovedDuplicate){

            String monoCode = null;
            if(triCode.equals("null")){
                monoCode = "null";
            }else{
                monoCode = String.valueOf(triCode.charAt(0));
            }

            //创建不去重的单层的codeList
            dancengCodeListWithoutRemovedDuplicate.add(monoCode);

            //创建去重的单层的codeList
            if(!dancengCodeListRemovedDuplicate.contains(monoCode)){
                dancengCodeListRemovedDuplicate.add(monoCode);
            }
        }
    }

    //获取每个单词的单层code的数目
    public static Map<String,Integer> getCountForPerMonoCode(List<String> dancengCodeListWithoutRemovedDuplicate){

        Map<String,Integer> monoCodeMap = new HashMap<>();

        for(String str : dancengCodeListWithoutRemovedDuplicate){
            if(monoCodeMap.get(str) == null){
                monoCodeMap.put(str,1);
            }else{
                int count = monoCodeMap.get(str);
                count++;
                monoCodeMap.put(str,count);
            }
        }

        return monoCodeMap;
    }

    public static void main(String[] args) {

//        String content = "总之/c ，/w 本/r 丛书/n 的/u 隆重/ad 推出/v ，/w 对于/p 出版界/n 来说/u ，/w 总之/c ，/w 本/r 丛书/n 的/u 隆重/ad 推出/v ";
//        String keyword = "丛书";

        //得到分词，频数，概率
        String content1 = loadFile("D:/移动硬盘暂存盘/CYFQ/CYFQ/src/Data/DDSC_Beat2.0/表面/表面.txt");
        String keyword1 = "表面";
        wordsMap = getLeftandRightWord(keyword1,content1);
        showfenciInfoByListOrder(wordsMap,wordListRemovedDuplicate);


        //得到分词对应的Tricode
        loadTHES_txt("D:/THES.TXT");
        showList1(wordsListsInTHES);
        showMap(wordsAndSenseInTHES);

        wordAndCodeMap = getWordCode();
        System.out.println("----------------------------------------三层");

        wordAndCodeMap = getWordCodeReplaceNull(wordListWithoutRemovedDuplicate);
        //不去重显示tricode,需要不去重的wordlist
        System.out.println("不去重显示tricode");
//        showList(wordListWithoutRemovedDuplicate);
//        showMap1(wordAndCodeMap,wordListWithoutRemovedDuplicate);
//        System.out.println("wordListWithoutRemovedDuplicate size:"+wordListWithoutRemovedDuplicate.size());

        //输出对应的code的顺序(需要去重的list)
        sancengCodeListRemovedDuplicate = getSancengCodeListRemovedDuplicate(wordAndCodeMap,wordListRemovedDuplicate);
        showList(sancengCodeListRemovedDuplicate);

        //统计Tricode的个数，将code作为key，添加到sancengCodeMap中
        sancengCodeMap = getCountForPerCode(wordListWithoutRemovedDuplicate);
        showSanCengInfoByListOrder(sancengCodeMap,sancengCodeListRemovedDuplicate,wordListRemovedDuplicate);

        //得到monoCode
        System.out.println("----------------------------------------单层");
        getMonoCode();
        dancengCodeMap = getCountForPerMonoCode(dancengCodeListWithoutRemovedDuplicate);
        showTriAndMono(sancengCodeListRemovedDuplicate,dancengCodeMap);
        showDanCengInfoByListOrder(dancengCodeMap,dancengCodeListRemovedDuplicate,wordListRemovedDuplicate);

        System.out.println("正在打印文件");
        String fileInfo = mergeInfo();
        writeInfoIntoFile(fileInfo,keyword1);
    }

    //将分词，三层，单层的字符串信息拼接成一个总的字符串信息，便于输出
    public static String mergeInfo(){

        String fenci = "-=-=-=-=-=-=-=-==-=-=-=-==-=-=-=-=-=-==-=-=-=-分词"+"\n";
        String sanceng = "-=-=-=-=-=-=-=-==-=-=-=-==-=-=-=-=-=-==-=-=-=-三层"+"\n";
        String danceng = "-=-=-=-=-=-=-=-==-=-=-=-==-=-=-=-=-=-==-=-=-=-单层"+"\n";

        fenci += tranformFenCiToString(wordsMap,wordListRemovedDuplicate);
        sanceng += tranformSanCengToString(sancengCodeMap,sancengCodeListRemovedDuplicate,wordListRemovedDuplicate);
        danceng += tranformDanCengToString(dancengCodeMap,dancengCodeListRemovedDuplicate,wordListRemovedDuplicate);

        String fileInfo = fenci+sanceng+danceng;
        return fileInfo;
    }

    //将DDSC的所有文件，统计并打印到指定resprio文件夹中
    @Test
    public void test(){

        //得到DDSC文件夹下的所有文件名
        String dirPath = "D:/移动硬盘暂存盘/CYFQ/CYFQ/src/Data/DDSC_Beat2.0/";
        List<String> fileList = getListFiles(dirPath);
        for(String filename:fileList){
            System.out.println(filename+".txt");

            //得到分词，频数，概率
            String filePath = dirPath + filename + "/" + filename + ".txt";
            String content1 = loadFile(filePath);
            String keyword1 = filename;
            wordsMap = getLeftandRightWord(keyword1,content1);
            showfenciInfoByListOrder(wordsMap,wordListRemovedDuplicate);


            //得到分词对应的Tricode
            loadTHES_txt("D:/THES.TXT");
//            showList1(wordsListsInTHES);
//            showMap(wordsAndSenseInTHES);

            wordAndCodeMap = getWordCode();
            System.out.println("----------------------------------------三层");

            wordAndCodeMap = getWordCodeReplaceNull(wordListWithoutRemovedDuplicate);
            //不去重显示tricode,需要不去重的wordlist
            System.out.println("不去重显示tricode");
//        showList(wordListWithoutRemovedDuplicate);
//        showMap1(wordAndCodeMap,wordListWithoutRemovedDuplicate);
//        System.out.println("wordListWithoutRemovedDuplicate size:"+wordListWithoutRemovedDuplicate.size());

            //输出对应的code的顺序(需要去重的list)
            sancengCodeListRemovedDuplicate = getSancengCodeListRemovedDuplicate(wordAndCodeMap,wordListRemovedDuplicate);
//            showList(sancengCodeListRemovedDuplicate);

            //统计Tricode的个数，将code作为key，添加到sancengCodeMap中
            sancengCodeMap = getCountForPerCode(wordListWithoutRemovedDuplicate);
//            showSanCengInfoByListOrder(sancengCodeMap,sancengCodeListRemovedDuplicate,wordListRemovedDuplicate);

            System.out.println("----------------------------------------单层");
            getMonoCode();
            dancengCodeMap = getCountForPerMonoCode(dancengCodeListWithoutRemovedDuplicate);
//            showTriAndMono(sancengCodeListRemovedDuplicate,dancengCodeMap);
//            showDanCengInfoByListOrder(dancengCodeMap,dancengCodeListRemovedDuplicate,wordListRemovedDuplicate);

            System.out.println("正在打印文件");
            String fileInfo = mergeInfo();
            writeInfoIntoFile(fileInfo,keyword1);
            cleanAllStructure();
        }
    }

    public static void cleanAllStructure(){
        wordLists.clear();  //存储所有分词
        wordListRemovedDuplicate.clear();
        wordListWithoutRemovedDuplicate.clear();
        wordsMap.clear();

        //同义词词林可以不清空
//        wordsListsInTHES.clear();
//        senseListInTHES.clear();
//        wordsAndSenseInTHES.clear();

        wordAndCodeMap.clear();
        sancengCodeMap.clear();
        sancengCodeListRemovedDuplicate.clear();
        sancengCodeListWithoutRemovedDuplicate.clear();

        dancengCodeMap.clear();
        dancengCodeListRemovedDuplicate.clear();
        dancengCodeListWithoutRemovedDuplicate.clear();

    }
}
