package com.example.rishabh.roomexample;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by rishabh on 5/26/18.
 */

public class MyViewModel extends ViewModel {
    private MutableLiveData<List<User>> userList=new MutableLiveData<>();
    private GetUserUseCase getUserUseCase;
    private AddUserUseCase addUserUseCase;
    private DeleteUserUseCase deleteUserUseCase;
    private CompositeDisposable disposable = new CompositeDisposable();

    public MyViewModel(GetUserUseCase getUserUseCase , AddUserUseCase addUserUseCase
            ,DeleteUserUseCase deleteUserUseCase) {
        this.getUserUseCase = getUserUseCase;
        this.addUserUseCase = addUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
    }

    public LiveData<List<User>> getUsersObservable() {
         return userList;
    }

    public void loadUsers() {
        disposable.add(getUserUseCase.getAllUsers()
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        userList.postValue(users);
                    }
                })
        );

    }

    public void addUser(String firstName , String lastName){
        disposable.add(
                addUserUseCase.addUser(firstName,lastName)
                        .subscribe());
    }

    public void deleteUser(User user){
            disposable.add(
                    deleteUserUseCase.deleteUser(user)
                            .subscribe());

    }
    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable!=null&&!disposable.isDisposed()){
            disposable.dispose();
        }
    }
}
