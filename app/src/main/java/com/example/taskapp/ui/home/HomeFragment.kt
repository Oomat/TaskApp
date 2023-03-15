package com.example.taskapp.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskapp.App
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentHomeBinding
import com.example.taskapp.ui.home.new_task.NewTaskFragment

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var taskAdapter: TaskAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)


        initViews()
        initListener()
        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.task_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_sortAZ) {
            val items = arrayOf("A-Z","Z-A","Date")
            val builder = AlertDialog.Builder(requireContext())
            with(builder)
            {
                setTitle("Sort by")
                setItems(items) { dialog, which ->
                    when(which){
                        0->{
                           taskAdapter.addTasksFromRoom(App.db.dao().getAllTasksByAZ())
                            dialog.dismiss()
                        }
                        1->{
                            taskAdapter.addTasksFromRoom(App.db.dao().getAllTasksByZA())
                            dialog.dismiss()

                        }
                        2->{
                           taskAdapter.addTasksFromRoom(App.db.dao().getAllTasksByZA())
                            dialog.dismiss()

                        }
                    }

                    dialog.dismiss()
                }

                show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initListener() {
        binding.fabHome.setOnClickListener {
            findNavController().navigate(R.id.navigation_new_task)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskAdapter = TaskAdapter(this::onLongClickListener)
    }

    private fun initViews() {

        binding.rvHome.layoutManager = LinearLayoutManager(context)
        binding.rvHome.adapter = taskAdapter

        /*setFragmentResultListener(NewTaskFragment.TASK_KEY,){ _, bundle ->
            Log.e("ololo", "initViews: "
                    +bundle.getString("title")+
                    bundle.getString("desc") )

            val title = bundle.getString("title")
            val desc = bundle.getString("desc")*/
        getDataFromLocalDB()

    }

    private fun getDataFromLocalDB() {
        val listOfTasks = App.db.dao().getAllTasks().reversed()
        taskAdapter.addTasksFromRoom(listOfTasks)
    }

    private fun onLongClickListener(position: Int) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Deleting")
        builder.setMessage("Are you sure delete " + taskAdapter.getTask(position).title)

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            App.db.dao().deleteTask(taskAdapter.getTask(position))
            getDataFromLocalDB()
        }

        builder.setNegativeButton(android.R.string.no) { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

}