package com.example.taskapp.ui.home.new_task

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.taskapp.App
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentNewTaskBinding
import com.example.taskapp.ui.home.TaskModel


class NewTaskFragment : Fragment() {
    private lateinit var binding: FragmentNewTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewTaskBinding.inflate(inflater, container, false)
        initViews()
        initListener()
        return binding.root
    }

    private fun initListener() {
        binding.btnSave.setOnClickListener {
            /* setFragmentResult(TASK_KEY, bundleOf(
                 "title" to binding.etTitle.text.toString(),
                 "desc" to binding.etDescription.text.toString()
             ))*/
            App.db.dao().insert(
                TaskModel(
                    title = binding.etTitle.text.toString(),
                    desc = binding.etDescription.text.toString()
                )
            )
            Log.e("ololo", "Room inserted successfully: ")
            findNavController().navigateUp()
        }
    }

    private fun initViews() {

    }

    companion object {
        const val TASK_KEY = "new task"
    }

}