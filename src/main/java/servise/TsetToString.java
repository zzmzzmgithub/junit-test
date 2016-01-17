package servise;

import java.util.ArrayList;
import java.util.List;

import model.Student;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import common.TestUtil;


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
		Student ss = new Student("张三", "69");
		TestUtil.modelToString(ss);
	}
	
	@Test
	public void ww(){
		Student ss = new Student("张三", "69");
		Student aa = new Student("张四", "659");
		List<Student> sdList = new ArrayList<Student>();
		sdList.add(aa);
		sdList.add(ss);
		TestUtil.ListModelToString(sdList);
	}
}
