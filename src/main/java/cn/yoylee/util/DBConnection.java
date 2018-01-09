package cn.yoylee.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class DBConnection {

    private static DataSource dataSource = new ComboPooledDataSource();

    public static Connection getDBConnection(){
        Connection con=null;
        try {
            con=dataSource.getConnection();
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
