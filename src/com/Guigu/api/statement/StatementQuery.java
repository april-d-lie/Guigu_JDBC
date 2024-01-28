package com.Guigu.api.statement;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class StatementQuery {
    public static void main(String[] args) throws SQLException {

        //1.注册驱动
        DriverManager.registerDriver(new Driver());

        //2.获取连接
        //url:  jdbc:数据库厂商名://ip地址:端口号/数据库名
        Connection connection = DriverManager.
                getConnection("jdbc:mysql://127.0.0.1:3306/guigu", "root", "root");

        //3.创建statement
        Statement statement = connection.createStatement();

        //4.发送SQL语句，并获取返回结果
        String sql = "select * from t_user";
        ResultSet resultSet = statement.executeQuery(sql);

        //5.结果解析
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String account = resultSet.getString("account");
            String password = resultSet.getString("password");
            String nickname = resultSet.getString("nickname");
            System.out.println(id + "--" + account + "--" + password + "--" + nickname);
        }

        //6.关闭资源
        resultSet.close();
        statement.close();
        connection.close();
    }
}
