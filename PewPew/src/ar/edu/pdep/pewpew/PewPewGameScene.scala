package ar.edu.pdep.pewpew

import com.uqbar.vainilla.GameScene

import com.uqbar.vainilla.GameComponent
import com.uqbar.vainilla.appearances.Rectangle
import java.awt.Color
import com.uqbar.vainilla.Game

import scala.collection.JavaConversions._

class PewPewGameScene(game:Game) extends GameScene {
  this.setGame(game)
  
  this.addComponent(new PlainBackground(this, Color.darkGray))
  
  this.addComponent(new PlayerShip(this))
  
  this.addComponent(new EnemySpawner(8.0, 10, 0.5))
  
  def collisionableGameComponents:List[CollisionDrivenGameComponent] = 
    this.getComponents.filter(_.isInstanceOf[CollisionDrivenGameComponent]).map(_.asInstanceOf[CollisionDrivenGameComponent]).toList 
}