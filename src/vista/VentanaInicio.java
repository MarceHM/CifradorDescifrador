package vista;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class VentanaInicio extends JFrame{
	
	private PanelOpciones panelOpciones;
	private PanelSeleccion panelSeleccion;
	private File archivo;
	
	public VentanaInicio() {
		
		setLayout(new BorderLayout());
		setTitle("Cifrador y descifrador de archivos");

		panelOpciones = new PanelOpciones(this);
		panelSeleccion = new PanelSeleccion(this);
		
		add(panelSeleccion, BorderLayout.CENTER);
		add(panelOpciones, BorderLayout.SOUTH);
		
		pack();
		
	}
	
	public void setArchivo(File f) {
		archivo=f;
	}
	
	public void cifrar() {
		String ruta = panelSeleccion.getRuta();
		String contra = panelSeleccion.getContra();
		
		if(ruta!=null && contra!=null && !ruta.equals("") && !contra.equals("")) {
			
			if(archivo!=null) {
				//Cifrar el archivo
				
			}
			
			
		}else if(ruta==null || ruta.equals("")) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un archivo", "Error", JOptionPane.ERROR_MESSAGE);
			
		}else if(contra==null || contra.equals("")) {
			JOptionPane.showMessageDialog(null, "Debe elegir una contraseña", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void descifrar() {
		String ruta = panelSeleccion.getRuta();
		String contra = panelSeleccion.getContra();
		
		if(ruta!=null && contra!=null && !ruta.equals("") && !contra.equals("")) {
			
			if(archivo!=null) {
				//Descifrar el archivo
				
			}
			
		}else if(ruta==null || ruta.equals("")) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un archivo", "Error", JOptionPane.ERROR_MESSAGE);
			
		}else if(contra==null || contra.equals("")) {
			JOptionPane.showMessageDialog(null, "Debe elegir una contraseña", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
