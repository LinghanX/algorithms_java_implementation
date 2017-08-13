package Graphs;

import java.io.*;

/**
 * The DFS class is named after the main algorithm implemented in the class:
 * Depth First Search. This class takes a graph and runs DFS on the graph to
 * mark the discover and finish time of each vertices.
 *
 * Operations supported:
 * findSCC: finds the strongly connected component of the graph give.
 *          complexity: \theta (|E| + |V|)
 */
public class DFS {
    private Graph g;
    private int time;

    public DFS(Graph graph) {
        int nOfV = graph.getnOfVertices();
        g = graph;
        time = 0;
        dfs();
    }

    /**
     * This function runs dfsUtil on each vertex whose color is white
     */
    private void dfs() {
        for(Graph.Vertex v : g.getVertices()) {
            if(v.color == 0) dfsUtil(v);
        }
    }

    /**
     * The dfsUtil function use a stack implementation of dfs algorithm.
     *
     * Side effects:
     *     Each vertex's discover time and finish time is marked
     *
     * @param v vertex in a graph
     */
    //color: 0: unvisited, 1: visited
    private void dfsUtil(Graph.Vertex v) {
        MyStack<Graph.Vertex> stack = new MyStack<>();
        stack.push(v);

        while(!stack.isEmpty()) {
            Graph.Vertex u = stack.peek();
            if(u.discover != 0 && u.finish != 0 ) {
                stack.pop();
                continue;
            }
            if(u.color == 1) {
                time = time + 1;
                u.finish = time;
                stack.pop();
                continue;
            }

            u.color = 1;
            time = time + 1;
            u.discover = time;
            Graph.Vertex neighbour = u.next();
            while(neighbour != null) {
                Graph.Vertex neighbourVertex = g.getVertices()[neighbour.getVal()];
                if(neighbourVertex.color == 0) {
                    neighbourVertex.parent = u.getVal();
                    stack.push(neighbourVertex);
                }
                neighbour = neighbour.next();
            }
        }
    }

    /**
     * findSCC finds strongly connected component of the graph in O(|E|+|V|)
     *
     * @return A list of lists, each list contains the value of vertex in the
     *         same strongly connected component
     */
    public MyLinkedList<MyLinkedList<Integer>> findSCC() {
        Graph newG = g.reverse();

        // A copy of pointers are created to keep track of the original vertices
        Graph.Vertex[] copy = new Graph.Vertex[newG.getnOfVertices()];
        for(int i = 0; i < newG.getnOfVertices(); i++) {
            copy[i] = newG.getVertices()[i];
        }

        // here we sort the vertices by its finish time, so that when we generate
        // scc the dfs will run based on their finish time, in non-ascending order
        sortByFinish(copy);
        MyLinkedList<MyLinkedList<Integer>> scc =  generateComponents(newG, copy);
        return scc;
    }

    /**
     * generateComponents function traverse the vertices of the original graph
     * in order of their finish time(the max finish comes first). For each of
     * the vertex, it calls searchComponent() to generate a dfs tree.
     *
     * @param newG a transposed copy of the graph
     * @param copy a copy of pointers of each vertex in the graph,
     *             in non-descending order sorted by finish time.
     * @return A list of strongly connected component, represented by their
     *         number in a list.
     */
    private MyLinkedList<MyLinkedList<Integer>>
    generateComponents(Graph newG, Graph.Vertex[] copy) {
        boolean[] visited = new boolean[newG.getnOfVertices()];
        MyLinkedList<MyLinkedList<Integer>> components = new MyLinkedList<>();
        for(int i = copy.length - 1; i >= 0; i--) {
            Graph.Vertex v = copy[i];
            if(!visited[v.getVal()]) {
                components.add(searchComponent(v, newG, visited));
            }
        }

        return components;
    }

    /**
     * searchComponent generates a dfs tree starting with the given vertex v
     *
     * @param v a vertex in the graph
     * @param graph a transposed copy of the original graph
     * @param visited an array to mark if a vertex is visited in the second dfs
     * @return A list of Integers which indicates the vertex in a strongly
     *         connected component
     */
    private MyLinkedList<Integer> searchComponent(Graph.Vertex v,
                                                  Graph graph,
                                                  boolean[] visited) {
        MyLinkedList<Integer> component = new MyLinkedList<>();

        MyStack<Graph.Vertex> stack = new MyStack<>();
        stack.push(v);

        while(!stack.isEmpty()) {
            Graph.Vertex u = stack.peek();
            if(visited[u.getVal()]) {
                stack.pop();
                continue;
            }

            visited[u.getVal()] = true;
            component.add(u.getVal());
            Graph.Vertex neighbour = u.next();
            while(neighbour != null) {
                Graph.Vertex neighbourVertex = graph.getVertices()[neighbour.getVal()];
                if(!visited[neighbourVertex.getVal()]) {
                    stack.push(neighbourVertex);
                }
                neighbour = neighbour.next();
            }
        }

        return component;
    }

    /**
     * sortByFinish uses quicksort to sort an array of vertices in
     * non-descending orders based on their finish time.
     *
     * @param vertices an array of vertices, each of them should have their
     *                 finish time already marked.
     */
    private void sortByFinish(Graph.Vertex[] vertices) {
        int low = 0, high = vertices.length-1;
        quickSortByFinish(vertices, low, high);
    }

    /**
     *
     * @param vertices an array of vertices
     * @param low the left bound index of the array
     * @param high the right bound index of the array
     */
    private void quickSortByFinish(Graph.Vertex[] vertices,
                                   int low,
                                   int high) {
        if(high <= low) return;
        int j = partition(vertices, low, high);
        quickSortByFinish(vertices, low, j-1);
        quickSortByFinish(vertices, j+1, high);
    }

    /**
     * The partition util function for quicksort
     *
     * @param vertices an array of vertices
     * @param low the left bound index of the array
     * @param high the right bound index of the array
     * @return
     */
    private int partition(Graph.Vertex[] vertices, int low, int high) {
        int i = low;
        int j = high + 1;
        int partitionValue = vertices[low].finish;

        while(true) {
            while(vertices[++i].finish < partitionValue) if(i == high) break;
            while(partitionValue < vertices[--j].finish) if(j == low) break;
            if(i >= j) break;
            exchange(vertices, i, j);
        }

        exchange(vertices, low, j);
        return j;
    }

    /**
     * exchange switch two vertices in an array
     *
     * @param vertices an array of vertices
     * @param i index of the first vertex
     * @param j index of the second vertex to be exchanged
     */
    private void exchange(Graph.Vertex[] vertices, int i, int j) {
        Graph.Vertex temp = vertices[i];
        vertices[i] = vertices[j];
        vertices[j] = temp;
    }

    public static void main(String[] args) throws FileNotFoundException {
        /* test files
        String testFilePath =
                "/Users/Linghan/Documents/NEU/CS5800/assignments/ProgrammingAssignment/src/graph.txt";
        String testMediumFilePath =
                "/Users/Linghan/Documents/NEU/CS5800/assignments/ProgrammingAssignment/src/mediumGraph.txt";
        String testLargeFilePath =
                "/Users/Linghan/Documents/NEU/CS5800/assignments/ProgrammingAssignment/src/largeDG.txt";
        */
        String argPath = args[0];
        File file = new File(argPath);
        Graph test = new Graph(file);
        DFS sample = new DFS(test);

        MyLinkedList<MyLinkedList<Integer>> scc = sample.findSCC();
        MyLinkedList<Integer> current = scc.remove();
        try {
            PrintWriter writer = new PrintWriter("Components.txt", "UTF-8");
            while(current != null) {
                writer.println("Component:");
                Integer i = current.remove();
                while(i != null) {
                    writer.println(i + 1);
                    i = current.remove();
                }
                current = scc.remove();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
