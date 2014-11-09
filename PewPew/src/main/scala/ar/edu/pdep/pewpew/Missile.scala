package ar.edu.pdep.pewpew

class Missile(scene: PewPewGameScene, x: Double, y: Double, xSpeed:Double) extends Proyectile(ResourceManager.MISSILE_SPRITE, scene, x, y, 40, 300, xSpeed) {
  
	override val maxImpacts = 4
  
	override def impactEffect = ImpactEffect.explosion(this)
	
}