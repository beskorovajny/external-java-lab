import {ILocation} from "./ILocation";
import {Employee} from "../../EuropeanCompany/src/Employee";

export class CompanyLocationArray implements ILocation {
    private employees: Employee[] = [];

    addPerson(person: Employee): void {
        this.employees.push(person);
    }

    getPerson(index: number): Employee | undefined {
        return this.employees[index];
    }

    getCount(): number {
        return this.employees.length;
    }
}