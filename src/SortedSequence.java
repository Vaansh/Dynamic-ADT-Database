/**
 * SortedList class with
 * required methods.
 */
public class SortedSequence {
    AList<Student> student;

    /**
     * Default constructor.
     */
    public SortedSequence() {
        student = new AList<Student>();
    }

    /**
     * allKeys method.
     *
     * @return AList of integer keys.
     */
    public AList<Integer> allKeys() {
        AList<Integer> all = new AList<Integer>();
        for (int i = 0; i < student.size(); i++) {
            all.add(student.get(i).getKey());
        }
        return all;
    }

    /**
     * add method.
     *
     * @param k key.
     * @param v value.
     * @return boolean status of addition.
     */
    public boolean add(int k, String v) {
        if (binarySearch(student, 0, student.size() - 1, k) != -1) {
            return false;
        }
        Student s = new Student(k, v);
        int j = size();
        if (j == 0) {
            student.add(s);
        } else {
            while (j > 0) {
                if (k > student.get(j - 1).getKey()) {
                    break;
                }
                j--;
            }
            student.add(j, s);
            return true;
        }
        return false;
    }

    /**
     * remove method.
     *
     * @param key key to remove.
     * @return boolean status of removal.
     */
    public boolean remove(int key) {
        int ind = binarySearch(student, 0, student.size() - 1, key);
        if (ind == -1) {
            return false;
        } else {
            student.remove(ind);
            return true;
        }
    }

    /**
     * getValues method.
     *
     * @param k key.
     * @return String value, if exists.
     */
    public String getValues(int k) {
        int keyIndex = binarySearch(student, 0, student.size() - 1, k);
        if (keyIndex == -1) {
            return "No such record exists.";
        }
        return student.get(keyIndex).getValue();
    }

    /**
     * nextKey method.
     *
     * @param k key.
     * @return next key.
     */
    public int nextKey(int k) {
        int keyIndex = binarySearch(student, 0, student.size() - 1, k);
        if (keyIndex == -1 || ++keyIndex == student.size()) {
            return -1;
        }
        return student.get(keyIndex).getKey();
    }

    /**
     * prevKey method.
     *
     * @param k key.
     * @return previous key.
     */
    public int prevKey(int k) {
        int keyIndex = binarySearch(student, 0, student.size() - 1, k);
        if (keyIndex == -1 || keyIndex == 0) {
            return -1;
        }
        return student.get(--keyIndex).getKey();
    }

    /**
     * rangeKey method.
     *
     * @param k1 key 1.
     * @param k2 key 2.
     * @return number of keys between k1 and k2.
     */
    public int rangeKey(int k1, int k2) {
        int range = -1;
        int ink1 = binarySearch(student, 0, student.size() - 1, k1);
        int ink2 = binarySearch(student, 0, student.size() - 1, k2);
        if (ink1 < ink2) {
            while (ink1 < ink2) {
                range += 1;
                ink1 += 1;
            }
        }
        return range;
    }

    /**
     * binarySearch method.
     *
     * @param a     AList.
     * @param first min.
     * @param last  max.
     * @param k     key.
     * @return integer value of index, else -1.
     */

    public int binarySearch(AList<Student> a, int first, int last, int k) {
        if (last >= first) {
            int middle = first + (last - first) / 2;
            if (a.get(middle).getKey() < k) {
                return binarySearch(a, first + 1, last, k);
            } else if (a.get(middle).getKey() > k) {
                return binarySearch(a, first, last - 1, k);
            } else {
                return middle;
            }
        }
        return -1;
    }

    /**
     * findPosition method.
     *
     * @param a AList.
     * @param k key.
     * @return integer position.
     */
    public int findPosition(AList<Student> a, int k) {
        int position = a.size() - 1;
        while (position > 0 && k < a.get(position - 1).getKey()) {
            a.set(position, a.get(position - 1));
            position--;
        }
        return position;
    }

    /**
     * clear method.
     */
    public void clear() {
        student = new AList<Student>();
    }

    /**
     * size method.
     *
     * @return size.
     */
    public int size() {
        return student.size();
    }

    /**
     * isEmpty method.
     *
     * @return boolean value of empty status.
     */
    public boolean isEmpty() {
        return student == null || student.size() == 0;
    }

    /**
     * String form of information.
     */
    public String toString() {
        return student.toString();
    }
}
