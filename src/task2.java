public class task2 {

    public static int[] t2(int[] arrTask2) throws RuntimeException {
        int position = -2;
        boolean ask = false;

        for (int i = arrTask2.length - 1; i >= 0; i--) {
            if (arrTask2[i] == 4) {
                position = i;
                ask = true;
                break;
            }
        }

        if (!ask) {
            throw new RuntimeException("В массиве нету 4 !"); }
        else {
            int[] result = new int[arrTask2.length - position - 1];
            for (int i = 0; i < result.length; i++) {
                result[i] = arrTask2[i + position + 1];
            }
//            if (ask){
//                for (int i = 0; i < result.length; i++) {
//                    System.out.println(result[i]);
//                }
//            }
            return result;
        }

    }
}
