package demo_323;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControlJspFile{
	public static boolean notUpdate = false;
	public static void main(String[] args) {
		String base = "C:\\Users\\uigsw\\Desktop\\test\\";
		File file = new File("O:\\orcaldriver\\njust-jw");
	List<File> list =retFile(file);
	list.forEach(o->{
		StringBuffer sb = openFile(o);
			sb = new StringBuffer(changeToobar(sb.toString()));
		if(notUpdate)
		writeFile(sb,o);
	});
	}
	
	private static String changeToobar (String str){
		notUpdate = true;
		String findModelStar = "";
		return str;
	}
	


	private static String changeText(String str) {
		notUpdate = true;
		String replaceRegex = "<div\\s*class=\"col-md-12\\s*text-right\\s*badge-sm\">";
		String end ="</div>";
		String insertStar = "</form>\\s*</div>";
		String addDiv ="<div class=\"panel-footer text-right\">\n";
		Matcher mat = getRegex(str, replaceRegex);
		String getTar = null;
		while(mat.find()){
			int thisButtonDivStarS = mat.start();
			int thisButtonDivStarE = mat.end();
			
			Matcher endMat = getRegex(str, end);
			 endMat.find(mat.start());
			 int endFlagEnd  = endMat.end();
			 int endflagStar = endMat.start();
			  getTar = str.substring(thisButtonDivStarE,endflagStar);//获取按钮的div
			  getTar = getTar.replaceAll("w-xs", "w-xs btn-xs"); // 替换样式
			  getTar=addDiv+getTar+end+"\n";
			 str = str.substring(0,thisButtonDivStarS)+str.substring(endFlagEnd,str.length());
		}
		Matcher matind = getRegex(str, insertStar);
		int insertIndex = 0;
		while (matind.find()) {
			 insertIndex = matind.end();
		}
		if(getTar == null){
			notUpdate = false;
		}
		str ="<"+str.substring(0,insertIndex)+getTar+str.substring(insertIndex,str.length());
		return str;
	}


	private static List<File> retFile(File file){
		List<File> list = new ArrayList<>();
		if(file.exists()){
			if(file.isDirectory()){
				File[] files = file.listFiles();
				for (File fi : files) {
					list.addAll(retFile(fi)); 
				}
			}else {
				String path = file.getAbsolutePath();
				if(path.endsWith("list.jsp")){
					list.add(file);
				}
			}
		}
		return list;
	}
	private static void writeFile(StringBuffer buffer,File file){
		if(!(file.exists()&&file.isFile())){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			BufferedWriter out = null;
			try {
				out = new BufferedWriter(new FileWriter(file));
				out.write(buffer.toString().trim());
				out.flush();
			} catch ( IOException e) {
				e.printStackTrace();
			}finally {
				try {
					if(out != null)
						out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	private static StringBuffer openFile(File file){
		StringBuffer sb = new StringBuffer();
		if(file.exists() && file.isFile()){
			BufferedInputStream buff = null;
			try {
				 buff = new BufferedInputStream(new FileInputStream(file));
				while(buff.read()!=-1){
					byte [] b = new byte[10000];
					buff.read(b);
					sb.append(new String(b,"UTF-8"));
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				if(buff != null){
					try {
						buff.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return sb;
	}
	private static Matcher getRegex(String tar ,String regex){
		Pattern pat = Pattern.compile(regex,Pattern.MULTILINE);
		return pat.matcher(tar);
	}
}

