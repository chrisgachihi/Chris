package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var edtFName: EditText
    lateinit var edtLName: EditText
    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText
    lateinit var edtPass: EditText
    lateinit var btnCreate: Button
    lateinit var btnCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        edtFName = findViewById(R.id.mEdtFName)
        edtLName = findViewById(R.id.mEdtLName)
        edtEmail = findViewById(R.id.mEdtEmail)
        edtPassword = findViewById(R.id.mEdtPassword)
        edtPass = findViewById(R.id.mEdtPass)
        btnCreate= findViewById(R.id.mBtnCreate)
        btnCancel= findViewById(R.id.mBtnCancel)


        // Set onClick listener to the button and textview
        btnCreate.setOnClickListener {
            val FirstName = edtFName.text.toString().trim()
            val LastName = edtLName.text.toString().trim()
            val email = edtEmail.text.toString().trim()
            val password = edtPassword.text.toString().trim()
            val pass = edtPass.text.toString().trim()

            if (FirstName.isNotEmpty() || LastName.isNotEmpty() || email.isNotEmpty() || password.isNotEmpty() || pass.isNotEmpty()){
                if (password == pass){

                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful){
                            val intent = Intent(this,JoinActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, it.exception.toString(),
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this,"Please check your password again",
                        Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Please fill all the fields",
                    Toast.LENGTH_SHORT).show()
            }
        }

        btnCancel.setOnClickListener {
            var intent = Intent(this,StartUpActivity::class.java)
            startActivity(intent)
        }
    }
}