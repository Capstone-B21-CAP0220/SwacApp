package com.capstone0220.swacapp.ui.result

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.capstone0220.swacapp.R
import com.capstone0220.swacapp.databinding.ActivityResultBinding
import com.capstone0220.swacapp.ui.home.HomeActivity
import org.json.JSONObject

class ResultActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setData()
        binding.apply {
            btnResultHome.setOnClickListener(this@ResultActivity)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_result_home -> {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finishAffinity()
            }
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

    fun setData(){
        Toast.makeText(this@ResultActivity,"Selamat, Pelaporan Berhasil", Toast.LENGTH_SHORT).show()
        val results = intent.getStringExtra("json_results")
        val responseObject = JSONObject(results)
        val data = responseObject.getJSONObject("data")
        val edtResName = findViewById<EditText>(R.id.edt_result_name)
        val edtResEmail = findViewById<EditText>(R.id.edt_result_email)
        val edtResPhone = findViewById<EditText>(R.id.edt_result_no)
        val edtResCategory = findViewById<EditText>(R.id.edt_result_category)
        val edtResProcess = findViewById<EditText>(R.id.edt_result_process)

        edtResName.setText(data.getString("name").toString())
        edtResEmail.setText(data.getString("email").toString())
        edtResPhone.setText(data.getString("no_telphone").toString())
        edtResCategory.setText(data.getString("jenis_kekerasan").toString())
        edtResProcess.setText(data.getString("tindakan_kasus").toString())
    }
}