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
        return this.head.isNIL();
    }

    @Override
    public int size()
    {
        SingleLinkedListNode<T> auxHead = head;
        int cont = 0;
        while(!auxHead.isNIL())
        {
            cont++;
            auxHead = auxHead.next;
        }
        return cont;
    }

    @Override
    public T search(T element)
    {
        T e = null;

        if(this.head.data.equals(element)) e = element;

        SingleLinkedListNode<T> auxHead = head;

        while(auxHead.data != element && !auxHead.next.isNIL())
        {
            auxHead = auxHead.next;
        }

        return e;
    }

    @Override
    public void insert(T element)
    {
        if(element != null)
        {
            SingleLinkedListNode<T> auxHead = head;

            if(auxHead.isNIL())
            {
                auxHead.data = element;
                auxHead.next = new SingleLinkedListNode<T>();
            }
            else auxHead = auxHead.next;
        }
    }

    @Override
    public void remove(T element)
    {
        SingleLinkedListNode<T> auxHead = head;
        if(auxHead.data.equals(element))
        {
            auxHead.data = auxHead.next.data;
            auxHead.next = auxHead.next.next;
        }
    }

    @Override
    public T[] toArray()
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not implemented!");
    }

    public SingleLinkedListNode<T> getHead()
    {
        return head;
    }

    public void setHead(SingleLinkedListNode<T> head)
    {
        this.head = head;
    }

}
