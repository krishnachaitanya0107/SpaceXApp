package com.example.space_xapp.ui.ship

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.space_xapp.R
import com.example.space_xapp.databinding.FragmentShipDetailsBinding

class ShipDetailsFragment : Fragment(R.layout.fragment_ship_details) {

    private var _binding:FragmentShipDetailsBinding?=null
    private val binding:FragmentShipDetailsBinding get() = _binding!!

    val args by navArgs<ShipDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding= FragmentShipDetailsBinding.bind(view)

        binding.apply {
            val ship=args.ship

            name.text = ship.name
            status.text = if(ship.active){
                status.setTextColor(Color.GREEN)
                "Active"
            } else {
                status.setTextColor(Color.RED)
                "InActive"
            }
            yearBuilt.text = ship.year_built.toString()

            var shipRoles=""
            for(i in 0 until ship.roles.size){
                shipRoles += if(i==ship.roles.size-1){
                    ("${ship.roles[i]}\n")
                } else {
                    ("${ship.roles[i]} ,\n")
                }
            }
            roles.text=shipRoles

            wikipediaLink.setOnClickListener {
                val uri = Uri.parse(ship.link)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                root.context.startActivity(intent)
            }

            Glide
                .with(image)
                .load(ship.image)
                .placeholder(R.drawable.ic_person_icon)
                .listener(object :
                    RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility= View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        name.visibility=View.VISIBLE
                        status.visibility=View.VISIBLE
                        yearBuilt.visibility=View.VISIBLE
                        roles.visibility=View.VISIBLE
                        wikipediaLink.visibility=View.VISIBLE

                        return false
                    }
                })
                .into(image)

        }

    }

}