package com.mybike.data.entities;

import com.mybike.data.entities.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @ManyToMany
    private Set<Role> roles;

    @OneToMany(targetEntity = Enduro.class, mappedBy = "owner")
    private Set<Enduro> enduroBikes;

    @Column
    @Transient
    private boolean isAccountNonExpired;

    @Column
    @Transient
    private boolean isAccountNonLocked;

    @Column
    @Transient
    private boolean isCredentialsNonExpired;

    @Column
    @Transient
    private boolean isEnabled;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> authorities;

    public User() {
        authorities = new HashSet<>();
    }

}
