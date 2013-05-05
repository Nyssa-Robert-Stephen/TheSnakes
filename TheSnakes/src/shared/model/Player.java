package shared.model;

import java.awt.Color;
import java.io.Serializable;

public class Player implements Serializable {
	
	private static final long serialVersionUID = 1081339949494210162L;
	private String name;
	private Snake snake;
	private int position;
	private int score;
	private Color color;
	
	public String toString() {
		return name + " | " + snake;
	}
	
	public Player(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Color getColor() {
		return color;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public void setSnake(Snake snake) {
		this.snake = snake;
	}
	public Snake getSnake(){
		return snake;
	}

}
