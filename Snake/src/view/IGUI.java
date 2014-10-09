package view;

import model.IGameState;
import controller.Direction;
import controller.Event;

public interface IGUI {
	public void repaint();
	public void update(IGameState gs, Event e);
	public Direction getDirection();
}
