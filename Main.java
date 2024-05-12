public class Main {
    public static void main(String[] args) {
        GenerateData generator = new GenerateData(10000);

        //buildTime
        long startBuild = System.nanoTime();
        SegmentTree a = new SegmentTree(generator.getArray());
        System.out.println("BUILD Время: " + (System.nanoTime() - startBuild) + " Итерации: " + a.getBuildIterations());

        //SearchTime
        long allSearch = 0;
        long allIter = 0;
        for (int[] i : generator.generateForQuery(100)){
            long startSearch = System.nanoTime();
            a.query(i[0], i[1]);
            allSearch += System.nanoTime() - startSearch;
            allIter += a.getQueryIterations();
        }
        System.out.println("SEARCH Время: " + allSearch/100 + " Итерации: " + allIter/100);

        //Add
        long allAdd = 0;
        long allAddIter = 0;
        for (int i : generator.generateForAdd(1000)){
            long startSearch = System.nanoTime();
            a.add(i);
            allAdd += System.nanoTime() - startSearch;
            allAddIter += a.getBuildIterations();
        }

        System.out.println("ADD Время: " + allAdd/1000 + " Итерации: " + allAddIter/1000);

        //Update
        long allUpdate = 0;
        long allUpdateIter = 0;
        for (int[] i : generator.generateForUpdate(1000)){
            long startSearch = System.nanoTime();
            a.update(i[0], i[1]);
            allUpdate += System.nanoTime() - startSearch;
            allUpdateIter += a.getQueryIterations();
        }
        System.out.println("UPDATE Время: " + allUpdate/1000 + " Итерации: " + allUpdateIter/1000);

        //Remove
        try {
            long allRemove = 0;
            long allRemoveIter = 0;
            for (int i : generator.generateForRemove(1000)) {
                long startSearch = System.nanoTime();
                a.remove(i);
                allRemove += System.nanoTime() - startSearch;
                allRemoveIter += a.getBuildIterations();
            }
            System.out.println("REMOVE Время: " + allRemove / 1000 + " Итерации: " + allRemoveIter / 1000);
        } catch (Exception e){
            System.out.println("AHAHAHAHAHAHAHAHAHAH");
        }
    }
}
