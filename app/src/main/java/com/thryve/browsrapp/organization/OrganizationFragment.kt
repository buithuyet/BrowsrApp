package com.thryve.browsrapp.organization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.thryve.browsrapp.databinding.FragmentOrganizationBinding

class OrganizationFragment : Fragment() {

    private var _binding: FragmentOrganizationBinding? = null
    private val binding get() = _binding!!

    private lateinit var organizationFragmentViewModel: OrganizationFragmentViewModel
    private val adapter by lazy { OrganizationListItemAdapter(::onFavoriteClick) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrganizationBinding.inflate(inflater, container, false)
        initViews()
        initViewModel()

        return binding.root
    }

    private fun initViewModel() {
        organizationFragmentViewModel = ViewModelProvider(this)[OrganizationFragmentViewModel::class.java]
        organizationFragmentViewModel.organizationsResultsLiveData.observe(this) {
            if (it.first.isSuccess) {
                adapter.updateAll(
                    OrganizationListItemMapper.mapToListItems(
                        it.first.organizations,
                        it.second.toMutableList()
                    )
                )
            } else {
                Toast.makeText(
                    requireContext(),
                    "Error: " + it.first.errorMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        organizationFragmentViewModel.getOrganizations(requireContext())
    }

    private fun initViews() = with(binding) {
        organizationListRecyclerView.adapter = adapter
        organizationListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        organizationListRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        searchOrganization.doAfterTextChanged {
            val searchText = it?.toString() ?: ""
            adapter.searchItem(searchText)

        }
    }

    private fun onFavoriteClick(organizationListItem: OrganizationListItem) {
        adapter.updateFavoriteItem(organizationListItem)
        organizationFragmentViewModel.insertOrUpdateFavorite(requireContext(), organizationListItem)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}