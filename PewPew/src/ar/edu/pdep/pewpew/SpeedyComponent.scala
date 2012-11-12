package ar.edu.pdep.pewpew

import com.uqbar.vainilla.DeltaState
import com.uqbar.vainilla.GameScene

import MathUtils.bound
import ar.edu.pdep.pewpew.Vector2D.toVector2D

trait SpeedyComponent[T <: GameScene] extends RichGameComponent[T] {
  
  def getMinX = 0.0
  def getMinY = 0.0
  
  var speed: Vector2D = (0.0, 0.0)
  
  def applySpeed(state: DeltaState): Unit = {
    this.setX(bound(this.getX + (speed.x1 * state.getDelta), this.getMinX, this.getMaxX))
    this.setY(bound(this.getY + (speed.x2 * state.getDelta), this.getMinY, this.getMaxY))
  }
   
  def getMaxY:Double = this.getGame.getDisplayHeight
  def getMaxX:Double = this.getGame.getDisplayWidth
}