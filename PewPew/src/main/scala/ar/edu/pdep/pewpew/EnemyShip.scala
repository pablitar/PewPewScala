package ar.edu.pdep.pewpew

import com.uqbar.vainilla.DeltaState
import Behaviour._
import Vector2D._
import com.uqbar.vainilla.appearances.Appearance

object EnemyShip {
  def default(x: Double, y: Double): EnemyShip = new EnemyShip(25, x, y).withBehaviour(zigZagMovement((250.0, 70.0)))
  def ufo(x: Double, y: Double, spread: Double = 0): EnemyShip = new UFO(x, y).withBehaviour(straightMovement(300, (PewPewGame.randomizer.nextDouble - 0.5) * spread))
}

class EnemyShip(health: Int, x: Double, y: Double, app: Appearance = ResourceManager.ENEMY_SHIP_SPRITE) extends RectangularGameComponent with SpeedyComponent[PewPewGameScene] {

  val behaviour = new NullBehaviour(this)

  var currentHealth = health

  this.setX(x)
  this.setY(y)

  this.addCollisionEvent((CollisionGroups.PLAYER,
    (aPlayer: CollisionDrivenGameComponent) => {
      aPlayer.asInstanceOf[PlayerShip].takeDamage(currentHealth)
      this.explode
    }))

  this.setAppearance(app)

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

  //TODO: Listener
  def explode: Unit = {
    this.getScene.addComponent(ImpactEffect.explosion(this))
    this.getScene.sumScore
    this.destroy()
  }

  override def getMaxY = Double.PositiveInfinity
  override def getMinY = Double.NegativeInfinity

}

class UFO(x: Double, y: Double) extends EnemyShip(10, x, y, ResourceManager.ENEMY_UFO_SPRITE) {
  override def getMaxX: Double = Double.PositiveInfinity
  override def getMinX: Double = Double.NegativeInfinity
}