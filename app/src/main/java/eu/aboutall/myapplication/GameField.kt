package eu.aboutall.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class GameField @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
        defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val balloons = ArrayList<Balloon>()
    private val paint = Paint()

    init {
        paint.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawBalloons(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        generateAndDrawCircle()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                return onUserTouched(event.x, event.y)
            }
        }
        return false
    }

    private fun onUserTouched(x: Float, y: Float): Boolean {
        val balloonsIterator = balloons.iterator()
        while (balloonsIterator.hasNext()) {
            val balloon = balloonsIterator.next()
            if (balloon.isTouched(x, y)) {
                balloonsIterator.remove()
                invalidate()
                generateAndDrawCircle()
                return true
            }
        }

        return false
    }

    private fun generateAndDrawCircle() {
        balloons.add(Balloon.newRandomInstance(width, height))
        invalidate()
    }

    private fun drawBalloons(canvas: Canvas?) {
        canvas?.let {
            balloons.forEach { item ->
                paint.color = item.color
                canvas.drawCircle(item.x, item.y, item.radius, paint)
            }
        }
    }
}
