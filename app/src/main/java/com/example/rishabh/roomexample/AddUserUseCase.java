package com.example.rishabh.roomexample;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rishabh on 5/26/18.
 */

public class AddUserUseCase {

    private DatabaseManager databaseManager;

    public AddUserUseCase(DatabaseManager databaseManager){
        this.databaseManager = databaseManager;
    }
        public Completable addUser(String firstName , String lastName){
        return databaseManager.addUser(firstName,lastName).subscribeOn(Schedulers.io());
    }
}
