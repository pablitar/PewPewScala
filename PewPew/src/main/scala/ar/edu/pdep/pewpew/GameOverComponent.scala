package ar.edu.pdep.pewpew

import com.uqbar.vainilla.GameComponent
import com.uqbar.vainilla.appearances.Label
import java.awt.Font
import java.awt.Color
import com.uqbar.vainilla.events.constants.Key

class GameOverComponent(scene: PewPewGameScene) extends EventDrivenGameComponent[PewPewGameScene] {
  this.setScene(scene)

  val gameOverLabel = new Label(new Font(Font.SERIF, Font.BOLD, 48), Color.ORANGE, "Game Over!", "Press enter to restart")
  
  this.setAppearance(gameOverLabel)
  
  this.setX(this.getGame.getDisplayWidth / 2 - gameOverLabel.getWidth / 2)

  this.setY(300)
  this.setZ(4)

  withEvents(List(
    (Key.ENTER, () => this.getScene.restartOnEnd)))

}