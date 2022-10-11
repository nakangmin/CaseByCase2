package com.example.casebycase.record

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.casebycase.adapter.RidingRecordAdapter
import com.example.casebycase.databinding.FragmentRecordBinding
import com.example.casebycase.dto.riding_record


class RecordFragment: Fragment() {

    private var _binding: FragmentRecordBinding? = null
    private val binding get() = _binding!!
    private var adapter: RidingRecordAdapter? = null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecordBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val list = ArrayList<riding_record>()
        list.add(riding_record("2022.06.15", "총 거리 : 54km", "달린 시간: 2시간"))
        list.add(riding_record("2022.06.15", "총 거리 : 54km", "달린 시간: 2시간"))
        list.add(riding_record("2022.06.15", "총 거리 : 54km", "달린 시간: 2시간"))
        list.add(riding_record("2022.06.15", "총 거리 : 54km", "달린 시간: 2시간"))
        list.add(riding_record("2022.06.15", "총 거리 : 54km", "달린 시간: 2시간"))
        list.add(riding_record("2022.06.15", "총 거리 : 54km", "달린 시간: 2시간"))
        list.add(riding_record("2022.06.15", "총 거리 : 54km", "달린 시간: 2시간"))
        list.add(riding_record("2022.06.15", "총 거리 : 54km", "달린 시간: 2시간"))
        list.add(riding_record("2022.06.15", "총 거리 : 54km", "달린 시간: 2시간"))
        list.add(riding_record("2022.06.15", "총 거리 : 54km", "달린 시간: 2시간"))
        list.add(riding_record("2022.06.15", "총 거리 : 54km", "달린 시간: 2시간"))
        adapter = RidingRecordAdapter()
        binding.ridingRecordRecycleView.layoutManager = LinearLayoutManager(context)
        binding.ridingRecordRecycleView.adapter = adapter
        adapter?.submitList(list)






        return root
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}