package com.mishbanya.effectivemobiletest.presentation.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mishbanya.effectivemobiletest.R
import com.mishbanya.effectivemobiletest.databinding.ActivityMainBinding
import com.mishbanya.effectivemobiletest.domain.main.usecase.FragmentChangeListener
import dagger.hilt.android.AndroidEntryPoint
import java.util.LinkedList
import java.util.Locale

@AndroidEntryPoint
@SuppressLint("UseCompatLoadingForDrawables")
class MainActivity : AppCompatActivity(), FragmentChangeListener {
    private val binding by viewBinding(ActivityMainBinding::bind)

    private val labels = LinkedList<Pair<ImageButton, TextView>>()
    private val actives = LinkedList<Drawable>()
    private val inActives = LinkedList<Drawable>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setActives()
        setInActives()
        setLabels()
        setLocationLanguage("en")
        startUpLoading()

        initListenerSearchButton()
        initListenerFavoritesButton()
        initListenerCallbacksButton()
        initListenerMessagesButton()
        initListenerProfileButton()
    }
    private fun initListenerSearchButton(){
        labels[0].first.setOnClickListener {
            onSearchClicked()
            updateLabels(0)
        }
    }
    private fun initListenerFavoritesButton(){
        labels[1].first.setOnClickListener {
            onFavoritesClicked()
            updateLabels(1)
        }
    }
    private fun initListenerCallbacksButton(){
        labels[2].first.setOnClickListener {
            onCallbacksClicked()
            updateLabels(2)
        }
    }
    private fun initListenerMessagesButton(){
        labels[3].first.setOnClickListener {
            onMessagesClicked()
            updateLabels(3)
        }
    }
    private fun initListenerProfileButton(){
        labels[4].first.setOnClickListener {
            onProfileClicked()
            updateLabels(4)
        }
    }

    override fun onSearchClicked() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentHolderId, SearchFragment())
            .commit()
    }

    override fun onFavoritesClicked() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentHolderId, FavoritesFragment())
            .commit()
    }

    override fun onCallbacksClicked() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentHolderId, CallbacksFragment())
            .commit()
    }

    override fun onMessagesClicked() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentHolderId, MessagesFragment())
            .commit()
    }

    override fun onProfileClicked() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentHolderId, ProfileFragment())
            .commit()
    }


    private fun startUpLoading() {
        greetingMessageShow()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentHolderId, SearchFragment())
            .commit()
    }
    private fun setActives(){
        actives.add(getResources().getDrawable(R.drawable.search_active))
        actives.add(getResources().getDrawable(R.drawable.heart_active))
        actives.add(getResources().getDrawable(R.drawable.callbacks_default))
        actives.add(getResources().getDrawable(R.drawable.messages_default))
        actives.add(getResources().getDrawable(R.drawable.profile_default))
    }
    private fun setInActives(){
        inActives.add(getResources().getDrawable(R.drawable.search_default))
        inActives.add(getResources().getDrawable(R.drawable.heart_default))
        inActives.add(getResources().getDrawable(R.drawable.callbacks_default))
        inActives.add(getResources().getDrawable(R.drawable.messages_default))
        inActives.add(getResources().getDrawable(R.drawable.profile_default))
    }
    private fun setLabels(){
        labels.add(Pair(binding.searchButton, binding.searchButtonLabel))
        labels.add(Pair(binding.favoritesButton, binding.favoritesButtonLabel))
        labels.add(Pair(binding.callbacksButton, binding.callbacksButtonLabel))
        labels.add(Pair(binding.messagesButton, binding.messagesButtonLabel))
        labels.add(Pair(binding.profileButton, binding.profileButtonLabel))
    }
    private fun updateLabels(id: Int){
        for(i in 0..4){
            if(id==i){
                labels[i].first.setImageDrawable(actives[i])
                labels[i].second.setTextColor(getResources().getColor(R.color.blue))
            }else{
                labels[i].first.setImageDrawable(inActives[i])
                labels[i].second.setTextColor(getResources().getColor(R.color.gray4))
            }
        }
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