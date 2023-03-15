package com.example.taskapp.ui.profile


import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.taskapp.Extenssions.loadImage
import com.example.taskapp.Extenssions.showName
import com.example.taskapp.Extenssions.showToast
import com.example.taskapp.databinding.FragmentProfileBinding
import com.example.taskapp.utils.Preferences


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding




    private val selectImageFromGalleryResult =
        registerForActivityResult(ActivityResultContracts.GetContent()){uri:Uri? ->
    uri?.let{
        binding.image.setImageURI(uri)



        showToast("Успешно")
        binding.image.loadImage(uri.toString())
        binding.etName.showName("sadbjbh")

        Preferences(requireContext()).imgProfile = uri.toString()
        Preferences(requireContext()).etName.toString()


    }
        }


    override fun onCreateView(
        inflater: LayoutInflater,
         container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        binding.image.loadImage(Preferences(requireContext()).imgProfile.toString())
    }
}