package com.example.calculator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class HelloController {

	@FXML
	private Tab calculatorTab;

	@FXML
	private Button clearHistoryButton;

	@FXML
	private TabPane tabPane;

	@FXML
	private Tab historyTab;

	// Text field to display current input
	@FXML
	private TextField textField;

	// Text to display the saved numbers and operation
//	@FXML
//	private Text savedNumbers;

	// Stores the first number before an operation is selected
	private String firstNumber = "";

	// Stores the current number being input
	private String currentNumber = "";

	// Stores the selected calculation operation (+, -, *, /)
	private String calculationType;

	// ListView to display calculation history
	@FXML
	private ListView<String> historyListView;

	// ArrayList to store calculation history
	private ArrayList<String> history = new ArrayList<>();


	// ObservableList to bind to ListView
	private ObservableList<String> historyObservableList = FXCollections.observableArrayList();

// Initialize method to set up the ListView
@FXML
public void initialize() {
	historyListView.setItems(historyObservableList);

	// Allow double-click to delete a selected history item
	historyListView.setOnMouseClicked(event -> {
		if (event.getClickCount() == 2) {
			String selectedItem = historyListView.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				history.remove(selectedItem);
				historyObservableList.setAll(history);
			}
		}
	});

	// On single click, clear all history — but DON'T switch tabs
	clearHistoryButton.setOnMouseClicked(event -> {
		if (event.getClickCount() == 1) {
			clearHistory(); // Just clears history
		}
	});
}


	// For '+' button
	@FXML
	void addAction(ActionEvent event) {
		calculationSetup("+");
	}

	// For '-' button
	@FXML
	void minusAction(ActionEvent event) {
		calculationSetup("-");
	}

	// For '*' button
	@FXML
	void multiplicationAction(ActionEvent event) {
		calculationSetup("*");
	}

	// For '/' button
	@FXML
	void divideAction(ActionEvent event) {
		calculationSetup("/");
	}

	// Stores the current input as the first number and prepares for the next number.
	public void calculationSetup(String calculationType) {
		this.calculationType = calculationType;
		firstNumber = currentNumber;
		currentNumber = "";
//		savedNumbers.setText(firstNumber + " " + calculationType);
		textField.setText(firstNumber + " " + calculationType);
	}

	// For '=' button to perform calculation
	@FXML
	void calculate(ActionEvent event) {
		try {
			//  Converts string inputs to numbers
			int firstNumberInt = Integer.parseInt(firstNumber);
			int secondNumberInt = Integer.parseInt(currentNumber);
			String resultText;

			// Perform operation based on the selected calculation type
			switch (calculationType) {
				case "+" -> {
					int calculatedNumber = firstNumberInt + secondNumberInt;
					resultText = firstNumber + " + " + currentNumber + " = " + calculatedNumber;
//					savedNumbers.setText(resultText);
					textField.setText(resultText);
					addToHistory(resultText);
				}

				case "-" -> {
					int calculatedNumber = firstNumberInt - secondNumberInt;
					resultText = firstNumber + " - " + currentNumber + " = " + calculatedNumber;
//					savedNumbers.setText(resultText);
					textField.setText(resultText);
					addToHistory(resultText);
				}

				case "/" -> {
					if (secondNumberInt == 0) {
						textField.setText("Error: Divide by 0");
						return;
					}
					double calculatedNumber = firstNumberInt / (double) secondNumberInt;
					resultText = firstNumber + " / " + currentNumber + " = " + calculatedNumber;
//					savedNumbers.setText(resultText);
					textField.setText(resultText);
					addToHistory(resultText);
				}

				case "*" -> {
					int calculatedNumber = firstNumberInt * secondNumberInt;
					resultText = firstNumber + " * " + currentNumber + " = " + calculatedNumber;
//					savedNumbers.setText(resultText);
					textField.setText(resultText);
					addToHistory(resultText);
				}
			}
			// Reset for next calculation
			currentNumber = textField.getText();
			firstNumber = "";
			calculationType = null;
		} catch (NumberFormatException e) {
			textField.setText("Error: Invalid input");
		}
	}



	@FXML
	void addToHistory(String calculation) {
		history.add(calculation);
		historyObservableList.setAll(history); // refreshes the Listview
	}

	// Clears everything from the history
	@FXML
	void clearHistory() {
		history.clear();
		historyListView.getItems().clear();
		tabPane.getSelectionModel().select(calculatorTab);
	}

//	To clear all input and output fields
	@FXML
	void clearTextField(ActionEvent event) {
		currentNumber = "";
		firstNumber = "";
		calculationType = null;
		textField.setText("");
//		savedNumbers.setText("");
	}

	// Individual button click handlers for 0–9
	@FXML
	void button0Clicked(ActionEvent event) {
		if (!currentNumber.equals("")) {
			addNumber("0");
		}
	}

	@FXML
	void button1Clicked(ActionEvent event) {
		addNumber("1");
	}

	@FXML
	void button2Clicked(ActionEvent event) {
		addNumber("2");
	}

	@FXML
	void button3Clicked(ActionEvent event) {
		addNumber("3");
	}

	@FXML
	void button4Clicked(ActionEvent event) {
		addNumber("4");
	}

	@FXML
	void button5Clicked(ActionEvent event) {
		addNumber("5");
	}

	@FXML
	void button6Clicked(ActionEvent event) {
		addNumber("6");
	}

	@FXML
	void button7Clicked(ActionEvent event) {
		addNumber("7");
	}

	@FXML
	void button8Clicked(ActionEvent event) {
		addNumber("8");
	}

	@FXML
	void button9Clicked(ActionEvent event) {
		addNumber("9");
	}

//	// Updates the text field with the current input number.
//	public void updateTextField() {
//		textField.setText(currentNumber);
//	}

	// Adds number to input and updates the screen
	public void addNumber(String number) {
		currentNumber += number;
		if (calculationType != null && !firstNumber.isEmpty()) {
			textField.setText(firstNumber + " " + calculationType + " " + currentNumber);
		} else {
			textField.setText(currentNumber);
		}
	}


	@FXML
	public void switchToHistoryTab() {
		tabPane.getSelectionModel().select(historyTab);
	}

	@FXML
	public void switchToCalculatorTab() {
		for (Tab tab : tabPane.getTabs()) {
			if (tab.getText().equals("Calculator")) {
				tabPane.getSelectionModel().select(tab);
				break;
			}
		}
	}

}