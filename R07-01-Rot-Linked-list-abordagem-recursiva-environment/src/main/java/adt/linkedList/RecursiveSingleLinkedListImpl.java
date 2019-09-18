package adt.linkedList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T>
{

    protected T data;
    protected RecursiveSingleLinkedListImpl<T> next;


    public RecursiveSingleLinkedListImpl()
    {

    }


    @Override
    public boolean isEmpty()
    {
        return this.data == null;
    }

    @Override
    public int size()
    {
        int size = 0;
        if(!this.isEmpty()) size += 1+this.getNext().size();
        return size;
    }

    @Override
    public T search(T element)
    {
        T e = null;

        if(element != null)
        {
            if(this.getData().equals(element)) e = element;
            else if(!this.next.isEmpty()) e = this.getNext().search(element);
        }

        return e;
    }

    @Override
    public void insert(T element)
    {
        if(element!=null)
        {
            if(this.isEmpty())
            {
                this.setData(element);
                this.next = new RecursiveSingleLinkedListImpl<>();
            }

            else this.getNext().insert(element);
        }
    }

    @Override
    public void remove(T element)
    {
        if(this.getNext().isEmpty()) this.setData(null);

        else if(this.getNext().getData().equals(element))
        {
            this.setData(this.getNext().getData());
            this.setNext(this.getNext().getNext());
        }
    }

    @Override
    public T[] toArray()
    {
        List<T> toTArrayList = new ArrayList<T>();
        this.toToArray(toTArrayList);

        return (T[]) toTArrayList.toArray();
    }

    protected void toToArray(List<T> l)
    {
        if(!this.isEmpty())
        {
            l.add(this.getData());

            this.getNext().toToArray(l);
        }
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    public RecursiveSingleLinkedListImpl<T> getNext()
    {
        return next;
    }

    public void setNext(RecursiveSingleLinkedListImpl<T> next)
    {
        this.next = next;
    }

}
