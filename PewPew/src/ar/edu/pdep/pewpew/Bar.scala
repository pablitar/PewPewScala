package ar.edu.pdep.pewpew

import com.uqbar.vainilla.GameComponent
import java.awt.Color

object Bar {
  val defaultBackColor = Color.GRAY

  val barWidth = 300
}

abstract class Bar(val label: String, frontColor: Color, backColor: Color, x: Double, y: Double) extends GameComponent[PewPewGameScene] {

  this.setX(x)
  this.setY(y)

  this.setZ(3)

  def this(label: String, frontColor: Color, x: Double, y: Double) = this(label, frontColor, Bar.defaultBackColor, x, y)

  def getMaxValue: Int
  def getCurrentValue: Int

  this.setAppearance(new BarAppearance(this, Bar.barWidth, backColor, frontColor))

}