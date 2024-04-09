import java.util.Vector;

/**
 * Implementación de una cola con prioridad basada en un heap (montículo) usando un Vector.
 * Esta estructura permite mantener elementos ordenados por prioridad, donde el elemento de menor
 * prioridad (según el orden natural de Comparable) se encuentra en la raíz del heap.
 *
 * @param <E> el tipo de elementos que se almacenan en la cola con prioridad, deben ser comparables.
 */
public class VectorHeap<E extends Comparable<E>> {
    private Vector<E> data;

    /**
     * Constructor para crear un VectorHeap vacío.
     */
    public VectorHeap() {
        data = new Vector<>();
    }

    /**
     * Constructor para crear un VectorHeap a partir de un vector existente.
     *
     * @param v el vector inicial que contiene los elementos para construir el heap.
     */
    public VectorHeap(Vector<E> v) {
        int i;
        data = new Vector<>(v.size());
        for (i = 0; i < v.size(); i++) {
            add(v.get(i));
        }
    }

    /**
     * Devuelve el índice del padre del nodo en la posición especificada.
     *
     * @param i el índice del nodo para encontrar su padre.
     * @return el índice del padre en el heap.
     */
    protected static int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * Devuelve el índice del hijo izquierdo del nodo en la posición especificada.
     *
     * @param i el índice del nodo para encontrar su hijo izquierdo.
     * @return el índice del hijo izquierdo en el heap.
     */
    protected static int left(int i) {
        return 2 * i + 1;
    }

    /**
     * Devuelve el índice del hijo derecho del nodo en la posición especificada.
     *
     * @param i el índice del nodo para encontrar su hijo derecho.
     * @return el índice del hijo derecho en el heap.
     */
    protected static int right(int i) {
        return (2 * i + 1) + 1;
    }

    /**
     * Realiza el proceso de percolación hacia arriba para mantener la propiedad del heap después de
     * agregar un nuevo elemento en la posición especificada.
     *
     * @param leaf el índice del nodo desde el que se realiza la percolación hacia arriba.
     */
    protected void percolateUp(int leaf) {
        int parent = parent(leaf);
        E value = data.get(leaf);
        while (leaf > 0 && (value.compareTo(data.get(parent)) < 0)) {
            data.set(leaf, data.get(parent));
            leaf = parent;
            parent = parent(leaf);
        }
        data.set(leaf, value);
    }

    /**
     * Realiza el proceso de hundimiento hacia abajo (push-down) para mantener la propiedad del heap
     * después de eliminar el elemento en la raíz del heap.
     *
     * @param root el índice del nodo raíz desde el que se realiza el hundimiento hacia abajo.
     */
    protected void pushDownRoot(int root) {
        int heapSize = data.size();
        E value = data.get(root);
        while (root < heapSize) {
            int childpos = left(root);
            if (childpos < heapSize) {
                if ((right(root) < heapSize) && ((data.get(childpos + 1)).compareTo(data.get(childpos)) < 0)) {
                    childpos++;
                }
                if ((data.get(childpos)).compareTo(value) < 0) {
                    data.set(root, data.get(childpos));
                    root = childpos;
                } else {
                    data.set(root, value);
                    return;
                }
            } else {
                data.set(root, value);
                return;
            }
        }
    }

    /**
     * Obtiene el primer elemento (elemento de menor prioridad) en la cola con prioridad.
     *
     * @return el primer elemento en la cola con prioridad.
     */
    public E getFirst() {
        return data.get(0);
    }

    /**
     * Elimina y devuelve el primer elemento (elemento de menor prioridad) en la cola con prioridad.
     *
     * @return el primer elemento eliminado de la cola con prioridad.
     */
    public E remove() {
        E minVal = getFirst();
        data.set(0, data.get(data.size() - 1));
        data.setSize(data.size() - 1);
        if (data.size() > 1) pushDownRoot(0);
        return minVal;
    }

    /**
     * Agrega un nuevo elemento a la cola con prioridad manteniendo el orden del heap.
     *
     * @param value el elemento a agregar a la cola con prioridad.
     */
    public void add(E value) {
        data.add(value);
        percolateUp(data.size() - 1);
    }

    /**
     * Devuelve el número de elementos en la cola con prioridad.
     *
     * @return el tamaño de la cola con prioridad.
     */
    public int size() {
        return data.size();
    }

    /**
     * Verifica si la cola con prioridad está vacía.
     *
     * @return true si la cola con prioridad está vacía, false de lo contrario.
     */
    public boolean isEmpty() {
        return size() == 0;
    }
}