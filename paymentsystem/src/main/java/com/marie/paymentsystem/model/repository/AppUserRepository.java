package com.marie.paymentsystem.model.repository;

import com.marie.paymentsystem.model.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository <AppUser, Long>{

     AppUser findByEmail (String email);
}
