package model;
import java.util.List;

public interface IGameState {
	public Field getFoodField();
	public List<Field> getSnake();
	public void removeTail();
	public void addNewHead(Field f);
	public Field getHead();
	public int getWidth();
	public int getHeight();
	public void spawnFood();
	public Field getTail();
}
