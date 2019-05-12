package com.example.demo.Repositorys;

import com.example.demo.pojo.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;




@RestController
public interface FoodsRepository extends JpaRepository<Food,Long> {


}
