package ar.edu.pdep.pewpew

import com.uqbar.vainilla.DeltaState

object Behaviour {
  def zigZagMovement(speed:Vector2D) = (aShip:EnemyShip) => new ZigZagBehaviour(aShip, speed)
  def straightMovement(speed: Double, xSpeed:Double = 0) = (aShip:EnemyShip) => new StraightBehaviour(aShip, (xSpeed, speed))
}

class NullBehaviour(val ship: EnemyShip) {

  var nextBehaviour: Option[NullBehaviour] = None

  def update(state: DeltaState): Unit = {
    if (this.doUpdate(state)) {
      this.callNextBehaviour(state)
    }
  }

  def doUpdate(state: DeltaState): Boolean = true

  def callNextBehaviour(state: DeltaState): Unit = {
    nextBehaviour match {
      case Some(behaviour) => behaviour.update(state)
      case None => ()
    }
  }
  
  def setNextBehaviour(aBehaviour: NullBehaviour): Unit = {
    nextBehaviour match {
      case Some(behaviour) => behaviour.setNextBehaviour(aBehaviour)
      case None => nextBehaviour = Some(aBehaviour) 
    }
  }

}