import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * HelloWorld - Main Java application demonstrating various Java features
 * This class serves as the entry point for the Java portion of our multi-language demo.
 */
public class HelloWorld {
    private static Scanner scanner = new Scanner(System.in);
    private static Calculator calculator = new Calculator();
    private static TaskManager taskManager = new TaskManager();
    
    public static void main(String[] args) {
        System.out.println("☕ Welcome to the Java Multi-Language Demo!");
        System.out.println("==========================================");
        
        // Demonstrate basic Java features
        demonstrateBasicFeatures();
        
        // Interactive menu
        runInteractiveMenu();
        
        scanner.close();
        System.out.println("\n👋 Thanks for using the Java demo!");
    }
    
    private static void demonstrateBasicFeatures() {
        System.out.println("\n🚀 Demonstrating Basic Java Features:");
        System.out.println("=====================================");
        
        // Variables and data types
        String greeting = "Hello from Java!";
        int year = 2024;
        double pi = 3.14159;
        boolean isJavaAwesome = true;
        
        System.out.println("📝 String: " + greeting);
        System.out.println("🔢 Integer: " + year);
        System.out.println("🎯 Double: " + pi);
        System.out.println("✅ Boolean: " + isJavaAwesome);
        
        // Arrays and Collections
        String[] languages = {"Java", "TypeScript", "HTML", "CSS"};
        List<String> frameworks = new ArrayList<>();
        frameworks.add("Spring Boot");
        frameworks.add("Angular");
        frameworks.add("React");
        
        System.out.println("\n📚 Programming Languages:");
        for (String lang : languages) {
            System.out.println("  • " + lang);
        }
        
        System.out.println("\n🛠️ Frameworks:");
        frameworks.forEach(framework -> System.out.println("  • " + framework));
    }
    
    private static void runInteractiveMenu() {
        while (true) {
            System.out.println("\n🎮 Interactive Java Demo Menu:");
            System.out.println("==============================");
            System.out.println("1. 🧮 Calculator Demo");
            System.out.println("2. 📝 Task Manager Demo");
            System.out.println("3. 🎲 Random Number Generator");
            System.out.println("4. 📊 Data Processing Demo");
            System.out.println("5. 🏃 Exit");
            System.out.print("\nChoose an option (1-5): ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        runCalculatorDemo();
                        break;
                    case 2:
                        runTaskManagerDemo();
                        break;
                    case 3:
                        runRandomNumberDemo();
                        break;
                    case 4:
                        runDataProcessingDemo();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("❌ Invalid option. Please choose 1-5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a number.");
            }
        }
    }
    
    private static void runCalculatorDemo() {
        System.out.println("\n🧮 Calculator Demo");
        System.out.println("=================");
        
        try {
            System.out.print("Enter first number: ");
            double num1 = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Enter second number: ");
            double num2 = Double.parseDouble(scanner.nextLine());
            
            System.out.println("\nResults:");
            System.out.printf("➕ %.2f + %.2f = %.2f%n", num1, num2, calculator.add(num1, num2));
            System.out.printf("➖ %.2f - %.2f = %.2f%n", num1, num2, calculator.subtract(num1, num2));
            System.out.printf("✖️ %.2f × %.2f = %.2f%n", num1, num2, calculator.multiply(num1, num2));
            
            if (num2 != 0) {
                System.out.printf("➗ %.2f ÷ %.2f = %.2f%n", num1, num2, calculator.divide(num1, num2));
            } else {
                System.out.println("➗ Division by zero is not allowed!");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid number format!");
        }
    }
    
    private static void runTaskManagerDemo() {
        System.out.println("\n📝 Task Manager Demo");
        System.out.println("===================");
        
        while (true) {
            System.out.println("\nTask Manager Options:");
            System.out.println("1. Add task");
            System.out.println("2. List tasks");
            System.out.println("3. Complete task");
            System.out.println("4. Remove task");
            System.out.println("5. Back to main menu");
            System.out.print("Choose option: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        System.out.print("Enter task description: ");
                        String description = scanner.nextLine();
                        taskManager.addTask(description);
                        System.out.println("✅ Task added successfully!");
                        break;
                    case 2:
                        taskManager.listTasks();
                        break;
                    case 3:
                        System.out.print("Enter task ID to complete: ");
                        int completeId = Integer.parseInt(scanner.nextLine());
                        if (taskManager.completeTask(completeId)) {
                            System.out.println("✅ Task completed!");
                        } else {
                            System.out.println("❌ Task not found!");
                        }
                        break;
                    case 4:
                        System.out.print("Enter task ID to remove: ");
                        int removeId = Integer.parseInt(scanner.nextLine());
                        if (taskManager.removeTask(removeId)) {
                            System.out.println("✅ Task removed!");
                        } else {
                            System.out.println("❌ Task not found!");
                        }
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("❌ Invalid option!");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input!");
            }
        }
    }
    
    private static void runRandomNumberDemo() {
        System.out.println("\n🎲 Random Number Generator");
        System.out.println("==========================");
        
        System.out.print("Enter minimum value: ");
        try {
            int min = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter maximum value: ");
            int max = Integer.parseInt(scanner.nextLine());
            
            if (min >= max) {
                System.out.println("❌ Minimum must be less than maximum!");
                return;
            }
            
            System.out.println("\nGenerating 5 random numbers:");
            for (int i = 1; i <= 5; i++) {
                int randomNum = min + (int)(Math.random() * (max - min + 1));
                System.out.println(i + ". " + randomNum);
            }
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid number format!");
        }
    }
    
    private static void runDataProcessingDemo() {
        System.out.println("\n📊 Data Processing Demo");
        System.out.println("======================");
        
        // Create sample data
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add((int)(Math.random() * 100) + 1);
        }
        
        System.out.println("📈 Generated sample data: " + numbers);
        
        // Calculate statistics
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        double average = numbers.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        int max = numbers.stream().mapToInt(Integer::intValue).max().orElse(0);
        int min = numbers.stream().mapToInt(Integer::intValue).min().orElse(0);
        
        System.out.println("\n📊 Statistics:");
        System.out.println("Sum: " + sum);
        System.out.printf("Average: %.2f%n", average);
        System.out.println("Maximum: " + max);
        System.out.println("Minimum: " + min);
        System.out.println("Count: " + numbers.size());
        
        // Filter even numbers
        List<Integer> evenNumbers = numbers.stream()
            .filter(n -> n % 2 == 0)
            .toList();
        
        System.out.println("\n🔢 Even numbers: " + evenNumbers);
        
        // Sort in descending order
        List<Integer> sorted = numbers.stream()
            .sorted((a, b) -> b.compareTo(a))
            .toList();
        
        System.out.println("📉 Sorted (descending): " + sorted);
    }
}