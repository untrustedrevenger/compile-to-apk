interface Task {
    id: number;
    text: string;
    completed: boolean;
    createdAt: Date;
}
declare class TodoManager {
    private containerElement;
    private tasks;
    private nextId;
    constructor(containerElement: HTMLElement);
    addTask(text: string): void;
    toggleTask(id: number): void;
    deleteTask(id: number): void;
    private render;
    private saveTasks;
    private loadTasks;
}
declare class Calculator {
    static add(a: number, b: number): number;
    static subtract(a: number, b: number): number;
    static multiply(a: number, b: number): number;
    static divide(a: number, b: number): number;
}
declare class QuoteGenerator {
    private static quotes;
    static getRandomQuote(): string;
}
declare let todoManager: TodoManager;
declare function setupEventListeners(): void;
declare function changeBackgroundColor(): void;
//# sourceMappingURL=app.d.ts.map