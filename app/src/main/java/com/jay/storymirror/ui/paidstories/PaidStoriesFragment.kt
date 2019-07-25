package com.jay.storymirror.ui.paidstories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jay.storymirror.R
import com.jay.storymirror.ui.StoryAdapter
import com.jay.storymirror.ui.StoryFragmentViewModel


class PaidStoriesFragment : Fragment() {

    private lateinit var adapter: StoryAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: StoryFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.paid_stories_fragment, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_paid_stories)
        val mLayoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.hasFixedSize()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StoryFragmentViewModel::class.java)

        viewModel.getPaidStories().observe(this, Observer {
            if (it != null && it.isNotEmpty()) {
                adapter = StoryAdapter(requireContext(), it)
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        })
    }
}
