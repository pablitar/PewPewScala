package ar.edu.pdep.pewpew

object MathUtils {
  def bound(aNumber: Double, lowerBound: Double, upperBound: Double) = aNumber max lowerBound min upperBound

  implicit def double2Int(double: Double): Int = double.toInt
}