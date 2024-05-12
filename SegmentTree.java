import java.util.Arrays;

public class SegmentTree {
    private int[] tree;
    private int[] data;
    private int size;

    private int buildIterations = 0;
    private int queryIterations = 0;
    private int updateIterations = 0;

    public SegmentTree(int[] data){
        this.data = data;
        size = data.length;
        tree = new int[4*size];
        build(0, 0, size - 1);
    }

    private void build(int i, int start, int end){
        buildIterations++;
        if(start == end) {
            tree[i] = data[start];
            return;
        }
        int middle = (start + end) / 2;
        int left = 2*i + 1;
        int right = 2*i + 2;
        build(left, start, middle);
        build(right, middle + 1, end);
        tree[i] = tree[left] + tree[right];
    }

    public int getUpdateIterations() {
        return updateIterations;
    }

    public void update(int index, int value){
        updateIterations = 0;
        update(0, 0, size-1, index, value);
    }

    private void update(int i, int start, int end, int index, int value){
        updateIterations++;
        if(start == end){
            tree[i] = value;
            return;
        }
        int middle = (start + end) / 2;
        int left = 2*i + 1;
        int right = 2*i + 2;
        if(index >= start && index <= middle) update(left, start, middle, index, value);
        else update(right, middle + 1, end, index, value);
        tree[i] = tree[left] + tree[right];
    }

    public int query(int left, int right){
        queryIterations = 0;
        return query(0, 0, size - 1, left, right);
    }

    private int query(int i, int start, int end, int left, int right){
        queryIterations++;
        if(right < start || left > end) return 0;
        if(left <= start && right >= end) return tree[i];
        int middle = (start + end) / 2;
        int leftSum = query(2*i + 1, start, middle, left, right);
        int rightSum = query(2*i + 2, middle + 1, end, left, right);
        return leftSum + rightSum;
    }

    public void add(int value){
        size++;
        tree = new int[size*4];
        data = Arrays.copyOf(data, size);
        data[size - 1] = value;
        buildIterations = 0;
        build(0, 0, size - 1);
    }

    public void remove(int value){
        int[] tmp = new int[size - 1];
        int j = 0;
        for (int i = 0; i < size; i++){
            if (value != data[i]) tmp[j++] = data[i];
        }

        size--;
        data = tmp;
        tree = new  int[4*size];
        buildIterations = 0;
        build(0, 0, size - 1);
    }

    public int getBuildIterations() {
        return buildIterations;
    }

    public int getQueryIterations() {
        return queryIterations;
    }

    @Override
    public String toString() {
        return "SegmentTree{" +
                "tree=" + Arrays.toString(tree) +
                '}';
    }
}
