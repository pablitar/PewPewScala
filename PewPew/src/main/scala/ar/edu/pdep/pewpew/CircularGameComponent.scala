package ar.edu.pdep.pewpew

import com.uqbar.vainilla.colissions.CollisionDetector
import CollisionDrivenGameComponent._
import java.awt.Graphics2D
import java.awt.Color

trait CircularGameComponent extends CollisionDrivenGameComponent {
  
  def getR:Int = ((this.getWidth min this.getHeight) / 2).toInt
  
  lazy val circleOffset: Vector2D = Vector2D(-getR,-getR)
  
  def circleCenter = this.position + circleOffset

  def hasCollidedWith(aComponent: CollisionDrivenGameComponent) = {
    aComponent.hasCollidedWithCircle(this.circleCenter.x1, this.circleCenter.x2, this.getR)
  }

  def hasCollidedWithCircle(x: Double, y: Double, r: Int) = {
    detector.collidesCircleAgainstCircle(this.circleCenter.x1, this.circleCenter.x2, this.getR, x, y, r)
  }
  
  def hasCollidedWithRect(x: Double, y: Double, w: Int, h:Int) = {
    detector.collidesCircleAgainstRect(this.circleCenter.x1, this.circleCenter.x2, this.getR, x, y, w, h)
  }
  
  def diameter = getR * 2

  override def renderHitbox(graphics: Graphics2D) = {
    graphics.setColor(Color.YELLOW);
		graphics.fillOval(this.circleCenter.x1.toInt, this.circleCenter.x2.toInt, diameter, diameter);
  }
  

}