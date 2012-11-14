package ar.edu.pdep.pewpew

import CollisionGroup._
import com.uqbar.vainilla.GameComponent

object CollisionGroups {
  val ENEMY: CollisionGroup = (aComponent:GameComponent[_]) => aComponent.isInstanceOf[EnemyShip]
  val PLAYER: CollisionGroup = (aComponent:GameComponent[_]) => aComponent.isInstanceOf[PlayerShip]
}