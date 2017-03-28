package mainbody;

/**
 * 程序的一个方式 , 读取输入 , 之后进行 判断和处理 , 之后进行输出
 * 输入的东西有 可以 是指令,也可以是 信息 , 输出的东西 可以是结果 , 提示 ,错误之类的信息 
 * 这一切都是在run
 *	方法里面执行的 
 * @author uigsw
 *
 */
public interface GenIneterface {
	/**
	 * 对于输入的处理 , 输入的指令只能是字符串 , 但是
	 * 在输入之后的问题中,有什么? 输入之后, 判断的问题,
	 *   1:输入的是什么 ? 
	 *   2:输入的东西怎么处理 
	 *   所以 需要定义一个输入类,在输入的实现中 ,对这个输入类进行判断 , 
	 *   做为输入的内容 , 都是字符串 , 但是 , 具体的判断之后 , 只有两种可能 , 
	 *   1:指令 
	 *   2:命令的参数 , 
	 *   现在的话 , 对于命令和参数的处理 有两种 , 命令行,+ 参数的同时输入
	 * 	 统一的使用 指令+参数的形式来进行格式的解析 , 这样的话 , 指令的格式要求就没有那么的严格
	 * 	统一额返回  obj 的数组
	 * 输入的方法
	 * @return 
	 */
	public abstract Object[] input(); 
	/**
	 * 传入一个方法的构造器 进行构造
	 * 运行的方法
	 */
	public abstract void run();
	
	/**
	 * 输出
	 * @param outInfo
	 * @return
	 */
	public abstract String outPut(String outInfo);
}
