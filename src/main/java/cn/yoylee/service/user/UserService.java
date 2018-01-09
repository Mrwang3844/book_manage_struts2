package cn.yoylee.service.user;

import cn.yoylee.dao.user.UserDao;
import cn.yoylee.entity.User;
import com.opensymphony.xwork2.ActionContext;

import java.util.List;

import static cn.yoylee.util.BookConstants.USER_SESSION;

public class UserService {

    UserDao userDao = new UserDao();

    public User userLogin(User user){
        return userDao.login(user);
    }

    public List<User> getAllUser(){
        return userDao.selectAllUser();
    }

    public User getOneUser(String  stuid){
        return userDao.selectOneUser(stuid);
    }

    public List<User> getUserLike(String stuid){
        return userDao.selectUserLike(stuid);
    }

    public int userRegister(User user){
        return userDao.register(user);
    }

    public int updatePassword(String pass){
        User user = (User) ActionContext.getContext().getSession().get(USER_SESSION);
        return userDao.updatePassword(user.getStuid(),pass);
    }
    public int updatePower(int stuid,int power){
        return userDao.updatePower(stuid,power);
    }

    public int updateConut(int stuId,int num){
        return userDao.updateCount(stuId,num);
    }

    public int deleteUser(String stuId){
        return userDao.deleteUser(stuId);
    }
}
