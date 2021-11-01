package com.example.space_xapp.ui.info

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.space_xapp.R
import com.example.space_xapp.databinding.FragmentInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoFragment : Fragment(R.layout.fragment_info) {

    private var _binding:FragmentInfoBinding?=null
    private val binding:FragmentInfoBinding get() = _binding!!

    private val viewModel:InfoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding= FragmentInfoBinding.bind(view)


        binding.apply {
            Glide
                .with(logo)
                .load(getString(R.string.spaceXImageUrl))
                .listener(object :RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar1.visibility = View.GONE
                        if (e != null) {
                            errorMsg.text=e.localizedMessage
                        }
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar1.visibility = View.GONE
                        group.visibility=View.VISIBLE

                        return false
                    }
                })
                .into(logo)

            viewModel.info.observe(viewLifecycleOwner){ result->

                var companyInfo=result.data

                if (companyInfo != null) {
                    name.text=companyInfo.name
                    founded.text= companyInfo.founded.toString()
                    founder.text= companyInfo.founder
                    ceo.text=companyInfo.ceo
                    noOfEmployees.text=companyInfo.employees.toString()

                    headquarters.text=
                        "${companyInfo.headquarters.address} , ${companyInfo.headquarters.city} , ${companyInfo.headquarters.state}"

                    summary.text=companyInfo.summary

                    links.text=
                        "${companyInfo.links.website} \n\n ${companyInfo.links.twitter} \n\n ${companyInfo.links.flickr}"

                } else {
                    Toast.makeText(requireContext(),"Something went wrong",Toast.LENGTH_SHORT).show()
                }


            }
        }
    }

}