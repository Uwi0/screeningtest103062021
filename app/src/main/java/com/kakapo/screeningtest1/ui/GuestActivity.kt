package com.kakapo.screeningtest1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.kakapo.screeningtest1.R
import com.kakapo.screeningtest1.adapter.GuestAdapter
import com.kakapo.screeningtest1.databinding.ActivityGuestBinding
import com.kakapo.screeningtest1.model.GuestData
import com.kakapo.screeningtest1.model.service.GuestRepo
import com.kakapo.screeningtest1.utils.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GuestActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityGuestBinding
    private lateinit var mGuest: GuestAdapter
    private var guestList: ArrayList<GuestData>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityGuestBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setupToolbar()
        getData()
        initialiseAdapter()
    }

    private fun setupToolbar(){
        setSupportActionBar(mBinding.toolbarGuest)
        supportActionBar?.let {
            it.title = resources.getString(R.string.guest)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mBinding.toolbarGuest.setNavigationOnClickListener {
            onBackPressed()
            finish()
        }
    }

    private fun initialiseAdapter(){
        mBinding.rvGuest.layoutManager = GridLayoutManager(this, 2)
        mGuest = GuestAdapter(this)
        mBinding.rvGuest.adapter = mGuest
        mBinding.rvGuest.setHasFixedSize(true)
    }

    private fun getData(){
        val postServices = GuestRepo.create()
        postServices.getGuest().enqueue(object : Callback<List<GuestData>>{
            override fun onResponse(
                call: Call<List<GuestData>>,
                response: Response<List<GuestData>>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    Log.d("reponse", "response ${data?.size}")
                    val listData = arrayListOf<GuestData>()
                    data?.map {
                        Log.d("response", "response $it")
                        val guestData = GuestData(it.id, it.name, it.birthdate)
                        listData.add(guestData)
                    }
                    guestList = listData
                    val imageList = Constant.guestImageProfile()
                    mGuest.guestList(guestList!!, imageList)
                }
            }

            override fun onFailure(call: Call<List<GuestData>>, t: Throwable) {
                Log.e("tag", "errornya ${t.message}")
            }

        })
    }

}