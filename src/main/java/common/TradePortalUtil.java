package common;


import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;


/**
 * 交易前置系统专用工具类
 * @create Aug 15, 2012 12:16:49 PM
 * @update Sep 27, 2012 3:07:09 PM
 * @author 玄玉<http://blog.csdn/net/jadyer>
 * @version v2.0
 * @history v1.7.2-->新增<code>getHexSign()</code>通过指定算法签名字符串方法
 * @history v1.7.2-->新增<code>getString()</code>字节数组转为字符串方法
 * @history v1.7.3-->修改<code>getSysJournalNo()</code>实现细节为<code>java.util.UUID.randomUUID()</code>
 * @history v1.7.4-->新增<code>getHexSign()</code>根据指定的签名密钥和算法签名Map<String,String>
 * @history v1.7.5-->新增<code>getStringSimple()</code>获取一个字符串的简明效果,返回的字符串格式类似于"abcd***hijk"
 * @history v2.0-->局部的StringBuffer一律StringBuilder之(本思路提示自坦克<captmjc@gmail.com>)
 */
public class TradePortalUtil {
    private TradePortalUtil(){}
    
    /**
     * 获取系统流水号
     * @see 若欲指定返回值的长度or是否全由数字组成等,you can call {@link TradePortalUtil#getSysJournalNo(int, boolean)}
     * @return 长度为20的全数字
     */
    public static String getSysJournalNo(){
        return getSysJournalNo(20, true);
    }
    
    
    /**
     * 获取系统流水号
     * @param length   指定流水号长度
     * @param toNumber 指定流水号是否全由数字组成
     */
    public static String getSysJournalNo(int length, boolean isNumber){
        //replaceAll()之后返回的是一个由十六进制形式组成的且长度为32的字符串
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        if(uuid.length() > length){
            uuid = uuid.substring(0, length);
        }else if(uuid.length() < length){
            for(int i=0; i<length-uuid.length(); i++){
                uuid = uuid + Math.round(Math.random()*9);
            }
        }
        if(isNumber){
            return uuid.replaceAll("a", "1").replaceAll("b", "2").replaceAll("c", "3").replaceAll("d", "4").replaceAll("e", "5").replaceAll("f", "6");
        }else{
            return uuid;
        }
    }
    
    
    /**
     * 判断输入的字符串参数是否为空
     * @return boolean 空则返回true,非空则flase
     */
    public static boolean isEmpty(String input) {
        return null==input || 0==input.length() || 0==input.replaceAll("\\s", "").length();
    }
    
    
    /**
     * 判断输入的字节数组是否为空
     * @return boolean 空则返回true,非空则flase
     */
    public static boolean isEmpty(byte[] bytes){
        return null==bytes || 0==bytes.length;
    }
    
    
    
    /**
     * 获取实体类中的属性
     * @see 本方法用到了反射,其适用于所有的属性类型均为byte[]的JavaBean
     * @return String field11=value11 field22=value22 field33=value33
     */
    public static String getStringFromObjectForByte(Object obj){
        StringBuilder sb = new StringBuilder(); //局部的StringBuffer一律StringBuilder之
        sb.append(obj.getClass().getName()).append("@").append(obj.hashCode()).append("[");
        for(Field field : obj.getClass().getDeclaredFields()){
            String methodName = "get" + StringUtils.capitalize(field.getName()); //构造getter方法
            Object fieldValue = null;
            try{
                fieldValue = obj.getClass().getDeclaredMethod(methodName).invoke(obj); //执行getter方法,获取其返回值
            }catch(Exception e){
                //一旦发生异常,便将属性值置为UnKnown,故此处没必要一一捕获所有异常
                sb.append("\n").append(field.getName()).append("=UnKnown");
                continue;
            }
            if(fieldValue == null){
                sb.append("\n").append(field.getName()).append("=null");
            }else{
                sb.append("\n").append(field.getName()).append("=").append(new String((byte[])fieldValue));
            }
        }
        return sb.append("\n]").toString();
    }
    
    
    /**
     * 获取Map中的属性
     * @see 由于Map.toString()打印出来的参数值对,是横着一排的...参数多的时候,不便于查看各参数值
     * @see 故此仿照commons-lang.jar中的ReflectionToStringBuilder.toString()编写了本方法
     * @return String key11=value11 \n key22=value22 \n key33=value33 \n......
     */
    public static String getStringFromMap(Map<String, String> map){
        StringBuilder sb = new StringBuilder();
        sb.append(map.getClass().getName()).append("@").append(map.hashCode()).append("[");
        for(Map.Entry<String,String> entry : map.entrySet()){
            sb.append("\n").append(entry.getKey()).append("=").append(entry.getValue());
        }
        return sb.append("\n]").toString();
    }

    
    /**
     * 获取Map中的属性
     * @see 该方法的参数适用于打印Map<String, byte[]>的情况
     * @return String key11=value11 \n key22=value22 \n key33=value33 \n......
     */
    public static String getStringFromMapForByte(Map<String, byte[]> map){
        StringBuilder sb = new StringBuilder();
        sb.append(map.getClass().getName()).append("@").append(map.hashCode()).append("[");
        for(Map.Entry<String,byte[]> entry : map.entrySet()){
            sb.append("\n").append(entry.getKey()).append("=").append(new String(entry.getValue()));
        }
        return sb.append("\n]").toString();
    }
    
    
    /**
     * 获取Map中的属性
     * @see 该方法的参数适用于打印Map<String, Object>的情况
     * @return String key11=value11 \n key22=value22 \n key33=value33 \n......
     */
    public static String getStringFromMapForObject(Map<String, Object> map){
        StringBuilder sb = new StringBuilder();
        sb.append(map.getClass().getName()).append("@").append(map.hashCode()).append("[");
        for(Map.Entry<String,Object> entry : map.entrySet()){
            sb.append("\n").append(entry.getKey()).append("=").append(entry.getValue().toString());
        }
        return sb.append("\n]").toString();
    }
    
    
    /**
     * 金额元转分
     * @see 注意:该方法可处理贰仟万以内的金额,且若有小数位,则不限小数位的长度
     * @see 注意:如果你的金额达到了贰仟万以上,则不推荐使用该方法,否则计算出来的结果会令人大吃一惊
     * @param amount  金额的元进制字符串
     * @return String 金额的分进制字符串
     */
    public static String moneyYuanToFen(String amount){
        if(isEmpty(amount)){
            return amount;
        }
        //传入的金额字符串代表的是一个整数
        if(-1 == amount.indexOf(".")){
            return Integer.parseInt(amount) * 100 + "";
        }
        //传入的金额字符串里面含小数点-->取小数点前面的字符串,并将之转换成单位为分的整数表示
        int money_fen = Integer.parseInt(amount.substring(0, amount.indexOf("."))) * 100;
        //取到小数点后面的字符串
        String pointBehind = (amount.substring(amount.indexOf(".") + 1));
        //amount=12.3
        if(pointBehind.length() == 1){
            return money_fen + Integer.parseInt(pointBehind)*10 + "";
        }
        //小数点后面的第一位字符串的整数表示
        int pointString_1 = Integer.parseInt(pointBehind.substring(0, 1));
        //小数点后面的第二位字符串的整数表示
        int pointString_2 = Integer.parseInt(pointBehind.substring(1, 2));
        //amount==12.03,amount=12.00,amount=12.30
        if(pointString_1 == 0){
            return money_fen + pointString_2 + "";
        }else{
            return money_fen + pointString_1*10 + pointString_2 + "";
        }
    }
    
    
    /**
     * 金额元转分
     * @see 该方法会将金额中小数点后面的数值,四舍五入后只保留两位....如12.345-->12.35
     * @see 注意:该方法可处理贰仟万以内的金额
     * @see 注意:如果你的金额达到了贰仟万以上,则非常不建议使用该方法,否则计算出来的结果会令人大吃一惊
     * @param amount  金额的元进制字符串
     * @return String 金额的分进制字符串
     */
    public static String moneyYuanToFenByRound(String amount){
        if(isEmpty(amount)){
            return amount;
        }
        if(-1 == amount.indexOf(".")){
            return Integer.parseInt(amount) * 100 + "";
        }
        int money_fen = Integer.parseInt(amount.substring(0, amount.indexOf("."))) * 100;
        String pointBehind = (amount.substring(amount.indexOf(".") + 1));
        if(pointBehind.length() == 1){
            return money_fen + Integer.parseInt(pointBehind)*10 + "";
        }
        int pointString_1 = Integer.parseInt(pointBehind.substring(0, 1));
        int pointString_2 = Integer.parseInt(pointBehind.substring(1, 2));
        //下面这种方式用于处理pointBehind=245,286,295,298,995,998等需要四舍五入的情况
        if(pointBehind.length() > 2){
            int pointString_3 = Integer.parseInt(pointBehind.substring(2, 3));
            if(pointString_3 >= 5){
                if(pointString_2 == 9){
                    if(pointString_1 == 9){
                        money_fen = money_fen + 100;
                        pointString_1 = 0;
                        pointString_2 = 0;
                    }else{
                        pointString_1 = pointString_1 + 1;
                        pointString_2 = 0;
                    }
                }else{
                    pointString_2 = pointString_2 + 1;
                }
            }
        }
        if(pointString_1 == 0){
            return money_fen + pointString_2 + "";
        }else{
            return money_fen + pointString_1*10 + pointString_2 + "";
        }
    }
    
    
    /**
     * 金额分转元
     * @see 注意:如果传入的参数中含小数点,则直接原样返回
     * @see 该方法返回的金额字符串格式为<code>00.00</code>,其整数位有且至少有一个,小数位有且长度固定为2
     * @param amount  金额的分进制字符串
     * @return String 金额的元进制字符串
     */
    public static String moneyFenToYuan(String amount){
        if(isEmpty(amount)){
            return amount;
        }
        if(amount.indexOf(".") > -1){
            return amount;
        }
        if(amount.length() == 1){
            return "0.0" + amount;
        }else if(amount.length() == 2){
            return "0." + amount;
        }else{
            return amount.substring(0, amount.length()-2) + "." + amount.substring(amount.length()-2);
        }
    }
    
    
    /**
     * 获取一个字符串的简明效果
     * @return String 返回的字符串格式类似于"abcd***hijk"
     */
    public static String getStringSimple(String data){
        return data.substring(0,4) + "***" + data.substring(data.length()-4);
    }
    /**
     * 获取前一天日期yyyyMMdd
     * @see 经测试，针对闰年02月份或跨年等情况，该代码仍有效。测试代码如下
     * @see calendar.set(Calendar.YEAR, 2013);
     * @see calendar.set(Calendar.MONTH, 0);
     * @see calendar.set(Calendar.DATE, 1);
     * @see 测试时，将其放到<code>calendar.add(Calendar.DATE, -1);</code>前面即可
     * @return 返回的日期格式为yyyyMMdd
     */
    public static String getYestoday(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
    }
    
    
    /**
     * 获取当前的日期yyyyMMdd
     */
    public static String getCurrentDate(){
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }
    
    
    /**
     * 获取当前的时间yyyyMMddHHmmss
     */
    public static String getCurrentTime(){
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }
    
    
    /**
     * HTML字符转义
     * @see 对输入参数中的敏感字符进行过滤替换,防止用户利用JavaScript等方式输入恶意代码
     * @see String input = <img src='http://t1.baidu.com/it/fm=0&gp=0.jpg'/>
     * @see HtmlUtils.htmlEscape(input);         //from spring.jar
     * @see StringEscapeUtils.escapeHtml(input); //from commons-lang.jar
     * @see 尽管Spring和Apache都提供了字符转义的方法,但Apache的StringEscapeUtils功能要更强大一些
     * @see StringEscapeUtils提供了对HTML,Java,JavaScript,SQL,XML等字符的转义和反转义
     * @see 但二者在转义HTML字符时,都不会对单引号和空格进行转义,而本方法则提供了对它们的转义
     * @return String 过滤后的字符串
     */
    public static String htmlEscape(String input) {
        if(isEmpty(input)){
            return input;
        }
        input = input.replaceAll("&", "&amp;");
        input = input.replaceAll("<", "&lt;");
        input = input.replaceAll(">", "&gt;");
        input = input.replaceAll(" ", "&nbsp;");
        input = input.replaceAll("'", "&#39;");   //IE暂不支持单引号的实体名称,而支持单引号的实体编号,故单引号转义成实体编号,其它字符转义成实体名称
        input = input.replaceAll("\"", "&quot;"); //双引号也需要转义，所以加一个斜线对其进行转义
        input = input.replaceAll("\n", "<br/>");  //不能把\n的过滤放在前面，因为还要对<和>过滤，这样就会导致<br/>失效了
        return input;
    }
}
