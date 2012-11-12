package ar.edu.pdep.pewpew

import com.uqbar.vainilla.GameComponent

object CollisionGroup {
  implicit def toCollisionGroup(condition: GameComponent[_] => Boolean): CollisionGroup = 
    new CollisionGroup() { def isInCollisionGroup(aComponent: GameComponent[_]) = condition(aComponent) }
  
  implicit def toFunction(aCollisionGroup:CollisionGroup):(GameComponent[_] => Boolean) =
    aCollisionGroup.isInCollisionGroup(_)
}

abstract class CollisionGroup {
  def isInCollisionGroup(aComponent: GameComponent[_]): Boolean
}