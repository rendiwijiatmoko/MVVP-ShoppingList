package com.rendiwijiatmoko.shoppinglist.ui.shoppinglist


import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.rendiwijiatmoko.shoppinglist.R
import com.rendiwijiatmoko.shoppinglist.data.db.entities.ShoppingItem

class AddShoppingItemDialog(
    context: Context,
    var addDialogListener: AddDialogListener
):AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_item)

        val tvAdd = findViewById<Button>(R.id.tvAdd)
        val tvCancle = findViewById<Button>(R.id.tvCancle)
        val etName = findViewById<EditText>(R.id.etName)
        val etAmount = findViewById<EditText>(R.id.etAmount)

        tvAdd?.setOnClickListener{
            val name = etName?.text.toString()
            val amount = etAmount?.text.toString()

            if (name.isEmpty() || amount.isEmpty()){
                Toast.makeText(context,"Please enter the field", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val item = ShoppingItem(name, amount.toInt())
            addDialogListener.onAddButtonCliked(item)
            dismiss()
        }

        tvCancle?.setOnClickListener{
            cancel()
        }
    }

}