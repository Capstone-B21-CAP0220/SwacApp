package com.capstone0220.swacapp.ui.form


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.capstone0220.swacapp.R
import com.capstone0220.swacapp.databinding.ActivityFormBinding
import com.capstone0220.swacapp.ui.confirmation.ConfirmationActivity

class FormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnFormBack.setOnClickListener(this@FormActivity)
            btnFormReport.setOnClickListener(this@FormActivity)
        }
    }



    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_form_back -> {
                onBackPressed()
            }
            R.id.btn_form_report -> {
                val intent = Intent(this, ConfirmationActivity::class.java)
                startActivity(intent)
            }
        }
    }
}