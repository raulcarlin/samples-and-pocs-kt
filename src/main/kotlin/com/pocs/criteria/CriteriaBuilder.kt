package com.pocs.criteria

import kotlin.reflect.full.createType
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.valueParameters

data class SimpleCriteria(val name: String, val numberOfWhatever: Int, val iamNull: String? = null)

object CriteriaBuilder {

    fun buildQuery(query: String, criteria: Any, otherClauses: String? = null): Pair<String, Map<String, Any>> {
        val map = mutableMapOf<String, Any>()
        criteria::class.memberProperties.forEach { member ->
            member.getter.call(criteria)?.let {
                map[member.name.toSnakeCase()] = it
            }

            // TODO: Ver se é uma collection para IN (), alterar criação da query e params no map
            // -> member.returnType.isSubtypeOf(Collection::class.createType())
            // Não é performático
        }

        val fullQuery = "$query ${if(map.keys.isEmpty()) "" 
            else "WHERE ${map.keys.joinToString(" AND ") { "$it = ?" }}"} ${otherClauses ?: ""}"

        return fullQuery.trim() to map
    }

}

fun String.toSnakeCase(): String {
    return "(?<=[a-zA-Z])[A-Z]".toRegex().replace(this) {
        "_${it.value}"
    }.lowercase()
}