package com.david.hashmap;
/**
 * @author David
 */
public interface Map<K,V> {
    /**
     * TODO 向HashMap中插入值
     * @param k key
     * @param c value
     * @return V
     */
    V put(K k,V c);

    /**
     * TODO 根据Key获取HashMap中的值
     * @param k key
     * @return v
     */
    V get(K k);

    /**
     * TODO 获取集合中的元素个数
     * @return i
     */
    int size();


    /**
     * TODO 获取几何中，键值对的对象
     * @param <K>
     * @param <V>
     */
    interface  Entry<K,V>{
        /**
         * TODO 获取key
         * @return K
         */
        K getKey();

        /**
         * TODO 获取value
         * @return V
         */
        V getValue();

        /**
         * TODO 获取value
         * @param v v
         * @return V
         */
        V setValue(V v);
    }
}
