import {Employee} from "../../EuropeanCompany/src/Employee";
import {ILocation} from "./ILocation";
import {CustomStorage} from "./CustomStorage"

export class CompanyLocationLocalStorage implements ILocation {
    private key: string;
    private customStorage: CustomStorage;

    constructor(key: string, customStorage: CustomStorage) {
        this.key = key;
        this.customStorage = customStorage;
    }

    addPerson(person: Employee): void {
        const storedPeople = this.customStorage.getItem(this.key); // localStorage use const storedPeople = localStorage.getItem(this.key);
        const employees = storedPeople || [];// localStorage use const employees = storedPeople ? JSON.parse(storedPeople) : [];

        employees.push(person);

        this.customStorage.setItem(this.key, employees); //localStorage use  localStorage.setItem(this.key, JSON.stringify(employees));
    }

    getPerson(index: number): Employee | undefined {
        const storedPeople = this.customStorage.getItem(this.key); // const storedPeople = localStorage.getItem(this.key);
        const employees = storedPeople || []; // const people = storedPeople ? JSON.parse(storedPeople) : [];

        return index < employees.length ? employees[index] : undefined;
    }

    getCount(): number {
        const employees = this.customStorage.getItem(this.key);
        // add this line if you want to use localStorage: const employees = storedPeople ? JSON.parse(storedPeople) : [];
        return employees.length;
    }
}