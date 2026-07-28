package src;

public class HoorayList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private int mCapacity, mSize;
    private E[] mData;

    public HoorayList() {
        this(DEFAULT_CAPACITY);
    }

    public HoorayList(int initialCapacity) {
        //Thow exception if initialCapacity < 1
        if(initialCapacity < 1)
            throw new IllegalArgumentException("Initial capacity must be above 0");
        
        mCapacity = initialCapacity;
        mSize = 0;
        mData = (E[]) new Object[mCapacity];
    }

    public E get(int index) {
        //If index is out of bounds throw exception
        if(index < 0 || index >= mSize)
            throw new IndexOutOfBoundsException("Index must be between 0 and < " + mSize);

        return mData[index];
    }

    public E set(int index, E element) {
        //If index is out of bounds throw exception
        if(index < 0 || index >= mSize)
            throw new IndexOutOfBoundsException("Index must be between 0 and < " + mSize);
        
        //Replace the selected
        E temp = mData[index];
        mData[index] = element;
        return temp;
    }

    public void ensureCapacity(int minCapacity) {
        E[] newData = (E[]) new Object[minCapacity];

        //Copy items to new array
        for(int i = 0; i < mSize; i++)
            newData[i] = mData[i];

        mData = newData; 
    }

    public int indexOf(E element) {
        //Check each item until match is found
        for(int i = 0; i < mSize; i++)
            if(mData[i].equals(element))
                return i;

        //If no match found
        return -1;
    }

    public boolean contains(E element) {
        return indexOf(element) >= 0;
    }

    public boolean add(int index, E element) {
        //If index is out of bounds throw exception
        if(index < 0 || index > mSize)
            throw new IndexOutOfBoundsException("Index must be between 0 and " + mSize);

        //Update capacity if needed
        if(mSize >= mCapacity)
            ensureCapacity(mCapacity * 2);
        
        //Shift over items to make room for new entry
        for(int i = mSize; i > index; i--)
            mData[i] = mData[i-1];

        //Add new entry to array
        mData[index] = element;
        mSize++;
        return true;
    }

    public boolean add(E element) {
        return add(mSize, element);
    }

    public E remove(int index) {
        //If index is out of bounds throw exception
        if(index < 0 || index > mSize)
            throw new IndexOutOfBoundsException("Index must be between 0 and " + mSize);
        
        //Get the item to be removed
        E item = mData[index];

        //Shift all subsequent items over and adjust mSize
        for(int i = index; i < mSize - 1; i++)
            mData[i] = mData[i + 1];
        mSize--;

        return item;
    }

    public boolean remove(E element) {
        //Find out whether element exists
        boolean exists = indexOf(element) >= 0;

        if(exists)
            remove(indexOf(element));

        return exists;
    }

    public void clear() {
        mSize = 0;
        mCapacity = 10;
        mData = (E[]) new Object[mSize];
    }

    public int size() {
        return mSize;
    }

    @Override
    public String toString() {
        String output = "[";
        
        //Append each item to output
        for(int i = 0; i < mSize; i++)
            output += mData[i].toString() + ", ";

        return output + "Hooray!]";
    }
}