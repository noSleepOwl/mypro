package mainbody;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Mianimport implements GenIneterface {

	private Object[] input;
	private FromCommandGetMethod fcg;
	private MethodModel MM;

	public Mianimport(FromCommandGetMethod fcg) {
		this.fcg = fcg;
	}

	@Override
	public Object[] input() {
		Scanner scanner = new Scanner(System.in);
		String res = scanner.nextLine();
		input = res.split(" ");
		return input;
	}

	@Override
	public void run() {
		while (true) {
			input();
			String command = getCommand();
			List<Object> par = getMethodParam();
			MM = fcg.getMMethodModel(command);
			if (MM == null) {
				System.out.println("命令错误");
				continue;
			}
			MM.run(par);
			if (MM.useSelfOutput()) {
				MM.output(); // 方法自定义的输出方法
			} else {
//				outPut(MM.run(par));
			}
		}
	}

	@Override
	public String outPut(String res) {
		return null;
	}

	/**
	 * 获取 命令
	 * 
	 * @return
	 */
	private String getCommand() {
		return input[0].toString();
	}

	/**
	 * 获取参数
	 * 
	 * @return
	 */
	private java.util.List<Object> getMethodParam() {
		java.util.List<Object> list = Arrays.asList(input);
		list.remove(0);
		return list;
	}
}
