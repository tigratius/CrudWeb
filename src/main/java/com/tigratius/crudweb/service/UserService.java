package com.tigratius.crudweb.service;

import com.tigratius.crudweb.entity.Skill;
import com.tigratius.crudweb.entity.User;
import com.tigratius.crudweb.repository.SkillRepository;
import com.tigratius.crudweb.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserService {

    private UserRepository userRepository;
    private SkillRepository skillRepository;

    public UserService(UserRepository userRepository, SkillRepository skillRepository) {

        this.userRepository = userRepository;
        this.skillRepository = skillRepository;

    }

    public User getById(Long id) {
        try {
            return userRepository.getById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public void delete(Long id) {
        userRepository.delete(id);
    }

    public void create(String firstName, String lastName, String specialty, List<String> skillIds){
        User user = getNewUser(firstName, lastName, specialty, skillIds);
        userRepository.add(user);
    }

    public void update(Long id, String firstName, String lastName, String specialty, List<String> skillIds){
        /*User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setSpecialty(specialty);
        Set<Skill> skills = new HashSet<>();
        for (String skillId: skillIds) {
            Skill skill = skillRepository.getById(Long.parseLong(skillId));
            skills.add(skill);
        }
        user.setSkills(skills);
        userRepository.update(user);*/

        User user = getNewUser(firstName, lastName, specialty, skillIds);
        user.setId(id);
        userRepository.update(user);
    }

    public List<Skill> getSkills()
    {
        return skillRepository.getAll();
    }

    private User getNewUser(String firstName, String lastName, String specialty, List<String> skillIds) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setSpecialty(specialty);
        Set<Skill> skills = new HashSet<>();
        for (String skillId: skillIds) {
            Skill skill = skillRepository.getById(Long.parseLong(skillId));
            skills.add(skill);
        }
        user.setSkills(skills);
        return user;
    }
}
