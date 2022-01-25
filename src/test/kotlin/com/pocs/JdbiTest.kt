package com.pocs

import com.pocs.jdbi.OtherRepository
import com.pocs.jdbi.Sample
import com.pocs.jdbi.SampleRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest

class JdbiTest {

    @Autowired
    lateinit var sampleRepository: SampleRepository

    @Autowired
    lateinit var otherRepository: OtherRepository

    @Test
    fun sampleRepositoryTest() {
        val generatedId = sampleRepository.insert(Sample(name = "Test", number = 10.5, addAt = LocalDateTime.now()))

        val saved = sampleRepository.findById(generatedId)

        assertEquals("Test", saved.name)
    }

    @Test
    fun otherRepository() {
        val other = otherRepository.findOther();

        assertEquals(20.0, other.number)
    }
}