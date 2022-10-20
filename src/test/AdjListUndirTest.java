package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Test;

import upo.graph20024119.AdjListUndir;
import upo.graph20024119.AdjListUndirWeight;

public class AdjListUndirTest {
    AdjListUndir listUndir = new AdjListUndir();

    @Test
    public void testAddVertex() {
        assertEquals(0, listUndir.addVertex("A"));
        assertEquals(1, listUndir.addVertex("B"));
        assertEquals(2, listUndir.addVertex("C"));
    }

    @Test
    public void testRemoves() {
        int num = 6;
        for (char a = 'A'; a < 'A' + num; a++)
            listUndir.addVertex(new String(new char[] { a }));
        listUndir.addEdge("A", "B");
        listUndir.addEdge("A", "C");
        listUndir.addEdge("D", "B");
        listUndir.addEdge("E", "A");
        listUndir.addEdge("E", "F");
        listUndir.removeVertex("A");
        assertThrows(IllegalArgumentException.class, () -> listUndir.containsEdge("A", "E"));
        assertFalse(listUndir.containsVertex("A"));
        var cc = listUndir.connectedComponents();
        assertEquals(3, cc.size());
    }

    @Test
    public void testContainsVertex() {
        assertEquals(0, listUndir.addVertex("A"));
        assertEquals(1, listUndir.addVertex("B"));
        assertEquals(2, listUndir.addVertex("C"));
        assertTrue(listUndir.containsVertex("A"));
        assertTrue(listUndir.containsVertex("B"));
        assertTrue(listUndir.containsVertex("C"));
        assertEquals(3, listUndir.size());
    }

    @Test
    public void testEdgeMethods() {
        assertEquals(0, listUndir.addVertex("A"));
        assertEquals(1, listUndir.addVertex("B"));
        assertEquals(2, listUndir.addVertex("C"));
        assertTrue(listUndir.containsVertex("A"));
        assertTrue(listUndir.containsVertex("B"));
        assertTrue(listUndir.containsVertex("C"));
        assertEquals(3, listUndir.size());

        listUndir.addEdge("A", "B");
        assertTrue(listUndir.containsEdge("A", "B"));
        assertTrue(listUndir.containsEdge("B", "A"));
        assertThrows(IllegalArgumentException.class, () -> listUndir.containsEdge("C", "D"));
    }

    @Test
    public void testTrueEquals() {
        AdjListUndir tempAdjListUndir = new AdjListUndir();
        assertEquals(0, listUndir.addVertex("A"));
        assertEquals(0, tempAdjListUndir.addVertex("A"));
        assertEquals(1, listUndir.addVertex("B"));
        assertEquals(1, tempAdjListUndir.addVertex("B"));
        assertEquals(2, listUndir.addVertex("C"));
        assertEquals(2, tempAdjListUndir.addVertex("C"));
        assertEquals(3, listUndir.addVertex("D"));
        assertEquals(3, tempAdjListUndir.addVertex("D"));

        listUndir.addEdge("A", "B");
        tempAdjListUndir.addEdge("A", "B");
        listUndir.addEdge("C", "D");
        tempAdjListUndir.addEdge("C", "D");

        assertTrue(listUndir.equals(tempAdjListUndir));
        assertThrows(NoSuchElementException.class, () -> listUndir.getEdgeWeight("A", "C"));

    }

    @Test
    public void testFalseEqualsDifferentClass() {
        AdjListUndirWeight anotherList = new AdjListUndirWeight();
        assertFalse(listUndir.equals(anotherList));
        // Different classes lead to false result
    }

    @Test
    public void testExceptionCallingEdgeWeight() {
        assertThrows(UnsupportedOperationException.class, () -> listUndir.setEdgeWeight("A", "B", 1.0));
        assertThrows(IllegalArgumentException.class, () -> listUndir.getEdgeWeight("A", "B"));
    }

    @Test
    public void testDFSTree() {
        int[] timings = { 12, 5, 7, 4, 11, 10 };
        int num = 6;
        for (char a = 'A'; a < 'A' + num; a++)
            listUndir.addVertex(new String(new char[] { a }));
        listUndir.addEdge("A", "B");
        listUndir.addEdge("A", "C");
        listUndir.addEdge("D", "B");
        listUndir.addEdge("E", "A");
        listUndir.addEdge("E", "F");
        var forest = listUndir.getDFSTree("A");
        for (int i = 0; i < listUndir.size(); i++)
            assertEquals((Integer)timings[i], forest.getEndTime(listUndir.getVertexLabel(i)));
    }

    @Test
    public void testDFSTOTTree() {
        int[] timings = { 12, 5, 7, 4, 11, 10 };
        int num = 6;
        for (char a = 'A'; a < 'A' + num; a++)
            listUndir.addVertex(new String(new char[] { a }));
        listUndir.addEdge("A", "B");
        listUndir.addEdge("A", "C");
        listUndir.addEdge("D", "B");
        listUndir.addEdge("E", "A");
        listUndir.addEdge("E", "F");
        var forest = listUndir.getDFSTOTForest("A");
        for (int i = 0; i < listUndir.size(); i++)
            assertEquals((Integer)timings[i], forest.getEndTime(listUndir.getVertexLabel(i)));

    }

    @Test
    public void testDFSTOTArrayTree() {
        int[] timings = { 12, 5, 7, 4, 11, 10 };
        int num = 6;
        for (char a = 'A'; a < 'A' + num; a++)
            listUndir.addVertex(new String(new char[] { a }));
        listUndir.addEdge("A", "B");
        listUndir.addEdge("A", "C");
        listUndir.addEdge("D", "B");
        listUndir.addEdge("E", "A");
        listUndir.addEdge("E", "F");
        var forest = listUndir.getDFSTOTForest(new String[] {"A", "B", "C", "D", "E", "F"});
        for (int i = 0; i < listUndir.size(); i++)
            assertEquals((Integer)timings[i], forest.getEndTime(listUndir.getVertexLabel(i)));

    }

    @Test
    public void testBFSTree() {
        int num = 6;
        for (char a = 'A'; a < 'A' + num; a++)
            listUndir.addVertex(new String(new char[] { a }));
        listUndir.addEdge("A", "B");
        listUndir.addEdge("A", "C");
        listUndir.addEdge("D", "B");
        listUndir.addEdge("E", "A");
        listUndir.addEdge("E", "F");
        var visit = listUndir.getBFSTree("A");
        assertNotNull(visit);
        String[] vertices = new String[listUndir.size()];
        for (int i = listUndir.getVertexIndex("A") + 1; i < listUndir.size(); i++) {
            char a = (char) ('A' + i);
            vertices[i] = visit.getPartent(new String(new char[] { a }));
        }
        var arrayResult = new String[] { null, "A", "A", "B", "A", "E" };
        assertArrayEquals(arrayResult, vertices);
    }

    @Test
    public void testConnectedComponents() {
        int num = 6;
        for (char a = 'A'; a < 'A' + num; a++)
            listUndir.addVertex(new String(new char[] { a }));
        listUndir.addEdge("A", "B");
        listUndir.addEdge("A", "C");
        listUndir.addEdge("D", "E");
        listUndir.addEdge("E", "F");

        var components = listUndir.connectedComponents();
        assertEquals(2, components.size());
        components.forEach(c -> assertEquals(3, c.size()));

    }

    @Test
    public void testCyclic(){
        int num = 6;
        for (char a = 'A'; a < 'A' + num; a++)
            listUndir.addVertex(new String(new char[] { a }));
        listUndir.addEdge("A", "B");
        listUndir.addEdge("A", "C");
        listUndir.addEdge("D", "E");
        listUndir.addEdge("E", "F");

        assertFalse(listUndir.isCyclic());
        listUndir.addEdge("B", "C");
        assertTrue(listUndir.isCyclic());
    }

    @Test
    public void testPrimException(){
        int num = 6;
        for (char a = 'A'; a < 'A' + num; a++)
            listUndir.addVertex(new String(new char[] { a }));
        listUndir.addEdge("A", "B");
        listUndir.addEdge("A", "C");
        listUndir.addEdge("D", "E");
        listUndir.addEdge("E", "F");

        assertThrows(UnsupportedOperationException.class, () -> listUndir.getPrimMST("A"));
    }
}
