package upo.graph20024119;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdjListItem {

    private String vertex;
    private ArrayList<VertexAdjListItem> adjList;

    public String getVertex() {
        return vertex;
    }

    public void setVertex(String vertex) {
        this.vertex = vertex;
    }

    public List<VertexAdjListItem> getAdjList() {
        return adjList;
    }

    public AdjListItem(String vertex) {
        this.vertex = vertex;
        adjList = new ArrayList<>();
    }

    public AdjListItem(String vertex, Collection<? extends VertexAdjListItem> collection) {
        this.vertex = vertex;
        adjList = new ArrayList<>(collection);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj.getClass().equals(this.getClass())) {
            AdjListItem comparedTo = (AdjListItem) obj;
            if (vertex == comparedTo.getVertex() && adjList.size() == comparedTo.adjList.size()) {
                for (int i = 0; i < adjList.size(); i++) {
                    var local = adjList.get(i);
                    var compared = comparedTo.adjList.get(i);
                    if (!local.equals(compared))
                        return false;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return vertex.length() + vertex.hashCode() + adjList.size() + adjList.hashCode();
    }
}
