package model;
public class Field {
	public final int x, y;

	public Field(int x, int y) {
		if (x < 0 || y < 0)
			throw new IllegalArgumentException("Illegal Parameters! x: " + x + " y: " + y);
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Field))
			return false;
		return (this.x == ((Field)o).x && this.y == ((Field)o).y);
	}
}
