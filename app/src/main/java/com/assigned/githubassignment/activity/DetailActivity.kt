package com.assigned.githubassignment.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.assigned.githubassignment.R
import com.assigned.githubassignment.model.UserModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlin.math.log

class DetailActivity : AppCompatActivity() {

    private var crewList: ArrayList<UserModel> = ArrayList()
    private var position:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        received_data()
    }

    private fun received_data() {
        if(intent.hasExtra(applicationContext.resources.getString(R.string.send_data))){
            crewList = intent.getParcelableArrayListExtra<UserModel>(applicationContext.resources.getString(R.string.send_data))!!
            position = intent.getIntExtra(applicationContext.resources.getString(R.string.position),0)

            txt_login.text = crewList[position].login.toString()
            txt_id.text = crewList[position].id.toString()
            txt_nodeId.text = crewList[position].node_id.toString()
            txt_avatar.text = crewList[position].avatar_url.toString()
            txt_gravatar.text = crewList[position].gravatar_id.toString()
            txt_url.text = crewList[position].url.toString()
            txt_html_url.text = crewList[position].html_url.toString()

        }
    }
}