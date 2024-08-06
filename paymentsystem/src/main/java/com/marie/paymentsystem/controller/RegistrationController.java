package com.marie.paymentsystem.controller;

import com.marie.paymentsystem.DTO.RegistrationDTO;
import com.marie.paymentsystem.model.model.AppUser;
import com.marie.paymentsystem.model.repository.AppUserRepository;
import com.marie.paymentsystem.model.repository.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class RegistrationController {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //TODO logout ღილაკზე ბაგია და გასასწორებელია , მაინც ჩანს

    @GetMapping("/register")
    public String register(Model model) {
        RegistrationDTO registrationDTO = new RegistrationDTO();
        model.addAttribute("registrationDTO", registrationDTO);
        model.addAttribute("success", false);
        return "register";
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @ModelAttribute("registrationDTO") RegistrationDTO registrationDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        AppUser existingUser = appUserRepository.findByEmail(registrationDTO.getEmail());
        if (existingUser != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
            return ResponseEntity.badRequest().body("There is already an account registered with that email");
        }

        try {

            var bCryptEncoder = new BCryptPasswordEncoder();

            AppUser appUser = new AppUser();
            appUser.setFirstName(registrationDTO.getName());
            appUser.setlastName(registrationDTO.getlastName());
            appUser.setEmail(registrationDTO.getEmail());
            appUser.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
            appUser.setCreatedAt(new Date());
            appUser.setUserRoles(UserRoles.CUSTOMER);

            // Determines user role based on email domain
            if (registrationDTO.getEmail().endsWith("@sky.inc")) {
                appUser.setUserRoles(UserRoles.ADMIN);
            } else {
                appUser.setUserRoles(UserRoles.CUSTOMER);
            }

            appUserRepository.save(appUser);

            //to clear registration form add new DTO object
            model.addAttribute("registrationDTO", new RegistrationDTO());
            //to display success message
            model.addAttribute("success", true);

            return ResponseEntity.ok("User registered successfully");

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }


    }


}
