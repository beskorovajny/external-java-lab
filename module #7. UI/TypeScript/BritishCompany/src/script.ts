import {Company} from "./Company";
import {CompanyLocationLocalStorage} from "./CompanyLocationLocalStorage";
import {Employee} from "../../EuropeanCompany/src/Employee";

export const localStorageLocation = new CompanyLocationLocalStorage('employees');
export const company = new Company(localStorageLocation);
export function saveEmployee() {
    alert('Save button')
    const employeeInput = document.getElementById("employeeInput") as HTMLInputElement;

    const employeeName = employeeInput.value.trim();

    if (employeeName) {
        const employee = new Employee(employeeName, "Project");
        company.add(employee)

        employeeInput.value = "";

        displayEmployees();
    } else {
        alert("Please enter a valid employee name.");
    }
}


export function displayEmployees() {
    const employeeList = document.getElementById("employeeList") as HTMLUListElement;
    employeeList.innerHTML = "";

    const existingEmployees = company.getNameList() ? company.getNameList() : []

    if (existingEmployees.length <= 0) {
        alert("Employees not found")
    }

    existingEmployees.forEach((employee: string) => {
        const listItem = document.createElement("li");
        listItem.textContent = employee;
        employeeList.appendChild(listItem);
    });
}

export const saveButton = document.getElementById("saveButton") as HTMLButtonElement;
saveButton.addEventListener("click", saveEmployee, false);

displayEmployees();
