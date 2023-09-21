"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Company = void 0;
var Company = /** @class */ (function () {
    function Company(location) {
        this.location = location;
    }
    Company.prototype.add = function (employee) {
        this.location.addPerson(employee);
    };
    Company.prototype.getProjectList = function () {
        var employees = this.location.getCount();
        var projectList = [];
        for (var i = 0; i < employees; i++) {
            var employee = this.location.getPerson(i);
            if (employee) {
                projectList.push(employee.getCurrentProject());
            }
        }
        return projectList;
    };
    Company.prototype.getNameList = function () {
        var employees = this.location.getCount();
        var nameList = [];
        for (var i = 0; i < employees; i++) {
            var employee = this.location.getPerson(i);
            if (employee) {
                nameList.push(employee.getName());
            }
        }
        return nameList;
    };
    return Company;
}());
exports.Company = Company;
