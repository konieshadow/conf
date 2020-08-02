export class FetchError extends Error {
    constructor(status, message) {
        super(`Fetch failed with status ${status}: ${message}`);
        this.status = status;
    }
}