package com.mvp.question.services;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.mvp.question.models.Role;
import com.mvp.question.models.enums.ERole;
import com.mvp.question.repository.RoleRepo;


@Component
@RequiredArgsConstructor
public class DemoData {

    private final RoleRepo repo;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        Long roles = repo.count();
        if(roles < 2){ 
        repo.save(new Role(ERole.ADMIN));
        repo.save(new Role(ERole.USER));
        }
    }
}