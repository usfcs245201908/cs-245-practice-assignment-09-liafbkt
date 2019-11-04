import java.util.Arrays;

public class BinaryHeap {
    protected final int DEFAULT_SIZE = 10;
    private int[] data = new int[DEFAULT_SIZE];
    private int size = 0;


    /**
     * add a priority to the queue, and swap if parent is greater than child
     *
     * @param priority
     */
    public void add(int priority) {
        if (size + 1 == data.length) {
            grow_array();
        }
        data[size++] = priority;
        int child = size - 1;
        int parent = (child - 1) / 2;
        while (parent >= 0 && data[parent] > data[child]) {
            swap(data, parent, child);
            child = parent;
            parent = (child - 1) / 2;
        }
    }

    /**
     * swap parent and child
     *
     * @param data   - the queue
     * @param parent
     * @param child
     */
    private void swap(int data[], int parent, int child) {
        int temp = data[child];
        data[child] = data[parent];
        data[parent] = temp;
    }

    /**
     * pop the first item in the queue, and rearrange the queue so all parents are smaller than their children
     *
     * @return the first item in the queue
     */
    public int remove() {
        int temp = data[0];
        data[0] = data[--size];
        siftDown(0);
        return temp;
    }

    /**
     * iterate through the queue and swap parent and child if the child is smaller than parent
     *
     * @param parent
     */
    private void siftDown(int parent) {
        int child = parent * 2 + 1; // left child
        if (child < size) {
            if ((child + 1) < size && data[child + 1] < data[child]) {
                child = child + 1; // right child
                if (data[parent] > data[child]) {
                    swap(data, child, parent);
                    siftDown(child);
                }
            }
            if (data[parent] > data[child]) {
                swap(data, child, parent);
                siftDown(child);
            }
            /*else if(child < size ){//&& (child + 1) < size){
            if (data[parent] > data[child]) {
                swap(data, child, parent);
                siftDown(child);
            }
        }*/
        }
    }

    private void grow_array() {
        data = Arrays.copyOfRange(data, 0, data.length * 2);
    }
}

