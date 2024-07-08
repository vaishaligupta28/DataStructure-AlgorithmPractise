package org.example.arrays_and_lists.impls;
import java.util.Arrays;

/**
 *
 * Different Implementations of Resizable Arrays[Grows and Shrinks] in Java are:
 *
 * 1. ArrayList
 *  `   - Introduced in Java 1.2 in Java Collections Framework
 *      - No synchronized overhead i.e. Not Thread safe.
 *      - There are better alternatives with Collections.synchronizedList that ensure synchronize
 *                  only critical parts unlike Vector.
 *      - Faster than Vector
 *      - Better for single threaded applications
 *      - Easier to convert to other collections classes like LinkedList and HashSet.
 *
 * 2. Vector
 *      - Legacy in Java 1.0
 *      - Synchronized Overhead i.e. Thread safe.
 *      - Slower due to additional synchronization
 *      - Preferred for critical multithreaded applications
 *      - Not Easier to convert to other collections classes like LinkedList and HashSet.
 *      - Synchronizes each and every operation i.e. acquires/release lock for each operation
 *      while in reality generally we need to synchronise set of operations.
 *
 * 3. Synchronized Array List
 *      - Synchronizes basic operations like get,add,remove so no two threads
 *      can modify the same index.
 *      -  Iterating over a synchronized list still requires external synchronization
 *      -  If one thread is iterating over the list (using an Iterator, for example)
 *      while another thread modifies it,
 *      clients must manually synchronize access to avoid
 *      ConcurrentModificationException
 *      - Most used
 *
 *  - CopyOnWriteArrayList
 *      - All operations read write traverse are synchronised.
 *      - No explicit synchronization needed
 *
 *  Reference:
 *  Why not to use Vector class?
 *  <a href="https://javaconceptoftheday.com/not-use-vector-class-code/">...</a>
 * @param <T>
 */
// ResizableArray Class
public final class ResizableArrayImpl<T> {
    // Default capacity for the array
    private static final int DEFAULT_CAPACITY = 1;
    // Array to store elements
    private Object[] array;
    // Current size of the array
    private int size;

    // Constructor to initialize the array
    // with default capacity and size
    public ResizableArrayImpl() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    // method to add an element to the array
    public void add(T element) {
        System.out.println("<<<---Add new["+element+"]--->>>");
        // ensure capacity before adding an element
        ensureCapacity();
        // add the element to the array and increment the size
        array[size++] = element;
    }

    // method to remove an element at a specific index
    public void remove(int index) {
        System.out.println("<<<---Remove index["+index+"]--->>>");
        // check if the index is valid
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Array Index OutOfBound Exception occurred");
        }
        // Use arraycopy to shift elements
        // and remove the specified element
        System.arraycopy(array, index + 1, array, index, size - index - 1);

        // Set the last element to
        // null and decrement the size
        array[--size] = null;
        System.out.println("[After Deleting]ResizableArray: " + this);
    }

    public int capacity() {
        return array.length;
    }

    public void remove() {
        System.out.println("<<<---Remove last--->>>");
        // Set the last element to
        // null and decrement the size
        array[--size] = null;
        if(size > 0 && size == array.length/4) { // check if the size is one quarter of the array length
            int newCapacity = array.length / 2;// halve the capacity
            this.array = resize(newCapacity);
            System.out.println("[DEBUG]ResizableArray::newCapacity = " + capacity());
        }
        System.out.println("[After Deleting]ResizableArray: " + this);
    }

    // Method to get the current size of the array
    public int size() {
        return size;
    }

    // Private method to ensure that
    // the array has enough capacity
    private void ensureCapacity() {
        if (size == array.length) { // check if array is full
            int newCapacity = array.length * 2;// double the capacity

            // Can use Arrays.copyOf to create a
            // new array with the increased capacity
            this.array = resize(newCapacity);
            System.out.println("[DEBUG]ResizableArray::newCapacity = " + capacity());
        }
    }

    private Object[] resize(int newCapacity) {
        Object[] newArray = new Object[newCapacity];
        for(int i=0; i<size;i++) {
            newArray[i] = this.array[i];
        }
        return newArray;
    }

    // Override toString method to
    // display the contents of the array
    @Override
    public String toString() {
        // Use Arrays.toString to create
        // a string representation of the array
        return Arrays.toString(Arrays.copyOf(array, size));
    }

    public static void main(String[] args) {
        ResizableArrayImpl<Integer> dynamicArray = new ResizableArrayImpl<>();
        dynamicArray.add(100);
        dynamicArray.add(150);
        dynamicArray.add(200);
        dynamicArray.add(200);
        dynamicArray.add(500);

        System.out.println("ResizableArray after adding elements: " + dynamicArray);
        System.out.println("Number of elements: " + dynamicArray.size());

        dynamicArray.remove();
        dynamicArray.remove();
        dynamicArray.remove(1);
        System.out.println("Number of elements: " + dynamicArray.size());
    }
}
