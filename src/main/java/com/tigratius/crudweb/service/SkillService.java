package com.tigratius.crudweb.service;

import com.tigratius.crudweb.entity.Skill;
import com.tigratius.crudweb.repository.SkillRepository;

import java.util.List;

public class SkillService {

    private SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Skill getById(Long id) {
        try {
            return skillRepository.getById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public List<Skill> getAll() {
        return skillRepository.getAll();
    }

    public void delete(Long id) {
        skillRepository.delete(id);
    }

    public void create(String name) {
     /*   Skill skill = new Skill();
        skill.setName(name);
        skillRepository.add(skill);*/

        Skill skill = getNewSkill(name);
        skillRepository.add(skill);
    }

    public void update(Long id, String name) {
        /*Skill skill = new Skill();
        skill.setId(id);
        skill.setName(name);
        skillRepository.update(skill);*/

        Skill skill = getNewSkill(name);
        skill.setId(id);
        skillRepository.update(skill);
    }

    private Skill getNewSkill(String name) {
        Skill skill = new Skill();
        skill.setName(name);
        return skill;
    }
}
