// This is the student class

public class Student {
	private String stu_id;
	private String stu_name;
	private double JavaScore;
	private double PythonScore;
	private double CScore;
	private double TotalScore;
	
	public String getStuId() {
		return stu_id;
	}
	
	public void setStuId(String id) {
		this.stu_id = id;
	}
	
	public String getStuName() {
		return stu_name;
	}
	
	public void setStuName(String name) {
		this.stu_name = name;
	}
	
	public double getJavaScore() {
		return JavaScore;
	}
	
	public void setJavaScore(double JavaScore) {
		this.JavaScore = JavaScore;
	}
	
	public double getPythonScore() {
		return PythonScore;
	}
	
	public void setPythonScore(double PythonScore) {
		this.PythonScore = PythonScore;
	}
	
	public double getCScore() {
		return CScore;
	}
	
	public void setCScore(double CScore) {
		this.CScore = CScore;
	}
	
	public double getTotalScore() {
		return TotalScore;
	}
	
	public void setTotalScore(double TotalScore) {
		this.TotalScore = TotalScore;
	}
}
