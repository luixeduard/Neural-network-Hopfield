
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FuncionesUtiles{
    public static int[] obtenerEntrada(File file){
	try {
            BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
            String line;
            ArrayList<Integer> listaEntrada = new ArrayList<>();
            int i = 0;
            while((line = br.readLine()) != null){
		if(i < 4){
                    i++;
		}
		else{
                    listaEntrada.add(Integer.valueOf(line));
		}
            }
            br.close();
            int[] entrada = new int[listaEntrada.size()];
            for(int j = 0; j < entrada.length; j++){
		entrada[j] = listaEntrada.get(j).intValue();
            }
            //Transformar los datos a 1 y -1
	for(int j = 0; j < entrada.length; j++){
            if(entrada[j] > 127){
		entrada[j] = 1;
            }
            else{
		entrada[j] = -1;
		}
            }
	return entrada;
	} catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
	}
    }

    public static File[] seleccionarArchivos(){
	JFileChooser fileChooser = new JFileChooser();
	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
	fileChooser.setMultiSelectionEnabled(true);
	FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos PGM","pgm");
	fileChooser.setFileFilter(filter);
	int result = fileChooser.showOpenDialog(null);
	if(result == JFileChooser.APPROVE_OPTION){
            File[] archivosSeleccionados = fileChooser.getSelectedFiles();
            return archivosSeleccionados;
	}
	else{
            return null;
	}
    }

    public static File seleccionarArchivo(){
	JFileChooser fileChooser = new JFileChooser();
	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
	FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos PGM","pgm");
	fileChooser.setFileFilter(filter);
	int result = fileChooser.showOpenDialog(null);
	if(result == JFileChooser.APPROVE_OPTION){
            File archivoSeleccionado = fileChooser.getSelectedFile();
            System.out.println("Archivos seleccionados:");
            System.out.println(archivoSeleccionado.getName());
            System.out.println();
            return archivoSeleccionado;
	}
	else{
            System.out.println("Favor de seleccionar un archivo PGM.");
            return null;
	}
    }
	
    public static int[] obtenerDimensionesImagen(File file){
	int [] dimensiones = null;
	try {
            BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
            String line;
            int i = 0;
            while((line = br.readLine()) != null){
		if(i == 2){
                    String[] dimStrg = line.split(" ");
                    dimensiones = new int[dimStrg.length];
                    dimensiones[0] = Integer.valueOf(dimStrg[0]);
                    dimensiones[1] = Integer.valueOf(dimStrg[1]);
                    break;
		}
		else{
                    i++;
		}
            }
            br.close();
	} catch (FileNotFoundException e) {
            e.printStackTrace();
	} catch (NumberFormatException e) {
            e.printStackTrace();
	} catch (IOException e) {
            e.printStackTrace();
	}
	return dimensiones;
    }
}
