package br.com.smartjob.financialapp.view.form

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.graphics.Color
import br.com.smartjob.financialapp.R
import br.com.smartjob.financialapp.databinding.ActivityCreateClientBinding
import br.com.smartjob.financialapp.databinding.ActivityPersonalDataBinding
import br.com.smartjob.financialapp.view.ui.MainActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PersonalDataActivity : AppCompatActivity() {
    private var TAG = "PERSONAL_DATA"

    private lateinit var binding: ActivityPersonalDataBinding
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSaveCard.setOnClickListener {view ->
            val personalData = hashMapOf(
                "name" to binding.editCardDueDate.text.toString(),
                "description" to binding.editCardDescription.text.toString(),
                "owner" to auth.currentUser?.uid.toString()
            )

            try {
            db.collection("users")
                .document(auth.currentUser?.uid.toString())
                .set(personalData)
                .addOnSuccessListener {
                    Log.d(TAG, "DocumentSnapshot successfully written!")
                    showMessage(view ,"Sucesso ao salvar os dados", Color.GREEN)
                    moveMainScreen()
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error writing document", e)
                    showMessage(view ,"Um erro foi encontrado ao salvar os dados", Color.RED)
                }
            } catch (e: Exception)  {
                Log.d(TAG, "Error to create the Client: $e")
                showMessage(view ,"Um erro foi encontrado ao salvar os dados", Color.RED)
            }
        }
    }

    private fun showMessage(view: View, text: String, color: Int){
        val snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG)
        snackbar.setBackgroundTint(color)
        snackbar.show()
    }

    private fun moveMainScreen(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}