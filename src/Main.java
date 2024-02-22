import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;
public class Main {
    public static String[] readResponses(String fileName) { //read txt files and turn to array
        ArrayList<String> responsesList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                responsesList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert ArrayList to Array
        String[] responsesArray = new String[responsesList.size()];
        responsesArray = responsesList.toArray(responsesArray);

        return responsesArray;
    }
    public static void appendArrayToFile(String fileName, String[] array) { //write survey data to txt file
        try (FileWriter fw = new FileWriter(fileName, true)) {
            for (String item : array) {
                fw.write(item + "\n"); // Append each item to the file with a new line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String predictParty(String[] currentResponses, String[][] questions) {
        // Initialize counters for each party
        int[] partyCount = new int[4]; // Assuming 4 parties

        // Loop through each question
        for (int i = 0; i < questions.length; i++) {
            // Get the index of the user's response for the current question
            int responseIndex = Integer.parseInt(currentResponses[i]) - 1;

            // Increment the corresponding party count based on the user's response
            for (int j = 1; j < questions[i].length; j++) {
                if (questions[i][j].equals("Pizza") && responseIndex == j - 1) {
                    partyCount[0]++;
                } else if (questions[i][j].equals("Birthday") && responseIndex == j - 1) {
                    partyCount[1]++;
                } else if (questions[i][j].equals("Pity") && responseIndex == j - 1) {
                    partyCount[2]++;
                } else if (questions[i][j].equals("Toga") && responseIndex == j - 1) {
                    partyCount[3]++;
                }
            }
        }

        // Find the party with the highest count
        int maxCount = 0;
        int predictedPartyIndex = -1;
        for (int i = 0; i < partyCount.length; i++) {
            if (partyCount[i] > maxCount) {
                maxCount = partyCount[i];
                predictedPartyIndex = i;
            }
        }

        // Return the predicted party based on the index
        switch (predictedPartyIndex) {
            case 0:
                return "Pizza Party";
            case 1:
                return "Birthday Party";
            case 2:
                return "Pity Party";
            case 3:
                return "Toga Party";
            default:
                return "Unknown";
        }
    }

    public static void main(String[] args) {
        // Initialize scanner for user input
        Scanner scanner = new Scanner(System.in);
        //Read data.txt and initialize previousResponses array
        String[] previousResponses = readResponses("data.txt");

        //DEBUG Show previous responses
        System.out.println("Previous Responses: " + Arrays.deepToString(previousResponses));

        String[][] questions = new String[21][5];

        // Populate the array with questions and answers
        questions[0] = new String[]{"What does your favorite food taste like?", "Pizza", "Chicken nuggets", "Ice Cream", "Salad"};
        questions[1] = new String[]{"What does your favorite food smell like?", "Pizza", "Chicken nuggets", "Ice Cream", "Salad"};
        questions[2] = new String[]{"What is your favorite food textured like?", "Pizza", "Chicken nuggets", "Ice Cream", "Salad"};
        questions[3] = new String[]{"What is your favorite food spelt like?", "Pizza", "Chicken nuggets", "Ice Cream", "Salad"};
        questions[4] = new String[]{"What does your favorite food resemble?", "Pizza", "Chicken nuggets", "Ice Cream", "Salad"};
        questions[5] = new String[]{"If you could eat one thing for the rest of your life what would it be?", "Pizza", "Chicken nuggets", "Ice Cream", "Salad"};
        questions[6] = new String[]{"If you could smell one thing for the rest of your life what would it be?", "Pizza", "Chicken nuggets", "Ice Cream", "Salad"};
        questions[7] = new String[]{"If you could feel one thing for the rest of your life what would it be?", "Pizza", "Chicken nuggets", "Ice Cream", "Salad"};
        questions[8] = new String[]{"If you could dream one thing for the rest of your life what would it be?", "Pizza", "Chicken nuggets", "Ice Cream", "Salad"};
        questions[9] = new String[]{"If you could buy one thing for the rest of your life what would it be?", "Pizza", "Chicken nuggets", "Ice Cream", "Salad"};
        questions[10] = new String[]{"If you could imagine one thing for the rest of your life what would it be?", "Pizza", "Chicken nuggets", "Ice Cream", "Salad"};
        questions[11] = new String[]{"If you could start a food business what would you sell?", "Pizza", "Chicken nuggets", "Ice Cream", "Salad"};
        questions[12] = new String[]{"If you could marry a food would would you marry?", "Pizza", "Chicken nuggets", "Ice Cream", "Salad"};
        questions[13] = new String[]{"If you had to name your 1st child after a food item what would it be?", "Pizza", "Chicken nuggets", "Ice Cream", "Salad"};
        questions[14] = new String[]{"If you had to name your 2nd child after a food item what would it be?", "Pizza", "Chicken nuggets", "Ice Cream", "Salad"};
        questions[15] = new String[]{"If you had to name your 3rd child after a food item what would it be?", "Pizza", "Chicken nuggets", "Ice Cream", "Salad"};
        questions[16] = new String[]{"If you had to name your 4th child after a food item what would it be?", "Pizza", "Chicken nuggets", "Ice Cream", "Salad"};
        questions[17] = new String[]{"If you had to name your 5th child after a food item what would it be?", "Pizza", "Chicken nuggets", "Ice Cream", "Salad"};
        questions[18] = new String[]{"If you had to name your 6th child after a food item what would it be?", "Pizza", "Chicken nuggets", "Ice Cream", "Salad"};
        questions[19] = new String[]{"If you had to name your 7th child after a food item what would it be?", "Pizza", "Chicken nuggets", "Ice Cream", "Salad"};
        questions[20] = new String[]{"What is your Political Party", "Pizza Party", "Chicken nuggets Party", "Ice Cream Party", "Salad Party"};
        // Initialize variables to store survey responses for amt of questions
        String[] newResponses = new String[questions.length];

        // Present survey questions to the user
        for (int i = 0; i < questions.length; i++) {
            System.out.println("Question " + (i + 1) + ": " + questions[i][0]);
            System.out.println("   1: " + questions[i][1]);
            System.out.println("   2: " + questions[i][2]);
            System.out.println("   3: " + questions[i][3]);
            System.out.println("   4: " + questions[i][4]);
            newResponses[i] = scanner.nextLine();
        }
        //Append newResponses to data set after survey is done
        appendArrayToFile("data.txt", newResponses);

        //DEBUG Display survey result and append data.txt
        System.out.println("Results: \n" + Arrays.deepToString(newResponses));

        //Final Prediction
        System.out.println("Based on your responses, your political affiliation is predicted to be:\n" + predictParty(newResponses, questions));

        // Close scanner
        scanner.close();
    }
}