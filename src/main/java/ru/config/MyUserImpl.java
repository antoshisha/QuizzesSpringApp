package ru.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MyUserImpl extends User {


    public MyUserImpl(String username, String password, Integer id, String...authorities) {
        super(username, password, AuthorityUtils.createAuthorityList(authorities));
        this.id = id;
        this.username = username;
    }

    private final String username;
    private Integer id;

    @Override
    public String getUsername() {
        return username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
