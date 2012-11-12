package ar.edu.pdep.pewpew

import com.uqbar.vainilla.DeltaState
import Behaviour._
import Vector2D._

object EnemyShip {
  def default(x: Double, y: Double): EnemyShip = new EnemyShip(25, x, y).withBehaviour(zigZagMovement((250.0, 70.0)))
}

class EnemyShip(health: Int, x: Double, y: Double) extends RectangularGameComponent with SpeedyComponent[PewPewGameScene] {

  val behaviour = new NullBehaviour(this)

  var currentHealth = health

  this.setX(x)
  this.setY(y)

  this.addCollisionEvent((CollisionGroups.PLAYER,
    (aPlayer: CollisionDrivenGameComponent) => {
      aPlayer.asInstanceOf[PlayerShip].takeDamage(health)
      this.explode
    }))

  this.setAppearance(ResourceManager.ENEMY_SHIP_SPRITE)

  override def update(state: DeltaState): Unit = {
    super.update(state)
    this.checkCollisions
    behaviour.update(state)
  }

  def takeDamage(damage: Int): Unit = {
    currentHealth -= damage
    if (currentHealth <= 0) {
      this.explode
    }
  }

  def withBehaviour(behaviourFactory: EnemyShip => NullBehaviour) = {
    behaviour.setNextBehaviour(behaviourFactory(this))
    this
  }

  def explode: Unit = {
    this.getScene.addComponent(ImpactEffect.explosion(this))
    this.destroy()
  }

  override def getMaxY = Double.PositiveInfinity
  override def getMinY = Double.NegativeInfinity

}