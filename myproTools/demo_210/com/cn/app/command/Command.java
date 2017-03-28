package com.cn.app.command;

public interface Command {
	/**
	 * 退出命令
	 */
	public static final String OUT="OUT";
	/**
	 * 结束命令
	 */
	public static final String EXIT="EXIT";
	/**
	 * 命令解析
	 * @param command
	 */
	public void parseCommand();
	/**
	 * 获取命令
	 * @return
	 */
	public String getCommand();
	/**
	 * 获取参数
	 * @return
	 */
	public String[] getArgv();
}
