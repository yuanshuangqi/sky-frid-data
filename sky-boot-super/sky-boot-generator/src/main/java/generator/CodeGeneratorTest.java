package generator;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;

public class CodeGeneratorTest {
    private Connection connection;
    @Before
    public void init() throws Exception{
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://120.92.140.55:3306/sky-boot?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "5#/QqefSqV,-";
        Properties properties = new Properties();
        properties.put("user",username);
        properties.put("password",password);
        properties.put("remarksReporting","true");
        Class.forName(driver);
        connection = DriverManager.getConnection(url,properties);
    }
    // 获取数据库列表
   @Test
    public void test1() throws Exception{
       DatabaseMetaData metaData = connection.getMetaData();
       ResultSet catalogs = metaData.getCatalogs();
       while (catalogs.next()){
           System.out.println(catalogs.getString(1));
       }
       catalogs.close();
       connection.close();
   }
   // 获取数据库中的表
    @Test
    public void test2() throws Exception{
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet rs = metaData.getTables(null, null, null, new String[]{"TABLE"});
        while (rs.next()){
            System.out.println(rs.getString("TABLE_NAME"));
        }
    }
    // 获取数据库中表的字段信息

    /**
     * String catalog, 数据库名称
     * String schemaPattern, oracle中的表空间 大写
     *  String tableNamePattern, 表名称
     *  String columnNamePattern
     * @throws Exception
     */
    @Test
    public void test3() throws Exception{
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet rs = metaData.getColumns(null, null, "user", null);
        while (rs.next()){

        }

    }
}
