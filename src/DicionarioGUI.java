/**
 * 
 */

/**
 * @author harlan
 *
 */





import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;




public class DicionarioGUI extends JFrame {
	
	
	private static final long serialVersionUID = 1L;
		
	//	Constantes
	
	//-------------------------------------------------------------
	
	//variaveis GUI
	

	//painel list
	private ArrayList <JPanel> panelList= new ArrayList <JPanel>();
		
	//Dimensions
	private Dimension dimensionFrame = new Dimension(1100,1000);
		
	private Dimension dimensionTA2 = new Dimension(1000,200);
	
	private Dimension dimensionLabel = new Dimension(100,20);
	
	private Dimension dimensionTF = new Dimension(250,20);
	
	private Dimension dimensionTextFieldSeparador = new Dimension(20,20);
	
	private Dimension dimensionBotoes = new Dimension(200,50);
	//---------------
	
	//Cores:
	// Azul da identidade visual
	private Color backColorAzul = new Color (2,92,117);
	// Amarelo da identidade visual
	private Color backColorAmarelo = new Color (248,212,141);
	//---------------
	
	//-Labels
	
	private JLabel labelModelo = new JLabel("modelo a ser traduzido");
	private ArrayList<JLabel> labelFonteList = new ArrayList<JLabel>();
	private ArrayList<JLabel> labelSeparadorList = new ArrayList<JLabel>();
	
			
	// text fields e botao filechooser 
	
	private ArrayList<JButton> botaoFCList = new ArrayList<JButton>();
	
	private ArrayList<JTextField> textFieldFonteList = new ArrayList<JTextField>();
	
	private ArrayList<JTextField> textFieldSeparadorList = new ArrayList<JTextField>();
	
	
	//text area
	private ArrayList<JTextArea> taList = new ArrayList<JTextArea>();
	
	//scroll pane
	private ArrayList<JScrollPane> scrollList = new ArrayList<JScrollPane>();
	
	//Buttons traduzir
	private ArrayList<JButton> botaoTraduzirList = new ArrayList<JButton>();
	
	
	//-------------------------------------------
	//construtor
	DicionarioGUI(){
		super("Tradutor");
		
		
		
		//criar 4 paineis
		for(int i = 0 ; i < 4; i++){
			
			JPanel p = new JPanel();
			p.setBackground(backColorAzul);
			p.setLayout(new FlowLayout(FlowLayout.CENTER));
			panelList.add(p);
		}
		
		//criar 4 text areas
		for(int i = 0 ; i < 4; i++){
			JTextArea ta = new JTextArea();
			ta.setText("");
			ta.setPreferredSize(dimensionTA2);
			ta.setMaximumSize(dimensionTA2);
			ta.setFont(new Font("courier", Font.PLAIN, 14));
			ta.setLineWrap(true);
			taList.add(ta);
		}
		
		//criar 4 scroll panes
		for(int i = 0 ; i < 4; i++){
			
			JScrollPane sp = new JScrollPane(taList.get(i));

			sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			//scrollModelo.setHorizontalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			sp.setPreferredSize(dimensionTA2);
			//sp.setMaximumSize(dimensionTA2);
			sp.setAlignmentX(CENTER_ALIGNMENT);
			scrollList.add(sp);
		}
		
		//editar labels
		
		labelModelo.setForeground(Color.white);
		
		//criar 3 fonte labels
		for(int i = 0 ; i < 3; i++){
			JLabel lf = new JLabel("Fonte do tradutor:");
			lf.setForeground(Color.white);
			lf.setPreferredSize(dimensionLabel);
			labelFonteList.add(lf);
		}
		
		//criar 3 separador labels
		for(int i = 0 ; i < 3; i++){
			JLabel ls = new JLabel("separador:", SwingConstants.RIGHT);
			ls.setPreferredSize(dimensionLabel);
			ls.setForeground(Color.white);
			labelSeparadorList.add(ls);
		}
		
		//criar 3 text fields para fonte
		for(int i = 0 ; i < 3; i++){
			JTextField tff = new JTextField();
			tff.setPreferredSize(dimensionTF);
			textFieldFonteList.add(tff);
		}
		
		//criar 3 botoes para chamar um filechooser
		for(int i = 0 ; i < 3; i++){
			JButton b = new JButton("Localizar");
			botaoFCList.add(b);
		
		}
		
		//definir acao dos botoes que chamam um filechooser
		
		botaoFCList.get(0).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
			    FileNameExtensionFilter filter = new FileNameExtensionFilter( "Fontes", "txt", "csv");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	textFieldFonteList.get(0).setText(chooser.getSelectedFile().getName());
			    }
			}
		});
		botaoFCList.get(1).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter( "Fontes", "txt", "csv");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	textFieldFonteList.get(1).setText(chooser.getSelectedFile().getName());
			    }
			}
		});
		botaoFCList.get(2).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter( "Fontes", "txt", "csv");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	textFieldFonteList.get(2).setText(chooser.getSelectedFile().getName());
			    }
			}
		});
		
		//criar 3 text fields para separador
		for(int i = 0 ; i < 3; i++){
			JTextField tfs = new JTextField();
			tfs.setPreferredSize(dimensionTextFieldSeparador);
			textFieldSeparadorList.add(tfs);
			
		}
		
		//criar 3 buttons para traduzir
		
		JButton b0 = new JButton("Traduzir");
		
		b0.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//HashMap<String,String> d = Tradutor.montarDicio(fName, separador);
				HashMap<String,String> d = Tradutor.montarDicio( textFieldFonteList.get(0).getText() ,
						textFieldSeparadorList.get(0).getText());
				
				//DicionarioGUI.taList.get(i - 1).getText()
				taList.get(1).setText(Tradutor.traduzir(taList.get(0).getText(), d));
			}
		});
		botaoTraduzirList.add(b0);
		
		JButton b1 = new JButton("Traduzir");
		
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//HashMap<String,String> d = Tradutor.montarDicio(fName, separador);
				HashMap<String,String> d = Tradutor.montarDicio( textFieldFonteList.get(1).getText() ,
						textFieldSeparadorList.get(1).getText());
				
				//DicionarioGUI.taList.get(i - 1).getText()
				taList.get(2).setText(Tradutor.traduzir(taList.get(1).getText(), d));
			}
		});
		botaoTraduzirList.add(b1);
		
		JButton b2 = new JButton("Traduzir");
		
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//HashMap<String,String> d = Tradutor.montarDicio(fName, separador);
				HashMap<String,String> d = Tradutor.montarDicio( textFieldFonteList.get(2).getText() ,
						textFieldSeparadorList.get(2).getText());
				
				//DicionarioGUI.taList.get(i - 1).getText()
				taList.get(3).setText(Tradutor.traduzir(taList.get(2).getText(), d));
			}
		});
		botaoTraduzirList.add(b2);
		
		
		//montar painel 0
		
		panelList.get(0).add(labelModelo);
		panelList.get(0).add(scrollList.get(0));
		
		//montar o resto dos paineis
		
		for(int i = 1 ; i < 4; i++){
			panelList.get(i).add(labelFonteList.get(i - 1));
			panelList.get(i).add(textFieldFonteList.get(i - 1));
			panelList.get(i).add(botaoFCList.get(i - 1));
			panelList.get(i).add(labelSeparadorList.get(i - 1));
			panelList.get(i).add(textFieldSeparadorList.get(i - 1));
			panelList.get(i).add(botaoTraduzirList.get(i - 1));
			panelList.get(i).add(scrollList.get(i));
		}
		
				
		//-------------------------------------------------------------------------------------------
		this.setSize(dimensionFrame);
		this.setLayout(new GridLayout(4,1));
		//this.add(panelCenter, BorderLayout.NORTH );
		for(int i = 0 ; i < 4; i++){
			this.add(panelList.get(i));
		}
		
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
		
	
	//M�TODO MAIN

	public static void main(String[] args) {
		
		System.out.println();
		DicionarioGUI d = new DicionarioGUI();
		
	}
	//--------------------------------------------------------------------
}
