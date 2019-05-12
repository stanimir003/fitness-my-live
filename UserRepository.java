package com.example.demo.Repositorys;

import com.example.demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByNameAndEmail (@Param("username") String u,@Param("email") String t);
    boolean existsByNameAndPassword (@Param("username") String u,@Param("password") String i);
}
