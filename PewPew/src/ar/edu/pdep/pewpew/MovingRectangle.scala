package ar.edu.pdep.pewpew

import com.uqbar.vainilla.GameComponent
import java.awt.Color
import com.uqbar.vainilla.appearances.Rectangle
import com.uqbar.vainilla.DeltaState
import com.uqbar.vainilla.events.constants.Key
import java.awt.Point
import java.awt.geom.Point2D
import Vector2D._

class MovingRectangle(scene: PewPewGameScene, color: Color) extends MovableWithKeyboard[PewPewGameScene] {

  this.setScene(scene)
  this.setAppearance(new Rectangle(color, 32, 32))

  def maxSpeed = (1000.0, 1000.0)
  def maxAccel = (4000.0, 4000.0)
  override def deaccelRate = 0.5
}