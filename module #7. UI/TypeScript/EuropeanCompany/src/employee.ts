import {IEmployee} from "../../AmericanCompany/src/IEmployee";

export class Employee implements IEmployee {
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
