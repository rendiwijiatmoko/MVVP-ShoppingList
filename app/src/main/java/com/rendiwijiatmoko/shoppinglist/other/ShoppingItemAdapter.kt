package com.rendiwijiatmoko.shoppinglist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rendiwijiatmoko.shoppinglist.R
import com.rendiwijiatmoko.shoppinglist.data.db.entities.ShoppingItem
import com.rendiwijiatmoko.shoppinglist.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter (
    var item: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item,parent, false)
        return ShoppingViewHolder(view)

    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val item = holder.itemView
        val curShoppingItem = this.item[position]
        val tvName = holder.itemView.findViewById<TextView>(R.id.tvName)
        val tvAmount = item.findViewById<TextView>(R.id.tvAmount)
        val ivDelete = item.findViewById<Button>(R.id.ivDelete)
        val ivMinus = item.findViewById<Button>(R.id.ivMinus)
        val ivPlus = item.findViewById<Button>(R.id.ivPlus)

        tvName.text = curShoppingItem.name
        tvAmount.text = curShoppingItem.amount.toString()

        ivPlus.setOnClickListener{
            curShoppingItem.amount++
            viewModel.upsert(curShoppingItem)
        }
        ivMinus.setOnClickListener{
            if (curShoppingItem.amount > 0){
                curShoppingItem.amount--
                viewModel.upsert(curShoppingItem)
            }
        }


    }

    inner class  ShoppingViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)
}