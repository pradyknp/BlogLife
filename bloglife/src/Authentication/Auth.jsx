class Auth {

    /**
     * Authenticate a user. Save a token string in Local Storage
     *
     * @param {string} token
     */
    static authenticateUser(token,user) {
        console.log(token);
        localStorage.setItem('token', token);
        localStorage.setItem('user', user);
    }

    /**
     * Check if a user is authenticated - check if a token is saved in Local Storage
     *
     * @returns {boolean}
     */
    static isUserAuthenticated() {
        return localStorage.getItem('token') !== null;
    }

    /**
     * Deauthenticate a user. Remove a token from Local Storage.
     *
     */
    static deauthenticateUser() {
        localStorage.removeItem('token');
    }

    /**
     * Get a token value.
     *
     * @returns {string}
     */

    static getUser(){
        console.log(localStorage.getItem('user'));
        return localStorage.getItem('user');
    }

    static getToken() {
        console.log(localStorage.getItem('token'));
        return localStorage.getItem('token');
    }

}

export default Auth;