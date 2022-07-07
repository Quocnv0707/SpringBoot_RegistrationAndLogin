package com.nvq.RegistrationAndLogin.Repository;

import com.nvq.RegistrationAndLogin.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u where u.email = ?1")
    User findByEmail(String userName);
}
