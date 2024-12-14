

public class Matrices{
    public static int[][] matrizIdentidadNxN(int n){
        int[][] matrizIdentidad = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
		if(i == j){
                    matrizIdentidad[i][j] = 1;
		}
		else{
                    matrizIdentidad[i][j] = 0;
		}		
            }
	}
	return matrizIdentidad;
    }
	
    public static int[][] producto_Mx1_1xN(int[] A, int[] B){
	int M = A.length;
	int N = B.length;
	int [][] producto = new int[M][N];
	for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
		producto[i][j] = A[i] * B[j];
            }
	}
	return producto;
    }
	
    public static int[][] suma(int[][] A, int[][] B){
	int[] dimensionesA = {A.length,A[0].length};
	int[] dimensionesB = {B.length,B[0].length};
	if(dimensionesA[0] != dimensionesB[0] || dimensionesA[1] != dimensionesB[1]){
            throw new IllegalArgumentException("Las Dimensiones de las matrices no coinciden");
	}	
	int[][] suma = new int[dimensionesA[0]][dimensionesA[1]];
	for(int i = 0; i < dimensionesA[0]; i++){
            for(int j = 0; j < dimensionesA[1]; j++){
		suma[i][j] = A[i][j] + B[i][j];
            }
	}	
	return suma;
    }
	
    public static int[][] resta(int[][] A, int[][] B){
	int[] dimensionesA = {A.length,A[0].length};
	int[] dimensionesB = {B.length,B[0].length};
	if(dimensionesA[0] != dimensionesB[0] || dimensionesA[1] != dimensionesB[1]){
            throw new IllegalArgumentException("Las Dimensiones de las matrices no coinciden");
	}	
	int[][] resta = new int[dimensionesA[0]][dimensionesA[1]];
	for(int i = 0; i < dimensionesA[0]; i++){
            for(int j = 0; j < dimensionesA[1]; j++){
		resta[i][j] = A[i][j] - B[i][j];
            }
	}
	return resta;
    }

    public static int[] producto_1xN_NxN(int[] A, int[][] B){
	int[] dimensionesA = {1,A.length};
	int[] dimensionesB = {B.length,B[0].length};
	if(dimensionesA[1] != dimensionesB[0] || dimensionesB[0] != dimensionesB[1]){
            throw new IllegalArgumentException("Dimensiones no validas");
	}	
	int N = dimensionesA[1];
	int [] producto = new int[N];
	int suma = 0;
	for(int i = 0; i < N; i++){
            suma = 0;
            for(int j = 0; j < N; j++){
                suma += A[j] * B[i][j];
            }
            producto[i] = suma;
	}
	return producto;
    }
}

