var UserService = function ($http, $q) {
    console.debug("constructing UserService");

    UserService.headers = {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    };

    UserService.defaultConfig = {
        headers: UserService.headers
    };

    UserService.prototype.add = function (user) {
        var deferred;
        console.debug("UserService.add()");
        deferred = $q.defer();
        $http.post("/addUserInfo", user).then(function (data) {
            console.info("Successfully add user");
            return deferred.resolve(data);
        }, function (data, status) {
            console.error("Failed to add user" + status);
            return deferred.reject(data);
        });
        return deferred.promise;
    };

    UserService.prototype.update = function (user) {
        var deferred;
        console.debug("UserService.update()");
        deferred = $q.defer();
        $http.post("/updateUserInfo", user).then(function (data) {
            console.info("Successfully update userInfo ");
            return deferred.resolve(data);
        }, function (data, status) {
            console.error("Failed to update userInfo - status " + status);
            return deferred.reject(data);
        });
        return deferred.promise;
    };


    UserService.prototype.delete = function (user) {
        var deferred;
        console.debug("UserService.update()");
        deferred = $q.defer();
        $http.post("/deleteUserInfo", user).then(function (data) {
            console.info("Successfully delete userInfo ");
            return deferred.resolve(data);
        }, function (data, status) {
            console.error("Failed to delete userInfo - status " + status);
            return deferred.reject(data);
        });
        return deferred.promise;
    };
};
servicesModule.service('UserService', UserService);

