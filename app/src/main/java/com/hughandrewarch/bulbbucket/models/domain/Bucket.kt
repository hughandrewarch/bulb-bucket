package com.hughandrewarch.bulbbucket.models.domain

import kotlin.random.Random

class Bucket(numberPerColour: Int, numberOfUniqueColours: Int) {
    val size: Int
    private val lightBulbs: MutableSet<Lightbulb>

    init {
        require(numberPerColour > 0) { "numberPerColour is: $numberPerColour, and should be positive" }
        require(numberOfUniqueColours > 0) { "numberOfUniqueColours is: $numberOfUniqueColours, and should be positive" }
        size = numberPerColour * numberOfUniqueColours

        lightBulbs = mutableSetOf()
        var lightBulb = 1
        var lightBulbId = 1

        for (i in 1..numberOfUniqueColours) {
            lightBulbs.addAll(
                generateSequence {
                    Lightbulb(
                        id = lightBulbId++,
                        colour = lightBulb
                    )
                }.take(numberPerColour)
            )
            lightBulb++
        }
    }

    fun selectRandomLightBulb(excluded: Set<Lightbulb> = emptySet()): Lightbulb {
        val availableBulbs = lightBulbs.subtract(excluded)
        require(availableBulbs.isNotEmpty()) { "all light bulbs excluded" }
        return availableBulbs.elementAt(Random.nextInt(0, availableBulbs.size))
    }

    fun selectLightBulbs(number: Int): Set<Lightbulb> {
        require(number >= 0) { "cannot select $number bulbs" }
        require(number <= size) { "there are only $size bulbs, cannot select $number bulbs" }

        val selectedBulbs = mutableSetOf<Lightbulb>()

        repeat(number){
            val selectedBulb = selectRandomLightBulb(selectedBulbs)
            selectedBulbs.add(selectedBulb)
        }

        return selectedBulbs
    }
}
