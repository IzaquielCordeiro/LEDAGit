package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T>
{

    protected SingleLinkedListNode<T> head;

    public SingleLinkedListImpl()
    {
        this.head = new SingleLinkedListNode<T>();
    }

    @Override
    public boolean isEmpty()
    {
        return (head.isNIL());
    }

    @Override
    public int size()
    {
        int size = 0;

        SingleLinkedListNode<T> auxHead = head;

        while (!auxHead.isNIL())
        {
            auxHead = auxHead.getNext();
            size++;
        }

        return size;
    }

    @Override
    public T search(T element)
    {
        T e = null;
        if (element != null)
        {

            SingleLinkedListNode<T> auxHead = head;

            while (!auxHead.isNIL())
            {
                if (auxHead.getData().equals(element))
                {
                    e = auxHead.getData();
                }

                auxHead = auxHead.getNext();
            }
        }
        return e;
    }

    @Override
    public void insert(T element)
    {
        if (element != null)
        {
            SingleLinkedListNode<T> auxHead = head;

            while (!auxHead.isNIL())
            {
                auxHead = auxHead.getNext();
            }

            auxHead.setData(element);
            auxHead.setNext(new SingleLinkedListNode<T>());
        }
    }

    @Override
    public void remove(T element)
    {
        if (!isEmpty() && element != null)
        {
            SingleLinkedListNode<T> auxHead = head;

            if (head.getData().equals(element)) head = head.next;

            else
            {
                SingleLinkedListNode<T> previous = new SingleLinkedListNode<>();

                while (!auxHead.isNIL())
                {
                    if (auxHead.getData().equals(element))
                    {
                        previous.setNext(auxHead.getNext());
                        auxHead.setNext(new SingleLinkedListNode<T>());
                        break;
                    }

                    previous = auxHead;
                    auxHead = auxHead.getNext();
                }
            }
        }
    }

    @Override
    public T[] toArray()
    {
        int size = size();
        T[] toArray = (T[]) new Object[size];
        SingleLinkedListNode<T> auxHead = head;
        int index = 0;

        while (!auxHead.isNIL())
        {
            toArray[index] = auxHead.getData();
            auxHead = auxHead.getNext();
            index++;
        }
        return toArray;
    }

    public SingleLinkedListNode<T> getHead()
    {
        return this.head;
    }

    public void setHead(SingleLinkedListNode<T> head)
    {
        this.head = head;
    }

}