package com.lego;

import java.util.List;

public class GameOver {
	
	protected boolean checkGameOver(List<List<Integer>> bricks) {
		boolean game_over=true;
		for (List<Integer> brick : bricks) {
			if(brick.get(0)!= 2 || brick.get(0)!= 1) {
				game_over = false;
				break;
			}
		}
		return game_over;
	}
}
