package ar.edu.pdep.pewpew

import java.awt.Graphics2D
import com.uqbar.vainilla.appearances.SimpleAppearance
import java.awt.Color
import MathUtils.double2Int
import java.awt.FontMetrics
import java.awt.Font
import com.uqbar.vainilla.appearances.Label

class BarAppearance(bar: Bar, width: Int, height: Int, backColor: Color, frontColor: Color, marginRatio: Double) extends SimpleAppearance[BarAppearance] {

  //TODO: Font Color
  val label = new Label(new Font(Font.SANS_SERIF, Font.BOLD, 12), Color.WHITE, bar.label + ":")

  def this(bar: Bar, width: Int, backColor: Color, frontColor: Color) = this(bar, width, width / 20, backColor, frontColor, 0.02)

  def scale(scaleX: Double, scaleY: Double): BarAppearance = {
    new BarAppearance(bar, (width * scaleX).toInt, (height * scaleY).toInt, backColor, frontColor, marginRatio)
  }

  override def copy[BarAppearance](): BarAppearance = { this.clone.asInstanceOf[BarAppearance] }

  def getWidth = width
  def getHeight = height

  protected def doRenderAt(x: Int, y: Int, graphics: Graphics2D): Unit = {
    label.render(bar, graphics)
    
    graphics.setColor(backColor);
    val realX = x + label.getWidth() + 10
    graphics.fillRect(realX, y, width, height);

    val totalMargin = width * marginRatio

    graphics.setColor(frontColor);
    graphics.fillRect(realX + (totalMargin / 2), y + (totalMargin / 2), (width - totalMargin) * (bar.getCurrentValue.toDouble / bar.getMaxValue), height - totalMargin);
  }

  def update(delta: Double): Unit = {}

}