package searching;

/**
 * A java implementation of Union find
 * Using weighted quick union method from the Algorithms 4th Edition
 * Created by Linghan on 7/13/17.
 */
public class UnionFind {
    private int[] union;
    private int[] size;
    private int count;

    public UnionFind(int n) {
        this.count = n;
        this.union = new int[n];
        this.size = new int[n];
        for(int i = 0; i < n; i++) {
            union[i] = i;
            size[i] = 1;
        }
    }

    public int count() {
        return this.count;
    }

    public int find(int p) {
        while(p != union[p])
            p = union[p];
        return p;
    }

    public boolean connected(int i, int j) {
        return find(i) == find(j);
    }

    public void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return;

        if(size[aRoot] < size[bRoot]) {
            union[aRoot] = bRoot;
            size[bRoot] += size[aRoot];
        } else {
            union[bRoot] = aRoot;
            size[aRoot] += size[bRoot];
        }
        count--;
    }
}
