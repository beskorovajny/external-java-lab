import {Employee} from "../../EuropeanCompany/src/Employee";
import {ILocation} from "./ILocation"
export class Location implements ILocation {
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