package com.zhangpeng.administrator.smarthome;

import android.content.Context;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Administrator on 2016/8/8.
 */

public class SQLcheck {
    String name;
    String password;
    Connection con;
    Statement sql;
    ResultSet rs;
    Context context;

    public SQLcheck(String name, String password,Context context) {
        this.name = name;
        this.password = password;
        this.context = context;
    }


    public boolean authenticate(){
        Boolean flag = false;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();//将驱动类生成实例
            String url = "jdbc:mysql://localhost/javalecture"; /* jdbc:mysql:// + 数据库地址/数据库名称*/
            con= DriverManager.getConnection(url,name,password);//创建链接
            sql=con.createStatement() ;
            rs=sql.executeQuery("SELECT * FROM  students");//从表名student查询全部
            while(rs.next()) {
                String id = rs.getString(1);
                String userName = rs.getString(2);
                String userPassword = rs.getString(3);
                if(userName == name){
                    if (userPassword == password){
                        flag = true;
                    }else{
                        Toast.makeText(context,"密码不正确",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flag;
    }
}
