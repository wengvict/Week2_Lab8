import java.util.Scanner;

public class StudentInfo {
	// OOP(?) the only way to get methods to read array info
	public static int[] stuNum = { 1, 2, 3, 4, 5, 6 };
	public static String[] students = { "Richard Hendricks", "Dinesh Chugtai", "Bertram Gilfoyle", "Erlich Bachman",
			"Jin Yang", "Peter Gregory" };
	public static String[] favoriteFood = { "pizza", "sushi", "mac n cheese", "burgers", "fish",
			"grilled cheese sandwich" };
	public static String[] hometown = { "Palo Alto, CA", "Pakistan", "Canada", "Palo Alto, CA", "China",
			"Silicon Valley, CA" };

	public static void main(String[] args) {
		// declaration
		int userNum;
		String userInput;

		// scanner setup + prompt + user input
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to our Java class.");
		System.out.print("Which student would you like to learn more about? (enter a number 1-6): ");

		// force userNum validation w/ hard-coded false
		boolean validNum = false;
		while (validNum == false) {
			userNum = scan.nextInt() - 1; // -1 to match student number to index number
			try {
				if (userNum < 6) {
					validNum = true;
				} else { // throws array index exception to make sure userNum doesn't exceed array
					validNum = false;
					throw new ArrayIndexOutOfBoundsException();
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.print("That student does not exist. Please try again. Enter a number between 1-6: ");
				continue; // tells user to continue inputting until int is within index param
			}

			System.out.print("Student " + stuNum[userNum] + " is " + students[userNum] + ". ");

			// more info on student, user can continue loop through info if they answer yes
			do {
				System.out.print("What would you like to know about " + students[userNum]
						+ "? (enter 'hometown' or 'favorite food'): ");
				scan.nextLine();

				boolean validInput = false;
				while (validInput == false) {
					userInput = scan.next();
					try {
						if (userInput.equalsIgnoreCase("hometown") || userInput.toLowerCase().startsWith("fav")) {
							validInput = true;
						} else {
							validInput = false;
							throw new IllegalArgumentException();
						}
					} catch (IllegalArgumentException e) {
						System.out.print(
								"That data does not exist. Please try again. Enter 'hometown' or 'favorite food': ");
						continue;
					}

					System.out.print(students[userNum] + getStuInfo(userNum, userInput)
							+ ". Would you like to know more? (enter 'yes' or 'no'): ");
				}
				scan.nextLine();
				userInput = scan.next();
			} while (userInput.contains("y"));
			System.out.println("Thanks!");
		}
		scan.close();
	}

	// method for matching input to student data
	public static String getStuInfo(int userNum, String userRequest) {
		String s = "";
		if (userRequest.equalsIgnoreCase("hometown")) {
			s = " is from " + hometown[userNum];
		} else if (userRequest.toLowerCase().startsWith("fav")) {
			s = "'s favorite food is " + favoriteFood[userNum];
		}
		return s;
	}
}
