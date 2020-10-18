package eu.aboutall.myapplication

import android.graphics.Color
import androidx.annotation.ColorInt
import kotlin.random.Random

data class Balloon(val x: Float, val y: Float, @ColorInt val color: Int, val radius: Float = RADIUS) {


    fun isTouched(touchedX: Float, touchedY: Float) =
            touchedX > x - RADIUS &&
                    touchedX < x + RADIUS &&
                    touchedY > y - RADIUS &&
                    touchedY < y + RADIUS

    companion object {
        const val RADIUS = 48f
        const val PADDING = 30

        fun newRandomInstance(width: Int, height: Int): Balloon {
            var x = width * Random.nextFloat()
            var y = height * Random.nextFloat()
            val border = RADIUS + PADDING

            if (x < border) {
                x = border
            }

            if (x > width - border) {
                x = width - border
            }

            if (y < border) {
                y = border
            }

            if (y > height - border) {
                y = height - border
            }

            return Balloon(x, y, randomColor())
        }

        private fun randomColor() =
                Color.argb(
                        255,
                        Random.nextInt(256),
                        Random.nextInt(256),
                        Random.nextInt(256))
    }
}