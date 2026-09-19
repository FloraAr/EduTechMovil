package main.kotlin

import main.kotlin.exceptions.CursoCompletoException
import main.kotlin.exceptions.SaldoInsuficienteException
import main.kotlin.payments.MasterCard
import main.kotlin.payments.MercadoPago
import main.kotlin.payments.PaymentMethod
import main.kotlin.payments.Visa
import main.kotlin.repositories.CourseRepository
import main.kotlin.repositories.UserRepository
import main.kotlin.services.CourseService
import main.kotlin.services.EnrollmentService
import main.kotlin.services.PurchaseService
import java.util.Scanner

fun main() {

    val scanner = Scanner(System.`in`)

    val courseService = CourseService()
    val enrollmentService = EnrollmentService()
    val purchaseService = PurchaseService()

    println("========== EDUTECH ==========")
    println()
    println("LOGIN")

    print("Nickname: ")
    val nickname = scanner.nextLine()

    print("Password: ")
    val password = scanner.nextLine()

    val user = UserRepository.login(nickname, password)

    if (user == null) {

        println()
        println("Usuario o contraseña incorrectos.")

    } else {

        var option = 0

        println()
        println("Bienvenido/a ${user.name} ${user.surname}")

        while (option != 6) {

            println()
            println("========== MENU PRINCIPAL ==========")
            println("1. Ver mis cursos")
            println("2. Ver cursos disponibles")
            println("3. Ver cursos completados")
            println("4. Comprar curso")
            println("5. Ver saldo")
            println("6. Salir")

            option = scanner.nextInt()

            when (option) {

                1 -> {

                    println()
                    println("===== MIS CURSOS =====")

                    val myCourses = courseService.getCoursesForUser(user.id)

                    if (myCourses.isEmpty()) {

                        println("No tenés cursos adquiridos.")

                    } else {

                        myCourses.forEach { course ->
                            println("${course.id} - ${course.name}")
                        }
                    }
                }

                2 -> {

                    println()
                    println("===== CURSOS DISPONIBLES =====")

                    val availableCourses =
                        courseService.getAvailableCourses(user.id)

                    if (availableCourses.isEmpty()) {

                        println("No hay cursos disponibles.")

                    } else {

                        availableCourses.forEach { course ->
                            println("${course.id} - ${course.name} - $10.000")
                        }
                    }
                }

                3 -> {

                    println()
                    println("===== CURSOS COMPLETADOS =====")

                    val completedCourses =
                        courseService.getCompletedCoursesForUser(user.id)

                    if (completedCourses.isEmpty()) {

                        println("No tenés cursos completados.")

                    } else {

                        completedCourses.forEach { course ->
                            println("${course.id} - ${course.name}")
                        }
                    }
                }

                4 -> {

                    println()
                    println("===== COMPRAR CURSO =====")

                    val availableCourses =
                        courseService.getAvailableCourses(user.id)

                    if (availableCourses.isEmpty()) {

                        println("No hay cursos disponibles para comprar.")

                    } else {

                        availableCourses.forEach { course ->
                            println("${course.id} - ${course.name} - $10.000")
                        }

                        print("Ingrese el ID del curso que desea comprar: ")
                        val courseId = scanner.nextLong()

                        val selectedCourse = availableCourses.find {
                            it.id == courseId
                        }

                        if (selectedCourse == null) {

                            println("El curso seleccionado no está disponible.")

                        } else {

                            println()
                            println("Seleccione el medio de pago:")
                            println("1. Mercado Pago")
                            println("2. Visa")
                            println("3. MasterCard")
                            print("Opción: ")

                            val paymentOption = scanner.nextInt()

                            val paymentMethod: PaymentMethod? = when (paymentOption) {
                                1 -> MercadoPago()
                                2 -> Visa()
                                3 -> MasterCard()
                                else -> null
                            }

                            if (paymentMethod == null) {

                                println("Medio de pago inválido.")

                            } else {

                                try {

                                    if (!enrollmentService.canEnroll(selectedCourse)) {

                                        throw CursoCompletoException()
                                    }

                                    val total = purchaseService.purchase(
                                        user,
                                        listOf(selectedCourse),
                                        paymentMethod
                                    )

                                    enrollmentService.enroll(
                                        user.id,
                                        selectedCourse
                                    )

                                    println()
                                    println("Compra realizada correctamente.")
                                    println("Curso: ${selectedCourse.name}")
                                    println("Total pagado: $$total")
                                    println("Saldo restante: $${user.money}")

                                } catch (e: CursoCompletoException) {

                                    println()
                                    println(e.message)

                                } catch (e: SaldoInsuficienteException) {

                                    println()
                                    println(e.message)
                                }
                            }
                        }
                    }
                }

                5 -> {

                    println()
                    println("===== SALDO =====")
                    println("Saldo disponible: $${user.money}")
                }

                6 -> {

                    println()
                    println("Gracias por utilizar EduTech.")

                }

                else -> {

                    println()
                    println("Opción inválida.")
                }
            }
        }
    }
}