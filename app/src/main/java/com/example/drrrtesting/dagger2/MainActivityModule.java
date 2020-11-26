package com.example.drrrtesting.dagger2;


import com.example.drrrtesting.main.MainActPresenter;
import com.example.drrrtesting.room.dao.DaoUsers;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    MainActPresenter provideMAPresenter(DaoUsers daoUsers){
        return new MainActPresenter(daoUsers);
    }
}
