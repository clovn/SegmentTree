import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GenerateData {
    private int[] data;

    public GenerateData(int size){
        data = new int[size];
        Random random = new Random();
        for(int i = 0; i < size; i++){
            data[i] = random.nextInt(Integer.MAX_VALUE);
        }
    }

    public Set<Integer> generateForRemove(int count){
        Set<Integer> removed  =new HashSet<>();
        Random random = new Random();

        while (removed.size() < count){
            removed.add(data[random.nextInt(data.length)]);
        }

        return removed;
    }

    public int[] generateForAdd(int count){
        Random random = new Random();
        int[] add = new int[count];

        for (int i = 0; i < count; i++){
            add[i] = random.nextInt(Integer.MAX_VALUE);
        }

        return add;
    }

    public int[][] generateForUpdate(int count){
        Random random = new Random();
        int[][] update = new int[count][2];

        for (int i = 0; i < count; i++){
            update[i][0] = random.nextInt(data.length);
            update[i][1] = random.nextInt(Integer.MAX_VALUE);
        }

        return update;
    }

    public int[][] generateForQuery(int count){
        Random random = new Random();
        int[][] update = new int[count][2];

        for (int i = 0; i < count; i++){
            update[i][0] = random.nextInt(data.length);
            update[i][1] = random.nextInt(data.length - update[i][0]) + update[i][0];
        }

        return update;
    }

    public int[] getArray() {
        return data;
    }
}
