package com.assigned.githubassignment.model


import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


/*
@Entity(tableName = "crew")
data  class UserModel(
    @PrimaryKey(autoGenerate = true)
    val crew_id: Int,
    val login: String,
    val node_id: String,
    val avatar_url: String,
    val avatar_id: String,
    val url: String,
    val html_url: String,
) : List<UserModel>
*/


@Entity(tableName = "crew")
class UserModel() :Parcelable {

    @PrimaryKey(autoGenerate = true)
    var crew_id: Int? = null
    var login: String? = null
    var id = 0
    var node_id: String? = null
    var avatar_url: String? = null
    var gravatar_id: String? = null
    var url: String? = null
    var html_url: String? = null

    constructor(parcel: Parcel) : this() {
        login = parcel.readString()
        id = parcel.readInt()
        node_id = parcel.readString()
        avatar_url = parcel.readString()
        gravatar_id = parcel.readString()
        url = parcel.readString()
        html_url = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(login)
        parcel.writeInt(id)
        parcel.writeString(node_id)
        parcel.writeString(avatar_url)
        parcel.writeString(gravatar_id)
        parcel.writeString(url)
        parcel.writeString(html_url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }
}










