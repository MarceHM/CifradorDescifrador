package vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PanelSeleccion extends JPanel implements ActionListener{
	
	public final static String RUTA = "Buscar";
	
	private JTextField txtRuta;
	private JTextField txtContra;
	private JLabel lbRuta;
	private JLabel lbContra;
	private JButton btnRuta;
	
	private VentanaInicio principal;
	
	public PanelSeleccion(VentanaInicio i) {
		
		principal = i;
		setLayout(new GridLayout(2,3,5,5));
		setBorder(new TitledBorder("Información"));
		
		lbRuta = new JLabel("Ruta del archivo: ");
		lbContra = new JLabel("Contraseña para cifrar/descifrar:");
		JLabel vacio1 = new JLabel("");
		
		btnRuta = new JButton(RUTA);
		btnRuta.setActionCommand(RUTA);
		btnRuta.addActionListener(this);
		btnRuta.setEnabled(true);
		
		txtRuta = new JTextField();
		txtRuta.setEditable(true);
		txtRuta.setEnabled(true);
		
		txtContra = new JTextField();
		txtContra.setEditable(true);
		txtContra.setEnabled(true);
		
		add(lbRuta);
		add(txtRuta);
		add(btnRuta);
		add(lbContra);
		add(txtContra);
		add(vacio1);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand();
		
		if(comando.equals(RUTA)) {
			File archivo = seleccionarArchivo();
			principal.setArchivo(archivo);
		}
		
	}
	
	public String getRuta() {
		return txtRuta.getText();
	}

	public String getContra() {
		return txtContra.getText();
	}
	
	public File seleccionarArchivo() {
		JFileChooser selectorArchivos = new JFileChooser();
		selectorArchivos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int seleccion = selectorArchivos.showOpenDialog(this);
		File archivo = selectorArchivos.getSelectedFile();
		
		
		
		if ((archivo == null) || (archivo.getName().equals(""))) {
			 JOptionPane.showMessageDialog(this, "Nombre de archivo inválido", "Nombre de archivo inválido", JOptionPane.ERROR_MESSAGE);
		}else {
			txtRuta.setText(archivo.getPath());
		}
		
		return archivo;
	}
	
}
