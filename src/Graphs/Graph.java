package Graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A graph representation using adjacency lists
 * Operations supported are:
 *     reverse: make a transpose copy of the graph such that for each e(u, v),
 *              the newGraph has an e(v, u), time complexity O(|E| + |V|)
 *     printGraph: Print out each vertex of the graph and their adjacency
 *                 vertices, time complexity O(|E| + |V|)
 */
public class Graph {
    private Vertex[] vertices;
    private int nOfVertices;
    private int nOfEdges;

    /**
     * Inner class vertex is designed to represent information of each vertex
     * For convenience purpose the graph traversal related attributes such as
     * discover, finish, component, color and parent are designed to be public.
     */
    protected static class Vertex {
        private int val;
        private Vertex next;
        public int discover;
        public int finish;
        public int component;
        public int color;
        public int parent;

        public Vertex(Integer e) {
            val = e;
            next = null;
            discover = 0;
            finish = 0;
            component = 0;
            color = 0;
            parent = -1;
        }
        public void add(Vertex e) {
            Vertex lastNode = this;
            while(lastNode.next != null) lastNode = lastNode.next;
            lastNode.next = e;
        }
        public Vertex next() {
            return this.next;
        }
        public boolean hasNext() {
            return !(this.next == null);
        }
        public int getVal() { return this.val; }
    }

    public int getnOfVertices() { return this.nOfVertices; }
    public Vertex[] getVertices() { return this.vertices; }

    /**
     * Protect default instantiator
     */
    private Graph(){}

    /**
     *
     * @param file
     *             a source file of a graph, format requirements:
     *             The first line of the file includes two integers n and m
     *             which indicates the number of vertices in the graph and
     *             the number of edges in the graph respectively.
     *             1 ≤ n, m ≤ 231 − 1. The integers
     *             are separated by one blank character (one space).
     *             The vertices of the graph will be named 1, 2, ..., n.
     *
     *             Starting at the second line, each line contains 2 integers i
     *             and j such that 1 ≤ i,j ≤ n.
     *             The integers are separated by one blank character (one space)
     * @throws FileNotFoundException throw error when the provided path is not
     *                               found
     */
    public Graph(File file) throws FileNotFoundException {
        Scanner in = new Scanner(file);
        nOfVertices = in.nextInt();
        nOfEdges = in.nextInt();

        /* Alteration for Algs4 examples */

        vertices = new Vertex[nOfVertices];

        // initiate adjacency lists
        for(int i = 0; i < nOfVertices; i++) {
            vertices[i] = new Vertex(i);
        }

        for(int i = 0; i < nOfEdges; i++) {
            // -1 to make it easier for array operations
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            vertices[from].add(new Vertex(to));
        }
    }

    /**
     * A constructor provided for easy copy of a graph
     *
     * @param nOfVertices number of vertices
     * @param nOfEdges number of edges
     */
    public Graph(int nOfVertices, int nOfEdges) {
        this.nOfVertices = nOfVertices;
        this.nOfEdges = nOfEdges;
        this.vertices = new Vertex[nOfVertices];

        for(int i = 0; i < nOfVertices; i++) {
            vertices[i] = new Vertex(i);
        }
    }

    /**
     *  Make a transpose copy of the graph such that for each e(u, v),
     *  the newGraph has an e(v, u), time complexity O(|E| + |V|)
     *
     * @return a transposed graph
     */
    public Graph reverse() {
        Graph newG = new Graph(nOfVertices, nOfEdges);
        for(int i = 0; i < newG.getnOfVertices(); i++) {
            newG.getVertices()[i].discover = vertices[i].discover;
            newG.getVertices()[i].finish = vertices[i].finish;
            newG.getVertices()[i].color = 0;
        }

        for(Vertex v: vertices) {
            Vertex to = v.next();
            while(to != null) {
                newG.getVertices()[to.getVal()].add(new Vertex(v.getVal()));
                to = to.next();
            }
        }
        return newG;
    }

    /**
     * Print out each vertex of the graph and their adjacency
     * vertices, time complexity O(|E| + |V|)
     *
     * @param g the graph to be printed
     */
    public static void printGraph(Graph g) {
        for(Vertex vertex : g.vertices) {
            while(vertex != null) {
                System.out.print(vertex.val + "->");
                vertex = vertex.next;
            }
            System.out.println("null");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(args[0]);
        Graph test = new Graph(file);
        printGraph(test);
        System.out.println("Reverse Graph is: ");
        Graph reverse = test.reverse();
        printGraph(reverse);
    }
}
