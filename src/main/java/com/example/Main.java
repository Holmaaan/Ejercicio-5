package com.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Por favor, proporciona la ruta al archivo de texto como argumento.");
            return;
        }

        String filePath = args[0];
        try {
            TextAnalyzer analyzer = new TextAnalyzer(filePath);
            analyzer.printStatistics();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
