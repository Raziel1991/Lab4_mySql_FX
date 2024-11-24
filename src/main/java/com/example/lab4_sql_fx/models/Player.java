package com.example.lab4_sql_fx.models;

public class Player {
    private int player_id; // auto_increment
    private String first_name; //not null
    private String last_name; //not null
    private String address;
    private String postal_code;
    private String province;
    private String phone_number;


    public Player(String first_name, String last_name) {
        if (first_name== null || first_name.trim().isEmpty()) throw new IllegalArgumentException("first_name cannot be null or empty");
        if (last_name== null || last_name.trim().isEmpty()) throw new IllegalArgumentException("last_name cannot be null or empty");
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Player(int player_id, String first_name, String last_name, String address, String postal_code, String province, String phone_number) {
        if (player_id <= 0) throw new IllegalArgumentException("player_id must be positive");
        if (first_name== null || first_name.trim().isEmpty()) throw new IllegalArgumentException("first_name cannot be null or empty");
        if (last_name== null || last_name.trim().isEmpty()) throw new IllegalArgumentException("last_name cannot be null or empty");
        this.player_id = player_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.postal_code = postal_code;
        this.province = province;
        this.phone_number = phone_number;
    }

    public Player(String first_name, String last_name, String address, String postal_code, String province, String phone_number) {
        if (first_name== null || first_name.trim().isEmpty()) throw new IllegalArgumentException("first_name cannot be null or empty");
        if (last_name== null || last_name.trim().isEmpty()) throw new IllegalArgumentException("last_name cannot be null or empty");
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.postal_code = postal_code;
        this.province = province;
        this.phone_number = phone_number;
    }


    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        if (player_id <= 0) throw new IllegalArgumentException("player_id must be positive");
        this.player_id = player_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        if (first_name== null || first_name.trim().isEmpty()) throw new IllegalArgumentException("first_name cannot be null or empty");
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        if (last_name== null || last_name.trim().isEmpty()) throw new IllegalArgumentException("last_name cannot be null or empty");
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

}
