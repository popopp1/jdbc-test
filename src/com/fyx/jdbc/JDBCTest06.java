package com.fyx.jdbc;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCTest06 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        ResourceBundle bundle = ResourceBundle.getBundle("JDBC");

        try {
            //1、注册驱动
            Class.forName(bundle.getString("driver"));
            //2、获取连接
            conn = DriverManager.getConnection(bundle.getString("url"),bundle.getString("user"),
                    bundle.getString("password"));
            //3、获取数据库操作对象
            stmt = conn.createStatement();
            //4、执行sql
            String sql = "insert into dept(deptno,dname,loc) values(90,'财务部','广东')";
            String sql2 = "select deptno,dname,loc from dept";

            int count = stmt.executeUpdate(sql);
            rs = stmt.executeQuery(sql2);
            System.out.println(count == 1 ? "插入成功" : "插入失败");
            //5、处理查询结果集
            while (rs.next()){
                String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                System.out.println(deptno + "," + dname + "," + loc);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
