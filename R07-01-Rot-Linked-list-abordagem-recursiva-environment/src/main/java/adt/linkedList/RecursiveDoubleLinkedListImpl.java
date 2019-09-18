package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T>
{

    protected RecursiveDoubleLinkedListImpl<T> previous;

    public RecursiveDoubleLinkedListImpl() {}

    @Override
    public void insert(T element)
    {
        if (element != null)
        {
            if(this.isEmpty()) this.insertFirst(element);

            else if (this.getNext() != null && this.getNext().isEmpty())
            {
                this.next = new RecursiveDoubleLinkedListImpl<T>();

                ((RecursiveDoubleLinkedListImpl<T>) this.next).setPrevious(this);
                this.next.setData(element);
                this.next.setNext(new RecursiveDoubleLinkedListImpl<T>());
            }

            else this.getNext().insert(element);
        }
    }

    @Override
    public void insertFirst(T element)
    {
        RecursiveDoubleLinkedListImpl<T> list = this;

        this.setPrevious(new RecursiveDoubleLinkedListImpl<T>());
        this.setData(this.next.data);
        this.setNext(list);

        list.setPrevious(this);
    }

    @Override
    public void remove(T element)
    {

    }

    @Override
    public void removeFirst()
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public void removeLast()
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    public RecursiveDoubleLinkedListImpl<T> getPrevious()
    {
        return previous;
    }

    public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous)
    {
        this.previous = previous;
    }

}
