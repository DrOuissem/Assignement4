package com.example.assignement4

import android.app.Activity
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        registerButton.setOnClickListener {
            if(TextUtils.isEmpty(rfname.text)){
                rfname.setError( "First name is required!" );
                Toast.makeText(applicationContext,"FirstName is required",Toast.LENGTH_LONG).show()
            }
                else if(TextUtils.isEmpty(rlname.text))
                {
                    rlname.setError( "Last name is required!" );
                    Toast.makeText(applicationContext,"Lastname is required",Toast.LENGTH_LONG).show()

                }else if(TextUtils.isEmpty(remail.text))
                {
                remail.setError( " Email is required!" );
                Toast.makeText(applicationContext,"Email is required",Toast.LENGTH_LONG).show()
                }
                else if(TextUtils.isEmpty(rpassword.text))
                {
                rpassword.setError( "Password is required!" );
                Toast.makeText(applicationContext,"Password is required",Toast.LENGTH_LONG).show()

                } else {
                val username=remail.text.toString()
                val fname=rfname.text.toString()
                val lname=rlname.text.toString()
                val password=rpassword.text.toString()
                val user=User(fname, lname,username,password)
                val rintent = intent
                intent.putExtra("New_User", user);
                setResult(Activity.RESULT_OK, rintent)
                finish()
                }
            }

            }
    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

}
