package Sorting;

public abstract class Code
{
    public abstract void sort(int[] array, int leftIndex, int rightIndex);

    public void swap(int[] array, int i, int j)
    {
        int aux = array[j];

        array[i] = array[j];
        array[j] = aux;
    }
}
