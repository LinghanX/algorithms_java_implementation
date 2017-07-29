import java.util.PriorityQueue;

/**
 * Implementation of Huffman code using greedy algorithm
 * Created by Linghan on 7/18/17.
 */
public class HuffmanCode {
    private static class Node implements Comparable<Node> {
        private double p;
        private Node left;
        private Node right;

        public Node(double p) {
            this.p = p;
            this.left = null;
            this.right = null;
        }

        @Override
        public int compareTo(Node o) {
            if(this.p > o.p) {
                return 1;
            } else if(this.p == o.p) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    private PriorityQueue<Node> heap;

    public HuffmanCode(double[] p) {
        int n = p.length;
        heap = new PriorityQueue<>();

        for(double d : p) {
            Node node = new Node(d);
            heap.offer(node);
        }

        for(int i = 0; i < n-2; i++) {
            Node p1 = heap.poll();
            Node p2 = heap.poll();

            double newP = p1.p + p2.p;

            Node newNode = new Node(newP);
            newNode.left = p2;
            newNode.right = p1;

            heap.offer(newNode);
        }
    }

    public Node root() { return heap.peek();}
    public int size() { return heap.size();}
    public void printTree(Node tree) {
        if(tree == null) return;
        if(tree.left != null){
            System.out.print("0");
            tree = tree.left;
            printTree(tree);
        }
        if(tree.right != null) {
            System.out.print("1");
            tree = tree.right;
            printTree(tree);
        }
    }

    public static void main(String[] args) {
        double[] sampleP = new double[] { 0.05, 0.05, 0.1, 0.2, 0.25, 0.35};
        double[] test2 = new double[] {0.32, 0.15, 0.15, 0.18, 0.1, 0.1};
        HuffmanCode test = new HuffmanCode(test2);
        Node tree = test.root();
        test.printTree(tree);
    }
}
