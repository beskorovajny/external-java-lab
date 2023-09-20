import { Frontend } from './frontend';
import { Backend } from './backend';
import { Company } from './company';
import {Employee} from "./employee";

const company = new Company();
const employee1 = new Frontend("John Doe", "Project X (frontend)");
const employee2 = new Backend("Jane Smith", "Project Y (backend)");
const employee = new Employee("New Employee", "Project undefined")

company.add(employee1);
company.add(employee2);
company.add(employee)

console.log("Employee Names:");
console.log(company.getNameList());

console.log("\nEmployee Projects:");
console.log(company.getProjectList());