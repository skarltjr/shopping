package com.example.shopping.repository;

import com.example.shopping.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long>,ItemRepositoryCustom {
    @Query("select i from Item i")
    List<Item> items2();
}
