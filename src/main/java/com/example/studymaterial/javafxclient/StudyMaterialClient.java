package com.example.studymaterial.javafxclient;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

/**
 * JavaFX Desktop Client for Study Material System.
 * Provides a minimal GUI to interact with the REST API.
 * 
 * Note: This is a basic example. In production, you would implement
 * proper error handling, threading, and UI responsiveness.
 */
public class StudyMaterialClient extends Application {
    
    private static final String API_BASE_URL = "http://localhost:8080";
    private HttpClient httpClient;
    private TextArea outputArea;
    private TextField usernameField;
    private PasswordField passwordField;
    
    @Override
    public void start(Stage primaryStage) {
        httpClient = HttpClient.newHttpClient();
        
        primaryStage.setTitle("Study Material System - Desktop Client");
        primaryStage.setWidth(600);
        primaryStage.setHeight(500);
        
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        
        // Login Section
        VBox loginSection = createLoginSection();
        
        // Output Area
        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setWrapText(true);
        outputArea.setPrefHeight(300);
        
        // Buttons
        HBox buttonBox = new HBox(10);
        Button listMaterialsBtn = new Button("List Materials");
        listMaterialsBtn.setOnAction(e -> listMaterials());
        
        Button listEventsBtn = new Button("List Events");
        listEventsBtn.setOnAction(e -> listEvents());
        
        buttonBox.getChildren().addAll(listMaterialsBtn, listEventsBtn);
        
        root.getChildren().addAll(
            new Label("Login"),
            loginSection,
            new Separator(),
            new Label("Actions"),
            buttonBox,
            new Label("Output"),
            outputArea
        );
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private VBox createLoginSection() {
        VBox loginBox = new VBox(10);
        
        usernameField = new TextField();
        usernameField.setPromptText("Username");
        
        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        
        Button loginBtn = new Button("Login");
        loginBtn.setOnAction(e -> login());
        
        loginBox.getChildren().addAll(usernameField, passwordField, loginBtn);
        return loginBox;
    }
    
    private void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        outputArea.appendText("Attempting login with username: " + username + "\n");
        // In a real implementation, you would make an HTTP POST request to /auth/login
    }
    
    private void listMaterials() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_BASE_URL + "/materials"))
                    .GET()
                    .build();
            
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            outputArea.appendText("Materials Response: " + response.body() + "\n");
        } catch (Exception e) {
            outputArea.appendText("Error: " + e.getMessage() + "\n");
        }
    }
    
    private void listEvents() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_BASE_URL + "/events"))
                    .GET()
                    .build();
            
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            outputArea.appendText("Events Response: " + response.body() + "\n");
        } catch (Exception e) {
            outputArea.appendText("Error: " + e.getMessage() + "\n");
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
