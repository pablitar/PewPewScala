package ar.edu.pdep.pewpew

import ar.edu.pdep.pewpew.Vector2D.toVector2D

class StraightBehaviour(ship: EnemyShip, speed: Vector2D) extends MovementBehaviour(ship) {

  def getSpeedForShip = speed

  override def removeIfOutsideTheScreen {
	 super.removeIfOutsideTheScreen
	 if(ship.isAtLeftOfTheScreen || ship.isAtRightOfTheScreen) {
	   ship.destroy()
	 }
  }
}