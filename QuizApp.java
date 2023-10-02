import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion {
    private String question;
    private List<String> options;
    private int correctOptionIndex;

    public QuizQuestion(String question, List<String> options, int correctOptionIndex) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}

public class QuizApp {
    private List<QuizQuestion> questions;
    private int currentQuestionIndex;
    private int score;
    private Timer timer;
    private Scanner scanner;

    public QuizApp() {
        questions = new ArrayList<>();
        currentQuestionIndex = 0;
        score = 0;
        timer = new Timer();
        scanner = new Scanner(System.in);
    }

    public void addQuestion(QuizQuestion question) {
        questions.add(question);
    }

    public void startQuiz() {
        if (questions.isEmpty()) {
            System.out.println("No questions added to the quiz.");
            return;
        }

        for (QuizQuestion question : questions) {
            presentQuestion(question);
        }

        displayResult();
    }

    private void presentQuestion(QuizQuestion question) {
        System.out.println(question.getQuestion());
        List<String> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }

        int correctOptionIndex = question.getCorrectOptionIndex();
        int userAnswer = getUserAnswer(options.size());
        if (userAnswer == correctOptionIndex) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect. Correct answer: " + options.get(correctOptionIndex));
        }
    }

    private int getUserAnswer(int numOptions) {
        System.out.print("Your answer (1-" + numOptions + "): ");
        int userAnswer = scanner.nextInt();
        if (userAnswer < 1 || userAnswer > numOptions) {
            System.out.println("Invalid choice. Please choose a valid option.");
            return getUserAnswer(numOptions);
        }
        return userAnswer - 1; // Subtract 1 to get the zero-based index.
    }

    private void displayResult() {
        System.out.println("Quiz completed!");
        System.out.println("Your score: " + score + " out of " + questions.size());
    }

    public static void main(String[] args) {
        QuizApp quizApp = new QuizApp();

        // Add quiz questions here
        quizApp.addQuestion(new QuizQuestion("What is 2 + 2?", List.of("3", "4", "5"), 1));
        quizApp.addQuestion(
                new QuizQuestion("What is the capital of France?", List.of("Berlin", "Madrid", "Paris"), 2));

        quizApp.startQuiz();
    }
}
