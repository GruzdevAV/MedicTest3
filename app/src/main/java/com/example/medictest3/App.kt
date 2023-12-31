package com.example.medictest3

import android.app.Application
import android.content.res.TypedArray
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class App : Application() {
    private lateinit var db : DatabaseReference
    private lateinit var _cards: Array<AnalysisCardClass>
    val cards : Array<AnalysisCardClass>
        get() = _cards
    private lateinit var _news: Array<NewsClass>
    val news : Array<NewsClass>
        get() = _news
    private lateinit var _menus: Array<String>
    val menus : Array<String>
        get() = _menus

    override fun onCreate() {
        super.onCreate()
        _cards= arrayOf()
        _menus= arrayOf()
        _news= arrayOf()
        db = Firebase.database.getReference("MedicFigma")
        val taskCards = db.child("cards").get().addOnSuccessListener {
            val cards = ArrayList<AnalysisCardClass>()
            for( card in it.children){
                val c = card.value as HashMap<String,String>

                cards.add(AnalysisCardClass(
                    name = c["name"]!!,
                    price = c["price"]!!.toInt(),
                    time = c["time"]!!.toInt()
                ))
            }
            _cards = cards.toArray(arrayOf())
        }
        val taskNews = db.child("news").get().addOnSuccessListener {
            val news = ArrayList<NewsClass>()
            for( new in it.children){
                val n = new.value as HashMap<String,String>
                val bgId = resources.getIdentifier(n["background"]!!.removePrefix("@drawable/"),"drawable",packageName)
                val imgId = resources.getIdentifier(n["image"]!!.removePrefix("@drawable/"),"drawable",packageName)
                news.add(NewsClass(
                    description = n["description"]!!,
                    price = n["price"]!!.toInt(),
                    title = n["title"]!!,
                    bg = resources.getDrawable(bgId),
                    image = resources.getDrawable(imgId)
                ))
            }
            _news = news.toArray(arrayOf())
        }
        val taskMenu = db.child("menu_categories").get().addOnSuccessListener {
            val menus = ArrayList<String>()
            for( menu in it.children){
                menus.add(menu.getValue(String::class.java)!!)
            }
            _menus = menus.toArray(arrayOf())
        }
        runBlocking {
            taskMenu.await()
            taskCards.await()
            taskNews.await()
        }
    }
}