package com.pocs.jdbi

import org.jdbi.v3.core.mapper.RowMapper
import org.jdbi.v3.core.statement.StatementContext
import org.jdbi.v3.sqlobject.kotlin.RegisterKotlinMapper
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import org.springframework.stereotype.Component
import java.sql.ResultSet

interface SampleRepository {
    fun listAll(): List<Sample>
    fun findById(id: Long): Sample
    fun insert(sample: Sample): Long
}

@RegisterKotlinMapper(Sample::class)
interface SampleJdbiRepository : SampleRepository {

    @SqlQuery("SELECT id, name, number, add_at FROM sample")
    override fun listAll(): List<Sample>

    @SqlQuery("SELECT id, name, number, add_at FROM sample WHERE id = :id")
    override fun findById(id: Long): Sample

    @GetGeneratedKeys("id")
    @SqlUpdate("INSERT INTO sample(name, number, add_at) VALUES (:sample.name, :sample.number, :sample.addAt)")
    override fun insert(sample: Sample): Long
}

interface OtherRepository {
    fun findOther(): Other
}

@UseClasspathSqlLocator
interface OtherJdbiRepository : OtherRepository {
    @SqlQuery
    override fun findOther(): Other
}

@Component
class OtherRowMapper : RowMapper<Other> {

    override fun map(rs: ResultSet?, ctx: StatementContext?): Other {
        return Other(
            id = rs?.getLong("id") ?: 0,
            name = rs?.getString("other_name") ?: "",
            number = rs?.getDouble("other_number") ?: 0.0,
            addAt = rs?.getTimestamp("other_date")?.toLocalDateTime()
                ?: throw IllegalArgumentException()
        )
    }

}