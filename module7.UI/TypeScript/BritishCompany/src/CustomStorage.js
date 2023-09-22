"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.CustomStorage = void 0;
var CustomStorage = /** @class */ (function () {
    function CustomStorage() {
        this.storage = new Map();
    }
    CustomStorage.prototype.setItem = function (key, value) {
        this.storage.set(key, value);
    };
    CustomStorage.prototype.getItem = function (key) {
        var item = this.storage.get(key);
        return item !== undefined ? item : null;
    };
    CustomStorage.prototype.removeItem = function (key) {
        this.storage.delete(key);
    };
    CustomStorage.prototype.clear = function () {
        this.storage.clear();
    };
    return CustomStorage;
}());
exports.CustomStorage = CustomStorage;
