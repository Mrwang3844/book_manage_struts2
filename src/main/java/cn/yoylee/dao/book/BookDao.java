package cn.yoylee.dao.book;

import cn.yoylee.entity.Book;
import cn.yoylee.entity.User;
import cn.yoylee.util.DBConnection;
import com.opensymphony.xwork2.ActionContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static cn.yoylee.util.BookConstants.USER_SESSION;

public class BookDao {
    // 获得所有的图书信息
    public List<Book>  selectAllBook() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = DBConnection.getDBConnection();
        List<Book> books = new ArrayList<Book>();
        String sql = "select * from book";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            Book book;
            while (rs.next()) {
                book = new Book();
                book.setId(rs.getInt("bid"));
                book.setName(rs.getString("bname"));
                book.setPrice(rs.getDouble("bprice"));
                book.setStuid(rs.getInt("bstuid"));
                book.setComment(rs.getString("bcomment"));
                book.setAuthor(rs.getString("bauthor"));
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(con, pstmt, rs);
        }
        return books;
    }

    // 获得单个图书信息
    public Book  selectOneBook(int bid) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = DBConnection.getDBConnection();
        String sql = "select * from book where bid = ?";
        Book book = new Book();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,bid);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                book.setId(rs.getInt("bid"));
                book.setName(rs.getString("bname"));
                book.setAuthor(rs.getString("bauthor"));
                book.setPrice(rs.getDouble("bprice"));
                book.setComment(rs.getString("bcomment"));
                book.setStuid(rs.getInt("bstuid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(con, pstmt, rs);
        }
        return book;
    }


    // / 模糊查询书籍信息
    public List<Book>  selectBookLike(String name) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = DBConnection.getDBConnection();
        List<Book> books = new ArrayList<Book>();
        String sql = "select * from book where bname like '%" + name + "%'";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            Book book;
            while (rs.next()) {
                book = new Book();
                book.setId(rs.getInt("bid"));
                book.setName(rs.getString("bname"));
                book.setAuthor(rs.getString("bauthor"));
                book.setPrice(rs.getDouble("bprice"));
                book.setStuid(rs.getInt("bstuid"));
                book.setComment(rs.getString("bcomment"));
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(con, pstmt, rs);
        }
        return books;
    }


    //查询用户所借书籍
    public List<Book>  selectUserBook(int stuid) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = DBConnection.getDBConnection();
        List<Book> books = new ArrayList<Book>();
        String sql = "select * from book where bstuid = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,stuid);
            rs = pstmt.executeQuery();
            Book book;
            while (rs.next()) {
                book = new Book();
                book.setId(rs.getInt("bid"));
                book.setName(rs.getString("bname"));
                book.setAuthor(rs.getString("bauthor"));
                book.setPrice(rs.getDouble("bprice"));
                book.setComment(rs.getString("bcomment"));
                book.setStuid(rs.getInt("bstuid"));
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(con, pstmt, rs);
        }
        return books;
    }


    // 修改用户值(借书)
    public int updateBook(int bookId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBConnection.getDBConnection();
        String sql = "update book set bstuid=? where bid = ?";
        User user = (User)ActionContext.getContext().getSession().get(USER_SESSION);
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, user.getStuid());
            pstmt.setInt(2, bookId);
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(con, pstmt, rs);
        }
        return row;
    }

    // 修改用户值(还书)
    public int revertBook(int bookId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBConnection.getDBConnection();
        String sql = "update book set bstuid=? where bid = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, 0);
            pstmt.setInt(2, bookId);
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(con, pstmt, rs);
        }
        return row;
    }

    // 修改书籍信息
    public int updateBookInfo(Book book) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBConnection.getDBConnection();
        String sql = "update book set bname=?,bauthor=?,bprice=?,bcomment=? where bid = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, book.getName());
            pstmt.setString(2, book.getAuthor());
            pstmt.setDouble(3, book.getPrice());
            pstmt.setString(4, book.getComment());
            pstmt.setInt(5, book.getId());
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(con, pstmt, rs);
        }
        return row;
    }

    // 删除书籍
    public int deleteBook(int bookId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBConnection.getDBConnection();
        String sql = "delete from book where bid = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, bookId);
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(con, pstmt, rs);
        }
        return row;
    }


    // 增加书籍信息
    public int insertBook(Book book) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBConnection.getDBConnection();
        String sql = "insert into book VALUES(?,?,?,?,?,?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, book.getId());
            pstmt.setString(2, book.getName());
            pstmt.setString(3, book.getAuthor());
            pstmt.setDouble(4, book.getPrice());
            pstmt.setString(5, book.getComment());
            pstmt.setString(6, null);
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(con, pstmt, rs);
        }
        return row;
    }

}
