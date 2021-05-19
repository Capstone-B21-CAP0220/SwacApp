package com.capstone0220.swacapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.capstone0220.swacapp.R
import com.capstone0220.swacapp.databinding.ActivityHomeBinding
import com.capstone0220.swacapp.ui.form.FormActivity
import com.capstone0220.swacapp.ui.information.InformationActivity

class HomeActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationHome.btnHomeReport.setOnClickListener(this)
        binding.bottomNavigationHome.btnHomeInformation.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_home_report -> {
                val intent = Intent(this, FormActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_home_information -> {
                val intent = Intent(this, InformationActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_home_menu -> {

            }
        }
    }
}