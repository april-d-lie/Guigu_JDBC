package com.Guigu.api.statement;

import java.sql.*;
import java.util.Scanner;

import static java.sql.DriverManager.*;

public class StatementUserlogin {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入账号：");
        String account = scanner.nextLine();
        System.out.println("请输入密码：");
        String password = scanner.nextLine();

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = getConnection("jdbc:mysql:///guigu", "root", "root");

        Statement statement = connection.createStatement();

        String sql = "select * from t_user where account = '"+account+"' and password = '"+password+"';";

        ResultSet resultSet = statement.executeQuery(sql);

        if(resultSet.next()){
            System.out.println("登陆成功");
        }else {
            System.out.println("登陆失败");
        }

        resultSet.close();
        statement.close();
        connection.close();

    }
}
