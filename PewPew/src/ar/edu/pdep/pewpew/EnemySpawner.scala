package ar.edu.pdep.pewpew

import com.uqbar.vainilla.GameComponent
import com.uqbar.vainilla.DeltaState

class EnemySpawner(spawnCoolDown:Double, waveLength:Int, waveCooldown:Double) extends GameComponent[PewPewGameScene] {
  
  var spawnX:() => Double = () => PewPewGame.randomizer.nextDouble * this.getGame().getDisplayWidth().toDouble
  var spawnY:() => Double = () => -ResourceManager.ENEMY_SHIP_SPRITE.getHeight()
  
  var currentMaxCoolDown = spawnCoolDown
  var currentCoolDown = currentMaxCoolDown

  override def update(state: DeltaState): Unit = {
    currentCoolDown -= state.getDelta
    
    if(currentCoolDown <= 0) {
      this.createWave(state)
      currentCoolDown = currentMaxCoolDown
      //TODO: Hardcoded
      currentMaxCoolDown = (currentMaxCoolDown - 0.1) max 2
    }
  }
  
  def createWave(state:DeltaState):Unit = {
    this.getScene.addComponent(new Wave(spawnX(), spawnY(), waveLength, waveCooldown))
  }
}