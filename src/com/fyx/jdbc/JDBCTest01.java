package com.fyx.jdbc;

import java.sql.*;

public class JDBCTest01 {
    public static void main(String[] args) {
        Statement stmt = null;
        Connection conn = null;
        try {
        //1、注册驱动
            /*
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
             */
            Class.forName("com.mysql.cj.jdbc.Driver");

        //2、获取连接
            String url = "jdbc:mysql://127.0.0.1:3306/bjpowernode";
            String user = "root";
            String password = "021221Ljy";
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("数据库连接对象 = " + conn);

        //3、获取数据库操作对象
            stmt = conn.createStatement();

        //4、执行sql
            String sql = "insert into dept(deptno,dname,loc) values(50,'人事部','广州')";
            //executeUpdate专门执行DML语句(insert,delete,update)
            //返回值是”影响数据库中的记录条数“
            int count = stmt.executeUpdate(sql);
            System.out.println(count == 1 ? "保存成功" : "保存失败");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
        //6、释放资源
            //为了保证资源一定释放，在finally语句块中关闭资源
            //并且要遵循从小到大依次关闭
            //分别对其try catch()
            if (stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }




        //5、处理查询结果集

    }
}
