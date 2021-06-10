import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Problem1 {
    public static void main(String[] args) {
        try {
            List<String> input = new ArrayList<>();
            Scanner scanner = new Scanner(System.in);

            for (int i = 0; i < 2; i++) {
                input.add(scanner.next());
            }            
            scanner.close();

            if (Integer.parseInt(input.get(0)) >= 1 && Integer.parseInt(input.get(0)) <= 1000000) {
                Dice dice = new Dice(input.get(0), input.get(1));
                dice.result();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}

class Dice {
    public final int DICE_FIVE = 5;
    public final int DICE_SIX = 6;    
    public final int MAX_COUNTER_EX1 = 2;

    protected int counterEx1;
    protected int counterEx2;
    protected int counterEx3;    
    protected int resultEx1;
    protected int resultEx2;
    protected int resultEx3;
    protected int totalTrial;
    protected String varEx3;

    public List<Integer> listDice;
    public List<Integer> listTrial;
    public HashMap<Integer, List> listLuckySerie;

    public Dice(String total, String list) {
    	this.counterEx1 = 0;
    	this.counterEx2 = 0;
    	this.counterEx3 = 0;
        this.resultEx1 = 0;
        this.resultEx2 = 0;
        this.resultEx3 = 0;
        this.varEx3 = "";

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

    public void exerciseOne(int index) {
        if (listTrial.get(index) == this.DICE_SIX) {
        	this.counterEx1++;
            
            if (this.counterEx1 == this.MAX_COUNTER_EX1) {
            	if (index == this.listTrial.size() - 1) {
                    this.resultEx1++;
                    this.counterEx1 = 0;    		
            	} else {
            		if (listTrial.get(index + 1) != this.DICE_SIX) {
	                    this.resultEx1++;
	                    this.counterEx1 = 0;            			
            		}
            	}
            }
        }
        else { 
        	this.counterEx1 = 0;
        }
    }

    public void exerciseTwo(int index) {    	
    	if (listTrial.get(index) != this.DICE_SIX) {
    		this.counterEx2++;
    		this.resultEx2 = this.counterEx2 > this.resultEx2 ? this.counterEx2 : this.resultEx2;
    	}
    	else {
    		this.counterEx2 = 0;
    	}
    }

    public void exerciseThree(int index) {
        if (listTrial.get(index) == this.DICE_FIVE || listTrial.get(index) == this.DICE_SIX) {
        	this.counterEx3 += 1;
            this.varEx3 = (String) this.varEx3 + "" + listTrial.get(index);
            
            if (index == this.listTrial.size() - 1) {
                this.addToListEx3();
            }
        } else {
            this.addToListEx3();
            this.counterEx3 = 0;
            this.varEx3 = "";
        }
    }

    private void addToListEx3() {
        if (this.counterEx3 >= 1) {
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
    }

    public int getResultEx1() {
        return this.resultEx1;
    }

    public int getResultEx2() {
        return this.resultEx2;
    }

    public int getResultEx3() {
        int counterSize = 0;
        int counterTotal = 0;
        Integer size;
        Iterator iterator = this.listLuckySerie.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry me = (Map.Entry) iterator.next();
            List<String> list = (ArrayList<String>) me.getValue();
            size = (Integer) me.getKey();

            if (list.size() >= counterTotal) {
                counterSize = size;
                counterTotal = list.size();
            }
        }

        this.resultEx3 = counterSize;
        return this.resultEx3;
    }

    public void result() {
        this.exercise();       

        System.out.println(this.getResultEx1());
        System.out.println(this.getResultEx2());
        System.out.println(this.getResultEx3());
    }
}