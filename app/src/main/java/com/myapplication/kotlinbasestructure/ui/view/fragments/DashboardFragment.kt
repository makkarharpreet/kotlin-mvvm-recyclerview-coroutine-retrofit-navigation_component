package com.myapplication.kotlinbasestructure.ui.view.fragments

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.myapplication.kotlinbasestructure.R
import com.myapplication.kotlinbasestructure.data.api.ApiService
import com.myapplication.kotlinbasestructure.data.interfaces.ClickListener
import com.myapplication.kotlinbasestructure.data.model.TracksApiResponse
import com.myapplication.kotlinbasestructure.data.repository.DashboardRepository
import com.myapplication.kotlinbasestructure.databinding.FragmentDashboardBinding
import com.myapplication.kotlinbasestructure.network.Resource
import com.myapplication.kotlinbasestructure.ui.adapters.DashboardAdapter
import com.myapplication.kotlinbasestructure.ui.viewmodels.DashboardViewModel

class DashboardFragment : BaseFragment<DashboardViewModel, FragmentDashboardBinding, DashboardRepository>(),ClickListener {
    private var list = listOf<TracksApiResponse.Result>()
    private lateinit var dashboardAdapter: DashboardAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.tracksApi("https://itunes.apple.com/search?term=Michael+jackson").observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    binding.progress.visibility=View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progress.visibility=View.GONE
                    list=it.value.results
                    dashboardAdapter = DashboardAdapter(
                        requireContext(),  list,this)
                    binding.recyclerView.adapter=dashboardAdapter
                }
                is Resource.Failure -> {
                    binding.progress.visibility=View.GONE
                }
            }
        })
    }

    override fun getViewModel() = DashboardViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) : ViewDataBinding {

        return  DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false);

    }

    override fun getFragmentRepository(): DashboardRepository {
        return  DashboardRepository(remoteDataSource.buildApi(ApiService::class.java))
    }

    override fun onItemClick(position: Int,view:View?) {
        val bundle = Bundle()
        bundle.putSerializable("data",list[position])
        view?.findNavController()?.navigate(R.id.action_dashboardFragment_to_infoFragment, bundle)
    }

}