<div align="center">
  <img src="images/logo.png" alt="FlowMart SDK" width="150" style="border-radius: 50%;"/>
  <h1>FlowMart SDK</h1>
</div>



[![Maven Central](https://img.shields.io/maven-central/v/io.github.angatiabenson/flowmartsdk.svg?version=0.0.1)](https://mvnrepository.com/artifact/io.github.angatiabenson/flowmartsdk)

The FlowMart SDK provides a simple and efficient way to integrate with
the [FlowMart Open API](https://github.com/angatiabenson/flow-mart-api) for managing categories,
products, and user accounts in your inventory management applications.

See [FloMart API Documentation](https://flowmart.banit.co.ke/docs)

---

## 📥 Installation

Add the following dependency to your `build.gradle.kts` file:

```groovy
implementation("io.github.angatiabenson:flowmartsdk:0.0.1")
```

## ⚙️ Configuration

To get started, configure the SDK with your API key and desired environment (`DEVELOPMENT` or
`PRODUCTION`):

```kotlin
val sdk = FlowMartSdk.Builder()
    .configure(
        configuration = FlowMartConfiguration(
            apiKey = "your_api_key"
        ),
        environment = Environment.DEVELOPMENT
    )
    .build()
```

> **Note:** Replace `"your_api_key"` with your actual FlowMart API key obtained during login.

If you've hosted the FlowMart API on your own domain, you can specify your custom base URL in the
`FlowMartConfiguration`. This allows the SDK to direct its requests to your API endpoint.

```kotlin
val sdk = FlowMartSdk.Builder()
    .configure(
        configuration = FlowMartConfiguration(
            apiKey = "your_api_key",
            baseUrl = "https://flowmart.banit.co.ke/"
        ),
        environment = Environment.DEVELOPMENT
    )
    .build()
```

> **Note:** Ensure that `"https://flowmart.banit.co.ke/"` is replaced with the actual base URL of
> your hosted API.

## 🚀 Usage Examples

### 1. Creating a Product

After setting up the SDK, you can easily create a product in a specified category by providing the
category ID, product name, and quantity.

```kotlin
suspend fun createProductExample(sdk: FlowMartSdk) {
    val result = sdk.createProduct(
        categoryId = 1,
        productName = "Laptops",
        quantity = "5 units"
    )
    result.onSuccess { response ->
        println("Product created successfully: ${response.message}")
    }.onError { error ->
        println("Failed to create product: ${error.message}")
    }
}
```

### 2. Error Handling

The FlowMart SDK uses `Result` type, which allows you to handle success and failure cases
in a clear, structured way. Here’s an example of how to handle errors when creating a product:

```kotlin
suspend fun createProductWithErrorHandling(sdk: FlowMartSdk) {
    val result = sdk.createProduct(
        categoryId = 1,
        productName = "Laptops",
        quantity = "5 units"
    )
    result.onSuccess { response ->
        println("Product created successfully: ${response.message}")
    }.onError { error ->
        when (error) {
            is ApiException -> {
                // Handle API-specific errors based on status code
                println("API error: ${error.code} - ${error.message}")
            }

            is NetworkException -> {
                // Handle network errors (e.g., connectivity issues)
                println("Network error: ${error.message}")
            }

            else -> {
                // Handle other types of exceptions (e.g., unexpected errors)
                println("Unexpected error: ${error.message}")
            }
        }
    }
}
```

---

## 📚 Additional Resources

- [GitHub - FlowMart Open API Project](https://github.com/angatiabenson/flow-mart-api)
- [Swagger - FlowMart Open API Documentation](https://flowmart.banit.co.ke/docs)
- [GitHub Issues](https://github.com/angatiabenson/flowmart-android-sdk/issues)

## 💬 Support

For questions, feature requests, or issues, please reach out
through [GitHub Issues](https://github.com/angatiabenson/flowmart-android-sdk/issues).

---

## 📝 License

This project is licensed under the Apache License. See
the [LICENSE](LICENSE) file for details.
