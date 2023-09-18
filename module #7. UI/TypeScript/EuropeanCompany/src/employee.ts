export class Employee {
    private name: string;
    private currentProject: string;

    constructor(name: string, currentProject: string) {
        this.name = name;
        this.currentProject = currentProject;
    }

    getName(): string {
        return this.name;
    }

    getCurrentProject(): string {
        return this.currentProject;
    }
}
