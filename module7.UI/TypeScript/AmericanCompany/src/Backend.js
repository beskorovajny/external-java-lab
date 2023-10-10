"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Backend = void 0;
var Backend = /** @class */ (function () {
    function Backend(name, currentProject) {
        this.name = name;
        this.currentProject = currentProject;
    }
    Backend.prototype.getCurrentProject = function () {
        return this.currentProject;
    };
    Backend.prototype.getName = function () {
        return this.name;
    };
    return Backend;
}());
exports.Backend = Backend;
