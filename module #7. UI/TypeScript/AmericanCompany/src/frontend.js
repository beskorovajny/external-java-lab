"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Frontend = void 0;
var Frontend = /** @class */ (function () {
    function Frontend(name, currentProject) {
        this.name = name;
        this.currentProject = currentProject;
    }
    Frontend.prototype.getCurrentProject = function () {
        return this.currentProject;
    };
    Frontend.prototype.getName = function () {
        return this.name;
    };
    return Frontend;
}());
exports.Frontend = Frontend;
