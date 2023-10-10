import {Employee} from "../../EuropeanCompany/src/Employee";

export class CustomStorage {
    private storage: Map<string, any>;

    constructor() {
        this.storage = new Map<string, any>();
    }

    setItem(key: string, value: any): void {
        this.storage.set(key, value);
    }

    getItem(key: string): any | null {
        const item = this.storage.get(key);
        return item !== undefined ? item : null;
    }

    removeItem(key: string): void {
        this.storage.delete(key);
    }

    clear(): void {
        this.storage.clear();
    }
}
