package com.Guigu.api.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class PSUserlogin {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入账号：");
        String account = scanner.nextLine();
        System.out.println("请输入密码：");
        String password = scanner.nextLine();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///guigu", "root", "root");

        /*
        * preparedstatement
        *   1. 创建sql语句结构
        *   2. 创建preparedstatement
        *   3. 动态值赋值
        *   4. 发送sql语句
        */
        String sql = "select * from t_user where account = ? and password = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, account);
        preparedStatement.setObject(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            System.out.println("登陆成功");
        }else {
            System.out.println("登陆失败");
        }


    }
}
