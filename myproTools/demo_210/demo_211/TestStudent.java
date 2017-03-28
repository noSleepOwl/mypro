package demo_211;



public   class TestStudent{
	public static void main(String[] args) {
		Score sc = new Score();
		Student student = new Student("1","王日日日伟",false,sc);
		student.setScore(10.2, 102.3, 100.1, 20);
		System.out.println(student.getTotalScore());
		student.speak();
		student.speak("看我的湖蓝话 别准吧");
		student.speak("看我在说普通话了,是不是很标准","普通话");
	}	
}
class Score{
	/**
	 * 语文成绩
	 */
	private Double chinese;
	/**
	 * 外语成绩
	 */
	private Double english;
	/**
	 * 数学成绩
	 */
	private Double math;
	public Double getChinese() {
		return chinese;
	}
	public void setChinese(Double chinese) {
		this.chinese = chinese;
	}
	public Double getEnglish() {
		return english;
	}
	public void setEnglish(Double english) {
		this.english = english;
	}
	public Double getMath() {
		return math;
	}
	public void setMath(Double math) {
		this.math = math;
	}
	
	
	
}
class Student {
	public Student() {
	}
	public  Student(String id ,String name, boolean special ,Score score) {
		this.id = id;
		this.name = name;
		this.special = special;
		this.score = score;
	}
	private String id;
	private String name;
	private boolean special;
	private Score score;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Score getScore() {
		return score;
	}
	public void setScore(Score score) {
		this.score = score;
	}
	public void setScore(double chinese,double math ,double english,double x){
		score.setChinese(chinese);
		score.setMath(math);
		score.setEnglish(english);
	}
	public Double getTotalScore(){
		double TotalScore = score.getChinese()+score.getEnglish()+score.getMath();
		if(special)return TotalScore+10;
		else return TotalScore;
	}
	public boolean isSpecial() {
		return special;
	}
	public void setSpecial(boolean special) {
		this.special = special;
	}
	public void speak(){
		String content = "Hello World";
		this.speak(content);
	}
	public void speak(String content){
		String language = "湖兰话";
		this.speak(content,language);
	}
	public void speak(String content ,String language){
		System.out.println("你好我的名字是:"+name +"我说"+language+"我说一句你听听:"+content);
	}
	
}


