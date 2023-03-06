package com.example.homework_6

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_6.databinding.FragmentFirstBinding


class FragmentFirst : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return FragmentFirstBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = mutableListOf<Item>()

        val adapter = Adapter(items)


        with(binding) {

            val editTextFields = listOf<EditText>(
                editTextName,
                editTextSurname, editTextPhone, editTextAge, editTextDob
            )

            val textWatcherEditText: TextWatcher = object : TextWatcher {

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(editable: Editable?) {
                    val condition: Boolean = editTextFields[0].text.toString().isNotEmpty() &&
                            editTextFields[1].text.toString().isNotEmpty() &&
                            editTextFields[2].text.toString().isNotEmpty() &&
                            editTextFields[3].text.toString().isNotEmpty() &&
                            editTextFields[4].text.toString().isNotEmpty()

                    button1.isEnabled = condition
                    if (button1.isEnabled) {
                        button1.setBackgroundColor(Color.parseColor("#FF0000"))
                    } else {
                        button1.setBackgroundColor(Color.parseColor("black"))
                    }
                }
            }

            for (element in editTextFields) {
                element.addTextChangedListener(textWatcherEditText)
            }

            if (button1.isEnabled
            ) {
                button1.setOnClickListener {

                    val item = Item(editTextFields[0].text.toString(),
                        editTextFields[1].text.toString(),
                        editTextFields[2].text.toString(),
                        editTextFields[3].text.toString(),
                        editTextFields[4].text.toString()
                    )

                    items.add(item)
                    adapter.submitList(items)


                }

            }

            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter


        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}