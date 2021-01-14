package krasnikov.project.familytree.ui

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.RecyclerView
import krasnikov.project.familytree.R
import krasnikov.project.familytree.databinding.RecyclerItemPersonBinding
import krasnikov.project.familytree.model.Person

class FamilyAdapter(private val items: LinkedHashMap<Person, Int>) :
    RecyclerView.Adapter<FamilyAdapter.PersonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding =
            RecyclerItemPersonBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val (person, padding) = items.entries.elementAt(position)
        holder.bind(person, padding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class PersonViewHolder(private val binding: RecyclerItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(person: Person, padding: Int) {
            itemView.updatePadding(
                left = (padding * Resources.getSystem().displayMetrics.density).toInt()
            )
            binding.tvName.text = person.name
            binding.tvAge.text = person.age.toString()
        }
    }
}