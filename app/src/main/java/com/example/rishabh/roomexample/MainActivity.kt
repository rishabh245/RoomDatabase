package com.example.rishabh.roomexample

import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_new_user.*
import kotlinx.android.synthetic.main.add_new_user.view.*

class MainActivity : AppCompatActivity(), UsersAdapter.UserViewListener {
    var dataBaseManager : DatabaseManager? = null
    var getUserUseCase : GetUserUseCase? = null
    var addUserUseCase : AddUserUseCase? = null
    var deleteUserUseCase : DeleteUserUseCase? = null
    var myViewModel : MyViewModel? = null

    override fun onClicked(user : User) {
        myViewModel?.deleteUser(user)
    }


    private var adapter: UsersAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataBaseManager = DatabaseManager.getInstance(this)
        getUserUseCase = GetUserUseCase(dataBaseManager)
        addUserUseCase = AddUserUseCase(dataBaseManager)
        deleteUserUseCase = DeleteUserUseCase(dataBaseManager)
        myViewModel = ViewModelProviders.
                of(this , ViewModelFactory(getUserUseCase, addUserUseCase
                        ,deleteUserUseCase))
                .get(MyViewModel::class.java)

        recyler_view_users_list.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        adapter = UsersAdapter(this ,this)
        recyler_view_users_list.adapter = adapter

        myViewModel?.usersObservable?.observe(this, Observer {
            it?.let {
                Log.d("tagg" , it.size.toString() )
                adapter?.setUserList(it);
            }
        })

        myViewModel?.loadUsers()
        fab.setOnClickListener { showdialog() }
    }

    private fun showdialog() {
        val builder = AlertDialog.Builder(this)
        var firstName : String
        var lastName : String
        val view = layoutInflater.inflate(R.layout.add_new_user,null)
        builder.setView(view)
        builder.setTitle("Add new User")
        builder.setMessage("Enter the details")
        builder.setPositiveButton("YES"){dialog, which ->
            firstName = view.edit_text_firstname.text.toString()
            lastName = view.edit_text_lastname.text.toString()
            if(firstName.equals("") || lastName.equals("")){
                Toast.makeText(this,"Enter all the info!!" , Toast.LENGTH_SHORT).show()
            }else{
                myViewModel?.addUser(firstName,lastName);
            }
        }
        builder.setNegativeButton("Cancel"){
            dialog , which ->
             dialog.dismiss()
        }
        val dialog:AlertDialog = builder.create()
        dialog.show()

    }

}
