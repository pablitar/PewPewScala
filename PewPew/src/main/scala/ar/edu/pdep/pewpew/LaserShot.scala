package ar.edu.pdep.pewpew

import com.uqbar.vainilla.GameComponent
import com.uqbar.vainilla.DeltaState

class LaserShot(scene: PewPewGameScene, x: Double, y: Double, xSpeed:Double) extends Proyectile(ResourceManager.LASERSHOT_SPRITE, scene, x, y, 12, 1200, xSpeed) 