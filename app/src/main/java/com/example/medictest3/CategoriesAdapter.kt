package com.example.medictest3
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.BaseAdapter
//import android.widget.Button
//
//class CategoriesAdapter(private val context: Context) : BaseAdapter() {
//    private val categories: Array<String>
//    private val myApp : App = context.applicationContext as App
//    private var _selectedCategory : Int?
//    var SelectedCategory : Int?
//        get() = _selectedCategory
//        set(value) = run{
//            _selectedCategory=value
//            notifyDataSetChanged()
//        }
//    init {
//        _selectedCategory=null
//        categories = myApp.menus
//    }
//    override fun getCount(): Int {
//        return categories.size
//    }
//
//    override fun getItem(index: Int): Any {
//        return categories[index]
//    }
//
//    override fun getItemId(id: Int): Long {
//        return id.toLong()
//    }
//
//    override fun getView(pos: Int, v: View?, parent: ViewGroup?): View {
//        val view = v ?:
//        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.category_layout,null)
//
//        val Button = view.findViewById<Button>(R.id.btn_category)
//        Button.text = categories[pos]
//        Button.background = if (pos == _selectedCategory)
//            view.resources.getDrawable(R.drawable.btn_chip_on)
//        else view.resources.getDrawable(R.drawable.btn_chip_off)
//
//        return view
//    }
//}