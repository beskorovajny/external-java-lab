import { Employee } from "./employee";
export class Frontend extends Employee {
    constructor(name: string, currentProject: string) {
        super(name, currentProject);
    }
}