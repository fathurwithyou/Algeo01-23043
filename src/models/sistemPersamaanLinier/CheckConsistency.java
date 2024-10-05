package src.models.sistemPersamaanLinier;

import src.datatypes.Matrix;

public class CheckConsistency {
    public int checkConsistency(Integer n, Integer m, Matrix matrix) {
        int flag = -1;
        for(int i = 0; i < n; i++) {
            boolean isZero = true;
            for(int j = 0; j < n; j++) {
                if(matrix.get(i, j) != 0) {
                    isZero = false;
                    break;
                }
            }

            if(isZero && matrix.get(i, m-1) != 0) {
                flag = 1;
                break;
            }
            if(isZero && matrix.get(i, m-1) == 0) {
                flag = 0;
            }
        }
        return flag;
    }
}
