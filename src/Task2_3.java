//import java.util.ArrayList;
//import java.util.List;
//
//public class Task2_3 {
//    public static void main(String[] args) {
//        int i=0;
//        int [] arrTask2 = {1,2,4,4,2,3,4,1,7};
//        int [] arrTask3 = {1,1,4,4,4,1,1,5};
//        int [] arrResult2 = new int[10];
//        List<Integer> arrRes2 = new ArrayList<Integer>();
//        task2 t2 = new task2();
//
//        //arrResult2 = t2(arrTask2);
//        t2(arrTask2);
////        while (i<arrResult2.length) {
////            System.out.println(arrResult2[i]);
////            i++;
////        }
////
////        System.out.println(arrResult2);
//
//    }
//
//    private static int[] t2(int[] arrTask2) throws RuntimeException {
//        int position = -2;
//        boolean ask = false;
//
//        for (int i = arrTask2.length - 1; i >= 0; i--) {
//            if (arrTask2[i] == 4) {
//                position = i;
//                ask = true;
//                break;
//            }
//        }
//
//        if (!ask) {
//            throw new RuntimeException("В массиве нету 4 !"); }
//        else {
//            int[] result = new int[arrTask2.length - position - 1];
//            for (int i = 0; i < result.length; i++) {
//                result[i] = arrTask2[i + position + 1];
//            }
//            if (ask){
//                for (int i = 0; i < result.length; i++) {
//                    System.out.println(result[i]);
//                }
//            }
////            return result;
//        }
//
//
//    }
//    }


