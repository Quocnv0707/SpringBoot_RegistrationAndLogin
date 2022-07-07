package com.nvq.RegistrationAndLogin.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nvq.RegistrationAndLogin.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository repo;

    @Test
    public void testCreateUser(){
        User user = new User();
        user.setEmail("test@123.com");
        user.setFirstName("Julia");
        user.setLastName("Albedo");
        user.setPassword("123456");

        User saveUser = repo.save(user);

        User existUser = entityManager.find(User.class, saveUser.getId());

        //assert (user.getEmail()).equals(existUser.getEmail());
        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
    }

    @Test
    public void testFindByEmail(){
        String email = "test@1232.com";
        User user = repo.findByEmail(email);
        assertThat(user).isNotNull();
    }
}
