
package controller;

public class Event {
	public final boolean win;
	public final boolean loss;
	public final Direction movementDirection;
	public final boolean hasMoved;
	public final boolean pickedUpFood;

	private Event(boolean win, boolean loss, Direction movementDirection, boolean moved, boolean food) {
		this.win = win;
		this.loss = loss;
		this.movementDirection = movementDirection;
		this.hasMoved = moved;
		this.pickedUpFood = food;
	}

	public static class EventFactory {
		private boolean win;
		private boolean loss;
		private Direction movementDirection;
		private boolean pickedUpFood;

		public EventFactory() {
			this.win = false;
			this.loss = false;
			this.movementDirection = null;
			this.pickedUpFood = false;
		}

		public EventFactory setWin(boolean win) {
			this.win = win;
			return this;
		}

		public EventFactory setLoss(boolean loss) {
			this.loss = loss;
			return this;
		}

		public EventFactory setMovementDirection(Direction movementDirection) {
			this.movementDirection = movementDirection;
			return this;
		}

		public EventFactory setPickedUpFood(boolean pickedUpFood) {
			this.pickedUpFood = pickedUpFood;
			return this;
		}

		public Event buildEvent() {
			if (win && loss)
				throw new IllegalStateException("You can't win and loose at the same time!");
			if (!win && !loss && movementDirection == null)
				throw new IllegalStateException("You have to move if you didn't end the game!");
			if ((win || loss) && movementDirection != null)
				throw new IllegalStateException("You can't move if you won/lost the game!");
			if ((win || loss) && pickedUpFood)
				throw new IllegalStateException("You can't pick up food if you won/lost the game!");

			return new Event(win, loss, movementDirection, (!win && !loss), pickedUpFood);
		}
	}
}
