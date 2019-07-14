package org.exemple.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Yatzy {
	
	static final int PAIR 	= 2;
	static final int THREE  = 3;
	static final int FOUR 	= 4;
	
	public static enum Straight {
		  SMALL,
		  LARGE;
	}

    public static int computeChanceScore(List<Integer> dice) {
         return dice.stream()
        		 .mapToInt(i -> i)
        		 .sum();
    }

    public static int computeYatzyScore(List<Integer> dice) {
    	Integer first = dice.get(0);
    	return (dice.stream().allMatch(i -> i == first) == true ? 50 : 0) ;
    }

    public static int computeSoreWithSide(Integer side, List<Integer> dice) {
        return (int) dice.stream()
            .filter(s -> s.equals(side))
            .reduce(0, (total, value) -> total + value);
    }

    private static <E> Map<Integer, Long> scanList(List<Integer> dice) {
    	Map<Integer, Long> maps =  dice.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    	return maps;
    }

    public static int computePairScore(List<Integer> dice) {
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
    
    public static int computeTwoPairScore(List<Integer> dice) {
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
    
    public static int computeThreeOfKindScore(List<Integer> dice) {
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
    
    public static int computeFourOfKindScore(List<Integer> dice) {
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

    public static int computeFullHouseScore(List<Integer> dice) {
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

    public static int computeStraightScore(List<Integer> dice, List<Integer> straightList,Straight straightMode) {
    	List<Integer> result = straightList.stream().filter(s -> dice.contains(s))
                .collect(Collectors.toList());
    	
    	if(result != null && result.size()==5){
    		return (straightMode.equals(Straight.SMALL) ? 15 : 20);
    	}
    	return 0 ;
    }

}
