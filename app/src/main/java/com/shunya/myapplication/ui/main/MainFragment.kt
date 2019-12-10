package com.shunya.myapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.shunya.myapplication.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.loadData().observe(this, Observer { networkResource ->
            when (networkResource.status) {
                Status.LOADING -> {
                    message.text = "loading data from network"
                }
                Status.SUCCESS -> {
                    val person = networkResource.data
                    person?.let {
                        message.text =
                            person.firstName + " " + person.lastName + "\n" + person.email
                    }
                }
                Status.ERROR -> {
                    message.text = "error loading data from network"
                }
            }
        })
    }

}
