package ar.edu.pdep.pewpew

class UFOWave(x: Double, y: Double, waveLength: Int, waveCooldown: Double) extends Wave(x, y, waveLength, waveCooldown) {
  
  override def createShip = EnemyShip.ufo(x, y, 250)

}