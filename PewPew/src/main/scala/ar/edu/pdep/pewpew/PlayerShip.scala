package ar.edu.pdep.pewpew

import com.uqbar.vainilla.DeltaState
import com.uqbar.vainilla.events.constants.Key
import com.uqbar.vainilla.appearances.Circle

class PlayerShip(scene: PewPewGameScene) extends MovableWithKeyboard[PewPewGameScene] with CircularGameComponent {

  val coolDownTime = 0.12

  val maxHealth = 100
  val maxShield = 50.0
  val shieldRegenRate = 3.0

  var health = maxHealth
  var shield = maxShield

  val laserShot = new LaserShotWeapon(scene, this)
  val bazooka = new Bazooka(scene, this)

  var weapon: Weapon = laserShot

  this.setZ(2)
  this.setScene(scene)
  this.setAppearance(ResourceManager.SPACE_SHIP_SPRITE)
  
  override def getR:Int = ((this.getWidth min this.getHeight) / 8).toInt 

  this.setX(this.getGame.getDisplayWidth / 2)
  this.setY(this.getGame.getDisplayHeight * 0.8)
  
  override val debugHitbox = true

  withEvents(
    List(
    		(release(Key.CTRL), () => this.fireReleased),
      (Key.CTRL, () => this.firePressed),
      (Key.TAB, () => this.swapWeapon)))

  def swapWeapon {
    weapon =
      if (weapon == laserShot)
        bazooka
      else laserShot
  }

  def maxAccel(): Vector2D = (3000.0, 3000.0)

  def maxSpeed(): Vector2D = (600.0, 600.0)

  override def update(state: DeltaState): Unit = {
    super.update(state)

    weapon.update(state)

    this.regenShield(state: DeltaState)
  }

  def regenShield(state: DeltaState): Unit = {
    this.shield = (this.shield + shieldRegenRate * state.getDelta) min maxShield
  }

  def firePressed: Unit = {
    weapon.firePressed
  }

  def fireReleased: Unit = {
    weapon.fireReleased
  }

  def getCenterX: Double = {
    this.getX + this.getAppearance.getWidth / 2
  }

  def takeDamage(damage: Int) = {
    val damageToHealth = (damage - this.shield) max 0
    this.shield = (shield - damage) max 0

    this.health = (this.health - damageToHealth).toInt max 0

    if (this.health <= 0) {
      this.death
    }
  }

  def death: Unit = {
    this.scene.addComponent(ImpactEffect.explosion(this))
    this.scene.gameOver
    this.destroy
  }
}