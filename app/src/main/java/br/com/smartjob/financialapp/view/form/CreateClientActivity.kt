package br.com.smartjob.financialapp.view.form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.smartjob.financialapp.R
import br.com.smartjob.financialapp.databinding.ActivityCreateClientBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.lang.Exception
import java.util.zip.Inflater

class CreateClientActivity : AppCompatActivity() {

    private var TAG = "CREATE_CLIENT"

    private lateinit var binding: ActivityCreateClientBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateClientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCreateClient.setOnClickListener {
            val newClient = hashMapOf(
                "name" to binding.editClientName.text.toString(),
                "description" to binding.editClientDescription.text.toString()
            )

//            try {
//            db.collection("teste")
//                .document("A").collection("clients")
//                .document("B").set(newClient)
//                .addOnCompleteListener { result ->
//                    if(result.isSuccessful){
//                        Log.d(TAG, "Success to save the new Client")
//                    }
//                }
//                .addOnFailureListener { error ->
//                    Log.d(TAG, error.message.toString())
//                }
//            } catch (e: Exception)  {
//                Log.d(TAG, "Error to create the Client: $e")
//            }
        }
    }
}