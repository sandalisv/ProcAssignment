package com.example.assignment.part2.repository;

import com.example.assignment.part2.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAll();

    //    @Query("SELECT i FROM item i JOIN i.users u WHERE u.username = :username")
//    List<Item> findByUserUsername(@Param("username") String username);
    List<Item> findByUserUsername(String username);

//    @Query(value = "SELECT i.* FROM ITEM i INNER JOIN USERS u ON i.userid = u.id AND u.username = :username", nativeQuery = true)
//    List<Item> findByUserUsername(@Param("username") String username);
}

