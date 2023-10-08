import axios from "axios";

const BASE_URL = "http://localhost:8088"

export default new (class Config {
    constructor() {
        this.axios = null;
    }

    async setup() {
        this.axios = axios.create({
            baseURL: BASE_URL,
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                //'Authorization': `Bearer a80334ea4c45625928d5a80c7dbedac757aa5ba3`
            },
        });
    }
})();
