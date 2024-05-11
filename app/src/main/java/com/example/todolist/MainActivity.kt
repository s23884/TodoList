package com.example.todolist

import ShoppingAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter
    private lateinit var shoppingAdapter: ShoppingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = TodoAdapter(mutableListOf())
        shoppingAdapter = ShoppingAdapter(mutableListOf())

        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        rvShoppingItems.adapter = shoppingAdapter
        rvShoppingItems.layoutManager = LinearLayoutManager(this)

        btnAddTodo.setOnClickListener {
            val todoTitle = etTodoTitle.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                etTodoTitle.text.clear()
            }
        }

        btnAddShoppingItem.setOnClickListener {
            val shoppingItemName = etShoppingItem.text.toString()
            if (shoppingItemName.isNotEmpty()) {
                val shoppingItem = ShoppingItem(shoppingItemName)
                shoppingAdapter.addShoppingItem(shoppingItem)
                etShoppingItem.text.clear()
            }
        }

        btnDeleteDoneTodos.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }

        btnDeletePurchasedItems.setOnClickListener {
            shoppingAdapter.deletePurchasedItems()
        }

        val lineChart = findViewById<LineChart>(R.id.lineChart)

        val entries = listOf(Entry(0f, 20f), Entry(1f, 21f), Entry(2f, 22f), Entry(3f, 23f))
        val dataSet = LineDataSet(entries, "Label")
        val lineData = LineData(dataSet)
        lineChart.data = lineData
        lineChart.invalidate()
    }
}
