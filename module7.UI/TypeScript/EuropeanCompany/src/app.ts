import { Frontend } from './Frontend';
import { Backend } from './Backend';
import { Company } from './Company';
import {Employee} from "./Employee";

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