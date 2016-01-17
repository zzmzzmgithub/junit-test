package common;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public final class TestUtil {
	
	/**
	 * 业务对象重写tostring
	 * @param obj
	 */
	public static final void modelToString( Object obj){
		//对象及其属性一行显示
	    System.out.println(ToStringBuilder.reflectionToString(obj));
	    System.out.println(ToStringBuilder.reflectionToString(obj, ToStringStyle.DEFAULT_STYLE));
	    //属性换行显示
	    System.out.println(ToStringBuilder.reflectionToString(obj, ToStringStyle.MULTI_LINE_STYLE));
	    //不显示属性名，只显示属性值，同一行显示
	    System.out.println(ToStringBuilder.reflectionToString(obj, ToStringStyle.NO_FIELD_NAMES_STYLE));
	    //对象名称简写
	    System.out.println(ToStringBuilder.reflectionToString(obj, ToStringStyle.SHORT_PREFIX_STYLE));
	    //只显示属性
	    System.out.println(ToStringBuilder.reflectionToString(obj, ToStringStyle.SIMPLE_STYLE));
	}
	
	/**
	 * List<业务对象>重写tostring
	 * @param <T>
	 * @param objList
	 */
	public static final <T> void ListModelToString(List<T> tList){
		if(tList !=null && tList.size()>0){
			for(Object obj : tList){
				if(obj !=null  && ! "".equals(obj)){
					//对象名称简写
				    System.out.println(ToStringBuilder.reflectionToString(obj, ToStringStyle.SHORT_PREFIX_STYLE));
				}else{
					System.out.println("对象为空");
				}
			}
		}
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
}
