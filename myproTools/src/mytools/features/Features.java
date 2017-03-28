package mytools.features;

public abstract class Features {
	public Features(String name, String help) {
		this.name = name;
		this.help = help;
	}

	/**
	 * 参数的数量多了
	 */
	public static final Integer ERROR_PARMERY_MORE = 0;
	/**
	 * 参数的数量少了
	 */
	public static final Integer ERROR_PARMERY_LESS = 1;
	/**
	 * 参数的 数目不对
	 */
	public static final Integer ERROR_PARMERY_TYPE = 2;

	/**
	 * 功能名称
	 */
	protected String name;
	/**
	 * 功能说明
	 */
	protected String help;
	/**
	 * 运行结果
	 */
	protected Object result;

	/**
	 * 帮助
	 * 
	 * @return
	 */
	public String getHelp() {
		return this.help;
	}

	/**
	 * 功能名称
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 方法主体
	 * 
	 * @return
	 */
	public void methodBody(Object... parmy) {
		outPut(result);
	}

	public void methodBody(String name, String help, Object result, Object... parmy) {
		this.name = name;
		this.help = help;
		this.result = result;
		methodBody(parmy);
	}

	/**
	 * @return
	 * 
	 * 		获取结果
	 */
	public Object getResult() {
		return this.result;
	}

	public void outPut(Object obj) {
		System.out.println(obj);
	}

	/**
	 * 方法执行之前
	 */
	public void intoMessage() {
		System.out.println("\t\t\t\t进入" + getName() + "请按提示继续输入 或者 输入 /TP 来查看帮助");
	};

	/**
	 * 方法执行结束之后
	 */
	public void outMessage() {
		System.out.println("执行结束:-------------------->\n");
	}

	public void help() {
		System.out.println("\t\t\t\t" + getHelp() + "--------或者输入 /out 返回主页面");
	}

}
