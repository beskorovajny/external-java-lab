"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Employee = void 0;
var Employee = /** @class */ (function () {
    function Employee(name, currentProject) {
        this.name = name;
        this.currentProject = currentProject;
    }
    Employee.prototype.getName = function () {
        return this.name;
    };
    Employee.prototype.getCurrentProject = function () {
        return this.currentProject;
    };
    return Employee;
}());
exports.Employee = Employee;
