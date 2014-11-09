package ar.edu.pdep.pewpew

import com.uqbar.vainilla.GameScene
import com.uqbar.vainilla.GameComponent
import com.uqbar.vainilla.appearances.Rectangle
import java.awt.Color
import com.uqbar.vainilla.Game
import scala.collection.JavaConversions._
import com.uqbar.vainilla.appearances.Label
import java.awt.Font
import java.awt.Graphics2D

class PewPewGameScene(game: Game) extends GameScene {
  this.setGame(game)

  this.addComponent(new StarFieldBackground(this))

  val playerShip = new PlayerShip(this)

  this.addComponent(playerShip)

  this.addComponent(new HealthBar(playerShip, 10, 10))
  this.addComponent(new ShieldBar(playerShip, 10, 40))

  this.addComponent(new EnemySpawner(2.0, 10, 0.4))
  
  val scoreDisplay = new ScoreDisplay(this)
  
  this.addComponent(scoreDisplay)

  def collisionableGameComponents: List[CollisionDrivenGameComponent] =
    this.getComponents.filter(_.isInstanceOf[CollisionDrivenGameComponent]).map(_.asInstanceOf[CollisionDrivenGameComponent]).toList

  def gameOver = {
    this.addComponent(new GameOverComponent(this));
  }

  def restart = {
    this.getGame.setCurrentScene(new PewPewGameScene(this.getGame))
  }

  var isRestartOnEnd = false

  def restartOnEnd = {
    isRestartOnEnd = true
  }

  override def takeStep(graphics: Graphics2D) = {
    super.takeStep(graphics)

    if (isRestartOnEnd) restart
  }
  
  def sumScore = {
    scoreDisplay.sumScore
  }
}