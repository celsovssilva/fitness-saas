package com.example.fitness_saas.service;

import com.example.fitness_saas.Enum.UserRole;
import com.example.fitness_saas.entity.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
@Service
@AllArgsConstructor
public class UserDetailsIMPL implements UserDetails{
    private Long id;
    private String email;
    private String name;
    private String username;
    private String password;

    public UserDetailsIMPL(Long id, String name, String password, String email, String sexo, LocalDate dataNascimento, UserRole role, String name1) {
    }

    public  static  UserDetailsIMPL buildUserDetailsIMPL(User user){
        return new UserDetailsIMPL(user.getId(),
                user.getName(),
                user.getPassword(),
                user.getEmail(),
                user.getSexo(),
                user.getDataNascimento(),
                user.getRole(),
                user.getName());
    }

    private Collection<? extends GrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }
}
