/**
 * AList Class.
 * Consists of methods and
 * operations to perform
 * on a custom AList.
 */
public class AList<Student>
{
    int size;
    private Student[] array;

    /**
     * Default constructor.
     */
    public AList()
    {
        array = (Student[]) new Object[10];
        size = 0;
    }

    /**
     * add method.
     *
     * @param index index to add at.
     * @param element element to add.
     */
    public void add(int index, Student element)
    {
        if (index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException();
        }
        add(element);
        for (int i=size-1; i>index; i--)
        {
            array[i] = array[i-1];
        }
        array[index] = element;
    }

    /**
     * get method.
     *
     * @param index index to get.
     * @return element at index.
     */
    public Student get(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    /**
     * @param obj  object to remove.
     * @return status of removal.
     */
    public boolean remove(Object obj)
    {
        int index = indexOf(obj);
        if (index == -1)
        {
            return false;
        }
        remove(index);
        return true;
    }

    /**
     * set method.
     *
     * @param index index to replace.
     * @param element element to replace.
     * @return Old student at index.
     */
    public Student set(int index, Student element)
    {
        Student old = get(index);
        array[index] = element;
        return old;
    }

    /**
     * add method.
     *
     * @param element element to add.
     * @return status of adding.
     */
    public boolean add(Student element)
    {
        if (size >= array.length)
        {
            Student[] bigger = (Student[]) new Object[array.length * 2];
            System.arraycopy(array, 0, bigger, 0, array.length);
            array = bigger;
        }
        array[size] = element;
        size++;
        return true;
    }

    /**
     * remove method.
     *
     * @param index position to remove.
     * @return Student at index.
     */
    public Student remove(int index)
    {
        Student element = get(index);
        for (int i=index; i<size-1; i++)
        {
            array[i] = array[i+1];
        }
        size--;
        return element;
    }

    /**
     * Checks equality of two objects.
     *
     * @param target Target object.
     * @param element Element object.
     * @return boolean value of equality.
     */
    private boolean equals(Object target, Object element)
    {
        if (target == null)
        {
            return element == null;
        }
        else
        {
            return target.equals(element);
        }
    }

    /**
     * indexOf emthod.
     *
     * @param target Object to get.
     * @return index of target.
     */
    public int indexOf(Object target)
    {
        for (int i = 0; i<size; i++)
        {
            if (equals(target, array[i]))
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * isEmpty method
     *
     * @return boolean value of empty status.
     */
    public boolean isEmpty()
    {
        return size == 0;
    }

    /**
     * size method
     *
     * @return size.
     */
    public int size()
    {
        return size;
    }

    /**
     * clear method
     */
    public void clear()
    {
        size = 0;
    }
}