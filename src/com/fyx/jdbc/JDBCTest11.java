package com.fyx.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
JDBC事务机制
    1、JDBC中的事务是自动提交的
        只要执行任意一条DML语句，则自动提交一次。这是JDBC默认的事务行为
        但是在实际的业务当中，通常都是N条DML语句共同联合才能完成的，必须
        保证他们这些DML语句在同一个事务中同时成功或者同时失败

    2、以下程序先来验证一下JDBC的事务是否自动提交机制
        测试结果：JDBC中只要执行任意一条DML语句，就提交一次
 */
public class JDBCTest11 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            //1、注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2、获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","021221Ljy");
            //3、获取预编译数据库操作对象
            String sql = "update dept set dname = ? where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"x部门");
            ps.setInt(2,30);
            int count = ps.executeUpdate();
            System.out.println(count);

            //4、重新给占位符传值
            ps.setString(1,"y部门");
            ps.setInt(2,20);
            count = ps.executeUpdate();
            System.out.println(count);

            //5、执行SQL
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //6、释放资源
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
