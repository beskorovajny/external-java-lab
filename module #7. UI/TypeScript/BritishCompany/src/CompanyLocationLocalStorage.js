"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.CompanyLocationLocalStorage = void 0;
var CompanyLocationLocalStorage = /** @class */ (function () {
    function CompanyLocationLocalStorage(key) {
        this.key = key;
    }
    CompanyLocationLocalStorage.prototype.addPerson = function (person) {
        if (typeof localStorage === "undefined" || localStorage === null) {
            var LocalStorage = require('node-localstorage').LocalStorage; //need to install types/node  "npm install @types/node --save-dev"
            localStorage = new LocalStorage('./scratch');
        }
        var storedPeople = localStorage.getItem(this.key);
        var people = storedPeople ? JSON.parse(storedPeople) : [];
        people.push(person);
        localStorage.setItem(this.key, JSON.stringify(people));
    };
    CompanyLocationLocalStorage.prototype.getPerson = function (index) {
        var storedPeople = localStorage.getItem(this.key);
        var people = storedPeople ? JSON.parse(storedPeople) : [];
        return index < people.length ? people[index] : undefined;
    };
    CompanyLocationLocalStorage.prototype.getCount = function () {
        var storedPeople = localStorage.getItem(this.key);
        var people = storedPeople ? JSON.parse(storedPeople) : [];
        return people.length;
    };
    return CompanyLocationLocalStorage;
}());
exports.CompanyLocationLocalStorage = CompanyLocationLocalStorage;
