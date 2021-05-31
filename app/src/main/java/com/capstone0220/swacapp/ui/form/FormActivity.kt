package com.capstone0220.swacapp.ui.form


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.capstone0220.swacapp.R
import com.capstone0220.swacapp.databinding.ActivityFormBinding
import com.capstone0220.swacapp.ui.confirmation.ConfirmationActivity
import com.capstone0220.swacapp.ui.data.UserData

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
                val edtName = findViewById<EditText>(R.id.edt_form_name)
                val edtEmail = findViewById<EditText>(R.id.edt_form_email)
                val edtPhone = findViewById<EditText>(R.id.edt_form_phone)
                val edtDesc = findViewById<EditText>(R.id.edt_form_desc)
                val name = edtName.text.toString()
                val email = edtEmail.text.toString()
                val phone = edtPhone.text.toString()
                val desc = edtDesc.text.toString()

                if (name.isNullOrEmpty()) {
                    edtName?.error = "Nama Tidak Boleh Kosong"
                    edtName?.requestFocus()
                }else if(email.isNullOrEmpty()){
                    edtEmail?.error = "Email Tidak Boleh Kosong"
                    edtEmail?.requestFocus()
                }else if(phone.isNullOrEmpty()){
                    edtPhone?.error = "No.Telp Tidak Boleh Kosong"
                    edtPhone?.requestFocus()
                }else if (desc.isNullOrEmpty()){
                    edtDesc?.error = "Deskripsi Tidak Boleh Kosong"
                    edtDesc?.requestFocus()
                }else{
                    val userData = UserData(
                        name,email,phone,desc
                    )
                    val intent = Intent(this, ConfirmationActivity::class.java)
                    intent.putExtra(ConfirmationActivity.EXTRA_USER, userData)
                    startActivity(intent)
                }
            }
        }
    }

}