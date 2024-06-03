package com.example.winiynews.ui.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.winiynews.R
import com.example.winiynews.databinding.BottomsheetFragmentSearchRecipeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * @Author winiymissl
 * @Date 2024-06-03 15:44
 * @Version 1.0
 */
class IngredientBottomSheet : BottomSheetDialogFragment() {
    lateinit var binding: BottomsheetFragmentSearchRecipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.bottomsheet_fragment_search_recipe,
            container,
            false
        )
        binding = BottomsheetFragmentSearchRecipeBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        var list = ArrayList<String>()

        arguments?.apply {
            binding.textViewFoodHeatDetail.text = getStringArrayList("recipeData")?.toString()
        }
    }
}