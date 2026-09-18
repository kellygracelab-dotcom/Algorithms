package scopefunctions

data class Profile(var name: String, var age: Int, var bio: String = "")

fun greet(username: String?): String {
    return username?.let { "Hello, $it" } ?: "Hello, guest"
}

fun buildProfile(name: String, age: Int): Profile {
    return Profile(name, age).apply {
        bio = "New user"
    }
}

fun addAndCount(list: MutableList<Int>, value: Int): Int {
    return list.run {
        add(value)
        size
    }
}

fun summarize(profile: Profile): String {
    return with(profile) {
        "$name, $age y.o. — $bio"
    }
}
