package com.example.drrrtesting.main;

public interface MainActPresenter {
    void addUser(String name, String email, String password);
    void deleteUserByName(String name);
    void getAllUsers();
}
