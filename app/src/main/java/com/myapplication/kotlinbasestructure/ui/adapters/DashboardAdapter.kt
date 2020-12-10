package com.myapplication.kotlinbasestructure.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.kotlinbasestructure.data.interfaces.ClickListener
import com.myapplication.kotlinbasestructure.data.model.TracksApiResponse
import com.myapplication.kotlinbasestructure.databinding.DashboardAdapterItemBinding
import com.myapplication.kotlinbasestructure.utility.Utility.loadImage
import kotlinx.android.synthetic.main.dashboard_adapter_item.view.*

class DashboardAdapter(
    private val context: Context,
    private var list: List<TracksApiResponse.Result>,
    private val listener:ClickListener
) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DashboardAdapterItemBinding.inflate(LayoutInflater.from(context))
        return ViewHolder( binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dashboardAdapterItemBinding.track.text = list[position].trackName
        holder.dashboardAdapterItemBinding.image.loadImage(list[position].artworkUrl100)
        holder.dashboardAdapterItemBinding.description.text = list[position].collectionName
        holder.dashboardAdapterItemBinding.rootLayout.setOnClickListener { v: View? -> listener.onItemClick(position,v) }
    }

    inner class ViewHolder(var dashboardAdapterItemBinding: DashboardAdapterItemBinding) :
        RecyclerView.ViewHolder(dashboardAdapterItemBinding.root)
}
