import java.util.stream.Stream;

/**
 * 这个类主要的作用 主要的是对字符串进行解析 一个有规律的字符串,对其进行增删改查之后进行一个整合的操作输出
 * 
 * @author uigsw
 *
 */
public class ChangeToString {
	public ChangeToString(String target, String jianGe, String prefix, String suffix, String middle,
			CharSequence oldChar, CharSequence newChar) {
		this.target = target;
		this.jianGe = jianGe;
		this.preFix = prefix;
		this.suffix = suffix;
		this.middle = middle;
	}

	public static String StringToFormat() {
		return StringToFormat();
	}

	private String target;
	private String jianGe;
	private String preFix;
	private String suffix;
	private String middle;
	private CharSequence oldChar;
	private CharSequence newChar;

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getJianGe() {
		return jianGe;
	}

	public void setJianGe(String jianGe) {
		this.jianGe = jianGe;
	}

	public String getPreFix() {
		return preFix;
	}

	public void setPreFix(String preFix) {
		this.preFix = preFix;
	}

	public String getEnd() {
		return suffix;
	}

	public void setEnd(String end) {
		this.suffix = end;
	}

	public String getMiddle() {
		return middle;
	}

	public void setMiddle(String middle) {
		this.middle = middle;
	}

	public CharSequence getOldChar() {
		return oldChar;
	}

	public void setOldChar(CharSequence oldChar) {
		this.oldChar = oldChar;
	}

	public CharSequence getNewChar() {
		return newChar;
	}

	public void setNewChar(CharSequence newChar) {
		this.newChar = newChar;
	}

	/**
	 * 最近经常处理的是 使用map 来进行的处理, Map<String ,object>
	 * 
	 * @param oldChar
	 *            字符串中需要替换的字符
	 * @param newChar
	 *            替换的目标字符
	 * @param target
	 *            目标字符串台
	 * @param targetJianGe
	 *            字符串间隔
	 * @param prefix
	 *            添加前缀
	 * @param suffix
	 *            后缀
	 * @param groupMiddle
	 *            字符串之间新的间隔
	 * @return 新的字符串
	 */
	private String StringToFormat(String target, String targetJianGe, String prefix, String suffix, String groupMiddle,
			CharSequence oldChar, CharSequence newChar) {
		StringBuffer sb = new StringBuffer();
		sb.append(Stream.of(target.split(targetJianGe))
				.map(o -> prefix + replaceTo(o.trim(), oldChar, newChar) + suffix + groupMiddle)
				.reduce((o, a) -> o = o + a).get());
		sb.deleteCharAt(sb.lastIndexOf(groupMiddle));
		return sb.toString();
	}

	/**
	 * 字符替换的函数
	 * 
	 * @param target
	 * @param oldChar
	 * @param newChar
	 * @return
	 */
	private String replaceTo(String target, CharSequence oldChar, CharSequence newChar) {
		// Exception e = null;
		boolean haveFalse = false;
		try {
			return target.replace(oldChar, newChar);
		} catch (RuntimeException e) {
			haveFalse = true;
			throw new RuntimeException();
		} finally {
			if (haveFalse)
				return target;
		}

	}

	private static att TEstFora(att aec) {
		aec.setB(100);
		aec.setC(1000);
		return aec;
	}
	public static void main(String[] args) {
		att at = new att(10, 10);
		at.get();
		TEstFora(at);
		at.get();
	}
}

class att {
	public att(int b, int c) {
		this.b = b;
		this.c = c;
	}

	private int b;
	private int c;

	public void setB(int b) {
		this.b = b;
	}

	public void setC(int c) {
		this.c = c;
	}

	public void get() {
		System.out.println(String.format("b的指值是 %d ,c 的值是 %d", b, c));
	}
}
