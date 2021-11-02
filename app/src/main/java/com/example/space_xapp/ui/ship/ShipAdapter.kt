package com.example.space_xapp.ui.ship

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.space_xapp.R
import com.example.space_xapp.data.Ship
import com.example.space_xapp.databinding.SingleShipItemLayoutBinding

class ShipAdapter(private val listener: OnItemClickListener) :
    ListAdapter<Ship, ShipAdapter.ViewHolder>(ShipComparator()) {

    inner class ViewHolder(private val binding: SingleShipItemLayoutBinding) :
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

        fun bind(ship: Ship) {
            binding.apply {
                shipName.text = ship.name

                shipStatus.text = if (ship.active) {
                    shipStatus.setTextColor(Color.GREEN)
                    "Active"
                } else {
                    shipStatus.setTextColor(Color.RED)
                    "InActive"
                }

                shipYearBuilt.text = ship.year_built.toString()

                var roles = ""
                for (i in 0 until ship.roles.size) {
                    roles += if (i == ship.roles.size - 1) {
                        ("${ship.roles[i]}\n")
                    } else {
                        ("${ship.roles[i]} ,\n")
                    }
                }
                shipRoles.text = roles

                if (!ship.link.isNullOrEmpty()) {
                    wikipediaLink.setOnClickListener {
                        val uri = Uri.parse(ship.link)
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        root.context.startActivity(intent)
                    }
                } else {
                    wikipediaLink.visibility = View.GONE
                }

                    Glide
                        .with(shipImage)
                        .load(ship.image)
                        .placeholder(R.drawable.ic_directions_boat_icon)
                        .error(R.drawable.ic_directions_boat_icon)
                        .into(shipImage)

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            SingleShipItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curItem = getItem(position)
        if (curItem != null) {
            holder.bind(curItem)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(ship: Ship)
    }


    class ShipComparator : DiffUtil.ItemCallback<Ship>() {
        override fun areItemsTheSame(oldItem: Ship, newItem: Ship): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Ship, newItem: Ship): Boolean {
            return oldItem == newItem
        }

    }

}