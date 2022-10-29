package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import upo.graph20024119.AdjListUndirWeight;

public class TSPTest {

    AdjListUndirWeight graph = new AdjListUndirWeight();

    @Test
    public void testTSPApprox() {
        int num = 5;
        for (char a = 'A'; a < 'A' + num; a++)
            graph.addVertex(new String(new char[] { a }));
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("A", "D");
        graph.addEdge("A", "E");
        graph.addEdge("B", "C");
        graph.addEdge("B", "D");
        graph.addEdge("B", "E");
        graph.addEdge("C", "D");
        graph.addEdge("C", "E");
        graph.addEdge("D", "E");
        graph.setEdgeWeight("A", "B", 5.0);
        graph.setEdgeWeight("A", "C", 4.0);
        graph.setEdgeWeight("A", "D", 3.0);
        graph.setEdgeWeight("A", "E", 2.0);
        graph.setEdgeWeight("B", "C", 2.0);
        graph.setEdgeWeight("B", "D", 6.0);
        graph.setEdgeWeight("B", "E", 3.0);
        graph.setEdgeWeight("C", "D", 4.0);
        graph.setEdgeWeight("C", "E", 2.0);
        graph.setEdgeWeight("D", "E", 5.0);

        assertTrue(5.0 == graph.getEdgeWeight("A", "B"));

        AdjListUndirWeight resultCompare = new AdjListUndirWeight();
        for (char a = 'A'; a < 'A' + num; a++)
            resultCompare.addVertex(new String(new char[] { a }));
        resultCompare.addEdge("E", "A");
        resultCompare.addEdge("A", "D");
        resultCompare.addEdge("C", "B");
        resultCompare.addEdge("E", "C");
        resultCompare.setEdgeWeight("E", "A", 2.0);
        resultCompare.setEdgeWeight("A", "D", 3.0);
        resultCompare.setEdgeWeight("C", "B", 2.0);
        resultCompare.setEdgeWeight("E", "C", 2.0);

        var result = graph.getPrimMST("E");
        assertEquals(resultCompare, result);


        result = graph.getApproximatedTSP("E");
    }
}
