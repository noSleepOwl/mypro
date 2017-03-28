package mytools.features.formatstring;

import java.awt.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mytools.features.file.FileGet;

/**
 * @author uigsw 字符串处理类
 */
public class FormatString {
	/**
	 * 格式化成java 可用的字符串
	 * 
	 * @param str
	 * @return
	 */
	public String formatJavaString(String str) {

		str = str.replaceAll("\\\\", "\\\\\\\\");
		str = str.replaceAll("\"", "\\\\\"");
		return str;
	}

	/**
	 * 获取java 文件中的 filed
	 * 
	 * @return
	 */
	public String getJavaFileField(String change) {
		return getJavaFileField(change, "^(private)\\s(.*)\\s(.*);$?");
	}

	public String toStrapTable(Map<String, String> field) {
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> fie : field.entrySet()) {
			sb.append("<th data-field=\"" + fie.getKey() + "\"  \t\t\t data-sortable=\"true\" 	>" + fie.getValue()
					+ "\t\t</th>\n");
		}
		return sb.toString();
	}

	public String getJavaFileField(String change, String regex) {
		change = new FileGet(change).getResult();
		if (change == null) {
			return null;
		}
		// String regex = "^(private)\\s(.*)\\s(.*);$?";
		Pattern pattern = Pattern.compile(regex);
		String[] chages = change.split("\n");
		for (String str : chages) {
			Matcher matcher = pattern.matcher(str.trim());
			while (matcher.find()) {
				System.out.println(matcher.group(3));
			}
		}
		return change;

	}

}
