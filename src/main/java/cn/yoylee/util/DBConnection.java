package cn.yoylee.util;

import java.sql.*;

public class DBConnection {

    private static String driverName="com.mysql.cj.jdbc.Driver";
    private static String userName="root";
    private static String userPwd="lyy504677";
    private static String dbName="book_db";

    public static Connection getDBConnection(){
        String url1="jdbc:mysql://111.231.113.148:3306/"+dbName;
        String url2="?user="+userName+"&password="+userPwd;
        String url3="&useUnicode=true&characterEncoding=utf-8";
        String url=url1+url2+url3;
        Connection con=null;
        try {
            Class.forName(driverName);
            con=DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void closeDB(Connection con,PreparedStatement pstm,ResultSet rs){
        if(rs!=null)
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        if(pstm!=null)
            try {
                pstm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        if(con!=null)
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
