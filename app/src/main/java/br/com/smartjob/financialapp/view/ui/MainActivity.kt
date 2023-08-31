package br.com.smartjob.financialapp.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import br.com.smartjob.financialapp.R
import br.com.smartjob.financialapp.databinding.ActivityMainBinding
import br.com.smartjob.financialapp.view.form.PersonalDataActivity

class MainActivity : AppCompatActivity() {
    private var TAG = "LOGIN_ACTIVITY"
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMenuCreatePersonal.setOnClickListener { view ->
            movePersonalData()
        }
    }

    private fun movePersonalData(){
        val intent = Intent(this, PersonalDataActivity::class.java)
        startActivity(intent)
        finish()
    }
}