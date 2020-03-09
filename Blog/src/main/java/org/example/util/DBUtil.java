package org.example.util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    /*
    * 获取数据库连接java.sql.Connection
    * 1.DriverManger.getConnection(url, username, password)
    * 每次都会创建一个真实的物理连接,connection.close()关闭连接，也是真实关闭物理连接。
    * 效率比较低
    * 2.DataSource（数据库连接池，数据源）
    * new MysqlDataSource().getConnection()
    * 特点：创建datasource对象的时候，就会初始化一定数量的数据库连接Connection
    * 使用的时候，直接在连接池DataSource中获取
    * 关闭的时候，connection.close()也不是关闭物理连接，而是把connection重置，返回连接池
    * */
    private static final String URL = "jdbc:mysql://localhost:3306/blogdemo";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    //使用单例模式创建DataSource

    private static volatile DataSource DATA_SOURCE;

    private DBUtil(){

    }

    //双充校验锁的写法
    private static DataSource getDataSource(){
        if (DATA_SOURCE == null){
            synchronized (DBUtil.class){
                if (DATA_SOURCE == null){
                    //初始化
                    DATA_SOURCE = new MysqlDataSource();
                    ((MysqlDataSource) DATA_SOURCE).setURL(URL);
                    ((MysqlDataSource) DATA_SOURCE).setUser(USERNAME);
                    ((MysqlDataSource) DATA_SOURCE).setPassword(PASSWORD);
                }
            }
        }
        return DATA_SOURCE;
    }

    //引入的是java.sql.Connection
    public static Connection getConnection(){
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("获取数据库连接失败", e);
        }
    }

    //释放资源
    public static void close(Connection connection, Statement statement, ResultSet resultSet){
        try {
            if (resultSet != null){
                resultSet.close();
            }
            if (statement != null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        }catch (Exception e){
            throw new RuntimeException("获取数据库连接失败", e);
        }

    }


    //非查询操作，不是resultSet结果集
    public static void close(Connection connection, Statement statement){
        close(connection, statement, null);
    }

}
