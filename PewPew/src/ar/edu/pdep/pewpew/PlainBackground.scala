package ar.edu.pdep.pewpew

import com.uqbar.vainilla.GameComponent
import java.awt.Color
import com.uqbar.vainilla.appearances.Rectangle
import com.uqbar.vainilla.GameScene

class PlainBackground(scene: PewPewGameScene, val color: Color) extends GameComponent[PewPewGameScene] {
  this.setScene(scene)
  this.setAppearance(new Rectangle(color, this.getGame().getDisplayWidth(), this.getGame().getDisplayHeight()))
}