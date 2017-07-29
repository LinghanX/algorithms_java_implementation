package Graphs;

/**
 * A representation of graph using Adjacency lists
 * Created by Linghan on 7/19/17.
 */
public class AdjListGraph {
    public static class Edge {
        private Vertex from;
        private Vertex to;
        private int weight;

        Edge(Vertex from, Vertex to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public Vertex from() {
            return from;
        }

        public Vertex to() {
            return to;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int newWeight) {
            this.weight = newWeight;
        }
    }

    private static class Vertex {
        private char name;
        private Edge[] adj;
    }
}
