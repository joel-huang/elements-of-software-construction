public class StripedMapWithSize {
    //synchronization policy: buckets[n] guarded by locks[n%N_LOCKS]
    private static final int N_LOCKS = 16;
    private final Node[] buckets;
    private final Object[] locks;
    private int numBuckets;
    
    public StripedMapWithSize(int numBuckets) {
        this.numBuckets = numBuckets;
        buckets = new Node[numBuckets];
        locks = new Object[N_LOCKS];
        
        for (int i = 0; i < N_LOCKS; i++) {
            locks[i] = new Object();
        }
    }
    
    public Object put(Object key, Object value) {
        int hash = hash(key);
        synchronized (locks[hash % N_LOCKS]) {
            for (Node m = buckets[hash]; m != null; m = m.next)
                if (m.key.equals(key)) {
                    m.value = value;
                    return m.value;
                }
            buckets[hash] = new Node(key,value,buckets[hash]);
        }
        return null;
    }
    
    public Object get (Object key) {
        //todo: get the item with the given key in the map
        int hash = hash(key);
        // synchronize on the (hash%N_LOCKS)-th stripe
        synchronized (locks[hash % N_LOCKS]) {
            for (Node m = buckets[hash]; m != null; m = m.next)
                if (m.key.equals(key)) {
                    return m.value;
                }
        }
        return null;        
    }
    
    private final int hash (Object key) {
        return Math.abs(key.hashCode() % buckets.length);
    }
    
    public void clear () {
        for (int i = 0; i < numBuckets; i++) {
            int hash = hash(i);
            synchronized (locks[hash % N_LOCKS]) {
                if (buckets[hash] != null)
                    buckets[hash] = null;
            }
        }
    }

    public int size () {
        int count = 0;
        for (int i = 0; i < numBuckets; i++) {
            int hash = hash(i);
            synchronized (locks[hash % N_LOCKS]) {
                if (buckets[hash] != null) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        StripedMapWithSize s = new StripedMapWithSize(128);
        s.put(100, 1000);
        System.out.println(s.get(100));
        System.out.println(s.get(101));
        System.out.println(s.size());
        s.clear();
        System.out.println(s.size());
    }

    class Node {
        Node next;
        Object key;
        Object value;
        Node(Object key, Object value, Node next) {
            this.next = next;
            this.key = key;
            this.value = value;
        }
    }
}

