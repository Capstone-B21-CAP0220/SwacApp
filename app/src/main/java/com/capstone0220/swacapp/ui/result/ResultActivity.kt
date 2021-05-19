package com.capstone0220.swacapp.ui.result

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.capstone0220.swacapp.R
import com.capstone0220.swacapp.databinding.ActivityResultBinding
import com.capstone0220.swacapp.ui.home.HomeActivity

class ResultActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnResultHome.setOnClickListener(this@ResultActivity)
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_result_home -> {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                onDestroy()
            }
        }
    }
}