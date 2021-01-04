/**
 * Student Class.
 * Consists of methods and
 * data to store.
 */
public class Student
{
    private int key;
    private String value;

    /**
     * Default constructor.
     * Initializes values of all the variables.
     */
    public Student()
    {
        key = -1;
        value = "";
    }

    /**
     * Parameterized constructor.
     * Sets values of all the variables.
     * @param k Key.
     * @param v Value.
     */
    public Student(int k, String v)
    {
        key = k;
        value = v;
    }

    /**
     * Accessor method.
     *
     * @return key.
     */
    public int getKey()
    {
        return key;
    }

    /**
     * Accessor method.
     *
     * @return value value.
     */
    public String getValue()
    {
        return value;
    }

    /**
     * String form of information.
     */
    public String toString()
    {
        return "Key: " + key + " Value: " + value + "\n";
    }

    /**
     * compareTo method.
     *
     * @param student Student to compare with.
     * @return Numerical value of student comparison.
     */
    public int compareTo(Student student) {
        if(getKey() > student.getKey())
        {
            return 1;
        }
        else if (getKey() < student.getKey())
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
}
