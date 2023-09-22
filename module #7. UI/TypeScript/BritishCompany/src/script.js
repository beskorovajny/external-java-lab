"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.saveButton = exports.displayEmployees = exports.saveEmployee = exports.company = exports.localStorageLocation = void 0;
var Company_1 = require("./Company");
var CompanyLocationLocalStorage_1 = require("./CompanyLocationLocalStorage");
var Employee_1 = require("../../EuropeanCompany/src/Employee");
exports.localStorageLocation = new CompanyLocationLocalStorage_1.CompanyLocationLocalStorage('employees');
exports.company = new Company_1.Company(exports.localStorageLocation);
function saveEmployee() {
    alert('Save button');
    var employeeInput = document.getElementById("employeeInput");
    var employeeName = employeeInput.value.trim();
    if (employeeName) {
        var employee = new Employee_1.Employee(employeeName, "Project");
        exports.company.add(employee);
        employeeInput.value = "";
        displayEmployees();
    }
    else {
        alert("Please enter a valid employee name.");
    }
}
exports.saveEmployee = saveEmployee;
function displayEmployees() {
    var employeeList = document.getElementById("employeeList");
    employeeList.innerHTML = "";
    var existingEmployees = exports.company.getNameList() ? exports.company.getNameList() : [];
    if (existingEmployees.length <= 0) {
        alert("Employees not found");
    }
    existingEmployees.forEach(function (employee) {
        var listItem = document.createElement("li");
        listItem.textContent = employee;
        employeeList.appendChild(listItem);
    });
}
exports.displayEmployees = displayEmployees;
exports.saveButton = document.getElementById("saveButton");
exports.saveButton.addEventListener("click", saveEmployee, false);
displayEmployees();
