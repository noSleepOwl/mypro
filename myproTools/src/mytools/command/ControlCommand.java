package mytools.command;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControlCommand {

	private Map<String, InputModel> css = null;

	/**
	 * 获取所有命令 名称 , 说明和值
	 */
	public ControlCommand() {
		css = new HashMap<>();
		Class<?> chosse = Choose.class;

		for (Field fie : chosse.getDeclaredFields()) {
			if (fie.isAnnotationPresent(ShuoMing.class)) {
				ShuoMing shouming = fie.getAnnotation(ShuoMing.class);
				InputModel csm = new InputModel();
				String value = shouming.value();
				String name = shouming.name();
				String word = shouming.word();
				csm.setName(name);
				csm.setValue(value);
				csm.setWord(word);
				csm.setCommand(true);
				css.put(shouming.value(), csm);
			}
		}
	}

	public Map<String, InputModel> getCss() {
		return css;
	}

	public InputModel getCommand(String value) {
		return css.get(value);
	}
}
