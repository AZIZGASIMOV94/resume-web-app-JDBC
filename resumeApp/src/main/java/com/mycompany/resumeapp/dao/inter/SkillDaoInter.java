package com.mycompany.resumeapp.dao.inter;

import com.mycompany.bean.Skill;
import java.util.List;

public interface SkillDaoInter {
    //get all skill
    public List<Skill> getAllSkills();
    //update skill
    public boolean updateSkill(Skill s);
    //insert skill
    public boolean addSkill(Skill s);
    //delete skill
    public boolean deleteSkill(int id);
}
