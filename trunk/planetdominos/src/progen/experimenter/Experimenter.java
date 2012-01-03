package progen.experimenter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import progen.ProGenException;
import progen.context.ProGenContext;
import progen.kernel.error.Error;

/**
 * Interface que define los métodos de acceso a un experimento
 * @author jirsis
 * @since 2.0
 */
public abstract class Experimenter {
	
	/**
	 * Comprueba si se dan las condiciones para dar por 
	 * acabado el experimento.
	 * 
	 * @return <code>true</code> si se acabó y <code>false</code> 
	 * en caso contrario.
	 */
	public abstract boolean isDone();
	
	/**
	 * Define, en ProGenContext, el valor de la propiedad que se está
	 * procesando en un momento determinado.
	 * @see ProGenContext
	 */
	public abstract void defineValues();
	
	/**
	 * Incrementa el valor de las propiedades para generar un nuevo experimento 
	 * concreto. 
	 */
	public abstract void updateValues();
	
	/**
	 * Genera los ficheros de salida correspondiente al exeperimento que se haya ejecutado.
	 */
	public abstract void generateResults();
	
	/**
	 * Copia el fichero original definido como un path absoluto, en el path de destino, manteniendo el
	 * nombre del fichero original.
	 * @param original Path absoluto del fichero original a copiar.
	 * @param copyPath Path de destino de la copia.
	 */
	protected void copyFile(String original, String copyPath){
		BufferedReader reader;
		PrintWriter printer;
		File file = new File(original);
		String name = file.getName();
		String line;
		try {
			reader=new BufferedReader(new FileReader(original));
			printer = new PrintWriter(new BufferedWriter(new FileWriter(copyPath+File.separator+name)));
			
			line=reader.readLine();
			while(line!=null){
				printer.println(line);
				line=reader.readLine();
			}
			reader.close();
			printer.close();
		} catch (FileNotFoundException e) {
			throw new ProGenException(Error.get(31)+" ["+original+"]");
		} catch (IOException e) {
			throw new ProGenException(e.getLocalizedMessage());
		}
	}
	
	/**
	 * Función recursiva que elimina todo su contenido para poder eliminarla.
	 * 
	 * @param path
	 *            Ruta de la carpeta a borrar.
	 * @return <code>true</code> si se pudo eliminar el directorio y <code>false</code> en
	 * caso contrario.
	 */
	protected boolean deleteDirectory(File path) {
		boolean deleted=true;
		if (!path.exists()) {
			deleted=false;
		}else{
			for (File file : path.listFiles()) {
				if (file.isDirectory()) {
					deleteDirectory(file);
				} else {
					deleted=file.delete();
				}
			}
			deleted = path.delete() && deleted;
		}
		return deleted;
	}
}
