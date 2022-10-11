package com.example.assignement4

import android.app.Activity
import android.content.Intent
import android.content.Intent.ACTION_SENDTO
import android.content.Intent.EXTRA_EMAIL
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*


class MainActivity : AppCompatActivity() {
    var users:ArrayList<User>?=arrayListOf<User>(
        User("khaled","k","khaled@gmail.com","khaledPassword"),
        User("Stephanie","James","stephanie@gmail.com","stephaniePassword"),
        User("test","test","test@gmail.com","testPassword"),
        User("Tony","Montana","tony@gmail.com","tonyPassword"),
        User("Jhon","Jhonny","jhon@gmail.com","jhonPassword"),
        User("Jhon","Jhonny","t","t"),

    );

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sign_in.setOnClickListener{
            val username=input_username.text.toString()
            val password=input_password.text.toString()
            if(isCorrectSignIn(username,password)){
                val intent = Intent(this, Shopping::class.java)
                intent.putExtra("username",username )
                startActivity(intent)
            }else {
                Toast.makeText(applicationContext,"User name or password incorrect", Toast.LENGTH_LONG).show()
            }
        }
        button2.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            resultContracts.launch(intent)
        }
        btn_forgot.setOnClickListener {
            val to = arrayOf("email@server.com")
            val uri = Uri.parse("mailto:email@server.com")
                .buildUpon()
                .appendQueryParameter("subject", "the password")
                .appendQueryParameter("body", "****")
                .build()
            val emailIntent = Intent(ACTION_SENDTO, uri)
            emailIntent.putExtra(EXTRA_EMAIL, to)
            startActivity(Intent.createChooser(emailIntent, "Send mail..."))
            Toast.makeText(applicationContext,"Send and email", Toast.LENGTH_LONG).show()
        }
    }
        //Retrieve the Result back doing the below step
        var resultContracts = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
            if(result.resultCode == Activity.RESULT_OK)
            {
                val new_user:User = result.data?.getSerializableExtra("New_User") as User
                if(isUserExist(new_user.userName)){
                    Toast.makeText(applicationContext,"${new_user.userName} already exist", Toast.LENGTH_LONG).show()
                }
                else {
                    users!!.add(new_user)
                    Toast.makeText(applicationContext,"User added successfully", Toast.LENGTH_LONG).show()
                }
            }
            else {
                //nothing to do
                }
            }



    fun isCorrectSignIn( username:String, password:String):Boolean{
        for(u:User in this.users!!){
            if(u.userName.equals(username)&&u.password.equals(password))return true
        }
        return false
    }
    fun isUserExist(username:String):Boolean{
        for(u:User in this.users!!){
            if(u.userName.equals(username))return true
        }
        return false
    }
}