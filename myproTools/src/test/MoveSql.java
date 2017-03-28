package test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import testmybatis.UserMapper;

public class MoveSql {
	private static final String gds_fs = "xm	姓名、xbdm	性别代码、mzdm	民族代码、zzmmdm	政治面貌代码、csrq	出生日期、gzrq	工作日期、byrq	毕业日期、byxx	毕业学校、sfzh	身份证号、yzbm	邮政编码、lxdh	联系电话、txdz	通信地址、xzqhdm	行政区划代码、zf	总分、pxzf	排序总分、ccdm	层次代码、cc	层次、xxxs	学校形式、lqzydm	录取专业代码、lqzy	录取专业、syxz	生源性质、bz	备注";

	public static void main(String[] args) {
		int a = new MoveSql().sqlConnect(UserMapper.class, o->{
			return o.queryIntBysql("select count(*) from rxks_kwgl");
		});
		System.out.println(a);
	}

	private <T, R> R sqlConnect(Class<T> clazz, matg<T, R> m) {
		System.out.println("开始执行,,,,,,,");
		String resource = "testmybatis/config.xml";
		InputStream inputStream;
		SqlSession session = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		T t = session.getMapper(clazz);
		R r = m.getType(t);

		session.commit();
		session.close();
		return r;
	}

	
	
}

interface matg<T, R> {
	public R getType(T t);
}
