package com.capstone0220.swacapp.ui.information

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.capstone0220.swacapp.R
import com.capstone0220.swacapp.databinding.ActivityInformationBinding
import kotlinx.android.synthetic.main.activity_information.*
import kotlinx.android.synthetic.main.navigation_botom.*
import kotlinx.android.synthetic.main.toolbar_infrormation.*

class InformationActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityInformationBinding
    private val url = "https://kekerasan.kemenpppa.go.id/ringkasan"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btn_information_back.setOnClickListener(this@InformationActivity)
        }

        val webView = findViewById<WebView>(R.id.webView)

        progressBar.visibility = View.VISIBLE
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                progressBar.visibility = View.GONE
                view.loadUrl("javascript:alert('Web Loaded successfully')")
            }
        }
        webView.webChromeClient = object : WebChromeClient() {
            override fun onJsAlert(view: WebView, url: String, message: String, result: android.webkit.JsResult): Boolean {
                Toast.makeText(this@InformationActivity, message, Toast.LENGTH_LONG).show()
                result.confirm()
                return true
            }
        }
        webView.loadUrl(url)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_information_back -> {
                onBackPressed()
            }
        }
    }
}