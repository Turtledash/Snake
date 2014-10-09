package model;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import util.Settings;


public class GameState implements IGameState {
	int width, height;
	LinkedList<Field> snake;
	Field food;
	Random r = new Random();


	public GameState(int width, int height, LinkedList<Field> snake, Field food) {
		this.width = width;
		this.height = height;
		this.snake = snake;
		this.food = food;
	}

	public GameState() {
		this.width = Settings.fields;
		this.height = Settings.fields;
		this.snake = new LinkedList<Field>();
		snake.add(new Field(3, 0));
		snake.add(new Field(2, 0));
		snake.add(new Field(1, 0));
		snake.add(new Field(0, 0));
		this.food = new Field(10, 0);
	}

	@Override
	public Field getFoodField() {
		return food;
	}

	@Override
	public List<Field> getSnake() {
		return snake;
	}

	@Override
	public void removeTail() {
		snake.removeLast();
	}

	@Override
	public void addNewHead(Field f) {
		snake.addFirst(f);
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public Field getHead() {
		return snake.getFirst();
	}

	//TODO Do this again
	@Override
	public void spawnFood() {
		List<Field> possibleSpawns = new LinkedList<Field>();
		for (int i = 0; i < Settings.fields; i++)
			for (int j = 0; j < Settings.fields; j++) {
				Field f = new Field(i, j);
				if (!snake.contains(f))
					possibleSpawns.add(f);
			}
		food = possibleSpawns.get(r.nextInt(possibleSpawns.size()));
	}

	@Override
	public Field getTail() {
		return snake.getLast();
	}
}
