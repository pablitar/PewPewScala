package ar.edu.pdep.pewpew

import ar.edu.pdep.pewpew.Vector2D.toVector2D

class ZigZagBehaviour(ship: EnemyShip, targetSpeed: Vector2D) extends MovementBehaviour(ship) {
  var xDirection = if (PewPewGame.randomizer.nextDouble >= 0.5) 1 else -1
  
  def getSpeedForShip = {
    if (ship.getX <= ship.getMinX) {
      xDirection = 1
    } else if (ship.getX >= ship.getMaxX) {
      xDirection = -1
    }

    (targetSpeed.x1 * xDirection, targetSpeed.x2)
  }
}