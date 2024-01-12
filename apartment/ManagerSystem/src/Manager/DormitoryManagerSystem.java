/*目标：宿舍信息管理系统

基本功能：
1. 记录宿舍基本信息（如所楼层、属栋、区等，容量，当前住了多少人等，可自行思考相关）
2. 查询添加删除学生所属宿舍
3. 通过宿舍查询学生信息
4. 查询宿舍舍长
5. 需要有操作记录
6. 数据可以持久化存储
7. 面向对象开发
8. 可以通过 楼层、属栋、区等 查询有什么宿舍
9. 对入住学生信息可以进行排序（可以通过姓名、学号等）（通过姓名搜索时可通过部分姓名搜出所有相关结果）
10. 可以快速查找学生信息（学生姓名可能冲突）
11. 需要有足够的健壮性

评分标准：
1. 功能完成度（30%）
2. 代码结构以及健壮性（30%）
3. 执行效率（20%）
4. 用户体验（20%）

附加分：
在完成基本功能基础上拓展功能，视功能创意以及难度给分（30%）

提示：
1. 使用jdk的Api完成
2. 禁止使用第三方包
3. 需要答辩演讲思路并回答面试官问题（顺序抽签决定）*/



//学生添加删除 √
//宿舍添加删除 √

package Manager;

import java.sql.*;
import java.util.Scanner;


public class DormitoryManagerSystem {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/school";
        String user = "root";
        String password = "123456";

        Connection connection = DriverManager.getConnection(url,user,password);

        if(connection == null){
            System.out.println("数据库连接失败");
        }else {
            System.out.println("数据库连接成功");
        }

        Statement statement = connection.createStatement();

        while (true) {

            System.out.println("----------宿舍信息管理系统--------");
            System.out.println("1.新增、删除宿舍");
            System.out.println("2.学生入住、删除");
            System.out.println("3.宿舍查询");
            System.out.println("4.学生查询");
            System.out.println("5.操作记录");
            System.out.println("0.退出");
            System.out.print("请输入你的选择(数字):");
            Scanner in = new Scanner(System.in);
            switch (in.next()) {

                case "1": {
                    loop:while (true) {
                        System.out.println("----------宿舍信息管理系统--------");
                        System.out.println("1.新增宿舍");
                        System.out.println("2.删除宿舍");
                        System.out.println("0.返回上一级");
                        System.out.print("请选择您要进行的操作:");
                        switch (in.next()) {
                            case "0": {
                            break loop;
                            }
                            case "1": {
                                try{
                                    System.out.println("----------宿舍信息管理系统--------");
                                    String sql = DorOperate.DorAdd();
                                    statement.executeUpdate(sql);
                                    System.out.println("-------------------------------");
                                    System.out.println("添加成功！");
                                }catch (SQLIntegrityConstraintViolationException e){
                                    System.out.println("-------------------------------");
                                    System.out.println("该宿舍已存在！");    //防止添加失败
                                }
                                catch (SQLException e){
                                    System.out.println("-------------------------------");
                                    System.out.println("添加失败");
                                }

                                break;
                            }
                            case "2": {
                                try{
                                    System.out.println("----------宿舍信息管理系统--------");
                                    String sql = DorOperate.DorDelete();
                                    statement.executeUpdate(sql);
                                    System.out.println("-------------------------------");
                                    System.out.println("操作成功！");
                                }catch (SQLException e){
                                    System.out.println("-------------------------------");
                                    System.out.println("操作失败!");
                                }
                                break ;
                            }
                            default:{
                                System.out.println("-------------------------------");
                                System.out.println("没有该选项！");
                            break ;
                        }
                    }
                }
                break;
                }
                case "2": {
                    loop:while (true) {
                        System.out.println("----------宿舍信息管理系统--------");
                        System.out.println("1.学生入住");
                        System.out.println("2.学生删除");
                        System.out.println("0.返回上一级");
                        System.out.print("请选择您要进行的操作:");
                        switch (in.next()) {
                            case "0": {
                                break loop;
                            }

                            case "1": {
                                try {
                                    System.out.println("----------宿舍信息管理系统--------");
                                    String a = DorOperate.Dorid();   //宿舍唯一id
                                    String sql1 = "SELECT Capacity FROM dormitory WHERE DorId = '" + a + "';";
                                    ResultSet capacity = statement.executeQuery(sql1);
                                    capacity.next();
                                    int m = capacity.getInt("Capacity");
                                    capacity.close();
                                    String sql2 = "SELECT COUNT(*) AS persons FROM student WHERE DorId ='" + a + "';";
                                    ResultSet persons = statement.executeQuery(sql2);
                                    persons.next();
                                    int n = persons.getInt("persons");
                                    persons.close();
                                    if (m <= n) {
                                        System.out.println("-------------------------------");
                                        System.out.println("宿舍已住满!");
                                    } else {

                                        try {
                                            String sql = StudentOperate.StuAdd(a);
                                            statement.executeUpdate(sql);
                                            System.out.println("-------------------------------");
                                            System.out.println("添加成功！");
                                        } catch (SQLIntegrityConstraintViolationException e) {
                                            System.out.println("-------------------------------");
                                            System.out.println("该学生已入住，请先删除该学生!");
                                        } catch (SQLException e) {
                                            System.out.println("-------------------------------");
                                            System.out.println("添加失败");
                                        }
                                    }
                                    break;
                                }catch (SQLException e){
                                    System.out.println("-------------------------------");
                                    System.out.println("该宿舍不存在");
                                }
                                break ;
                            }
                            case "2": {
                                System.out.println("----------宿舍信息管理系统--------");
                                System.out.println("1.通过宿舍查找学号删除");
                                System.out.println("2.通过学生学号删除");
                                System.out.println("0.返回上一级");
                                System.out.print("请选择您要进行的操作:");
                                switch (in.next()) {
                                    case "1": {
                                        try {
                                            System.out.println("----------宿舍信息管理系统--------");
                                            String a = DorOperate.Dorid();
                                            String sql = "SELECT * FROM dormitory WHERE DorId ='" + a + "'";
                                            String sql1 = "SELECT * FROM student WHERE DorId ='" + a + "'";
                                            ResultSet resultSet = statement.executeQuery(sql);
                                            if (resultSet.next()) {
                                                resultSet.close();
                                                ResultSet resultSet1 = statement.executeQuery(sql1);
                                                if(resultSet1.next()) {
                                                    System.out.println("-------------------------------");
                                                    System.out.println("所住学生有:");
                                                    System.out.print("//姓名:" + resultSet1.getObject("name") + "\t");
                                                    System.out.print("年龄:" + resultSet1.getObject("age") + "\t");
                                                    System.out.print("学号:" + resultSet1.getObject("studentid") + "\t");
                                                    System.out.print("专业班级:" + resultSet1.getObject("major") + "\t");
                                                    System.out.print("手机号:" + resultSet1.getObject("phonenum") + "\t");
                                                    System.out.print("邮箱:" + resultSet1.getObject("email") + "  ");
                                                    System.out.println("宿舍号:" + resultSet1.getObject("Dorid") + "\t");
                                                    while (resultSet1.next()) {
                                                        System.out.print("//姓名:" + resultSet1.getObject("name") + "\t");
                                                        System.out.print("年龄:" + resultSet1.getObject("age") + "\t");
                                                        System.out.print("学号:" + resultSet1.getObject("studentid") + "\t");
                                                        System.out.print("专业班级:" + resultSet1.getObject("major") + "\t");
                                                        System.out.print("手机号:" + resultSet1.getObject("phonenum") + "\t");
                                                        System.out.print("邮箱:" + resultSet1.getObject("email") + "  ");
                                                        System.out.println("宿舍号:" + resultSet1.getObject("Dorid") + "\t");
                                                    }
                                                }
                                                else {
                                                    System.out.println("-------------------------------");
                                                    System.out.println("该宿舍暂无学生！");
                                                    break ;
                                                }

                                            }
                                            else{
                                                System.out.println("-------------------------------");
                                                System.out.println("未找到该宿舍！");
                                                break ;
                                            }
                                        }catch (SQLException e){
                                            System.out.println("-------------------------------");
                                            System.out.println("操作失败");
                                            break ;
                                        }
                                    }
                                    case "2": {
                                        try{
                                        String sql = StudentOperate.StuDelete();
                                        statement.executeUpdate(sql);
                                        System.out.println("-------------------------------");
                                        System.out.println("删除完成！");
                                        }catch (SQLException e){
                                            System.out.println("-------------------------------");
                                            System.out.println("删除失败");
                                        }

                                    }

                                    case "0": {
                                        break;
                                    }
                                    default:{
                                        System.out.println("-------------------------------");
                                        System.out.println("没有该选项!");
                                        break ;
                                    }
                                }
                                break;
                            }
                            default:{
                                System.out.println("-------------------------------");
                                System.out.println("没有该选项！");
                                break ;
                            }
                        }
                    }
                    break;
                }
                case "3": {
                    loop:while (true) {
                        System.out.println("----------宿舍信息管理系统--------");
                        System.out.println("1.单个宿舍查询");
                        System.out.println("2.范围查询");
                        System.out.println("0.返回上一级");
                        System.out.print("请输入您的选择:");

                        switch (in.next()) {
                            case "0":{
                                break loop;
                            }
                            case "1": {
                                System.out.println("----------宿舍信息管理系统--------");
                                String a = DorOperate.Dorid();
                                String sql = "SELECT * FROM dormitory WHERE DorId ='" + a + "'";
                                ResultSet rs = statement.executeQuery(sql);




                                if (rs.next()) {
                                    System.out.println("-------------------------------");
                                    System.out.println("查询到以下结果");
                                    System.out.println("区域:" + rs.getObject("Area") + "\t");
                                    System.out.print("栋属:" + rs.getObject("Building") + "\t");
                                    if (rs.getInt("Num") < 10) {
                                        System.out.print("房号:" + rs.getObject("Floor") + "0"
                                                + rs.getObject("Num"));
                                    } else {
                                        System.out.print("房号:" + rs.getObject("Floor")
                                                + rs.getObject("Num"));
                                    }
                                    System.out.println("容量:" + rs.getObject("Capacity") + "人\t");
                                    rs.close();

                                    String sql1 = "SELECT * FROM `student` WHERE DorId ='" + a + "'";
                                    ResultSet resultSet = statement.executeQuery(sql1);
                                    if (resultSet.next()) {
                                        resultSet.close();
                                        ResultSet resultSet1 = statement.executeQuery(sql1);
                                        System.out.println("所住学生有:");
                                        while (resultSet1.next()) {
                                            System.out.print("//姓名:" + resultSet1.getObject("name") + "\t");
                                            System.out.print("年龄:" + resultSet1.getObject("age") + "\t");
                                            System.out.print("学号:" + resultSet1.getObject("studentid") + "\t");
                                            System.out.print("专业班级:" + resultSet1.getObject("major") + "\t");
                                            System.out.print("手机号:" + resultSet1.getObject("phonenum") + "\t");
                                            System.out.print("邮箱:" + resultSet1.getObject("email") + "  ");
                                            System.out.println("宿舍号:" + resultSet1.getObject("Dorid") + "\t");
                                        }
                                        resultSet1.close();
                                    } else {
                                        System.out.println("-------------------------------");
                                        System.out.println("暂无学生入住");
                                    }
                                } else {
                                    System.out.println("-------------------------------");
                                    System.out.println("未查找到该宿舍");
                                }
                                break ;
                            }
                            case "2": {             //范围查询
                                System.out.println("----------宿舍信息管理系统--------");
                                System.out.println("1.区范围查找");
                                System.out.println("2.精确到栋范围查找");
                                System.out.println("3.精确到楼范围查找");
                                System.out.println("4.宿舍总览");
                                System.out.println("0.返回上一级");
                                System.out.print("请输入您的选择:");
                                switch (in.next()) {
                                    case "1": {
                                        System.out.println("----------宿舍信息管理系统--------");
                                        System.out.print("请输入区域(南区/北区):");
                                        String sql = "SELECT * FROM `dormitory` WHERE Area ='" + in.next() + "'";
                                        ResultSet rs = statement.executeQuery(sql);
                                        if (rs.next()) {    //使用next()会使光标下移，所以干脆在判断时顺便光标所指的数据
                                            System.out.println("-------------------------------");
                                            System.out.println("查询到以下结果");
                                            System.out.print("//区域:" + rs.getObject("Area") + "\t");
                                            System.out.print("栋属:" + rs.getObject("Building") + "\t");
                                            if (rs.getInt("Num") < 10) {
                                                System.out.print("房号:" + rs.getObject("Floor") + "0"
                                                        + rs.getObject("Num"));
                                            } else {
                                                System.out.print("房号:" + rs.getObject("Floor")
                                                        + rs.getObject("Num"));
                                            }
                                            System.out.println("容量:" + rs.getObject("Capacity") + "人\t");

                                            while (rs.next()) {
                                                System.out.print("//区域:" + rs.getObject("Area") + "\t");
                                                System.out.print("栋属:" + rs.getObject("Building") + "\t");
                                                if (rs.getInt("Num") < 10) {
                                                    System.out.print("房号:" + rs.getObject("Floor") + "0"
                                                            + rs.getObject("Num"));
                                                } else {
                                                    System.out.print("房号:" + rs.getObject("Floor")
                                                            + rs.getObject("Num"));
                                                }
                                                System.out.println("容量:" + rs.getObject("Capacity") + "人\t");

                                            }
                                        } else {
                                            System.out.println("暂无宿舍信息！");
                                        }
                                        break;
                                    }
                                    case "2": {
                                        Dormitory d = new Dormitory();
                                        System.out.println("----------宿舍信息管理系统--------");
                                        System.out.print("请输入区域(南区/北区):");
                                        d.setArea(in.next());
                                        System.out.print("请输入栋属(大写英文或数字):");
                                        d.setBuilding(in.next());
                                        String sql = "SELECT * FROM `dormitory` WHERE Area ='" + d.getArea() + "' AND Building ='" + d.getBuilding() + "';";
                                        ResultSet rs = statement.executeQuery(sql);

                                        if (rs.next()) {
                                            System.out.println("-------------------------------");
                                            System.out.println("查询到以下结果");
                                            System.out.print("//区域:" + rs.getObject("Area") + "\t");
                                            System.out.print("栋属:" + rs.getObject("Building") + "\t");
                                            if (rs.getInt("Num") < 10) {
                                                System.out.print("房号:" + rs.getObject("Floor") + "0"
                                                        + rs.getObject("Num"));
                                            } else {
                                                System.out.print("房号:" + rs.getObject("Floor")
                                                        + rs.getObject("Num"));
                                            }
                                            System.out.println("容量:" + rs.getObject("Capacity") + "人\t");


                                            while (rs.next()) {
                                                System.out.print("//区域:" + rs.getObject("Area") + "\t");
                                                System.out.print("栋属:" + rs.getObject("Building") + "\t");
                                                if (rs.getInt("Num") < 10) {
                                                    System.out.print("房号:" + rs.getObject("Floor") + "0"
                                                            + rs.getObject("Num"));
                                                } else {
                                                    System.out.print("房号:" + rs.getObject("Floor")
                                                            + rs.getObject("Num"));
                                                }
                                                System.out.println("容量:" + rs.getObject("Capacity") + "人\t");
                                            }
                                        } else {
                                            System.out.println("-------------------------------");
                                            System.out.println("暂无宿舍信息！");
                                        }
                                        break;
                                    }
                                    case "3": {
                                        Dormitory d = new Dormitory();
                                        System.out.print("请输入区域(南区/北区):");
                                        d.setArea(in.next());
                                        System.out.print("请输入栋属(大写英文或数字):");
                                        d.setBuilding(in.next());
                                        System.out.print("请输入楼层:");
                                        d.setFloor(in.nextInt());

                                        String sql = "SELECT * FROM `dormitory` WHERE Area = '" + d.getArea() + "'AND Building = '" + d.getBuilding() + "'AND Floor = '" + d.getFloor() + "';";
                                        ResultSet rs = statement.executeQuery(sql);


                                        if (rs.next()) {
                                            System.out.println("-------------------------------");
                                            System.out.println("查询到以下结果");
                                            System.out.print("//区域:" + rs.getObject("Area") + "\t");
                                            System.out.print("栋属:" + rs.getObject("Building") + "\t");
                                            if (rs.getInt("Num") < 10) {
                                                System.out.print("房号:" + rs.getObject("Floor") + "0"
                                                        + rs.getObject("Num"));
                                            } else {
                                                System.out.print("房号:" + rs.getObject("Floor")
                                                        + rs.getObject("Num"));
                                            }
                                            System.out.println("容量:" + rs.getObject("Capacity") + "人\t");
                                            while (rs.next()) {
                                                System.out.print("//区域:" + rs.getObject("Area") + "\t");
                                                System.out.print("栋属:" + rs.getObject("Building") + "\t");
                                                if (rs.getInt("Num") < 10) {
                                                    System.out.print("房号:" + rs.getObject("Floor") + "0"
                                                            + rs.getObject("Num"));
                                                } else {
                                                    System.out.print("房号:" + rs.getObject("Floor")
                                                            + rs.getObject("Num"));
                                                }
                                                System.out.println("容量:" + rs.getObject("Capacity") + "人\t");
                                            }
                                        } else {
                                            System.out.println("-------------------------------");
                                            System.out.println("暂无宿舍信息！");
                                        }
                                        break;

                                    }
                                    case "0":{
                                        break;
                                    }
                                    default:{
                                        System.out.println("-------------------------------");
                                        System.out.println("没有这个选项!");
                                    }
                                }
                                break;
                            }
                            default:{
                                System.out.println("-------------------------------");
                                System.out.println("没有该选项！");
                            }
                        }
                    }
                    break;
                }
                case "4": {
                    loop:while(true){
                        System.out.println("----------宿舍信息管理系统--------");
                        System.out.println("1.通过姓名查询");
                        System.out.println("2.通过学号查询");
                        System.out.println("3.学生总览");
                        System.out.println("0.返回上一级");
                        System.out.print("请选择您要进行的操作:");
                        switch (in.next()){
                            case "0": {
                                break loop;
                            }
                            case "1": {
                                String sql = StudentOperate.NameSearch();
                                ResultSet rs = statement.executeQuery(sql);
                                if (rs.next()){
                                    System.out.println("查询到以下结果:");
                                        //仅用来判断是否有值

                                    ResultSet  resultSet = statement.executeQuery(sql);   //调用next()光标会下移所以重开一个
                                while (resultSet.next()) {    //返回数据集，光标下走
                                    System.out.print("//姓名:" + resultSet.getObject("name") + "\t");
                                    System.out.print("年龄:" + resultSet.getObject("age") + "\t");
                                    System.out.print("学号:" + resultSet.getObject("studentid") + "\t");
                                    System.out.print("专业班级:" + resultSet.getObject("major") + "\t");
                                    System.out.print("手机号:" + resultSet.getObject("phonenum") + "\t");
                                    System.out.print("邮箱:" + resultSet.getObject("email") + "\t");
                                    System.out.println("宿舍号:" + resultSet.getObject("Dorid") + "\t");
                                    }
                                }
                                else
                                    System.out.println("未查询到该姓名");
                                break;
                            }
                            case "2": {
                                String sql = StudentOperate.IdSearch();
                                ResultSet rs = statement.executeQuery(sql);
                                if (rs.next()){
                                    System.out.println("查询到以下结果:");
                                    rs.close();     //仅用来判断是否有值
                                    ResultSet resultSet = statement.executeQuery(sql);   //调用next()光标会下移所以重开一个
                                    while (resultSet.next()) {    //返回数据集，光标下走
                                        System.out.print("//姓名:" + resultSet.getObject("name") + "\t");
                                        System.out.print("年龄:" + resultSet.getObject("age") + "\t");
                                        System.out.print("学号:" + resultSet.getObject("age") + "\t");
                                        System.out.print("专业班级:" + resultSet.getObject("major") + "\t");
                                        System.out.print("手机号:" + resultSet.getObject("phonenum") + "\t");
                                        System.out.print("邮箱:" + resultSet.getObject("email") + "\t");
                                        System.out.println("宿舍号:" + resultSet.getObject("Dorid") + "\t");
                                    }
                                }
                                else {
                                    System.out.println("-------------------------------");
                                    System.out.println("未查询到该学号");
                                    break;
                                }
                            }
                            case "3":{
                                System.out.println("----------宿舍信息管理系统--------");
                                System.out.println("1.姓名升序");
                                System.out.println("2.学号升序");
                                System.out.println("0.返回上一级");
                                System.out.print("请选择您要进行的操作:");
                                switch (in.next()){
                                    case "1":{
                                        ResultSet resultSet =statement.executeQuery("SELECT * FROM `student` ORDER BY CONVERT(SUBSTR(NAME,1,1) USING gbk) ASC");
                                        while (resultSet.next()) {    //返回数据集，光标下走
                                            System.out.print("//姓名:" + resultSet.getObject("name") + "\t");
                                            System.out.print("年龄:" + resultSet.getObject("age") + "\t");
                                            System.out.print("学号:" + resultSet.getObject("age") + "\t");
                                            System.out.print("专业班级:" + resultSet.getObject("major") + "\t");
                                            System.out.print("手机号:" + resultSet.getObject("phonenum") + "\t");
                                            System.out.print("邮箱:" + resultSet.getObject("email") + "\t");
                                            System.out.println("宿舍号:" + resultSet.getObject("Dorid") + "\t");
                                        }
                                        resultSet.close();
                                        break ;
                                    }
                                    case "2":{
                                        ResultSet resultSet1 =statement.executeQuery("SELECT * FROM `student` ORDER BY studentid ASC");
                                        while (resultSet1.next()) {    //返回数据集，光标下走
                                            System.out.print("//姓名:" + resultSet1.getObject("name") + "\t");
                                            System.out.print("年龄:" + resultSet1.getObject("age") + "\t");
                                            System.out.print("学号:" + resultSet1.getObject("age") + "\t");
                                            System.out.print("专业班级:" + resultSet1.getObject("major") + "\t");
                                            System.out.print("手机号:" + resultSet1.getObject("phonenum") + "\t");
                                            System.out.print("邮箱:" + resultSet1.getObject("email") + "  ");
                                            System.out.println("宿舍号:" + resultSet1.getObject("Dorid") + "\t");
                                        }
                                        resultSet1.close();
                                        break ;
                                    }
                                    case "0":{
                                        break ;
                                    }
                                    default:{
                                        System.out.println("-------------------------------");
                                        System.out.println("没有该选项！");
                                        break ;
                                    }
                                }
                            }
                            default:{
                                System.out.println("-------------------------------");
                                System.out.println("没有该选项！");
                                break ;
                            }
                        }
                    }
                    break;
                }
                case "5":{
                    ResultSet rs = statement.executeQuery("SELECT * FROM logs");
                    if (rs.next()){
                        System.out.println("-------------------------------");
                        System.out.println("操作类型:"+rs.getObject("operation")+"  操作时间:"+rs.getObject("operate_time")+"  数据变更:"+rs.getObject("operate_params"));
                        while (rs.next()){
                            System.out.println("操作类型:"+rs.getObject("operation")+"  操作时间:"+rs.getObject("operate_time")+"  数据变更:"+rs.getObject("operate_params"));
                        }
                    }
                    else{
                        System.out.println("-------------------------------");
                        System.out.println("暂无操作记录");
                    }
                    break;
                }
                case "0": {
                    System.exit(0);
                }
                default: {
                    System.out.println("-------------------------------");
                    System.out.println("没有该选项");
                    break;
                }
            }
        }
    }
}


