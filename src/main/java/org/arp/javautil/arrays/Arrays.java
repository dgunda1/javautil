package org.arp.javautil.arrays;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Utilities for arrays.
 * 
 * @author Andrew Post
 * 
 */
public final class Arrays {
	/**
	 * Private constructor.
	 */
	private Arrays() {
	}

	/**
	 * Return a string which is the concatenation of calling to
	 * <code>toString()</code> method on each of a given array of
	 * <code>Object</code>s.
	 * 
	 * @param a
	 *            an Object[] array. If <code>null</code>, an empty
	 *            <code>String</code> will be returned.
	 * @param separator
	 *            the separator between elements. If <code>null</code>,
	 *            <code>""</code> is used.
	 * @return a <code>String</code>.
	 */
	public static String join(Object[] a, String separator) {
		if (separator == null) {
			separator = "";
		}

		if (a == null) {
			return "";
		} else {
			StringBuilder buf = new StringBuilder(a.length * 4);
			for (int i = 0; i < a.length; i++) {
				Object next = a[i];
				if (next != null) {
					buf.append(next);
				}
				if (i < a.length - 1) {
					buf.append(separator);
				}
			}
			return buf.toString();
		}
	}

	/**
	 * Return a string which is the concatenation of an array of
	 * <code>double</code>s.
	 * 
	 * @param a
	 *            a double[] array. If <code>null</code>, an empty
	 *            <code>String</code> will be returned.
	 * @param separator
	 *            the separator between elements. If <code>null</code>,
	 *            <code>""</code> is used.
	 * @return a <code>String</code>.
	 */
	public static String join(double[] a, String separator) {
		if (separator == null) {
			separator = "";
		}

		if (a == null) {
			return "";
		} else {
			StringBuilder buf = new StringBuilder(a.length * 4);
			for (int i = 0; i < a.length; i++) {
				buf.append(a[i]);
				if (i < a.length - 1) {
					buf.append(separator);
				}
			}
			return buf.toString();
		}
	}

	/**
	 * Formats an array like <code>java.util.Collection</code>s do in their
	 * <code>toString()</code> methods.
	 * 
	 * @param a
	 *            an array of <code>Object</code>s.
	 * @return a <code>String</code>. Returns <code>"null"</code> if the
	 *         argument is <code>null</code>.
	 */
	public static String format(Object[] a) {
		if (a == null) {
			return "null";
		} else {
			return "[" + join(a, ", ") + "]";
		}
	}

	/**
	 * Copies a 2D array. Nothing happens if either argument is
	 * <code>null</code>.
	 * 
	 * @param src
	 *            an array.
	 * @param dest
	 *            an array.
	 */
	public static void matrixCopy(Object[][] src, Object[][] dest) {
		if (src != null && dest != null) {
			for (int i = 0; i < src.length; i++) {
				System.arraycopy(src[i], 0, dest[i], 0, src[i].length);
			}
		}
	}

	/**
	 * Fills a 2D array with a value. Nothing happens if <code>matrix</code>
	 * is <code>null</code>. Likewise, nothing happens to a <code>null</code>
	 * row.
	 * 
	 * @param matrix
	 *            a 2D <code>Object[]</code> array.
	 * @param val
	 *            an <code>Object</code> value.
	 */
	public static void matrixFill(Object[][] matrix, Object val) {
		if (matrix != null) {
			for (int i = 0, n = matrix.length; i < n; i++) {
				if (matrix[i] != null) {
					for (int j = 0, m = matrix[i].length; j < m; j++) {
						matrix[i][j] = val;
					}
				}
			}
		}
	}

	/**
	 * Returns where an object is in a given array.
	 * 
	 * @param arr
	 *            the <code>Object[]</code> to search.
	 * @param obj
	 *            the <code>Object</code> to search for.
	 * @return <code>true</code> of <code>obj</code> is in <code>arr</code>,
	 *         <code>false</code> otherwise. Returns <code>false</code> if
	 *         <code>arr</code> is <code>null</code>.
	 */
	public static boolean contains(Object[] arr, Object obj) {
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				Object arri = arr[i];
				if (arri == obj || (arri != null && arri.equals(obj))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns a Set (specfically, a HashSet) with all the unique elements 
	 * from the given array of String objects.
	 * @param objs An array of String objects.
	 * @return A Set object containing all the unique elements from the array. 
	 */
    public static Set<String> asSet(String[] objs) {
        Set<String> objSet = null;
        if (objs != null) {
            objSet = new HashSet<String>();
            for (String obj : objs) {
                objSet.add(obj);
            }
        }
        return objSet;
    }
    
    /**
     * Adds the contents of one or more arrays to a collection.
     *
     * @param collection a {@link Collection}. Cannot be <code>null</code>.
     * @param arr zero or more {@link Object[]}.
     */
    public static <E> void addAll(Collection<E> collection, E[]... arr) {
        if (collection == null)
            throw new IllegalArgumentException("collection cannot be null");
        for (E[] oarr : arr) {
            for (E o : oarr) {
                collection.add(o);
            }
        }
    }

    /**
     * Returns a newly created random access list with the contents of the
     * provided arrays.
     *
     * @param <E> any class.
     * @param arrs an {@link E[]}.
     * @return a newly created random access {@link List<E>}.
     */
    public static <E> List<E> asList(E[]... arrs) {
        int size = 0;
        for (E[] arr : arrs) {
            if (arr == null)
                throw new IllegalArgumentException("Null arrays not allowed");
            size += arr.length;
        }
        List<E> result = new ArrayList<E>(size);
        addAll(result, arrs);
        return result;
    }
}
