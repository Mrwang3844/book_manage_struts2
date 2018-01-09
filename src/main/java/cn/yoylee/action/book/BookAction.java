package cn.yoylee.action.book;

import cn.yoylee.entity.Book;
import cn.yoylee.entity.User;
import cn.yoylee.service.book.BookService;
import cn.yoylee.service.user.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static cn.yoylee.util.BookConstants.USER_SESSION;

@ParentPackage(value = "struts-default")
@Namespace(value = "/book")
public class BookAction extends ActionSupport{


    Book book;
    int bookId;
    String name;
    BookService bookService = new BookService();
    UserService userService = new UserService();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    Map session =ActionContext.getContext().getSession();
    User user = (User)session.get(USER_SESSION);


    @Action(
            value = "/allBook",
            results = {
                    @Result(name = "user_success",location = "/book/search.jsp",type = "dispatcher"),
                    @Result(name = "manage_success",location = "/book/manage_search.jsp",type = "dispatcher"),
                    @Result(name = "fail",location = "/error.jsp",type = "dispatcher")
            }
    )
    public String getAllBook(){
        String forward = null;
        List<Book> books = bookService.getAllBook();
        if (books.size() == 0){
            forward = "fail";
        }else{
            request.setAttribute("books",books);
            if (user.getPower()==0)
                forward = "user_success";
            else
                forward = "manage_success";
        }
        return forward;
    }

    @Action(
            value = "/bookLike",
            results = {
                    @Result(name = "user_success",location = "/book/search.jsp",type = "dispatcher"),
                    @Result(name = "manage_success",location = "/book/manage_search.jsp",type = "dispatcher")
            }
    )
    public String getBookLike(){
        String forward = null;
        List<Book> books = bookService.getBookLike(name);
        request.setAttribute("books",books);
        if (user.getPower()==0)
            forward = "user_success";
        else
            forward = "manage_success";
        return forward;
    }

    @Action(
            value = "/oneBook",
            results = {
                    @Result(name = "success",location = "/book/manage_update.jsp",type = "dispatcher"),
                    @Result(name = "fail",location = "/error.jsp",type = "dispatcher")
            }
    )
    public String getOneBook(){
        String forward = null;
        Book book = bookService.getOneBook(bookId);
        request.setAttribute("book",book);
        if (book == null)
            forward = "fail";
        else
            forward = "success";
        return forward;
    }


    @Action(
            value = "/bookUser",
            results = {
                    @Result(name = "success",location = "/book/revert.jsp",type = "dispatcher"),
            }
    )
    public String getUserBook(){
        String forward = null;
        List<Book> books = bookService.getUserBook(user.getStuid());
        request.setAttribute("books",books);
        forward = "success";
        return forward;
    }

    @Action(value = "/borrow")
    public void borrow(){
        int  re = bookService.borrowBook(bookId);
        int re2 = userService.updateConut(user.getStuid(),1);
        User user1 = new User();
        user1 = user;
        user1.setCount(user.getCount()+1);
        session.put(USER_SESSION,user1);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/plain;charset=UTF-8");
        try {
            response.getWriter().print(re);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Action(value = "/revert")
    public void revert(){
        int  re = bookService.revertBook(bookId);
        int re2 = userService.updateConut(user.getStuid(),0);
        User user1 = new User();
        user1 = user;
        user1.setCount(user.getCount()-1);
        session.put(USER_SESSION,user1);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/plain;charset=UTF-8");
        try {
            response.getWriter().print(re);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Action(
            value = "/updateBook",
            results = {
                    @Result(name = "success",location = "allBook",type = "chain"),
                    @Result(name = "fail",location = "/error.jsp",type = "dispatcher"),
            }
    )
    public String updateBook(){
        String forward = null;
        int re = bookService.updateBookInfo(book);
        if (re == 1){
            forward="success";
        }else{
            forward = "fail";
        }
        return forward;
    }

    @Action(value = "/deleteBook")
    public void delete(){
        int  re = bookService.deleteBook(bookId);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/plain;charset=UTF-8");
        try {
            response.getWriter().print(re);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Action(
            value = "insertBook",
            results = {
                    @Result(name = "success",location = "/book/manage_add.jsp",type = "dispatcher"),
                    @Result(name = "fail",location = "/error.jsp",type = "dispatcher"),
            }
    )
    public String insertBook(){
        String forward = null;
        int re = bookService.insertBook(book);
        if (re == 1){
            forward="success";
        }else{
            forward = "fail";
        }
        return forward;
    }


    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
