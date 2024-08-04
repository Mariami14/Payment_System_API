package com.marie.paymentsystem.service;


import com.marie.paymentsystem.model.model.AppUser;
import com.marie.paymentsystem.model.repository.AppUserRepository;
import com.marie.paymentsystem.model.repository.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {
    @Autowired
    private AppUserRepository appUserRepository; // to find users by email , from database

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(email);

        if (appUser != null){
            var authorities = appUser.getUserRoles() == UserRoles.ADMIN
                    ? new SimpleGrantedAuthority("ROLE_ADMIN")
                    : new SimpleGrantedAuthority("ROLE_CUSTOMER");

            return User.withUsername(appUser.getEmail())
                    .password(appUser.getPassword())
                    .authorities(authorities)
                    .build();
        }
        throw new UsernameNotFoundException("User not found");

    }
}
