package adt.skipList;

public class SkipListImpl<T> implements SkipList<T>
{

    protected SkipListNode<T> root;
    protected SkipListNode<T> NIL;

    protected int maxHeight;

    protected double PROBABILITY = 0.5;

    public SkipListImpl(int maxHeight)
    {
        this.maxHeight = maxHeight;
        root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
        NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
        connectRootToNil();
    }

    /**
     * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
     * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
     * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
     * metodo deve conectar apenas o forward[0].
     */
    private void connectRootToNil()
    {
        for (int i = 0; i < maxHeight; i++)
        {
            root.forward[i] = NIL;
        }
    }


    @Override
    public void insert(int key, T newValue, int height)
    {
        if(newValue != null)
        {
            SkipListNode[] update = new SkipListNode[maxHeight];
            SkipListNode<T> aux = this.root;

            for (int i = maxHeight - 1; i >= 0; i--)
            {
                while (aux.forward[i].key < key) aux = aux.forward[i];
                update[i] = aux;
            }

            aux = aux.forward[0];

            if (aux.key == key) aux.value = newValue;

            else
            {
                if (height > maxHeight) throw new RuntimeException();

                aux = new SkipListNode(key, height, newValue);
                for (int i = 0; i <= height - 1; i++)
                {
                    aux.forward[i] = update[i].forward[i];
                    update[i].forward[i] = aux;
                }
            }
        }
    }

    @Override
    public void remove(int key)
    {
        SkipListNode<T>[] update = new SkipListNode[maxHeight];
        SkipListNode aux = this.root;

        for(int i = maxHeight-1; i>=0; i--)
        {
            while(aux.forward[i].key < key) aux = aux.forward[i];
            update[i] = aux;
        }
        aux = aux.forward[0];

        if(aux.key == key)
        {
            for(int i = 0; i<=maxHeight-1; i++)
            {
                if(update[i].forward[i].equals(aux)) update[i].forward[i] = aux.forward[i];
            }
        }

    }

    @Override
    public int height()
    {
        if(this.root.forward.equals(NIL)) return 0;

        SkipListNode<T> aux = this.root;

        while(aux.forward[maxHeight-1] != NIL) aux = aux.forward[maxHeight-1];

        return aux.height();
    }

    @Override
    public SkipListNode<T> search(int key)
    {
        SkipListNode<T> aux = this.root;
        for(int i = maxHeight-1; i>=0; i--)
        {
            while (aux.forward[i].key < key) aux = aux.forward[i];
        }

        if(aux.getForward(0).key == key) return aux.getForward(0);
        return null;
    }

    @Override
    public int size()
    {
        int size = 0;
        SkipListNode<T> aux = root;
        while (aux != null && aux.getForward(0).getKey() != Integer.MAX_VALUE)
        {
            if(aux.getKey() != Integer.MAX_VALUE) size += 1;
            aux = aux.getForward(0);
        }
        return size;
    }

    @Override
    public SkipListNode<T>[] toArray()
    {
        SkipListNode<T>[] array = new SkipListNode[size() + 2];
        SkipListNode<T> aux = root;
        int cont = 0;
        while (aux != null)
        {
            array[cont++] = aux;
            aux = aux.getForward(0);
        }
        return array;
    }

}
