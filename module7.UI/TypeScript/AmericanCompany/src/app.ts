import {Frontend} from "./Frontend";
import {Backend} from "./Backend";
import {Company} from "../../EuropeanCompany/src/Company";

const company = new Company();

const frontendEmployee1 = new Frontend("Frontend Employee 1", "Frontend Project A");
const frontendEmployee2 = new Frontend("Frontend Employee 2", "Frontend Project B");
const backendEmployee1 = new Backend("Backend Employee 1", "Backend Project X");
const backendEmployee2 = new Backend("Backend Employee 2", "Backend Project Y");

company.add(frontendEmployee1);
company.add(frontendEmployee2);
company.add(backendEmployee1);
company.add(backendEmployee2);

console.log("Project List:");
console.log(company.getProjectList());

console.log("\nName List:");
console.log(company.getNameList());