package krasnikov.project.familytree.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import krasnikov.project.familytree.model.FamilyTreeBuilder
import krasnikov.project.familytree.R
import krasnikov.project.familytree.databinding.FragmentFamilyTreeBinding
import krasnikov.project.familytree.model.Person

class FamilyTreeFragment : Fragment() {

    private lateinit var binding: FragmentFamilyTreeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFamilyTreeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun setupRecycler() {
        val me = Person("Andrii", 23)
        val familyTree = with(FamilyTreeBuilder(me)) {
            genFamily()
            build()
        }

        val adapter = FamilyAdapter(familyTree)

        val recyclerView = binding.rvFamily
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            FamilyTreeFragment()
    }
}