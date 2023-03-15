package com.example.taskapp.ui.onBoard


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.taskapp.databinding.FragmentOnBoardPageBinding
import com.example.taskapp.utils.Preferences


@Suppress("DEPRECATION")
class OnBoardPageFragment(
    private var listenerScip: () -> Unit,
    private  var listenerNext: () -> Unit
) : Fragment() {
    private lateinit var binding: FragmentOnBoardPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardPageBinding.inflate(inflater, container, false)
        initViews()
        initListener()

        return binding.root
    }

    private fun initListener() {
        binding.btnNext.setOnClickListener {
            listenerNext.invoke()
        }
        binding.btnSkip.setOnClickListener {
            listenerScip.invoke()
        }
        binding.btnStart.setOnClickListener {
            findNavController().navigateUp()
            Preferences(requireContext()).board = true
        }
    }

    private fun initViews() {
        val data = arguments?.getSerializable("OnBoard") as BoardModel
        data.image?.let { binding.imageBord.setImageResource(it) }
        binding.tvTitleBoard.text = data.title
        binding.tvDescBoard.text = data.desc

        binding.btnSkip.isVisible = data.isLast == false
        binding.btnNext.isVisible = data.isLast == false

        binding.btnStart.isVisible = data.isLast == true
    }
}