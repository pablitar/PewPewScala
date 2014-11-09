package ar.edu.pdep.pewpew

import com.uqbar.vainilla.GameComponent
import com.uqbar.vainilla.DeltaState

trait Weapon extends GameComponent[PewPewGameScene] {

  var firing = false
  var cooldown = 0.0

  def coolDownTime: Double

  override def update(state: DeltaState): Unit = {
    super.update(state)

    if (this.firing) {
      this.coolDownAndFire(state.getDelta)
    }

    this.cooldown = (this.cooldown - state.getDelta) max 0
  }

  def firePressed {
    this.firing = true
  }

  def fireReleased {
    this.firing = false
  }

  def coolDownAndFire(delta: Double): Unit = {
    if (this.cooldown - delta <= 0) {
      this.doFire
      this.cooldown = coolDownTime
    }
  }

  def doFire

}