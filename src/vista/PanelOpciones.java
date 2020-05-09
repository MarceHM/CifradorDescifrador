package vista;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/*
 * Clase que representa un panel con las opciones para cifrar o descifrar un archivo.
 */
public class PanelOpciones extends JPanel implements ActionListener{

	public final static String CIFRAR = "Cifrar un archivo";
	public final static String DESCIFRAR = "Descifrar un archivo";
	
	private JButton btnCifrar;
	private JButton btnDescifrar;
	private VentanaInicio principal;
	
	public PanelOpciones(VentanaInicio i) {
		// TODO Auto-generated constructor stub
		btnCifrar = new JButton(CIFRAR);
		btnCifrar.setActionCommand(CIFRAR);
		btnCifrar.addActionListener(this);
		btnCifrar.setEnabled(true);
		
		btnDescifrar = new JButton(DESCIFRAR);
		btnDescifrar.setActionCommand(DESCIFRAR);
		btnDescifrar.addActionListener(this);
		btnDescifrar.setEnabled(true);
		
		principal = i;
		
		setLayout(new GridLayout(1, 2, 5, 5));
		setBorder(new TitledBorder("Seleccione una opcion: "));
		
		add(btnCifrar);
		add(btnDescifrar);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand();
		
		if(comando.equals(CIFRAR)) {
			principal.cifrar();
			
		}else if(comando.equals(DESCIFRAR)) {
			principal.descifrar();
			
		}
		
	}
}
