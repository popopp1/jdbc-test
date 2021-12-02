package com.fyx.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
create table t_act(
    actno int,
    balance double(7,2)
);
insert into t_act(actno,balance) values(111,20000);
insert into t_act(actno,balance) values(222,0);
commit;
select * from t_act;

重点三行代码：
    conn.setAutoCommit(false);//开启事务
    conn.commit();//提交事务
    conn.rollback();//回滚事务
 */
public class JDBCTest12 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1、注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2、获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","021221Ljy");
            //将自动提交机制修改为手动修改
            conn.setAutoCommit(false);  //开启事务
            //3、获取预编译操作对象
            String sql = "update t_act set balance = ? where actno = ?";
            ps = conn.prepareStatement(sql);

            //给占位符传值
            ps.setDouble(1,10000);
            ps.setInt(2,111);
            int count = ps.executeUpdate();

            //再给占位符？传值
            ps.setDouble(1,10000);
            ps.setInt(2,222);
            count += ps.executeUpdate();

            System.out.println(count == 2 ? "转账成功" : "转账失败" );
            //程序能够走到这里，说明程序没有发生异常，事务结束，收到提交数据
            conn.commit();//提交事务

        } catch (Exception e) {
            //回滚事务
            if (conn != null){
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
