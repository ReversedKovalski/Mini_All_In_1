package com.example.drrrtesting.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.drrrtesting.room.objects.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface DaoUsers {

    @Query("select * from user")
    Flowable<List<User>> getAllUsers();

    @Query("select * from user where id = :userId")
    Single<User> getUserById(int userId);

    @Query("select * from user where email = :userEmail")
    Single<User> getUserByEmail(String userEmail);

    @Query("select * from user where email = :userEmail and password = :userPassword")
    Maybe<User> checkExistUser(String userEmail, String userPassword);

    @Insert
    Completable addUser(User user);

    @Update
    Completable updateUser(User user);

    @Query("delete from user where name = :name")
    Completable deleteUserByName(String name);
}
