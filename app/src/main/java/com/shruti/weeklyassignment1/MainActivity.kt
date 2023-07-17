package com.shruti.weeklyassignment1

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.shruti.weeklyassignment1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: ArrayAdapter<String>

    var item = arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, item)
        binding.listview.adapter = adapter
        binding.fabButton.setOnClickListener {
            var dialog = Dialog(this)
            dialog.setContentView(R.layout.customdialog)
            var name = dialog.findViewById<TextView>(R.id.tvname)
            var etname = dialog.findViewById<EditText>(R.id.etname)
            var btadd = dialog.findViewById<Button>(R.id.btadd)
            btadd.setOnClickListener {
               if (etname.text.toString().isNullOrEmpty()){
                   etname.error = "Enter name"
               }
                else {
                    item.add(etname.text.toString())
                   dialog.dismiss()
                   adapter.notifyDataSetChanged()
               }
            }
            dialog.show()
        }
        binding.listview.setOnItemClickListener { adapterView, view, position, l ->
        AlertDialog.Builder(this)
            .setTitle("Do you want to update")
            .setPositiveButton("Update"){_,_->
                Toast.makeText(this,"Update",Toast.LENGTH_SHORT).show()
                var dialog = Dialog(this)
                dialog.setContentView(R.layout.customupdate)
                var tvupdate = dialog.findViewById<TextView>(R.id.tvupdate)
                var etupdate = dialog.findViewById<EditText>(R.id.etupdate)
                var btupdate = dialog.findViewById<Button>(R.id.btupdate)
                btupdate.setOnClickListener {
                    if (etupdate.text.toString().isNullOrEmpty())
                    {
                        etupdate.error = "It is not updated"
                    }
                    else{
                        item.set(position,etupdate.text.toString())
                        dialog.dismiss()
                        adapter.notifyDataSetChanged()
                    }
                }
                dialog.show()
            }
            .setNegativeButton("Delete")   { _,_->
                Toast.makeText(this,"Delete",Toast.LENGTH_SHORT).show()
                item.removeAt(position)
                adapter.notifyDataSetChanged()
            }
            .setNeutralButton("Cancel"){ _,_->
                Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show()
            }
            .setCancelable(false)
            .show()
        }

    }
}