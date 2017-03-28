package mytools.features.file;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author uigsw 文件操作工具实现类 : 1:赋值单个文件 2:复制文件夹包括里面的文件 3:删除单个文件 4:删除文件夹包括里面的文件
 *         5:重命名单个文件 6:重命名文件夹中相同类型的文件格式: xxx_1.jpg. xxx_2.jpg,``````
 *         7:剪切点个文件到指定文件夹,包含里面的文件
 *         9:指定文件夹下指定类型文件统计数量,包含里面文件夹下符合条件的文件,并返回每个符合条件文件的绝对路径字符串台 arrayList
 *         10:指定目录下查找符合条件的文件,并返回包含条件文件的绝对路径字符串台 arrayList
 * 
 *
 */
public interface FileOperationInterface {
	/**
	 * 1.复制单个文件 <br>
	 * 2.复制文件夹(包括里边的文件)
	 * 
	 * @param sorFile
	 *            需要复制的文件或文件夹绝对路径
	 * @param toFile
	 *            目标文件夹绝对路径
	 * @return <br>
	 *         boolean true 复制成功 false 复制失败
	 */
	public abstract boolean copyFils(String sorFile, String toFile) throws IOException;

	/**
	 * 3.删除单个文件 <br>
	 * 4.删除文件夹(包括里边的文件)
	 * 
	 * @param delFile
	 *            需要删除的文件绝对路径或文件夹绝对路径
	 * @return <br>
	 *         boolean true 删除成功 false 删除失败
	 */
	public abstract boolean deleteFiles(String delFile) throws IOException;

	/**
	 * 5.重名单个文件
	 * 
	 * @param oldFile
	 *            需要重命名的文件绝对路径
	 * @param newName
	 *            新的文件名
	 * @return <br>
	 *         boolean true 重命名成功 false 重命名失败
	 */
	public abstract boolean renameFile(String oldFile, String newName) throws IOException;

	/**
	 * 6.重命名文件夹中相同类型的文件格式：XXX_1.jpg、XXX_2.jpg、XXX_3.jpg
	 * 
	 * @param files
	 *            文件所在的文件夹绝对路径
	 * @param type
	 *            指定类型的文件
	 * @param name
	 *            指定文件名的前一部分
	 * @return <br>
	 *         boolean true 重命名成功 false 重命名失败
	 */
	public abstract boolean renameFile(String files, String type, String name) throws IOException;

	/**
	 * 7.剪切单个文件到指定文件夹 <br>
	 * 8.剪切文件夹到指定文件夹(包含里边的文件)
	 * 
	 * @param file
	 *            需要剪切的文件或文件夹所在绝对路径
	 * @param toFiles
	 *            剪切到的文件夹绝对路径
	 * @return <br>
	 *         boolean true 剪切成功 false 剪切失败
	 */
	public abstract boolean clipFile(String file, String toFiles) throws IOException;

	/**
	 * 9.指定文件夹下指定类型文件统计数量(包含里边文件夹下的符合条件文件)，并返回每个符合条件的文件的绝对路径字符串ArrayList
	 * 
	 * @param findFiles
	 *            查找的目录绝对路径
	 * @param type
	 *            查找的文件类型 格式：.jpg、.exe(不区分大小写)
	 * @return <br>
	 *         ArrayList<String> 返回所有符合条件的文件绝对路径的字符串ArrayList<br>
	 *         注意：返回值ArrayList<String>对象的xX.get(0)是统计的数量，即就是第一个存储的是统计结果
	 */
	public abstract ArrayList<String> findFileType(String findFiles, String type) throws IOException;

	/**
	 * 10.指定目录下查找符合条件的文件，并返回包含符合条件的文件绝对路径字符串ArrayList
	 * 
	 * @param findFiles
	 *            查找的目录绝对路径
	 * @param file
	 *            查找的文件名，可以是一部分也可以全名
	 * @return <br>
	 *         ArrayList<String> <br>
	 *         返回所有符合条件的文件绝对路径的字符串ArrayList
	 */
	public abstract ArrayList<String> findFile(String findFiles, String file) throws IOException;
}
