package com.hughandrewarch.bulbbucket.models

import com.hughandrewarch.bulbbucket.models.domain.Bucket
import com.hughandrewarch.bulbbucket.models.domain.Lightbulb
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested

internal class BucketTest {

    @Test
    fun `should throw exception when numberPerColour is non positive`() {

        assertThatThrownBy {
            Bucket(0, 1)
        }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("numberPerColour is: 0, and should be positive")
    }

    @Test
    fun `should throw exception when numberOfUniqueColours is non positive`() {

        assertThatThrownBy {
            Bucket(1, -1)
        }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("numberOfUniqueColours is: -1, and should be positive")
    }

    @Test
    fun getSize() {
        val bucket = Bucket(2, 10)

        assertEquals(bucket.size, 20)
    }

    @Nested
    inner class `selecting lightbulbs` {
        private val bucket = Bucket(1, 2)
        val lightBulb1 = Lightbulb(1, 1)
        val lightBulb2 = Lightbulb(2, 2)

        private val possibleLightBulbs = setOf(
            lightBulb1,
            lightBulb2
        )

        @Test
        fun `selectRandomLightBulb should select a light bulb excluding none`() {
            val randomLightBulb = bucket.selectRandomLightBulb()

            assertThat(possibleLightBulbs).contains(randomLightBulb)
        }

        @Test
        fun `selectRandomLightBulb should select a light bulb excluding some`() {
            val randomLightBulb = bucket.selectRandomLightBulb(setOf(lightBulb1))

            assertThat(randomLightBulb).isEqualTo(lightBulb2)
        }

        @Test
        fun `selectRandomLightBulb should throw an exception when all lightbulbs are excluded`() {
            assertThatThrownBy {
                bucket.selectRandomLightBulb(possibleLightBulbs)
            }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("all light bulbs excluded")
        }

        @Test
        fun `selectLightBulbs should throw an exception when a negative number is selected`() {
            assertThatThrownBy {
                bucket.selectLightBulbs(-1)
            }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("cannot select -1 bulbs")
        }

        @Test
        fun `selectLightBulbs should throw an exception when too many bulbs are selected`() {
            assertThatThrownBy {
                bucket.selectLightBulbs(3)
            }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("there are only 2 bulbs, cannot select 3 bulbs")
        }

        @Test
        fun `selectLightBulbs should return an empty set when none are selected`() {
                val selectedLightBulbs = bucket.selectLightBulbs(0)

                assertThat(selectedLightBulbs).isEmpty()
        }

        @Test
        fun `selectLightBulbs should return the a single bulb`() {
            val selectedLightBulbs = bucket.selectLightBulbs(1)

            assertEquals(selectedLightBulbs.size, 1)
            val selectedLightBulb = selectedLightBulbs.elementAt(0)
            assertThat(possibleLightBulbs).contains(selectedLightBulb)
        }

        @Test
        fun `selectLightBulbs should return the multiple bulbs`() {
            val selectedLightBulbs = bucket.selectLightBulbs(2)

            assertEquals(selectedLightBulbs.size, 2)
            val selectedLightBulb1 = selectedLightBulbs.elementAt(0)
            assertThat(possibleLightBulbs).contains(selectedLightBulb1)
            val selectedLightBulb2 = selectedLightBulbs.elementAt(1)
            assertThat(possibleLightBulbs).contains(selectedLightBulb2)
        }
    }
}