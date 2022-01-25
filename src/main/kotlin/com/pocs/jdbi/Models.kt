package com.pocs.jdbi

import org.jdbi.v3.core.mapper.RowMapper
import org.jdbi.v3.core.statement.StatementContext
import org.springframework.stereotype.Component
import java.sql.ResultSet
import java.time.LocalDateTime

data class Sample(val id: Long? = null, val name: String, val number: Double, val addAt: LocalDateTime)

data class Other(val id: Long? = null, val name: String, val number: Double, val addAt: LocalDateTime)