package com.example.springmvc.mvcLayer.domain.security;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
@ToString(exclude = "roles")
@EqualsAndHashCode(exclude = {"id", "roles"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя обязательно")
//    @Size(min = 1,max = 30)
    private String username;

//    @Size(min = 6,max = 80)
    @NotBlank(message = "Пароль обязательно")
    private String password;

    @NotBlank(message = "email обязательно")
    private String email;

    private boolean enabled = true;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;
}