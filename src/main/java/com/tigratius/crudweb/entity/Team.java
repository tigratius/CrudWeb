package com.tigratius.crudweb.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team extends NamedEntity{

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    private Set<User> users;

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
