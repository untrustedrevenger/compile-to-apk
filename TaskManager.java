import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TaskManager - A comprehensive task management system in Java
 * Demonstrates ArrayList usage, object-oriented programming, and date handling
 */
public class TaskManager {
    private List<Task> tasks;
    private int nextId;
    
    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.nextId = 1;
    }
    
    /**
     * Inner class representing a Task
     */
    public static class Task {
        private int id;
        private String description;
        private boolean completed;
        private LocalDateTime createdAt;
        private LocalDateTime completedAt;
        private String priority;
        
        public Task(int id, String description) {
            this.id = id;
            this.description = description;
            this.completed = false;
            this.createdAt = LocalDateTime.now();
            this.completedAt = null;
            this.priority = "Medium";
        }
        
        // Getters and setters
        public int getId() { return id; }
        public String getDescription() { return description; }
        public boolean isCompleted() { return completed; }
        public LocalDateTime getCreatedAt() { return createdAt; }
        public LocalDateTime getCompletedAt() { return completedAt; }
        public String getPriority() { return priority; }
        
        public void setDescription(String description) { this.description = description; }
        public void setPriority(String priority) { this.priority = priority; }
        
        public void complete() {
            this.completed = true;
            this.completedAt = LocalDateTime.now();
        }
        
        public void uncomplete() {
            this.completed = false;
            this.completedAt = null;
        }
        
        @Override
        public String toString() {
            String status = completed ? "✅" : "⭕";
            String dateStr = createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return String.format("%s [%d] %s (Priority: %s, Created: %s)", 
                               status, id, description, priority, dateStr);
        }
    }
    
    /**
     * Add a new task
     */
    public void addTask(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Task description cannot be empty!");
        }
        
        Task newTask = new Task(nextId++, description.trim());
        tasks.add(newTask);
    }
    
    /**
     * Add a task with priority
     */
    public void addTask(String description, String priority) {
        addTask(description);
        Task lastTask = tasks.get(tasks.size() - 1);
        lastTask.setPriority(priority);
    }
    
    /**
     * Complete a task by ID
     */
    public boolean completeTask(int id) {
        Task task = findTaskById(id);
        if (task != null && !task.isCompleted()) {
            task.complete();
            return true;
        }
        return false;
    }
    
    /**
     * Remove a task by ID
     */
    public boolean removeTask(int id) {
        return tasks.removeIf(task -> task.getId() == id);
    }
    
    /**
     * Find a task by ID
     */
    public Task findTaskById(int id) {
        return tasks.stream()
                   .filter(task -> task.getId() == id)
                   .findFirst()
                   .orElse(null);
    }
    
    /**
     * List all tasks
     */
    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("📋 No tasks found. Add some tasks to get started!");
            return;
        }
        
        System.out.println("\n📋 Task List:");
        System.out.println("=============");
        
        // Separate completed and pending tasks
        List<Task> pendingTasks = tasks.stream()
                                     .filter(task -> !task.isCompleted())
                                     .toList();
        
        List<Task> completedTasks = tasks.stream()
                                       .filter(Task::isCompleted)
                                       .toList();
        
        if (!pendingTasks.isEmpty()) {
            System.out.println("\n🔄 Pending Tasks:");
            pendingTasks.forEach(System.out::println);
        }
        
        if (!completedTasks.isEmpty()) {
            System.out.println("\n✅ Completed Tasks:");
            completedTasks.forEach(System.out::println);
        }
        
        System.out.printf("\n📊 Summary: %d total, %d pending, %d completed\n", 
                         tasks.size(), pendingTasks.size(), completedTasks.size());
    }
    
    /**
     * List tasks by priority
     */
    public void listTasksByPriority(String priority) {
        List<Task> filteredTasks = tasks.stream()
                                      .filter(task -> task.getPriority().equalsIgnoreCase(priority))
                                      .toList();
        
        if (filteredTasks.isEmpty()) {
            System.out.println("No tasks found with priority: " + priority);
            return;
        }
        
        System.out.println("\n📋 Tasks with priority '" + priority + "':");
        filteredTasks.forEach(System.out::println);
    }
    
    /**
     * Get statistics
     */
    public void printStatistics() {
        int total = tasks.size();
        long completed = tasks.stream().mapToLong(task -> task.isCompleted() ? 1 : 0).sum();
        long pending = total - completed;
        
        // Count by priority
        long highPriority = tasks.stream().mapToLong(task -> "High".equalsIgnoreCase(task.getPriority()) ? 1 : 0).sum();
        long mediumPriority = tasks.stream().mapToLong(task -> "Medium".equalsIgnoreCase(task.getPriority()) ? 1 : 0).sum();
        long lowPriority = tasks.stream().mapToLong(task -> "Low".equalsIgnoreCase(task.getPriority()) ? 1 : 0).sum();
        
        System.out.println("\n📊 Task Statistics:");
        System.out.println("==================");
        System.out.println("Total Tasks: " + total);
        System.out.println("Completed: " + completed);
        System.out.println("Pending: " + pending);
        if (total > 0) {
            System.out.printf("Completion Rate: %.1f%%\n", (completed * 100.0 / total));
        }
        System.out.println("\nBy Priority:");
        System.out.println("  High: " + highPriority);
        System.out.println("  Medium: " + mediumPriority);
        System.out.println("  Low: " + lowPriority);
    }
    
    /**
     * Clear all completed tasks
     */
    public int clearCompletedTasks() {
        int removedCount = (int) tasks.stream().mapToLong(task -> task.isCompleted() ? 1 : 0).sum();
        tasks.removeIf(Task::isCompleted);
        return removedCount;
    }
    
    /**
     * Get all tasks (for external access)
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks); // Return a copy to prevent external modification
    }
    
    /**
     * Get task count
     */
    public int getTaskCount() {
        return tasks.size();
    }
    
    /**
     * Check if there are any pending tasks
     */
    public boolean hasPendingTasks() {
        return tasks.stream().anyMatch(task -> !task.isCompleted());
    }
}