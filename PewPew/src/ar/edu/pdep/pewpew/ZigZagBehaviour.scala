package ar.edu.pdep.pewpew

import com.uqbar.vainilla.DeltaState

class ZigZagBehaviour(ship: EnemyShip, targetSpeed: Vector2D) extends NullBehaviour(ship) {

  var xDirection = if (PewPewGame.randomizer.nextDouble >= 0.5) 1 else -1

  override def doUpdate(state: DeltaState): Boolean = {
    this.removeIfOutsideTheScreen
    ship.speed = this.getSpeedForShip
    ship.applySpeed(state)
    true
  }

  def removeIfOutsideTheScreen: Unit = {
    if((targetSpeed.x2 >= 0 && ship.isBelowTheScreen) || (targetSpeed.x2 <= 0 && ship.isOverTheScreen)) {
      ship.destroy
    }
  }

  def getSpeedForShip: Vector2D = {
    if (ship.getX <= ship.getMinX) {
      xDirection = 1
    } else if (ship.getX >= ship.getMaxX) {
      xDirection = -1
    }

    (targetSpeed.x1 * xDirection, targetSpeed.x2)
  }

}