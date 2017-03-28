package demo_324;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.reflection.SystemMetaObject;

/**
 * 
 * 自定义模板的解析
 * @author uigsw
 *
 */
public class ForSelfDemo {
	
	/**
	 * + 一次或者多次 {1,} ,
	 * ? 0次或者一次{0,1}
	 * * 0次或者多次{0,}
	 * @param args
	 */
	public static void main(String[] args) {
//		boolean test = "".matches("n+");
//		System.out.println(test);
		ForSelfDemo sel = new ForSelfDemo();
		sel.parseSelfModle("name[aa]");
	}
	
	/**
	 * 
	 *对各种各样的格式进行识别 
	 *	1:类   name.a.b
	 *	2:数组 array[1] 
	 *	3:list list[1].a
	 *	4 list 数组 list[1][1].a 用来取出来他的值 , 是什么 ?    这里的list 和array 可以写成同一种 , 即使是二维的数组和list 的数组也是一样的 , 
	 * 5 :map map[name]a nameb
	 *	一个定义的类型 , 
	 * 自定义方法的解析
	 * @param tar  输入的自定义模板的方式
	 * @return
	 */
	public String parseSelfModle(String tar){
		String regex = "((.*)((\\[((\\d?)|(\\w?))\\])+|(\\.){1})(\\2)(\\3)){1,}";
		Pattern pat = Pattern.compile(regex);
		Matcher matcher = pat.matcher(tar);
		boolean find = matcher.find();
		while(find){
			int all = matcher.groupCount();
			for(int i =0; i<all;i++)
			{
				System.out.println(matcher.group(i));
			}
			find = matcher.find();
		}
		return tar ;
	}
}
