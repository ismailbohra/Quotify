package com.example.todayquotes

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context:Context):ViewModel() {
    private var quotelist:Array<Quote> = emptyArray()
    private var index = 0

    init {
        quotelist = loadQuotesFromAssets()
    }

    private fun loadQuotesFromAssets(): Array<Quote> {
        val inputStream = context.assets.open("quotes.json")
        val size : Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json,Array<Quote>::class.java)
    }

    fun getquote() = quotelist[index]

    fun nextquote() = quotelist[(++index + quotelist.size) % quotelist.size]
        //quotelist[++index %  quotelist.size]

    fun previousquote() = quotelist[(--index + quotelist.size) % quotelist.size]

}