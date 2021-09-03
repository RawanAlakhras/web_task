package com.example.web_task.Servic;

import com.example.web_task.Repository.PrivilegeRepository;
import com.example.web_task.Repository.RoleRepository;
import com.example.web_task.Repository.UserRepository;
import com.example.web_task.model.ApplicationUser;
import com.example.web_task.model.Privilege;
import com.example.web_task.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PrivilegeRepository privilegeRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        // == create initial privileges
        final Privilege addPrivilege = createPrivilegeIfNotFound("ADD_PRIVILEGE");
        final Privilege deletePrivilege = createPrivilegeIfNotFound("DELETE_PRIVILEGE");

        // == create initial roles
        final List<Privilege> adminPrivileges = new ArrayList<>(Arrays.asList(addPrivilege, deletePrivilege));
        final List<Privilege> userPrivileges = new ArrayList<>(Arrays.asList(addPrivilege));
        final Role adminRole = createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        final Role userRole =createRoleIfNotFound("ROLE_USER", userPrivileges);

        // == create initial user
        ApplicationUser adminUser=createUserIfNotFound("admin@test.com",bCryptPasswordEncoder.encode("123"), "Admin", "Admin", new ArrayList<>(Arrays.asList(adminRole)));
        ApplicationUser normalUser=createUserIfNotFound("user@test.com",bCryptPasswordEncoder.encode("123"), "Andy", "Bicheal", new ArrayList<>(Arrays.asList(userRole)));
        alreadySetup = true;
    }
    @Transactional
    Privilege createPrivilegeIfNotFound(final String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilege = privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(final String name, final Collection<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
        }
        role.setPrivileges(privileges);
        role = roleRepository.save(role);
        return role;
    }
    @Transactional
    ApplicationUser createUserIfNotFound(final String email,final String password, final String firstName, final String lastName,  final Collection<Role> roles) {
        ApplicationUser user = userRepository.findByEmail(email);
        if (user == null) {
            user = new ApplicationUser();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setEmail(email);
            user.setEnabled(true);

        }
        user.setRoles(roles);
        user = userRepository.save(user);
//        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        return user;
    }

}
