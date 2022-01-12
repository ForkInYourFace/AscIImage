package calculatrice;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fenetre extends JFrame {
	private JButton bou0= new JButton("0");
	private JButton bou1= new JButton("1");
	private JButton bou2= new JButton("2");
	private JButton bou3= new JButton("3");
	private JButton bou4= new JButton("4");
	private JButton bou5= new JButton("5");
	private JButton bou6= new JButton("6");
	private JButton bou7= new JButton("7");
	private JButton bou8= new JButton("8");
	private JButton bou9= new JButton("9");
	
	private JButton bouEgal= new JButton("=");
	private JButton bouVir= new JButton(".");
	
	private JButton bouPlus= new JButton("+");
	private JButton bouMoins= new JButton("-");
	private JButton bouDiv= new JButton(":");
	private JButton bouFois= new JButton("X");
	private JButton bouCor= new JButton("AC");
	private JButton bouPui= new JButton("^");
	
	private JLabel label=new JLabel("|");
	
	private JPanel pave=new JPanel();
	private JPanel signe=new JPanel();
	private JPanel bas=new JPanel();
	private JPanel haut=new JPanel();
	private JPanel container=new JPanel();
	
	private double v1=0;
	private double v2=0;
	private String op="";
	
	private boolean op1=true;
	private boolean n1=true;
	private int vir=0;
	
	public Fenetre() {
		this.setTitle("Calculatrice");
		this.setSize(500,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setContentPane(container);
	    this.setResizable(false);

	    Toolkit tk = Toolkit.getDefaultToolkit();
	    Image img = tk.getImage("icone.png");
	    this.setIconImage(img);
		
	    GridLayout g1=new GridLayout(4,3);
	    g1.setHgap(5); //Cinq pixels d'espace entre les colonnes (H comme Horizontal)
	    g1.setVgap(5); //Cinq pixels d'espace entre les lignes (V comme Vertical) 
	    //Ou en abrégé : GridLayout gl = new GridLayout(3, 2, 5, 5);
	    
	    bouCor.setFont(new Font("Tahoma",10, 20));
	    bouPlus.setFont(new Font("Tahoma",10, 20));
	    bouMoins.setFont(new Font("Tahoma",10, 20));
	    bouDiv.setFont(new Font("Tahoma",10, 20));
	    bouFois.setFont(new Font("Tahoma",10, 20));
	    bouPui.setFont(new Font("Tahoma",10, 20));
	    bouEgal.setFont(new Font("Tahoma",10, 20));
	    bouVir.setFont(new Font("Tahoma",10, 20));
	    
	    bou0.setFont(new Font("Tahoma",10, 20));
	    bou1.setFont(new Font("Tahoma",10, 20));
	    bou2.setFont(new Font("Tahoma",10, 20));
	    bou3.setFont(new Font("Tahoma",10, 20));
	    bou4.setFont(new Font("Tahoma",10, 20));
	    bou5.setFont(new Font("Tahoma",10, 20));
	    bou6.setFont(new Font("Tahoma",10, 20));
	    bou7.setFont(new Font("Tahoma",10, 20));
	    bou8.setFont(new Font("Tahoma",10, 20));
	    bou9.setFont(new Font("Tahoma",10, 20));
	    
		pave.setLayout(g1);
		
		pave.add(bou1);
		pave.add(bou2);
		pave.add(bou3);
		pave.add(bou4);
		pave.add(bou5);
		pave.add(bou6);
		pave.add(bou7);
		pave.add(bou8);
		pave.add(bou9);
		pave.add(bouVir);
		pave.add(bou0);
		pave.add(bouEgal);
		
		signe.setLayout(new GridLayout(6,1,5,10));
		
		bouCor.setForeground(Color.RED);
		signe.add(bouCor);
		signe.add(bouPlus);
		signe.add(bouMoins);
		signe.add(bouFois);
		signe.add(bouDiv);
		signe.add(bouPui);
		
		bouPlus.addActionListener(new ListenerOp());
		bouMoins.addActionListener(new ListenerOp());
		bouFois.addActionListener(new ListenerOp());
		bouDiv.addActionListener(new ListenerOp());
		bouEgal.addActionListener(new ListenerOp());
		bouPui.addActionListener(new ListenerOp());
		
		bou0.addActionListener(new ListenerPave());
		bou1.addActionListener(new ListenerPave());
		bou2.addActionListener(new ListenerPave());
		bou3.addActionListener(new ListenerPave());
		bou4.addActionListener(new ListenerPave());
		bou5.addActionListener(new ListenerPave());
		bou6.addActionListener(new ListenerPave());
		bou7.addActionListener(new ListenerPave());
		bou8.addActionListener(new ListenerPave());
		bou9.addActionListener(new ListenerPave());
		bouCor.addActionListener(new ListenerPave());
		bouVir.addActionListener(new ListenerPave());
		
		pave.setBackground(Color.gray);
		signe.setBackground(Color.gray);
		
		bas.setLayout(new BoxLayout(bas, BoxLayout.LINE_AXIS));
		bas.add(pave);
		bas.add(signe);
		bas.setBackground(Color.white);
		
		label.setFont(new Font("Tahoma", 500, 40));
		label.setHorizontalTextPosition(JLabel.LEFT);
		
		haut.setBorder(BorderFactory.createLineBorder(Color.black));
		haut.setBackground(Color.yellow);
		haut.setLayout(new GridLayout(1,1, 5,10));
		haut.add(label);
		
		container.setLayout(new BoxLayout(container,BoxLayout.PAGE_AXIS));
		container.add(haut).setLocation(0,0);
		container.add(bas);
		
		this.setVisible(true);
	}
	
	class ListenerPave implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource()== bou0) {
				setLabel("0");
				if (n1) {
					v1=0;
					v2=0;
					vir=0;
					label.setText("|");
					n1=false;
				}
				if (op1) {
					if(vir==0) {
						v1*=10;
						v1+=0;
					}else {
						v1+=0/Math.pow(10, vir);
						vir++;
					}
				}else {
					if(vir==0) {
						v2*=10;
						v2+=0;
					}else {
						v2+=0/Math.pow(10, vir);
						vir++;
					}
				}
			} else if (arg0.getSource()== bou1) {
				if (n1) {
					v1=0;
					v2=0;
					vir=0;
					label.setText("|");
					n1=false;
				}
				setLabel("1");
				if (op1) {
					if(vir==0) {
						v1*=10;
						v1+=1;
					}else {
						v1+=1/Math.pow(10, vir);
						vir++;
					}
				}else {
					if(vir==0) {
						v2*=10;
						v2+=1;
					}else {
						v2+=1/Math.pow(10, vir);
						vir++;
					}
				}
			} else if (arg0.getSource()== bou2) {
				if (n1) {
					v1=0;
					v2=0;
					vir=0;
					label.setText("|");
					n1=false;
				}
				setLabel("2");
				if (op1) {
					if(vir==0) {
						v1*=10;
						v1+=2;
					}else {
						v1+=2/Math.pow(10, vir);
						vir++;
					}
				}else {
					if(vir==0) {
						v2*=10;
						v2+=2;
					}else {
						v2+=2/Math.pow(10, vir);
						vir++;
					}
				}
			} else if (arg0.getSource()== bou3) {
				if (n1) {
					v1=0;
					v2=0;
					vir=0;
					label.setText("|");
					n1=false;
				}
				setLabel("3");
				if (op1) {
					if(vir==0) {
						v1*=10;
						v1+=3;
					}else {
						v1+=3/Math.pow(10, vir);
						vir++;
					}
				}else {
					if(vir==0) {
						v2*=10;
						v2+=3;
					}else {
						v2+=3/Math.pow(10, vir);
						vir++;
					}
				}
			} else if (arg0.getSource()== bou4) {
				if (n1) {
					v1=0;
					v2=0;
					vir=0;
					label.setText("|");
					n1=false;
				}
				setLabel("4");
				if (op1) {
					if(vir==0) {
						v1*=10;
						v1+=4;
					}else {
						v1+=4/Math.pow(10, vir);
						vir++;
					}
				}else {
					if(vir==0) {
						v2*=10;
						v2+=4;
					}else {
						v2+=4/Math.pow(10, vir);
						vir++;
					}
				}
			} else if (arg0.getSource()== bou5) {
				if (n1) {
					v1=0;
					v2=0;
					vir=0;
					label.setText("|");
					n1=false;
				}
				setLabel("5");
				if (op1) {
					if(vir==0) {
						v1*=10;
						v1+=5;
					}else {
						v1+=5/Math.pow(10, vir);
						vir++;
					}
				}else {
					if(vir==0) {
						v2*=10;
						v2+=5;
					}else {
						v2+=5/Math.pow(10, vir);
						vir++;
					}
				}
			} else if (arg0.getSource()== bou6) {
				if (n1) {
					v1=0;
					v2=0;
					vir=0;
					label.setText("|");
					n1=false;
				}
				setLabel("6");
				if (op1) {
					if(vir==0) {
						v1*=10;
						v1+=6;
					}else {
						v1+=6/Math.pow(10, vir);
						vir++;
					}
				}else {
					if(vir==0) {
						v2*=10;
						v2+=6;
					}else {
						v2+=6/Math.pow(10, vir);
						vir++;
					}
				}
			} else if (arg0.getSource()== bou7) {
				if (n1) {
					v1=0;
					v2=0;
					vir=0;
					label.setText("|");
					n1=false;
				}
				setLabel("7");
				if (op1) {
					if(vir==0) {
						v1*=10;
						v1+=7;
					}else {
						v1+=7/Math.pow(10, vir);
						vir++;
					}
				}else {
					if(vir==0) {
						v2*=10;
						v2+=7;
					}else {
						v2+=7/Math.pow(10, vir);
						vir++;
					}
				}
			} else if (arg0.getSource()== bou8) {
				if (n1) {
					v1=0;
					v2=0;
					vir=0;
					label.setText("|");
					n1=false;
				}
				setLabel("8");
				if (op1) {
					if(vir==0) {
						v1*=10;
						v1+=8;
					}else {
						v1+=8/Math.pow(10, vir);
						vir++;
					}
				}else {
					if(vir==0) {
						v2*=10;
						v2+=8;
					}else {
						v2+=8/Math.pow(10, vir);
						vir++;
					}
				}
			} else if (arg0.getSource()== bou9) {
				if (n1) {
					v1=0;
					v2=0;
					vir=0;
					label.setText("|");
					n1=false;
				}
				setLabel("9");
				if (op1) {
					if(vir==0) {
						v1*=10;
						v1+=9;
					}else {
						v1+=9/Math.pow(10, vir);
						vir++;
					}
				}else {
					if(vir==0) {
						v2*=10;
						v2+=9;
					}else {
						v2+=9/Math.pow(10, vir);
						vir++;
					}
				}
			} else if (arg0.getSource()==bouVir) {
				if (n1) {
					v1=0;
					v2=0;
					label.setText("0");
					n1=false;
				}
				if(op1) {
					if (label.getText().indexOf(".")==-1) {
						if (v1==0) {
							label.setText("0.");
							vir++;
						}else {
							setLabel(".");
							vir++;
						}
					}else {
						String i=label.getText();
						label.setText("ERORR");
						label.setForeground(Color.red);
						container.repaint();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						label.setText(i);
						label.setForeground(Color.black);
						container.repaint();
					}
				}else {
					boolean tvir=true;
					for(int i=String.valueOf(v2).indexOf(".")+1;i<String.valueOf(v2).length(); i++) {
						if (!String.valueOf(v2).substring(i, i+1).equals("0")) {
							tvir=false;
							break;
						}
					}
					if (tvir) {
						if(v2==0) {
							setLabel("0.");
							vir++;
						}else {
							setLabel(".");
							vir++;
						}
					}else {
						String i=label.getText();
						label.setText("ERORR");
						label.setForeground(Color.red);
						container.repaint();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						label.setText(i);
						label.setForeground(Color.black);
						container.repaint();
					}
				}
			} else if (arg0.getSource()== bouCor) {
				label.setText("|");
				v1=0;
				v2=0;
				op="";
				op1=true;
				vir=0;
			}
		}
		
		private void setLabel(String add) {
			if(label.getText()!="|") {
				label.setText(label.getText()+add);
			}else {
				label.setText(add);
			}
		}
	}

	class ListenerOp implements ActionListener{
		public void actionPerformed(ActionEvent arg1) {
			if (arg1.getSource()==bouPlus) {
				n1=false;
				if (op1) {
					op1=false;
					op="+";
					setLabel("+");
					vir=0;
				}else {
					v1=equal();
					v2=0;
					label.setText(v1+"+");
					op="+";
					vir=0;
				}
			} else 	if (arg1.getSource()==bouMoins) {
				n1=false;
				if (op1) {
					op1=false;
					op="-";
					setLabel("-");
					vir=0;
				}else {
					v1=equal();
					v2=0;
					vir=0;
					label.setText(v1+"-");
					op="-";
				}
			} else if (arg1.getSource()==bouFois) {
				n1=false;
				if (op1) {
					op1=false;
					op="*";
					setLabel("x");
					vir=0;
				}else {
					v1=equal();
					v2=0;
					vir=0;
					label.setText(v1+"x");
					op="*";
				}
			}else if (arg1.getSource()==bouDiv) {
				n1=false;
				if (op1) {
					op1=false;
					op="/";
					vir=0;
					setLabel(":");
				}else {
					v1=equal();
					v2=0;
					vir=0;
					label.setText(v1+":");
					op="/";
				}
			}else if (arg1.getSource()==bouPui) {
				n1=false;
				if (op1) {
					op1=false;
					vir=0;
					op="^";
					setLabel("^");
				}else {
					v1=equal();
					v2=0;
					vir=0;
					label.setText(v1+"^");
					op="^";
				}
			} else {
				v1=equal();
				label.setText("= "+v1);
				v2=0;
				vir=0;
				op="";
				n1=true;
				op1=true;
			}
		}
		private void setLabel(String add) {
			if(label.getText()!="|") {
				label.setText(label.getText()+add);
			}else {
				label.setText(add);
			}
		}
		private double equal() {
			if (op=="") {
				return v1;
			} else if (op=="+"){
				return v1+v2;
			} else if (op=="-"){
				return v1-v2;
			} else if (op=="*"){
				return v1*v2;
			} else if (op=="/"){
				return v1/v2;
			} else if(op=="^"){
				return Math.pow(v1, v2);
			}else {
				return 0;
			}
		}
	}
}
