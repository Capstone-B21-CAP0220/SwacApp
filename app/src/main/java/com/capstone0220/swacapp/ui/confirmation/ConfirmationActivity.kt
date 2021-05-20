package com.capstone0220.swacapp.ui.confirmation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.capstone0220.swacapp.R
import com.capstone0220.swacapp.databinding.ActivityConfirmationBinding
import com.capstone0220.swacapp.ui.result.ResultActivity

class ConfirmationActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityConfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnConfirmBack.setOnClickListener(this@ConfirmationActivity)
            btnConfirmReport.setOnClickListener(this@ConfirmationActivity)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_confirm_back -> {
                onBackPressed()
            }
            R.id.btn_confirm_report -> {
                val intent = Intent(this, ResultActivity::class.java)
                startActivity(intent)
            }
        }
    }
}