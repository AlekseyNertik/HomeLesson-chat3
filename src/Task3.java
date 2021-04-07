public class Task3 {
    public static boolean task31 (int[] list) {
        int i = 0;
            while (i < list.length){
                if (list[i]!=1 && list[i]!=4) {return false; }
                i++;
            }
//        for (int j = 0; j < list.length; j++) {
//            if (list[j]!=1 && list[j]!=4) {return false; }
//        }
        return true;
    }
}
