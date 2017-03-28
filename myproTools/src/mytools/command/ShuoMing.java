package mytools.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ShuoMing {
	/**
	 * 命令的说明
	 * @return
	 */
	String word();

	/**
	 * 命令的名字
	 * @return
	 */
	String name();

	String value();
}
