package upo.graph20024119;

public class VertexAdjListItem {

    private String vertex;
    private double weight;

    public String getVertex() {
        return vertex;
    }

    public void setVertex(String vertex) {
        this.vertex = vertex;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public VertexAdjListItem(String vertex, double weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(obj.getClass().equals(this.getClass()))
        {
            VertexAdjListItem comparedTo = (VertexAdjListItem)obj;
            return (vertex.equals(comparedTo.vertex) && weight == comparedTo.weight);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return vertex.length() + vertex.hashCode();
    }
}
