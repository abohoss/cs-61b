package deque;

import java.util.Comparator;

public class MaxArrayDeque<Item> extends ArrayDeque<Item>{
    Comparator<Item> cmp;
    public MaxArrayDeque(Comparator<Item> c){
        cmp=c;
    }
    /*
    public Item max(){
        if(size()==0) {return null;}
        int max=0;
        for(Item i : this)
    }
    */

}
