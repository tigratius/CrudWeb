package com.tigratius.crudweb.service;

import com.tigratius.crudweb.entity.Project;
import com.tigratius.crudweb.entity.Team;
import com.tigratius.crudweb.repository.ProjectRepository;
import com.tigratius.crudweb.repository.TeamRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProjectService {

    private ProjectRepository projectRepository;
    private TeamRepository teamRepository;

    public ProjectService(ProjectRepository projectRepository, TeamRepository teamRepository) {

        this.projectRepository = projectRepository;
        this.teamRepository = teamRepository;

    }

    public Project getById(Long id) {
        try {
            return projectRepository.getById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public List<Project> getAll() {
        return projectRepository.getAll();
    }

    public void delete(Long id) {
        projectRepository.delete(id);
    }

    public void create(String name, String budget, List<String> teamIds){
        /*Project project = new Project();
        project.setName(name);
        project.setBudget(Double.parseDouble(budget));
        Set<Team> teams = new HashSet<>();
        for (String teamId : teamIds) {
            Team team = teamRepository.getById(Long.parseLong(teamId));
            teams.add(team);
        }
        project.setTeams(teams);
        projectRepository.add(project);*/

        Project project = getNewProject(name, budget, teamIds);
        projectRepository.add(project);
    }

    public void update(Long id, String name, String budget, List<String> teamIds){
        /*Project project = new Project();
        project.setId(id);
        project.setName(name);
        project.setBudget(Double.parseDouble(budget));
        Set<Team> teams = new HashSet<>();
        for (String teamId : teamIds) {
            Team team = teamRepository.getById(Long.parseLong(teamId));
            teams.add(team);
        }
        project.setTeams(teams);
        projectRepository.update(project);*/

        Project project = getNewProject(name, budget, teamIds);
        project.setId(id);
        projectRepository.update(project);
    }

    public List<Team> getTeams() {
        return teamRepository.getAll();
    }

    private Project getNewProject(String name, String budget, List<String> teamIds)
    {
        Project project = new Project();
        project.setName(name);
        project.setBudget(Double.parseDouble(budget));
        Set<Team> teams = new HashSet<>();
        for (String teamId : teamIds) {
            Team team = teamRepository.getById(Long.parseLong(teamId));
            teams.add(team);
        }
        project.setTeams(teams);
        return project;
    }
}
