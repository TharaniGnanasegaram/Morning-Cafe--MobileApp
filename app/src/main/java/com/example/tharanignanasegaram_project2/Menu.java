package com.example.tharanignanasegaram_project2;

import java.io.Serializable;

public class Menu implements Serializable {
    private String menuId;
    private String menuName;
    private String menuDescription;
    private double price;
    private String menuImage;

    public Menu(){

    }

    public Menu(String menuName, String menuDescription, double price, String menuImage) {
//        this.menuId = menuId;
        this.menuName = menuName;
        this.menuDescription = menuDescription;
        this.price = price;
        this.menuImage = menuImage;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
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
}
