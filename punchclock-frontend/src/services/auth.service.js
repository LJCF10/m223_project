import axios from "axios";

const API_URL = "http://localhost:8081/"

class AuthService {
    login(username, password) {
        return axios.post(API_URL + "")
    }
}