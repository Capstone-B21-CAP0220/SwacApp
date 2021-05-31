package com.capstone0220.swacapp.ui.confirmation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.capstone0220.swacapp.R
import com.capstone0220.swacapp.databinding.ActivityConfirmationBinding
import com.capstone0220.swacapp.ui.data.UserData
import com.capstone0220.swacapp.ui.result.ResultActivity

class ConfirmationActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityConfirmationBinding
    companion object {
        const val EXTRA_USER = "extra_user"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btnConfirmBack.setOnClickListener(this@ConfirmationActivity)
            btnConfirmReport.setOnClickListener(this@ConfirmationActivity)
        }
        setData()
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

    fun setData(){
        val userData = intent.getParcelableExtra<UserData>(EXTRA_USER) as UserData
        val edtConfName = findViewById<EditText>(R.id.edt_confirm_name)
        val edtConfEmail = findViewById<EditText>(R.id.edt_confirm_email)
        val edtConfPhone = findViewById<EditText>(R.id.edt_confirm_phone)
        val edtConfDesc = findViewById<EditText>(R.id.edt_confirm_desc)
        edtConfName.setText(userData.name.toString())
        edtConfEmail.setText(userData.email.toString())
        edtConfPhone.setText(userData.no_telp.toString())
        edtConfDesc.setText(userData.description.toString())


    }
}