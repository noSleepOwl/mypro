package mytools.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import mytools.command.Choose;
import mytools.command.CommandMethod;
import mytools.features.Features;
import mytools.features.file.FileGet;
import mytools.features.formatstring.FormatString;
import mytools.features.systemmethod.SystemMethod;

public class newChoose extends CommandMethod {
	@Override
	public String getInput() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		return sc.nextLine().trim();
	}

	@Override
	public Features runFunction(String command) {
		if (Choose.GN_FORMAT_JAVA_STR.equalsIgnoreCase(command)) {
			String name = "java字符串格式化";
			String help = "请输入需要格式化的字符串";
			return new Features(name, help) {
				@Override
				public void methodBody(Object... parmy) { //
					result = new FormatString().formatJavaString(parmy[0].toString());
					outPut(result);
				}
			};
		} else if (Choose.GN_HELP.equalsIgnoreCase(command)) {
			String name = "帮助";
			String help = "按回车,显示所有的命令";
			return new Features(name, help) {
				@Override
				public void methodBody(Object... parmy) {
					new SystemMethod().systemHelp();
				}
			};
		} else if (Choose.GN_FILE_CREATE_FOLDER.equalsIgnoreCase(command)) {
			String name = "创建一个新的文件";
			String help = "输入路径创建新的文件夹";
			return new Features(name, help) {
				@Override
				public void methodBody(Object... parmy) {
					System.out.println("输入数字选择 根目录 1:O:\\orcaldriver\\njust-jw\\webapp");
					String aa = getInput();
					String pat = null;
					String pat_1 = "O:/orcaldriver/njust-jw/webapp";
					switch (aa) {
					case "1":
						pat = pat_1 + parmy[0].toString();
						break;
					default:
						System.out.println("请选择正确的路径");
						return;
					}
					new FileGet().createFolder(pat);
				}
			};
		} else if (Choose.GN_BOOTSTRAP_TABLE_TR.equalsIgnoreCase(command)) {
			String name = "bootstrapTabl 属性生成";
			String help = "请按回车继续输入";
			return new Features(name, help) {
				@Override
				public void methodBody(Object... parmy) {
					Map<String, String> val = new HashMap<>();
					System.out.println("开始输入key 和value");
					while (true) {
						System.out.print("key=");
						String key = getInput();
						if (key.equalsIgnoreCase("-q")) {
							break;
						}
						System.out.print("value=");
						String value = getInput();
						val.put(key, value);
						System.out.println("生成:" + key + "=" + value + "继续输入,或者输入 -Q 结束");
					}
					outPut(new FormatString().toStrapTable(val));
				}

			};
		} else if (Choose.GN_NEW_JSP.equalsIgnoreCase(command)) {
			String name = "添加模块页面";
			String help = "请输入路径";
			return new Features(name, help) {
				@Override
				public void methodBody(Object... parmy) {
					FileGet fg = new FileGet();

					System.out.println("输入模块名称");
					String fileNewName = getInput();
					fg.readFile(parmy[0].toString(), fileNewName);
				}

				@Override
				public void intoMessage() {
					System.out.println("\n输入路径 或者 输入 /out返回");
				}
			};

		} else {
			return null;
		}
	}
}
