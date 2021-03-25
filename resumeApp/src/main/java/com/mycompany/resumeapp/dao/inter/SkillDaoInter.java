package com.mycompany.resumeapp.dao.inter;

import com.mycompany.bean.Skill;
import com.mycompany.bean.User;
import com.mycompany.bean.UserSkill;
import java.util.List;

/**
 *
 * @author azizg
 */
public interface SkillDaoInter {
    //get all skill
    public List<Skill> getAllSkills();
    //update skill
    public boolean updateSkill(Skill l);
    //insert skill
    public boolean addSkill(Skill u);
    //delete skill
    public boolean deleteSkill(int id);
}
