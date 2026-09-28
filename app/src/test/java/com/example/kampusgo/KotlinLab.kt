package com.example.kampusgo

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Lab 02 — Kotlin essentials
 *
 * Read the handout links first. Replace every TODO(). Do not use !!.
 *
 * RUN: green triangle next to allChecks() — not the toolbar Play that opens the phone.
 */

/**
 * data class = a class made to hold data.
 * Each Student has fields you read with a dot: student.name, student.gpa, …
 * advisor is String?  →  it may be a String OR null (no advisor).
 */
data class Student(
    val name: String,
    val npm: String,
    val angkatan: Int,
    val gpa: Double,
    val advisor: String?,
)

val sampleStudents: List<Student> = listOf(
    Student("Budi Santoso", "001", 2024, 3.81, "Ibu Wati"),
    Student("Siti Rahma", "002", 2024, 3.20, "Ibu Wati"),
    Student("Andi Wijaya", "003", 2023, 2.70, null),
    Student("Dewi Lestari", "004", 2025, 3.95, "Pak Eko"),
    Student("Raka Putra", "005", 2023, 3.55, "Pak Eko"),
    Student("Maya Chen", "006", 2025, 3.40, null),
    Student("Fajar Nugroho", "007", 2024, 3.62, "Ibu Wati"),
)

/**
 * Keep students with GPA >= 3.5, highest GPA first.
 * Read: kotlinlang.org/docs/collection-filtering.html
 * and collection-transformations (sortedByDescending).
 */
fun deanList(students: List<Student>): List<Student> {
    return students
        .filter { it.gpa >= 3.5 }
        .sortedByDescending { it.gpa }
}

/**
 * Average of all GPAs. Empty list returns 0.0.
 * Read: kotlinlang.org/docs/collection-transformations.html (map)
 */
fun averageGpa(students: List<Student>): Double {
    return if (students.isEmpty()) {
        0.0
    } else {
        students.map { it.gpa }.average()
    }
}

/**
 * Map from angkatan to the names in that year.
 * Read: kotlinlang.org/docs/collection-grouping.html
 */
fun namesByAngkatan(students: List<Student>): Map<Int, List<String>> {
    return students
        .groupBy { it.angkatan }
        .mapValues { (_, studentsInCohort) ->
            studentsInCohort.map { it.name }
        }
}

/**
 * NPM of every student whose advisor is null.
 * Read the filter page. Use == null. Do not use !!.
 * Null safety: kotlinlang.org/docs/null-safety.html
 */
fun missingAdvisorNpms(students: List<Student>): List<String> {
    return students
        .filter { it.advisor == null }
        .map { it.npm }
}

/**
 * 3.5+ → A,  3.0+ → B,  2.0+ → C,  else → D. Check the highest band first.
 * Read: kotlinlang.org/docs/control-flow.html#when-expression
 */
fun letterGrade(gpa: Double): Char {
    return when {
        gpa >= 3.5 -> 'A'
        gpa >= 3.0 -> 'B'
        gpa >= 2.0 -> 'C'
        else -> 'D'
    }
}

fun topAdvisor(students: List<Student>): String? {
    return students
        .mapNotNull { it.advisor }
        .groupingBy { it }
        .eachCount()
        .maxByOrNull { it.value }
        ?.key
}

/** Do not change this test. Fill the five functions until it PASSES. */
class KotlinLabTest {

    @Test
    fun allChecks() {
        assertEquals("deanList size", 4, deanList(sampleStudents).size)
        assertEquals(
            "deanList first",
            "Dewi Lestari",
            deanList(sampleStudents).first().name,
        )
        assertEquals(
            "average roughly 3.46",
            3,
            averageGpa(sampleStudents).toInt(),
        )
        assertEquals("empty average", 0.0, averageGpa(emptyList()), 0.0)
        assertEquals(
            "angkatan keys",
            setOf(2023, 2024, 2025),
            namesByAngkatan(sampleStudents).keys,
        )
        assertEquals(
            "names in 2024",
            listOf("Budi Santoso", "Siti Rahma", "Fajar Nugroho"),
            namesByAngkatan(sampleStudents)[2024],
        )
        assertEquals(
            "missing advisors",
            listOf("003", "006"),
            missingAdvisorNpms(sampleStudents),
        )
        assertEquals("letter 3.81", 'A', letterGrade(3.81))
        assertEquals("letter 3.5", 'A', letterGrade(3.5))
        assertEquals("letter 3.0", 'B', letterGrade(3.0))
        assertEquals("letter 2.70", 'C', letterGrade(2.70))
        assertEquals("letter 1.9", 'D', letterGrade(1.9))
    }

    @Test
    fun topAdvisorCheck() {
        val students = listOf(
            Student("Student 1", "101", 2023, 3.5, "Dr. Budi"),
            Student("Student 2", "102", 2023, 3.2, "Dr. Budi"),
            Student("Student 3", "103", 2024, 3.8, "Dr. Sari"),
            Student("Student 4", "104", 2024, 3.0, null)
        )

        assertEquals("Dr. Budi", topAdvisor(students))
        assertEquals(null, topAdvisor(emptyList()))
    }
}