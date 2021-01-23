package com.rendiwijiatmoko.shoppinglist.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rendiwijiatmoko.shoppinglist.R
import com.rendiwijiatmoko.shoppinglist.data.db.ShoppingDatabase
import com.rendiwijiatmoko.shoppinglist.data.db.entities.ShoppingItem
import com.rendiwijiatmoko.shoppinglist.data.repositories.ShoppingRepository
import com.rendiwijiatmoko.shoppinglist.other.ShoppingItemAdapter

class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val rvShoppingItems = findViewById<RecyclerView>(R.id.rvShoppingItems)
        val fab = findViewById<FloatingActionButton>(R.id.fab)

        val database = ShoppingDatabase(this )
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProviders.of(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)
        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        viewModel.getAllShopping().observe(this, Observer {
            adapter.item = it
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener{
            AddShoppingItemDialog(this,
            object : AddDialogListener{
                override fun onAddButtonCliked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }
    }
}