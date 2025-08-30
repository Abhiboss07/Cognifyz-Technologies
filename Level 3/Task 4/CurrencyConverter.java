import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrencyConverter {

    private static final String API_URL = "https://open.er-api.com/v6/latest/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Welcome to the Currency Converter!");
            System.out.print("Enter the base currency (e.g., USD, EUR, INR): ");
            String baseCurrency = scanner.nextLine().toUpperCase();

            System.out.print("Enter the target currency (e.g., INR, GBP, JPY): ");
            String targetCurrency = scanner.nextLine().toUpperCase();

            System.out.print("Enter the amount to convert: ");
            double amount = scanner.nextDouble();

            String apiResponse = getApiResponse(baseCurrency);

            double exchangeRate = parseExchangeRate(apiResponse, targetCurrency);

            double convertedAmount = amount * exchangeRate;

            System.out.println("\n--- Conversion Result ---");
            System.out.printf("%.2f %s = %.2f %s%n", amount, baseCurrency, convertedAmount, targetCurrency);
            System.out.println("Based on an exchange rate of 1 " + baseCurrency + " = " + exchangeRate + " " + targetCurrency);

        } catch (IOException e) {
            System.err.println("Error: Could not connect to the API service. Please check your internet connection.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Error: The request to the API was interrupted.");
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid amount entered. Please enter a valid number.");
        } catch (RuntimeException e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static String getApiResponse(String baseCurrency) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + baseCurrency))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new IOException("Failed to fetch data from API. Response code: " + response.statusCode());
        }
    }

    private static double parseExchangeRate(String jsonResponse, String targetCurrency) {
        String patternString = "\"" + targetCurrency + "\":(\\d+\\.?\\d*)";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(jsonResponse);

        if (matcher.find()) {
            return Double.parseDouble(matcher.group(1));
        } else {
            if (jsonResponse.contains("\"result\":\"error\"")) {
                 throw new RuntimeException("Invalid base currency code provided.");
            }
            throw new RuntimeException("Target currency '" + targetCurrency + "' not found in the API response.");
        }
    }
}

