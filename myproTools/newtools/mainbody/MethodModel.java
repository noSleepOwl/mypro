package mainbody;

import java.util.List;

/**
 * 方法的模板
 * 
 * @author uigsw
 *
 */
public interface MethodModel {
	@command(command = "HELP", shuoMing = "帮助文档")
	public static final String HELP = "HELP";

	/**
	 * 名字
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * 命令
	 * 
	 * @return
	 */
	public String getComm();

	/**
	 * 帮助
	 * 
	 * @return
	 */
	public String getHelp();

	/**
	 * 方法主体
	 * 
	 * @return
	 */
	public <T> T run(List<T> obj);

	/**
	 * 结果输出
	 */
	public void output();

	/**
	 * 是否使用自身的方法
	 * 
	 * @return
	 */
	public boolean useSelfOutput();
}
