import { Employee } from './Employee';
import {IEmployee} from "../../AmericanCompany/src/IEmployee";
export class Company {
    //private employees: Employee[] = []; for Task 10
    private employees: IEmployee[] = [];

    add(employee: IEmployee): void {
        this.employees.push(employee);
    }

    getProjectList(): string[] {
        return this.employees.map(employee => employee.getCurrentProject());
    }

    getNameList(): string[] {
        return this.employees.map(employee => employee.getName());
    }
}