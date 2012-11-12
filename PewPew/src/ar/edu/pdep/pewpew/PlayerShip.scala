package ar.edu.pdep.pewpew

import com.uqbar.vainilla.appearances.Rectangle
import java.awt.Color
import com.uqbar.vainilla.events.constants.Key
import com.uqbar.vainilla.DeltaState

class PlayerShip(scene:PewPewGameScene) extends MovableWithKeyboard[PewPewGameScene] with CircularGameComponent {

  val coolDownTime = 0.12

  var firing = false
  var cooldown = 0.0

  this.setZ(100)
  this.setScene(scene)
  this.setAppearance(ResourceManager.SPACE_SHIP_SPRITE)
  
  this.setX(this.getGame.getDisplayWidth / 2)
  this.setY(this.getGame.getDisplayHeight * 0.8)  


  withEvents(
    List(
      (Key.CTRL, () => this.firePressed),
      (release(Key.CTRL), () => this.fireReleased)))

  def maxAccel(): Vector2D = (3000.0, 3000.0)

  def maxSpeed(): Vector2D = (600.0, 600.0)

  override def update(state: DeltaState): Unit = {
    super.update(state)

    if (this.firing) {
      this.coolDownAndFire(state.getDelta)
    }

    this.cooldown = (this.cooldown - state.getDelta) max 0
  }

  def coolDownAndFire(delta: Double): Unit = {
    if (this.cooldown - delta <= 0) {
      this.doFire
      this.cooldown = coolDownTime
    }
  }

  def doFire = {
    this.getScene.addComponent(new LaserShot(this.getScene, this.getX, this.getY))
  }

  def firePressed: Unit = {
    this.firing = true
  }

  def fireReleased: Unit = {
    this.firing = false
  }

  def getCenterX: Double = {
    this.getX + this.getAppearance.getWidth / 2
  }
  
  def takeDamage(damage:Int) = {
    
  }
}