package com.example.lab4_sql_fx.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Player {
    private SimpleIntegerProperty player_id; // auto_increment
    private SimpleStringProperty first_name; //not null
    private SimpleStringProperty last_name; //not null
    private SimpleStringProperty address;
    private SimpleStringProperty postal_code;
    private SimpleStringProperty province;
    private SimpleStringProperty phone_number;


    public Player(int player_id, String first_name, String last_name, String address, String postal_code, String province, String phone_number) {
        if (player_id <= 0) throw new IllegalArgumentException("player_id must be positive");
        if (first_name== null || first_name.trim().isEmpty()) throw new IllegalArgumentException("first_name cannot be null or empty");
        if (last_name== null || last_name.trim().isEmpty()) throw new IllegalArgumentException("last_name cannot be null or empty");
        this.player_id = new SimpleIntegerProperty(player_id);
        this.first_name = new SimpleStringProperty(first_name);
        this.last_name = new SimpleStringProperty(last_name);
        this.address = new SimpleStringProperty(address);
        this.postal_code = new SimpleStringProperty(postal_code);
        this.province = new SimpleStringProperty(province);
        this.phone_number = new SimpleStringProperty(phone_number);
    }


    public int getPlayer_id() {
        return player_id.get();
    }

    public SimpleIntegerProperty player_idProperty() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id.set(player_id);
    }

    public String getFirst_name() {
        return first_name.get();
    }

    public SimpleStringProperty first_nameProperty() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name.set(first_name);
    }

    public String getLast_name() {
        return last_name.get();
    }

    public SimpleStringProperty last_nameProperty() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name.set(last_name);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getPostal_code() {
        return postal_code.get();
    }

    public SimpleStringProperty postal_codeProperty() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code.set(postal_code);
    }

    public String getProvince() {
        return province.get();
    }

    public SimpleStringProperty provinceProperty() {
        return province;
    }

    public void setProvince(String province) {
        this.province.set(province);
    }

    public String getPhone_number() {
        return phone_number.get();
    }

    public SimpleStringProperty phone_numberProperty() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number.set(phone_number);
    }
}
