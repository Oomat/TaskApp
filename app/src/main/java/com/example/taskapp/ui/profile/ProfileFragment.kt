package com.example.taskapp.ui.profile

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.taskapp.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding


    private val selectImageFromGalleryResult =
        registerForActivityResult(ActivityResultContracts.GetContent()){uri:Uri? ->
    uri?.let{
        binding.image.setImageURI(uri)

        Glide
            .with(requireContext())
            .load(uri)
            .circleCrop()
            .into(binding.image )

    }
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        initViews()
        initListener()
        return binding.root
    }

    private fun initListener() {
        binding.image.setOnClickListener{
        selectImageFromGalleryResult.launch("image/*")

        }

    }

    private fun initViews() {

    }
}