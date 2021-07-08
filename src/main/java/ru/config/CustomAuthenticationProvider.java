package ru.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    List<MyUserImpl> dummyUsers = new ArrayList<>();

    public CustomAuthenticationProvider() {
        dummyUsers.add(new MyUserImpl("admin", "{noop}admin", 1, "ROLE_ADMIN"));
        dummyUsers.add(new MyUserImpl("user", "{noop}user", 2, "ROLE_USER"));
    }
    @Override
    public Authentication authenticate(Authentication auth)
            throws AuthenticationException {


        String username = auth.getName();
        String password = auth.getCredentials()
                .toString();

        Optional<MyUserImpl> authenticatedUser = dummyUsers.stream().filter(
                user -> user.getUsername().equals(username)).findFirst();


        if (!authenticatedUser.isPresent()) {
            throw new BadCredentialsException("External system authentication failed bb");
        }

        return new UsernamePasswordAuthenticationToken(authenticatedUser.get(), password, authenticatedUser.get().getAuthorities());
    }

        @Override
        public boolean supports (Class < ? > auth){
            return auth.equals(UsernamePasswordAuthenticationToken.class);
        }


    }