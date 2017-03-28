package myproTools.sys;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import mainbody.MethodModel;
import mainbody.command;

public class sysService {

	public Map<String, String> help() {

		Map<String, String> aa = new HashMap<>();
		Class<?> clazz = MethodModel.class;
		Field[] fields = clazz.getDeclaredFields();
		for (Field fie : fields) {
			if (fie.isAnnotationPresent(command.class)) {
				command command = fie.getAnnotation(command.class);
				String commandd = command.command();
				String shuomingd = command.shuoMing();
				aa.put(commandd, shuomingd);

			}
		}
		return aa;
	}

}
