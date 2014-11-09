package ar.edu.pdep.pewpew

import com.uqbar.vainilla.GameScene

class LaserShotWeapon(scene: PewPewGameScene, ship: PlayerShip) extends Weapon {
  
  setScene(scene)

  val coolDownTime = 0.12

  def doFire = {
    val xSpeed = (PewPewGame.randomizer.nextDouble - 0.5) * 300
    this.getScene.addComponent(new LaserShot(this.getScene, ship.getX, ship.getY, xSpeed))
  }
}