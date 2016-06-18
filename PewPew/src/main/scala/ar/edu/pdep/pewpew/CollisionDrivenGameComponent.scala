package ar.edu.pdep.pewpew

import com.uqbar.vainilla.GameComponent
import ar.edu.pdep.pewpew.CollisionGroup.toFunction
import java.awt.Graphics2D

object CollisionDrivenGameComponent {
  val detector = com.uqbar.vainilla.colissions.CollisionDetector.INSTANCE
}

trait CollisionDrivenGameComponent extends RichGameComponent[PewPewGameScene] {
  var collisionEvents: List[(CollisionGroup, CollisionAction)] = List()

  def checkCollisions: Unit = {
    for ((group, action) <- collisionEvents) {
      for (component <- this.getScene.collisionableGameComponents if group.isInCollisionGroup(component) && this.hasCollidedWith(component)) {
        action.collideWith(component)
      }
    }
  }
  
  def addCollisionEvent(anEvent:(CollisionGroup, CollisionAction)) = 
    collisionEvents = collisionEvents :+ anEvent

  def hasCollidedWith(aComponent: CollisionDrivenGameComponent): Boolean

  def hasCollidedWithCircle(x: Double, y: Double, r: Int): Boolean

  def hasCollidedWithRect(x: Double, y: Double, w: Int, h:Int): Boolean
  
  val debugHitbox = false
  
  override def render(graphics: Graphics2D) = {
    super.render(graphics)
    if(debugHitbox) {
      this.renderHitbox(graphics)
    }
  }

  def renderHitbox(graphics: Graphics2D) = {}
  
}