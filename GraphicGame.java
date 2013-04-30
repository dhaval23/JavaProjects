/**
 * CS 9053
 * Graphical Conways Game Of Life
 * Assuming new generation is generated when generate button is clicked everytime.
 * Dhaval Doshi(0490700)
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
*class GraphicGame
*contains the main class
*/
class GraphicGame
{
	/**
	*main()
	*/
	public static void main(String args[])
	{
		int row,col;
		if(args.length==2)
		{
			 row=Integer.parseInt(args[0]);
			 col=Integer.parseInt(args[1]);
			 GameGUI game=new GameGUI(row,col);
		}
		else if(args.length==1)
		{
			 row=Integer.parseInt(args[0]);
			 col=Integer.parseInt(args[0]);
			 GameGUI game=new GameGUI(row,col);
		}
		else if(args.length==0)
		{
			 row=20;
			 col=20;
			 GameGUI game=new GameGUI(row,col);
		}
		else 
			System.out.println("Invalid Arguements");
		
	}
}
/**
 * class MyFrame
 * contains a constructor that sets particular properties of the frame
 */
class MyFrame extends JFrame
{
	/**
	 * MyFrame()
	 * Constructor that sets the title and some other properties of the frame
	 */
	MyFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Conways Game Of Life");
	}
}
/**
 * class GameGUI
 * Contains a constructor that creates the GUI for the Game
 */
class GameGUI
{
	final JButton[][] button;
	static JButton[][] buttonArr;
	/**
	 * GameGUI()
	 * Constructor that creates the GUI using the rows and columns provided
	 * @param row
	 * @param col
	 */
	GameGUI(final int row,final int col)
	{
		
		MyFrame myj=new MyFrame();
		myj.setLayout(new BorderLayout());
		myj.setVisible(true);
		JPanel panel1=new JPanel();
		JButton gButton=new JButton("Generate");
		JButton clearButton=new JButton("Clear");
		panel1.add(gButton);
		panel1.add(clearButton);
		myj.add(panel1,BorderLayout.NORTH);
		
		JPanel panel2=new JPanel();
		panel2.setLayout(new GridLayout(row,col));
		myj.add(panel2,BorderLayout.CENTER);
		
		button=new JButton[row+2][col+2];
		for(int i=0; i<row+2; i++)
				{
					for(int j=0;j<col+2; j++)
					{
						button[i][j]=new JButton();
						button[i][j].setBackground(Color.magenta);
						button[i][j].setPreferredSize(new Dimension(20,20));
					}
				}
		for(int x=1;x<row+1;x++)
		        for(int y=1;y<col+1;y++)
		        {
					final int i=x,j=y;
		            panel2.add(button[x][y]);
					button[x][y].addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent e)
								{
									if(button[i][j].getBackground()==Color.magenta)
										button[i][j].setBackground(Color.black);
									else
										button[i][j].setBackground(Color.magenta);
								}
							});
					
		        }
		clearButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				for(int i=0; i<row; i++)
				{
					for(int j=0;j<col; j++)
					{
						button[i][j].setBackground(Color.magenta);
					}
				}
			}
		});
		gButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				gamePlay(button,row,col);
			}
		});
		myj.pack();
	}
	/**
	 * gamePlay()
	 * Contains the game logic 
	 * @param JButton[][]
	 * @param row
	 * @param col
	 */
	public void gamePlay(JButton[][] button,int row,int col)
	{
			buttonArr=new JButton[row+2][col+2];
			for(int i=0;i<row+2;i++)
			{
				for(int j=0;j<col+2;j++)
				{
					buttonArr[i][j]=new JButton();
					buttonArr[i][j].setBackground(Color.magenta);
				}
			}
			for(int i=1;i<row+1;i++)
			{
				for(int j=1;j<col+1;j++)
				{
					int neigbour=neighbourCheck(i,j);
					
					if ((neigbour == 2 || neigbour==3) && button[i][j].getBackground()==Color.black)
					{
						buttonArr[i][j]=button[i][j];
					}
					else if (neigbour < 2 || neigbour > 3)
					{
						buttonArr[i][j].setBackground(Color.magenta);
					}
					else if (neigbour == 3 && button[i][j].getBackground()==Color.magenta)
					{
						buttonArr[i][j].setBackground(Color.black);
					}

				}
			}
			for(int i=0;i<row+2;i++)
			{
				for(int j=0;j<col+2;j++)
				{
					if(buttonArr[i][j].getBackground()==Color.black)
					{
						button[i][j].setBackground(Color.black);
					}
					else
					{
						button[i][j].setBackground(Color.magenta);
					}
				}
			}
			
	}
	/**
	 * neighbourCkeck()
	 * checks the neighbour of the location provided by the parameters
	 * @param i,j
	 * @return neigbour
	 */
	public int neighbourCheck(int i,int j)
	{
		 int neigbour = 0;

                if (button[i - 1][j - 1].getBackground() == Color.black)
                {
                    neigbour++;
                }
                if (button[i - 1][j].getBackground() == Color.black)
                {
                    neigbour++;
                }
                if (button[i - 1][j + 1].getBackground() == Color.black)
                {
                    neigbour++;
                }
                if (button[i][j - 1].getBackground() == Color.black)
                {
                    neigbour++;
                }
                if (button[i][j + 1].getBackground() == Color.black)
                {
                    neigbour++;
                }
                if (button[i + 1][ j - 1].getBackground() == Color.black)
                {
                    neigbour++;
                }
                if (button[i + 1][j].getBackground() == Color.black)
                {
                    neigbour++;
                }
                if (button[i + 1][j + 1].getBackground() == Color.black)
                {
                    neigbour++;
                }
				return neigbour;
	}
}