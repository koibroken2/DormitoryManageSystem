package Manager;


import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class StudentOperate {



    public static String StuAdd(String a){



        Student s = new Student();
        Scanner in = new Scanner(System.in);   //将唯一宿舍id赋予学生对象

        System.out.print("请输入学生学号:");
        s.setStudentId(in.nextInt());
        System.out.print("请输入学生姓名:");
        s.setName(in.next());
        System.out.print("请输入学生年龄:");
        s.setAge(in.nextInt());
        System.out.print("请输入学生班级:");
        s.setGrade(in.next());
        System.out.print("请输入学生电话号码:");
        s.setPhoneNum(in.nextInt());
        System.out.print("请输入学生邮箱:");
        s.setEmail(in.next());

        return "INSERT INTO student(studentid,name,age,major,phonenum,email,DorId) VALUES('"
                +s.getStudentId()+"','"
                +s.getName()+"','"
                +s.getAge()+"','"
                +s.getGrade()+"','"
                +s.getPhoneNum()+"','"
                +s.getEmail()+"','"
                +a+"');";




    }
    public static String StuDelete() {
        Scanner in = new Scanner(System.in);
        Student s = new Student();
        System.out.println("请输入要删除学生的学号:");
        s.setStudentId(in.nextInt());

        return "DELETE FROM student WHERE studentid ='"+s.getStudentId()+"';";

    }
    public static String NameSearch(){
        Scanner in = new Scanner(System.in);
        Student s = new Student();
        System.out.print("请输入学生的姓名:");
        s.setName(in.next());

        return "SELECT * FROM `student` WHERE name LIKE '%"+s.getName()+"%';";
    }
    public static String IdSearch(){
        Scanner in = new Scanner(System.in);
        Student s = new Student();
        System.out.println("请输入学生学号");
        s.setStudentId(in.nextInt());
        return "SELECT * FROM `student` WHERE studentid LIKE '%"+s.getStudentId()+"%' ORDER BY studentid ASC;";

    }
}


