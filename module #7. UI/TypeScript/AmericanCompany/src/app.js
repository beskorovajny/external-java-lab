"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var frontend_1 = require("./frontend");
var backend_1 = require("./backend");
var company_1 = require("../../EuropeanCompany/src/company");
var company = new company_1.Company();
// Create Frontend and Backend employees and add them to the company
var frontendEmployee1 = new frontend_1.Frontend("Frontend Employee 1", "Frontend Project A");
var frontendEmployee2 = new frontend_1.Frontend("Frontend Employee 2", "Frontend Project B");
var backendEmployee1 = new backend_1.Backend("Backend Employee 1", "Backend Project X");
var backendEmployee2 = new backend_1.Backend("Backend Employee 2", "Backend Project Y");
company.add(frontendEmployee1);
company.add(frontendEmployee2);
company.add(backendEmployee1);
company.add(backendEmployee2);
// Display the result of getProjectList and getNameList methods in the console
console.log("Project List:");
console.log(company.getProjectList());
console.log("\nName List:");
console.log(company.getNameList());
