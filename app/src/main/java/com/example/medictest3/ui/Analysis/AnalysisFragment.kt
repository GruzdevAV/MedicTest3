package com.example.medictest3.ui.Analysis

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medictest3.AnalysisCardAdapter2
import com.example.medictest3.CategoriesAdapter2
import com.example.medictest3.NewsAdapter2
import com.example.medictest3.R

class AnalysisFragment : Fragment() {

    companion object {
        fun newInstance() = AnalysisFragment()
    }

    private lateinit var viewModel: AnalysisViewModel

    private lateinit var rvNews : RecyclerView
    private lateinit var rvCategories : RecyclerView
    private lateinit var rvAnalysis : RecyclerView

    private lateinit var newsAdapter: NewsAdapter2
    private lateinit var categoriesAdapter: CategoriesAdapter2
    private lateinit var analysisCardAdapter: AnalysisCardAdapter2



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_analysis, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AnalysisViewModel::class.java)
        rvNews = requireView().findViewById(R.id.rv_news)
        rvCategories = requireView().findViewById(R.id.rv_category_options)
        rvAnalysis = requireView().findViewById(R.id.rv_analysis_list)

        rvNews.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvCategories.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvAnalysis.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        newsAdapter = NewsAdapter2(requireContext())
        categoriesAdapter = CategoriesAdapter2(requireContext())
        categoriesAdapter.selectedCategory = 0
        analysisCardAdapter = AnalysisCardAdapter2(requireContext())

        rvNews.adapter=newsAdapter
        rvCategories.adapter=categoriesAdapter
        rvAnalysis.adapter=analysisCardAdapter
    }

}