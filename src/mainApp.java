import loader.Loader;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

import algorithm.SGDAlgorithm;

public class mainApp {
	
	
	public static void main(String[] args) {
		
		Loader l = new Loader();
		SGDAlgorithm sgd = new SGDAlgorithm();
		SGDAlgorithm sgd1 = new SGDAlgorithm();
		SGDAlgorithm sgd2 = new SGDAlgorithm();
		DecimalFormat df = new DecimalFormat("######0.0000");
		DecimalFormat df2 = new DecimalFormat("###0.0");
		
		Scanner reader = new Scanner(System.in);
		System.out.println("----------- Logistic Regression - Stochastic Gradient Descent -----------");
		System.out.println("----------- Dataset used: Acute Inflammations, from UCI repository -----------");
		System.out.println("-------- 6 attributes:");
		System.out.println("----- X[i][0]: Temperature of patient");
		System.out.println("----- X[i][1]: Occurrence of nausea, '0' for no, '1' for yes");
		System.out.println("----- X[i][2]: Lumbar pain, '0' for no, '1' for yes");
		System.out.println("----- X[i][3]: Urine pushing, '0' for no, '1' for yes");
		System.out.println("----- X[i][4]: Micturition pains, '0' for no, '1' for yes");
		System.out.println("----- X[i][5]: Burning of urethra, '0' for no, '1' for yes");
		System.out.println("-------- 2 decisions (4 classes):");
		System.out.println("----- Y1[i]: Inflammation of urinary bladder, '0' for no, '1' for yes");
		System.out.println("----- Y2[i]: Nephritis of renal pelvis origin , '0' for no, '1' for yes");
		
		System.out.println("\nEnter the filename of dataset: ");
		String filename = reader.nextLine();
		
		int m = l.fileloaderDataLineCounter(filename);
		l.fileloaderData(filename, m);
		
		//l.testPrintDataset(m, l.getX(), l.getY1(), l.getY2());
		sgd.shuffleDataset(m, l.getY1(), l.getY2(), l.getX());
		//l.testPrintDataset(m, l.getX(), l.getY1(), l.getY2());
		

		double j = sgd1.SGD(0.002, m, l.getY1(), l.getX());
		System.out.println("Algorithm trained regarding decision 1 with final J(theta)=" + df.format(j) + " and Theta vector=" 
				+ df.format(sgd1.getFinalTheta()[0]) +", " + df.format(sgd1.getFinalTheta()[1]) +", " + df.format(sgd1.getFinalTheta()[2]) +", "
				+ df.format(sgd1.getFinalTheta()[3]) +", " + df.format(sgd1.getFinalTheta()[4]) +", " + df.format(sgd1.getFinalTheta()[5]) +", " 
				+ df.format(sgd1.getFinalTheta()[6]));
		j = sgd2.SGD(0.002, m, l.getY2(), l.getX());
		System.out.println("Algorithm trained regarding decision 2 with final J(theta)=" + df.format(j) + " and Theta vector=" 
				+ df.format(sgd2.getFinalTheta()[0]) +", " + df.format(sgd2.getFinalTheta()[1]) +", " + df.format(sgd2.getFinalTheta()[2]) +", "
				+ df.format(sgd2.getFinalTheta()[3]) +", " + df.format(sgd2.getFinalTheta()[4]) +", " + df.format(sgd2.getFinalTheta()[5]) +", "
				+ df.format(sgd2.getFinalTheta()[6]));
		
		
		
		double[] input = new double[6];
		String choice;
		System.out.println("\n1/6: What is the temperature of the patient:");
		choice = reader.nextLine();
		input[0] = Double.parseDouble(choice);
		System.out.println("\n2/6: Does the patient have nausea? ('yes' or 'no'):");
		choice = reader.nextLine();
		while(true){
			if (choice.equals("yes")){
				input[1] = 1;
				break;
			}
			else if (choice.equals("no")){
				input[1] = 0;
				break;
			}
			else{
				System.out.println("Answer must be either 'yes' or 'no'. Try again:");
				choice = reader.nextLine();
			}
		}

		System.out.println("\n3/6: Does the patient have Lambar pain? ('yes' or 'no'):");
		choice = reader.nextLine();
		while(true){
			if (choice.equals("yes")){
				input[2] = 1;
				break;
			}
			else if (choice.equals("no")){
				input[2] = 0;
				break;
			}
			else{
				System.out.println("Answer must be either 'yes' or 'no'. Try again:");
				choice = reader.nextLine();
			}
		}
		System.out.println("\n4/6: Does the patient have urine pushing? ('yes' or 'no'):");
		choice = reader.nextLine();
		while(true){
			if (choice.equals("yes")){
				input[3] = 1;
				break;
			}
			else if (choice.equals("no")){
				input[3] = 0;
				break;
			}
			else{
				System.out.println("Answer must be either 'yes' or 'no'. Try again:");
				choice = reader.nextLine();
			}
		}
		System.out.println("\n5/6: Does the patient have micturition pains? ('yes' or 'no'):");
		choice = reader.nextLine();
		while(true){
			if (choice.equals("yes")){
				input[4] = 1;
				break;
			}
			else if (choice.equals("no")){
				input[4] = 0;
				break;
			}
			else{
				System.out.println("Answer must be either 'yes' or 'no'. Try again:");
				choice = reader.nextLine();
			}
		}
		System.out.println("\n6/6: Does the patient have burning of urethra? ('yes' or 'no'):");
		choice = reader.nextLine();
		while(true){
			if (choice.equals("yes")){
				input[5] = 1;
				break;
			}
			else if (choice.equals("no")){
				input[5] = 0;
				break;
			}
			else{
				System.out.println("Answer must be either 'yes' or 'no'. Try again:");
				choice = reader.nextLine();
			}
		}
		
		reader.close();
		
		System.out.println("\nVector of inputed attributes of patient:" + Arrays.toString(input));
		
		
		double prediction1 = sgd1.finalSigmoidHypothesis(sgd1.getFinalTheta(), input);
		double prediction2 = sgd2.finalSigmoidHypothesis(sgd2.getFinalTheta(), input);
		System.out.println("\nBased on the attributes inserted, patient has a " + df2.format(prediction1*100) 
				+ "% chance of having inflammation of the urinary bladder and\n"
				+ df2.format(prediction2*100) + "% chance of having Nephritis of renal pelvis origin.");
		
	}

}
