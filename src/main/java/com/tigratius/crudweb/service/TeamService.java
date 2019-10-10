package com.tigratius.crudweb.service;

import com.tigratius.crudweb.entity.Team;
import com.tigratius.crudweb.entity.User;
import com.tigratius.crudweb.repository.TeamRepository;
import com.tigratius.crudweb.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TeamService {

    private TeamRepository teamRepository;
    private UserRepository userRepository;

    public TeamService(TeamRepository teamRepository, UserRepository userRepository) {

        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    public Team getById(Long id) {
        try {
            return teamRepository.getById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public List<Team> getAll() {
        return teamRepository.getAll();
    }

    public void delete(Long id) {
        teamRepository.delete(id);
    }

    public void create(String name, List<String> userIds){
        Team team = getNewTeam(name, userIds);
        teamRepository.add(team);
    }

    public void update(Long id, String name,  List<String> userIds) {
       /* Team team = new Team();
        team.setId(id);
        team.setName(name);
        Set<User> users = new HashSet<>();
        for (String userId : userIds) {
            User user = userRepository.getById(Long.parseLong(userId));
            users.add(user);
        }
        team.setUsers(users);
        teamRepository.update(team);*/

        Team team = getNewTeam(name, userIds);
        team.setId(id);
        teamRepository.update(team);
    }

    public List<User> getUsers() {
        return userRepository.getAll();
    }

    private Team getNewTeam(String name, List<String> userIds) {
        Team team = new Team();
        team.setName(name);
        Set<User> users = new HashSet<>();
        for (String userId : userIds) {
            User user = userRepository.getById(Long.parseLong(userId));
            users.add(user);
        }
        team.setUsers(users);
        return team;
    }
}
