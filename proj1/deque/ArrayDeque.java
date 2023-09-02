package deque;

import java.util.Iterator;

public class ArrayDeque<Item> implements Iterable<Item> {
    private int size;
    private Item items[];
    private int nextFirst;
    private int nextLast;
    public ArrayDeque(){
        items=(Item[]) new Object[8];
        size=0;
        nextFirst=2;
        nextLast=3;
    }
    public void addFirst(Item x){
        if(size == items.length){
            resize(size*2);
        }
        items[nextFirst]=x;
        nextFirst--;
        if(nextFirst<0){
            nextFirst = items.length-1;
        }
        size++;
    }
    public void addLast(Item x){
        if(size == items.length){
            resize(size*2);
        }
        items[nextLast]=x;
        nextLast++;
        if(nextLast == items.length){
            nextLast=0;
        }
        size++;
    }

    private void resize(int cap) {
        Item[] a=(Item[]) new Object[cap];
        //difference between index 0 and inner array start value
        int diff=nextFirst+1;
        for(int i=diff;i<size;i++){
            a[i-diff]=items[i];
        }
        //last item in items[] will be at index size-1-diff in a[] so element after it is at size-diff
        for(int j=0;j<=nextFirst;j++){
            a[size-diff+j]=items[j];
        }
        nextFirst = cap-1;
        nextLast = size;
        items = a;
    }
    public int size()   {return size;}

    public boolean isEmpty(){
        if(size==0) {return true;}
        return false;
    }

    public Item get(int index){
        return items[index];
    }

    public void printDeque(){
        Item[] a=(Item[]) new Object[items.length];
        //difference between index 0 and inner array start value
        int diff=nextFirst+1;
        for(int i=diff;i<size;i++){
            a[i-diff]=items[i];
        }
        //last item in items[] will be at index size-1-diff in a[] so element after it is at size-diff
        for(int j=0;j<=nextFirst;j++){
            a[size-diff+j]=items[j];
        }
        System.out.print("Items: ");
        for (Item i : a){
            System.out.print(i+" ");
        }
        System.out.println(" ");
    }

    public Item removeFirst(){
        double R=size/ items.length;
        if(R < 0.25){
            resize(size/2);
        }
        Item x=items[nextFirst+1];
        if(x==null) {return null;}

        items[nextFirst+1] = null;
        nextFirst++;
        size--;
        return x;
    }

    public Item removeLast(){
        double R=size/ items.length;
        if(R < 0.25){
            resize(size/2);
        }
        Item x=items[nextLast-1];
        if(x==null) {return null;}
        items[nextLast-1] = null;
        nextLast--;
        size--;
        return x;
    }

    @Override
    public boolean equals(Object o){
        if(this == o)   {return true;}
        if(o instanceof ArrayDeque){
            ArrayDeque li=(ArrayDeque) o;
            // return false if both are not same size,
            if(this.size != li.size)    {return false;}
            for(Item i : this){
                if(!li.contains(i))   {return false;}
            }
            return true;
        }
        return false;
    }

    /*Iterates over array to see if element is in the list. */
    private boolean contains(Item x){
        for(Item i : this){
            if(i.equals(x)) {return true;}
        }
        return false;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<Item>{
        private int wizPos;
        public ArrayDequeIterator(){
            wizPos=0;
        }
        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public Item next() {
            Item returnItem = get(wizPos);
            wizPos++;
            return returnItem;
        }
    }



}
