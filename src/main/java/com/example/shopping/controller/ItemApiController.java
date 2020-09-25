package com.example.shopping.controller;

import com.example.shopping.domain.item.Book;
import com.example.shopping.domain.item.Item;
import com.example.shopping.dto.ItemDto;
import com.example.shopping.dto.ItemSearchCond;
import com.example.shopping.dto.request.CreateBookRequest;
import com.example.shopping.dto.request.CreateBookResponse;
import com.example.shopping.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ItemApiController {
    private final ItemRepository itemRepository;

    @GetMapping("/items")
    public List<ItemDto> items( )
    {
        return itemRepository.searhAll();
    }

    //문제

    //아이템 cond 동적쿼리찾기
    @GetMapping("/itemfind")
    public Page<ItemDto> findItem(ItemSearchCond cond, Pageable pageable)
    {
        return itemRepository.searchItem(cond,pageable);
    }



    //아이템 생성 request response
    @PostMapping("/createItem")
    public CreateBookResponse createItem(@RequestBody @Valid CreateBookRequest request) {
        Book book = new Book(request.getItemName(), request.getPrice(), request.getStockQuantity(),
                request.getAuthor());
        Book save = itemRepository.save(book);
        return new CreateBookResponse(save.getId(),save.getName());

    }
}
