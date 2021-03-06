package loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Loader {
	
	private double[] Y1;
	private double[] Y2;
	private double[][] X;
	
	private double[] Y1test;
	private double[] Y2test;
	private double[][] Xtest;
	
	public double[] getY1() {return Y1;}
	public double[] getY2() {return Y2;}
	public double[][] getX() {return X;}
	
	public double[] getY1test() {return Y1test;}
	public double[] getY2test() {return Y2test;}
	public double[][] getXtest() {return Xtest;}
	
	//returns the number m of training examples inputed by the user
	public int fileloaderDataLineCounter(String filename) {
		int lineCounter = 1;

        File f = null;
        BufferedReader TXTreader = null;
        String line;

        try {
            f = new File(filename);
        } catch (NullPointerException e) {
            System.err.println("File not found.");
        }

        try {
            TXTreader = new BufferedReader(new FileReader(f));
        } catch (FileNotFoundException e) {
            System.err.println("Error opening file!");
        }

        try {

        	line = TXTreader.readLine();
    		 
            while (line!=null){
            	//System.out.println(line);
            	line = TXTreader.readLine();
            	lineCounter++;
            }
            
            
            
        } //try
        catch (IOException e) {
            System.err.println("Error reading line " + lineCounter + ".");
        }

        try {
            TXTreader.close();
        } catch (IOException e) {
            System.err.println("Error closing file.");
        }
        
        return lineCounter - 1;
	}

	
	//read dataset and initialize the X and Y vectors (arrays)
	public void fileloaderData(String filename, int m) {
		
		
		X = new double[m][6];
		Y1 = new double[m];
		Y2 = new double[m];
		
		//Y1 has the target value: 0 for "does not have Inflammation of urinary bladder", 1 for "has"
		//Y2 has the target value: 0 for "does not have Nephritis of renal pelvis origin", 1 for "has"
		//X has the attribute of each give example
		//Attributes: 6
		//7th token in example is the target value Y1, 8th for Y2
		
    	int lineCounter = 1;
    	int i = 0;
    	int j = 0;
    		
        File f = null;
        BufferedReader TXTreader = null;
        String line;

        try {
            f = new File(filename);
        } catch (NullPointerException e) {
            System.err.println("File not found.");
        }

        try {
            TXTreader = new BufferedReader(new FileReader(f));
        } catch (FileNotFoundException e) {
            System.err.println("Error opening file!");
        }

        try {

        	line = TXTreader.readLine();
    		 
            while (line!=null){
            	
        		StringTokenizer st = new StringTokenizer(line.trim());
                String token = st.nextToken();
                
                X[i][j] = Double.parseDouble(token);
            	for (int a =0; a<7; a++){
            		
            		token = st.nextToken();
                	j++;
            		if (a<5) {
                    	
                    	if (token.equals("yes")) {
                    		X[i][j] = 1;
                    	}
                    	else {X[i][j] = 0;}
            		}
            		else if (a==5) {
            			if (token.equals("yes")) {
            				Y1[i] = 1;
            			}
            			else {Y1[i] = 0;}
            		}
            		else if (a==6) {
            			if (token.equals("yes")) {
            				Y2[i] = 1;
            			}
            			else {Y2[i] = 0;}
            			 
            		}
            	}	
            	i++;
            	j=0;
            	line = TXTreader.readLine();
            	lineCounter++;
            }
            
            
            
        } //try
        catch (IOException e) {
            System.err.println("Error reading line " + lineCounter + ".");
        }

        try {
            TXTreader.close();
        } catch (IOException e) {
            System.err.println("Error closing file.");
        }
	}
	
	
	
	//prints the inputed dataset
	public void testPrintDataset(int m, double[][] X, double[] Y1, double[] Y2) {

		System.out.println("Number of training examples: " + m);
		for (int i=0; i<m; i++) {
			System.out.println("Traning Example: "+ (i+1));
			System.out.print(X[i][0]);
			System.out.print(" ");
			System.out.print(X[i][1]);
			System.out.print(" ");
			System.out.print(X[i][2]);
			System.out.print(" ");
			System.out.print(X[i][3]);
			System.out.print(" ");
			System.out.print(X[i][4]);
			System.out.print(" ");
			System.out.print(X[i][5]);
			System.out.print(" Y1:");
			System.out.print(Y1[i]);
			System.out.print(" Y2:");
			System.out.print(Y2[i]);
			System.out.print("\n");
		}

		
	}


	
	public void buildTestSet(int m, int n, double[][] X, double[] Y1, double[] Y2){
		
		Y1test = new double[n-m];
		Y2test = new double[n-m];
		Xtest = new double[n-m][6];
		
		int b=0;
		for(int a=m; a<n; a++){
			Y1test[b] = Y1[a];
			Y2test[b] = Y2[a];
			
			for(int j=0; j<6; j++){
				Xtest[b][j] = X[a][j];
			}
			
			b++;
		}
	}
	
}
