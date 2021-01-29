package com.mycompany.resumeapp.dao.inter;

import com.mycompany.bean.User;
import java.util.List;

/**
 *
 * @author azizg
 */
public interface UserDaoInter {

    //get all users
    public List<User> getAll();
    //get one user
    public User getById(int id);
    //update user
    public boolean updateUser(User u);
    //delete user
    public boolean deleteUser(int id);
}
