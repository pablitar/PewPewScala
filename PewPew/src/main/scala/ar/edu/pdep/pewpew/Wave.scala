package ar.edu.pdep.pewpew

import com.uqbar.vainilla.GameComponent
import com.uqbar.vainilla.DeltaState

class Wave(x: Double, y: Double, waveLength: Int, waveCooldown: Double) extends GameComponent[PewPewGameScene] {
  var toSpawn = waveLength

  var currentCooldown = waveCooldown

  override def update(state: DeltaState): Unit = {
    currentCooldown -= state.getDelta
    if (currentCooldown <= 0) {
      this.getScene.addComponent(EnemyShip.default(x, y))
      currentCooldown = waveCooldown
      toSpawn -= 1
      if (toSpawn <= 0) {
        this.destroy
      }
    } 
  }

}