package com.mvp.question.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.List;

import com.mvp.question.exceptions.BadRequestException;
import com.mvp.question.exceptions.DuplicatException;
import com.mvp.question.exceptions.NotFoundException;
import com.mvp.question.models.Role;
import com.mvp.question.models.User;
import com.mvp.question.models.enums.ERole;
import com.mvp.question.payload.request.EditUserRequest;
import com.mvp.question.payload.request.SignupRequest;
import com.mvp.question.repository.RoleRepo;
import com.mvp.question.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder encoder;

    public User create(SignupRequest signUpRequest) {
        if (userRepo.existsByUsername(signUpRequest.getUsername())) {
            throw new DuplicatException("Duplicate Username");
        }
        if (userRepo.existsByPhone(signUpRequest.getPhone())) {
            throw new DuplicatException("Duplicate Phone");
        }
        if (userRepo.existsByEmail(signUpRequest.getEmail())) {
            throw new DuplicatException("Duplicate Email");
        }
        // Create new user's account

        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getFullname(),
                signUpRequest.getEmail(),
                signUpRequest.getPhone());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setTalents(signUpRequest.getTalents());
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepo.findByName(ERole.USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "Admin":
                        Role adminRole = roleRepo.findByName(ERole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleRepo.findByName(ERole.USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);

        return userRepo.save(user);
    }

    public User get(Long user_id) throws UsernameNotFoundException {
        User user = userRepo.findById(user_id)
                .orElseThrow(() -> new NotFoundException("User Not Found with id" + user_id));
        return user;
    }

    public User getByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User Not Found with username" + username));
        return user;
    }

    public List<User> search() {
        return userRepo.findAll();
    }

    public User Edit(Long user_id, EditUserRequest request) {
        User user = this.get(user_id);
        if (request.getPassword() != null) {
            if (request.getConfirm_password() == null ||
                    !request.getPassword().equals(request.getConfirm_password())) {
                throw new BadRequestException("invalid confirm password");
            }
            user.setPassword(encoder.encode(request.getPassword()));
        }
        if (request.getEmail() != null) {
            Optional<User> us = userRepo.findByEmail(request.getEmail());
            if (us.isPresent() && !us.get().getEmail().equals(request.getEmail()))
                throw new DuplicatException("Email already exist");

            user.setEmail(request.getEmail());
        }
        if (request.getFullname() != null)
            user.setFullname(request.getFullname());
        if (request.getPhone() != null) {
            Optional<User> us = userRepo.findByPhone(request.getPhone());
            if (us.isPresent() && !us.get().getPhone().equals(request.getPhone()))
                throw new DuplicatException("phone already exist");

            user.setPhone(request.getPhone());
        }
        if (request.getUsername() != null) {
            Optional<User> us = userRepo.findByUsername(request.getUsername());
            if (us.isPresent() && !us.get().getUsername().equals(request.getUsername()))
                throw new DuplicatException("username already exist");
            else
                user.setUsername(request.getUsername());
        }
        return userRepo.save(user);
    }

    public String delete(Long id) {

        User user = this.get(id);
        try {
            userRepo.delete(user);
            return "successfully deleted";
        } catch (Exception e) {
            // TODO: handle exception
            return "cannot be deleted!";
        }
    }

}
