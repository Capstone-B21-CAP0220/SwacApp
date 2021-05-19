package com.capstone0220.swacapp.ui.information

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.capstone0220.swacapp.R
import com.capstone0220.swacapp.databinding.ActivityInformationBinding
import kotlinx.android.synthetic.main.navigation_botom.*
import kotlinx.android.synthetic.main.toolbar_infrormation.*

class InformationActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btn_information_back.setOnClickListener(this@InformationActivity)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_information_back -> {
                onBackPressed()
            }
        }
    }
}