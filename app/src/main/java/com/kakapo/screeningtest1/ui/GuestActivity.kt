package com.kakapo.screeningtest1.ui

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.kakapo.screeningtest1.R
import com.kakapo.screeningtest1.adapter.GuestAdapter
import com.kakapo.screeningtest1.databinding.ActivityGuestBinding
import com.kakapo.screeningtest1.fragment.MapsFragment
import com.kakapo.screeningtest1.model.GuestData
import com.kakapo.screeningtest1.model.service.GuestRepo
import com.kakapo.screeningtest1.utils.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GuestActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityGuestBinding
    private lateinit var mGuest: GuestAdapter
    private lateinit var mProgressDialog: Dialog

    private var guestList: ArrayList<GuestData>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityGuestBinding.inflate(layoutInflater)
        mProgressDialog = Dialog(this)
        setContentView(mBinding.root)
        setupToolbar()
        initialiseAdapter()
        mBinding.rlGuest.setOnRefreshListener {
            initialiseAdapter()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_guest, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_map ->{
                mBinding.rvGuest.visibility = View.GONE
                val manager = supportFragmentManager
                val transaction = manager.beginTransaction()
                transaction.add(R.id.map, MapsFragment())
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
        return super.onOptionsItemSelected(item)
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
        getData()
        mBinding.rvGuest.layoutManager = GridLayoutManager(this, 2)
        mGuest = GuestAdapter(this)
        mBinding.rvGuest.adapter = mGuest
        mBinding.rvGuest.setHasFixedSize(true)
    }

    private fun getData(){
        val postServices = GuestRepo.create()
        showProgressDialog()
        postServices.getGuest().enqueue(object : Callback<List<GuestData>>{
            override fun onResponse(
                call: Call<List<GuestData>>,
                response: Response<List<GuestData>>
            ) {
                hideProgressDialog()
                if (mBinding.rlGuest.isRefreshing){
                    mBinding.rlGuest.isRefreshing = false
                }
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
                hideProgressDialog()
                if (mBinding.rlGuest.isRefreshing){
                    mBinding.rlGuest.isRefreshing = false
                }
                Log.e("tag", "errornya ${t.message}")
            }

        })
    }

    private fun showProgressDialog(){
        mProgressDialog.let {
            it.setContentView(R.layout.dialog_custom_progress)
            it.show()
        }
    }

    private fun hideProgressDialog(){
        mProgressDialog.dismiss()
    }


}