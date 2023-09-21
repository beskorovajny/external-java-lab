import {ILocation} from "./ILocation";
import {Employee} from "../../EuropeanCompany/src/Employee";

export class Company {
    constructor(private location: ILocation) {}

    add(employee: Employee): void {
        this.location.addPerson(employee);
    }

    getProjectList(): string[] {
        const employees = this.location.getCount();
        const projectList: string[] = [];

        for (let i = 0; i < employees; i++) {
            const employee = this.location.getPerson(i);
            if (employee) {
                projectList.push(employee.getCurrentProject());
            }
        }

        return projectList;
    }

    getNameList(): string[] {
        const employees = this.location.getCount();
        const nameList: string[] = [];

        for (let i = 0; i < employees; i++) {
            const employee = this.location.getPerson(i);
            if (employee) {
                nameList.push(employee.getName());
            }
        }

        return nameList;
    }
}