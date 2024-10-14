package com.mishbanya.effectivemobiletest.presentation.views

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mishbanya.effectivemobiletest.R
import com.mishbanya.effectivemobiletest.domain.main.usecase.FragmentChangeListener
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), FragmentChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setLocationLanguage("en")

        startUpLoading()

    }


    override fun onSearchClicked() {
        supportFragmentManager.beginTransaction()
            //.replace(R.id.fragmentHolderId, SearchFragment())
            .commit()
    }



    private fun startUpLoading() {
        greetingMessageShow()
        supportFragmentManager.beginTransaction()
            //.replace(R.id.fragmentHolderId, SearchFragment())
            .commit()
    }


    private fun setLocationLanguage(language: String){
        val context: Context = applicationContext
        context.resources.configuration.setLocale(Locale(language))
    }

    private fun greetingMessageShow(){
        showToast(this.getString(R.string.welcome))
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}