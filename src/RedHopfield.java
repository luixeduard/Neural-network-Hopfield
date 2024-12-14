import java.io.File;
import java.util.Arrays;

public class RedHopfield{
    private int[][] pesos;
    private int[][] entradas;
    private int neuronas;
    
    public RedHopfield(File[] patronesEntrenamiento){
	entradas = new int[patronesEntrenamiento.length][];
	for(int i = 0; i < entradas.length; i++){
            entradas[i] = FuncionesUtiles.obtenerEntrada(patronesEntrenamiento[i]);
        }
        
	neuronas = entradas[0].length;
	pesos = new int[neuronas][neuronas];
    }
	
    public void entrenar(){
        int M = entradas.length;
        int[][] simetrica = new int[neuronas][neuronas];
        int[][] matrizIdentidad = Matrices.matrizIdentidadNxN(neuronas);
        for(int i = 0; i < M; i++){
            int[] transpuesta = Arrays.copyOf(entradas[i], neuronas);
            simetrica = Matrices.producto_Mx1_1xN(transpuesta, entradas[i]);
            simetrica = Matrices.resta(simetrica, matrizIdentidad);
            pesos = Matrices.suma(pesos, simetrica);
        }
    }
	
    public int[] salida(int maxIteraciones){
        File file = FuncionesUtiles.seleccionarArchivo();
        int[] entrada = FuncionesUtiles.obtenerEntrada(file);
        int[] dimensiones = FuncionesUtiles.obtenerDimensionesImagen(file);
        int[] salida = Matrices.producto_1xN_NxN(entrada, pesos);
        int[] salidaAnterior = Arrays.copyOf(salida, salida.length);;
        int i = 1;
        for(; i < maxIteraciones; i++){
            salida = Matrices.producto_1xN_NxN(salida, pesos);
            salida = funcionEscalonAVector(salida);
            if(salidaIgualSalidaAnterior(salida,salidaAnterior)){
                break;
            }
            salidaAnterior = Arrays.copyOf(salida, salida.length);
        }
        if(i == maxIteraciones){
	System.out.println("Maximo numero de iteraciones alcanzado.");
        }
        return salida;
    }
	
    private int[] funcionEscalonAVector(int[] salida){
        int[] resultado = new int[salida.length];
	for(int i = 0; i < salida.length; i++){
            resultado[i] = funcionEscalon(salida[i]);
	}
	return resultado;
    }
	
    private int funcionEscalon(int s){
        if(s < 0){
            return -1;
	}
	else{
            return 1;
        }
    }

    private boolean salidaIgualSalidaAnterior(int[] salida, int[] salidaAnterior){
	for(int i = 0; i < salida.length; i++){
            if(salida[i] != salidaAnterior[i]){
		return false;
            }
	}
	return true;
    }
}
