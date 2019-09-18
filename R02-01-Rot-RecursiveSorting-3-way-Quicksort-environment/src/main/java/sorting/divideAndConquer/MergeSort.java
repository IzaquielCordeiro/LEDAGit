package sorting.divideAndConquer;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T>
{

    private void merge(T[] array, int leftIndex, int MidleIndex, int rightIndex)
    {
        T[] aux = Arrays.copyOf(array, array.length);

        int i = leftIndex;
        int j = MidleIndex + 1;
        int k = leftIndex;

        while (i <= MidleIndex && j <= rightIndex)
        {
            if (aux[i].compareTo(aux[j]) <= 0)
            {
                array[k] = aux[i];
                i++;
            }
            else
            {
                array[k] = aux[j];
                j++;
            }
            k++;
        }

        while (i <= MidleIndex)
        {
            array[k] = aux[i];
            i++;
            k++;
        }
        while (j <= rightIndex)
        {
            array[k] = aux[j];
            j++;
            k++;
        }
    }

    @Override
    public void sort(T[] array, int leftIndex, int rightIndex)
    {
        if (leftIndex >= 0 && leftIndex < rightIndex && rightIndex < array.length)
        {

            int MidleIndex = (leftIndex + rightIndex) / 2;

            if (leftIndex != rightIndex)
            {
                this.sort(array, leftIndex, MidleIndex);
                this.sort(array, MidleIndex + 1, rightIndex);
            }

            this.merge(array, leftIndex, MidleIndex, rightIndex);
        }
    }
}