package com.example.web_task.Repository;

import com.example.web_task.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);

    @Override
    void delete(Role role);
}
