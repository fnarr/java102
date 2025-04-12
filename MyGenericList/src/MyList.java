import javax.swing.*;

public class MyList<T> {
    private T[] array;
    private int size;

    // Varsayılan Kapasite
    private static final int DEFAULT_CAPACITY = 10;

    public MyList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public MyList(int capacity){
        this.array = (T[]) new Object[capacity];
        this.size = 0;
    }

    public int size(){
        return size;
    }

    public int getCapacity(){
        return array.length;
    }

    public void add(T data){
        if (size == array.length){
            resize(array.length * 2);
        }
        array[size++] = data;
    }

    private void resize(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++){
            newArray[i] = array[i];
        }
        array = newArray;
    }

    public T get(int index){
        if (index < 0 || index >= size) return null;
        return array[index];
    }

    public T remove(int index){
        if (index < 0 || index >= size) return null;
        T removed = array[index];
        for (int i = index; i < size - 1; i++){
            array[i] = array[i + 1];
        }
        array[--size] = null;
        return removed;
    }

    public T set(int index, T data){
        if (index < 0 || index >= size)return null;
        T old = array[index];
        array[index] = data;
        return old;
    }

    public String toString(){
        if (size == 0) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++){
            sb.append(array[i]).append(", ");
        }
        sb.append(array[size - 1 ]).append("]");
        return sb.toString();
    }

    public int indexOf(T data){
        for (int i = 0; i < size; i++){
            if (array[i].equals(data)) return i;
        }
        return -1;
    }

    public int lastIndexOf(T data){
        for (int i = size - 1; i >= 0; i--){
            if (array[i].equals(data)) return i;
        }
        return -1;
    }

    public  boolean isEmpty(){
        return size == 0;
    }

    public T[] toArray(){
        T[] result = (T[]) new Object[size];
        for (int i = 0; i < size; i++){
            result[i] = array[i];
        }
        return result;
    }

    public void clear(){
        for (int i = 0; i < size; i++){
            array[i] = null;
        }
        size = 0;
    }

    public MyList<T> subList(int start, int finish) {
        if (start < 0 || finish > size || start > finish) return null;
        MyList<T> sublist = new MyList<>(finish - start);
        for (int i = start; i < finish; i++) {
            sublist.add(array[i]);
        }
        return sublist;
    }

    public boolean contains(T data) {
        return indexOf(data) != -1;
    }

}
