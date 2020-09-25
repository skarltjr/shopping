package com.example.shopping.repository;

import com.example.shopping.domain.item.Item;
import com.example.shopping.dto.ItemDto;
import com.example.shopping.dto.ItemSearchCond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemRepositoryCustom {

    //동적쿼리로 이름(eq) 가격(얼마이상) 재고(몇개 이상)
    Page<ItemDto> searchItem(ItemSearchCond cond, Pageable pageable);
    List<ItemDto> searhAll();
}
