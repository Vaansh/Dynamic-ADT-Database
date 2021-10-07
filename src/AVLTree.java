/**
 * AVLTree class with
 * required methods.
 */
public class AVLTree {
    public Node root;
    public int nodeCount = 0;

    /**
     * allKeys method.
     *
     * @return AList of integer keys.
     */
    public AList<Integer> allKeys() {
        AList<Integer> all = new AList<Integer>();
        addAllKeys(all, root);
        return all;
    }

    /**
     * addAllKeys method.
     *
     * @param a    AList.
     * @param node Node.
     * @return AList value of integers.
     */
    public AList<Integer> addAllKeys(AList<Integer> a, Node node) {
        if (node == null) {
            return a;
        } else {
            addAllKeys(a, node.left);
            a.add(node.value.getKey());
            addAllKeys(a, node.right);
        }
        return null;
    }

    /**
     * add method.
     *
     * @param k key.
     * @param v value.
     * @return boolean status of addition.
     */
    public boolean add(int k, String v) {
        return add(new Student(k, v));
    }

    /**
     * add method.
     *
     * @param value Student value.
     * @return boolean status of addition.
     */
    public boolean add(Student value) {
        if (value == null) {
            return false;
        }
        if (!contains(root, value)) {
            root = add(root, value);
            nodeCount++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Helper to add emthod.
     *
     * @param node  Node.
     * @param value Value of student.
     * @return
     */
    private Node add(Node node, Student value) {
        if (node == null) {
            return new Node(value);
        }

        int position = value.compareTo(node.value);

        if (position < 0) {
            node.left = add(node.left, value);
        } else {
            node.right = add(node.right, value);
        }

        updateHBF(node);

        return balance(node);
    }

    /**
     * remove method.
     *
     * @param key key to remove.
     * @return boolean status of removal.
     */
    public boolean remove(int key) {
        return remove(new Student(key, ""));
    }

    /**
     * Helper to remove method.
     *
     * @param value Value of student.
     * @return boolean status of removal.
     */
    private boolean remove(Student value) {
        if (contains(root, value)) {
            root = remove(root, value);
        }
        return false;
    }

    /**
     * Helper to remove method.
     *
     * @param node  Node.
     * @param value Value of student.
     * @return boolean status of removal.
     */
    private Node remove(Node node, Student value) {
        if (node == null) {
            return null;
        }

        int position = value.compareTo(node.value);

        if (position < 0) {
            node.left = remove(node.left, value);
        } else if (position > 0) {
            node.right = remove(node.right, value);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                if (node.left.height > node.right.height) {
                    Student next = findMax(node.left);
                    node.value = next;
                    node.left = remove(node.left, next);
                } else {
                    Student next = findMin(node.right);
                    node.value = next;
                    node.right = remove(node.right, next);
                }
            }
        }
        updateHBF(node);
        return balance(node);
    }

    /**
     * getValues method.
     *
     * @param key key.
     * @return String value, if exists.
     */
    public String getValues(int key) {
        return getValues(root, key);
    }

    /**
     * Helper to getValues method.
     *
     * @param node Node.
     * @param key  key.
     * @return String value, if exists.
     */
    private String getValues(Node node, int key) {
        if (node.value.getKey() == key) {
            return node.value.getValue();
        } else {
            getValues(node.left, key);
            getValues(node.right, key);
        }
        return "Key doesn't exist in database.";
    }

    /**
     * nextKey method.
     *
     * @param key key.
     * @return next key.
     */
    public int nextKey(int key) {
        return nextKey(root, key);
    }

    /**
     * Helper to nextKey method.
     *
     * @param node Node.
     * @param key  key.
     * @return next key.
     */
    private int nextKey(Node node, int key) {
        if (node.value.getKey() == key) {
            if (node.right.value.getKey() != 0) {
                return node.right.value.getKey();
            } else {
                return -2;
            }
        } else {
            nextKey(node.left, key);
            nextKey(node.right, key);
        }
        return -1;
    }

    /**
     * prevKey method.
     *
     * @param key key.
     * @return previous key.
     */
    public int prevKey(int key) {
        return prevKey(root, key);
    }

    /**
     * Helper to prevKey method.
     *
     * @param node Node.
     * @param key  key.
     * @return previous key.
     */
    private int prevKey(Node node, int key) {
        if (node.left.value.getKey() == key || node.right.value.getKey() == key) {
            return node.value.getKey();
        } else {
            prevKey(node.left, key);
            prevKey(node.right, key);
        }
        return -1;
    }

    /**
     * rangeKey method.
     *
     * @param k1 key 1.
     * @param k2 key 2.
     * @return number of keys between k1 and k2.
     */
    public int rangeKey(int k1, int k2) {
        int range = 0;
        Node node1 = getNode(root, k1);
        Node node2 = getNode(root, k2);
        if (node1 == null || node2 == null) {
            return range;
        }
        AList<Integer> num = new AList<>();
        num = inRangeNodes(num, node1, node2);
        return num.size();
    }

    /**
     * inRangeNodes method
     * helper to rangeKey.
     *
     * @param a     AList.
     * @param node1 Node.
     * @param node2 Node.
     * @return number of keys between k1 and k2.
     */
    private AList<Integer> inRangeNodes(AList<Integer> a, Node node1, Node node2) {
        if (node1 == node2) {
            return a;
        } else {
            inRangeNodes(a, node1.left, node2);
            a.add(node1.value.getKey());
            inRangeNodes(a, node1.right, node2);
        }
        return null;
    }

    /**
     * getNode method helper
     * to inRangeNodes.
     *
     * @param node Node.
     * @param k    key.
     * @return Node at key.
     */
    private Node getNode(Node node, int k) {
        if (node.value.getKey() == k) {
            return node;
        } else {
            getNode(node.left, k);
            getNode(node.right, k);
        }
        return null;
    }

    /**
     * height method.
     *
     * @return integer value of height.
     */
    public int height() {
        if (root == null) {
            return 0;
        }
        return root.height;
    }

    /**
     * size method.
     *
     * @return size.
     */
    public int size() {
        return nodeCount;
    }

    /**
     * isEmpty method.
     *
     * @return boolean value of empty status.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * contains method.
     *
     * @param value Student.
     * @return status of existence in tree.
     */
    public boolean contains(Student value) {
        return contains(root, value);
    }

    /**
     * Helper to contains method.
     *
     * @param node  Node.
     * @param value Student.
     * @return status of existence in tree.
     */
    private boolean contains(Node node, Student value) {
        if (node == null) {
            return false;
        }

        int position = value.compareTo(node.value);

        if (position < 0) {
            return contains(node.left, value);
        }

        if (position > 0) {
            return contains(node.right, value);
        }

        return true;
    }

    /**
     * findMin method.
     *
     * @param node Node.
     * @return Min student.
     */
    private Student findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.value;
    }

    /**
     * findMax method.
     *
     * @param node Node.
     * @return Max student.
     */
    private Student findMax(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node.value;
    }

    /**
     * updateHBF.
     *
     * @param node Node to update balance factor.
     */
    private void updateHBF(Node node) {
        int left = (node.left == null) ? -1 : node.left.height;
        int right = (node.right == null) ? -1 : node.right.height;

        node.height = 1 + (left > right ? left : right);
        node.balance = right - left;
    }

    /**
     * balance tree.
     *
     * @param node Node.
     * @return Node.
     */
    private Node balance(Node node) {
        if (node.balance == -2) {
            if (node.left.balance <= 0) {
                return caseLeftLeft(node);
            } else {
                return caseLeftRight(node);
            }
        } else if (node.balance == 2) {
            if (node.right.balance >= 0) {
                return caseRightRight(node);
            } else {
                return caseRightLeft(node);
            }
        }
        return node;
    }

    /**
     * leftRotate method.
     *
     * @param node Node.
     * @return parent.
     */
    private Node leftRotate(Node node) {
        Node parent = node.right;
        node.right = parent.left;
        parent.left = node;
        updateHBF(node);
        updateHBF(parent);
        return parent;
    }

    /**
     * rightRotate method.
     *
     * @param node Node.
     * @return parent.
     */
    private Node rightRotate(Node node) {
        Node parent = node.left;
        node.left = parent.right;
        parent.right = node;
        updateHBF(node);
        updateHBF(parent);
        return parent;
    }

    /**
     * caseLeftLeft method.
     *
     * @param node Node.
     * @return Corrected node.
     */
    private Node caseLeftLeft(Node node) {
        return rightRotate(node);
    }

    /**
     * caseLeftRight method.
     *
     * @param node Node.
     * @return Corrected node.
     */
    private Node caseLeftRight(Node node) {
        node.left = leftRotate(node.left);
        return caseLeftLeft(node);
    }

    /**
     * caseRightRight method.
     *
     * @param node Node.
     * @return Corrected node.
     */
    private Node caseRightRight(Node node) {
        return leftRotate(node);
    }

    /**
     * caseRightLeft method.
     *
     * @param node Node.
     * @return Corrected node.
     */
    private Node caseRightLeft(Node node) {
        node.right = rightRotate(node.right);
        return caseRightRight(node);
    }

    /**
     * getNodeCount method.
     *
     * @return nodeCount
     */
    public int getNodeCount() {
        return nodeCount;
    }

    /**
     * Node class.
     */
    class Node {
        public int balance;
        public Student value;
        public int height;
        public Node left, right;

        /**
         * Parameterized constructor.
         *
         * @param value Student value.
         */
        public Node(Student value) {
            this.value = value;
        }

        /**
         * Accessor method.
         */
        public Student getValue() {
            return value;
        }

        /**
         * Accessor method.
         */
        public Node getLeft() {
            return left;
        }

        /**
         * Accessor method.
         */
        public Node getRight() {
            return right;
        }

        /**
         * String form of information.
         */
        public String toString() {
            return value.toString();
        }
    }
}