package br.com.smartjob.financialapp.view.form

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import br.com.smartjob.financialapp.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    private var TAG = "LOGIN_ACTIVITY"

    private lateinit var binding: ActivityLoginBinding
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (checkUserIsAuthenticated() ){
            moveMainScreen()
        }

        binding.btnLogin.setOnClickListener {view ->
            val email = binding.loginEmail.text.toString()
            val password = binding.loginPassword.text.toString()

            if(email.isEmpty() || password.isEmpty()){
                showMessage(view, "Preencha todos os campos", Color.RED)
            }else{
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            val user = auth.currentUser
                            Log.d(TAG, user?.uid.toString())
                            moveMainScreen()
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            showMessage(view, "Falha ao efetuar o login", Color.RED)
                        }
                    }
            }
        }
    }

    private fun showMessage(view: View, text: String, color: Int){
        val snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG)
        snackbar.setBackgroundTint(color)
        snackbar.show()
    }

    private fun moveMainScreen(){
        val intent = Intent(this, br.com.smartjob.financialapp.view.ui.MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun checkUserIsAuthenticated() : Boolean{
        val user = auth.currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val name = it.displayName
            val email = it.email
            val photoUrl = it.photoUrl

            // Check if user's email is verified
            val emailVerified = it.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            val uid = it.uid
            Log.d(TAG, "${name} ${email} ${uid} ${emailVerified}")
            return true
        }
        return false
    }
}