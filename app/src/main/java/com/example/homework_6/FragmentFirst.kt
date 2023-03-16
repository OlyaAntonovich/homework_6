package com.example.homework_6


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.graphics.Color
import android.icu.util.Calendar
import android.os.Build
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_6.databinding.FragmentFirstBinding


class FragmentFirst : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var items = mutableListOf<Item>()
    private val calendar = if (Build.VERSION.SDK_INT >= VERSION_CODES.N) {
        Calendar.getInstance()
    } else {
        TODO("VERSION.SDK_INT < N")
    }

    @RequiresApi(VERSION_CODES.N)
    private val year = calendar.get(Calendar.YEAR)

    @RequiresApi(VERSION_CODES.N)
    private val month = calendar.get(Calendar.MONTH)

    @RequiresApi(VERSION_CODES.N)
    private val day = calendar.get(Calendar.DAY_OF_MONTH)


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
                context?.let { it1 ->
                    DatePickerDialog(
                        it1,
                        DatePickerDialog.OnDateSetListener { _, mDay, mMonth, mYear
                            ->
                            editTextDob.setText("" + mDay + "." + (mMonth+1) + "." + mYear)
                        },
                        day, month, year
                    )


                }?.show()

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
                    val item = makeItem(editTextFields)
                    items.add(item)
//                    tw.text = items.toString()
                    adapter.notifyDataSetChanged()

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








