package ar.edu.pdep.pewpew

import com.uqbar.vainilla.appearances.Sprite
import com.uqbar.vainilla.appearances.Animation
import scala.math.Pi

object ResourceManager {
  val SPRITES_SCALE = 0.8

  val SPACE_SHIP_SPRITE = Sprite.fromImage("/png/player.png").scale(SPRITES_SCALE).center();
  val LASERSHOT_SPRITE = Sprite.fromImage("/png/laserRed.png").scale(SPRITES_SCALE).center();
  val LASERSHOT_IMPACT_SPRITE = Sprite.fromImage("/png/laserRedShot.png").scale(SPRITES_SCALE).center();

  val EXPLOSION_SPRITE = Sprite.fromImage("/png/explosion.png").scale(SPRITES_SCALE).center();

  val EXPLOSION = new Animation(0.1, EXPLOSION_SPRITE, EXPLOSION_SPRITE.rotate(Pi / 4).center(),
      EXPLOSION_SPRITE.rotate(Pi / 2).center(), 
      EXPLOSION_SPRITE.rotate(Pi * 0.75).center(),
      EXPLOSION_SPRITE.rotate(Pi).center())

  val ENEMY_SHIP_SPRITE = Sprite.fromImage("/png/enemyShip.png").scale(SPRITES_SCALE).center();

  def initializeResources: Unit = {

  }
}