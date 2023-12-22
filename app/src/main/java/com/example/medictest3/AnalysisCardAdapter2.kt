package com.example.medictest3

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medictest3.databinding.AnalysisCardLayoutBinding

class AnalysisCardAdapter2(private val context: Context) : RecyclerView.Adapter<AnalysisCardAdapter2.AnalysisCardViewHolder>() {

    class AnalysisCardViewHolder(val binding: AnalysisCardLayoutBinding) : RecyclerView.ViewHolder(binding.root)
    private val analysisCards : Array<AnalysisCardClass>
    private val myApp : App = context.applicationContext as App
    init {
        analysisCards=myApp.cards
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnalysisCardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AnalysisCardLayoutBinding.inflate(inflater,parent,false)

        return AnalysisCardViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return analysisCards.size
    }

    override fun onBindViewHolder(holder: AnalysisCardViewHolder, position: Int) {
        val card = analysisCards[position]

        with(holder.binding){
            tvCardName.text = card.name
            val price = "${card.price} ${context.resources.getString(R.string.rouble_sign)}"
            tvCardPrice.text = price
            val time = context.resources.getQuantityString(R.plurals.days,card.time,card.time)
            tvCardTime.text = time
        }
    }
}