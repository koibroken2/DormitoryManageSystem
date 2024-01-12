package Manager;

import java.util.Scanner;


public class DorOperate {



    public static String DorAdd(){
        Dormitory dormitory = new Dormitory();
        Scanner in = new Scanner(System.in);
        System.out.println("请输入宿舍区（南区/北区):");
        dormitory.setArea(in.next());
        System.out.println("请输入栋属(数字或大写字母):");
        dormitory.setBuilding(in.next());
        System.out.println("输入房间号");
        int a = in.nextInt();
        dormitory.setFloor(a/100);
        dormitory.setNum(a%100);
        System.out.println("请输入宿舍可容纳人数（默认为4）");
        dormitory.setCapacity(in.nextInt());

        //通过get对象属拼凑出sql语句
        if (dormitory.getNum()>10) {
            return "INSERT INTO dormitory(Area,Building,Floor,Num,Capacity,DorId) VALUES('"
                    + dormitory.getArea() + "','"
                    + dormitory.getBuilding() + "','"
                    + dormitory.getFloor() + "','"
                    + dormitory.getNum() + "','"
                    + dormitory.getCapacity() + "','"
                    + dormitory.getArea() + dormitory.getBuilding() + dormitory.getFloor() + dormitory.getNum() + "');";
        }
        else{
            return "INSERT INTO dormitory(Area,Building,Floor,Num,Capacity,DorId) VALUES('"
                    + dormitory.getArea() + "','"
                    + dormitory.getBuilding() + "','"
                    + dormitory.getFloor() + "','"
                    + dormitory.getNum() + "','"
                    + dormitory.getCapacity() + "','"
                    + dormitory.getArea() + dormitory.getBuilding() + dormitory.getFloor() +"0"+ dormitory.getNum() + "');";
        }


    }
    public static String DorDelete(){
        System.out.println("请选择要删除的宿舍");

        return "DELETE student,dormitory FROM dormitory JOIN student ON dormitory.DorId = student.DorId WHERE dormitory.DorId = '"+Dorid()+"';";
    }


    public static String Dorid(){
        Scanner in = new Scanner(System.in);
        Dormitory dormitory = new Dormitory();
        System.out.print("请输入宿舍区（南区/北区):");
        dormitory.setArea(in.next());
        System.out.print("请输入栋属(数字或大写字母):");
        dormitory.setBuilding(in.next());
        System.out.print("输入房间号:");
        int a = in.nextInt();
        dormitory.setFloor(a/100);
        dormitory.setNum(a%100);
        if (a%100<10){
            return dormitory.getArea()
                    +dormitory.getBuilding()
                    +dormitory.getFloor()+"0"
                    +dormitory.getNum();
        }
        else {
            return dormitory.getArea()
                    + dormitory.getBuilding()
                    + dormitory.getFloor()
                    + dormitory.getNum();   //形成唯一宿舍id
        }

    }
}




