package mytools.command;

import mytools.features.Features;

public abstract class CommandMethod {

	ControlCommand ccmd = new ControlCommand();

	/**
	 * 判断输入时不是 返回命令 false 不是 , ture 是的
	 * 
	 * @param command
	 * @return
	 */
	private boolean isCommand(String command) {
		boolean res = false;
		if (ccmd.getCommand(command.toUpperCase()) != null) {
			res = true;
		}
		return res;
	}

	/**
	 * 输入处理的方法
	 */
	private InputModel inPut(String command) {
		InputModel input = null;
		command = command.toUpperCase();
		if (isCommand(command)) {
			input = ccmd.getCommand(command);
			return input;
		} else {
			input = new InputModel();
			input.setCommand(false);
			input.setValue(command);
			return input;
		}
	}

	/**
	 * 命令执行 执行的流程 , 1:启动 >2输入命令 >3解析命令进入对应的功能 > 输出结果 > 等待新命令
	 */
	public void commandRun() {
		System.out.println("Welcome_Input:>\n");
		runFunction("/HELP").methodBody("/HELP");
		while (true) {
			String input = getInput();
			InputModel inputModel = inPut(input);
			if (!inputModel.isCommand()) {
				System.out.println(input + "\t:不是一个合法的命令  输入 /help 查看命令列表");
				continue;
			} else {
				// 执行问题 识别命令并执行相应的方法
				Features features = (Features) runFunction(input);
				if (features == null) {
					System.out.println("功能尚未开放 请重新输入");
					continue;
				} else {
					features.intoMessage();
					while (true) {
						input = getInput();
						inputModel = inPut(input);
						if (inputModel.isCommand()) {
							if (Choose.GN_OUT.equalsIgnoreCase(input)) {
								System.out.println("\t\t\t\t主页面:---------:输入指定命令 或者  输入 /exit 退出");
								runFunction("/HELP").methodBody("/HELP");
								break;
							}
							if (Choose.GN_HELP_THIS.equalsIgnoreCase(input)) {
								features.help();
							}
						}
						features.methodBody(input);
						features.outMessage();
					}

				}

			}
			if (Choose.GN_EXIT.equalsIgnoreCase(input))
				break;

		}
		System.out.println("--------------------\t<<程序执行结束>>");
	}

	/**
	 * 获取输入的方法
	 * 
	 * @return
	 */
	public abstract String getInput();

	/**
	 * 不同的命令执行不同的函数
	 * 
	 * @param command
	 * @return
	 */
	public abstract Features runFunction(String command);

}
