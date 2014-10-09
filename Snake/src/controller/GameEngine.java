package controller;
import java.util.concurrent.TimeUnit;

import model.Field;
import model.GameState;
import model.IGameState;
import util.Settings;
import view.IGUI;


public class GameEngine {
	IGUI gui;
	IGameState gs;

	public GameEngine(IGUI gui) {
		this.gui = gui;
		this.gs = new GameState();
	}

	public void play() {
		Event event = null;
		long start;
		long elapsed;
		long wait;

		do {
			start = System.nanoTime();

			//Do work
			event = step();
			gui.update(gs, event);

			//Fps Stuff
			elapsed = System.nanoTime() - start;
			wait = TimeUnit.NANOSECONDS.toMillis(((TimeUnit.SECONDS.toNanos(1)/Settings.ticksPerSecond) - elapsed));

			if (wait < 0)
				wait = 5;
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!event.win && !event.loss);
	}

	private Event step() {
		Event.EventFactory eventFactory = new Event.EventFactory();
		if(isGameWon())
			eventFactory.setWin(true);
		else if (!isLegalMove())
			eventFactory.setLoss(true);
		else {
			Field moveTo = move(gs.getHead(), gui.getDirection());
			gs.addNewHead(moveTo);
			eventFactory.setMovementDirection(gui.getDirection());

			if (moveTo.equals(gs.getFoodField())) {
				gs.spawnFood();
				eventFactory.setPickedUpFood(true);
			} else
				gs.removeTail();
		}
		return eventFactory.buildEvent();
	}

	private boolean isGameWon() {
		return (gs.getSnake().size() == Settings.fields * Settings.fields);
	}

	private boolean isLegalMove() {
		Direction d = gui.getDirection();
		Field head = gs.getHead();
		if (head.x + d.x < 0 || head.x + d.x >= Settings.fields || head.y + d.y < 0 || head.y + d.y >= Settings.fields)
			return false;
		if (gs.getSnake().contains(move(head, d)) && !gs.getTail().equals(move(head, d)))
			return false;
		return true;
	}

	private Field move(Field f, Direction d) {
		return new Field(f.x + d.x, f.y + d.y);
	}
}
