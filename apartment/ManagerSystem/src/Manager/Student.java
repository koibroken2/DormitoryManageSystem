package Manager;

public class Student  {
    private String name; //姓名
    private int studentid; //学号
    private int age; //年龄
    private String grade; //班级
    private int phonenum; //手机号
    private String email; //邮箱

    private String Dorid; //宿舍号

    public Student() {
    }

    public Student(String name, int studentId, int age, String grade, int phoneNum, String email,String dorid) {
        this.name = name;
        studentid = studentId;
        this.age = age;
        this.grade = grade;
        phonenum = phoneNum;
        this.email = email;
        this.Dorid = dorid;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudentId() {
        return studentid;
    }

    public void setStudentId(int studentId) {
        this.studentid = studentId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getPhoneNum() {
        return phonenum;
    }

    public void setPhoneNum(int PhoneNum) {
        this.phonenum = PhoneNum;
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDorid(){return Dorid;}

    public void  setDorid(String dorid){this.Dorid = dorid;}
}

