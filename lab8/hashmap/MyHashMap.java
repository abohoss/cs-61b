package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author Ahmed Hossam
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private HashSet<K> keySet;
    private double loadFactor;
    private int size;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        buckets = new Collection[16];
        for(int i = 0 ; i< buckets.length;i++){
            buckets[i] = createBucket();
        }
        keySet = new HashSet<>();
        size = 0;
        loadFactor = 0.75;
    }

    public MyHashMap(int initialSize) {
        buckets = new Collection[initialSize];
        for(int i = 0 ; i< buckets.length;i++){
            buckets[i] = createBucket();
        }
        keySet = new HashSet<>();
        size = 0;
        loadFactor = 0.75;
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        buckets = new Collection[initialSize];
        for(int i = 0 ; i< buckets.length;i++){
            buckets[i] = createBucket();
        }
        keySet = new HashSet<>();
        size = 0;
        loadFactor = maxLoad;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }


    // Your code won't compile until you do so!
    @Override
    public void clear() {
        size = 0;
        buckets = createTable(16);
        keySet = new HashSet<>();
    }

    @Override
    public boolean containsKey(K key) {
        if(key == null) throw new IllegalArgumentException("key is null");
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        if(key == null) throw new IllegalArgumentException("key is null");
        int i = hash(key);
        if(buckets[i] != null) {
            for (Node j : buckets[i]) {
                if (j.key.equals(key)) {
                    return j.value;
                }
            }
        }
        return null;
    }
    private Node getNode(K key) {
        if(key == null) throw new IllegalArgumentException("key is null");
        int i = hash(key);
        if(buckets[i] != null) {
            for (Node j : buckets[i]) {
                if (j.key.equals(key)) {
                    return j;
                }
            }
        }
        return null;
    }
    private int hash(K key) {
        int h = key.hashCode();
        h = Math.floorMod(h, buckets.length);
        return h;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if(key == null) throw new IllegalArgumentException("key is null");
        if(calclaodfactor() >= loadFactor) {
            resize(2* buckets.length);
        }
        if(get(key) != null) {
            Node n = getNode(key);
            n.value =value;
            return;
        }
        int i = hash(key);
        Node n = new Node(key, value);
        buckets[i].add(n);
        keySet.add(key);
        size++;
    }

    private double calclaodfactor() {
        return (double) size/ buckets.length;
    }

    private void resize(int capacity) {
        MyHashMap<K, V> a = new MyHashMap<>(capacity);
        for(int i=0;i<this.buckets.length;i++) {
            for(Node j : this.buckets[i]) {
                a.put(j.key, j.value);
            }
        }
        this.buckets = a.buckets;
    }

    @Override
    public Set<K> keySet() {
        return keySet;
    }

    @Override
    public V remove(K key) {
        int i = hash(key);
        for(Node n : buckets[i]) {
            if(n.key.equals(key)) {
                V val=n.value;
                keySet.remove(key);
                buckets[i].remove(n);
                return val;
            }
        }
        return null;
    }

    @Override
    public V remove(K key, V value) {
        int i = hash(key);
        for(Node n : buckets[i]) {
            if(n.key.equals(key) && n.value.equals(value)) {
                V val=n.value;
                keySet.remove(key);
                buckets[i].remove(n);
                return val;
            }
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return new HashIterator();
    }
    private class HashIterator implements Iterator<K> {
        private LinkedList<K> keyList = createKeyList();
        private LinkedList<K> createKeyList() {
            LinkedList<K> l = new LinkedList<>();
            for(int i=0;i< buckets.length;i++){
                if(buckets[i] != null) {
                    for(Node n : buckets[i]) {
                        l.add(n.key);
                    }
                }
            }
            return l;
        }
        @Override
        public boolean hasNext() {
            return keyList.size() > 0;
        }

        @Override
        public K next() {
            K returnKey = keyList.remove();
            return returnKey;
        }
    }
}
