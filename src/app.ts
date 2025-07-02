// TypeScript Demo Application
interface Task {
    id: number;
    text: string;
    completed: boolean;
    createdAt: Date;
}

class TodoManager {
    private tasks: Task[] = [];
    private nextId: number = 1;

    constructor(private containerElement: HTMLElement) {
        this.loadTasks();
        this.render();
    }

    addTask(text: string): void {
        if (text.trim() === '') return;
        
        const newTask: Task = {
            id: this.nextId++,
            text: text.trim(),
            completed: false,
            createdAt: new Date()
        };
        
        this.tasks.push(newTask);
        this.saveTasks();
        this.render();
    }

    toggleTask(id: number): void {
        const task = this.tasks.find(t => t.id === id);
        if (task) {
            task.completed = !task.completed;
            this.saveTasks();
            this.render();
        }
    }

    deleteTask(id: number): void {
        this.tasks = this.tasks.filter(t => t.id !== id);
        this.saveTasks();
        this.render();
    }

    private render(): void {
        this.containerElement.innerHTML = '';
        
        if (this.tasks.length === 0) {
            this.containerElement.innerHTML = '<p style="text-align: center; opacity: 0.7;">No tasks yet. Add one above!</p>';
            return;
        }

        this.tasks.forEach(task => {
            const taskElement = document.createElement('div');
            taskElement.className = `task-item ${task.completed ? 'completed' : ''}`;
            
            taskElement.innerHTML = `
                <span onclick="todoManager.toggleTask(${task.id})" style="cursor: pointer; flex-grow: 1;">
                    ${task.completed ? '✅' : '⭕'} ${task.text}
                </span>
                <button onclick="todoManager.deleteTask(${task.id})" style="background: #f44336; padding: 5px 10px; font-size: 12px;">
                    Delete
                </button>
            `;
            
            this.containerElement.appendChild(taskElement);
        });
    }

    private saveTasks(): void {
        localStorage.setItem('tasks', JSON.stringify(this.tasks));
    }

    private loadTasks(): void {
        const saved = localStorage.getItem('tasks');
        if (saved) {
            this.tasks = JSON.parse(saved).map((task: any) => ({
                ...task,
                createdAt: new Date(task.createdAt)
            }));
            this.nextId = Math.max(...this.tasks.map(t => t.id)) + 1;
        }
    }
}

class Calculator {
    static add(a: number, b: number): number {
        return a + b;
    }

    static subtract(a: number, b: number): number {
        return a - b;
    }

    static multiply(a: number, b: number): number {
        return a * b;
    }

    static divide(a: number, b: number): number {
        if (b === 0) {
            throw new Error("Division by zero is not allowed!");
        }
        return a / b;
    }
}

class QuoteGenerator {
    private static quotes: string[] = [
        "The only way to do great work is to love what you do. - Steve Jobs",
        "Innovation distinguishes between a leader and a follower. - Steve Jobs",
        "Life is what happens to you while you're busy making other plans. - John Lennon",
        "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
        "It is during our darkest moments that we must focus to see the light. - Aristotle",
        "The way to get started is to quit talking and begin doing. - Walt Disney",
        "Don't let yesterday take up too much of today. - Will Rogers",
        "You learn more from failure than from success. - Unknown",
        "Whether you think you can or you think you can't, you're right. - Henry Ford",
        "The only impossible journey is the one you never begin. - Tony Robbins"
    ];

    static getRandomQuote(): string {
        const randomIndex = Math.floor(Math.random() * this.quotes.length);
        return this.quotes[randomIndex];
    }
}

// Global variables
let todoManager: TodoManager;

// Main initialization
document.addEventListener('DOMContentLoaded', () => {
    console.log('🚀 TypeScript application loaded!');
    
    // Initialize todo manager
    const taskListElement = document.getElementById('taskList') as HTMLElement;
    todoManager = new TodoManager(taskListElement);
    
    // Set up event listeners
    setupEventListeners();
    
    // Show welcome message
    console.log('📝 Todo Manager initialized');
    console.log('🧮 Calculator ready');
    console.log('🎲 Quote generator loaded');
    console.log('☕ Check out the Java files in this project!');
});

function setupEventListeners(): void {
    // Todo input handling
    const taskInput = document.getElementById('taskInput') as HTMLInputElement;
    const addTaskBtn = document.getElementById('addTaskBtn') as HTMLButtonElement;
    
    addTaskBtn.addEventListener('click', () => {
        todoManager.addTask(taskInput.value);
        taskInput.value = '';
    });
    
    taskInput.addEventListener('keypress', (e) => {
        if (e.key === 'Enter') {
            todoManager.addTask(taskInput.value);
            taskInput.value = '';
        }
    });
    
    // Random quote button
    const randomQuoteBtn = document.getElementById('randomQuoteBtn') as HTMLButtonElement;
    randomQuoteBtn.addEventListener('click', () => {
        const quote = QuoteGenerator.getRandomQuote();
        const quoteDisplay = document.getElementById('quoteDisplay') as HTMLElement;
        quoteDisplay.innerHTML = `<p style="font-style: italic; margin-top: 15px; padding: 15px; background: rgba(255,255,255,0.1); border-radius: 6px;">"${quote}"</p>`;
    });
    
    // Color change button
    const colorChangeBtn = document.getElementById('colorChangeBtn') as HTMLButtonElement;
    colorChangeBtn.addEventListener('click', changeBackgroundColor);
}

// Global calculator function (called from HTML)
(window as any).calculate = function(operation: string): void {
    const num1Input = document.getElementById('num1') as HTMLInputElement;
    const num2Input = document.getElementById('num2') as HTMLInputElement;
    const output = document.getElementById('output') as HTMLElement;
    
    const num1 = parseFloat(num1Input.value);
    const num2 = parseFloat(num2Input.value);
    
    if (isNaN(num1) || isNaN(num2)) {
        output.innerHTML = '<span style="color: #ff6b6b;">❌ Please enter valid numbers!</span>';
        return;
    }
    
    try {
        let result: number;
        let operationSymbol: string;
        
        switch (operation) {
            case 'add':
                result = Calculator.add(num1, num2);
                operationSymbol = '+';
                break;
            case 'subtract':
                result = Calculator.subtract(num1, num2);
                operationSymbol = '-';
                break;
            case 'multiply':
                result = Calculator.multiply(num1, num2);
                operationSymbol = '×';
                break;
            case 'divide':
                result = Calculator.divide(num1, num2);
                operationSymbol = '÷';
                break;
            default:
                throw new Error('Invalid operation');
        }
        
        output.innerHTML = `
            <span style="color: #4CAF50;">✅ Result: ${num1} ${operationSymbol} ${num2} = ${result}</span>
        `;
    } catch (error) {
        output.innerHTML = `<span style="color: #ff6b6b;">❌ Error: ${error instanceof Error ? error.message : 'Unknown error'}</span>`;
    }
};

// Global function to show Java info
(window as any).showJavaInfo = function(): void {
    alert(`☕ Java Project Info:
    
📁 Files created:
• HelloWorld.java - Basic Java application
• Calculator.java - Java calculator class
• TaskManager.java - Java task management system

🔧 To run the Java code:
1. Make sure you have Java installed (javac and java commands)
2. Compile: javac *.java
3. Run: java HelloWorld

💡 The Java code demonstrates:
• Object-oriented programming
• Data structures (ArrayList)
• Exception handling
• Console I/O

Check the console for more details!`);
    
    console.log('☕ Java files in this project:');
    console.log('📄 HelloWorld.java - Entry point application');
    console.log('📄 Calculator.java - Mathematical operations');
    console.log('📄 TaskManager.java - Task management system');
    console.log('🔧 Run: javac *.java && java HelloWorld');
};

function changeBackgroundColor(): void {
    const colors = [
        'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
        'linear-gradient(135deg, #f093fb 0%, #f5576c 100%)',
        'linear-gradient(135deg, #4facfe 0%, #00f2fe 100%)',
        'linear-gradient(135deg, #43e97b 0%, #38f9d7 100%)',
        'linear-gradient(135deg, #fa709a 0%, #fee140 100%)',
        'linear-gradient(135deg, #a8edea 0%, #fed6e3 100%)',
        'linear-gradient(135deg, #ff9a9e 0%, #fecfef 100%)',
        'linear-gradient(135deg, #ffecd2 0%, #fcb69f 100%)'
    ];
    
    const randomColor = colors[Math.floor(Math.random() * colors.length)];
    document.body.style.background = randomColor;
    console.log('🎨 Background color changed!');
}