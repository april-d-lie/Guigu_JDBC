package com.Guigu.api.preparedstatement;

import org.junit.Test;

import java.sql.*;
import java.util.*;

public class PScurd {

    @Test
    public void testInsert() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///guigu","root","root");
        String sql = "insert into t_user(account, password, nickname) values(?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,"test");
        preparedStatement.setObject(2,"test");
        preparedStatement.setObject(3,"laowang");

        int rows = preparedStatement.executeUpdate();
        if(rows > 0){
            System.out.println("数据插入成功");
        }else {
            System.out.println("数据插入失败");
        }
        preparedStatement.close();
        connection.close();

    }

    @Test
    public void testUpdate() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///guigu", "root", "root");
        String sql = "update t_user set nickname = ? where id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,"zhangsan");
        preparedStatement.setObject(2,3);
        int row = preparedStatement.executeUpdate();
        if(row > 0){
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }

        preparedStatement.close();
        connection.close();

    }

    @Test
    public void testDelete() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///guigu", "root", "root");
        String sql = "delete from t_user where id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,3);
        int row = preparedStatement.executeUpdate();
        if(row > 0){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }

        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testSelect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///guigu", "root", "root");
        String sql = "select * from t_user;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Map> list = new ArrayList<>();

        //metaData获取列属性
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (resultSet.next()){
            Map map = new HashMap();
//            map.put("id", resultSet.getInt("id"));
//            map.put("account", resultSet.getString("account"));
//            map.put("password", resultSet.getString("password"));
//            map.put("nickname", resultSet.getString("nickname"));

            for(int i = 1; i <= columnCount; i++){
                Object value = resultSet.getObject(i);
                String columnLabel = metaData.getColumnLabel(i);
                map.put(columnLabel, value);
            }

            list.add(map);
        }
        System.out.println("list: " + list);

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }



}
