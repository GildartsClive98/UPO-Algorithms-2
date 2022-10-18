package upo.graph20024119;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import upo.graph.base.VisitForest;
import upo.graph.base.WeightedGraph;

public class AdjListUndirWeight implements WeightedGraph {

    ArrayList<AdjListItem> vertices = new ArrayList<>();

    @Override
    public void addEdge(String arg0, String arg1) throws IllegalArgumentException {
        if(!containsVertex(arg0) || !containsVertex(arg1))
            throw new IllegalArgumentException("Uno dei due vertici non è contenuto nel grafo.");

        vertices.get(getVertexIndex(arg0)).getAdjList().add(new VertexAdjListItem(arg1, defaultEdgeWeight));
        vertices.get(getVertexIndex(arg1)).getAdjList().add(new VertexAdjListItem(arg0, defaultEdgeWeight));

    }

    @Override
    public int addVertex(String arg0) {
        AdjListItem item = new AdjListItem(arg0);
        vertices.add(item);
        return vertices.indexOf(item);
    }

    @Override
    public Set<Set<String>> connectedComponents() throws UnsupportedOperationException {
        // for (AdjListItem adjListItem : vertices)
        // if (adjListItem.getVertex().equals(arg0))
        // for (VertexAdjListItem item : adjListItem.getAdjList())
        // if(item.getVertex().equals(arg1))
        return Collections.emptySet();
    }

    @Override
    public boolean containsEdge(String arg0, String arg1) throws IllegalArgumentException {
        if (!containsVertex(arg0) || !containsVertex(arg1))
            throw new IllegalArgumentException("Uno dei due vertici non è contenuto nel grafo.");

        for (AdjListItem adjListItem : vertices)
            if (adjListItem.getVertex().equals(arg0))
                for (VertexAdjListItem item : adjListItem.getAdjList())
                    if (item.getVertex().equals(arg1))
                        return true;
        return false;
    }

    @Override
    public boolean containsVertex(String arg0) {
        for (AdjListItem adjListItem : vertices)
            if (adjListItem.getVertex().equals(arg0))
                return true;

        return false;
    }

    @Override
    public Set<String> getAdjacent(String arg0) throws NoSuchElementException {
        if (!containsVertex(arg0))
            throw new NoSuchElementException("Il vertice non appartiene al grafo.");

        HashSet<String> set = new HashSet<>();
        for (AdjListItem adjListItem : vertices)
            if (adjListItem.getVertex().equals(arg0))
                for (VertexAdjListItem item : adjListItem.getAdjList())
                    set.add(item.getVertex());
        return set;
    }

    @Override
    public VisitForest getBFSTree(String arg0) throws UnsupportedOperationException, IllegalArgumentException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public VisitForest getDFSTOTForest(String arg0) throws UnsupportedOperationException, IllegalArgumentException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public VisitForest getDFSTOTForest(String[] arg0) throws UnsupportedOperationException, IllegalArgumentException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public VisitForest getDFSTree(String arg0) throws UnsupportedOperationException, IllegalArgumentException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getVertexIndex(String arg0) {
        for (AdjListItem adjListItem : vertices)
            if (adjListItem.getVertex().equals(arg0))
                return vertices.indexOf(adjListItem);
        return -1;
    }

    @Override
    public String getVertexLabel(Integer arg0) {
        return vertices.get(arg0).getVertex();
    }

    @Override
    public boolean isAdjacent(String arg0, String arg1) throws IllegalArgumentException {
        return containsEdge(arg0, arg1);
    }

    @Override
    public boolean isCyclic() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isDAG() {
        return false;
    }

    @Override
    public boolean isDirected() {
        return false;
    }

    @Override
    public void removeEdge(String arg0, String arg1) throws IllegalArgumentException, NoSuchElementException {
        if(!containsVertex(arg0) || !containsVertex(arg1))
            throw new IllegalArgumentException("Uno dei due vertici non è contenuto nel grafo.");
        if(!containsEdge(arg0, arg1))
            throw new NoSuchElementException("L'arco non appartiene al grafo.");
        
        vertices.get(getVertexIndex(arg0)).getAdjList().remove(new VertexAdjListItem(arg1, defaultEdgeWeight));
        vertices.get(getVertexIndex(arg1)).getAdjList().remove(new VertexAdjListItem(arg0, defaultEdgeWeight));

    }

    @Override
    public void removeVertex(String arg0) throws NoSuchElementException {
        // TODO Auto-generated method stub

    }

    @Override
    public int size() {
        return vertices.size();
    }

    @Override
    public Set<Set<String>> stronglyConnectedComponents() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Operazione non supportata su grafi non orientati.");
    }

    @Override
    public String[] topologicalSort() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Operazione non supportata su grafi non orientati.");
    }

    @Override
    public WeightedGraph getBellmanFordShortestPaths(String arg0)
            throws UnsupportedOperationException, IllegalArgumentException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WeightedGraph getDijkstraShortestPaths(String arg0)
            throws UnsupportedOperationException, IllegalArgumentException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double getEdgeWeight(String arg0, String arg1) throws IllegalArgumentException, NoSuchElementException {
        if (!containsVertex(arg0) || !containsVertex(arg1))
            throw new IllegalArgumentException("Uno dei due vertici non è contenuto nel grafo.");
        if(!containsEdge(arg0, arg1))
            throw new NoSuchElementException("L'arco non appartiene al grafo.");

        for (AdjListItem adjListItem : vertices)
            if (adjListItem.getVertex().equals(arg0))
                for (VertexAdjListItem item : adjListItem.getAdjList())
                    if (item.getVertex().equals(arg1))
                        return item.getWeight();
        return 0;
    }

    @Override
    public WeightedGraph getFloydWarshallShortestPaths() throws UnsupportedOperationException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WeightedGraph getKruskalMST() throws UnsupportedOperationException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WeightedGraph getPrimMST(String arg0) throws UnsupportedOperationException, IllegalArgumentException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setEdgeWeight(String arg0, String arg1, double arg2)
            throws IllegalArgumentException, NoSuchElementException {
            if (!containsVertex(arg0) || !containsVertex(arg1))
                throw new IllegalArgumentException("Uno dei due vertici non è contenuto nel grafo.");
            if(!containsEdge(arg0, arg1))
                throw new NoSuchElementException("L'arco non appartiene al grafo.");
    
            for (AdjListItem adjListItem : vertices)
                if (adjListItem.getVertex().equals(arg0))
                    for (VertexAdjListItem item : adjListItem.getAdjList())
                        if (item.getVertex().equals(arg1))
                            item.setWeight(arg2);
    }

}
