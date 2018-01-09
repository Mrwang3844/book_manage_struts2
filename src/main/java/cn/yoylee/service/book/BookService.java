package cn.yoylee.service.book;

import cn.yoylee.dao.book.BookDao;
import cn.yoylee.entity.Book;

import java.util.List;

public class BookService {


    BookDao bookDao = new BookDao();

    public List<Book> getAllBook(){
        return bookDao.selectAllBook();
    }

    public Book getOneBook(int bid){
        return bookDao.selectOneBook(bid);
    }

    public List<Book> getBookLike(String name){
        return bookDao.selectBookLike(name);
    }

    public List<Book> getUserBook(int stuid){
        return bookDao.selectUserBook(stuid);
    }

    public int borrowBook(int bookId){
        return bookDao.updateBook(bookId);
    }

    public int revertBook(int bookId){
        return bookDao.revertBook(bookId);
    }

    public int updateBookInfo(Book book){
        return bookDao.updateBookInfo(book);
    }

    public int deleteBook(int bookId){
        return bookDao.deleteBook(bookId);
    }

    public int insertBook(Book book){
        return bookDao.insertBook(book);
    }
}
