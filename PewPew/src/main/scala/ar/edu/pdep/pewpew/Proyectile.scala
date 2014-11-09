package ar.edu.pdep.pewpew

import com.uqbar.vainilla.appearances.Appearance
import com.uqbar.vainilla.DeltaState

class Proyectile(appearance: Appearance, scene: PewPewGameScene, x: Double, y: Double, val damage: Int, ySpeed: Double, val xSpeed: Double = 0.0) extends SpeedyComponent[PewPewGameScene] with RectangularGameComponent {

  this.setAppearance(appearance)
  this.setScene(scene)
  this.setX(x)
  this.setY(y)

  var impacts = 0
  def maxImpacts = 1

  this.addCollisionEvent((CollisionGroups.ENEMY,
    (anEnemy: CollisionDrivenGameComponent) => {
      anEnemy.asInstanceOf[EnemyShip].takeDamage(calculateDamage)
      this.impact
    }))

  this.speed = (xSpeed, -ySpeed)
  
  override def update(state: DeltaState) = {
    super.update(state)
    checkCollisions
    applySpeed(state)
    if (this.isOutsideOfTheScreen) {
      this.destroy
    }
  }
  
  def maxDamage = damage
  
  def calculateDamage = (maxDamage * (PewPewGame.randomizer.nextDouble * 0.5 + 0.5)).toInt

  def impact: Unit = {
    impacts += 1
    if (impacts >= maxImpacts) {
      this.getScene.addComponent(impactEffect)
      this.destroy
    }
  }

  def impactEffect = {
    ImpactEffect.laserShotImpact(this)
  }

  override def getMaxX = Double.PositiveInfinity
  override def getMaxY = Double.PositiveInfinity

  override def getMinX = Double.NegativeInfinity
  override def getMinY = Double.NegativeInfinity
}