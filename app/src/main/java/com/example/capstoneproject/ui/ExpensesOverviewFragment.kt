package com.example.capstoneproject.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.R
import com.example.capstoneproject.adapters.ExpensesAdapter
import com.example.capstoneproject.databinding.FragmentExpensesOverviewBinding
import com.example.capstoneproject.databinding.FragmentIncomeOverviewBinding
import com.example.capstoneproject.model.Expense
import com.example.capstoneproject.model.Income
import com.example.capstoneproject.viewmodels.ExpensesViewModel
import com.example.capstoneproject.viewmodels.IncomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ExpensesOverviewFragment : Fragment() {
    private lateinit var binding: ExpensesOverviewFragment

    private val viewModel: ExpensesViewModel by viewModels()

    private val expenses = arrayListOf<Expense>()
    private val expensesAdapter = ExpensesAdapter(expenses)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentExpensesOverviewBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAddExpense.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        initRv()

        observeAddIncomeResult()
    }

    private fun initRv() {
        binding.rvExpenses.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvIncomes.adapter = expensesAdapter

        createItemTouchHelper().attachToRecyclerView(binding.rvIncomes)
    }

    private fun observeAddIncomeResult() {
        viewModel.expenses.observe(viewLifecycleOwner, { incomes ->
            this.expenses.clear()
            this.expenses.addAll(incomes)
            expensesAdapter.notifyDataSetChanged()
        })
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                CoroutineScope(Dispatchers.Main).launch {
                    withContext(Dispatchers.IO) {
                        viewModel.deleteIncome(expenses[position])
                    }
                }
            }
        }

        return ItemTouchHelper(callback)
    }
}