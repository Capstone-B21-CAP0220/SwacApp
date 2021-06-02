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
import com.capstone0220.swacapp.ui.utils.NetworkMonitor

class FormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityFormBinding
    private lateinit var networkMonitor: NetworkMonitor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkConnections()
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
                setData()
            }
        }
    }

    fun setData(){
        val edtName = findViewById<EditText>(R.id.edt_form_name)
        val edtPosition = findViewById<EditText>(R.id.edt_form_reportingtype)
        val edtLocation = findViewById<EditText>(R.id.edt_form_location)
        val edtEmail = findViewById<EditText>(R.id.edt_form_email)
        val edtPhone = findViewById<EditText>(R.id.edt_form_phone)
        val edtDesc = findViewById<EditText>(R.id.edt_form_desc)
        val name = edtName.text.toString()
        val position = edtPosition.text.toString()
        val location = edtLocation.text.toString()
        val email = edtEmail.text.toString()
        val no_telp = edtPhone.text.toString()
        val desc = edtDesc.text.toString()

        if (name.isNullOrEmpty()) {
            edtName?.error = "Nama Harap Diisi"
            edtName?.requestFocus()
        }else if(position.isNullOrEmpty()){
            edtPosition?.error = "Posisi Pelapor Harap Diisi"
            edtPosition?.requestFocus()
        }else if(location.isNullOrEmpty()){
            edtLocation?.error = "Lokasi Harap Diisi"
            edtLocation?.requestFocus()
        }else if(email.isNullOrEmpty()){
            edtEmail?.error = "Email Harap Diisi"
            edtEmail?.requestFocus()
        }else if(no_telp.isNullOrEmpty()){
            edtPhone?.error = "No.Telp Harap Diisi"
            edtPhone?.requestFocus()
        }else if (desc.isNullOrEmpty()){
            edtDesc?.error = "Deskripsi Harap Diisi"
            edtDesc?.requestFocus()
        }else{
            val userData = UserData(
                name,position,location,email,no_telp,desc
            )
            val intent = Intent(this, ConfirmationActivity::class.java)
            intent.putExtra(ConfirmationActivity.EXTRA_USER, userData)
            startActivity(intent)
        }
    }

    private fun checkConnections(){
        networkMonitor = NetworkMonitor(application)
        networkMonitor.observe(this,{isAvailable->
            when(isAvailable){
                true -> {
                    binding.disconnected.disconnectedMsg.visibility = View.GONE
                    binding.formInputReport.visibility = View.VISIBLE
                }
                false -> {
                    binding.formInputReport.visibility = View.GONE
                    binding.disconnected.disconnectedMsg.visibility = View.VISIBLE
                }
            }
        })

    }
}