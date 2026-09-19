package main.kotlin.repositories

import main.kotlin.data.Enrollment

object EnrollmentRepository {

    private val enrollments = mutableListOf<Enrollment>()

    init {
        enrollments.add(Enrollment(1L, 1510L, mutableListOf(1L, 3L, 5L), mutableListOf(1L)))
        enrollments.add(Enrollment(2L, 1504L, mutableListOf(2L, 4L, 6L), mutableListOf(2L)))
        enrollments.add(Enrollment(3L, 2802L, mutableListOf(1L, 5L, 7L), mutableListOf()))
    }

    //De devolver la lista vacía, devuelve las listas cargadas.
    fun get(): List<Enrollment> {
        return enrollments
    }
}
