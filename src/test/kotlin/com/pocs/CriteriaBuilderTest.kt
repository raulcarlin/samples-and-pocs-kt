package com.pocs

import com.pocs.criteria.CriteriaBuilder
import com.pocs.criteria.SimpleCriteria
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CriteriaBuilderTest {

    @Test
    @DisplayName("Simple criteria generation")
    fun testSimpleCriteriaGeneration() {
        val initialQuery = "SELECT * FROM Table"
        val criteria = SimpleCriteria(
            "Test",
            10
        )
        val (fullQuery, params) = CriteriaBuilder.buildQuery(initialQuery, criteria)

        val expectedQuery = "$initialQuery WHERE name = ? AND number_of_whatever = ?"
        assertEquals(expectedQuery, fullQuery)
        assertAll("Params",
            { assertEquals(params["name"], "Test") },
            { assertEquals(params["number_of_whatever"], 10) }
        )
    }

    @Test
    @DisplayName("Clause query generation")
    fun testOtherClausesGeneration() {
        val initialQuery = "SELECT * FROM Table"
        val criteria = SimpleCriteria(
            "Test Clause",
            20
        )
        val otherClauses = "ORDER BY name"

        val (fullQuery, params) = CriteriaBuilder.buildQuery(initialQuery, criteria, otherClauses)

        val expectedQuery = "$initialQuery WHERE name = ? AND number_of_whatever = ? ORDER BY name"
        assertEquals(expectedQuery, fullQuery)
        assertAll("Params",
            { assertEquals(params["name"], "Test Clause") },
            { assertEquals(params["number_of_whatever"], 20) }
        )
    }
}