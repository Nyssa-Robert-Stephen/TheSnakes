package server.model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import shared.controller.SnakeGameInterface;
import shared.model.Food;
import shared.model.Player;
import shared.model.Snake;
import shared.model.Tile;

public class GameLogic implements SnakeGameInterface {
	
	private static Color COLOR_TOP_LEFT = Color.RED;
	private static Color COLOR_TOP_RIGHT = Color.MAGENTA;
	private static Color COLOR_BOT_LEFT = Color.BLUE;
	private static Color COLOR_BOT_RIGHT = Color.GREEN;
	
	private List<Player> players;
	private List<Food> foodItems;
	private Point bounds;
	
	public GameLogic(Point bounds) {
		this.bounds = bounds;
		foodItems = new ArrayList<Food>();
	}
	/*
	 * Give all players a position, colour and direction
	 */
	public void sendMove(String username, int dx, int dy) {
		
	}
	
	public void setPlayers(List<Player> players) {
		this.players = players;
		int dx = 0 , dy = 0;
		int x = 0, y = 0;
		Color c = Color.BLACK;
		for(Player p : players) {
			switch(p.getPosition()){
				case TOP_LEFT:
					dx = 1;
					x = 1;
					y = 1;
					c = COLOR_TOP_LEFT;
					break;
				case TOP_RIGHT:
					dx = -1;
					x = bounds.x - 2;
					y = 1;
					c = COLOR_TOP_RIGHT;
					break;
				case BOT_LEFT:
					x = 1;
					y = bounds.y - 2;
					dx = 1;
					c = COLOR_BOT_LEFT;
					break;
				case BOT_RIGHT:
					x = bounds.x - 2;
					y = bounds.y - 2;
					dx = -1;
					c = COLOR_BOT_RIGHT;
					break;
			}
			Snake snake = new Snake(x,y,c,bounds);
			snake.setDirection(dx, dy);
			p.setSnake(snake);
		}
	}
	
	private void interpretStatus() {
		
	}
	/*
	 * Steps the game forward one "tick"
	 */
	public void step() {
		interpretStatus();
		checkCollisions();
		checkFood();
		moveSnakes();
		spawnFood();
	}
	/*
	 * Moves all the snakes
	 * Checks for collisions of the snakes with themselves
	 */
	private void moveSnakes() {
		for(Player p : players) {
			
		}
	}
	/*
	 * This checks for collisions with other players.
	 * This needs to be called BEFORE the snakes are moved as this is 
	 * predictive collision detection
	 */
	private void checkCollisions() {
		
		for(Player p: players) {
			Point p_headPos = p.getSnake().getHeadPos();
			Point pDir = p.getSnake().getDirection();
			
			Point p_new_pos = new Point(p_headPos);
			p_new_pos.translate(pDir.x,pDir.y);
			/*
			 * If the current player has already lost, don't bother doing anything.
			 * This prevents doubling up of collision detection 
			 * this player will still be checked by other players to 
			 * see if a collision has happened.
			 */
			//if(statusMap.get(p.getName()) != STATUS_LOSE) {
				for(Player k: players) {
					if(p != k){					
						Point k_headPos = k.getSnake().getHeadPos();
						Point kDir = k.getSnake().getDirection();
						
						Point k_new_pos = new Point(k_headPos);
						k_new_pos.translate(kDir.x,kDir.y);
						
						if(k_new_pos.equals(p_new_pos)) {
							compareSize(p,k);
						} else{
							for(Tile t : k.getSnake().getSegments()) {
								if(t.getPoint().equals(p_new_pos)){
									if(p_new_pos.equals(k_headPos) && compareDirections(pDir, kDir)) {
										compareSize(p,k);
									} else {
										// Make player lose
										//statusMap.put(p.getName(), STATUS_LOSE);
									}
								}		
							}
						}
					}
				}
			//}
		}
	}
	/*
	 * Compares the sizes of two snakes and 
	 * will set the status of them accordingly
	 */
	private void compareSize(Player p, Player k){
		
		int p_snake_len = p.getSnake().getLength();
		int k_snake_len = k.getSnake().getLength();
		
		if(p_snake_len == k_snake_len) {
			// If they are the same size, one of them randomly looses
			Random r = new Random();
			switch(r.nextInt(2)) {
			case 0:
				
				break;
			case 1:
				break;
			}
		} else if(p_snake_len > k_snake_len) {
			// if p is greater than k, p wins
		} else {
			// Otherwise k must be larger so k wins
		}
		
	}
	/*
	 * Compares the directions of two points (treated as velocity vectors)
	 * returns true if p and k are both heading towards eachother 
	 */
	private boolean compareDirections(Point p, Point k) {
		if(p.y == (-1) * k.y){
			return true;
		}else if(p.x == (-1) * k.x){
			return true;
		}
		return false;
	}
	/*
	 * Checks for food collisions 
	 */
	private void checkFood() {
		for(Player p: players) {
			Point headPos = p.getSnake().getHeadPos();
			Iterator<Food> iter = foodItems.iterator();
			while(iter.hasNext()){
				Food item = iter.next();
				if(item.getTile().getPoint().equals(headPos)) {
					p.getSnake().growSnake(item.getGrowLevel());
					iter.remove();
				}
				
			}
		}
	}
	/*
	 * Spawns food at random locations on the board
	 * Maximum number at any one time is Food.MAX_FOOD 
	 */
	private void spawnFood() {
		for(int i = foodItems.size(); i < Food.MAX_FOOD ; i++){
			int x, y;
			Random r = new Random();
			do {
				x = r.nextInt(bounds.x);
				y = r.nextInt(bounds.y);
			}while(isTileOccupied(x,y));
			
			int type = 0;
			switch(r.nextInt(3)) {
				case 0:
					type = Food.TYPE_APPLE;
					break;
				case 1:
					type = Food.TYPE_ORANGE;
					break;
				case 2:
					type = Food.TYPE_BANANA;
					break;
			}
			Food food = new Food(x,y,type);
			foodItems.add(food);
		}
	}
	/*
	 * Checks a position to see if it is occupied
	 * only used for spawning food, not collisions
	 */
	private boolean isTileOccupied(int x, int y) {
		for(Player p : players){
			for(Tile t : p.getSnake().getSegments()) {
				if(t.getPoint().equals(new Point(x,y))){
					return true;
				}
			}
		}
		return false;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public List<Food> getFood() {
		return foodItems;
	}
}
