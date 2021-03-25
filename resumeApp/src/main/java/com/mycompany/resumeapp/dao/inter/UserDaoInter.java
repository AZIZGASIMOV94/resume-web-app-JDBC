package com.mycompany.resumeapp.dao.inter;

import com.mycompany.bean.User;
import com.mycompany.bean.UserSkill;
import java.util.List;

/**
 *
 * @author azizg
 */
public interface UserDaoInter {
    //get all users
    public List<User> getAllUsers();
    //get one user
    public User getById(int id);
    //update user
    public boolean updateUser(User u);
    //insert user
    public boolean addUser(User u);
    //delete user
    public boolean deleteUser(int id);
    //get all skill by user id
    //public List<UserSkill> getAllSkillByUserId(int userId);

}
