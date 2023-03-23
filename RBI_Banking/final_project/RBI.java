import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class RBI {
	public static void main(String[] args) {
		System.out.println("WELCOME TO THE RBI");
		System.out.println("Do you want to open an account? y/n:");
		Scanner userInput = new Scanner(System.in);
		String choice = userInput.nextLine();

		if (choice.equals("y") || choice.equals("Y")) {
			OpenAccount openAccount = new OpenAccount();
			openAccount.createAccount();
		}

		if (choice.equals("n") || choice.equals("N")) {
			BankAccount bankAccount = new BankAccount();
			bankAccount.showMenu();
		}
		userInput.close();
	}
}

class OpenAccount {
	String name;
	double accountNum;
	String accountType;
	String birthDate;
	String bankName;

	void createAccount() {
		Scanner userInput = new Scanner(System.in);
		System.out.println("In which bank you want to open your account?");
		System.out.println("1. SBI 2. PNB 3. ICICI");
		int chooseBank = userInput.nextInt();

		if (chooseBank == 1) {
			bankName = "SBI";
		}
		if (chooseBank == 2) {
			bankName = "PNB";
		}
		if (chooseBank == 3) {
			bankName = "ICICI";
		}
		
		System.out.println("Enter your name: ");
		userInput.nextLine();
		name = userInput.nextLine();
		System.out.println("Enter your date of birth: ");
		birthDate = userInput.nextLine();
		System.out.println("What Type of account you want to Open?");
		System.out.println("1. Saving 2.Current");
		int chooseAccount = userInput.nextInt();

		if (chooseAccount == 1) {
			accountType = "Saving";
		}
		if (chooseAccount == 2) {
			accountType = "Current";
		}

		accountNum = Math.random();
		System.out.println("Your account has been created.");
		System.out.println("Please find the details below.");
		System.out.println("******************************");
		System.out.println("Bank name: " + bankName);
		System.out.println("Your name: " + name);
		System.out.println("Date of birth: " + birthDate);
		System.out.println("Account Type: " + accountType);
		System.out.println("Account Number: " + accountNum);
		userInput.nextLine();
		BankAccount bankaccount = new BankAccount();
		bankaccount.showMenu();
		userInput.close();

	}
}

class BankAccount {
	int balance;
	int prevTransaction;
	String customerName;
	String customerId;
	String accountType;
	double totalInterest;

	void calculateInterest(double balance) {
		System.out.println("What type of account do you have?");
		System.out.println("1. Saving 2. Current");
		Scanner userInput = new Scanner(System.in);
		int chooseAccount = userInput.nextInt();

		if (chooseAccount == 1) {
			accountType = "Saving";
			int r = 5;
			System.out.println("Enter years to calculate the interest: ");
			int t = userInput.nextInt();

			double finalAmount = balance * (1 + r * t);
			totalInterest = finalAmount - balance;

			System.out.println("Your Total Interest amount is: " + totalInterest);

		}
		if (chooseAccount == 2) {
			accountType = "Current";
			int r = 8;

			System.out.println("Enter years to calculate the Interest: ");
			int t = userInput.nextInt();

			System.out.println("Enter number of times interest is compounded per year ");
			int n = userInput.nextInt();
			
			int totalTime= n*t;

			double finalAmount = balance * (Math.pow((1 + r / n), totalTime));

			totalInterest = finalAmount - balance;

			System.out.println("Your Total Interest amount is: " + totalInterest);
	
		}
	}

	void deposit(int amount) {
		if (amount != 0) {
			balance = balance + amount;
			System.out.println("Balance after deposit: " + balance);
			prevTransaction = amount;
		}
	}

	void withdraw(int amount) {
		if (amount != 0) {
			balance = balance - amount;
			System.out.println("Balance after withdraw: " + balance);
			prevTransaction = -amount;
		}
	}

	void previousTransaction() {
		FileOutputStream out;
		PrintStream p;

		try {
			out = new FileOutputStream("C:\\Users\\91701\\eclipse-workspace\\Project_2_CoreJava\\finalProject.txt");
			p = new PrintStream(out);

			if (prevTransaction > 0) {
				p.append("Deposited: " + prevTransaction);
				System.out.println("Deposited Amount: " + prevTransaction);

			}
			if (prevTransaction < 0) {
				p.append("Withdrawn: " + prevTransaction);
				System.out.println("Withdrawn: " + Math.abs(prevTransaction));

			}
			p.close();
		} catch (Exception e) {
			System.out.println("Error in printing the data " + e);
		}
	}

	void showMenu() {
		Scanner userInput = new Scanner(System.in);
		int choose = 0;
		System.out.println("************************");
		System.out.println("WELCOME TO THE MAIN MENU");
		System.out.println("1. check Balance");
		System.out.println("2. Deposit Amount ");
		System.out.println("3. Withdraw Amount ");
		System.out.println("4. Previous Transactions ");
		System.out.println("5. Calculate Interest");
		System.out.println("6. Exit");

		do {
			System.out.println("****************");
			System.out.println("CHOOSE AN OPTION");
			choose = userInput.nextInt();
			switch (choose) {
			case 1:
				System.out.println("your Balance: " + balance);
				userInput.nextLine();
				break;

			case 2:
				System.out.println("Enter amount you want to deposit:");
				int depositAmount = userInput.nextInt();
				deposit(depositAmount);
				System.out.println("Amount deposited successfully!!");
				break;
			case 3:
				System.out.println("Enter amount you want to withdraw:");
				int withdrawAmount = userInput.nextInt();
				withdraw(withdrawAmount);
				System.out.println("Your amount is deducted Successfully!!");
				break;
			case 4:
				System.out.println("**Your tansactions**");
				previousTransaction();
				break;
			case 5:
				calculateInterest(balance);
				break;
			case 6:
				break;
			default:
				System.out.println("Wrong choice. Try Again!");
				break;
			}
		} while (choose != 6);
		System.out.println("Thank You for Using Our Services.");
		userInput.close();
	}
}
