package servise;

import model.Student;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import common.TestUtil;
import common.TradePortalUtil;


public class TsetToString {

	@Test
	public void tt() {
		Student ss = new Student("张三", "081210");
		// 对象及其属性一行显示
		System.out.println(ToStringBuilder.reflectionToString(ss));
		System.out.println(ToStringBuilder.reflectionToString(ss,
				ToStringStyle.DEFAULT_STYLE));
		// 属性换行显示
		System.out.println(ToStringBuilder.reflectionToString(ss,
				ToStringStyle.MULTI_LINE_STYLE));
		// 不显示属性名，只显示属性值，同一行显示
		System.out.println(ToStringBuilder.reflectionToString(ss,
				ToStringStyle.NO_FIELD_NAMES_STYLE));
		// 对象名称简写
		System.out.println(ToStringBuilder.reflectionToString(ss,
				ToStringStyle.SHORT_PREFIX_STYLE));
		// 只显示属性
		System.out.println(ToStringBuilder.reflectionToString(ss,
				ToStringStyle.SIMPLE_STYLE));
	}
	
	@Test
	public void yy(){
		Student ss = new Student("张三", "081210");
		TestUtil.modelToString(ss);
	}
}
