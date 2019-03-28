package sorting.simpleSorting;

import sorting.AbstractSorting;

import java.util.Arrays;

import static util.Util.swap;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T>
{

    @Override
    public void sort(T[] array, int leftIndex, int rightIndex)
    {
        for(int i=leftIndex; i<rightIndex; i++)
        {
            int isOrderedUntil = i;

            while (array[i + 1].compareTo(array[isOrderedUntil]) < 0 && isOrderedUntil != 0)
            {
                swap(array, i, isOrderedUntil);
                isOrderedUntil--;
                System.out.println(Arrays.toString(array));
            }
        }
    }
}
