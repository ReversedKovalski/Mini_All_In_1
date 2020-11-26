package com.example.drrrtesting.main;

import com.example.drrrtesting.room.dao.DaoUsers;
import com.example.drrrtesting.room.objects.User;

import java.util.List;
import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActPresenter {
    private MainActivity view;
    private DaoUsers mDaoUsers;
    private CompositeDisposable cd;

    @Inject
    public MainActPresenter(DaoUsers daoUsers) {
        this.mDaoUsers = daoUsers;
        initWidgets();
    }

    private void initWidgets() {
        cd = new CompositeDisposable();
    }

    protected void attached(MainActivity mainActivity){
        view = mainActivity;
    }

    protected void detached(){
        view = null;
    }

    protected void addUser(String name, String email, String password) {
        Disposable d = mDaoUsers.addUser(new User(name, email, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.clearEdts();
                        getAllUsers();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.makeToast(e.getMessage());
                    }
                });
        cd.add(d);
    }

    protected void deleteUserByName(String name) {
        Disposable d = mDaoUsers.deleteUserByName(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.clearEdts();
                        getAllUsers();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.makeToast(e.getMessage());
                    }
                });
        cd.add(d);
    }

    protected void getAllUsers() {
        Disposable d = mDaoUsers.getAllUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<User>>() {
                    @Override
                    public void onSuccess(@NonNull List<User> users) {
                        view.adapter.updateUsers(users);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.makeToast(e.getMessage());
                    }
                });
        cd.add(d);
    }

    protected void disposeAll() {
        cd.dispose();
    }
}
