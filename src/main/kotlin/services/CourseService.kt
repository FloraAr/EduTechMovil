package main.kotlin.services

import main.kotlin.data.Course
import main.kotlin.repositories.CourseRepository
import main.kotlin.repositories.EnrollmentRepository

class CourseService {

    fun getCoursesForUser(userId: Long): List<Course> {

        val enrollment = EnrollmentRepository.get().find {
            it.userId == userId
        }

        return CourseRepository.get().filter { course ->
            enrollment?.coursesIds?.contains(course.id) == true
        }
    }

    fun getAvailableCourses(userId: Long): List<Course> {

        val enrollment = EnrollmentRepository.get().find {
            it.userId == userId
        }

        val ownedCourses = enrollment?.coursesIds ?: emptyList()

        return CourseRepository.get().filter { course ->
            !ownedCourses.contains(course.id)
                    && EnrollmentRepository.get().count { enrollment ->
                enrollment.coursesIds.contains(course.id)
            } < course.capacity
        }
    }

    fun getCompletedCoursesForUser(userId: Long): List<Course> {

        val enrollment = EnrollmentRepository.get().find {
            it.userId == userId
        }

        val completedIds = enrollment?.completedCoursesIds ?: emptyList()

        return CourseRepository.get().filter { course ->
            completedIds.contains(course.id)
        }
    }
}