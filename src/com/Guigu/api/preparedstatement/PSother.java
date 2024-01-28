package com.Guigu.api.preparedstatement;

import com.mysql.cj.jdbc.Driver;
import org.junit.Test;

import java.sql.*;

public class PSother {

    @Test
    public void Re_PrimaryKey() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///guigu", "root", "root");
        String sql = "insert into t_user(account, password, nickname) values(?,?,?);";

        //告诉preparedstatement取主键
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setObject(1, "test1");
        preparedStatement.setObject(2, "test1");
        preparedStatement.setObject(3, "lihua");

        int i = preparedStatement.executeUpdate();
        if(i>0){
            System.out.println("插入成功");
            //取主键
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int id = resultSet.getInt(1);
            System.out.println("id = " + id);

        }else {
            System.out.println("插入失败");
        }

        preparedStatement.close();
        connection.close();

    }

}
