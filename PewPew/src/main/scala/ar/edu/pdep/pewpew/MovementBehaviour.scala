package ar.edu.pdep.pewpew

import com.uqbar.vainilla.DeltaState

abstract class MovementBehaviour(ship: EnemyShip) extends NullBehaviour(ship) {

  override def doUpdate(state: DeltaState): Boolean = {
    this.removeIfOutsideTheScreen
    ship.speed = this.getSpeedForShip
    ship.applySpeed(state)
    true
  }

  def removeIfOutsideTheScreen: Unit = {
    if ((getSpeedForShip.x2 >= 0 && ship.isBelowTheScreen) || (getSpeedForShip.x2 <= 0 && ship.isOverTheScreen)) {
      ship.destroy
    }
  }

  def getSpeedForShip: Vector2D

}