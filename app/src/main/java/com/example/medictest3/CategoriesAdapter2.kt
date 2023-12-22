package com.example.medictest3

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.medictest3.databinding.CategoryLayoutBinding

class CategoriesAdapter2(private val context: Context) : RecyclerView.Adapter<CategoriesAdapter2.CategoriesViewHolder>() {
    class CategoriesViewHolder(val binding : CategoryLayoutBinding) : RecyclerView.ViewHolder(binding.root)
    private val categories: Array<String>
    private val myApp : App = context.applicationContext as App
    private var _selectedCategory : Int?
    var SelectedCategory : Int?
        get() = _selectedCategory
        set(value) = run{
            _selectedCategory?.let { notifyItemChanged(it) }
            _selectedCategory=value
            _selectedCategory?.let { notifyItemChanged(it) }
        }
    init {
        _selectedCategory=null
        categories = myApp.menus
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoryLayoutBinding.inflate(inflater,parent,false)
        return CategoriesViewHolder(binding)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val cat = categories[position]
        with(holder.binding){
            btnCategory.text = cat
            if (position==SelectedCategory){
                btnCategory.background = AppCompatResources.getDrawable(context,R.drawable.btn_chip_on)
                btnCategory.setTextColor(context.resources.getColor(R.color.white))
            }
            else {
                btnCategory.background =
                    AppCompatResources.getDrawable(context, R.drawable.btn_chip_off)
                btnCategory.setTextColor(context.resources.getColor(R.color.icons))
            }

        }
    }
}