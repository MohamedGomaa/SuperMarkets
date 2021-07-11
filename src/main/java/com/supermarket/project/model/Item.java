package com.supermarket.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @NotNull
    @Pattern(regexp = "/^[A-Za-z]+([\\ A-Za-z]+)*/")
    private String itemEnglishName;

    @NotNull
    @Pattern(regexp = "^[\\u0621-\\u064A\\u0660-\\u0669 ]+$")
    private String itemArabicName;

    @NotNull
    @Pattern(regexp = "[0-9]+([,.][0-9]{1,2})?")
    private float itemPrice;

    @ManyToOne
    @JoinColumn(name = "superMarketId")
    private SuperMarket theMarket;

    public Item(){}

    public Item(String itemEnglishName, String itemArabicName, float itemPrice, SuperMarket theMarket) {
        this.itemEnglishName = itemEnglishName;
        this.itemArabicName = itemArabicName;
        this.itemPrice = itemPrice;
        this.theMarket = theMarket;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemEnglishName() {
        return itemEnglishName;
    }

    public void setItemEnglishName(String itemEnglishName) {
        this.itemEnglishName = itemEnglishName;
    }

    public String getItemArabicName() {
        return itemArabicName;
    }

    public void setItemArabicName(String itemArabicName) {
        this.itemArabicName = itemArabicName;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }


    public SuperMarket getTheMarket() {
        return theMarket;
    }

    public void setTheMarket(SuperMarket theMarket) {
        this.theMarket = theMarket;
    }
}
