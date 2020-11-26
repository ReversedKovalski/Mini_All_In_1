package com.example.drrrtesting.main;

import com.example.drrrtesting.room.dao.DaoUsers;
import com.example.drrrtesting.room.objects.User;

import java.lang.ref.WeakReference;
import java.util.List;
import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class MainActPresenterImpl implements MainActPresenter{
    private WeakReference<MainActView> view;
    private DaoUsers mDaoUsers;
    private CompositeDisposable cd;

    @Inject
    public MainActPresenterImpl(DaoUsers daoUsers) {
        this.mDaoUsers = daoUsers;
        initWidgets();
    }

    private void initWidgets() {
        cd = new CompositeDisposable();
    }

    protected void attached(MainActView incView){
        view = new WeakReference<>(incView);
    }

    protected void detached(){
        view = null;
    }

    @Override
    public void addUser(String name, String email, String password) {
        Disposable d = mDaoUsers.addUser(new User(name, email, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.get().clearEdts();
                        view.get().showSuccess();
                        //getAllUsers();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.get().showError(e.getMessage());
                    }
                });
        cd.add(d);
    }

    @Override
    public void deleteUserByName(String name) {
        Disposable d = mDaoUsers.deleteUserByName(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.get().clearEdts();
                        view.get().showSuccess();
                        //getAllUsers();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.get().showError(e.getMessage());
                    }
                });
        cd.add(d);
    }

    @Override
    public void getAllUsers() {
        Disposable d = mDaoUsers.getAllUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<List<User>>() {
                                   @Override
                                   public void onNext(List<User> users) {
                                       view.get().updateAdapter(users);
                                   }

                                   @Override
                                   public void onError(Throwable t) {
                                       view.get().showError(t.getMessage());
                                   }

                                   @Override
                                   public void onComplete() {
                                       view.get().showError("I'am complete!!1!!, что кстати странно");
                                   }
                               });
        cd.add(d);
    }

    protected void disposeAll() {
        cd.dispose();
    }
}
