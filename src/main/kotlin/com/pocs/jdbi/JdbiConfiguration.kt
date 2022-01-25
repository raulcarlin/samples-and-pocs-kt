package com.pocs.jdbi

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.h2.H2DatabasePlugin
import org.jdbi.v3.core.kotlin.KotlinPlugin
import org.jdbi.v3.core.mapper.RowMapper
import org.jdbi.v3.sqlobject.SqlObjectPlugin
import org.jdbi.v3.sqlobject.kotlin.KotlinSqlObjectPlugin
import org.jdbi.v3.sqlobject.kotlin.onDemand
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy
import javax.sql.DataSource

@Configuration
class JdbiConfiguration {

    @Bean
    fun jdbi(dataSource: DataSource, mappers: List<RowMapper<*>>): Jdbi {
        val jdbi = Jdbi.create(TransactionAwareDataSourceProxy(dataSource)).installPlugins()
        mappers.forEach { jdbi.registerRowMapper(it) }
        return jdbi
    }

    @Bean
    fun sampleRepository(jdbi: Jdbi) = jdbi.onDemand<SampleJdbiRepository>()

    @Bean
    fun otherRepository(jdbi: Jdbi) = jdbi.onDemand<OtherJdbiRepository>()

}

