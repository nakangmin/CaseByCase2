package com.example.casebycase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.casebycase.databinding.ItemRecyclerviewBinding
import com.example.casebycase.dto.riding_record



class RidingRecordAdapter() : ListAdapter<riding_record, RidingRecordAdapter.BookItemViewHolder>(diffUtil) {

    inner class BookItemViewHolder(private val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root){


        fun bind(item:riding_record){
            binding.itemRidingData1.text = item.test1
            binding.itemRidingData2.text = item.test2
            binding.itemRidingData3.text = item.test3
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RidingRecordAdapter.BookItemViewHolder {
        return BookItemViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(
            parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RidingRecordAdapter.BookItemViewHolder, position: Int) {

        holder.bind(currentList[position])

    }


    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<riding_record>(){
            override fun areItemsTheSame(oldItem: riding_record, newItem: riding_record): Boolean = oldItem == newItem

            override fun areContentsTheSame(oldItem: riding_record, newItem: riding_record): Boolean = oldItem == newItem



        }
    }

}





