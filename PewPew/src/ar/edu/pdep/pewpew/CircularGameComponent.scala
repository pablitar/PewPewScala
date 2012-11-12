package ar.edu.pdep.pewpew

import com.uqbar.vainilla.colissions.CollisionDetector
import CollisionDrivenGameComponent._

trait CircularGameComponent extends CollisionDrivenGameComponent {
  def getR:Int = ((this.getWidth min this.getHeight) / 2).toInt

  def hasCollidedWith(aComponent: CollisionDrivenGameComponent) = {
    aComponent.hasCollidedWithCircle(this.getX, this.getY, this.getR)
  }

  def hasCollidedWithCircle(x: Double, y: Double, r: Int) = {
    detector.collidesCircleAgainstCircle(this.getX, this.getY, this.getR, x, y, r)
  }
  
  def hasCollidedWithRect(x: Double, y: Double, w: Int, h:Int) = {
    detector.collidesCircleAgainstRect(this.getX, this.getY, this.getR, x, y, w, h)
  }

}