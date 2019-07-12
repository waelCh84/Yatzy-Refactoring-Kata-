package org.exemple.stream;

import java.util.Arrays;
import java.util.List;

public class YatzyPojo {

    private List<Integer> dice;
    
    public YatzyPojo() {
    }
    
    public YatzyPojo(int d1, int d2, int d3, int d4, int d5)
    {
        dice =  Arrays.asList(d1,d2,d3,d4,d5);
    }

	public List<Integer> getDice() {
		return dice;
	}

	public void setDice(List<Integer> dice) {
		this.dice = dice;
	}

}



