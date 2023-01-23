package com.example.recyclerviewinkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter() : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent,false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.title.text = item.title
        holder.cbDone.isChecked = item.isChecked
        holder.removeTodo.setOnClickListener{
            if (differ.currentList.size == 1){
                differ.submitList(mutableListOf())
            }
            else{
                val list = copyList(differ.currentList, item)
                differ.submitList(list)
            }
        }
    }

    private fun copyList(currentList: List<Todo>, removeThis: Todo): MutableList<Todo>? {
        val res = mutableListOf<Todo>()
        for (item in currentList){
            if (item == removeThis) continue
            res.add(item)
        }

        return res
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


//    DiffUtil -
    private val differCallback = object : DiffUtil.ItemCallback<Todo>(){
//        Called to check whether two objects represent the same item :
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.title == newItem.title && oldItem.isChecked == newItem.isChecked
        }

//        Called to check whether two items have the same data :
        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)








//    Conventional way -

//    Note - onCreateViewHolder creates the view which is to be shown. For example, when the user scrolls down, the new recyclerView element is to be shown and via this method, that view is created.

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
////  We need to inflate out item_todo.xml here to get it as a view that we can access in Kotlin file.
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
//        //  If we set attachToRoot: true, then our app will crash as if we do so, then the view would automatically attach the layout to the root view. In case of recyclerViews, we don't want that.
//        return TodoViewHolder(view)
//
//    }
//
////    Note - This function binds the data to the recyclerView.
//    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
////  we need this holder to access the views inside the viewHolder. Position is the index of the particular view that we are binding.
//        holder.itemView.apply {
//            val textView: TextView = findViewById(R.id.tv_title)
//            textView.text = todos[position].title
//            val mCbDone = findViewById<CheckBox>(R.id.cbDone)
//            mCbDone.isChecked = todos[position].isChecked
//        }
//    }
//  Returns the size of recyclerView.
//    override fun getItemCount(): Int {
//        return todos.size // Never return 0 here otherwise nothing will be displayed.
//    }

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title : TextView = itemView.findViewById(R.id.tv_title)
        val cbDone : CheckBox = itemView.findViewById(R.id.cbDone)
        val removeTodo : Button = itemView.findViewById(R.id.btnRemoveTodo)
    }
}