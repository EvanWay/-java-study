package com.evan.study.mybatis;

import com.mysql.cj.jdbc.Driver;
import com.sun.xml.internal.fastinfoset.util.ValueArrayResourceException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;
import java.util.Properties;

/**
 * 测试JDBC简单使用
 */
@SpringBootTest
class JDBCTests {

    /**
     * 简单执行
     *
     */
    @Test
    void Test1() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        // 1.加载数据库驱动
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver)aClass.newInstance();
        String url = "jdbc:mysql://182.254.137.214:3307/study?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8";
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","EVANway123");

        //2.获取数据库连接
        Connection connect = driver.connect(url, properties);

        //3.执行sql
        String sql = "INSERT INTO `user`(user_name,password,real_name) VALUES('test1','test1','TEST1')";
        Statement statement = connect.createStatement();
        int rows = statement.executeUpdate(sql);// 返回生效行数
        System.out.println(rows > 0 ? "成功" : "失败");

        //4.释放连接
        statement.close();
        connect.close();
    }

    /**
     * statement、PreparedStatement
     *
     */
    @Test
    void Test2() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        // 1.加载数据库驱动（其实JDBC4.0开始就不需要加载，会自动SPI加载）
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://182.254.137.214:3307/study?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8";
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","EVANway123");

        //2.获取数据库连接
        Connection connect = DriverManager.getConnection(url, properties);

        //3.执行sql
        String sql = "INSERT INTO `user`(user_name,password,real_name) VALUES('test2','test2','TEST2')";

        //statement
        Statement statement = connect.createStatement();
        int rows = statement.executeUpdate(sql);// 返回生效行数
        System.out.println(rows > 0 ? "成功" : "失败");

        //PreparedStatement——防sql注入
        PreparedStatement ps = connect.prepareStatement("INSERT INTO `user`(user_name,password,real_name) VALUES(?,?,?)");
        ps.setObject(1,"222");
        ps.setObject(2,"222");
        ps.setObject(3,"222");
        int i = ps.executeUpdate();
        System.out.println(i > 0 ? "成功" : "失败");

        //executeQuery——返回ResultSet
        PreparedStatement ps2 = connect.prepareStatement("SELECT * FROM user");
        ResultSet resultSet = ps2.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("user_name") + "-" + resultSet.getString("password") + "-" + resultSet.getString("real_name"));
        }

        //4.释放连接
        statement.close();
        ps.close();
        connect.close();
    }

    /**
     * 手动事务
     *
     */
    @Test
    void Test3() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        // 1.加载数据库驱动
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://182.254.137.214:3307/study?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8";
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","EVANway123");

        //2.获取数据库连接
        Connection connect = DriverManager.getConnection(url, properties);
        //关闭自动提交
        connect.setAutoCommit(false);

        //3.执行sql
        String sql = "INSERT INTO `user`(user_name,password,real_name) VALUES('000','000','000')";

        //statement
        Statement statement = connect.createStatement();
        int rows = statement.executeUpdate(sql);// 返回生效行数
        System.out.println(rows > 0 ? "成功" : "失败");

        //提交事务
        connect.commit();

        //4.释放连接
        statement.close();
        connect.close();
    }

    /**
     * 批量执行
     *
     */
    @Test
    void Test4() throws ClassNotFoundException, SQLException {
        // 1.加载数据库驱动
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://182.254.137.214:3307/study?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8";
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","EVANway123");

        //2.获取数据库连接
        Connection connect = DriverManager.getConnection(url, properties);

        //3.执行sql
        String sql = "INSERT INTO `user`(user_name,password,real_name) VALUES(?,?,?)";
        PreparedStatement ps = connect.prepareStatement(sql);
        for (int i = 1; i <= 10; i++) {
            ps.setObject(1, "name_" + i);
            ps.setObject(2, "222");
            ps.setObject(3, "222");
            //1.攒sql
            ps.addBatch();
            if (i % 5 == 0) {
                //2.执行batch
                ps.executeBatch();
                //3.清空batch
                ps.clearBatch();
            }
        }

        //4.释放连接
        ps.close();
        connect.close();
    }
}
