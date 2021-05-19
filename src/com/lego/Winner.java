package com.lego;

import java.util.List;

public class Winner {

	protected boolean checkWinner(List<List<Integer>> bricks) {
		if (bricks.get(1).get(1) - bricks.get(0).get(1) == bricks.get(0).get(3)
				&& bricks.get(2).get(2) - bricks.get(1).get(2) == bricks.get(1).get(4)
				&& bricks.get(3).get(2) - bricks.get(0).get(2) == bricks.get(0).get(4)
				&& bricks.get(4).get(2) - bricks.get(2).get(2) == bricks.get(2).get(4)
				&& bricks.get(2).get(1).equals(bricks.get(4).get(1))
				&& bricks.get(2).get(1).equals(bricks.get(1).get(1))
				&& bricks.get(0).get(2).equals(bricks.get(1).get(2))
				&& bricks.get(0).get(1).equals(bricks.get(3).get(1))
				&& bricks.get(2).get(2).equals(bricks.get(3).get(2)))
		{

			return true;
		} else {
			return false;
		}

	}

}
