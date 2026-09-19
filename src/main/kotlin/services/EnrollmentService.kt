package main.kotlin.services

import main.kotlin.data.Course
import main.kotlin.exceptions.CursoCompletoException
import main.kotlin.repositories.EnrollmentRepository

class EnrollmentService {

    fun getEnrollmentForUser(userId: Long) =
        EnrollmentRepository.get().find {
            it.userId == userId
        }

    fun canEnroll(course: Course): Boolean {

        val enrolled = EnrollmentRepository.get().count { enrollment ->
            enrollment.coursesIds.contains(course.id)
        }

        return enrolled < course.capacity
    }

    fun enroll(userId: Long, course: Course) {

        val enrollment = getEnrollmentForUser(userId)

        if (enrollment == null) {
            return
        }

        if (enrollment.coursesIds.contains(course.id)) {
            return
        }

        if (!canEnroll(course)) {
            throw CursoCompletoException()
        }

        enrollment.coursesIds.add(course.id)
    }
}