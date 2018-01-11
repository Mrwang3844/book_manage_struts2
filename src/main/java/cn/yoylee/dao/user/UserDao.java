package cn.yoylee.dao.user;


import cn.yoylee.entity.User;
import cn.yoylee.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDao {

    // 登录
    public User login(User user) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = DBConnection.getDBConnection();
        User user2 = null;
        String sql = "select * from user where  stuid=? and  stupassword=? ";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, user.getStuid());
            pstmt.setString(2, user.getPassword());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user2 = new User();
                user2.setStuid(rs.getInt("stuid"));
                user2.setPassword(rs.getString("stupassword"));
                user2.setPower(rs.getInt("power"));
                user2.setCount(rs.getInt("count"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(con, pstmt, rs);
        }
        return user2;
    }
    // / 查询所有的用户信息
    public List<User> selectAllUser() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = DBConnection.getDBConnection();
        List<User> users = new ArrayList<User>();
        String sql = "select * from user";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            User user;
            while (rs.next()) {
                user = new User();
                user.setStuid(rs.getInt("stuid"));
                user.setPassword(rs.getString("stupassword"));
                user.setPower(rs.getInt("power"));
                user.setCount(rs.getInt("count"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(con, pstmt, rs);
        }
        return users;
    }
    // 获得单个用户信息
    public User  selectOneUser(String stuid) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = DBConnection.getDBConnection();
        String sql = "select * from user where stuid = ?";
        User user = new User();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,stuid);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user.setStuid(rs.getInt("stuid"));
                user.setPassword(rs.getString("stupassword"));
                user.setPower(rs.getInt("power"));
                user.setCount(rs.getInt("count"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(con, pstmt, rs);
        }
        return user;
    }
    // / 模糊查询用户信息
    public List<User>  selectUserLike(String stuid) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = DBConnection.getDBConnection();
        List<User> users = new ArrayList<User>();
        String sql = "select * from user where stuid like '%" + stuid + "%'";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            User user;
            while (rs.next()) {
                user = new User();
                user.setStuid(rs.getInt("stuid"));
                user.setPassword(rs.getString("stupassword"));
                user.setCount(rs.getInt("count"));
                user.setPower(rs.getInt("power"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(con, pstmt, rs);
        }
        return users;
    }

    // 想数据库中插入一个用户
    public int register(User user) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = DBConnection.getDBConnection();
        int row = 0;
        String sql = "insert into user values(?,?,?,?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, user.getStuid());
            pstmt.setString(2, user.getPassword());
            pstmt.setInt(3,0);
            pstmt.setInt(4,0);
            row = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(con, pstmt, rs);
        }
        return row;
    }

    // 修改用户密码
    public int updatePassword(int stuid, String pass) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBConnection.getDBConnection();
        String sql = "update user set stupassword=? where stuid = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, pass);
            pstmt.setInt(2, stuid);
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(con, pstmt, rs);
        }
        return row;
    }
    // 修改用户借阅数量
    public int updateCount(int stuid,int num) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBConnection.getDBConnection();
        String sql = "update user set count=count+1 where stuid = ?";
        String sql2 = "update user set count=count-1 where stuid = ?";
        try {
            if (num ==1)   //num为1代表进行借书操作,0代表还书操作
                pstmt = con.prepareStatement(sql);
            else
                pstmt = con.prepareStatement(sql2);
            pstmt.setInt(1, stuid);
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(con, pstmt, rs);
        }
        return row;
    }
    // 修改用户权限
    public int updatePower(int stuid,int power) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBConnection.getDBConnection();
        String sql = "update user set power=? where stuid = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, power);
            pstmt.setInt(2, stuid);
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(con, pstmt, rs);
        }
        return row;
    }



    // 删除用户
    public int deleteUser(String stuId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBConnection.getDBConnection();
        String sql = "delete from user where stuid = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, stuId);
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(con, pstmt, rs);
        }
        return row;
    }

}
