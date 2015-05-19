import java.util.*;

/**
 * Created by Stas on 19/05/2015 23:18.
 */

public class AssociationTable<K extends Comparable<K>, V>
{
    private ArrayList<KeyValuePair<K, V>> list;

    public AssociationTable()
    {
        list = new ArrayList<KeyValuePair<K, V>>();
    }

    public AssociationTable(K[] keys, V[] values) throws InvalidArgumentException
    {
        this();
        if (keys.length != values.length)
        {
            throw new InvalidArgumentException("Keys and values arrays must be with the same size");
        }

        for (int i = 0; i < keys.length; i++)
        {
            add(keys[i], values[i]);
        }
    }

    public void add(K key, V value)
    {
        ListIterator<KeyValuePair<K, V>> it = list.listIterator();
        int i = 0;
        while (it.hasNext() && key.compareTo(it.next().key) > 0)
        {
            i++;
        }

        if (i < size() && list.get(i).key.compareTo(key) == 0)
        {
            //override existing key
            list.get(i).value = value;
        }
        else
        {
            //add new key
            list.add(i, new KeyValuePair<K, V>(key, value));
        }
    }

    public V get(K key)
    {
        if (size() == 0)
        {
            return null;
        }

        ListIterator<KeyValuePair<K, V>> it = list.listIterator();
        int i = 0;
        while (it.hasNext() && key.compareTo(it.next().key) > 0)
        {
            i++;
        }

        if (i < size() && list.get(i).key.compareTo(key) == 0)
        {
            //override existing key
            return list.get(i).value;
        }
        return null;
    }

    public boolean contains(K key)
    {
        if (size() == 0)
        {
            return false;
        }

        ListIterator<KeyValuePair<K, V>> it = list.listIterator();
        int i = 0;
        while (it.hasNext() && key.compareTo(it.next().key) > 0)
        {
            i++;
        }

        if (i < size() && list.get(i).key.compareTo(key) == 0)
        {
            return true;
        }
        return false;
    }

    public boolean remove(K key)
    {
        if (size() == 0)
        {
            return false;
        }

        ListIterator<KeyValuePair<K, V>> it = list.listIterator();
        int i = 0;
        while (it.hasNext() && key.compareTo(it.next().key) > 0)
        {
            i++;
        }

        if (i < size() && list.get(i).key.compareTo(key) == 0)
        {
            list.remove(i);
            return true;
        }
        return false;
    }

    public int size()
    {
        return list.size();
    }

    public Iterator<K> keyIterator()
    {
        ArrayList<K> keyList = new ArrayList<K>(this.size());
        for (KeyValuePair<K, V> pair : this.list)
        {
            keyList.add(pair.key);
        }

        return keyList.listIterator();
    }
}
