# 💱 Currency Converter

A Java-based command-line currency converter application that provides real-time exchange rates using the Open Exchange Rates API.

## ✨ Features

- **🔄 Real-time Exchange Rates**: Fetches current exchange rates from the Open Exchange Rates API
- **🌍 Multiple Currency Support**: Supports all major world currencies (USD, EUR, INR, GBP, JPY, etc.)
- **💻 User-friendly Interface**: Simple command-line interface with clear prompts
- **🛡️ Error Handling**: Comprehensive error handling for network issues, invalid inputs, and API errors
- **📊 Precise Calculations**: Provides accurate currency conversion with proper decimal formatting

## 📋 Prerequisites

- ☕ Java 11 or higher
- 🌐 Internet connection (required for API calls)
- 🔑 No API key required (uses free tier of Open Exchange Rates API)

## 🚀 How to Run

### 🔨 Compilation
```bash
javac CurrencyConverter.java
```

### ▶️ Execution
```bash
java CurrencyConverter
```

## 📖 Usage

1. **▶️ Run the application** using the command above
2. **💱 Enter the base currency** (e.g., USD, EUR, INR)
3. **🎯 Enter the target currency** (e.g., INR, GBP, JPY)
4. **💰 Enter the amount** to convert
5. **📈 View the conversion result** with exchange rate information

### 💡 Example Usage
```
Welcome to the Currency Converter!
Enter the base currency (e.g., USD, EUR, INR): USD
Enter the target currency (e.g., INR, GBP, JPY): INR
Enter the amount to convert: 100

--- Conversion Result ---
100.00 USD = 8325.50 INR
Based on an exchange rate of 1 USD = 83.255 INR
```

## 🌍 Supported Currencies

The application supports all currencies available through the Open Exchange Rates API, including but not limited to:

- **💵 Major Currencies**: USD, EUR, GBP, JPY, CAD, AUD, CHF
- **🌏 Asian Currencies**: INR, CNY, KRW, SGD, THB, MYR
- **🌎 Other Currencies**: BRL, RUB, ZAR, SEK, NOK, DKK

## 🔧 Technical Details

### 🔌 API Integration
- 🌐 Uses the Open Exchange Rates API (https://open.er-api.com/v6/latest/)
- 📡 HTTP client implementation using Java 11+ HttpClient
- 📄 JSON response parsing using regex patterns

### ⚠️ Error Handling
The application handles various error scenarios:
- **🌐 Network connectivity issues**
- **❌ Invalid currency codes**
- **🔢 Invalid numeric inputs**
- **🚫 API service unavailability**
- **⏸️ Request interruption**

### 🏗️ Code Structure
- `main()`: Main application logic and user interaction
- `getApiResponse()`: Handles HTTP requests to the API
- `parseExchangeRate()`: Extracts exchange rates from JSON response

## 📦 Dependencies

- **☕ Java Standard Library**: No external dependencies required
- **🌐 HTTP Client**: Uses built-in `java.net.http.HttpClient`
- **📄 JSON Parsing**: Uses regex patterns for simple JSON parsing

## ⚠️ Limitations

- **⏱️ API Rate Limits**: Subject to Open Exchange Rates API rate limits
- **🌐 Internet Dependency**: Requires active internet connection
- **🆓 Free Tier**: Uses free API tier with limited features

## 🚀 Future Enhancements

Potential improvements for the application:
- 📈 Add support for historical exchange rates
- 📋 Implement currency conversion history
- 🔄 Add support for multiple currency conversions at once
- 🖥️ Create a graphical user interface (GUI)
- 💾 Add support for offline mode with cached rates
- 📚 Implement more robust JSON parsing using libraries like Jackson or Gson

## 📄 License

This project is part of the Cognifyz Technologies internship program.

## 👨‍💻 Author

Developed as part of Level 3 Task 4 in the Java Programming internship.
