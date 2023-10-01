package bstmap;

import java.security.Key;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>{

    private BSTNode root;
    private Set<K> keySet = new HashSet<>();
    private class BSTNode{
        private int size;
        private BSTNode left, right;
        private V val;
        private K key;

        public BSTNode(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }
    @Override
    public void clear() {
        root = null;
        keySet.clear();
    }

    @Override
    public boolean containsKey(K key) {
        if(key == null) {throw new IllegalArgumentException("insert an actual key!");};
        return containsKey(root, key);
    }
    private boolean containsKey(BSTNode x, K key) {
        if(x == null) {
            return false;
        }
        int cmp=key.compareTo(x.key);
        if(cmp < 0) {return containsKey(x.left, key);}
        else if(cmp > 0) {return containsKey(x.right, key);}
        else return true;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }
    private V get(BSTNode x, K key){
        if(x == null) {
            return null;
        }
        int cmp=key.compareTo(x.key);
        if(cmp < 0) {return get(x.left, key);}
        else if(cmp > 0) {return get(x.right, key);}
        else return x.val;

    }
    @Override
    public int size() {
        return size(root);
    }
    private int size(BSTNode x){
        if(x == null) {
            return 0;
        }
        return x.size;
    }
    @Override
    public void put(K key, V value) {
        if(key == null) {throw new IllegalArgumentException("insert an actual key!");};
        root = put(root, key, value);
    }
    private BSTNode put(BSTNode x, K key, V val) {
        if(x == null)   {
            keySet.add(key);
            return new BSTNode(key, val, 1);
        }
        int cmp=key.compareTo(x.key);
        if(cmp < 0) {x.left=put(x.left, key, val);}
        if(cmp > 0) {x.right=put(x.right, key, val);}
        else x.val=val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }
    @Override
    public Set<K> keySet() {
        return keySet;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("sorry");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("sorry");
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("sorry");
    }
    public void printInOrder() {
        printInOrder(root);
    }
    private void printInOrder(BSTNode x) {
        if(x == null) {
            return;
        }
        printInOrder(x.left);
        System.out.println("key: " + x.key + " value: " + x.val);
        printInOrder(x.right);
    }
}
