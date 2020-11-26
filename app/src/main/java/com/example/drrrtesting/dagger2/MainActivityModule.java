package com.example.drrrtesting.dagger2;


import com.example.drrrtesting.main.MainActPresenterImpl;
import com.example.drrrtesting.room.dao.DaoUsers;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    MainActPresenterImpl provideMAPresenter(DaoUsers daoUsers){
        return new MainActPresenterImpl(daoUsers);
    }
}
