package ar.edu.pdep.pewpew

import CollisionDrivenGameComponent._
import java.awt.Graphics2D
import java.awt.Color

trait RectangularGameComponent extends CollisionDrivenGameComponent {

  def hasCollidedWith(aComponent: CollisionDrivenGameComponent) = {
    aComponent.hasCollidedWithRect(this.leftBorder, this.topBorder, this.getWidth.toInt, this.getHeight.toInt)
  }

  def hasCollidedWithCircle(x: Double, y: Double, r: Int) = {
    detector.collidesCircleAgainstRect(x, y, r, this.leftBorder, this.topBorder, this.getWidth, this.getHeight)
  }

  def hasCollidedWithRect(x: Double, y: Double, w: Int, h: Int) = {
    detector.collidesRectAgainstRect(x, y, w, h, this.leftBorder, this.topBorder, this.getWidth.toInt, this.getHeight.toInt)
  }

  override def renderHitbox(graphics: Graphics2D) = {
    graphics.setColor(Color.YELLOW);
    graphics.fillRect(this.leftBorder.toInt, this.topBorder.toInt, this.getWidth.toInt, this.getHeight.toInt);
  }

}