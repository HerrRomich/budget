package com.hrrm.budget.domain.account;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;

@Entity
@DiscriminatorValue("BASKET")
public class BasketEntry extends AccountEntry {

    @ElementCollection(targetClass = BasketItem.class)
    @CollectionTable(name = "basket_item", joinColumns = @JoinColumn(name = "basket_id"))
    @OrderColumn(name = "item_order")
    private List<BasketItem> items;
    
    @Column(name = "countpart_id")
    private Integer counterpartyId;
    
}
