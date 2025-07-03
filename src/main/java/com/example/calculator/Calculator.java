package com.example.calculator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Calculator extends Application {
	private TextField num1Field;
	private TextField num2Field;
	private Label resultLabel;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("My Cool Calculator");

		// Make a grid to organize everything
		GridPane grid = new GridPane();
		grid.setHgap(10); // Space between columns
		grid.setVgap(10); // Space between rows
		grid.setPadding(new Insets(10, 10, 10, 10)); // Space around the edges

		// Text field for the first number
		num1Field = new TextField();
		num1Field.setPromptText("Enter first number");
		GridPane.setConstraints(num1Field, 0, 0);

		// Text field for the second number
		num2Field = new TextField();
		num2Field.setPromptText("Enter second number");
		GridPane.setConstraints(num2Field, 0, 1);

		// Label to show the result
		resultLabel = new Label("Result: ");
		GridPane.setConstraints(resultLabel, 0, 2);

		// Buttons for math operations
		Button addButton = new Button("Add");
		addButton.setOnAction(e -> performOperation('+'));
		GridPane.setConstraints(addButton, 1, 0);

		Button subtractButton = new Button("Subtract");
		subtractButton.setOnAction(e -> performOperation('-'));
		GridPane.setConstraints(subtractButton, 2, 0);

		Button multiplyButton = new Button("Multiply");
		multiplyButton.setOnAction(e -> performOperation('*'));
		GridPane.setConstraints(multiplyButton, 1, 1);

		Button divideButton = new Button("Divide");
		divideButton.setOnAction(e -> performOperation('/'));
		GridPane.setConstraints(divideButton, 2, 1);

		// Add everything to the grid
		grid.getChildren().addAll(num1Field, num2Field, resultLabel, addButton, subtractButton, multiplyButton, divideButton);

		// Create the scene (the window’s content)
		Scene scene = new Scene(grid, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void performOperation(char operator) {
		String num1Text = num1Field.getText();
		String num2Text = num2Field.getText();

		// Check if the inputs are valid numbers
		if (isValidNumber(num1Text) && isValidNumber(num2Text)) {
			double num1 = Double.parseDouble(num1Text);
			double num2 = Double.parseDouble(num2Text);
			double result = 0.0;

			// Do the math based on the button clicked
			switch (operator) {
				case '+':
					result = num1 + num2;
					break;
				case '-':
					result = num1 - num2;
					break;
				case '*':
					result = num1 * num2;
					break;
				case '/':
					if (num2 != 0) {
						result = num1 / num2;
					} else {
						resultLabel.setText("Result: Error (Divide by zero)");
						return;
					}
					break;
			}
			resultLabel.setText("Result: " + result);
		} else {
			resultLabel.setText("Result: Invalid Input");
		}
	}

	private boolean isValidNumber(String text) {
		return text.matches("-?\\d*\\.?\\d+"); // Checks if it’s a number (like 5 or -3.14)
	}
}
