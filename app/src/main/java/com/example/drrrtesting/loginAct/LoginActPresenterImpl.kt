package com.example.drrrtesting.loginAct

import com.example.drrrtesting.R
import com.example.drrrtesting.room.dao.DaoUsers
import com.example.drrrtesting.room.objects.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference
import javax.inject.Inject

class LoginActPresenterImpl @Inject constructor(daoUsers: DaoUsers, loginView: LoginActView) : LoginActPresenter{
    private val mDaoUsers = daoUsers
    private val cd: CompositeDisposable = CompositeDisposable()
    private val view: LoginActView = loginView

    override fun login(email: String, password: String) {
        val d: Disposable = mDaoUsers.checkExistUser(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableMaybeObserver<User>(){
                    override fun onSuccess(t: User) {
                        view.showSuccess()
                        view.startNewAct()
                    }

                    override fun onError(e: Throwable) {
                        view.showError(R.string.ERROR_CREATING_ACCOUNT)
                    }

                    override fun onComplete() {
                        view.showError(R.string.WRONG_FIELDS)
                    }

                })
        cd.add(d)
    }

    override fun createAccount(name: String, email: String, password: String) {
        val d: Disposable = mDaoUsers.addUser(User(name, email, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableCompletableObserver(){
                    override fun onComplete() {
                        view.showSuccess()
                        view.updateUItoLogin()
                    }

                    override fun onError(e: Throwable) {
                        view.showError(R.string.ERROR_CREATING_ACCOUNT)
                    }
                })
        cd.add(d)
    }

    override fun activityDead() {
        cd.dispose()
    }
}