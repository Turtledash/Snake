package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import controller.Direction;

public class DirectionManager implements KeyListener{
	Direction currentDirection;
	Direction nextDirection;

	public DirectionManager() {
		this.currentDirection = Direction.RIGHT;
		this.nextDirection = Direction.RIGHT;
	}

	public Direction getDirection() {
		return nextDirection;
	}

	public void updateDirection() {
		currentDirection = nextDirection;
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		switch (ke.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			if (currentDirection != Direction.RIGHT)
				nextDirection = Direction.LEFT;
			break;
		case KeyEvent.VK_RIGHT:
			if (currentDirection != Direction.LEFT)
				nextDirection = Direction.RIGHT;
			break;
		case KeyEvent.VK_UP:
			if (currentDirection != Direction.DOWN)
				nextDirection = Direction.UP;
			break;
		case KeyEvent.VK_DOWN:
			if (currentDirection != Direction.UP)
				nextDirection = Direction.DOWN;
			break;
		}}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
