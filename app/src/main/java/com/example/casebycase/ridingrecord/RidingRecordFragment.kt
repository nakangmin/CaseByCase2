package com.example.casebycase.ridingrecord

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.casebycase.adapter.RidingRecordAdapter
import com.example.casebycase.databinding.FragmentRidingRecordBinding
import com.example.casebycase.dto.riding_record

class RidingRecordFragment: Fragment() {

    private var _binding: FragmentRidingRecordBinding? = null
    private val binding get() = _binding!!
    private var adapter: RidingRecordAdapter? = null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRidingRecordBinding.inflate(inflater, container, false)
        val root: View = binding.root









        return root
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}