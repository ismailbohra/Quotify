package com.example.todayquotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    private val quotetext:TextView
        get() = findViewById(R.id.quoteText)
    private val quoteAuthor:TextView
        get() = findViewById(R.id.quoteAuthor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)

        setquote(mainViewModel.getquote())
    }



    fun setquote(quote: Quote){
        quotetext.text = quote.text
        quoteAuthor.text =quote.author
    }

    fun onPrevious(view: View) {
        setquote(mainViewModel.previousquote())
    }
    fun onNext(view: View) {
        setquote(mainViewModel.nextquote())
    }
    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getquote().text)
        startActivity(intent)
    }
}