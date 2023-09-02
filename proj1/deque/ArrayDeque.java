package deque;

public class ArrayDeque<Item> {
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
        size++;
    }
    public void addLast(Item x){
        if(size == items.length){
            resize(size*2);
        }
        items[nextLast]=x;
        nextLast++;
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
        System.out.print("Items: ");
        for (int i=0;i<size;i++){
            System.out.print(items[i]+" ");
        }
        System.out.println(" ");
    }
}
