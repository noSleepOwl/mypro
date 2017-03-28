package mytools.command;

public class InputModel {
	/**
	 * 命令接口中定义的field 的名称
	 */
	private String name;
	/**
	 * 输入的值
	 */
	private String value;
	/**
	 * 
	 */
	private String word;
	/**
	 * 是不是命令 fals: 不是 true: 是的;
	 */
	private boolean isCommand;

	public boolean isCommand() {
		return isCommand;
	}

	public void setCommand(boolean isCommand) {
		this.isCommand = isCommand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String message() {
		return isCommand ? value : "输入有误,请重新输入\n输入/help 查看命令列表";
	}

}
