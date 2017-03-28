package demo_320;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import com.jgoodies.common.base.SystemUtils;

interface Command{
	public static final String End = "END";
	public static final String StrSplit="STRSPLIT";
}

public class ManClass {
	/**
	 * 总的开关控制
	 */
	private static boolean goloab = true;
	private Map<String, Model> commandAndArguments;
	//加载所有的命令
	{
		Class<?> cla = Command.class;
		Field[] fields =cla.getDeclaredFields();
		for (Field field : fields) {
			commandAndArguments  = new HashMap<>();
			Command comm  = null;
			try {
				Model model = (Model)(Class.forName("demo_320.model"+field.getName()).newInstance());
				String command = field.get(comm).toString();
				commandAndArguments.put(command, model);
				System.out.println(command+"模块加载成功");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
				try {
					log(field.get(cla)+"模块加载失败");
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ManClass manClass = new ManClass();
		while(goloab){
			// 获取输入的命令
			String command = sc.nextLine();
			//解析命令   获取命令 和参数
			try {
				Model model = manClass.parseCommand(command);
				manClass.run(model);
			} catch (Exception e) {
				manClass.log(e.getMessage());
				sc = new Scanner(System.in);
			}
			//执行命令
		}
	}
	
	/**
	 * 命令的执行  获取惨数和命令之后进行执行
	 * @param commandAndArgus
	 */
	private void run(Model model) {
		try{
			model.run();
		}catch (Exception e) {
			log("请输入正确的参数 的测试版本");
		}
	}
	/**
	 * 命令 解析 , 解析成 命令和参数部分
	 * @param command
	 * @return
	 */
	private Model parseCommand(String command) throws RuntimeException {
		Model model = null;
		if(command!=null){
			String [] res  = Stream.of(command.split("\\s")).toArray(String[]::new);
			if(res.length == 1){
				model =	commandAndArguments.get(res[0].toUpperCase());
				if(model == null) throw new RuntimeException("请输入正确的命令1");
			}else {
				try {
					String[] argv =  Stream.of(res).skip(1).toArray(String[]::new);
					model =	commandAndArguments.get(res[0].toUpperCase());
					model.initArgv(argv);
				} catch (Exception e) {
					throw new RuntimeException("请输入正确的命令2");
				}
			}
		}else{
			throw new RuntimeException("请输入命令");
		}
		return model;
	}
	private void log(String error){
		System.out.println(error);
	}
	public static void setGoloab(boolean goloab) {
		ManClass.goloab = goloab;
	}
}
interface Model{
	public void initArgv(String [] argv);
	public void run();
}

abstract class Tools {
	protected String [] argv;
}
abstract class base extends Tools implements Model{
	@Override
	public void initArgv(String[] argv) {
		this.argv = argv;
	}
	@Override
	public abstract void run();
}
class modelEnd extends base implements Model
{
	@Override
	public void run() {
		ManClass.setGoloab(false);
		System.out.println("end 程序结束");
	}
}

class modelHelp extends base implements Model{
	public void run(){
		if(argv == null){
			System.out.println();
		}
	}
}
class modelStrSplit extends base implements Model{
	@Override
	public void run() {
		for (String string : argv) {
			System.out.println(string);
		}
	}
}
