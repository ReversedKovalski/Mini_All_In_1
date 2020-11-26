package com.example.drrrtesting.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.drrrtesting.room.dao.DaoUsers;
import com.example.drrrtesting.room.objects.User;

@Database(entities = {User.class}, version = 2, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    public abstract DaoUsers daoUsers();
}
