'use strict';

/**
 * You must return a date that comes in a predetermined number of seconds after 01.06.2020 00:00:002020
 * @param {number} seconds
 * @returns {Date}
 *
 * @example
 *      31536000 -> 01.06.2021
 *      0 -> 01.06.2020
 *      86400 -> 02.06.2020
 */
function secondsToDate(seconds) {
    // Base date
    const baseDate = new Date("2020-06-01T00:00:00.000Z");
    // Calculate the target date
    if (seconds <= 0) {
        return baseDate;
    }
    return new Date(baseDate.getTime() + seconds * 1000);
}

function formatDateToDdMmYyyy(isoDate) {
    let date = new Date(isoDate);
    let day = String(date.getDate()).padStart(2, '0');
    let month = String(date.getMonth() + 1).padStart(2, '0');
    let year = date.getFullYear();

    return `${day}.${month}.${year}`;
}
console.log('Proof of working the [secondsToDate] function\n')

console.log(formatDateToDdMmYyyy(secondsToDate(31536000)))
console.log(formatDateToDdMmYyyy(secondsToDate(0)))
console.log(formatDateToDdMmYyyy(secondsToDate(86400)))

console.log('\nEnd of proof for [secondsToDate] function...\n')
/**
 * You must create a function that returns a base 2 (binary) representation of a base 10 (decimal) string number
 * ! Numbers will always be below 1024 (not including 1024)
 * ! You are not able to use parseInt
 * @param {number} decimal
 * @return {string}
 *
 * @example
 *      5 -> "101"
 *      10 -> "1010"
 */
function toBase2Converter(decimal) {
    if (isNaN(decimal) || decimal >= 1024) {
        throw new Error("Invalid input or number out of range");
    }

    let binary = "";
    let tempDecimal = decimal;

    while (tempDecimal > 0) {
        binary = (tempDecimal % 2) + binary;
        tempDecimal = Math.floor(tempDecimal / 2);
    }

    if (binary === "") {
        binary = "0";
    }

    return binary;
}
console.log('Proof of working the [toBase2Converter] function\n')

console.log(toBase2Converter(5))
console.log(toBase2Converter(10))

console.log('\nEnd of proof for [toBase2Converter] function...\n')

/**
 * You must create a function that takes two strings as arguments and returns the number of times the first string
 * is found in the text.
 * @param {string} substring
 * @param {string} text
 * @return {number}
 *
 * @example
 *      'a', 'test it' -> 0
 *      't', 'test it' -> 2
 *      'T', 'test it' -> 2
 */
function substringOccurrencesCounter(substring, text) {
    let count= 0;
    let position= text.toLowerCase().indexOf(substring.toLowerCase());

    while (position !== -1) {
        count++;
        position = text.toLowerCase().indexOf(substring.toLowerCase(), position + 1);
    }

    return count;
}
console.log('Proof of working the [substringOccurrencesCounter] function\n')

console.log(substringOccurrencesCounter('a', 'test it'))
console.log(substringOccurrencesCounter('t', 'test it'))
console.log(substringOccurrencesCounter('T', 'test it'))

console.log('\nEnd of proof for [substringOccurrencesCounter] function...\n')

/**
 * You must create a function that takes a string and returns a string in which each character is repeated once.
 *
 * @param {string} string
 * @return {string}
 *
 * @example
 *      "Hello" -> "HHeelloo"
 *      "Hello world" -> "HHeello  wworrldd" // o, l is repeated more than once. Space was also repeated
 */
function repeatingLitters(string) {
    if (string.length === 0) {
        throw new Error("Invalid input")
    }

    let result = "";
    let charCount = {};

    for (const char of string) {
        charCount[char] = (charCount[char] || 0) + 1;
    }

    for (const char of string) {
        if (charCount[char] === 1) {
            result += char + char;
        } else {
            result += char;
        }
    }

    return result;
}

console.log('Proof of working the [repeatingLitters] function\n')

console.log(repeatingLitters('Hello'))
console.log(repeatingLitters('Hello world'))

console.log('\nEnd of proof for [repeatingLitters] function...\n')

/**
 * You must write a function redundant that takes in a string str and returns a function that returns str.
 * ! Your function should return a function, not a string.
 *
 * @param {string} str
 * @return {function}
 *
 * @example
 *      const f1 = redundant("apple")
 *      f1() ➞ "apple"
 *
 *      const f2 = redundant("pear")
 *      f2() ➞ "pear"
 *
 *      const f3 = redundant("")
 *      f3() ➞ ""
 */
function redundant(str) {
    return function() {
        return str;
    };
}

const f1 = redundant("apple")
const f2 = redundant("pear")
const f3 = redundant("")

console.log('Proof of working the [redundant] function\n')

console.log(f1())
console.log(f2())
console.log(f3())

console.log('\nEnd of proof for [redundant] function...\n')

/**
 * https://en.wikipedia.org/wiki/Tower_of_Hanoi
 *
 * @param {number} disks
 * @return {number}
 */
function towerHanoi(disks) {
    if (disks <= 0) {
        throw new Error("Number of disks must be positive.");
    }

    return Math.pow(2, disks) - 1;
}

console.log('Proof of working the [towerHanoi] function\n')

const numDisks1 = 3;
const movesRequired1 = towerHanoi(numDisks1);
const numDisks2 = 5;
const movesRequired2 = towerHanoi(numDisks2);
const numDisks3 = 10;
const movesRequired3 = towerHanoi(numDisks3);

console.log(`Moves required for ${numDisks1} disks : ${movesRequired1}`)
console.log(`Moves required for ${numDisks2} disks : ${movesRequired2}`)
console.log(`Moves required for ${numDisks3} disks : ${movesRequired3}`)

console.log('\nEnd of proof for [towerHanoi] function...\n')


/**
 * You must create a function that multiplies two matrices (n x n each).
 *
 * @param {array} matrix1
 * @param {array} matrix2
 * @return {array}
 *
 */
function matrixMultiplication(matrix1, matrix2) {
    const n = matrix1.length;
    const result = [];

    for (let i = 0; i < n; i++) {
        result[i] = [];
        for (let j = 0; j < n; j++) {
            let sum = 0;
            for (let k = 0; k < n; k++) {
                sum += matrix1[i][k] * matrix2[k][j];
            }
            result[i][j] = sum;
        }
    }

    return result;
}

// Matrix (2x2 for demonstration)
const matrixA = [[1, 2], [3, 4]];
const matrixB = [[5, 6], [7, 8]];

// Matrix (3x3 for demonstration)
const matrixA1 = [[1, 2, 3], [3, 4, 5], [3, 3, 3]];
const matrixB1 = [[5, 6, 7], [7, 8, 9], [5, 2, 9]];

console.log('Proof of working the [matrixMultiplication] function\n')

console.log('2x2 example :')
console.log(matrixMultiplication(matrixA, matrixB))
console.log('\n3x3 example :')
console.log(matrixMultiplication(matrixA1, matrixB1))

console.log('\nEnd of proof for [matrixMultiplication] function...\n')

/**
 * Create a gather function that accepts a string argument and returns another function.
 * The function calls should support continued chaining until order is called.
 * order should accept a number as an argument and return another function.
 * The function calls should support continued chaining until get is called.
 * get should return all the arguments provided to the gather functions as a string in the order specified in the order functions.
 *
 * @param {string} str
 * @return {string}
 *
 * @example
 *      gather("a")("b")("c").order(0)(1)(2).get() ➞ "abc"
 *      gather("a")("b")("c").order(2)(1)(0).get() ➞ "cba"
 *      gather("e")("l")("o")("l")("!")("h").order(5)(0)(1)(3)(2)(4).get()  ➞ "hello"
 *
 * @author Bezkorovainyi Andrii in cooperation with Pavlo X
 *
 */


function gather(str) {
    const args = [];
    const orderedArr = [];

    function gatherFunction(nextArg) {
        args.push(nextArg);
        return gatherFunction;
    }

    gatherFunction.order = function (nextArg) {
        orderedArr.push(nextArg)
        return gatherFunction.order;
    }


    gatherFunction.order.get = function () {
        return orderedArr.map((order) => args[order]).join('');
    };

    return gatherFunction(str);
}

const result1 = gather('a')('b')('c').order(0)(1)(2).get()
const result2 = gather('a')('b')('c').order(2)(1)(0).get()
const result3 = gather('e')('l')('o')('l')('!')('h').order(5)(0)(1)(3)(2)(4).get()

console.log('Proof of working the [gather] function\n')

console.log(result1)

console.log(result2)

console.log(result3)

console.log('\nEnd of proof for [gather] function...\n')

