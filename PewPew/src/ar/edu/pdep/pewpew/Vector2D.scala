package ar.edu.pdep.pewpew

object Vector2D {
  implicit def toVector2D(t1: (Double, Double)) = Vector2D(t1._1, t1._2)
}

case class Vector2D(var x1: Double, var x2: Double) {
  def +(aVector: Vector2D) = Vector2D(aVector.x1 + x1, aVector.x2 + x2)

  def *(aScalar: Double) = Vector2D(x1 * aScalar, x2 * aScalar)

  private def absoluteMin(x: Double, y: Double): Double =
    if (x.abs <= y.abs) x else y.abs * x.signum

  def absoluteMin(aVector: Vector2D): Vector2D =
    Vector2D(absoluteMin(x1, aVector.x1), absoluteMin(x2, aVector.x2))

  def toZero(aVector: Vector2D) =
    Vector2D(x1.signum * aVector.x1 * -1, x2.signum * aVector.x2 * -1)
  
  def sumAndClip(aVector: Vector2D) =
    (this + aVector).clipBySign(this)
    
  def clipBySign(aVector: Vector2D) =
    Vector2D(clipNum(x1, aVector.x1), clipNum(x2, aVector.x2))
  
  def clipNum(aNumber:Double, anotherNumber:Double) =
    if(aNumber.signum * anotherNumber.signum == -1) 0 else aNumber
    
  def versor : Vector2D = 
    Vector2D(x1.signum, x2.signum)
  
  def abs : Vector2D = 
    Vector2D(x1.abs, x2.abs)
    
  override def toString: String = "(" + x1 + ", " + x2 + ")"
}