package com.example.tharanignanasegaram_project2;

import java.io.Serializable;

public class MenuCart implements Serializable {

    private String menuName;
    private double price;
    private String menuImage;
    private int countItem;

    public MenuCart(){

    }

    public MenuCart(String menuName, double price, String menuImage, int countItem) {
        this.menuName = menuName;
        this.price = price;
        this.menuImage = menuImage;
        this.countItem = countItem;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(String menuImage) {
        this.menuImage = menuImage;
    }

    public int getCountItem() {
        return countItem;
    }

    public void setCountItem(int countItem) {
        this.countItem = countItem;
    }
}
