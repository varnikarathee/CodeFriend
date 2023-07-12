package com.example.CodeFriend.ui.messenger.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.CodeFriend.R
import com.example.CodeFriend.adapter.AdapterPeople
import com.example.CodeFriend.ui.messenger.ViewModelMessenger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_people.*
import javax.inject.Inject


@AndroidEntryPoint
class PeopleFragment : Fragment(R.layout.fragment_people) {

    private val viewModel by viewModels<ViewModelMessenger>()

    @Inject
    lateinit var peopleAdapter: AdapterPeople

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayout = LinearLayoutManager(activity)
        friends_rv.layoutManager=linearLayout



        viewModel.peopleAll.observe(viewLifecycleOwner) { people ->
            peopleAdapter.differ.submitList(people)
            friends_rv.adapter = peopleAdapter
        }

        peopleAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable("user", it)
            }
            findNavController().navigate(
                R.id.action_peopleFragment_to_conversationFragment,
                bundle
            )
        }

    }
}