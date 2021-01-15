package com.nwsuaf.utils;


import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySimHash {
    private String tokens; //字符串
    private BigInteger strSimHash;//字符产的hash值
    private int hashbits = 64; // 分词后的hash数;


    public MySimHash(String tokens) {
        this.tokens = tokens;
        this.strSimHash = this.simHash();
    }

    public MySimHash(String tokens, int hashbits) {
        this.tokens = tokens;
        this.hashbits = hashbits;
        this.strSimHash = this.simHash();
    }


    /**
     * 清除html标签
     * @param content
     * @return
     */
    private String cleanResume(String content) {
        // 若输入为HTML,下面会过滤掉所有的HTML的tag
        content = Jsoup.clean(content, Whitelist.none());
        content = StringUtils.lowerCase(content);
        String[] strings = {" ", "\n", "\r", "\t", "\\r", "\\n", "\\t", "&nbsp;"};
        for (String s : strings) {
            content = content.replaceAll(s, "");
        }
        return content;
    }


    /**
     * 这个是对整个字符串进行hash计算
     * @return
     */
    private BigInteger simHash() {

        tokens = cleanResume(tokens); // cleanResume 删除一些特殊字符

        int[] v = new int[this.hashbits];

        List<Term> termList = StandardTokenizer.segment(this.tokens); // 对字符串进行分词

        //对分词的一些特殊处理 : 比如: 根据词性添加权重 , 过滤掉标点符号 , 过滤超频词汇等;
        Map<String, Integer> weightOfNature = new HashMap<String, Integer>(); // 词性的权重
        weightOfNature.put("n", 2); //给名词的权重是2;
        Map<String, String> stopNatures = new HashMap<String, String>();//停用的词性 如一些标点符号之类的;
        stopNatures.put("w", ""); //
        int overCount = 5; //设定超频词汇的界限 ;
        Map<String, Integer> wordCount = new HashMap<String, Integer>();

        for (Term term : termList) {
            String word = term.word; //分词字符串

            String nature = term.nature.toString(); // 分词属性;
            //  过滤超频词
            if (wordCount.containsKey(word)) {
                int count = wordCount.get(word);
                if (count > overCount) {
                    continue;
                }
                wordCount.put(word, count + 1);
            } else {
                wordCount.put(word, 1);
            }

            // 过滤停用词性
            if (stopNatures.containsKey(nature)) {
                continue;
            }

            // 2、将每一个分词hash为一组固定长度的数列.比如 64bit 的一个整数.
            BigInteger t = this.hash(word);
            for (int i = 0; i < this.hashbits; i++) {
                BigInteger bitmask = new BigInteger("1").shiftLeft(i);
                // 3、建立一个长度为64的整数数组(假设要生成64位的数字指纹,也可以是其它数字),
                // 对每一个分词hash后的数列进行判断,如果是1000...1,那么数组的第一位和末尾一位加1,
                // 中间的62位减一,也就是说,逢1加1,逢0减1.一直到把所有的分词hash数列全部判断完毕.
                int weight = 1;  //添加权重
                if (weightOfNature.containsKey(nature)) {
                    weight = weightOfNature.get(nature);
                }
                if (t.and(bitmask).signum() != 0) {
                    // 这里是计算整个文档的所有特征的向量和
                    v[i] += weight;
                } else {
                    v[i] -= weight;
                }
            }
        }
        BigInteger fingerprint = new BigInteger("0");
        for (int i = 0; i < this.hashbits; i++) {
            if (v[i] >= 0) {
                fingerprint = fingerprint.add(new BigInteger("1").shiftLeft(i));
            }
        }
        return fingerprint;
    }


    /**
     * 对单个的分词进行hash计算;
     * @param source
     * @return
     */
    private BigInteger hash(String source) {
        if (source == null || source.length() == 0) {
            return new BigInteger("0");
        } else {
            /**
             * 当sourece 的长度过短，会导致hash算法失效，因此需要对过短的词补偿
             */
            while (source.length() < 3) {
                source = source + source.charAt(0);
            }
            char[] sourceArray = source.toCharArray();
            BigInteger x = BigInteger.valueOf(((long) sourceArray[0]) << 7);
            BigInteger m = new BigInteger("1000003");
            BigInteger mask = new BigInteger("2").pow(this.hashbits).subtract(new BigInteger("1"));
            for (char item : sourceArray) {
                BigInteger temp = BigInteger.valueOf((long) item);
                x = x.multiply(m).xor(temp).and(mask);
            }
            x = x.xor(new BigInteger(String.valueOf(source.length())));
            if (x.equals(new BigInteger("-1"))) {
                x = new BigInteger("-2");
            }
            return x;
        }
    }

    /**
     * 计算海明距离,海明距离越小说明越相似;
     * @param other
     * @return
     */
    private int hammingDistance(MySimHash other) {
        BigInteger m = new BigInteger("1").shiftLeft(this.hashbits).subtract(
                new BigInteger("1"));
        BigInteger x = this.strSimHash.xor(other.strSimHash).and(m);
        int tot = 0;
        while (x.signum() != 0) {
            tot += 1;
            x = x.and(x.subtract(new BigInteger("1")));
        }
        return tot;
    }


    public double getSemblance(MySimHash s2 ){
        double i = (double) this.hammingDistance(s2);
        return 1 - i/this.hashbits ;
    }

    public static void main(String[] args) {

        String s1 = "科比·比恩·布莱恩特（Kobe Bean Bryant，1978年8月23日—2020年1月26日），出生于美国宾夕法尼亚州费城，前美国职业篮球运动员，司职得分后卫/小前锋（锋卫摇摆人），绰号“黑曼巴”/“小飞侠”。 [1] \r\n" + 
        		"科比是前NBA球员乔·布莱恩特的儿子。在1996年NBA选秀中，科比于第1轮第13位被夏洛特黄蜂队选中，后被交易至洛杉矶湖人队，整个NBA生涯（1996年-2016年）都效力于洛杉矶湖人队。\r\n" + 
        		"科比的职业生涯随湖人队5夺NBA总冠军（2000年-2002年、2009年-2010年）；荣膺1次常规赛MVP（2007-08赛季），2次总决赛MVP（2009年-2010年），4次全明星赛MVP（2002年、2007年、2009年与2011年）；共18次入选NBA全明星阵容，15次入选NBA最佳阵容，12次入选NBA最佳防守阵容。 [2] \r\n" + 
        		"科比是NBA最好的得分手之一，生涯赢得无数奖项，突破、投篮、罚球、三分球他都驾轻就熟，几乎没有进攻盲区，单场比赛81分的纪录就有力地证明了这一点。除了疯狂的得分外，科比的组织能力也很出众，经常担任球队进攻的第一发起人。另外科比还是联盟中最好的防守人之一，贴身防守很具有压迫性。 [1] \r\n" + 
        		"2016年4月14日，科比·布莱恩特在生涯最后一场主场对阵爵士的常规赛后宣布退役。 [1]  2017年12月19日，湖人主场对阵勇士，中场时刻为科比的8号和24号两件球衣举行了退役仪式。 [3]  2018年3月13日，科比凭借和动画师格兰·基恩合作的短片《亲爱的篮球》获第90届奥斯卡最佳短片奖。 [4] \r\n" + 
        		"当地时间2020年1月26日，科比在加州卡拉巴萨斯因直升机事故遇难，年仅41岁。";
        String s2 = "沙奎尔·奥尼尔（Shaquille O'Neal），1972年3月6日出生于美国新泽西内瓦克，前美国职业篮球运动员，司职中锋，小名“沙克”（Shaq），绰号“大鲨鱼”。\r\n" + 
        		"沙奎尔·奥尼尔在1992年NBA选秀中于第1轮第1位以状元秀的身份被奥兰多魔术队选中，职业生涯曾效力于奥兰多魔术队、洛杉矶湖人队、迈阿密热火队、菲尼克斯太阳队、克里夫兰骑士队以及波士顿凯尔特人队，新秀赛季当选NBA年度最佳新秀，4次夺得NBA总冠军，2000年当选NBA常规赛MVP，3次当选NBA总决赛MVP（FMVP），15次入选NBA全明星阵容并3次当选NBA全明星MVP，8次入选NBA最佳阵容一阵，4次入选NBA最佳阵容二阵，2次入选NBA最佳阵容三阵，3次入选NBA最佳防守阵容二阵，2次当选NBA得分王 [1]  。\r\n" + 
        		"1996年，沙奎尔·奥尼尔入选NBA50大巨星 [2]  。2011年6月2日，“大鲨鱼”沙奎尔·奥尼尔在推特上宣布退役。 [3-6]  沙奎尔·奥尼尔的NBA生涯场均得到23.7分、10.9个篮板、2.3次盖帽；生涯共得到28596分，13099个篮板球，以及拥有58.2%的投篮命中率，是NBA实力最强的中锋之一 [1]  。\r\n" + 
        		"2013年4月3日，湖人队在主场迎战小牛队比赛的中场时间，为沙奎尔·奥尼尔的34号球衣举办了球衣退役仪式 [2]  。2016年4月4日，沙奎尔·奥尼尔正式入选2016年奈·史密斯篮球名人纪念堂。 [7]  同年12月23日，热火主场迎战湖人，在中场休息期间为奥尼尔的32号球衣举办了球衣退役仪式 [8]  。";
        String s3 = "里奥·梅西(Lionel Messi)，1987年6月24日出生于阿根廷圣菲省罗萨里奥市，阿根廷足球运动员，司职前锋，现效力于巴塞罗那足球俱乐部。 [1] \r\n" + 
        		"2000年，梅西加入巴塞罗那俱乐部。2005年，阿根廷青年队夺取世青赛冠军，梅西赢得了金球奖和金靴奖双项大奖。2008年北京奥运会上，梅西随阿根廷国奥队夺取了金牌。2009年底，他当选了欧洲足球先生和世界足球先生。2008-2009赛季，梅西率领球队连夺西甲、国王杯和欧冠三个冠军。2011年，梅西获得首届国际足联金球奖，还获得了欧足联欧洲最佳球员。2013年，他以46粒联赛进球的成绩第三次获得欧洲金靴奖奖杯。\r\n" + 
        		"2014年，梅西随阿根廷队参加2014年巴西世界杯，最终获得世界杯亚军 [2]  ；12月20日，梅西被IFFHS评为2013年世界职业联赛的最佳射手奖 [3]  。2015年8月，当选欧洲超级杯最佳球员 [4]  ；8月27日，获得欧洲最佳球员 [5]  。2016年1月12日，梅西荣膺2015年度FIFA金球奖，五度得奖创纪录；6月，在连续三次决赛（2014年世界杯、2015年美洲杯、2016年美洲杯）失利后，梅西正式宣布将退出阿根廷国家队 [6]  ；8月13日，梅西正式回归阿根廷国家队。\r\n" + 
        		"2017年11月24日，梅西领取了2016-17赛季的欧洲金靴奖；11月25日，巴萨官方和梅西续约到2021年 [7]  。2018年9月，入选国际足联年度最佳阵容 [8-9]  。2019年9月24日，梅西第六次当选世界足球先生 [10]  ；12月，梅西生涯第六次获得金球奖。";
        String s4 = "克里斯蒂亚诺·罗纳尔多（Cristiano Ronaldo、C罗）1985年2月5日出生于葡萄牙马德拉岛丰沙尔，葡萄牙职业足球运动员，司职边锋、中锋，效力于意甲尤文图斯足球俱乐部。 [1] \r\n" + 
        		"克里斯蒂亚诺·罗纳尔多在1995年加入马德拉国民足球俱乐部；1996年转入葡萄牙体育足球俱乐部青训体系；2001年，C罗在葡萄牙体育足球俱乐部经历了U16、U17、U18、二线队和一线队5个不同级别赛事 [2]  。2003年，C罗以1224万英镑身价转会曼彻斯特联足球俱乐部 [3]  ，并帮助球队获得包含2007-08赛季欧洲冠军联赛冠军在内的10项锦标 [4]  。C罗在曼联期间收获了2006-07赛季英格兰足球超级联赛最佳球员 [5]  ；2007-08赛季金球奖、国际职业足球运动员联合会最佳球员、英超最佳球员、英超金靴等奖项 [6-7]  。2009年，C罗以8000万英镑身价转会皇家马德里足球俱乐部 [8]  ，并在9年间帮助球队获得包括2013-14赛季 [9]  、2015-16赛季、2016-17赛季、2017-18赛季4次欧洲冠军联赛冠军在内的16项锦标 [10]  。而C罗在皇马期间收获了2012-13赛季、2013-14赛季、2015-16赛季、2016-17赛季金球奖等奖项 [11]  。2018年，C罗以1亿欧元身价转会至意甲尤文图斯足球俱乐部 [12]  ，并随队获得2018-19赛季意大利足球甲级联赛冠军 [13]  。\r\n" + 
        		"2003年，C罗代表葡萄牙国家青年队参加了“土伦杯”，并获得冠军 [14]  ；2003年8月20日，C罗在葡萄牙对阵哈萨克斯坦的友谊赛中首次代表国家队上场 [15]  ；2016年C罗随葡萄牙国家队首次夺得欧洲足球锦标赛冠军 [16]  ；2019年6月10日C罗随葡萄牙国家队赢取第1届欧洲国家联赛冠军 [17]  。";
        long l3 = System.currentTimeMillis();
        MySimHash hash1 = new MySimHash(s1, 64);
        
        MySimHash hash2 = new MySimHash(s2, 64);
        MySimHash hash3 = new MySimHash(s3, 64);
        
        MySimHash hash4 = new MySimHash(s4, 64);
        
        System.out.println("======================================");
        System.out.println(  "相似度:"+hash1.getSemblance(hash2) );
        System.out.println(  "相似度:"+hash1.getSemblance(hash3) );
        System.out.println(  "相似度:"+hash1.getSemblance(hash4) );
        long l4 = System.currentTimeMillis();
        System.out.println("总共耗时:"+(l4-l3)+"毫秒");
        System.out.println("======================================");



    }
}
