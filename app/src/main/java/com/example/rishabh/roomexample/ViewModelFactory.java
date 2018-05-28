package com.example.rishabh.roomexample;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

/**
 * Created by rishabh on 5/26/18.
 */

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private GetUserUseCase mGetUserUseCase;
    private AddUserUseCase mAddUserUseCase;
    private DeleteUserUseCase mDeleteUserUseCase;
    public ViewModelFactory (GetUserUseCase getUserUseCase , AddUserUseCase addUserUseCase
                ,DeleteUserUseCase deleteUserUseCase) {
        mGetUserUseCase = getUserUseCase;
        mAddUserUseCase = addUserUseCase;
        mDeleteUserUseCase = deleteUserUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MyViewModel(mGetUserUseCase, mAddUserUseCase,mDeleteUserUseCase);
    }
}
