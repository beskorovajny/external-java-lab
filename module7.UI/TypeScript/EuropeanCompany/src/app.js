"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var Frontend_1 = require("./Frontend");
var Backend_1 = require("./Backend");
var Company_1 = require("./Company");
var Employee_1 = require("./Employee");
var company = new Company_1.Company();
var employee1 = new Frontend_1.Frontend("John Doe", "Project X (frontend)");
var employee2 = new Backend_1.Backend("Jane Smith", "Project Y (backend)");
var employee = new Employee_1.Employee("New Employee", "Project undefined");
company.add(employee1);
company.add(employee2);
company.add(employee);
console.log("Employee Names:");
console.log(company.getNameList());
console.log("\nEmployee Projects:");
console.log(company.getProjectList());
