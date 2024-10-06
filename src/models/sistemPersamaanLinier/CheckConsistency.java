package src.models.sistemPersamaanLinier;

import src.datatypes.Matrix;

public class CheckConsistency {
    public int checkConsistency(Integer n, Integer m, Matrix matrix) {
        int flag = -1;
        // n = baris = persamaan
        // m = kolom = peubah + 1
        // we need peubah 
        for(int i = 0; i < Math.min(n,m-1); i++) {
            boolean isZero = true;
            for(int j = 0; j < Math.min(n,m-1); j++) {
                if(matrix.get(i, j) != 0) {
                    isZero = false;
                    break;
                }
            }

            if(isZero && matrix.get(i, Math.min(n,m-1)) != 0) {
                flag = 1; //tidak memiliki solusi
                break;
            }

            if(isZero && matrix.get(i, Math.min(n,m-1)) == 0) {
                flag = 0; //memiliki banyak solusi
            }

            //case matriks persegi panjang dengan banyak peubah > banyak persamaan
            if ((n < m-1)) {
                flag = 0; //memiliki banyak solusi
            }
        }
        return flag;
    }
}
