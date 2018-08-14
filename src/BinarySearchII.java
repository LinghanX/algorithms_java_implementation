class BinarySearchII {
	// this is a template for searching
	// in a range;
	int start;
	int end;
	// first, define the possible realm that 
	// an answer could be.

	while (start + 1 < end) {
		int mid = start + (end - start) / 2;

		if (meetsCondition(mid))
			end = mid;
		else start = mid;
	}

	if (meetsCondition(end))
		return end;

	return start;
}
