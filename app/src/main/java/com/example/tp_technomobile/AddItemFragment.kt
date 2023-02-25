package com.example.tp_technomobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_technomobile.recycleview.CustomAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AddItemFragment : Fragment() {

    private var titleTextInput: EditText? = null
    private var descTextInput: EditText? = null
    private var buttonAdd: Button? = null
    private var id: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        titleTextInput = view.findViewById<EditText>(R.id.titleTextInput)
        descTextInput = view.findViewById<EditText>(R.id.descTextInput)
        var addButton = view.findViewById<Button>(R.id.addOneItem)

        arguments?.let {
            if (it.get(KEY_ID)!=null) {
                id = it.getInt(KEY_ID)
            }
            titleTextInput?.setText(it.getString(KEY_TITLE))
            descTextInput?.setText(it.getString(KEY_DESCRIPTION))
        }

        addButton?.setOnClickListener {
            val bundle = Bundle()
            id?.let {
                bundle.putInt(KEY_ID,it)
            }
            bundle.putString(KEY_TITLE,titleTextInput?.toString())
            bundle.putString(KEY_DESCRIPTION,descTextInput?.toString())

            setFragmentResult(KEY_REQUEST,bundle)
            findNavController().popBackStack()
            Toast.makeText(context, "Item added Successfully", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        val KEY_REQUEST: String = "todorequest"
        val KEY_ID: String = "todoid"
        val KEY_TITLE: String = "todotitle"
        val KEY_DESCRIPTION: String = "tododescription"
    }
}