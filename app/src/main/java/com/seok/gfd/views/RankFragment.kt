package com.seok.gfd.views


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.seok.gfd.R
import com.seok.gfd.adapter.TRCommitListAdapter
import com.seok.gfd.utils.SharedPreferencesForUser
import com.seok.gfd.viewmodel.RankFragmentViewModel
import kotlinx.android.synthetic.main.fragment_rank.*

/**
 * A simple [Fragment] subclass.
 */
class RankFragment : Fragment() {
    private lateinit var sharedPreferencesForUser: SharedPreferencesForUser
    private lateinit var rankViewModel: RankFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initViewModelFun()
    }
    private fun init(){
        rankViewModel = ViewModelProviders.of(this).get(RankFragmentViewModel::class.java)
        sharedPreferencesForUser = SharedPreferencesForUser(this.activity?.application!!)
        rv_rank.layoutManager = LinearLayoutManager(activity)
        rv_rank.addItemDecoration(DividerItemDecoration(this.context, 1))
        rv_rank.setHasFixedSize(true)
        img_rank_sync.setOnClickListener {
            rankViewModel.getTodayRankCommitList()
        }
        rankViewModel.getTodayRankCommitList()
    }
    private fun initViewModelFun(){
        rankViewModel.serverResult.observe(this, Observer {
            rankViewModel.getTodayRankCommitList()
        })

        rankViewModel.rankList.observe(this, Observer {
            val adapter = TRCommitListAdapter(this.activity!!.application, it, this)
            rv_rank.adapter = adapter
            Glide.with(this).load(sharedPreferencesForUser.getValue(getString(R.string.user_image))).apply(
                RequestOptions.circleCropTransform()).into(img_rv_profile)
            this.activity!!.runOnUiThread {
                for(commitNumber in it.indices){
                    if(it[commitNumber].uid == sharedPreferencesForUser.getValue(getString(R.string.user_id))){
                        rv_rank.scrollToPosition(commitNumber)
                    }
                }
            }
        })
    }
}