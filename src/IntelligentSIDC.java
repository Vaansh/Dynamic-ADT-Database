import java.util.Random;

/**
 * IntelligentSIDC class.
 *
 * Holds methods to dynamically
 * determine appropriate datatype
 * and implement it.
 */
public class IntelligentSIDC
{
    private int size;
    SortedSequence StudentRecordsSequence;
    AVLTree StudentRecordsTree;

    /**
     * Default constructor.
     */
    public IntelligentSIDC()
    {
        size = 0;
        StudentRecordsSequence = new SortedSequence();
        StudentRecordsTree = new AVLTree();
    }

    /**
     * Parameterized constructor.
     *
     * @param s Size.
     */
    public IntelligentSIDC(int s)
    {
        SetSIDCThreshold(s);
        if(s<1000)
        {
            StudentRecordsSequence = new SortedSequence();

            while(s>0)
            {
                boolean added = false;
                while(!added)
                {
                    added = StudentRecordsSequence.add(generate(), size % 2 == 0 ? "Alan Turing" : "Nikola Tesla");
                }
                s--;
            }
        }
        else
        {
            StudentRecordsTree = new AVLTree();

            while(s>0) {
                Student temp = new Student(generate(), "Alan Turing");
                StudentRecordsTree.add(temp);
                s--;
            }
        }
    }

    /**
     * SetSIDCThreshold method.
     *
     * @param s size to set threshhold.
     */
    public void SetSIDCThreshold(int s)
    {
        size = s;
    }

    /**
     * generate. Creates a random number
     * generated between 10000000 amd
     * 100000000.
     *
     * @return Random 8-digit number.
     */
    public int generate()
    {
        int min = 10000000, max = 100000000;
        Random rand = new Random();
        int l = rand.nextInt(max - min) + min;
        return l;
    }

    /**
     * allKeys method.
     *
     * @return AList of integer keys.
     */
    public AList<Integer> allKeys()
    {
        if(size > 1000)
        {
            return StudentRecordsTree.allKeys();
        }
        else
        {
            return StudentRecordsSequence.allKeys();
        }
    }

    /**
     * add method.
     *
     * @param key key.
     * @param value value.
     * @return boolean status of addition.
     */
    public boolean add(int key,String value)
    {
        if(size > 1001)
        {
            if(StudentRecordsTree.add(key, value))
            {
                size += 1;
                return true;
            }
            return false;
        }
        else if(size == 1001)
        {
            size=0;
            for(int i = 0; i < 1001; i++)
            {
                size++;
                StudentRecordsTree.add(StudentRecordsSequence.student.get(i).getKey(), StudentRecordsSequence.student.get(i).getValue());
            }
            StudentRecordsSequence.clear();
            if(StudentRecordsTree.add(key, value))
            {
                size++;
                return true;
            }
            System.out.println(size);
            return false;
        }
        else
        {
            if(StudentRecordsSequence.add(key, value))
            {
                size += 1;;
                return true;
            }
            return false;
        }
    }

    /**
     * remove method.
     *
     * @param key key to remove.
     * @return boolean status of removal.
     */
    public boolean remove(int key)
    {
        if(size > 1000)
        {
            return StudentRecordsTree.remove(key);
        }
        else
        {
            return StudentRecordsSequence.remove(key);
        }
    }

    /**
     * getValues method.
     *
     * @param key key.
     * @return String value, if exists.
     */
    public String getValues(int key)
    {
        if(size > 1000)
        {
            return StudentRecordsTree.getValues(key);
        }
        else
        {
            return StudentRecordsSequence.getValues(key);
        }
    }

    /**
     * nextKey method.
     *
     * @param key key.
     * @return next key.
     */
    public int nextKey(int key)
    {
        if(size > 1000)
        {
            return StudentRecordsTree.nextKey(key);
        }
        else
        {
            return StudentRecordsSequence.nextKey(key);
        }
    }

    /**
     * prevKey method.
     *
     * @param key key.
     * @return previous key.
     */
    public int prevKey(int key)
    {
        if(size > 1000)
        {
            return StudentRecordsTree.prevKey(key);
        }
        else
        {
            return StudentRecordsSequence.prevKey(key);
        }
    }

    /**
     * rangeKey method.
     *
     * @param key1 key 1.
     * @param key2 key 2.
     * @return number of keys between k1 and k2.
     */
    public int rangeKey(int key1, int key2)
    {
        if(size > 1000)
        {
            return StudentRecordsTree.rangeKey(key1, key2);
        }
        else
        {
            return StudentRecordsSequence.rangeKey(key1, key2);
        }
    }

    /**
     * getSize method.
     *
     * @return size.
     */
    public int getSize()
    {
        return size;
    }

}
