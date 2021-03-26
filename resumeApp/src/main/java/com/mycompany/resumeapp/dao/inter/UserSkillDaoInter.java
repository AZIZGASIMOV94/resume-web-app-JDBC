package com.mycompany.resumeapp.dao.inter;

import com.mycompany.bean.User;
import com.mycompany.bean.UserSkill;
import java.util.List;

/**
 *
 * @author azizg
 */
public interface UserSkillDaoInter {
    //get all skill by user id
    public List<UserSkill> getAllSkillByUserId(int userId);
    
    public boolean deleteUserSkill(int id);
}
