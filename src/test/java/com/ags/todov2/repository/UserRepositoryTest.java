package com.ags.todov2.repository;

import com.ags.todov2.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAddUser(){
        User user = new User()
                .setUsername("d")
                .setPassword("d")
                .setName("John")
                .setSurname("Doe")
                .setProfilPictureUuid("c4430666-639e-4cd7-bdba-59ba349f2eba");
        user.setId(UUID.randomUUID().toString());

        userRepository.save(user);
    }


}
