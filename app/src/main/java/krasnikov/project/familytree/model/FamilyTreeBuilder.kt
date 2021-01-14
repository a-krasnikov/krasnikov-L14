package krasnikov.project.familytree.model

import java.util.*

class FamilyTreeBuilder(private val root: Person) {

    // Arrays of names
    private val maleNames = arrayOf("Alex", "Victor", "Roma", "Vova")
    private val femaleNames = arrayOf("Vita", "Olena", "Svitlana", "Kate")
    private val allNames = femaleNames + maleNames

    private val familyTree: LinkedHashMap<Person, Int> = LinkedHashMap<Person, Int>()

    private val paddingIncrement = 16

    fun genFamily(numberOfGenerations: Int = 3) {
        genRelatives(root, numberOfGenerations)
    }

    fun addParents(mother: Person, father: Person) {
        val queue: Queue<Person> = LinkedList<Person>()
        queue.add(root)

        var temp: Person?

        while (queue.isNotEmpty()) {
            temp = queue.remove()

            if (temp.mother == null && temp.father == null) {
                temp.mother = mother
                temp.father = father
                break
            } else {
                queue.add(temp.mother)
                queue.add(temp.father)
            }
        }
    }

    fun build(): LinkedHashMap<Person, Int> {
        recursiveTraversal(root, 0)
        return familyTree
    }

    private fun recursiveTraversal(person: Person, padding: Int) {
        familyTree[person] = padding

        person.mother?.let { recursiveTraversal(it, padding + paddingIncrement) }
        person.father?.let { recursiveTraversal(it, padding + paddingIncrement) }
    }

    // Generate Relatives
    private fun genRelatives(person: Person, numberOfGenerations: Int) {
        val mother = Person(femaleNames.random(), person.age + (16..25).random())
        val father = Person(maleNames.random(), person.age + (16..25).random())
        person.mother = mother
        person.father = father
        mother.children.add(person)
        father.children.add(person)

        genSiblings(mother, father)

        // go to next generation or exit
        if (numberOfGenerations == 1) {
            return
        } else {
            genRelatives(requireNotNull(person.father), numberOfGenerations - 1)
            genRelatives(requireNotNull(person.mother), numberOfGenerations - 1)
        }
    }

    // Generate siblings
    private fun genSiblings(mother: Person, father: Person) {
        // random(0..2) number of siblings
        val n = (0..2).random()

        if (n > 0) {
            // max age of children
            val maxAge = mother.age - 16
            val siblings = MutableList(n) {
                Person(allNames.random(), (1..maxAge).random()).apply {
                    this.mother = mother
                    this.father = father
                }
            }
            mother.children.addAll(siblings)
            father.children.addAll(siblings)
        }
    }
}