package com.example.recyclerviewinkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    var todos: List<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

//    Note - onCreateViewHolder creates the view which is to be shown. For example, when the user scrolls down, the new recyclerView element is to be shown and via this method, that view is created.

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
//  We need to inflate out item_todo.xml here to get it as a view that we can access in Kotlin file.
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        //  If we set attachToRoot: true, then our app will crash as if we do so, then the view would automatically attach the layout to the root view. In case of recyclerViews, we don't want that.
        return TodoViewHolder(view)

    }

//    Note - This function binds the data to the recyclerView.
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
//  we need this holder to access the views inside the viewHolder. Position is the index of the particular view that we are binding.
        holder.itemView.apply {
            val textView: TextView = findViewById(R.id.tv_title)
            textView.text = todos[position].title
            val mCbDone = findViewById<CheckBox>(R.id.cbDone)
            mCbDone.isChecked = todos[position].isChecked
        }
    }
//  Returns the size of recyclerView.
    override fun getItemCount(): Int {
        return todos.size // Never return 0 here otherwise nothing will be displayed.
    }

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}