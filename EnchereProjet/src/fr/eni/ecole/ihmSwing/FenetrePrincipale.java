package fr.eni.ecole.ihmSwing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.eni.ecole.BO.Adresse;
import fr.eni.ecole.BO.Role;
import fr.eni.ecole.BO.Utilisateur;
import fr.eni.ecole.controler.CtrlUtilisateur;

public class FenetrePrincipale extends JFrame 
{
	JLabel lblNom,lblPrenom,lblMail,lblPassword,lblNumero,lblRue,lblCodePostal,lblVille;
	JTextField txtNom,txtPrenom,txtMail,txtNumero,txtRue,txtCodePostal,txtVille;
	JPasswordField txtPassword;
	JPanel fond,pnlButton;
	JButton btnPrecedent,btnSuivant,btnPremier,btnDernier,btnAjouter,btnEnregistrer,btnSupprimer;
	CtrlUtilisateur ctrl;
	JComboBox cboRole;
	
	class ComboRoleModel extends DefaultComboBoxModel<Role> {
	    public ComboRoleModel(Role[] items) {
	        super(items);
	    }
	 
	    @Override
	    public Role getSelectedItem() {
	        Role selectedRole = (Role) super.getSelectedItem();
	        return selectedRole;
	    }
	}
	
	public FenetrePrincipale()
	{
		setSize(500,800);
		setTitle("les utilisateurs");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ctrl=new CtrlUtilisateur(this);
		initIhm();
		
	}

	private void initIhm() 
	{
		lblNom=new JLabel("nom");
		lblPrenom=new JLabel("prenom");
		lblMail=new JLabel("email");
		lblPassword=new JLabel("mot de passe");
		lblNumero=new JLabel("numero");
		lblRue=new JLabel("rue");
		lblCodePostal=new JLabel("code Postal");
		lblVille=new JLabel("ville");
		txtNom=new JTextField(30);
		txtPrenom=new JTextField(30);
		txtMail=new JTextField(30);
		txtPassword=new JPasswordField(30);
		txtNumero=new JTextField(3);
		txtRue=new JTextField(30);
		txtCodePostal=new JTextField(5);
		txtVille=new JTextField(15);
		btnPrecedent=new JButton("<");
		cboRole=new JComboBox(new ComboRoleModel((ctrl.getListeRole().toArray(new Role[0]))));
		JPanel p1;
		JPanel p2;
		btnPrecedent.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ctrl.afficherPrecedent();
			}
		});
		btnSuivant=new JButton(">");
		btnSuivant.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ctrl.afficherSuivant();
			}
		});
		btnPremier=new JButton("|<");
		btnPremier.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ctrl.afficherPremier();
			}
		});
		btnDernier=new JButton(">|");
		btnDernier.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ctrl.afficherDernier();
			}
		});
		btnSupprimer=new JButton("effacer");
		btnSupprimer.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ctrl.supprimer();// TODO Auto-generated method stub
			}
		});
		btnAjouter=new JButton("ajouter");
		btnAjouter.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ctrl.ajouter();
			}
		});
		btnEnregistrer=new JButton("enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ctrl.enregistrer();
			}
		});
		fond=new JPanel();
		fond.setLayout(new GridBagLayout());
		GridBagConstraints gbc;
		gbc=new GridBagConstraints();
		gbc.insets=(new Insets(5,5,5,5));
		gbc.gridx=0;
		gbc.gridy=0;
		fond.add(lblNom,gbc);
		gbc.gridx=1;
		gbc.gridy=0;
		fond.add(txtNom,gbc);
		gbc.gridx=0;
		gbc.gridy=1;
		fond.add(lblPrenom,gbc);
		gbc.gridx=1;
		gbc.gridy=1;
		fond.add(txtPrenom,gbc);
		gbc.gridx=0;
		gbc.gridy=2;
		fond.add(lblMail,gbc);
		gbc.gridx=1;
		gbc.gridy=2;
		fond.add(txtMail,gbc);
		gbc.gridx=0;
		gbc.gridy=3;
		fond.add(lblPassword,gbc);
		gbc.gridx=1;
		gbc.gridy=3;
		fond.add(txtPassword,gbc);
		p1=new JPanel();
		p1.add(lblNumero);
		p1.add(txtNumero);
		p1.add(lblRue);
		p1.add(txtRue);
		gbc.gridx=0;
		gbc.gridy=4;
		gbc.gridwidth=2;
		fond.add(p1,gbc);
		
		p2=new JPanel();
		p2.add(lblCodePostal);
		p2.add(txtCodePostal);
		p2.add(lblVille);
		p2.add(txtVille);
		gbc.gridx=0;
		gbc.gridy=5;
		gbc.gridwidth=2;
		fond.add(p2,gbc);
		gbc.gridx=0;
		gbc.gridy=6;
		fond.add(cboRole,gbc);
		
		getContentPane().add(fond);
		pnlButton=new JPanel();
		pnlButton.add(btnPremier);
		pnlButton.add(btnPrecedent);
		pnlButton.add(btnSuivant);
		pnlButton.add(btnDernier);
		pnlButton.add(btnAjouter);
		pnlButton.add(btnEnregistrer);
		pnlButton.add(btnSupprimer);
		gbc.gridx=0;
		gbc.gridy=7;
		gbc.gridwidth=2;
		fond.add(pnlButton,gbc);
		ctrl.afficherPremier();
	}
	
	public void afficherUtilisateur(Utilisateur u)
	{
		txtNom.setText(u.getNom());
		txtPrenom.setText(u.getPrenom());
		txtMail.setText(u.getEmail());
		txtPassword.setText(u.getPassword());
		if (u.getAdresse()!=null)
		{
			txtNumero.setText(String.valueOf(u.getAdresse().getNumero()));
			txtRue.setText(u.getAdresse().getRue());
			txtCodePostal.setText(String.valueOf(u.getAdresse().getCodePostal()));
			txtVille.setText(u.getAdresse().getVille());
		}
	}
	
	public Utilisateur getFromSaisie()
	{
		Utilisateur u;
		Adresse a;
		u=new Utilisateur();
		u.setNom(txtNom.getText());
		u.setPrenom(txtPrenom.getText());
		u.setEmail(txtMail.getText());
		try 
		{
			u.setPassword(new String(txtPassword.getPassword()));
			a=new Adresse(Integer.parseInt(txtNumero.getText()), txtRue.getText(), Integer.parseInt(txtCodePostal.getText()), txtVille.getText());
			u.setAdresse(a);
			u.setRole((Role)cboRole.getSelectedItem());
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u;
	}

	public void raz() 
	{
		txtNom.setText("");
		txtPrenom.setText("");
		txtMail.setText("");
		txtPassword.setText("");
		txtNumero.setText("");
		txtRue.setText("");
		txtCodePostal.setText("");
		txtVille.setText("");
		
	}
}
