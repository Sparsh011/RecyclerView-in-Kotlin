package com.example.recyclerviewinkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var todoList = mutableListOf(
            Todo("Hello World", false),
            Todo("Do Android", false),
            Todo("Do LeetCode", false)
        )
        val adapter = TodoAdapter(todoList)
        val mRecyclerView = findViewById<RecyclerView>(R.id.rv_TODO)
        mRecyclerView.adapter = adapter

        mRecyclerView.layoutManager = LinearLayoutManager(this)

        val btnTodo = findViewById<Button>(R.id.btnAddTODO)

        btnTodo.setOnClickListener{
            val etTodo = findViewById<EditText>(R.id.et_TODO)
            val title = etTodo.text.toString()
            val todo = Todo(title, false)
            todoList.add(todo)
//            Till here, we have created a new view for todoList. But to add it to the recyclerView -
            adapter.notifyItemInserted(todoList.size-1) // Passing position as todoList.size - 1 because in a list, data is always added at the end.
            etTodo.setText("")
        }
    }
}