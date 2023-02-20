package com.example.taskapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.taskapp.databinding.FragmentNewTaskBinding
import com.example.taskapp.databinding.NewTaskItemBinding

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskHolder>() {

    private var taskList = arrayListOf<TaskModel>()

    fun addTask(taskModel: TaskModel) {
        taskList.add(taskModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        return TaskHolder(
            NewTaskItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bind(taskList[position])
    }

    inner class TaskHolder(private var binding: NewTaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(taskModel: TaskModel) {
            binding.tvTitleItem.text = taskModel.title
            binding.tvDescItem.text = taskModel.desc
        }
    }

}