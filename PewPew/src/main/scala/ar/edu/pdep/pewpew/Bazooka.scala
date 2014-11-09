package ar.edu.pdep.pewpew

class Bazooka(scene: PewPewGameScene, ship: PlayerShip) extends Weapon {
  
  setScene(scene)
  
  val coolDownTime = 1d

  def doFire = {
    //TODO: Repite con laserShot
    val xSpeed = (PewPewGame.randomizer.nextDouble - 0.5) * 100
    this.getScene.addComponent(new Missile(this.getScene, ship.getX, ship.getY, xSpeed))
  }
}