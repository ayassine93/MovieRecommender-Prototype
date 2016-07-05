import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JRadioButton;

import java.awt.Color;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.*;


public class MovieRecommenderGUI {

	private JFrame frmMovieRecommenderV;

	private Scanner reader;
	private String[] MovieArray = new String[1001];
	public int counter = 0;
	
	private ButtonGroup U1group;
	private ButtonGroup U2group;
	
	Map<String, Integer> U1Ratings = new HashMap<String, Integer>();
	Map<String, Integer> U2Ratings = new HashMap<String, Integer>();
	
	private String CurrentMovieTitle;
	
	JScrollPane U1scrollpane;
	String[] U1displayRatings = new String [1001];
	JList U1RatingsList;
	JPanel U1panel;
	
	JScrollPane U2scrollpane;
	String[] U2displayRatings = new String [1001];
	JList U2RatingsList;
	JPanel U2panel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovieRecommenderGUI window = new MovieRecommenderGUI();
					window.openfile();
					window.readfile();
					window.closefile();
					window.frmMovieRecommenderV.setVisible(true);
					//window.printRatingsU1();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public MovieRecommenderGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMovieRecommenderV =  new JFrame();
		frmMovieRecommenderV.setTitle("Movie Recommender V1.0");
		frmMovieRecommenderV.setBounds(100, 100, 647, 542);
		frmMovieRecommenderV.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMovieRecommenderV.getContentPane().setLayout(null);
		
		JLabel lblUser = new JLabel("User 1 Rating:");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUser.setBounds(84, 162, 86, 14);
		frmMovieRecommenderV.getContentPane().add(lblUser);
		
		JRadioButton U1radioButton_1 = new JRadioButton("1");
		U1radioButton_1.setBounds(123, 179, 47, 23);
		frmMovieRecommenderV.getContentPane().add(U1radioButton_1);
		
		JRadioButton U1radioButton_2 = new JRadioButton("2");
		U1radioButton_2.setBounds(123, 205, 47, 23);
		frmMovieRecommenderV.getContentPane().add(U1radioButton_2);
		
		JRadioButton U1radioButton_3 = new JRadioButton("3");
		U1radioButton_3.setBounds(123, 231, 47, 23);
		frmMovieRecommenderV.getContentPane().add(U1radioButton_3);
		
		JRadioButton U1radioButton_4 = new JRadioButton("4");
		U1radioButton_4.setBounds(123, 257, 47, 23);
		frmMovieRecommenderV.getContentPane().add(U1radioButton_4);
		
		JRadioButton U1radioButton_5 = new JRadioButton("5");
		U1radioButton_5.setBounds(123, 283, 47, 23);
		frmMovieRecommenderV.getContentPane().add(U1radioButton_5);
		
		JLabel lblUserRating = new JLabel("User 2 Rating:");
		lblUserRating.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUserRating.setBounds(413, 162, 86, 14);
		frmMovieRecommenderV.getContentPane().add(lblUserRating);
		
		JRadioButton U2radioButton_1 = new JRadioButton("1");
		U2radioButton_1.setBounds(456, 179, 47, 23);
		frmMovieRecommenderV.getContentPane().add(U2radioButton_1);
		
		JRadioButton U2radioButton_2 = new JRadioButton("2");
		U2radioButton_2.setBounds(456, 205, 47, 23);
		frmMovieRecommenderV.getContentPane().add(U2radioButton_2);
		
		JRadioButton U2radioButton_3 = new JRadioButton("3");
		U2radioButton_3.setBounds(456, 231, 47, 23);
		frmMovieRecommenderV.getContentPane().add(U2radioButton_3);
		
		JRadioButton U2radioButton_4 = new JRadioButton("4");
		U2radioButton_4.setBounds(456, 257, 47, 23);
		frmMovieRecommenderV.getContentPane().add(U2radioButton_4);
		
		JRadioButton U2radioButton_5 = new JRadioButton("5");
		U2radioButton_5.setBounds(456, 283, 47, 23);
		frmMovieRecommenderV.getContentPane().add(U2radioButton_5);
		
		U1group = new ButtonGroup();
		U2group = new ButtonGroup();
		
		/* User 1 Rating buttons*/
		U1group.add(U1radioButton_1);
		U1group.add(U1radioButton_2);
		U1group.add(U1radioButton_3);
		U1group.add(U1radioButton_4);
		U1group.add(U1radioButton_5);
		
		/* User 2 Rating buttons*/
		U2group.add(U2radioButton_1);
		U2group.add(U2radioButton_2);
		U2group.add(U2radioButton_3);
		U2group.add(U2radioButton_4);
		U2group.add(U2radioButton_5);
		
		JLabel PlainMovielbl = new JLabel("Movie Title:");
		PlainMovielbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		PlainMovielbl.setBounds(10, 11, 112, 23);
		frmMovieRecommenderV.getContentPane().add(PlainMovielbl);
		
		JLabel MovieTitlelbl = new JLabel("Click Next or Previous to begin.");
		MovieTitlelbl.setToolTipText("");
		MovieTitlelbl.setForeground(Color.BLUE);
		MovieTitlelbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		MovieTitlelbl.setBounds(10, 42, 611, 23);
		frmMovieRecommenderV.getContentPane().add(MovieTitlelbl);
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (counter > 0)
				{
					counter--;
					MovieTitlelbl.setText(MovieArray[counter]);
				}
				else
				{
					counter = 1001;
					MovieTitlelbl.setText(MovieArray[counter]);
				}
				
			}
		});
		btnPrevious.setBounds(97, 76, 104, 30);
		frmMovieRecommenderV.getContentPane().add(btnPrevious);
		
		
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (counter < 1001) 
				{
					MovieTitlelbl.setText(MovieArray[counter]);
					counter++;
				}
				else
				{
					counter = 0;
					MovieTitlelbl.setText(MovieArray[counter]);
				}
			}
		});
		btnNext.setBounds(261, 76, 104, 30);
		frmMovieRecommenderV.getContentPane().add(btnNext);
		
		JButton U1btnSubmitRating = new JButton("Submit Rating");
		U1btnSubmitRating.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CurrentMovieTitle = MovieTitlelbl.getText();
				if (U1Ratings.containsKey(CurrentMovieTitle)) // if already rated, replace current rating
				{
					U1Ratings.remove(CurrentMovieTitle);
				}
				if (U1radioButton_1.isSelected() == true)
				{
					U1Ratings.put(CurrentMovieTitle, 1);
				}
				else if (U1radioButton_2.isSelected() == true)
				{
					U1Ratings.put(CurrentMovieTitle, 2);
				}
				else if (U1radioButton_3.isSelected() == true)
				{
					U1Ratings.put(CurrentMovieTitle, 3);
				}
				else if (U1radioButton_4.isSelected() == true)
				{
					U1Ratings.put(CurrentMovieTitle, 4);
				}
				else if (U1radioButton_5.isSelected() == true)
				{
					U1Ratings.put(CurrentMovieTitle, 5);
				}
				
			}
		});
		U1btnSubmitRating.setBounds(56, 313, 145, 37);
		frmMovieRecommenderV.getContentPane().add(U1btnSubmitRating);
		
		JButton U2btnSubmitRating = new JButton("Submit Rating");
		U2btnSubmitRating.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CurrentMovieTitle = MovieTitlelbl.getText();
				if (U2Ratings.containsKey(CurrentMovieTitle)) // if already rated, replace current rating
				{
					U2Ratings.remove(CurrentMovieTitle);
				}
				if (U2radioButton_1.isSelected() == true)
				{
					U2Ratings.put(CurrentMovieTitle, 1);
				}
				else if (U2radioButton_2.isSelected() == true)
				{
					U2Ratings.put(CurrentMovieTitle, 2);
				}
				else if (U2radioButton_3.isSelected() == true)
				{
					U2Ratings.put(CurrentMovieTitle, 3);
				}
				else if (U2radioButton_4.isSelected() == true)
				{
					U2Ratings.put(CurrentMovieTitle, 4);
				}
				else if (U2radioButton_5.isSelected() == true)
				{
					U2Ratings.put(CurrentMovieTitle, 5);
				}
			}
		});
		U2btnSubmitRating.setBounds(413, 313, 145, 37);
		frmMovieRecommenderV.getContentPane().add(U2btnSubmitRating);
		
		JButton U1Checkbutton = new JButton("Check Rating");
		U1Checkbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				U1scrollpane = new JScrollPane();
				int i = 0;
				
				for (Map.Entry<String, Integer> entry : U1Ratings.entrySet())
				{
					String key = entry.getKey();
				    Integer value = entry.getValue();
				    //System.out.println(key + "    " + value);
				    U1displayRatings[i] = "- Movie: " + key + " ----- " + "Rating: " + value + "/5" + "\r\n";
				    i++;
				}
				
				JList U1RatingsList = new JList(U1displayRatings);
				U1scrollpane = new JScrollPane(U1RatingsList);
				
				U1panel = new JPanel();
				U1panel.add(U1scrollpane);
				 U1scrollpane.setViewportView(U1RatingsList);
				 
				 JOptionPane.showMessageDialog(null, U1scrollpane, "User 1 Movie Ratings",  
                         JOptionPane.PLAIN_MESSAGE);
				
				
			}
		});
		U1Checkbutton.setBounds(56, 363, 145, 37);
		frmMovieRecommenderV.getContentPane().add(U1Checkbutton);
		
		JButton U2Checkbutton = new JButton("Check Rating");
		U2Checkbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				U2scrollpane = new JScrollPane();
				int i = 0;
				
				for (Map.Entry<String, Integer> entry : U2Ratings.entrySet())
				{
					String key = entry.getKey();
				    Integer value = entry.getValue();
				    //System.out.println(key + "    " + value);
				    U2displayRatings[i] = "- Movie: " + key + " ----- " + "Rating: " + value + "/5" + "\r\n";
				    i++;
				}
				
				JList U2RatingsList = new JList(U2displayRatings);
				U2scrollpane = new JScrollPane(U2RatingsList);
				
				U2panel = new JPanel();
				U2panel.add(U2scrollpane);
				 U2scrollpane.setViewportView(U2RatingsList);
				 
				 JOptionPane.showMessageDialog(null, U2scrollpane, "User 2 Movie Ratings",  
                         JOptionPane.PLAIN_MESSAGE);
			}
		});
		U2Checkbutton.setBounds(413, 361, 145, 37);
		frmMovieRecommenderV.getContentPane().add(U2Checkbutton);
		
		JButton U1Recommendation = new JButton("Run Recommendation");
		U1Recommendation.setForeground(Color.BLUE);
		U1Recommendation.setFont(new Font("Tahoma", Font.BOLD, 11));
		U1Recommendation.setBounds(21, 411, 219, 37);
		frmMovieRecommenderV.getContentPane().add(U1Recommendation);
		
		JButton button = new JButton("Run Recommendation");
		button.setForeground(Color.BLUE);
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBounds(375, 409, 219, 37);
		frmMovieRecommenderV.getContentPane().add(button);
	}
	
	public void openfile()
	{
		try{
			reader = new Scanner(new File("C:\\Users\\Ahmad\\Desktop\\Last Semester\\CPS842\\Project\\ListOfMovies.txt"));
		}
		catch (Exception e){
			System.out.println("Could not open file.");
		}
	}
	
	public void readfile()
	{
		int i = 0;
		while (reader.hasNext())
		{
			MovieArray[i] = reader.nextLine();
			i++;
		}
	}
	
	public void closefile()
	{
		reader.close();
	}
	
	public void printRatingsU1()
	{
		for (Map.Entry<String, Integer> entry : U1Ratings.entrySet())
		{
			String key = entry.getKey();
		    Integer value = entry.getValue();
		    System.out.println(key + "    " + value);
		}
	}

}
