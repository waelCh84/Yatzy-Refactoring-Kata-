package org.exemple.stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class YatzyTest {
	
	static final int SIDE_ONE 	= 1;
	static final int SIDE_TWO 	= 2;
	static final int SIDE_THREE = 3;
	static final int SIDE_FOUR 	= 4;
	static final int SIDE_FIVE 	= 5;
	static final int SIDE_SIX 	= 6;
	
	private YatzyPojo yatzy;
	
	@Before
	public void setUp() {
		yatzy = new YatzyPojo();
	}

	@Test
    public void shouldComputeChanceScoresSumOfAllDiceAndReturnFifteenPts() {
        int expected = 15;
        yatzy.setDice(Arrays.asList(2,3,4,5,1));
        int actual = Yatzy.computeChanceScore(yatzy.getDice());
        assertEquals(expected, actual);
    }
    
    @Test
    public void shouldComputeChanceScoresSumOfAllDiceAndNotReturnFifteenPts() {
        int expected = 15;
        yatzy.setDice(Arrays.asList(3,3,4,5,1));
        int actual = Yatzy.computeChanceScore(yatzy.getDice());
        assertNotEquals(expected, actual);
    }
    
    @Test 
    public void shouldComputeYatzyScoresSumOfAllDiceAndReturnFiftyPts() {
        int expected = 50;
        yatzy.setDice(Arrays.asList(4,4,4,4,4));
        int actual = Yatzy.computeYatzyScore(yatzy.getDice());
        assertEquals(expected, actual);
    }
    
    @Test 
    public void shouldComputeYatzyScoresSumOfAllDiceAndReturnZeroPts() {
        int expected = 0;
        yatzy.setDice(Arrays.asList(6,6,6,6,3));
        int actual = Yatzy.computeYatzyScore(yatzy.getDice());
        assertEquals(expected, actual);
    }    

    @Test 
    public void shouldComputeScoresSumOfAllDiceWithSideOneAndReturnTwoPts() {
        int expected = 2;
        yatzy.setDice(Arrays.asList(1,2,1,4,5));
        int actual = Yatzy.computeSoreWithSide(SIDE_ONE, yatzy.getDice());
        assertEquals(expected, actual);
    }   
    
    @Test 
    public void shouldComputeScoresSumOfAllDiceWithSideTwoAndReturnSixPts() {
        int expected = 6;
        yatzy.setDice(Arrays.asList(2,2,1,4,2));
        int actual = Yatzy.computeSoreWithSide(SIDE_TWO, yatzy.getDice());
        assertEquals(expected, actual);
    }    
    
    @Test 
    public void shouldComputeScoresSumOfAllDiceWithSideThreeAndReturnThreePts() {
        int expected = 3;
        yatzy.setDice(Arrays.asList(2,3,1,4,2));
        int actual = Yatzy.computeSoreWithSide(SIDE_THREE, yatzy.getDice());
        assertEquals(expected, actual);
    }    
    
    @Test 
    public void shouldComputeScoresSumOfAllDiceWithSideFourAndReturnSixteenPts() {
        int expected = 16;
        yatzy.setDice(Arrays.asList(4,4,1,4,4));
        int actual = Yatzy.computeSoreWithSide(SIDE_FOUR, yatzy.getDice());
        assertEquals(expected, actual);
    }   
    
    @Test
    public void shouldComputePairScoresSumOfAllDiceAndReturnSixPts() {
    	yatzy.setDice(Arrays.asList(3,5,1,5,3));
        assertEquals(10, Yatzy.computePairScore(yatzy.getDice()));
    }
    
    @Test
    public void shouldComputeTwoPairScoresSumOfAllDiceAndReturnSixteenPts() {
    	yatzy.setDice(Arrays.asList(3,5,1,5,3));
        assertEquals(16, Yatzy.computeTwoPairScore(yatzy.getDice()));
    }
    
	
    @Test
    public void shouldComputeThreeOfKindScoresSumOfAllDiceAndReturnNinePts() {
    	yatzy.setDice(Arrays.asList(3,3,1,5,3));
        assertEquals(9, Yatzy.computeThreeOfKindScore(yatzy.getDice()));
    }
	
    @Test
    public void shouldComputeFourOfKindScoresSumOfAllDiceAndReturnTwelvePts() {
    	yatzy.setDice(Arrays.asList(3,3,1,3,3));
        assertEquals(12, Yatzy.computeFourOfKindScore(yatzy.getDice()));
    }
    
    @Test
    public void shouldComputeFullHouseScoresSumOfAllDiceAndReturnThirteenPts() {
    	yatzy.setDice(Arrays.asList(3,2,2,3,3));
        assertEquals(13, Yatzy.computeFullHouseScore(yatzy.getDice()));
    }
    
    @Test
    public void smallStraight() {
        assertEquals(15, Yatzy.smallStraight(1,2,3,4,5));
        assertEquals(15, Yatzy.smallStraight(2,3,4,5,1));
        assertEquals(0, Yatzy.smallStraight(1,2,2,4,5));
    }

    @Test
    public void largeStraight() {
        assertEquals(20, Yatzy.largeStraight(6,2,3,4,5));
        assertEquals(20, Yatzy.largeStraight(2,3,4,5,6));
        assertEquals(0, Yatzy.largeStraight(1,2,2,4,5));
    }


}
