import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PassGen {

	private JFrame frmPassgenerator;
	private JTextField usrField;
	private JTextField pswField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PassGen window = new PassGen();
					window.frmPassgenerator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Number of different combinations of anagram of a n letter word: n! (6! = 720)
	public static char[][] store = new char[720][6];
	
	public static int num;

	/**
	 * Create the application.
	 */
	public PassGen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPassgenerator = new JFrame();
		frmPassgenerator.setTitle("PassGenerator");
		frmPassgenerator.setBounds(100, 100, 389, 300);
		frmPassgenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPassgenerator.getContentPane().setLayout(null);
		
		usrField = new JTextField();
		usrField.setBounds(61, 66, 130, 26);
		frmPassgenerator.getContentPane().add(usrField);
		usrField.setColumns(10);
		usrField.requestFocus();
		
		JLabel lblNewLabel = new JLabel("Εισαγωγή username");
		lblNewLabel.setBounds(61, 38, 130, 16);
		frmPassgenerator.getContentPane().add(lblNewLabel);
		
		JButton btnCopy = new JButton("Αντιγραφή");
		btnCopy.setEnabled(false);
		btnCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringSelection stringSelection = new StringSelection (pswField.getText());
				Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
				clpbrd.setContents (stringSelection, null);
				usrField.setText("");
				pswField.setText("");
				usrField.requestFocus();
				btnCopy.setEnabled(false);
			}
		});
		btnCopy.setBounds(228, 199, 117, 29);
		frmPassgenerator.getContentPane().add(btnCopy);
		
		JButton btnPassword = new JButton("Δημιουργία password");
		
		btnPassword.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String val = usrField.getText();
				
				if (val.length() != 6)
				{
					JOptionPane.showMessageDialog(null, "Το username πρέπει να είναι 6 χαρακτήρες - Προσπάθησε ξανά.", "Σφάλμα!" ,JOptionPane.INFORMATION_MESSAGE);
					usrField.setText("");
					pswField.setText("");
					usrField.requestFocus();
					btnCopy.setEnabled(false);
				}
				else
				{
					btnCopy.setEnabled(true);
					char[] array = new char[6];
					for (int i = 0; i < 6; i++)
						array[i] = val.charAt(i);
	
					num = 0;
					makeAnagram(array, 0);
					
					Random rand = new Random();
					int  n = rand.nextInt(719);
	
					/* System.out.println("Random number: " + n + "\n");
	
					for (int i = 0; i < 6; i++)
						System.out.print(store[n][i]);
					 */
					String stringName = String.valueOf(store[n]);
					
					Random r = new Random();
					List<Character> l = Arrays.asList('!', '@', '#', '$');
					char c = l.get(r.nextInt(l.size()));
					// System.out.println("CharSymbol: " + c);
					
					String stringNameSymbol = stringName + c;
					
					Random rnd = new Random();
					List<Character> lst = Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9');
					char ch = lst.get(rnd.nextInt(lst.size()));
					// System.out.println("CharNumber: " + ch);
					
					String stringFinal = stringNameSymbol + ch;
	
					pswField.setText(stringFinal);
				}
			}
		});
		
		btnPassword.setBounds(45, 118, 167, 47);

		frmPassgenerator.getContentPane().add(btnPassword);
		
		pswField = new JTextField();
		pswField.setBounds(61, 199, 130, 26);
		frmPassgenerator.getContentPane().add(pswField);
		pswField.setColumns(10);
	}

static void makeAnagram(char[] a, int i)
{
	// System.out.println("MakeAnagram i = " + i);
	if (i == a.length-1)
	{
		storeArray(a);
		num++;
	}
	else
	{
		for (int j = i; j < a.length; j++)
		{
			//swap a[i] with a[j]
			char c = a[i];
			a[i] = a[j];
			a[j] = c;
			makeAnagram(a, i+1);
			//swap back
			c = a[i];
			a[i] = a[j];
			a[j] = c;
		}
	}
}

static void storeArray(char [] a) {

	for (int i = 0; i < a.length; i++)
	{
		// System.out.print(a[i]);
		store[num][i] = a[i];
	}
	// System.out.println();
}
}