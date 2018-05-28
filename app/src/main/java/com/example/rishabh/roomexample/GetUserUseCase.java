package com.example.rishabh.roomexample;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.view.View;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rishabh on 5/26/18.
 */

public class GetUserUseCase {

    private
    DatabaseManager databaseManager;
    public GetUserUseCase(DatabaseManager databaseManager){
        this.databaseManager = databaseManager;
    }


    public Flowable<List<User>> getAllUsers(){
        return databaseManager.getUsers().subscribeOn(Schedulers.io());
    }
}
