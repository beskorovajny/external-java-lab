"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.CompanyLocationLocalStorage = void 0;
var CompanyLocationLocalStorage = /** @class */ (function () {
    function CompanyLocationLocalStorage(key, customStorage) {
        this.key = key;
        this.customStorage = customStorage;
    }
    CompanyLocationLocalStorage.prototype.addPerson = function (person) {
        var storedPeople = this.customStorage.getItem(this.key); // localStorage use const storedPeople = localStorage.getItem(this.key);
        var employees = storedPeople || []; // localStorage use const employees = storedPeople ? JSON.parse(storedPeople) : [];
        employees.push(person);
        this.customStorage.setItem(this.key, employees); //localStorage use  localStorage.setItem(this.key, JSON.stringify(employees));
    };
    CompanyLocationLocalStorage.prototype.getPerson = function (index) {
        var storedPeople = this.customStorage.getItem(this.key); // const storedPeople = localStorage.getItem(this.key);
        var employees = storedPeople || []; // const people = storedPeople ? JSON.parse(storedPeople) : [];
        return index < employees.length ? employees[index] : undefined;
    };
    CompanyLocationLocalStorage.prototype.getCount = function () {
        var employees = this.customStorage.getItem(this.key);
        // add this line if you want to use localStorage: const employees = storedPeople ? JSON.parse(storedPeople) : [];
        return employees.length;
    };
    return CompanyLocationLocalStorage;
}());
exports.CompanyLocationLocalStorage = CompanyLocationLocalStorage;
