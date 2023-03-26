package com.example.homework_6


import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_6.databinding.FragmentFirstBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.divider.MaterialDividerItemDecoration


class FragmentFirst : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var items = mutableListOf<Item>()
    private val launcher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentFirstBinding.inflate(inflater, container, false)
        .also { _binding = it }
        .root


    @RequiresApi(VERSION_CODES.N)
    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adapter = Adapter(items)

        with(binding) {

            val editTextFields = listOf(
                editTextName,
                editTextSurname, editTextPhone, editTextAge, editTextDob
            )

            editTextDob.setOnClickListener {

                val datePicker =
                    MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Select date")
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .build()

                datePicker.show(requireActivity().supportFragmentManager, "tag")
                datePicker.addOnPositiveButtonClickListener {
                    editTextDob.setText(datePicker.headerText)
                }

            }

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
                        button1.setBackgroundColor(Color.parseColor("#26C6DA"))
                    } else {
                        button1.setBackgroundColor(Color.parseColor("#E1F5FE"))
                    }
                }
            }

            for (element in editTextFields) {
                element.addTextChangedListener(textWatcherEditText)
            }

            if (button1.isEnabled
            ) {

                button1.setOnClickListener {
                    val item = makeItem(editTextFields)
                    items.add(item)
                    adapter.notifyDataSetChanged()
                }

            }

            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter
            recyclerView.addItemDecoration(
                MaterialDividerItemDecoration(
                    requireContext(),
                    MaterialDividerItemDecoration.VERTICAL
                )
            )

            button2.setOnClickListener {

                val direction = FragmentFirstDirections.toSecondFragment(items.toTypedArray())
                findNavController().navigate(direction)

            }

            toolbar.setOnMenuItemClickListener {
                if (it.itemId == R.id.action_1) {

                    val web =
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.britannica.com/browse/Fish")
                        )
                    startActivity(web)
                }
                if (it.itemId == R.id.action_2) {
                    launcher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
                    true

                } else {
                    false
                }

            }

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun makeItem(listEditText: List<EditText>): Item {

        return Item(
            listEditText[0].text.toString(),
            listEditText[1].text.toString(),
            listEditText[2].text.toString(),
            listEditText[3].text.toString(),
            listEditText[4].text.toString()
        )
    }

}








