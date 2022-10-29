package upo.graph20024119;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import upo.additionalstructures.RealPriorityQueue;
import upo.graph.base.VisitForest;
import upo.graph.base.WeightedGraph;
import upo.graph.base.VisitForest.Color;
import upo.graph.base.VisitForest.VisitType;

public class AdjListUndirWeight implements WeightedGraph {

    private static final String NON_IMPLEMENTATA = "Operazione non implementata.";
    private static final String VERTICE_NON_NEL_GRAFO = "Il vertice non appartiene al grafo.";
    private static final String VERTICI_NON_NEL_GRAFO = "Uno dei due vertici non è contenuto nel grafo.";
    private static final String ARCO_NON_APPARTIENE_AL_GRAFO = "L'arco non appartiene al grafo.";
    ArrayList<AdjListItem> vertices = new ArrayList<>();
    int time = 0;

    @Override
    public void addEdge(String arg0, String arg1) throws IllegalArgumentException {
        if (!containsVertex(arg0) || !containsVertex(arg1))
            throw new IllegalArgumentException(VERTICI_NON_NEL_GRAFO);

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
        Set<Set<String>> components = new HashSet<>();
        VisitForest forest = new VisitForest(this, VisitType.DFS);
        for (var element : vertices)
            if (forest.getColor(element.getVertex()) == Color.WHITE)
                components.add(getDFSComponents(forest, element.getVertex()));

        return components;
    }

    private Set<String> getDFSComponents(VisitForest forest, String arg0) {
        Set<String> set = new HashSet<>();
        recDFSComponentsTree(forest, arg0, set);
        return set;
    }

    private void recDFSComponentsTree(VisitForest forest, String arg0, Set<String> set) {
        forest.setColor(arg0, Color.GRAY);
        forest.setStartTime(arg0, time++);
        set.add(arg0);
        for (var element : getAdjacent(arg0)) {
            if (forest.getColor(element) == Color.WHITE) {
                forest.setParent(element, arg0);
                recDFSComponentsTree(forest, element, set);
            }
        }
        forest.setColor(arg0, Color.BLACK);
        forest.setEndTime(arg0, time++);
    }

    @Override
    public boolean containsEdge(String arg0, String arg1) throws IllegalArgumentException {
        if (!containsVertex(arg0) || !containsVertex(arg1))
            throw new IllegalArgumentException(VERTICI_NON_NEL_GRAFO);

        for (AdjListItem adjListItem : vertices)
            if (adjListItem.getVertex().equals(arg0) || adjListItem.getVertex().equals(arg1))
                for (VertexAdjListItem item : adjListItem.getAdjList())
                    if (item.getVertex().equals(arg1) || item.getVertex().equals(arg0))
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
            throw new NoSuchElementException(VERTICE_NON_NEL_GRAFO);

        HashSet<String> set = new HashSet<>();
        for (AdjListItem adjListItem : vertices)
            if (adjListItem.getVertex().equals(arg0))
                for (VertexAdjListItem item : adjListItem.getAdjList())
                    set.add(item.getVertex());
        return set;
    }

    @Override
    public VisitForest getBFSTree(String arg0) throws UnsupportedOperationException, IllegalArgumentException {
        if (!containsVertex(arg0))
            throw new IllegalArgumentException(VERTICE_NON_NEL_GRAFO);
        VisitForest forest = new VisitForest(this, VisitType.BFS);
        forest.setColor(arg0, Color.WHITE);
        forest.setDistance(arg0, 0);
        for (AdjListItem vertex : vertices) {
            for (VertexAdjListItem adj : vertex.getAdjList()) {
                if (forest.getColor(adj.getVertex()) == Color.WHITE) {
                    forest.setColor(adj.getVertex(), Color.GRAY);
                    forest.setParent(adj.getVertex(), vertex.getVertex());
                    forest.setDistance(adj.getVertex(), forest.getDistance(vertex.getVertex()) + 1);
                }
            }
            forest.setColor(vertex.getVertex(), Color.BLACK);
        }
        return forest;
    }

    @Override
    public VisitForest getDFSTOTForest(String arg0) throws UnsupportedOperationException, IllegalArgumentException {
        if (!containsVertex(arg0))
            throw new IllegalArgumentException(VERTICE_NON_NEL_GRAFO);
        time = 1;
        VisitForest forest = new VisitForest(this, VisitType.DFS_TOT);
        recDFSTree(forest, arg0);
        for (var element : vertices)
            if (forest.getColor(element.getVertex()) == Color.WHITE)
                recDFSTree(forest, element.getVertex());
        return forest;
    }

    @Override
    public VisitForest getDFSTOTForest(String[] arg0) throws UnsupportedOperationException, IllegalArgumentException {
        if (arg0.length != vertices.size())
            throw new IllegalArgumentException("Il numero di vertici non coincide.");
        time = 1;
        VisitForest forest = new VisitForest(this, VisitType.DFS_TOT);
        for (var element : arg0)
            if (forest.getColor(element) == Color.WHITE)
                recDFSTree(forest, element);
        return forest;
    }

    @Override
    public VisitForest getDFSTree(String arg0) throws UnsupportedOperationException, IllegalArgumentException {
        if (!containsVertex(arg0))
            throw new IllegalArgumentException(VERTICE_NON_NEL_GRAFO);
        time = 1;
        VisitForest forest = new VisitForest(this, VisitType.DFS);
        recDFSTree(forest, arg0);
        return forest;
    }

    private void recDFSTree(VisitForest forest, String vertex) {
        forest.setColor(vertex, Color.GRAY);
        forest.setStartTime(vertex, time++);
        for (var element : getAdjacent(vertex)) {
            if (forest.getColor(element) == Color.WHITE) {
                forest.setParent(element, vertex);
                recDFSTree(forest, element);
            }
        }
        forest.setColor(vertex, Color.BLACK);
        forest.setEndTime(vertex, time++);
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
        VisitForest forest = new VisitForest(this, null);
        for (var element : vertices)
            if (forest.getColor(element.getVertex()) == Color.WHITE && recCycleVisit(forest, element.getVertex()))
                return true;
        return false;
    }

    private boolean recCycleVisit(VisitForest forest, String item) {
        forest.setColor(item, Color.GRAY);
        for (var element : getAdjacent(item)) {
            if (forest.getColor(element) == Color.WHITE) {
                forest.setParent(element, item);
                if (recCycleVisit(forest, element))
                    return true;
            } else if (!element.equals(forest.getPartent(item)))
                return true;
        }
        forest.setColor(item, Color.BLACK);
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
        if (!containsVertex(arg0) || !containsVertex(arg1))
            throw new IllegalArgumentException(VERTICI_NON_NEL_GRAFO);
        if (!containsEdge(arg0, arg1))
            throw new NoSuchElementException(ARCO_NON_APPARTIENE_AL_GRAFO);

        var weight = getEdgeWeight(arg0, arg1);
        vertices.get(getVertexIndex(arg0)).getAdjList().remove(new VertexAdjListItem(arg1, weight));
        vertices.get(getVertexIndex(arg1)).getAdjList().remove(new VertexAdjListItem(arg0, weight));

    }

    @Override
    public void removeVertex(String arg0) throws NoSuchElementException {
        if (!containsVertex(arg0))
            throw new NoSuchElementException(VERTICE_NON_NEL_GRAFO);
        for (var element : getAdjacent(arg0))
            removeEdge(arg0, element);

        vertices.remove(getVertexIndex(arg0));
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
        throw new UnsupportedOperationException(NON_IMPLEMENTATA);
    }

    @Override
    public WeightedGraph getDijkstraShortestPaths(String arg0)
            throws UnsupportedOperationException, IllegalArgumentException {
        throw new UnsupportedOperationException(NON_IMPLEMENTATA);
    }

    @Override
    public double getEdgeWeight(String arg0, String arg1) throws IllegalArgumentException, NoSuchElementException {
        if (!containsVertex(arg0) || !containsVertex(arg1))
            throw new IllegalArgumentException(VERTICI_NON_NEL_GRAFO);
        if (!containsEdge(arg0, arg1))
            throw new NoSuchElementException(ARCO_NON_APPARTIENE_AL_GRAFO);

        for (AdjListItem adjListItem : vertices)
            if (adjListItem.getVertex().equals(arg0) || adjListItem.getVertex().equals(arg1))
                for (VertexAdjListItem item : adjListItem.getAdjList())
                    if (item.getVertex().equals(arg1) || item.getVertex().equals(arg0))
                        return item.getWeight();
        return 0;
    }

    @Override
    public WeightedGraph getFloydWarshallShortestPaths() throws UnsupportedOperationException {
        throw new UnsupportedOperationException(NON_IMPLEMENTATA);
    }

    @Override
    public WeightedGraph getKruskalMST() throws UnsupportedOperationException {
        throw new UnsupportedOperationException(NON_IMPLEMENTATA);
    }

    @Override
    public WeightedGraph getPrimMST(String arg0) throws UnsupportedOperationException, IllegalArgumentException {
        if (!containsVertex(arg0))
            throw new IllegalArgumentException(VERTICE_NON_NEL_GRAFO);
        AdjListUndirWeight graph = new AdjListUndirWeight();
        VisitForest forest = new VisitForest(this, VisitType.BFS);
        boolean[] def = new boolean[vertices.size()];
        Arrays.fill(def, false);
        RealPriorityQueue<AdjListItem, Double> queue = new RealPriorityQueue<>();
        for (AdjListItem v : vertices) {
            queue.enqueue(Double.POSITIVE_INFINITY, v);
            forest.setDistance(v.getVertex(), Double.POSITIVE_INFINITY);
        }
        forest.setDistance(arg0, 0);
        queue.decreaseKey(forest.getDistance(arg0), vertices.get(getVertexIndex(arg0)));
        while (!queue.isEmpty()) {
            var temp = queue.dequeue();
            var vertex = temp.getValue();
            graph.addVertex(vertex.getVertex());
            def[getVertexIndex(vertex.getVertex())] = true;
            for (VertexAdjListItem adj : vertex.getAdjList()) {
                if (!def[getVertexIndex(adj.getVertex())]
                        && forest.getDistance(adj.getVertex()) > getEdgeWeight(vertex.getVertex(), adj.getVertex())) {
                    forest.setParent(adj.getVertex(), vertex.getVertex());
                    forest.setDistance(adj.getVertex(), getEdgeWeight(vertex.getVertex(), adj.getVertex()));
                    queue.decreaseKey(forest.getDistance(adj.getVertex()),
                            vertices.get(getVertexIndex(adj.getVertex())));
                }
            }
        }
        for (AdjListItem vertex : vertices) {
            if (!vertex.getVertex().equals(arg0)) {
                graph.addEdge(forest.getPartent(vertex.getVertex()), vertex.getVertex());
                graph.setEdgeWeight(forest.getPartent(vertex.getVertex()), vertex.getVertex(),
                        forest.getDistance(vertex.getVertex()));
            }
        }
        return graph;
    }

    @Override
    public void setEdgeWeight(String arg0, String arg1, double arg2)
            throws IllegalArgumentException, NoSuchElementException {
        if (!containsVertex(arg0) || !containsVertex(arg1))
            throw new IllegalArgumentException(VERTICI_NON_NEL_GRAFO);
        if (!containsEdge(arg0, arg1))
            throw new NoSuchElementException(ARCO_NON_APPARTIENE_AL_GRAFO);

        for (AdjListItem adjListItem : vertices)
            if (adjListItem.getVertex().equals(arg0) || adjListItem.getVertex().equals(arg1))
                for (VertexAdjListItem item : adjListItem.getAdjList())
                    if (item.getVertex().equals(arg1) || item.getVertex().equals(arg0))
                        item.setWeight(arg2);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj.getClass() != getClass())
            return false;
        AdjListUndirWeight graph = (AdjListUndirWeight) obj;

        if (size() != graph.size())
            return false;

        for (AdjListItem vertex : vertices)
            if (!graph.containsVertex(vertex.getVertex()))
                return false;

        return compareEdges(graph);
    }

    private boolean compareEdges(WeightedGraph graph) {
        for (AdjListItem vertex : vertices)
            for (VertexAdjListItem adjacent : vertex.getAdjList()) {
                if (graph.containsEdge(vertex.getVertex(), adjacent.getVertex()) != containsEdge(vertex.getVertex(),
                        adjacent.getVertex()))
                    return false;
                if (getEdgeWeight(vertex.getVertex(), adjacent.getVertex()) != graph.getEdgeWeight(vertex.getVertex(),
                        adjacent.getVertex()))
                    return false;
            }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + vertices.size();
    }

    // Pratico 3
    public WeightedGraph getApproximatedTSP(String start) {
        AdjListUndirWeight graph = new AdjListUndirWeight();
        AdjListUndirWeight prim = (AdjListUndirWeight) getPrimMST(start);
        prim.equals(this);
        for(AdjListItem vertex : vertices)
            graph.addVertex(vertex.getVertex());
        var forest = prim.getDFSTOTForest(start);
        for (AdjListItem vertex : vertices) {
            if (!vertex.getVertex().equals(start)) {
                graph.addEdge(forest.getPartent(vertex.getVertex()), vertex.getVertex());
                graph.setEdgeWeight(forest.getPartent(vertex.getVertex()), vertex.getVertex(), prim.getEdgeWeight(forest.getPartent(vertex.getVertex()), vertex.getVertex()));
            }
        }
        return graph;
    }
}
