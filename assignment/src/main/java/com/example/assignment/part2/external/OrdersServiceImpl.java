package com.example.assignment.part2.external;

import com.example.assignment.part2.entity.Item;
import com.example.assignment.part2.repository.ItemRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private ItemRepository itemRepository;

    @PersistenceContext
    private EntityManager entityManager;
//    @Override
//    public List<Item> itemsBought(String username) {
//        // Remove the username condition for testing purposes
//        String sql = "SELECT i.* FROM ITEM i INNER JOIN USERS u ON i.userid = u.id AND u.username = '"+username+"'";
//        return entityManager.createNativeQuery(sql, Item.class)
//                .getResultList();
//    }

    @Override
    public List<Item> itemsBought(String username) {
        return itemRepository.findByUserUsername(username);
    }
}
