import {CompanyLocationArray} from "./CompanyLocationArray";
import {CompanyLocationLocalStorage} from "./CompanyLocationLocalStorage";
import {Company} from "./Company";
import {Employee} from "../../EuropeanCompany/src/Employee";
import {CustomStorage} from "./CustomStorage";

const customStorage = new CustomStorage();
const arrayLocation = new CompanyLocationArray();
const localStorageLocation = new CompanyLocationLocalStorage("employees", customStorage);

const company1 = new Company(arrayLocation);
const company2 = new Company(localStorageLocation)

const employee1 = new Employee('Employee 1', 'Project 1');
const employee2 = new Employee('Employee 2', 'Project 2');
const employee3 = new Employee('Employee 3', 'Project 3');

const employee4 = new Employee('Employee 4', 'Project 4');
const employee5 = new Employee('Employee 5', 'Project 5');
const employee6 = new Employee('Employee 6', 'Project 6');

company1.add(employee1);
company1.add(employee2);
company1.add(employee3);

company2.add(employee1);
company2.add(employee4);
company2.add(employee5);
company2.add(employee6);


console.log('Company 1 Project List:', company1.getProjectList());
console.log('Company 1 Name List:', company1.getNameList());
console.log('Company 1 employees : ', arrayLocation.getCount())

console.log('Company 2 Project List:', company2.getProjectList());
console.log('Company 2 Name List:', company2.getNameList());
console.log('Company 2 employees : ', localStorageLocation.getCount())
