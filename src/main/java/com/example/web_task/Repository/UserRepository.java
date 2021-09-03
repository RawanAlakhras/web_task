package com.example.web_task.Repository;

import com.example.web_task.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<ApplicationUser,Long> {
    ApplicationUser findByEmail(String email);

    @Override
    void delete(ApplicationUser user);

}
