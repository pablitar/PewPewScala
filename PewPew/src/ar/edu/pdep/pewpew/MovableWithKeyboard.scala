package ar.edu.pdep.pewpew

import com.uqbar.vainilla.GameComponent
import com.uqbar.vainilla.DeltaState
import Vector2D._
import com.uqbar.vainilla.events.constants.Key
import com.uqbar.vainilla.GameScene

trait MovableWithKeyboard[T <: GameScene] extends EventDrivenGameComponent[T] with SpeedyComponent[T] {
  var accel: Vector2D = (0.0, 0.0)

  def maxAccel: Vector2D
  def maxSpeed: Vector2D
  def deaccelRate: Double = 0.5

  withEvents (List(
    (hold(Key.LEFT), () => accel.x1 = -maxAccel.x1),
    (hold(Key.RIGHT), () => accel.x1 = maxAccel.x1),
    (hold(Key.UP), () => accel.x2 = -maxAccel.x2),
    (hold(Key.DOWN), () => accel.x2 = maxAccel.x2),
    (hold(Key.END), () => accel = (-maxAccel.x1, maxAccel.x2)),
    (hold(Key.HOME), () => accel = (-maxAccel.x1, -maxAccel.x2)),
    (hold(Key.PGDN), () => accel = (maxAccel.x1, maxAccel.x2)),
    (hold(Key.PGUP), () => accel = (maxAccel.x1, -maxAccel.x2)))
  )
  
  def toIdleAccel:Vector2D = 
    speed.toZero(maxAccel)

  override def update(state: DeltaState): Unit = {
    accel = (0.0, 0.0)
    
    super.update(state)

    this.speed = (this.speed + accel * state.getDelta) absoluteMin maxSpeed
    this.speed = (this.speed.sumAndClip(this.speed.toZero(this.getDeaccelVector*state.getDelta)))
    
    this.applySpeed(state)
  }
  
  def getDeaccelVector:Vector2D =
    (if(accel.x1 == 0) maxAccel.x1 else 0.0, if(accel.x2 == 0) maxAccel.x2 else 0.0)*this.deaccelRate
}