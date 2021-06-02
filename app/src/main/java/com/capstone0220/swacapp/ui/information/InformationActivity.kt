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
import com.capstone0220.swacapp.ui.utils.NetworkMonitor

class InformationActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityInformationBinding
    private val url = "https://kekerasan.kemenpppa.go.id/ringkasan"
    private lateinit var networkMonitor: NetworkMonitor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            binding.toolbarInformation.btnInformationBack.setOnClickListener(this@InformationActivity)
        }

        checkConnections()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_information_back -> {
                onBackPressed()
            }
        }
    }


    fun webView(){
        binding.disconnected.disconnectedMsg.visibility = View.INVISIBLE
        val webView = findViewById<WebView>(R.id.webView)
        binding.progressBar.visibility = View.VISIBLE
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                binding.progressBar.visibility = View.GONE
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


    private fun checkConnections(){
        networkMonitor = NetworkMonitor(application)
        networkMonitor.observe(this,{isAvailable->
            when(isAvailable){
                true -> {
                    webView()
                    binding.webView.visibility = WebView.VISIBLE
                }
                false -> {
                    binding.webView.visibility = WebView.GONE
                    binding.disconnected.disconnectedMsg.visibility = View.VISIBLE
                }
            }
        })

    }
}