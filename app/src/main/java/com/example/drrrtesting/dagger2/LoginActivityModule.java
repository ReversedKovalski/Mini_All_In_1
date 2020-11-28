package com.example.drrrtesting.dagger2;


import com.example.drrrtesting.loginAct.LoginActPresenter;
import com.example.drrrtesting.loginAct.LoginActPresenterImpl;
import com.example.drrrtesting.loginAct.LoginActView;
import com.example.drrrtesting.loginAct.LoginActivity;
import com.example.drrrtesting.room.dao.DaoUsers;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class LoginActivityModule {

    @Provides
    static LoginActPresenter provideLoginActPres(DaoUsers daoUsers, LoginActView view){
        return new LoginActPresenterImpl(daoUsers, view);
    }

    @Binds
    abstract LoginActView bindLogActView(LoginActivity loginActivity);
}
