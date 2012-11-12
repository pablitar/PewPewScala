package ar.edu.pdep.pewpew

import com.uqbar.vainilla.GameComponent
import com.uqbar.vainilla.GameScene
import com.uqbar.vainilla.DeltaState
import scala.collection.mutable.Set
import com.uqbar.vainilla.events.constants.Key

trait EventDrivenGameComponent[T <: GameScene] extends RichGameComponent[T] {
  def hold(aKey:Key) = (state: DeltaState) => state.isKeyBeingHold(aKey)
  def press(aKey:Key) = (state: DeltaState) => state.isKeyPressed(aKey)
  def release(aKey:Key) = (state: DeltaState) => state.isKeyReleased(aKey)

  type KeyEventType = Int

  implicit def toPredicate(aKey: Key) = press(aKey)
  implicit def toAction(aDeltaAction: Double => Unit) = (state: DeltaState) => aDeltaAction(state.getDelta())
  implicit def toAction(anAction: () => Unit) = (state: DeltaState) => anAction()

  type Predicate = (DeltaState => Boolean)
  type Action = (DeltaState => Unit)
  type Event = (Predicate, Action)

  val events: Set[Event] = Set()

  def addEvent(condition: Predicate, action: Action) = events.add((condition, action))

  def withEvents(someEvents: TraversableOnce[Event]) = events ++= someEvents

  override def update(state: DeltaState): Unit = {
    super.update(state)
    for ((predicate, action) <- events if (predicate(state))) yield {
      action(state)
    }
  }
}