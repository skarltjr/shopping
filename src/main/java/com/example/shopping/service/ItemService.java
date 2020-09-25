package com.example.shopping.service;

import com.example.shopping.domain.item.Item;
import com.example.shopping.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ItemService {
    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    public void save(Item item) {
        itemRepository.save(item);
    }

    //전체 엔티티조회
    public List<Item> findAll()
    {
        return itemRepository.findAll();
    }

    //단건
    public Item findOne(Long id) {
        return itemRepository.findById(id).get();
    }
}
