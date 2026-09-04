package main.kotlin.repositories

import main.kotlin.data.User

object UserRepository {

    private val users = mutableListOf<User>()

    init {
        users.add(User(1504L, "martin_albanesi", "1234", "Martin", "Albanesi", 3500000.50, "2024/05/13"))
        users.add(User(2802L, "franco_mazafra", "1234", "Franco German", "Mazafra", 200000.50, "2021/01/20"))
        users.add(User(1510L, "agus_maldonado", "1234", "Agustina Belen", "Arce Maldonado", 120000.0, "2018/04/15"))
    }

    fun login(): User? {
        return null
    }

}