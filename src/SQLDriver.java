import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDriver {
    // 连接实例
    private static Connection conn = null;
    public SQLDriver() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydbSystem?characterEncoding=utf-8", "root", "helloworld147");
    }

    //获得连接对象
    public static Connection getConnection(){
        return conn;
    }

    //关闭连接
    public static void CloseCon() throws SQLException{
        conn.close();
    }

}
