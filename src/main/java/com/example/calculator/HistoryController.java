package com.example.calculator;


import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.List;

public class HistoryController {
	@FXML
	private ListView<String> historyListView;

	public void setHistory(List<String> history) {
		historyListView.getItems().setAll(history);
	}

	@FXML
	public void closeWindow() {
		Stage stage = (Stage) historyListView.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void clearHistory() {
		historyListView.getItems().clear();
	}

}
