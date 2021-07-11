package com.supermarket.project.model;

import javax.persistence.Entity;

@Entity
public class SupermarketItems {



    private SuperMarket theMarket;

    private Item theItem;

    private boolean available;
}
