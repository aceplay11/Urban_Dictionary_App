package com.example.urbandictionaryapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.urbandictionaryapp.model.datasource.UrbanDictionary.ResponseList
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.recyclerview_items.view.*

class UrbanDictionaryRecyclerviewAdapter(private val definitionDetails: List<ResponseList>, private val context: Context) :
    Adapter<ViewHolder>() {
    
    

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_items, parent, false))
    }

    override fun getItemCount(): Int {
        return definitionDetails.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.rvTVDefinition.text = definitionDetails[position].definition
        holder.itemView.rvTVThumbsUp.text = definitionDetails[position].thumbsUp.toString()
        holder.itemView.rvTVThumbsDown.text = definitionDetails[position].thumbsDown.toString()

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}