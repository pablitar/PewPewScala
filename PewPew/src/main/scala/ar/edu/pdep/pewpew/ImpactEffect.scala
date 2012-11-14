package ar.edu.pdep.pewpew

import com.uqbar.vainilla.GameScene
import com.uqbar.vainilla.DeltaState
import com.uqbar.vainilla.appearances.Appearance

object ImpactEffect {
  def laserShotImpact(shot: LaserShot) = new ImpactEffect(shot, ResourceManager.LASERSHOT_IMPACT_SPRITE, 0.3)
  def explosion[T <: GameScene](component: SpeedyComponent[T]) = new ImpactEffect(component, ResourceManager.EXPLOSION.copy(), 0.5)
}

class ImpactEffect[T <: GameScene](component: SpeedyComponent[T], appearance: Appearance, duration: Double) extends SpeedyComponent[T] {
  var current = 0.0

  this.initializeFrom(component)

  speed = component.speed * 0.1

  this.setAppearance(appearance)

  override def update(state: DeltaState): Unit = {
    super.update(state)
    if (current >= duration) {
      this.destroy
    } else {
      applySpeed(state)
      current += state.getDelta
    }
  }

}