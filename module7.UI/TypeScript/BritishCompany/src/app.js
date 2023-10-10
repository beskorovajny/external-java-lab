"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var CompanyLocationArray_1 = require("./CompanyLocationArray");
var CompanyLocationLocalStorage_1 = require("./CompanyLocationLocalStorage");
var Company_1 = require("./Company");
var Employee_1 = require("../../EuropeanCompany/src/Employee");
var CustomStorage_1 = require("./CustomStorage");
var customStorage = new CustomStorage_1.CustomStorage();
var arrayLocation = new CompanyLocationArray_1.CompanyLocationArray();
var localStorageLocation = new CompanyLocationLocalStorage_1.CompanyLocationLocalStorage("employees", customStorage);
var company1 = new Company_1.Company(arrayLocation);
var company2 = new Company_1.Company(localStorageLocation);
var employee1 = new Employee_1.Employee('Employee 1', 'Project 1');
var employee2 = new Employee_1.Employee('Employee 2', 'Project 2');
var employee3 = new Employee_1.Employee('Employee 3', 'Project 3');
var employee4 = new Employee_1.Employee('Employee 4', 'Project 4');
var employee5 = new Employee_1.Employee('Employee 5', 'Project 5');
var employee6 = new Employee_1.Employee('Employee 6', 'Project 6');
company1.add(employee1);
company1.add(employee2);
company1.add(employee3);
company2.add(employee1);
company2.add(employee4);
company2.add(employee5);
company2.add(employee6);
console.log('Company 1 Project List:', company1.getProjectList());
console.log('Company 1 Name List:', company1.getNameList());
console.log('Company 1 employees : ', arrayLocation.getCount());
console.log('Company 2 Project List:', company2.getProjectList());
console.log('Company 2 Name List:', company2.getNameList());
console.log('Company 2 employees : ', localStorageLocation.getCount());
