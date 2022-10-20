package upo.graph20024119;

import java.util.NoSuchElementException;

import upo.graph.base.WeightedGraph;

public class AdjListUndir extends AdjListUndirWeight {

    @Override
    public void setEdgeWeight(String arg0, String arg1, double arg2)
            throws IllegalArgumentException, NoSuchElementException {
        throw new UnsupportedOperationException("Non è possibile impostare il peso dell'arco su un grafo non pesato.");
    }

    @Override
    public WeightedGraph getPrimMST(String arg0) throws UnsupportedOperationException, IllegalArgumentException {
        throw new UnsupportedOperationException("Non è possibile generare un Minimo Albero Ricoprente su un grafo non pesato.");
    }
}