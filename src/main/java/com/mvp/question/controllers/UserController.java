package com.mvp.question.controllers;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mvp.question.models.User;
import com.mvp.question.payload.request.EditUserRequest;
import com.mvp.question.security.service.UserDetailsImpl;
import com.mvp.question.services.UserService;

import io.swagger.annotations.ApiOperation;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "edit user profile")
    @PatchMapping("")
    public ResponseEntity<?> edit(@Valid @RequestBody EditUserRequest request) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        User result = userService.Edit(userDetails.getId(),request);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "user profile")
    @GetMapping("/profile")
    public ResponseEntity<?> get() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        User result = userService.get(userDetails.getId());
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "get all users only 'ADMIN' role")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(userService.search());
    }

}
