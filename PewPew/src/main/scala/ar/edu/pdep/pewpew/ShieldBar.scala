package ar.edu.pdep.pewpew

import com.uqbar.vainilla.GameComponent
import java.awt.Color

class ShieldBar(ship:PlayerShip, x:Double, y:Double) extends Bar("Shield", Color.CYAN, x, y) {
  
  def getCurrentValue = ship.shield.toInt
  def getMaxValue = ship.maxShield.toInt
  
}