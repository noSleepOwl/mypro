package mytools.command;

public interface Choose {

	/**
	 * 退出当前功能
	 */
	@ShuoMing(name = "GN_OUT", word = "退出当前功能", value = "/OUT")
	public static final String GN_OUT = "/OUT";
	/**
	 * 退出程序
	 */
	@ShuoMing(name = "GN_EXIT", word = "退出程序", value = "/EXIT")
	public static final String GN_EXIT = "/EXIT";
	/**
	 * 帮助命令
	 */
	@ShuoMing(name = "GN_HELP", word = "帮助命令", value = "/HELP")
	public static final String GN_HELP = "/HELP";
	/**
	 * 功能内帮助帮助命令
	 */
	@ShuoMing(name = "GN_HELP_THIS", word = "功能内帮助帮助命令", value = "/TP")
	public static final String GN_HELP_THIS = "/TP";
	/**
	 * 格式化java 字符串 使其成为合法的java 字符串
	 */
	@ShuoMing(name = "GN_FORMAT_JAVA_STR", word = "格式化java 字符串 使其成为合法的java 字符串", value = "/FJS")
	public static final String GN_FORMAT_JAVA_STR = "/FJS";
	/**
	 * 创建文件
	 */
	@ShuoMing(name = "GN_FILE_CREATE_FOLDER", word = "指定目录创建一个文件夹", value = "/FCF")
	public static final String GN_FILE_CREATE_FOLDER = "/FCF";
	/**
	 * bootstrap table 属性格式化
	 */
	@ShuoMing(name = "GN_BOOTSTRAP_TABLE_TR", word = "bootstrap table 属性格式化", value = "/GB")
	public static final String GN_BOOTSTRAP_TABLE_TR = "/GB";
	/**
	 * 新建功能页面
	 */
	@ShuoMing(name = "GN_NEW_JSP", word = "新建功能页面", value = "/NJSP")
	public static final String GN_NEW_JSP = "/NJSP";

}
