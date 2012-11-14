package ar.edu.pdep.pewpew

import com.uqbar.vainilla.DeltaState

class Explosion(component: SpeedyComponent[PewPewGameScene]) extends SpeedyComponent[PewPewGameScene] {
  this.speed = component.speed * 0.1

  this.initializeFrom(component)

  this.setAppearance(ResourceManager.EXPLOSION)

  //TODO: Es igual a lasershot impact

  val duration = 0.3
  var current = 0.0

  override def update(state: DeltaState): Unit = {
    if (current >= duration) {
      this.destroy
    } else {
      applySpeed(state)
      current += state.getDelta
    }
  }
}