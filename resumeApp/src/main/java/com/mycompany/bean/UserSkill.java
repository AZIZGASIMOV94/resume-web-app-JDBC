/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bean;

/**
 *
 * @author azizg
 */
public class UserSkill {
    private int id;
    private User user;
    private Skill skill;
    private int skillLevel;

    public UserSkill() {
    }

    public UserSkill(int id, User user, Skill skill, int skillLevel) {
        this.id = id;
        this.user = user;
        this.skill = skill;
        this.skillLevel = skillLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    @Override
    public String toString() {
        return "UserSkill{" + "id=" + id + ", user=" + user + ", skill=" + skill + ", skillLevel=" + skillLevel + '}';
    }
}
