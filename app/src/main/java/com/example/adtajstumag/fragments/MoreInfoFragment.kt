package com.example.adtajstumag.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.adtajstumag.ADTAJSTUMAG
import com.example.adtajstumag.databinding.FragmentMoreInfoBinding

class MoreInfoFragment : Fragment() {

    private var binding: FragmentMoreInfoBinding? = null

    private val args: MoreInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMoreInfoBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMoreInfo()
    }

    private fun getMoreInfo(){

        binding!!.tvTitle.text = args.title
        binding!!.tvDescription.text = args.description
        binding!!.tvPublishDate.text = args.date.toString()
        ADTAJSTUMAG().adtajstumag(args.image.toString(), binding!!.imgItem)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}