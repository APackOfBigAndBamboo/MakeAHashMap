package com.david.hashmap;

/**
 * @author David
 * @param <K>
 * @param <V>
 */
public class HashMap<K, V> implements Map<K, V> {
    /**
     * 数据存储结构=》 数组加链表
     */
    private Node<K, V>[] array = null;
    /**
     * 数组/hash桶的初始长度
     */
    private static int defaultLength = 16;

    /**
     * 加载因子/扩容因子
     */
    private static double factor = 0.75D;

    /**
     * 集合中的元素个数
     */
    private int size;

    /**
     * 调试打印函数
     */
    void print() {
        System.out.println("***************");
        if (array != null) {
            Node<K, V> node = null;
            for (int i = 0; i < array.length; i++) {
                node = array[i];
                System.out.print("下标【" + i + "】");
                while (node != null) {
                    System.out.print("[" + node.getKey() + ":" + node.getValue() + "]");
                    if (node != null) {
                        node = node.next;
                    }else {
                        node = null;
                    }
                }
                System.out.println();
            }
        }

    }

    /**
     * put元素方法
     * @param k key
     * @param v value
     * @return v
     */
    @Override
    public V put(K k, V v) {
        //1、懒加载机制，使用的时候进行分配
        if (array == null) {
            array = new Node[defaultLength];
        }

        //2、通过hash算法，计算出具体插入的位置
        int index = position(k, defaultLength);

        //判断是否需要扩容
        if (size > defaultLength * factor) {
            resize();
        }

        //3、加入要放入的元素
        Node<K, V> node = array[index];
        if (node == null) {
            array[index] = new Node<K, V>(k, v, null);
            size++;
        } else {
            if (k.equals(node.getKey()) || k == node.getKey()) {
                return node.setValue(v);
            } else {
                array[index] = new Node<K, V>(k, v, node);
                size++;
            }
        }
        return null;
    }

    /**
     * 扩容，并重新排列元素
     */
    private void resize() {
        //翻倍扩容
        //1、创建新temp array
        Node<K, V>[] temp = new Node[defaultLength << 1];

        //2、重新计算散列值，插入到新的array中。code=key%（defaultLength-1） ---》code=key%（defaultLength*2-1）
        Node<K, V> node = null;
        for (int i = 0; i < array.length; i++) {
            node = array[i];
            while (node != null) {
                //重新散列
                int index = position(node.getKey(), temp.length);
                Node<K, V> next = node.next;
                node.next = temp[index];
                temp[index] = node;
                node = next;
            }
        }

        //替换掉老的array
        array = temp;
        defaultLength = temp.length;
    }

    private int position(K k, int length) {
        int code = k.hashCode();
        //取模算法
        return code % (length - 1);
        //求与算法
        //return code & (defaultLength-1);
    }

    @Override
    public V get(K k) {
        if (array != null) {
            int index = position(k, defaultLength);
            //获取对应哈希桶节点
            Node<K, V> node = array[index];
            //遍历链表
            while (node != null) {
                //返回对应key的value
                if (node.getKey() == k){
                    return node.getValue();
                }else {
                    node = node.next;
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    static class Node<K, V> implements Map.Entry<K, V> {
        K key;
        V value;
        Node<K, V> next;

        private Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V v) {
            V oldValue = this.value;
            this.value = v;
            return oldValue;
        }
    }
}
