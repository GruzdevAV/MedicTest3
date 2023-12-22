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
//    private lateinit var _cardNames: Array<String>
//    val cardNames : Array<String>
//        get() = _cardNames
//    private lateinit var _cardTimes: IntArray
//    val cardTimes : IntArray
//        get() = _cardTimes
//    private lateinit var _cardPrices: IntArray
//    val cardPrices : IntArray
//        get() = _cardPrices
//    private lateinit var _categories: Array<String>
//    val categories : Array<String>
//        get() = _categories
//    private lateinit var _newsTitles: Array<String>
//    val newsTitles : Array<String>
//        get() = _newsTitles
//    private lateinit var _newsDescriptions: Array<String>
//    val newsDescriptions : Array<String>
//        get() = _newsDescriptions
//    private lateinit var _newsPrices: IntArray
//    val newsPrices : IntArray
//        get() = _newsPrices
//    private lateinit var _newsImages: TypedArray
//    val newsImages : TypedArray
//        get() = _newsImages
//    private lateinit var _newsBackgrounds: TypedArray
//    val newsBackgrounds : TypedArray
//        get() = _newsBackgrounds
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
//        _cardNames = resources.getStringArray(R.array.card_names)
//        _cardTimes = resources.getIntArray(R.array.card_times)
//        _cardPrices = resources.getIntArray(R.array.card_prices)
//        _categories = resources.getStringArray(R.array.menu_categories)
//        _newsPrices = resources.getIntArray(R.array.news_prices)
//        _newsTitles = resources.getStringArray(R.array.news_titles)
//        _newsDescriptions = resources.getStringArray(R.array.news_descriptions)
//        _newsImages = resources.obtainTypedArray(R.array.news_images)
//        _newsBackgrounds = resources.obtainTypedArray(R.array.news_backgrounds)
    }
}