package com.example.taskapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.databinding.NewTaskItemBinding

class TaskAdapter(var onLongClick : (Int)->Unit) : RecyclerView.Adapter<TaskAdapter.TaskHolder>() {

    private var taskList = arrayListOf<TaskModel>()

    fun addTask(taskModel: TaskModel) {
        taskList.add(taskModel)
    }

    fun addTasksFromRoom(list: List<TaskModel>){
        taskList = list as ArrayList<TaskModel>
        notifyDataSetChanged()
    }

    fun getTask(pos:Int):TaskModel{
        return taskList[pos]
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

            itemView.setOnLongClickListener{

                onLongClick(adapterPosition)

                return@setOnLongClickListener true
            }
        }
    }

}