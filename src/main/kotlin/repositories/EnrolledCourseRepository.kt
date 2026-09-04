package main.kotlin.repositories

import main.kotlin.data.EnrolledCourse

object EnrolledCourseRepository {

    private val enrolledCourses = mutableListOf<EnrolledCourse>()

    init {
        enrolledCourses.add(EnrolledCourse(1L, 4L, 60, "Presencial"))
        enrolledCourses.add(EnrolledCourse(2L, 3L, 45, "Virtual"))
        enrolledCourses.add(EnrolledCourse(3L, 1L, 60, "Presencial"))
        enrolledCourses.add(EnrolledCourse(4L, 5L, 90, "Presencial"))
        enrolledCourses.add(EnrolledCourse(5L, 2L, 60, "Virtual"))
        enrolledCourses.add(EnrolledCourse(6L, 6L, 45, "Presencial"))
        enrolledCourses.add(EnrolledCourse(7L, 7L, 60, "Virtual"))
    }
}
