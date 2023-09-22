import {Employee} from "../../EuropeanCompany/src/Employee";

export interface ILocation {
    addPerson(person: Employee): void;
    getPerson(index: number): Employee | undefined;
    getCount(): number;
}