"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.CompanyLocationArray = void 0;
var CompanyLocationArray = /** @class */ (function () {
    function CompanyLocationArray() {
        this.people = [];
    }
    CompanyLocationArray.prototype.addPerson = function (person) {
        this.people.push(person);
    };
    CompanyLocationArray.prototype.getPerson = function (index) {
        return this.people[index];
    };
    CompanyLocationArray.prototype.getCount = function () {
        return this.people.length;
    };
    return CompanyLocationArray;
}());
exports.CompanyLocationArray = CompanyLocationArray;
