package com.example.drrrtesting.main;

import com.example.drrrtesting.room.objects.User;

import java.util.List;

public interface MainActView {
    void updateAdapter(List<User> users);
    void clearEdts();
    void showError(String error);
    void showSuccess();
}
