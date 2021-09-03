package com.example.web_task.Repository;

import com.example.web_task.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege,Long> {
    Privilege findByName(String name);

    @Override
    void delete(Privilege privilege);
}
