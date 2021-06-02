package com.capstone0220.swacapp.ui.confirmation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.capstone0220.swacapp.R
import com.capstone0220.swacapp.databinding.ActivityConfirmationBinding
import com.capstone0220.swacapp.ui.data.UserData
import com.capstone0220.swacapp.ui.result.ResultActivity
import com.capstone0220.swacapp.ui.utils.APIService
import com.capstone0220.swacapp.ui.utils.RetrofitAPI
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

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
                binding.progressBar.visibility = View.VISIBLE
                postData()
            }
        }
    }
    fun postData(){
        val userData = intent.getParcelableExtra<UserData>(EXTRA_USER) as UserData
        val retrofit = RetrofitAPI().getRetroClientInstance()
        val service = retrofit.create(APIService::class.java)

        val jsonObject = JSONObject()
        jsonObject.put("name",userData.name.toString())
        jsonObject.put("job",userData.email.toString())
        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.createReport(requestBody)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string()
                        )
                    )
//                    Log.d("Pretty Printed JSON :", prettyJson)
                    val intent = Intent(this@ConfirmationActivity, ResultActivity::class.java)
                    intent.putExtra("json_results", prettyJson)
                    this@ConfirmationActivity.startActivity(intent)
                    binding.progressBar.visibility = View.GONE
                } else {
                    Toast.makeText(this@ConfirmationActivity,response.code().toString(), Toast.LENGTH_SHORT).show()
                    Log.e("RETROFIT_ERROR", response.code().toString())
                }
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