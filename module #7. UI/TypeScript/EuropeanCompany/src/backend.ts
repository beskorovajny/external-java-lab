import { Employee } from "./employee";
export class Backend extends Employee {
    constructor(name: string, currentProject: string) {
        super(name, currentProject);
    }
}