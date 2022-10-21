package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import upo.graph20024119.AdjListUndirWeight;

public class AdjListUndirWeightTest {
    
    AdjListUndirWeight listUndir = new AdjListUndirWeight();

    @Test
    public void testPrimMST(){
        int num = 5;
        for (char a = 'A'; a < 'A' + num; a++)
            listUndir.addVertex(new String(new char[] { a }));
        listUndir.addEdge("A", "B");
        listUndir.addEdge("A", "C");
        listUndir.addEdge("B", "D");
        listUndir.addEdge("C", "E");
        listUndir.addEdge("D", "E");
        listUndir.setEdgeWeight("A", "B", 2.0);
        listUndir.setEdgeWeight("A", "C", 4.0);
        listUndir.setEdgeWeight("B", "D", 3.0);
        listUndir.setEdgeWeight("C", "E", 2.0);
        listUndir.setEdgeWeight("D", "E", 2.0);

        AdjListUndirWeight resultCompare = new AdjListUndirWeight();
        for (char a = 'A'; a < 'A' + num; a++)
            resultCompare.addVertex(new String(new char[] { a }));
        resultCompare.addEdge("A", "B");
        resultCompare.addEdge("B", "D");
        resultCompare.addEdge("C", "E");
        resultCompare.addEdge("D", "E");
        resultCompare.setEdgeWeight("A", "B", 2.0);
        resultCompare.setEdgeWeight("B", "D", 3.0);
        resultCompare.setEdgeWeight("C", "E", 2.0);
        resultCompare.setEdgeWeight("D", "E", 2.0);

        var result = listUndir.getPrimMST("A");
        assertEquals(resultCompare, result);
    }

    @Test
    public void testEdgeMethods(){
        int num = 5;
        for (char a = 'A'; a < 'A' + num; a++)
            listUndir.addVertex(new String(new char[] { a }));
        listUndir.addEdge("A", "B");
        listUndir.addEdge("A", "C");
        listUndir.addEdge("B", "D");
        listUndir.addEdge("C", "E");
        listUndir.addEdge("D", "E");
        listUndir.setEdgeWeight("A", "B", 2.0);
        listUndir.setEdgeWeight("A", "C", 4.0);
        listUndir.setEdgeWeight("B", "D", 3.0);
        listUndir.setEdgeWeight("C", "E", 2.0);
        listUndir.setEdgeWeight("D", "E", 2.0);

        assertTrue(2.0 == listUndir.getEdgeWeight("A", "B"));
    }
}
