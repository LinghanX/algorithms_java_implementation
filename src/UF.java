class UF {
    private int[] parents;
    private int[] rank;
    private int counts;

    public UF(int size) {
        this.parents = new int[size];
        this.rank = new int[size];
        counts = size;
        
        for (int i = 0; i < size; i++) {
            parents[i] = i;
            rank[i] = 0;
        }
    }

	private int find(int p) {
		while (p != parents[p]) {
			parents[p] = parents[parents[p]]; // this is to compress path;
			p = parents[p];
		}
		return p;
	}

	public void union(int p, int q) {
		int rootP = find(p);
		int rootQ = find(q);
		if (rootP == rootQ) return;

		if (rank[rootP] > rank[rootQ]) parents[rootQ] = rootP;
		else if (rank[rootP] < rank[rootQ]) parents[rootP] = rootQ;
		else {
			parents[rootP] = rootQ;
			rank[rootQ] ++;
		}
		counts--;
	}

	public boolean isConnected(int p, int q) {
		return find(p) == find(q);
	}
}
