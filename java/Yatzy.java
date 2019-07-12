package org.exemple.stream;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Yatzy {
	
	static final int PAIR 	= 2;
	static final int THREE  = 3;
	static final int FOUR 	= 4;

    public static int computeChanceScore(List<Integer> dice)
    {
         return dice.stream()
        		 .mapToInt(i -> i)
        		 .sum();
    }

    public static int computeYatzyScore(List<Integer> dice)
    {
    	Integer first = dice.get(0);
    	return (dice.stream().allMatch(i -> i == first) == true ? 50 : 0) ;
    }

    public static int computeSoreWithSide(Integer side, List<Integer> dice) {
        return (int) dice.stream()
            .filter(s -> s.equals(side))
            .reduce(0, (total, value) -> total + value);
    }

    
    public static <E> Map<Integer, Long> scanList(List<Integer> dice) {
    	Map<Integer, Long> maps =  dice.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    	return maps;
    }

    public static int computePairScore(List<Integer> dice)
    {
    	List<Integer> pairList = scanList(dice).entrySet().stream()
    			.filter(x -> x.getValue().equals(new Long(PAIR)))
    			.map(x->x.getKey())
    			.collect(Collectors.toList());
    	
    	if(pairList != null && !pairList.isEmpty()){
    		return pairList.stream().max(Comparator.comparing(Integer::valueOf))
                    .get()*PAIR;
    	}
    		
		return 0;
    }
    
    public static int computeTwoPairScore(List<Integer> dice)
    {
    	List<Integer> pairList = scanList(dice).entrySet().stream()
    			.filter(x -> x.getValue().equals(new Long(PAIR)))
    			.map(x->x.getKey())
    			.collect(Collectors.toList());
    	
    	if(pairList != null && !pairList.isEmpty() && pairList.size()==2){
    		return pairList.stream()
    				  .reduce(0, Integer::sum)*PAIR;
    	}
    		
		return 0;
    }
    
    public static int computeThreeOfKindScore(List<Integer> dice)
    {
    	List<Integer> pairList = scanList(dice).entrySet().stream()
    			.filter(x -> x.getValue().equals(new Long(THREE)))
    			.map(x->x.getKey())
    			.collect(Collectors.toList());
    	
    	if(pairList != null && !pairList.isEmpty()){
    		return pairList.stream().findFirst()
                    .get()*THREE;
    	}
    		
		return 0;
    }
    
    public static int computeFourOfKindScore(List<Integer> dice)
    {
    	List<Integer> pairList = scanList(dice).entrySet().stream()
    			.filter(x -> x.getValue().equals(new Long(FOUR)))
    			.map(x->x.getKey())
    			.collect(Collectors.toList());
    	
    	if(pairList != null && !pairList.isEmpty()){
    		return pairList.stream().findFirst()
                    .get()*FOUR;
    	}
    		
		return 0;
    }

    public static int computeFullHouseScore(List<Integer> dice)
    {
    	Map<Integer, Long> map = scanList(dice);
    	List<Integer> list = map.entrySet().stream()
    			.filter(x -> x.getValue().equals(new Long(THREE)))
    			.map(x->x.getKey())
    			.collect(Collectors.toList());
    	
    	list.addAll(map.entrySet().stream()
    			.filter(x -> x.getValue().equals(new Long(PAIR)))
    			.map(x->x.getKey())
    			.collect(Collectors.toList()));
    	
    	if(list != null && !list.isEmpty() && list.size()==2){
    		return (list.get(0) * THREE) + (list.get(1) * PAIR);
    	}
    		
		return 0;
    }

    public static int smallStraight(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies;
        tallies = new int[6];
        tallies[d1-1] += 1;
        tallies[d2-1] += 1;
        tallies[d3-1] += 1;
        tallies[d4-1] += 1;
        tallies[d5-1] += 1;
        if (tallies[0] == 1 &&
            tallies[1] == 1 &&
            tallies[2] == 1 &&
            tallies[3] == 1 &&
            tallies[4] == 1)
            return 15;
        return 0;
    }

    public static int largeStraight(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies;
        tallies = new int[6];
        tallies[d1-1] += 1;
        tallies[d2-1] += 1;
        tallies[d3-1] += 1;
        tallies[d4-1] += 1;
        tallies[d5-1] += 1;
        if (tallies[1] == 1 &&
            tallies[2] == 1 &&
            tallies[3] == 1 &&
            tallies[4] == 1
            && tallies[5] == 1)
            return 20;
        return 0;
    }

}



