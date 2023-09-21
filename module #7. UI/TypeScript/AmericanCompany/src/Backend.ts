import {IEmployee} from "./IEmployee";

export class Backend implements IEmployee {
    constructor(private name: string, private currentProject: string) {}

    getCurrentProject(): string {
        return this.currentProject;
    }

    getName(): string {
        return this.name;
    }
}