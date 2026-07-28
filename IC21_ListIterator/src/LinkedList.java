public class LinkedList<E> {
    private Node mHead;

    public long size() {
        long count = 0;

        //start at head
        Node temp = mHead;

        //Loop through all nodes
        while(temp != null) {
            count++;
            temp = temp.mNext;
        }

        return count;
    }

    public boolean add(long index, E element) {
        if(index < 0 || index > size())
            throw new IndexOutOfBoundsException("Index must be between 0 and " + size());

        //If mHead is being updated
        if(index == 0)
            mHead = new Node(element, mHead);
        else {
        Node temp = mHead;

        //Find where to insert new node
        for(long i = 0; i < index - 1; i++)
            temp = temp.mNext;

        //Create new node and update node pointers
        temp.mNext = new Node(element, temp.mNext);
        }
        return true;
    }

    public boolean add(E element) {
        return add(size(), element);
    }

    public void addFirst(E element) {
        add(0, element);
    }

    public void addLast(E element) {
        add(size(), element);
    }

    public E get(long index) {
        //Check index
        if(index < 0 || index >= size())
            throw new IndexOutOfBoundsException("Index must be between 0 and " + (size() - 1));

        Node temp = mHead;

        //Find node to return
        for(long i = 0; i < index; i++)
            temp = temp.mNext;

        return temp.mData;
    }

    public E set(long index, E element) {
        //Check index
        if(index < 0 || index >= size())
            throw new IndexOutOfBoundsException("Index must be between 0 and " + (size() - 1));
            
        Node temp = mHead;

        //Find node to update
        for(long i = 0; i < index; i++)
            temp = temp.mNext;
        
        //Update node and save previous data
        E oldData = temp.mData;
        temp.mData = element;

        return oldData;
    }

    public long indexOf(Object element) {
        long index = 0;

        Node temp = mHead;

        //While there are more nodes
        while(temp != null) {
            if(temp.mData.equals(element))
                return index;
            
            //Update node and index
            temp = temp.mNext;
            index++;
        }

        //If no match was found return -1
        return -1;
    }

    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    public E remove(long index) {
    //Check index
    if(index < 0 || index >= size())
        throw new IndexOutOfBoundsException("Index must be between 0 and " + (size() - 1));
    
    E oldData = null;

    //If removing anything but the head
    if(index != 0) {
    Node temp = mHead;

    //Find node to remove
    for(long i = 0; i < index - 1; i++)
        temp = temp.mNext;

    //Update Node pointer and store removed data
    oldData = temp.mNext.mData;
    //If removing the last node
    if(index == size() - 1)
        temp.mNext = null;
    else    //If node is not the last
        temp.mNext = temp.mNext.mNext;
    } 
    //If removing the head
    else {
        //Update pointer and store the old head's data
        oldData = mHead.mData;
        mHead = mHead.mNext;
    }

    return oldData;
    }

    public boolean remove(E element) {
        long index = indexOf(element);

        //If index is not -1 remove that index
        if(index != -1) {
            remove(index);
            return true;
        } else {
            return false;
        }
    }

    public void clear() {
        mHead = null;
    }

    @Override
    public String toString() {
        String output = "LinkedList [";
        
        Node temp = mHead;

        if(mHead != null) {
            output += mHead.mData;
            temp = mHead.mNext;
        }

        //Add each node to the output
        while(temp != null) {
            output += ", " + temp.mData;
            temp = temp.mNext;
        }

        return output + "]";
    }

    public class ListIterator {
        private Node mPrev, mNext;
        private long mCursor;

        public ListIterator() {
            mPrev = null;
            mCursor = 0;
            mNext = mHead;
        }

        public boolean hasNext() {
            return mNext != null;
        }

        public boolean hasPrevious() {
            return mPrev != null;
        }

        public E next() {
            mPrev = mNext;
            mNext = mNext.mNext;
            mCursor++;

            return mPrev.mData;
        }

        public E previous() {
            mNext = mPrev;
            mCursor--;

            //If at the head there is no previous
            if(mCursor == 0)
                mPrev = null;
            else {
                //Find previous node
                Node temp = mHead;
                for (int i = 1; i < mCursor; i++) {
                    temp = temp.mNext;
                }
                mPrev = temp;
            }

            return mNext.mData;
        }

        public long nextIndex() {
            return mCursor + 1;
        }

        public long previousIndex() {
            return mCursor - 1;
        }

        public void add(E element) {
            LinkedList.this.add(mCursor, element);
        }

        public void remove() {
            LinkedList.this.remove(mCursor);
        }

        public E set(E e) {
            return LinkedList.this.set(mCursor, e);
        }
    }

    private class Node {
        private E mData;
        private Node mNext;

        Node(E data, Node next) {
            mData = data;
            mNext = next;
        }

        Node(E data) {
            this(data, null);
        }
    }
}