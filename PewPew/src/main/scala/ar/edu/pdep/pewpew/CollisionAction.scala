package ar.edu.pdep.pewpew

object CollisionAction {
  implicit def toCollisionAction(action: CollisionDrivenGameComponent => Unit) =
    new CollisionAction() { def collideWith(aComponent: CollisionDrivenGameComponent) = action(aComponent) }
}

abstract class CollisionAction {
  def collideWith(aComponent: CollisionDrivenGameComponent): Unit
}