"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var CompanyLocationArray_1 = require("./CompanyLocationArray");
var CompanyLocationLocalStorage_1 = require("./CompanyLocationLocalStorage");
var Company_1 = require("./Company");
var Employee_1 = require("../../EuropeanCompany/src/Employee");
var arrayLocation = new CompanyLocationArray_1.CompanyLocationArray();
var localStorageLocation = new CompanyLocationLocalStorage_1.CompanyLocationLocalStorage('employees');
var company1 = new Company_1.Company(arrayLocation);
var company2 = new Company_1.Company(localStorageLocation);
var employee1 = new Employee_1.Employee('Employee 1', 'Project 1');
var employee2 = new Employee_1.Employee('Employee 2', 'Project 2');
var employee3 = new Employee_1.Employee('Employee 3', 'Project 3');
company1.add(employee1);
company1.add(employee2);
company1.add(employee3);
company2.add(employee2);
company2.add(employee3);
console.log('Company 1 Project List:', company1.getProjectList());
console.log('Company 1 Name List:', company1.getNameList());
console.log('Company 1 employee : ', arrayLocation.getCount());
/*
console.log('\nCompany 2 Project List:', company2.getProjectList());
console.log('Company 2 Name List:', company2.getNameList());*/
