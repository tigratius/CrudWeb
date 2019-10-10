
package com.tigratius.crudweb.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project extends NamedEntity {

    private double budget;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "teamsprojects",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "team_id")})
    private Set<Team> teams;

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }
}

