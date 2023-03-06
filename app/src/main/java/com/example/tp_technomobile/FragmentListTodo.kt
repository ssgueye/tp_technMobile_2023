package com.example.tp_technomobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.tp_technomobile.data.MemResponse
import com.example.tp_technomobile.database.AppDatabase
import com.example.tp_technomobile.model.Mem
import com.example.tp_technomobile.recycleview.CustomAdapter
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentListTodo : Fragment() {

    private var dataset = ArrayList<Mem>()
    private var adapter: CustomAdapter? = null
    private var recyclerView: RecyclerView? = null
    private var database:AppDatabase?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(AddItemFragment.KEY_REQUEST) { requestKey, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            var todoId :Int? = null
            if (bundle.get(AddItemFragment.KEY_ID)!=null) {
                todoId = bundle.getInt(AddItemFragment.KEY_ID)
            }

            val todoTitle = bundle.getString(AddItemFragment.KEY_TITLE)
            //addOrModifyToListAndRefresh(todoId, todoTitle)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView?.layoutManager = LinearLayoutManager(activity)

        var addButton = view.findViewById<Button>(R.id.addButton)

        addButton?.setOnClickListener {
            //dataset.add("Item ${dataset.size+1}")
            goToFragmentAdd(null, null)
        }

        //Initialize database
        database= Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "mem_database"
        ).allowMainThreadQueries().build()

        dataset = database?.memDao()?.getAll() as ArrayList<Mem>

        if(dataset?.isEmpty() == true){
            loadDataFromNetwork()
        }
        else{
            adapter = CustomAdapter(dataset)

            recyclerView?.adapter = adapter
        }

    }

    private fun loadDataFromNetwork() {
        CoroutineScope(Dispatchers.IO).launch {
            // Appel avec l’API
            val client = HttpClient(CIO)
            val response: HttpResponse = client.get("https://api.imgflip.com/get_memes")
            println(response.status)
            val gson = Gson()
            val data: String = response.bodyAsText()
            val memeResponse: MemResponse = gson.fromJson(data, MemResponse::class.java)

            dataset = memeResponse.data.memes as ArrayList<Mem>
            database?.memDao()?.insertAll(dataset)
            client.close()

            withContext(Dispatchers.Main) {

                // Faire un traitement sur le MainThread
                adapter = CustomAdapter(dataset)

                recyclerView?.adapter = adapter
            }

        }
    }

    private fun goToFragmentAdd(position: Int? ,titre: String?) {
        val navController = findNavController()
        val bundle = bundleOf(Pair(AddItemFragment.KEY_ID,position),
            Pair(AddItemFragment.KEY_TITLE,titre))
        navController.navigate(R.id.action_fragmentListTodo_to_addItemFragment)
    }

    /*private fun addOrModifyToListAndRefresh(position:Int?,data: String?) {
        if (position != null){
            dataset.set(position,data!!)
        }else{
            dataset.add(data.toString())
        }
        adapter?.notifyDataSetChanged()
    }*/

}