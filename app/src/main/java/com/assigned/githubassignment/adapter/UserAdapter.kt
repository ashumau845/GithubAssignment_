package com.assigned.githubassignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assigned.githubassignment.R
import com.assigned.githubassignment.model.UserModel
import kotlinx.android.synthetic.main.cell_user.view.*

class UserAdapter(
    val context: Context,
    val useridlist: List<UserModel>,
    val Itemlistener: OnClickkey
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var listener: OnClickkey?=null

    interface OnClickkey{
        fun OnClickonkey(position: Int)
    }

    init {
        this.listener = Itemlistener
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cell_user, parent, false)
        return UserViewHolder(view)

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.itemView.apply {
            txtuser.text = useridlist[position].login
            header_title.setOnClickListener{
                if(listener != null){
                    listener?.OnClickonkey(position)
                }


            }
        }
    }

    override fun getItemCount(): Int {
        return useridlist.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}