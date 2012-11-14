package ar.edu.pdep.pewpew

import com.uqbar.vainilla.GameComponent
import scala.collection.mutable.Set
import PewPewGame._
import StarFieldBackground._
import com.uqbar.vainilla.DeltaState
import java.awt.Color
import java.awt.Graphics2D

object StarFieldBackground {
  val maxStars = 100
}

class StarFieldBackground(scene: PewPewGameScene) extends PlainBackground(scene, Color.DARK_GRAY) {
  this.setZ(-2)
  this.setScene(scene)

  this.initializeStars

  def initializeStars = {
    for (i <- 1 to maxStars) {
      scene.addComponent(new Star(randomizer.nextDouble * getGame.getDisplayWidth,
        randomizer.nextDouble * getGame.getDisplayHeight))
    }
  }
}