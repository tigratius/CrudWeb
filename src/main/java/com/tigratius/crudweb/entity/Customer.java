package com.tigratius.crudweb.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer extends NamedEntity {

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Set<Project> projects;

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
