import {Employee} from "../../EuropeanCompany/src/Employee";
import {ILocation} from "./ILocation";

export class CompanyLocationLocalStorage implements ILocation {
    private key: string;

    constructor(key: string) {
        this.key = key;
    }

    addPerson(person: Employee): void {
        if (typeof localStorage === "undefined" || localStorage === null) {
            const LocalStorage = require('node-localstorage').LocalStorage; //need to install types/node  "npm install @types/node --save-dev"
            localStorage = new LocalStorage('./scratch');
        }
        const storedPeople = localStorage.getItem(this.key);
        const people = storedPeople ? JSON.parse(storedPeople) : [];
        people.push(person);
        localStorage.setItem(this.key, JSON.stringify(people));
    }

    getPerson(index: number): Employee | undefined {
        const storedPeople = localStorage.getItem(this.key);
        const people = storedPeople ? JSON.parse(storedPeople) : [];
        return index < people.length ? people[index] : undefined;
    }

    getCount(): number {
        const storedPeople = localStorage.getItem(this.key);
        const people = storedPeople ? JSON.parse(storedPeople) : [];
        return people.length;
    }
}