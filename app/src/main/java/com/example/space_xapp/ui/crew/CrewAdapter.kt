package com.example.space_xapp.ui.crew

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.space_xapp.R
import com.example.space_xapp.data.Crew
import com.example.space_xapp.databinding.SingleCrewItemLayoutBinding

class CrewAdapter(private val listener: OnItemClickListener) :
    ListAdapter<Crew, CrewAdapter.ViewHolder>(CrewComparator()) {


    inner class ViewHolder(private val binding: SingleCrewItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(crew: Crew) {
            binding.apply {

                crewName.text = crew.name
                crewStatus.text = crew.status
                crewAgency.text = crew.agency

                wikipediaLink.setOnClickListener {
                    val uri = Uri.parse(crew.wikipedia)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    root.context.startActivity(intent)
                }

                Glide
                    .with(crewImage)
                    .load(crew.image)
                    .placeholder(R.drawable.ic_person_icon)
                    .error(R.drawable.ic_person_icon)
                    .into(crewImage)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            SingleCrewItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curItem = getItem(position)
        if (curItem != null) {
            holder.bind(curItem)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(crew: Crew)
    }

    class CrewComparator : DiffUtil.ItemCallback<Crew>() {
        override fun areItemsTheSame(oldItem: Crew, newItem: Crew): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Crew, newItem: Crew): Boolean {
            return oldItem == newItem
        }

    }

}