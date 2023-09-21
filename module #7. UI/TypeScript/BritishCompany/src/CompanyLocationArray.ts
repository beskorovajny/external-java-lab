import {ILocation} from "./ILocation";
import {Employee} from "../../EuropeanCompany/src/Employee";

export class CompanyLocationArray implements ILocation {
    private people: Employee[] = [];

    addPerson(person: Employee): void {
        this.people.push(person);
    }

    getPerson(index: number): Employee | undefined {
        return this.people[index];
    }

    getCount(): number {
        return this.people.length;
    }
}