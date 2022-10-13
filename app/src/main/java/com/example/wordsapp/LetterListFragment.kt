package com.example.wordsapp

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import com.example.wordsapp.databinding.FragmentLetterList1Binding

class LetterListFragment : Fragment() {
    private var _binding: FragmentLetterList1Binding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private var isLinear: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLetterList1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        changeLayout()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)

        val layoutButton = menu.findItem(R.id.layout)
        setIcon(layoutButton)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.layout -> {
                // Sets isLinearLayoutManager (a Boolean) to the opposite value
                isLinear = !isLinear
                // Sets layout and icon
                changeLayout()
                setIcon(item)

                return true
            }
            //  Otherwise, do nothing and use the core event handling

            // when clauses require that all possible paths be accounted for explicitly,
            //  for instance both the true and false cases if the value is a Boolean,
            //  or an else to catch all unhandled cases.
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun changeLayout() {
        if (isLinear) recyclerView.layoutManager = GridLayoutManager(context, 4)
        else recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = LetterAdapter()
    }

    private fun setIcon(menuItem: MenuItem) {
        if (menuItem == null) return
        menuItem.icon = if (isLinear)
            ContextCompat.getDrawable(this.requireContext(), R.drawable.grid)
        else
            ContextCompat.getDrawable(this.requireContext(), R.drawable.list)

    }
}