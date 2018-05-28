package com.example.rishabh.roomexample;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rishabh on 5/25/18.
 */

public class DatabaseManager {
    private static final String DB_NAME = "database-name";
    private Context context;
    private static DatabaseManager _instance;
    private AppDatabase db;

    public static DatabaseManager getInstance(Context context) {
        if (_instance == null) {
            _instance = new DatabaseManager(context);
        }
        return _instance;
    }


    public DatabaseManager(Context context) {
        this.context = context;
        db = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }

    public Flowable<List<User>> getUsers() {
        return db.userDao().getAll();
    }

    public Completable addUser(String firstName , String lastName){
        final User user = new User(firstName,lastName);
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.userDao().insert(user);
            }
        });
    }

    public Completable deleteUser(final User user){
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.userDao().delete(user);
            }
        });
    }

 /*  public void getUsers(final DatabaseCallback databaseCallback){
       db.userDao().getAll()
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<List<User>>() {
                   @Override
                   public void onSubscribe(Disposable d) {

                   }

                   @Override
                   public void onNext(List<User> users) {
                       databaseCallback.onUsersLoaded(users);
                   }

                   @Override
                   public void onError(Throwable e) {

                   }

                   @Override
                   public void onComplete() {

                   }
               });
   }*/

    /*public void addUser( final String firstName, final String lastName) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                User user = new User(firstName,lastName);
                db.userDao().insertAll(user);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        databaseCallback.onUserAdded();
                    }

                    @Override
                    public void onError(Throwable e) {
                        databaseCallback.onDataNotAvailable();
                    }
                });




    }

    public void deleteUser(final DatabaseCallback databaseCallback, final User user) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.userDao().delete(user);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                databaseCallback.onUserDeleted();
            }

            @Override
            public void onError(Throwable e) {
                databaseCallback.onDataNotAvailable();
            }
        });
    }


    public void updateUser(final DatabaseCallback databaseCallback, final User user) {
        user.setFirstName("first name first name");
        user.setLastName("last name last name");
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.userDao().updateUsers(user);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                databaseCallback.onUserUpdated();
            }

            @Override
            public void onError(Throwable e) {
                databaseCallback.onDataNotAvailable();
            }
        });
    }*/
}