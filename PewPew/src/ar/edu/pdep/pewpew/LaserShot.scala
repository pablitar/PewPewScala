package ar.edu.pdep.pewpew

import com.uqbar.vainilla.GameComponent
import com.uqbar.vainilla.DeltaState

class LaserShot(scene: PewPewGameScene, x: Double, y: Double) extends SpeedyComponent[PewPewGameScene] with RectangularGameComponent {

  this.setAppearance(ResourceManager.LASERSHOT_SPRITE)
  this.setScene(scene)
  this.setX(x)
  this.setY(y)
  
  this.addCollisionEvent((CollisionGroups.ENEMY,
    (anEnemy: CollisionDrivenGameComponent) => {
      anEnemy.asInstanceOf[EnemyShip].takeDamage(10)
      this.impact
    }))

  this.speed = (0.0, -1500.0)

  override def update(state: DeltaState) = {
    super.update(state)
    checkCollisions
    applySpeed(state)
    if (this.isOutsideOfTheScreen) {
      this.destroy
    }
  }

  def impact: Unit = {
    this.getScene.addComponent(ImpactEffect.laserShotImpact(this))
    this.destroy
  }

  override def getMaxX = Double.PositiveInfinity
  override def getMaxY = Double.PositiveInfinity

  override def getMinX = Double.NegativeInfinity
  override def getMinY = Double.NegativeInfinity
}