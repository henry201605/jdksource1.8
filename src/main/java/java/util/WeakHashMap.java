/***** Lobxxx Translate Finished ******/
/*
 * Copyright (c) 1998, 2013, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package java.util;

import java.lang.ref.WeakReference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;


/**
 * Hash table based implementation of the <tt>Map</tt> interface, with
 * <em>weak keys</em>.
 * An entry in a <tt>WeakHashMap</tt> will automatically be removed when
 * its key is no longer in ordinary use.  More precisely, the presence of a
 * mapping for a given key will not prevent the key from being discarded by the
 * garbage collector, that is, made finalizable, finalized, and then reclaimed.
 * When a key has been discarded its entry is effectively removed from the map,
 * so this class behaves somewhat differently from other <tt>Map</tt>
 * implementations.
 *
 * <p> Both null values and the null key are supported. This class has
 * performance characteristics similar to those of the <tt>HashMap</tt>
 * class, and has the same efficiency parameters of <em>initial capacity</em>
 * and <em>load factor</em>.
 *
 * <p> Like most collection classes, this class is not synchronized.
 * A synchronized <tt>WeakHashMap</tt> may be constructed using the
 * {@link Collections#synchronizedMap Collections.synchronizedMap}
 * method.
 *
 * <p> This class is intended primarily for use with key objects whose
 * <tt>equals</tt> methods test for object identity using the
 * <tt>==</tt> operator.  Once such a key is discarded it can never be
 * recreated, so it is impossible to do a lookup of that key in a
 * <tt>WeakHashMap</tt> at some later time and be surprised that its entry
 * has been removed.  This class will work perfectly well with key objects
 * whose <tt>equals</tt> methods are not based upon object identity, such
 * as <tt>String</tt> instances.  With such recreatable key objects,
 * however, the automatic removal of <tt>WeakHashMap</tt> entries whose
 * keys have been discarded may prove to be confusing.
 *
 * <p> The behavior of the <tt>WeakHashMap</tt> class depends in part upon
 * the actions of the garbage collector, so several familiar (though not
 * required) <tt>Map</tt> invariants do not hold for this class.  Because
 * the garbage collector may discard keys at any time, a
 * <tt>WeakHashMap</tt> may behave as though an unknown thread is silently
 * removing entries.  In particular, even if you synchronize on a
 * <tt>WeakHashMap</tt> instance and invoke none of its mutator methods, it
 * is possible for the <tt>size</tt> method to return smaller values over
 * time, for the <tt>isEmpty</tt> method to return <tt>false</tt> and
 * then <tt>true</tt>, for the <tt>containsKey</tt> method to return
 * <tt>true</tt> and later <tt>false</tt> for a given key, for the
 * <tt>get</tt> method to return a value for a given key but later return
 * <tt>null</tt>, for the <tt>put</tt> method to return
 * <tt>null</tt> and the <tt>remove</tt> method to return
 * <tt>false</tt> for a key that previously appeared to be in the map, and
 * for successive examinations of the key set, the value collection, and
 * the entry set to yield successively smaller numbers of elements.
 *
 * <p> Each key object in a <tt>WeakHashMap</tt> is stored indirectly as
 * the referent of a weak reference.  Therefore a key will automatically be
 * removed only after the weak references to it, both inside and outside of the
 * map, have been cleared by the garbage collector.
 *
 * <p> <strong>Implementation note:</strong> The value objects in a
 * <tt>WeakHashMap</tt> are held by ordinary strong references.  Thus care
 * should be taken to ensure that value objects do not strongly refer to their
 * own keys, either directly or indirectly, since that will prevent the keys
 * from being discarded.  Note that a value object may refer indirectly to its
 * key via the <tt>WeakHashMap</tt> itself; that is, a value object may
 * strongly refer to some other key object whose associated value object, in
 * turn, strongly refers to the key of the first value object.  If the values
 * in the map do not rely on the map holding strong references to them, one way
 * to deal with this is to wrap values themselves within
 * <tt>WeakReferences</tt> before
 * inserting, as in: <tt>m.put(key, new WeakReference(value))</tt>,
 * and then unwrapping upon each <tt>get</tt>.
 *
 * <p>The iterators returned by the <tt>iterator</tt> method of the collections
 * returned by all of this class's "collection view methods" are
 * <i>fail-fast</i>: if the map is structurally modified at any time after the
 * iterator is created, in any way except through the iterator's own
 * <tt>remove</tt> method, the iterator will throw a {@link
 * ConcurrentModificationException}.  Thus, in the face of concurrent
 * modification, the iterator fails quickly and cleanly, rather than risking
 * arbitrary, non-deterministic behavior at an undetermined time in the future.
 *
 * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
 * as it is, generally speaking, impossible to make any hard guarantees in the
 * presence of unsynchronized concurrent modification.  Fail-fast iterators
 * throw <tt>ConcurrentModificationException</tt> on a best-effort basis.
 * Therefore, it would be wrong to write a program that depended on this
 * exception for its correctness:  <i>the fail-fast behavior of iterators
 * should be used only to detect bugs.</i>
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * <p>
 *  基于哈希表的实现<tt> Map </tt>界面,使用<em>弱键</em>。 <tt> WeakHashMap </tt>中的条目将在其密钥不再正常使用时自动删除。
 * 更确切地说,给定键的映射的存在不会防止密钥被垃圾收集器丢弃,即,可被确定,完成,然后被回收。
 * 当某个键已被废弃时,其条目将有效地从映射中删除,因此此类的行为与其他<tt> Map </tt>实施有些不同。
 * 
 *  <p>支持空值和null键。此类具有类似于<tt> HashMap </tt>类的性能特性,并具有相同的效率参数<em>初始容量</em>和<em>负载因子</em>。
 * 
 *  <p>与大多数集合类一样,此类不同步。
 * 可以使用{@link Collections#synchronizeMap Collections.synchronizedMap}方法构造同步的<tt> WeakHashMap </tt>。
 * 
 * <p>此类主要用于<tt>等于</tt>方法使用<tt> == </tt>运算符测试对象身份的关键对象。
 * 一旦这样的密钥被丢弃,它就永远不能被重新创建,因此在以后的某个时间里不可能在<tt> WeakHashMap </tt>中查找该密钥,并且令人惊讶的是它的条目已经被删除。
 * 对于<tt>等于</tt>方法不是基于对象标识的关键对象,例如<tt> String </tt>实例,此类将非常适合。
 * 然而,对于这种可重现的键对象,自动删除其键被丢弃的<tt> WeakHashMap </tt>条目可能会令人困惑。
 * 
 * <p> <tt> WeakHashMap </tt>类的行为部分地取决于垃圾收集器的行为,因此一些熟悉的(虽然不是必需的)<tt> Map </tt>不变式不适用于此类。
 * 因为垃圾收集器可以随时丢弃密钥,所以<tt> WeakHashMap </tt>可能表现得好像未知的线程静默地删除条目。
 * 特别是,即使您在<tt> WeakHashMap </tt>实例上同步并且不调用其任何mutator方法,<tt> size </tt>方法也可能随时间返回较小的值, tt> isEmpty </tt>
 * 方法返回<tt> true </tt>,然后<tt> true </tt>和<tt> false </tt>,对于<tt> get </tt>方法返回给定键的值,但稍后返回<tt> null </tt>
 *  > put </tt>方法返回<tt> null </tt>和<tt> remove </tt>方法返回<tt> false </tt>以及用于连续检查密钥集,值集合和条目集以产生连续较小数量的元素。
 * 因为垃圾收集器可以随时丢弃密钥,所以<tt> WeakHashMap </tt>可能表现得好像未知的线程静默地删除条目。
 * 
 *  <p> <tt> WeakHashMap </tt>中的每个键对象间接存储为弱引用的指示。因此,只有在对映射内部和外部的弱引用已被垃圾收集器清除后,键才会自动删除。
 * 
 * <p> <strong>实施注意事项</strong>：<tt> WeakHashMap </tt>中的值对象由普通强引用来保存。
 * 因此,应该注意确保值对象不直接或间接地强烈引用它们自己的键,因为这将防止键被丢弃。
 * 注意,值对象可以通过<tt> WeakHashMap </tt>本身间接引用它的键;也就是说,值对象可以强烈地引用某些其他关键对象,其关联值对象反过来强烈地引用第一个值对象的关键字。
 * 如果映射中的值不依赖于强烈引用它们的映射,那么处理这种情况的一种方法是在插入之前在<tt> WeakReferences </tt>中封装值本身,如：<tt> m.put (key,new WeakRe
 * ference(value))</tt>,然后展开每个<tt> get </tt>。
 * 注意,值对象可以通过<tt> WeakHashMap </tt>本身间接引用它的键;也就是说,值对象可以强烈地引用某些其他关键对象,其关联值对象反过来强烈地引用第一个值对象的关键字。
 * 
 *  <p>由所有此类的"集合视图方法"返回的集合的<tt> iterator </tt>方法返回的迭代器<i> fail-fast </i>：如果地图在结构上被修改在创建迭代器之后的任何时候,除了通过迭代
 * 器自己的<tt> remove </tt>方法,迭代器将抛出一个{@link ConcurrentModificationException}。
 * 因此,面对并发修改,迭代器快速而干净地失败,而不是在将来的未确定时间冒任意的,非确定性行为的风险。
 * 
 * <p>请注意,迭代器的故障快速行为不能得到保证,因为一般来说,在不同步并发修改的情况下不可能做出任何硬的保证。
 * 故障快速迭代器在尽力而为的基础上抛出<tt> ConcurrentModificationException </tt>。
 * 因此,编写依赖于此异常的程序的正确性是错误的：<i>迭代器的故障快速行为应该仅用于检测错误。</i>。
 * 
 *  <p>此类是的成员
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 *  Java集合框架</a>。
 * 
 * 
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 *
 * @author      Doug Lea
 * @author      Josh Bloch
 * @author      Mark Reinhold
 * @since       1.2
 * @see         java.util.HashMap
 * @see         java.lang.ref.WeakReference
 */
public class WeakHashMap<K,V>
    extends AbstractMap<K,V>
    implements Map<K,V> {

    /**
     * The default initial capacity -- MUST be a power of two.
     * <p>
     *  默认的初始容量 - 必须是2的幂。
     * 
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     * <p>
     *  最大容量,如果较高的值由具有参数的任何构造函数隐式指定,则使用此容量。必须是二的幂<= 1 << 30。
     * 
     */
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * The load factor used when none specified in constructor.
     * <p>
     *  在构造函数中未指定时使用的负载系数。
     * 
     */
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * The table, resized as necessary. Length MUST Always be a power of two.
     * <p>
     *  该表,必要时调整大小。长度必须始终为二的幂。
     * 
     */
    Entry<K,V>[] table;

    /**
     * The number of key-value mappings contained in this weak hash map.
     * <p>
     *  此弱散列映射中包含的键值映射的数量。
     * 
     */
    private int size;

    /**
     * The next size value at which to resize (capacity * load factor).
     * <p>
     *  调整大小的下一个大小值(容量*负载系数)。
     * 
     */
    private int threshold;

    /**
     * The load factor for the hash table.
     * <p>
     *  哈希表的负载系数。
     * 
     */
    private final float loadFactor;

    /**
     * Reference queue for cleared WeakEntries
     * <p>
     *  清除WeakEntries的引用队列
     * 
     */
    private final ReferenceQueue<Object> queue = new ReferenceQueue<>();

    /**
     * The number of times this WeakHashMap has been structurally modified.
     * Structural modifications are those that change the number of
     * mappings in the map or otherwise modify its internal structure
     * (e.g., rehash).  This field is used to make iterators on
     * Collection-views of the map fail-fast.
     *
     * <p>
     *  此WeakHashMap已结构修改的次数。结构修改是改变映射中的映射的数量或以其它方式修改其内部结构(例如,重新哈希)的修改。此字段用于对映射的集合视图使故障快速的迭代器。
     * 
     * 
     * @see ConcurrentModificationException
     */
    int modCount;

    @SuppressWarnings("unchecked")
    private Entry<K,V>[] newTable(int n) {
        return (Entry<K,V>[]) new Entry<?,?>[n];
    }

    /**
     * Constructs a new, empty <tt>WeakHashMap</tt> with the given initial
     * capacity and the given load factor.
     *
     * <p>
     *  用给定的初始容量和给定的负载因子构造一个新的空的<tt> WeakHashMap </tt>。
     * 
     * 
     * @param  initialCapacity The initial capacity of the <tt>WeakHashMap</tt>
     * @param  loadFactor      The load factor of the <tt>WeakHashMap</tt>
     * @throws IllegalArgumentException if the initial capacity is negative,
     *         or if the load factor is nonpositive.
     */
    public WeakHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Initial Capacity: "+
                                               initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;

        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal Load factor: "+
                                               loadFactor);
        int capacity = 1;
        while (capacity < initialCapacity)
            capacity <<= 1;
        table = newTable(capacity);
        this.loadFactor = loadFactor;
        threshold = (int)(capacity * loadFactor);
    }

    /**
     * Constructs a new, empty <tt>WeakHashMap</tt> with the given initial
     * capacity and the default load factor (0.75).
     *
     * <p>
     * 使用给定的初始容量和默认负载系数(0.75)构造一个新的空的<tt> WeakHashMap </tt>。
     * 
     * 
     * @param  initialCapacity The initial capacity of the <tt>WeakHashMap</tt>
     * @throws IllegalArgumentException if the initial capacity is negative
     */
    public WeakHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Constructs a new, empty <tt>WeakHashMap</tt> with the default initial
     * capacity (16) and load factor (0.75).
     * <p>
     *  使用默认初始容量(16)和负载系数(0.75)构造一个新的空的<tt> WeakHashMap </tt>。
     * 
     */
    public WeakHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Constructs a new <tt>WeakHashMap</tt> with the same mappings as the
     * specified map.  The <tt>WeakHashMap</tt> is created with the default
     * load factor (0.75) and an initial capacity sufficient to hold the
     * mappings in the specified map.
     *
     * <p>
     *  使用与指定地图相同的映射构造新的<tt> WeakHashMap </tt>。 <tt> WeakHashMap </tt>是使用默认负载系数(0.75)和足以在指定映射中保存映射的初始容量创建的。
     * 
     * 
     * @param   m the map whose mappings are to be placed in this map
     * @throws  NullPointerException if the specified map is null
     * @since   1.3
     */
    public WeakHashMap(Map<? extends K, ? extends V> m) {
        this(Math.max((int) (m.size() / DEFAULT_LOAD_FACTOR) + 1,
                DEFAULT_INITIAL_CAPACITY),
             DEFAULT_LOAD_FACTOR);
        putAll(m);
    }

    // internal utilities

    /**
     * Value representing null keys inside tables.
     * <p>
     *  表示表中的空键的值。
     * 
     */
    private static final Object NULL_KEY = new Object();

    /**
     * Use NULL_KEY for key if it is null.
     * <p>
     *  如果键为空,请使用NULL_KEY。
     * 
     */
    private static Object maskNull(Object key) {
        return (key == null) ? NULL_KEY : key;
    }

    /**
     * Returns internal representation of null key back to caller as null.
     * <p>
     *  将null键的内部表示返回给调用者为null。
     * 
     */
    static Object unmaskNull(Object key) {
        return (key == NULL_KEY) ? null : key;
    }

    /**
     * Checks for equality of non-null reference x and possibly-null y.  By
     * default uses Object.equals.
     * <p>
     *  检查非空引用x和可能为null的y的相等性。默认情况下使用Object.equals。
     * 
     */
    private static boolean eq(Object x, Object y) {
        return x == y || x.equals(y);
    }

    /**
     * Retrieve object hash code and applies a supplemental hash function to the
     * result hash, which defends against poor quality hash functions.  This is
     * critical because HashMap uses power-of-two length hash tables, that
     * otherwise encounter collisions for hashCodes that do not differ
     * in lower bits.
     * <p>
     *  检索对象哈希码,并对结果哈希应用补充哈希函数,该哈希函数防御质量差的哈希函数。这是至关重要的,因为HashMap使用两个长度的乘方哈希表,否则会遇到在低位方面没有差异的hashCodes的冲突。
     * 
     */
    final int hash(Object k) {
        int h = k.hashCode();

        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    /**
     * Returns index for hash code h.
     * <p>
     *  返回哈希码h的索引。
     * 
     */
    private static int indexFor(int h, int length) {
        return h & (length-1);
    }

    /**
     * Expunges stale entries from the table.
     * <p>
     *  从表中清除失效的条目。
     * 
     */
    private void expungeStaleEntries() {
        for (Object x; (x = queue.poll()) != null; ) {
            synchronized (queue) {
                @SuppressWarnings("unchecked")
                    Entry<K,V> e = (Entry<K,V>) x;
                int i = indexFor(e.hash, table.length);

                Entry<K,V> prev = table[i];
                Entry<K,V> p = prev;
                while (p != null) {
                    Entry<K,V> next = p.next;
                    if (p == e) {
                        if (prev == e)
                            table[i] = next;
                        else
                            prev.next = next;
                        // Must not null out e.next;
                        // stale entries may be in use by a HashIterator
                        e.value = null; // Help GC
                        size--;
                        break;
                    }
                    prev = p;
                    p = next;
                }
            }
        }
    }

    /**
     * Returns the table after first expunging stale entries.
     * <p>
     *  首先清除失效条目后返回表。
     * 
     */
    private Entry<K,V>[] getTable() {
        expungeStaleEntries();
        return table;
    }

    /**
     * Returns the number of key-value mappings in this map.
     * This result is a snapshot, and may not reflect unprocessed
     * entries that will be removed before next attempted access
     * because they are no longer referenced.
     * <p>
     *  返回此地图中的键值映射的数量。此结果是快照,并且可能不反映在下次尝试访问之前将被删除的未处理条目,因为它们不再被引用。
     * 
     */
    public int size() {
        if (size == 0)
            return 0;
        expungeStaleEntries();
        return size;
    }

    /**
     * Returns <tt>true</tt> if this map contains no key-value mappings.
     * This result is a snapshot, and may not reflect unprocessed
     * entries that will be removed before next attempted access
     * because they are no longer referenced.
     * <p>
     * 如果此地图不包含键值映射,则返回<tt> true </tt>。此结果是快照,并且可能不反映在下次尝试访问之前将被删除的未处理条目,因为它们不再被引用。
     * 
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * <p>More formally, if this map contains a mapping from a key
     * {@code k} to a value {@code v} such that {@code (key==null ? k==null :
     * key.equals(k))}, then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There can be at most one such mapping.)
     *
     * <p>A return value of {@code null} does not <i>necessarily</i>
     * indicate that the map contains no mapping for the key; it's also
     * possible that the map explicitly maps the key to {@code null}.
     * The {@link #containsKey containsKey} operation may be used to
     * distinguish these two cases.
     *
     * <p>
     *  返回指定键映射到的值,如果此映射不包含键的映射,则返回{@code null}。
     * 
     *  更正式地说,如果此映射包含从密钥{@code k}到值{@code v}的映射,使得{@code(key == null?k == null：key.equals(k) )},那么这个方法返回{@code v}
     * ;否则返回{@code null}。
     *  (最多只能有一个这样的映射。)。
     * 
     *  <p> {@code null}的返回值不一定</i>表示地图不包含键的映射;也有可能映射将键明确映射到{@code null}。
     *  {@link #containsKey containsKey}操作可用于区分这两种情况。
     * 
     * 
     * @see #put(Object, Object)
     */
    public V get(Object key) {
        Object k = maskNull(key);
        int h = hash(k);
        Entry<K,V>[] tab = getTable();
        int index = indexFor(h, tab.length);
        Entry<K,V> e = tab[index];
        while (e != null) {
            if (e.hash == h && eq(k, e.get()))
                return e.value;
            e = e.next;
        }
        return null;
    }

    /**
     * Returns <tt>true</tt> if this map contains a mapping for the
     * specified key.
     *
     * <p>
     *  如果此地图包含指定键的映射,则返回<tt> true </tt>。
     * 
     * 
     * @param  key   The key whose presence in this map is to be tested
     * @return <tt>true</tt> if there is a mapping for <tt>key</tt>;
     *         <tt>false</tt> otherwise
     */
    public boolean containsKey(Object key) {
        return getEntry(key) != null;
    }

    /**
     * Returns the entry associated with the specified key in this map.
     * Returns null if the map contains no mapping for this key.
     * <p>
     *  返回与此地图中指定键相关联的条目。如果映射不包含此键的映射,则返回null。
     * 
     */
    Entry<K,V> getEntry(Object key) {
        Object k = maskNull(key);
        int h = hash(k);
        Entry<K,V>[] tab = getTable();
        int index = indexFor(h, tab.length);
        Entry<K,V> e = tab[index];
        while (e != null && !(e.hash == h && eq(k, e.get())))
            e = e.next;
        return e;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for this key, the old
     * value is replaced.
     *
     * <p>
     *  将指定的值与此映射中的指定键相关联。如果映射先前包含此键的映射,则替换旧值。
     * 
     * 
     * @param key key with which the specified value is to be associated.
     * @param value value to be associated with the specified key.
     * @return the previous value associated with <tt>key</tt>, or
     *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
     *         (A <tt>null</tt> return can also indicate that the map
     *         previously associated <tt>null</tt> with <tt>key</tt>.)
     */
    public V put(K key, V value) {
        Object k = maskNull(key);
        int h = hash(k);
        Entry<K,V>[] tab = getTable();
        int i = indexFor(h, tab.length);

        for (Entry<K,V> e = tab[i]; e != null; e = e.next) {
            if (h == e.hash && eq(k, e.get())) {
                V oldValue = e.value;
                if (value != oldValue)
                    e.value = value;
                return oldValue;
            }
        }

        modCount++;
        Entry<K,V> e = tab[i];
        tab[i] = new Entry<>(k, value, queue, h, e);
        if (++size >= threshold)
            resize(tab.length * 2);
        return null;
    }

    /**
     * Rehashes the contents of this map into a new array with a
     * larger capacity.  This method is called automatically when the
     * number of keys in this map reaches its threshold.
     *
     * If current capacity is MAXIMUM_CAPACITY, this method does not
     * resize the map, but sets threshold to Integer.MAX_VALUE.
     * This has the effect of preventing future calls.
     *
     * <p>
     *  将此映射的内容重新加热为具有更大容量的新阵列。当此映射中的键数达到其阈值时,将自动调用此方法。
     * 
     * 如果当前容量为MAXIMUM_CAPACITY,则此方法不会调整映射的大小,而是将阈值设置为Integer.MAX_VALUE。这具有防止未来呼叫的效果。
     * 
     * 
     * @param newCapacity the new capacity, MUST be a power of two;
     *        must be greater than current capacity unless current
     *        capacity is MAXIMUM_CAPACITY (in which case value
     *        is irrelevant).
     */
    void resize(int newCapacity) {
        Entry<K,V>[] oldTable = getTable();
        int oldCapacity = oldTable.length;
        if (oldCapacity == MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        Entry<K,V>[] newTable = newTable(newCapacity);
        transfer(oldTable, newTable);
        table = newTable;

        /*
         * If ignoring null elements and processing ref queue caused massive
         * shrinkage, then restore old table.  This should be rare, but avoids
         * unbounded expansion of garbage-filled tables.
         * <p>
         *  如果忽略空元素和处理ref队列导致大量收缩,那么恢复旧表。这应该是很少见的,但是避免了垃圾填充表的无界扩展。
         * 
         */
        if (size >= threshold / 2) {
            threshold = (int)(newCapacity * loadFactor);
        } else {
            expungeStaleEntries();
            transfer(newTable, oldTable);
            table = oldTable;
        }
    }

    /** Transfers all entries from src to dest tables */
    private void transfer(Entry<K,V>[] src, Entry<K,V>[] dest) {
        for (int j = 0; j < src.length; ++j) {
            Entry<K,V> e = src[j];
            src[j] = null;
            while (e != null) {
                Entry<K,V> next = e.next;
                Object key = e.get();
                if (key == null) {
                    e.next = null;  // Help GC
                    e.value = null; //  "   "
                    size--;
                } else {
                    int i = indexFor(e.hash, dest.length);
                    e.next = dest[i];
                    dest[i] = e;
                }
                e = next;
            }
        }
    }

    /**
     * Copies all of the mappings from the specified map to this map.
     * These mappings will replace any mappings that this map had for any
     * of the keys currently in the specified map.
     *
     * <p>
     *  将指定映射中的所有映射复制到此映射。这些映射将替换该映射对于当前在指定映射中的任何键的任何映射。
     * 
     * 
     * @param m mappings to be stored in this map.
     * @throws  NullPointerException if the specified map is null.
     */
    public void putAll(Map<? extends K, ? extends V> m) {
        int numKeysToBeAdded = m.size();
        if (numKeysToBeAdded == 0)
            return;

        /*
         * Expand the map if the map if the number of mappings to be added
         * is greater than or equal to threshold.  This is conservative; the
         * obvious condition is (m.size() + size) >= threshold, but this
         * condition could result in a map with twice the appropriate capacity,
         * if the keys to be added overlap with the keys already in this map.
         * By using the conservative calculation, we subject ourself
         * to at most one extra resize.
         * <p>
         *  如果映射的数量大于或等于阈值,则展开映射。
         * 这是保守的;明显的条件是(m.size()+ size)> = threshold,但是如果要添加的键与已经在此映射中的键重叠,则该条件可以产生具有两倍适当容量的映射。
         * 通过使用保守的计算,我们自己最多一个额外的调整大小。
         * 
         */
        if (numKeysToBeAdded > threshold) {
            int targetCapacity = (int)(numKeysToBeAdded / loadFactor + 1);
            if (targetCapacity > MAXIMUM_CAPACITY)
                targetCapacity = MAXIMUM_CAPACITY;
            int newCapacity = table.length;
            while (newCapacity < targetCapacity)
                newCapacity <<= 1;
            if (newCapacity > table.length)
                resize(newCapacity);
        }

        for (Map.Entry<? extends K, ? extends V> e : m.entrySet())
            put(e.getKey(), e.getValue());
    }

    /**
     * Removes the mapping for a key from this weak hash map if it is present.
     * More formally, if this map contains a mapping from key <tt>k</tt> to
     * value <tt>v</tt> such that <code>(key==null ?  k==null :
     * key.equals(k))</code>, that mapping is removed.  (The map can contain
     * at most one such mapping.)
     *
     * <p>Returns the value to which this map previously associated the key,
     * or <tt>null</tt> if the map contained no mapping for the key.  A
     * return value of <tt>null</tt> does not <i>necessarily</i> indicate
     * that the map contained no mapping for the key; it's also possible
     * that the map explicitly mapped the key to <tt>null</tt>.
     *
     * <p>The map will not contain a mapping for the specified key once the
     * call returns.
     *
     * <p>
     *  从该弱散列映射中删除该映射(如果存在)。
     * 更正式地,如果该映射包含从键<tt> k </tt>到值<tt> v </tt>的映射,使得<code>(key == null?k == null：key.equals ))</code>,则删除该映
     * 射。
     *  从该弱散列映射中删除该映射(如果存在)。 (地图最多只能包含一个这样的映射。)。
     * 
     * <p>返回此地图先前与键相关联的值,如果地图未包含键的映射,则返回<tt> null </tt>。
     * 返回值<tt> null </tt>不一定表示该映射不包含键的映射;还有可能映射将键明确映射到<tt> null </tt>。
     * 
     *  <p>在调用返回后,地图将不包含指定键的映射。
     * 
     * 
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with <tt>key</tt>, or
     *         <tt>null</tt> if there was no mapping for <tt>key</tt>
     */
    public V remove(Object key) {
        Object k = maskNull(key);
        int h = hash(k);
        Entry<K,V>[] tab = getTable();
        int i = indexFor(h, tab.length);
        Entry<K,V> prev = tab[i];
        Entry<K,V> e = prev;

        while (e != null) {
            Entry<K,V> next = e.next;
            if (h == e.hash && eq(k, e.get())) {
                modCount++;
                size--;
                if (prev == e)
                    tab[i] = next;
                else
                    prev.next = next;
                return e.value;
            }
            prev = e;
            e = next;
        }

        return null;
    }

    /** Special version of remove needed by Entry set */
    boolean removeMapping(Object o) {
        if (!(o instanceof Map.Entry))
            return false;
        Entry<K,V>[] tab = getTable();
        Map.Entry<?,?> entry = (Map.Entry<?,?>)o;
        Object k = maskNull(entry.getKey());
        int h = hash(k);
        int i = indexFor(h, tab.length);
        Entry<K,V> prev = tab[i];
        Entry<K,V> e = prev;

        while (e != null) {
            Entry<K,V> next = e.next;
            if (h == e.hash && e.equals(entry)) {
                modCount++;
                size--;
                if (prev == e)
                    tab[i] = next;
                else
                    prev.next = next;
                return true;
            }
            prev = e;
            e = next;
        }

        return false;
    }

    /**
     * Removes all of the mappings from this map.
     * The map will be empty after this call returns.
     * <p>
     *  从此地图中删除所有映射。此调用返回后,地图将为空。
     * 
     */
    public void clear() {
        // clear out ref queue. We don't need to expunge entries
        // since table is getting cleared.
        while (queue.poll() != null)
            ;

        modCount++;
        Arrays.fill(table, null);
        size = 0;

        // Allocation of array may have caused GC, which may have caused
        // additional entries to go stale.  Removing these entries from the
        // reference queue will make them eligible for reclamation.
        while (queue.poll() != null)
            ;
    }

    /**
     * Returns <tt>true</tt> if this map maps one or more keys to the
     * specified value.
     *
     * <p>
     *  如果此映射将一个或多个键映射到指定的值,则返回<tt> true </tt>。
     * 
     * 
     * @param value value whose presence in this map is to be tested
     * @return <tt>true</tt> if this map maps one or more keys to the
     *         specified value
     */
    public boolean containsValue(Object value) {
        if (value==null)
            return containsNullValue();

        Entry<K,V>[] tab = getTable();
        for (int i = tab.length; i-- > 0;)
            for (Entry<K,V> e = tab[i]; e != null; e = e.next)
                if (value.equals(e.value))
                    return true;
        return false;
    }

    /**
     * Special-case code for containsValue with null argument
     * <p>
     *  包含null参数的containsValue的特殊情况代码
     * 
     */
    private boolean containsNullValue() {
        Entry<K,V>[] tab = getTable();
        for (int i = tab.length; i-- > 0;)
            for (Entry<K,V> e = tab[i]; e != null; e = e.next)
                if (e.value==null)
                    return true;
        return false;
    }

    /**
     * The entries in this hash table extend WeakReference, using its main ref
     * field as the key.
     * <p>
     *  此哈希表中的条目扩展WeakReference,使用其主ref字段作为键。
     * 
     */
    private static class Entry<K,V> extends WeakReference<Object> implements Map.Entry<K,V> {
        V value;
        final int hash;
        Entry<K,V> next;

        /**
         * Creates new entry.
         * <p>
         *  创建新条目。
         * 
         */
        Entry(Object key, V value,
              ReferenceQueue<Object> queue,
              int hash, Entry<K,V> next) {
            super(key, queue);
            this.value = value;
            this.hash  = hash;
            this.next  = next;
        }

        @SuppressWarnings("unchecked")
        public K getKey() {
            return (K) WeakHashMap.unmaskNull(get());
        }

        public V getValue() {
            return value;
        }

        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public boolean equals(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry<?,?> e = (Map.Entry<?,?>)o;
            K k1 = getKey();
            Object k2 = e.getKey();
            if (k1 == k2 || (k1 != null && k1.equals(k2))) {
                V v1 = getValue();
                Object v2 = e.getValue();
                if (v1 == v2 || (v1 != null && v1.equals(v2)))
                    return true;
            }
            return false;
        }

        public int hashCode() {
            K k = getKey();
            V v = getValue();
            return Objects.hashCode(k) ^ Objects.hashCode(v);
        }

        public String toString() {
            return getKey() + "=" + getValue();
        }
    }

    private abstract class HashIterator<T> implements Iterator<T> {
        private int index;
        private Entry<K,V> entry;
        private Entry<K,V> lastReturned;
        private int expectedModCount = modCount;

        /**
         * Strong reference needed to avoid disappearance of key
         * between hasNext and next
         * <p>
         *  强参考需要避免hasNext和next之间的键消失
         * 
         */
        private Object nextKey;

        /**
         * Strong reference needed to avoid disappearance of key
         * between nextEntry() and any use of the entry
         * <p>
         *  强引用需要避免在nextEntry()和任何使用条目之间的键消失
         * 
         */
        private Object currentKey;

        HashIterator() {
            index = isEmpty() ? 0 : table.length;
        }

        public boolean hasNext() {
            Entry<K,V>[] t = table;

            while (nextKey == null) {
                Entry<K,V> e = entry;
                int i = index;
                while (e == null && i > 0)
                    e = t[--i];
                entry = e;
                index = i;
                if (e == null) {
                    currentKey = null;
                    return false;
                }
                nextKey = e.get(); // hold on to key in strong ref
                if (nextKey == null)
                    entry = entry.next;
            }
            return true;
        }

        /** The common parts of next() across different types of iterators */
        protected Entry<K,V> nextEntry() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (nextKey == null && !hasNext())
                throw new NoSuchElementException();

            lastReturned = entry;
            entry = entry.next;
            currentKey = nextKey;
            nextKey = null;
            return lastReturned;
        }

        public void remove() {
            if (lastReturned == null)
                throw new IllegalStateException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();

            WeakHashMap.this.remove(currentKey);
            expectedModCount = modCount;
            lastReturned = null;
            currentKey = null;
        }

    }

    private class ValueIterator extends HashIterator<V> {
        public V next() {
            return nextEntry().value;
        }
    }

    private class KeyIterator extends HashIterator<K> {
        public K next() {
            return nextEntry().getKey();
        }
    }

    private class EntryIterator extends HashIterator<Map.Entry<K,V>> {
        public Map.Entry<K,V> next() {
            return nextEntry();
        }
    }

    // Views

    private transient Set<Map.Entry<K,V>> entrySet;

    /**
     * Returns a {@link Set} view of the keys contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own <tt>remove</tt> operation), the results of
     * the iteration are undefined.  The set supports element removal,
     * which removes the corresponding mapping from the map, via the
     * <tt>Iterator.remove</tt>, <tt>Set.remove</tt>,
     * <tt>removeAll</tt>, <tt>retainAll</tt>, and <tt>clear</tt>
     * operations.  It does not support the <tt>add</tt> or <tt>addAll</tt>
     * operations.
     * <p>
     * 返回此地图中包含的键的{@link Set}视图。该集合由映射支持,因此对映射的更改反映在集合中,反之亦然。
     * 如果在迭代集合的过程中修改映射(除非通过迭代器自己的<tt> remove </tt>操作),迭代的结果是未定义的。
     * 集合支持元素删除,它通过<tt> Iterator.remove </tt>,<tt> Set.remove </tt>,<tt> removeAll </tt>,<tt从地图中删除相应的映射> ret
     * ainAll </tt>和<tt>清除</tt>操作。
     * 如果在迭代集合的过程中修改映射(除非通过迭代器自己的<tt> remove </tt>操作),迭代的结果是未定义的。它不支持<tt>添加</tt>或<tt> addAll </tt>操作。
     * 
     */
    public Set<K> keySet() {
        Set<K> ks = keySet;
        return (ks != null ? ks : (keySet = new KeySet()));
    }

    private class KeySet extends AbstractSet<K> {
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        public int size() {
            return WeakHashMap.this.size();
        }

        public boolean contains(Object o) {
            return containsKey(o);
        }

        public boolean remove(Object o) {
            if (containsKey(o)) {
                WeakHashMap.this.remove(o);
                return true;
            }
            else
                return false;
        }

        public void clear() {
            WeakHashMap.this.clear();
        }

        public Spliterator<K> spliterator() {
            return new KeySpliterator<>(WeakHashMap.this, 0, -1, 0, 0);
        }
    }

    /**
     * Returns a {@link Collection} view of the values contained in this map.
     * The collection is backed by the map, so changes to the map are
     * reflected in the collection, and vice-versa.  If the map is
     * modified while an iteration over the collection is in progress
     * (except through the iterator's own <tt>remove</tt> operation),
     * the results of the iteration are undefined.  The collection
     * supports element removal, which removes the corresponding
     * mapping from the map, via the <tt>Iterator.remove</tt>,
     * <tt>Collection.remove</tt>, <tt>removeAll</tt>,
     * <tt>retainAll</tt> and <tt>clear</tt> operations.  It does not
     * support the <tt>add</tt> or <tt>addAll</tt> operations.
     * <p>
     *  返回此地图中包含的值的{@link Collection}视图。集合由地图支持,因此对地图的更改会反映在集合中,反之亦然。
     * 如果在集合的迭代正在进行时修改映射(除非通过迭代器自己的<tt> remove </tt>操作),迭代的结果是未定义的。
     * 集合支持元素删除,通过<tt> Iterator.remove </tt>,<tt> Collection.remove </tt>,<tt> removeAll </tt>,<tt从地图中删除相应的映射>
     *  retainAll </tt>和<tt>清除</tt>操作。
     * 如果在集合的迭代正在进行时修改映射(除非通过迭代器自己的<tt> remove </tt>操作),迭代的结果是未定义的。它不支持<tt>添加</tt>或<tt> addAll </tt>操作。
     * 
     */
    public Collection<V> values() {
        Collection<V> vs = values;
        return (vs != null) ? vs : (values = new Values());
    }

    private class Values extends AbstractCollection<V> {
        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        public int size() {
            return WeakHashMap.this.size();
        }

        public boolean contains(Object o) {
            return containsValue(o);
        }

        public void clear() {
            WeakHashMap.this.clear();
        }

        public Spliterator<V> spliterator() {
            return new ValueSpliterator<>(WeakHashMap.this, 0, -1, 0, 0);
        }
    }

    /**
     * Returns a {@link Set} view of the mappings contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own <tt>remove</tt> operation, or through the
     * <tt>setValue</tt> operation on a map entry returned by the
     * iterator) the results of the iteration are undefined.  The set
     * supports element removal, which removes the corresponding
     * mapping from the map, via the <tt>Iterator.remove</tt>,
     * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retainAll</tt> and
     * <tt>clear</tt> operations.  It does not support the
     * <tt>add</tt> or <tt>addAll</tt> operations.
     * <p>
     * 返回此地图中包含的映射的{@link Set}视图。该集合由映射支持,因此对映射的更改反映在集合中,反之亦然。
     * 如果在对迭代器执行迭代(即通过迭代器自己的<tt> remove </tt>操作,或通过对迭代器返回的映射条目执行<tt> setValue </tt>操作) )迭代的结果是未定义的。
     * 集合支持元素删除,它通过<tt> Iterator.remove </tt>,<tt> Set.remove </tt>,<tt> removeAll </tt>,<tt从地图中删除相应的映射> ret
     * ainAll </tt>和<tt>清除</tt>操作。
     * 如果在对迭代器执行迭代(即通过迭代器自己的<tt> remove </tt>操作,或通过对迭代器返回的映射条目执行<tt> setValue </tt>操作) )迭代的结果是未定义的。
     */
    public Set<Map.Entry<K,V>> entrySet() {
        Set<Map.Entry<K,V>> es = entrySet;
        return es != null ? es : (entrySet = new EntrySet());
    }

    private class EntrySet extends AbstractSet<Map.Entry<K,V>> {
        public Iterator<Map.Entry<K,V>> iterator() {
            return new EntryIterator();
        }

        public boolean contains(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry<?,?> e = (Map.Entry<?,?>)o;
            Entry<K,V> candidate = getEntry(e.getKey());
            return candidate != null && candidate.equals(e);
        }

        public boolean remove(Object o) {
            return removeMapping(o);
        }

        public int size() {
            return WeakHashMap.this.size();
        }

        public void clear() {
            WeakHashMap.this.clear();
        }

        private List<Map.Entry<K,V>> deepCopy() {
            List<Map.Entry<K,V>> list = new ArrayList<>(size());
            for (Map.Entry<K,V> e : this)
                list.add(new AbstractMap.SimpleEntry<>(e));
            return list;
        }

        public Object[] toArray() {
            return deepCopy().toArray();
        }

        public <T> T[] toArray(T[] a) {
            return deepCopy().toArray(a);
        }

        public Spliterator<Map.Entry<K,V>> spliterator() {
            return new EntrySpliterator<>(WeakHashMap.this, 0, -1, 0, 0);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        Objects.requireNonNull(action);
        int expectedModCount = modCount;

        Entry<K, V>[] tab = getTable();
        for (Entry<K, V> entry : tab) {
            while (entry != null) {
                Object key = entry.get();
                if (key != null) {
                    action.accept((K)WeakHashMap.unmaskNull(key), entry.value);
                }
                entry = entry.next;

                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        Objects.requireNonNull(function);
        int expectedModCount = modCount;

        Entry<K, V>[] tab = getTable();;
        for (Entry<K, V> entry : tab) {
            while (entry != null) {
                Object key = entry.get();
                if (key != null) {
                    entry.value = function.apply((K)WeakHashMap.unmaskNull(key), entry.value);
                }
                entry = entry.next;

                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        }
    }

    /**
     * Similar form as other hash Spliterators, but skips dead
     * elements.
     * <p>
     * 它不支持<tt>添加</tt>或<tt> addAll </tt>操作。
     * 
     */
    static class WeakHashMapSpliterator<K,V> {
        final WeakHashMap<K,V> map;
        WeakHashMap.Entry<K,V> current; // current node
        int index;             // current index, modified on advance/split
        int fence;             // -1 until first use; then one past last index
        int est;               // size estimate
        int expectedModCount;  // for comodification checks

        WeakHashMapSpliterator(WeakHashMap<K,V> m, int origin,
                               int fence, int est,
                               int expectedModCount) {
            this.map = m;
            this.index = origin;
            this.fence = fence;
            this.est = est;
            this.expectedModCount = expectedModCount;
        }

        final int getFence() { // initialize fence and size on first use
            int hi;
            if ((hi = fence) < 0) {
                WeakHashMap<K,V> m = map;
                est = m.size();
                expectedModCount = m.modCount;
                hi = fence = m.table.length;
            }
            return hi;
        }

        public final long estimateSize() {
            getFence(); // force init
            return (long) est;
        }
    }

    static final class KeySpliterator<K,V>
        extends WeakHashMapSpliterator<K,V>
        implements Spliterator<K> {
        KeySpliterator(WeakHashMap<K,V> m, int origin, int fence, int est,
                       int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        public KeySpliterator<K,V> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid) ? null :
                new KeySpliterator<K,V>(map, lo, index = mid, est >>>= 1,
                                        expectedModCount);
        }

        public void forEachRemaining(Consumer<? super K> action) {
            int i, hi, mc;
            if (action == null)
                throw new NullPointerException();
            WeakHashMap<K,V> m = map;
            WeakHashMap.Entry<K,V>[] tab = m.table;
            if ((hi = fence) < 0) {
                mc = expectedModCount = m.modCount;
                hi = fence = tab.length;
            }
            else
                mc = expectedModCount;
            if (tab.length >= hi && (i = index) >= 0 &&
                (i < (index = hi) || current != null)) {
                WeakHashMap.Entry<K,V> p = current;
                current = null; // exhaust
                do {
                    if (p == null)
                        p = tab[i++];
                    else {
                        Object x = p.get();
                        p = p.next;
                        if (x != null) {
                            @SuppressWarnings("unchecked") K k =
                                (K) WeakHashMap.unmaskNull(x);
                            action.accept(k);
                        }
                    }
                } while (p != null || i < hi);
            }
            if (m.modCount != mc)
                throw new ConcurrentModificationException();
        }

        public boolean tryAdvance(Consumer<? super K> action) {
            int hi;
            if (action == null)
                throw new NullPointerException();
            WeakHashMap.Entry<K,V>[] tab = map.table;
            if (tab.length >= (hi = getFence()) && index >= 0) {
                while (current != null || index < hi) {
                    if (current == null)
                        current = tab[index++];
                    else {
                        Object x = current.get();
                        current = current.next;
                        if (x != null) {
                            @SuppressWarnings("unchecked") K k =
                                (K) WeakHashMap.unmaskNull(x);
                            action.accept(k);
                            if (map.modCount != expectedModCount)
                                throw new ConcurrentModificationException();
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        public int characteristics() {
            return Spliterator.DISTINCT;
        }
    }

    static final class ValueSpliterator<K,V>
        extends WeakHashMapSpliterator<K,V>
        implements Spliterator<V> {
        ValueSpliterator(WeakHashMap<K,V> m, int origin, int fence, int est,
                         int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        public ValueSpliterator<K,V> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid) ? null :
                new ValueSpliterator<K,V>(map, lo, index = mid, est >>>= 1,
                                          expectedModCount);
        }

        public void forEachRemaining(Consumer<? super V> action) {
            int i, hi, mc;
            if (action == null)
                throw new NullPointerException();
            WeakHashMap<K,V> m = map;
            WeakHashMap.Entry<K,V>[] tab = m.table;
            if ((hi = fence) < 0) {
                mc = expectedModCount = m.modCount;
                hi = fence = tab.length;
            }
            else
                mc = expectedModCount;
            if (tab.length >= hi && (i = index) >= 0 &&
                (i < (index = hi) || current != null)) {
                WeakHashMap.Entry<K,V> p = current;
                current = null; // exhaust
                do {
                    if (p == null)
                        p = tab[i++];
                    else {
                        Object x = p.get();
                        V v = p.value;
                        p = p.next;
                        if (x != null)
                            action.accept(v);
                    }
                } while (p != null || i < hi);
            }
            if (m.modCount != mc)
                throw new ConcurrentModificationException();
        }

        public boolean tryAdvance(Consumer<? super V> action) {
            int hi;
            if (action == null)
                throw new NullPointerException();
            WeakHashMap.Entry<K,V>[] tab = map.table;
            if (tab.length >= (hi = getFence()) && index >= 0) {
                while (current != null || index < hi) {
                    if (current == null)
                        current = tab[index++];
                    else {
                        Object x = current.get();
                        V v = current.value;
                        current = current.next;
                        if (x != null) {
                            action.accept(v);
                            if (map.modCount != expectedModCount)
                                throw new ConcurrentModificationException();
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        public int characteristics() {
            return 0;
        }
    }

    static final class EntrySpliterator<K,V>
        extends WeakHashMapSpliterator<K,V>
        implements Spliterator<Map.Entry<K,V>> {
        EntrySpliterator(WeakHashMap<K,V> m, int origin, int fence, int est,
                       int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        public EntrySpliterator<K,V> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid) ? null :
                new EntrySpliterator<K,V>(map, lo, index = mid, est >>>= 1,
                                          expectedModCount);
        }


        public void forEachRemaining(Consumer<? super Map.Entry<K, V>> action) {
            int i, hi, mc;
            if (action == null)
                throw new NullPointerException();
            WeakHashMap<K,V> m = map;
            WeakHashMap.Entry<K,V>[] tab = m.table;
            if ((hi = fence) < 0) {
                mc = expectedModCount = m.modCount;
                hi = fence = tab.length;
            }
            else
                mc = expectedModCount;
            if (tab.length >= hi && (i = index) >= 0 &&
                (i < (index = hi) || current != null)) {
                WeakHashMap.Entry<K,V> p = current;
                current = null; // exhaust
                do {
                    if (p == null)
                        p = tab[i++];
                    else {
                        Object x = p.get();
                        V v = p.value;
                        p = p.next;
                        if (x != null) {
                            @SuppressWarnings("unchecked") K k =
                                (K) WeakHashMap.unmaskNull(x);
                            action.accept
                                (new AbstractMap.SimpleImmutableEntry<K,V>(k, v));
                        }
                    }
                } while (p != null || i < hi);
            }
            if (m.modCount != mc)
                throw new ConcurrentModificationException();
        }

        public boolean tryAdvance(Consumer<? super Map.Entry<K,V>> action) {
            int hi;
            if (action == null)
                throw new NullPointerException();
            WeakHashMap.Entry<K,V>[] tab = map.table;
            if (tab.length >= (hi = getFence()) && index >= 0) {
                while (current != null || index < hi) {
                    if (current == null)
                        current = tab[index++];
                    else {
                        Object x = current.get();
                        V v = current.value;
                        current = current.next;
                        if (x != null) {
                            @SuppressWarnings("unchecked") K k =
                                (K) WeakHashMap.unmaskNull(x);
                            action.accept
                                (new AbstractMap.SimpleImmutableEntry<K,V>(k, v));
                            if (map.modCount != expectedModCount)
                                throw new ConcurrentModificationException();
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        public int characteristics() {
            return Spliterator.DISTINCT;
        }
    }

}
