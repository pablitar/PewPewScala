package ar.edu.pdep.pewpew

import PewPewGame._
import com.uqbar.vainilla.appearances.Rectangle
import java.awt.Color
import com.uqbar.vainilla.DeltaState

object Star {
  val maxSpeed = 250
  val minSpeed = 100
}

class Star(x: Double, y: Double) extends SpeedyComponent[PewPewGameScene] {
  this.setX(x)
  this.setY(y)
  this.setZ(-1)

  initSpeed
  
  val size = randomizer.nextInt(4) + 1

  this.setAppearance(new Rectangle(Color.LIGHT_GRAY, size, size))

  override def getMaxY = Double.PositiveInfinity
  override def getMinY = Double.NegativeInfinity

  override def update(state: DeltaState) = {
    super.update(state)
    this.applySpeed(state)
    if (this.isBelowTheScreen) {
      this.setY(-3);
      this.setX(randomizer.nextDouble * this.getMaxX)
      initSpeed
    }
  }
  
  def initSpeed = {
    speed = (0.0, randomizer.nextDouble * (Star.maxSpeed-Star.minSpeed) + Star.minSpeed)
  }
}