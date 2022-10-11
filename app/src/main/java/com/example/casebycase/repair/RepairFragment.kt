package com.example.casebycase.repair

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.casebycase.R
import com.example.casebycase.databinding.FragmentRepairBinding

class RepairFragment: Fragment(R.layout.fragment_repair) {
    private var repairBinding: FragmentRepairBinding?=null
    private val binding get() = repairBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        repairBinding = FragmentRepairBinding.inflate(inflater, container, false)
        val view = binding?.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.chairButton.setOnClickListener {
            binding.detailView.setImageResource(R.drawable.repair_detail)
        }

        binding.chainButton.setOnClickListener {
            binding.detailView.setImageResource(R.drawable.repair_detail2)
        }

        binding.frameButton.setOnClickListener {
            binding.detailView.setImageResource(R.drawable.repair_detail3)
        }

        binding.wheelButton.setOnClickListener {
            binding.detailView.setImageResource(R.drawable.repair_detail4)
        }
    }
}