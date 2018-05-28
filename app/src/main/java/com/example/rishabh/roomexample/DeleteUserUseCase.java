package com.example.rishabh.roomexample;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rishabh on 5/27/18.
 */

public class DeleteUserUseCase {
    private DatabaseManager databaseManager;

    public DeleteUserUseCase(DatabaseManager databaseManager){
        this.databaseManager = databaseManager;
    }
    public Completable deleteUser(User user){
        return databaseManager.deleteUser(user).subscribeOn(Schedulers.io());
    }
}
