"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.CompanyLocationArray = void 0;
var CompanyLocationArray = /** @class */ (function () {
    function CompanyLocationArray() {
        this.employees = [];
    }
    CompanyLocationArray.prototype.addPerson = function (person) {
        this.employees.push(person);
    };
    CompanyLocationArray.prototype.getPerson = function (index) {
        return this.employees[index];
    };
    CompanyLocationArray.prototype.getCount = function () {
        return this.employees.length;
    };
    return CompanyLocationArray;
}());
exports.CompanyLocationArray = CompanyLocationArray;
