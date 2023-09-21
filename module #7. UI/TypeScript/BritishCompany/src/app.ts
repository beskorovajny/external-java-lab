import {CompanyLocationArray} from "./CompanyLocationArray";
import {CompanyLocationLocalStorage} from "./CompanyLocationLocalStorage";
import {Company} from "./Company";
import {Employee} from "../../EuropeanCompany/src/Employee";

const arrayLocation = new CompanyLocationArray();

const company1 = new Company(arrayLocation);

const employee1 = new Employee('Employee 1', 'Project 1');
const employee2 = new Employee('Employee 2', 'Project 2');
const employee3 = new Employee('Employee 3', 'Project 3');

company1.add(employee1);
company1.add(employee2);
company1.add(employee3);


console.log('Company 1 Project List:', company1.getProjectList());
console.log('Company 1 Name List:', company1.getNameList());
console.log('Company 1 employee : ', arrayLocation.getCount())
