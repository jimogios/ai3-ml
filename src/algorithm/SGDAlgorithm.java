package algorithm;

import java.util.concurrent.ThreadLocalRandom;


public class SGDAlgorithm {
	
	//theta is 6 parameters +1 size = 7
	private double[] theta = {0,0,0,0,0,0,0};
	private double[] finalTheta = {0,0,0,0,0,0,0};
	
	
	public double[] getTheta() {return theta;}
	public double[] getFinalTheta() {return finalTheta;}


	//shuffles the dataset by swapping each row with another chosen randomly each time
	public void shuffleDataset(int m, double[] Y1, double[] Y2, double[][] X) {
		
		for (int i = 0; i<m; i++) {
			
			int randomI = ThreadLocalRandom.current().nextInt(0, 119 + 1);
			double temp = Y2[randomI];
			Y2[randomI] = Y2[i];
			Y2[i] = temp;
			
			temp = Y1[randomI];
			Y1[randomI] = Y1[i];
			Y1[i] = temp;
			
			for (int j = 0; j<6; j++) {
				temp = X[randomI][j];
				X[randomI][j] = X[i][j];
				X[i][j] = temp;

			}
			
			
		}
		System.out.println("Dataset successfully shuffled!");
	}
	

	//hypothesis function
	public double sigmoidHypothesis(int i, double[] Theta, double[][] X) {
		double thetaTx = Theta[0]*1 + X[i][0]*Theta[1] + X[i][1]*Theta[2] + X[i][2]*Theta[3] + 
				X[i][3]*Theta[4] + X[i][4]*Theta[5] + X[i][5]*Theta[6];
		
		//System.out.println(1/( 1 + Math.pow(Math.E,(-thetaTx))));
		
		return (1/( 1 + Math.pow(Math.E,(-thetaTx))));
	}
	

	//cost function
	public double cost(int i, double[] Theta, double[] Y, double[][] X) {
		return 0.5*Math.pow(sigmoidHypothesis(i, Theta, X) - Y[i], 2);
	}
	
	
	//J(theta) function
	public double JofTheta(int m, double[] Theta, double[] Y, double [][] X) {
		double sum=0;
		for (int i=0; i<m; i++) {
			sum = sum + cost(i, Theta, Y, X);
		}
		
		return sum/m;
	}
	
	
	//Stochastic Gradient Descent Algorithm
	public double SGD(double alpha, int m, double[] Y, double[][] X){
		
		int n = theta.length;
		double[] thetaCopy = new double[n];
		
//		System.out.println("Initial value of vector theta: " + theta[0] +" " + theta[1] +" " + 
//				theta[2] +" " + theta[3] +" " + theta[4] +" " + theta[5] +" " + theta[6]);
//		System.out.println("Initial Value of J(theta): " + JofTheta(m, theta, Y, X));
		
		double minValue = JofTheta(m, theta, Y, X);
		
		for (int r=0; r<10; r++){
			
			for (int i=0; i<m; i++){
				
				for (int j=0; j<n; j++){
					
					if (j==0){
						thetaCopy[j] = theta[j] - alpha*(sigmoidHypothesis(i, theta, X) - Y[i])*1;
					}
					else{
						thetaCopy[j] = theta[j] - alpha*(sigmoidHypothesis(i, theta, X) - Y[i])*X[i][j-1];
					}
					
					
				}
				
				updateTheta(thetaCopy);
				
				
				
				
				if(JofTheta(m, theta, Y, X)<minValue){
					minValue = JofTheta(m, theta, Y, X);
					//System.out.println(minValue);
					chosenTheta();
				}
				//System.out.println(JofTheta(m));
				//System.out.println(theta[0] +" " + theta[1] +" " + theta[2] +" " + theta[3] +" " + theta[4] +" " + theta[5] +" " + theta[6]);
				
			}
			
		}
		
//		System.out.println("Final value of vector theta: " + finalTheta[0] +" " + finalTheta[1] +" " + 
//				finalTheta[2] +" " + finalTheta[3] +" " + finalTheta[4] +" " + finalTheta[5] +" " + finalTheta[6]);
//		System.out.println("Final value of J(theta): " + JofTheta(m, finalTheta, Y, X));
		
		return JofTheta(m, finalTheta, Y, X);
		
	}
	
	
	
	public void updateTheta(double[] thetaCopy){
		
		for (int j=0; j<theta.length; j++){
			
			theta[j] = thetaCopy[j];
			
		}
		
	}
	
	
	public void chosenTheta(){
		
		for (int j=0; j<theta.length; j++){
			
			finalTheta[j] = theta[j];
			
		}
		
	}
	
	
	//hypothesis function, to be used to for new predictions, based on newly inputed data
	public double finalSigmoidHypothesis(double[] Theta, double[] input) {
		double thetaTx = Theta[0]*1 + input[0]*Theta[1] + input[1]*Theta[2] + input[2]*Theta[3] + 
				input[3]*Theta[4] + input[4]*Theta[5] + input[5]*Theta[6];
		
		
		return (1/( 1 + Math.pow(Math.E,(-thetaTx))));
	}

}
