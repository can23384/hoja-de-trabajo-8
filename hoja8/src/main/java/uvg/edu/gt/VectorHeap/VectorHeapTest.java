import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Vector;

public class VectorHeapTest {

    private VectorHeap<Paciente> heap;

    @Before
    public void setUp() {
        heap = new VectorHeap<>();
    }

    @Test
    public void testAddAndRemove() {
        heap.add(new Paciente("Juan Perez", "fractura de pierna", 'C'));
        heap.add(new Paciente("Maria Ramirez", "apendicitis", 'A'));
        heap.add(new Paciente("Lorenzo Toledo", "chikungunya", 'E'));

        assertEquals(3, heap.size());

        Paciente first = heap.remove();
        assertEquals("Maria Ramirez", first.getNombre());

        Paciente second = heap.remove();
        assertEquals("Juan Perez", second.getNombre());

        Paciente third = heap.remove();
        assertEquals("Lorenzo Toledo", third.getNombre());

        assertTrue(heap.isEmpty());
    }

    @Test
    public void testOrderOfRemoval() {
        heap.add(new Paciente("Juan Perez", "fractura de pierna", 'C'));
        heap.add(new Paciente("Maria Ramirez", "apendicitis", 'A'));
        heap.add(new Paciente("Lorenzo Toledo", "chikungunya", 'E'));

        assertEquals("Maria Ramirez", heap.remove().getNombre());
        assertEquals("Juan Perez", heap.remove().getNombre());
        assertEquals("Lorenzo Toledo", heap.remove().getNombre());
    }

    @Test
    public void testEmptyHeap() {
        assertTrue(heap.isEmpty());
        assertNull(heap.remove());
    }

    @Test
    public void testHeapWithSingleElement() {
        heap.add(new Paciente("Juan Perez", "fractura de pierna", 'C'));
        assertFalse(heap.isEmpty());
        assertEquals("Juan Perez", heap.remove().getNombre());
        assertTrue(heap.isEmpty());
    }
}