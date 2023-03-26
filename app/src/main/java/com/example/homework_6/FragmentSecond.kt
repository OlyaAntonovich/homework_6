package com.example.homework_6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_6.databinding.FragmentSecondBinding
import com.google.android.material.divider.MaterialDividerItemDecoration

class FragmentSecond : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val args by navArgs<FragmentSecondArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSecondBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = transformItems(args.item).toMutableList()

        val itemSecond = list

        val adapterSecond = AdapterSecond(itemSecond, requireContext())

        adapterSecond.notifyDataSetChanged()

        binding.recyclerViewOne.layoutManager = LinearLayoutManager(requireContext())

        binding.buttonTab.setOnClickListener {
            binding.recyclerViewOne.layoutManager = GridLayoutManager(requireContext(), 2)
        }

        binding.recyclerViewOne.adapter = adapterSecond
        binding.recyclerViewOne.addItemDecoration(
            MaterialDividerItemDecoration(
                requireContext(),
                MaterialDividerItemDecoration.HORIZONTAL
            )
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun transformItems(array: Array<Item>): List<ItemSecond> {
        val list = mutableListOf<ItemSecond>()

        for (i in array.indices) {

            val itemSecond = ItemSecond(
                array[i].name,
                array[i].surname, array[i].phone,
                array[i].age, array[i].dob
            )

            list.add(itemSecond)

        }

        return list
    }


}