package krasnikov.project.familytree.model

class Person(
    val name: String,
    val age: Int,
    var mother: Person? = null,
    var father: Person? = null,
    val children: MutableSet<Person> = HashSet<Person>()
) {

    val siblings: Set<Person>
        get() = mother?.children?.filter { it != this }.orEmpty().toHashSet()

    val numberOfRelatives
        get() = countRelatives()

    private fun countRelatives(): Int {
        var relativesCounter = 0

        siblings?.let { relativesCounter += it.size }

        mother?.let {
            relativesCounter++
            relativesCounter += requireNotNull(mother).numberOfRelatives
        }

        father?.let {
            relativesCounter++
            relativesCounter += requireNotNull(father).numberOfRelatives
        }

        return relativesCounter
    }

    override fun toString(): String {
        return "Person(name=$name, age=$age, mother=${mother ?: "UNKNOWN PERSON"}, father=${father ?: "UNKNOWN PERSON"})"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Person

        return (name == other.name
                && age == other.age
                && mother == other.mother
                && father == other.father)
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + age
        result = 31 * result + (mother?.hashCode() ?: 0)
        result = 31 * result + (father?.hashCode() ?: 0)
        return result
    }
}