import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Dice {
    public final int DICE_FIVE = 5;
    public final int DICE_SIX = 6;
    
    public final int MAX_COUNTER_EX1 = 2;

    public int counterEx1;
    public int counterEx2;
    public int counterEx3;

    public String varEx2;
    public String varEx3;
    
    public int resultEx1;
    public int resultEx2;
    public int resultEx3;
    public int totalTrial;

    public List<Integer> listDice;
    public List<Integer> listTrial;
    public HashMap<Integer, List> listLuckySerie;

    public Dice(String total, String list) {
    	this.counterEx1 = 0;
    	this.counterEx2 = 0;
    	this.counterEx3 = 0;
    	
    	this.varEx2 = "";
        this.varEx3 = "";
    	
        this.resultEx1 = 0;
        this.resultEx2 = 0;
        this.resultEx3 = 0;

        this.listLuckySerie = new HashMap<>();
        this.listDice = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            listDice.add(i);
        }
                
        this.listTrial = new ArrayList<>();
        for (int i = 0; i < list.length(); i++) {
            listTrial.add(Integer.parseInt(String.valueOf(list.charAt(i))));
        }
    }
    
    public void exercise() {
    	int index = 0;

        while (this.listTrial.size() > index) {
            this.exerciseOne(index);
            this.exerciseTwo(index);
            this.exerciseThree(index);
            index++;
        }
    }

    /**
     * How many times did it occur in the trial, that exactly two 6s were rolled after each other?
     * For example, in sequence 56611166626634416 it occurred twice, that exactly two 6s were thrown after each other.
     */
    public void exerciseOne(int index) {
        if (listTrial.get(index) == this.DICE_SIX) {
        	this.counterEx1++;
            // System.out.println(index + " &&& " + this.listTrial.size() + " &&& " + this.counterEx1);
            
            if (this.counterEx1 == this.MAX_COUNTER_EX1) {
            	if (index == this.listTrial.size() - 1) {
                    this.resultEx1++;
                    this.counterEx1 = 0;
                    // System.out.println("A");                		
            	} else {
            		if (listTrial.get(index + 1) != this.DICE_SIX) {
	                    this.resultEx1++;
	                    this.counterEx1 = 0;
	                    // System.out.println("B");                			
            		}
            	}
            }
        }
        else { 
        	this.counterEx1 = 0;
            // System.out.println("* " + listTrial.get(index));
        }
    }
    
    /**
     * Find the length of the longest subsequence of successive rolls, in which the value 6 does not occur.
     * (This number can be zero, if only 6s were thrown.)
     * For example, in the trial 66423612345654 the longest subsequence of successive rolls 
     * in which the value 6 does not occur is 12345. Its length is 5. 
     */
    public void exerciseTwo(int index) {    	
    	if (listTrial.get(index) != this.DICE_SIX) {
    		this.counterEx2++;
    		this.resultEx2 = this.counterEx2 > this.resultEx2 ? this.counterEx2 : this.resultEx2;
    	}
    	else {
    		this.counterEx2 = 0;
    	}
    }
    
    /**
     * We shall call a sequence of successive rolls in the trial a lucky series, 
     * if the sequence contains only 5s and 6s. For example 6556665 is a lucky series, with a length of 7.
     * Find out, which is the most frequent length for lucky series. 
     * If there are more than one "most frequent" lucky series lengths, print the longest. 
     * If there are no lucky series in the trial, print zero.
     */
    public void exerciseThree(int index) {
        if (listTrial.get(index) == this.DICE_FIVE || listTrial.get(index) == this.DICE_SIX) {
        	this.counterEx3 += 1;
            this.varEx3 = this.varEx3 + "" + listTrial.get(index);
        } else {
            if (this.counterEx3 >= 2) {
                if (this.listLuckySerie.containsKey(this.counterEx3)) {
                    List<String> existingList = new ArrayList<>();
                    existingList = this.listLuckySerie.get(this.counterEx3);
                    existingList.add(this.varEx3);
                } else {
                    List<String> newList = new ArrayList<>();
                    newList.add(this.varEx3);
                    this.listLuckySerie.put(this.counterEx3, newList);
                }
            }

            this.counterEx3 = 0;
            this.varEx3 = "";
        }
    }

    public void result() {
        this.exercise();
        System.out.println(this.resultEx1);
        System.out.println(this.resultEx2);

        Iterator iterator = this.listLuckySerie.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry me = (Map.Entry) iterator.next();
            Integer size = (Integer) me.getKey();
            List<String> list = (ArrayList<String>) me.getValue();

            System.out.println("SIZE: " + size);
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        }
        System.out.println(this.resultEx3);
    }
}