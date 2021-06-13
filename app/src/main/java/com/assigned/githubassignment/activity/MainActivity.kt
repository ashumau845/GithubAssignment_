package com.assigned.githubassignment.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assigned.githubassignment.R
import com.assigned.githubassignment.adapter.UserAdapter
import com.assigned.githubassignment.model.UserModel
import com.assigned.githubassignment.network.RetrofitService
import com.assigned.githubassignment.viewmodels.UserRepository
import com.assigned.githubassignment.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(),UserAdapter.OnClickkey {

    private var crewRepository: UserRepository? = null
    private var crewList: ArrayList<UserModel> = ArrayList()
    private var crewViewModel: UserViewModel? = null
    private var userAdapter:UserAdapter?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        crewRepository = UserRepository(application)
        crewViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        networkRequest()

        crewViewModel?.allCrew?.observe(this,
            { crewList ->
                if (crewList != null) {
                    var mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
                    recyclerview.layoutManager = mLayoutManager
                    userAdapter = UserAdapter(applicationContext, crewList,this)
                    recyclerview.adapter = userAdapter
                } else {
                    Toast.makeText(applicationContext, "No Data found", Toast.LENGTH_SHORT).show()
                }
            })



    }

    private fun networkRequest() {
        val networkCallback = RetrofitService.apiInterface.getAllCrews()
        networkCallback.enqueue(object : Callback<ArrayList<UserModel>>{
            override fun onResponse(
                call: Call<ArrayList<UserModel>>,
                response: Response<ArrayList<UserModel>>
            ) {
                if (response.isSuccessful) {
                    crewRepository!!.insert(response.body())
                    crewList = response.body()!!
                }
            }

            override fun onFailure(call: Call<ArrayList<UserModel>>, t: Throwable) {

            }
        })
    }

    override fun OnClickonkey(position: Int) {
        var intent = Intent(this,DetailActivity::class.java)
        intent.putParcelableArrayListExtra(applicationContext.resources.getString(R.string.send_data),crewList)
        intent.putExtra(applicationContext.resources.getString(R.string.position),position)
        startActivity(intent)
    }


}

