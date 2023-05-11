package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText
    lateinit var btnLogin: Button
    lateinit var btnCancel: Button
    lateinit var tvForgot: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        edtEmail = findViewById(R.id.mEdtEmail)
        edtPassword = findViewById(R.id.mEdtPassword)
        btnLogin = findViewById(R.id.mBtnLogin)
        btnCancel = findViewById(R.id.mBtnCancel)

        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString().trim()
            val password = edtPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()){
                auth.fetchSignInMethodsForEmail(email).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        val result = task.result
                        if (result != null && result.signInMethods != null && result.signInMethods!!.contains(
                                EmailAuthProvider.EMAIL_PASSWORD_SIGN_IN_METHOD)){
                            //The email address is already registered,so prompt the user to sign in instead.
                            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                                if (it.isSuccessful){
                                    val intent = Intent(this, MainActivity::class.java)
                                    startActivity(intent)
                                }else{
                                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                                }
                            }
                        }else{
                            //The email address is not registered,so show an error message to the user.
                            Toast.makeText(this, "Email address is not registered.", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
        }
        btnCancel.setOnClickListener {
            var intent = Intent(this,StartUpActivity::class.java)
            startActivity(intent)
        }
        tvForgot.setOnClickListener {
            var intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
            }
        }

    }
}