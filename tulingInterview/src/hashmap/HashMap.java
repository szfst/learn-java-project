package hashmap;

public class HashMap<K,V> implements Map<K, V> {
    private  static int defaultLenght = 16;
    private static double defalutLoader = 0.75;
    private Entry[] table = null;
    private int size = 0;

    public HashMap() {
        this(defaultLenght,defalutLoader);
    }

    public HashMap(int length,double loader) {
       defaultLenght = length;
       defalutLoader = loader;
       table = new Entry[defaultLenght];

    }

    @Override
    public V put(K k, V v) {
        int index = hash(k);
        Entry<K,V> entry = table[index];
        if(entry==null){
            size++;
            table[index]=newEntry(k,v,null);
        }else{

            table[index]=newEntry(k,v,entry);
        }
        return (V) table[index].getValue();
    }

    private Entry<K,V> newEntry(K k, V v, Entry<K,V> next) {
        return new Entry<K,V>(k,v,next);
    }


    public int hash(K k){
        int m = defaultLenght;
        int i = k.hashCode() % m;
        return i>=0?i:-i;
    }

    @Override
    public V get(K k) {
        int index = hash(k);
        if(table[index]==null){
            return null;
        }
        return (V) find(k,table[index]);
    }
    //有可能冲突了，所以要不停地找
    private V find(K k, Entry<K,V> entry) {
        if(k==entry.getKey() || k.equals(entry.getKey())){
            return entry.getValue();
        }else{
            //哈希算法决定了效率，另外每当扩容的时候需要重新hash，也影响了效率（如果知道数据量，可以直接决定length）
            if(entry.next !=null){
                return find(k,entry.next);
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }
    class Entry<K,V> implements Map.Entry<K,V> {
        K k;
        V v;
        Entry<K,V> next;

        public Entry(K k, V v, Entry<K, V> next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }
    }
}
