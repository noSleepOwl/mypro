package myproTools;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.ibatis.reflection.invoker.GetFieldInvoker;
import org.apache.tools.ant.types.resources.Files;

/**
 * @author uigsw 系统文件的操作 , 1 : 获取文件 , 2:操作文件 , 3:输出文件(这里的输出 可能是字面意思的输出文件,
 *         也可能是,文件名称,或者对文件类的操作) 这个 接口所定义的是 文件的操作类 的一个大致的框架 ,作为一个文件,操作类需要满足的东西 ,*
 * @param <O>
 *            操作文件 O:文件的处理结果
 * @param <R>
 *            R 最终需要返回的东西
 * @param <P>
 *            文件的输出指向
 */
public abstract class FileControl<O, R, P> {
	/**
	 * 文件的路径
	 */
	private String path;
	/**
	 * 文件的输出类
	 */
	private P p;

	public FileControl(String path, P p) {
		this.path = path;
		this.p = p;
	}

	/**
	 * 获取文件,
	 * 
	 * @param path
	 *            文件的路径
	 * @return
	 */
	public File[] getFile(File file) {
		return getFile(file, null);
	}

	private File[] getFile(File file, File[] files) {
		files = files == null ? new File[0] : files;
		List<File> fileList = Stream.of(files).filter(o -> o != null).collect(Collectors.toList());
		if (file.exists()) {
			if (file.isFile()) {
				fileList.add(file);
			} else {
				for (File fil : file.listFiles()) {
					getFile(fil, files);
				}
			}
		}

		return fileList.toArray(new File[fileList.size()]);
	}

	/**
	 * 
	 * @param file
	 *            一个或者多个文件
	 * @return
	 */
	public abstract O controlFile(File[] file);

	/**
	 *
	 * 
	 * @return
	 */
	public abstract R outputFile(O o, P p);

	/**
	 * 开始执行
	 */
	public void run() {
		File file = new File(path);
		File[] files = getFile(file);
		O o = controlFile(files);
		outputFile(o, p);
	}
}
