package ar.edu.pdep.pewpew

import com.uqbar.vainilla.appearances.Label
import java.awt.Font
import java.awt.Color

class ScoreDisplay(scene: PewPewGameScene) extends RichGameComponent[PewPewGameScene] {
  var score = 0
  
  this.setScene(scene)

  this.updateLabel

  this.setX(this.getGame.getDisplayWidth - this.getWidth - 40)
  this.setY(10)

  def updateLabel = {
    this.setAppearance(new Label(new Font(Font.SANS_SERIF, Font.BOLD, 16), Color.WHITE, "Score: " + score))
  }

  def sumScore = {
    score += 1
    this.updateLabel
  }
}