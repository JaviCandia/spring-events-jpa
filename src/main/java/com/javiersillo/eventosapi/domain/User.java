package com.javiersillo.eventosapi.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {

    private Long id;

    private String name;

    private String username;

    private String email;

    private String password;

    // fetch eager: traeme todas las propiedades
    // cascade: como en sql, si haces algo como eliminar, también se hará en users_roles
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), // this table
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id") // related table
    )
    private Set<Role> roles = new HashSet<>();
}
