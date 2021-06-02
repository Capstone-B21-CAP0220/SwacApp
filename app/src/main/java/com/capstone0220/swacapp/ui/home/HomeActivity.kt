package com.capstone0220.swacapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.capstone0220.swacapp.R
import com.capstone0220.swacapp.databinding.ActivityHomeBinding
import com.capstone0220.swacapp.ui.form.FormActivity
import com.capstone0220.swacapp.ui.information.InformationActivity
import com.capstone0220.swacapp.ui.slider.SliderAdapter
import com.capstone0220.swacapp.ui.slider.SliderItem

import kotlin.math.abs

class HomeActivity : AppCompatActivity(){

    private lateinit var viewPager2 : ViewPager2
    private val sliderHandler = Handler()
    private lateinit var binding: ActivityHomeBinding
    private var doubleBackToExitPressedOnce = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Navigate
        binding.include2.btnInformation.setOnClickListener {
            val intent = Intent(this, InformationActivity::class.java)
            startActivity(intent)
        }
        binding.include2.btnLapor.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }
        //EOF Navigate

        viewPager2 = findViewById(R.id.viewPager_ImageSlider)

        var sliderItems : MutableList<SliderItem> = ArrayList()

        sliderItems.add(SliderItem(R.drawable.information1))
        sliderItems.add(SliderItem(R.drawable.information2))
        sliderItems.add(SliderItem(R.drawable.information3))
        sliderItems.add(SliderItem(R.drawable.information4))

        viewPager2.adapter = SliderAdapter(sliderItems,viewPager2)

        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.offscreenPageLimit = 2
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(30))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1- abs(position)
            page.scaleY = 0.85f + r * 0.25f
        }

        viewPager2.setPageTransformer(compositePageTransformer)

        viewPager2.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(slideRunnable)
                sliderHandler.postDelayed(slideRunnable,10000)
            }
        })

    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

    private val slideRunnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    override fun onPause() {
        super.onPause()
        sliderHandler.postDelayed(slideRunnable,8000)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(slideRunnable,8000)
    }
}