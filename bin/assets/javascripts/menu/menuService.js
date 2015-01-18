var MenuService = function ($http, $q) {
    console.debug("constructing MenuService");

    MenuService.headers = {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    };

    MenuService.defaultConfig = {
        headers: MenuService.headers
    };

    MenuService.prototype.add = function (menu) {
        var deferred;
        console.debug("MenuService.add()");
        deferred = $q.defer();
        $http.post("/addMenuInfo", menu).then(function (data) {
            console.info("Successfully add menu");
            return deferred.resolve(data);
        }, function (data, status) {
            console.error("Failed to add menu" + status);
            return deferred.reject(data);
        });
        return deferred.promise;
    };

    MenuService.prototype.update = function (menu) {
        var deferred;
        console.debug("MenuService.update()");
        deferred = $q.defer();
        $http.post("/updateMenuInfo", menu).then(function (data) {
            console.info("Successfully update menuInfo ");
            return deferred.resolve(data);
        }, function (data, status) {
            console.error("Failed to update menuInfo - status " + status);
            return deferred.reject(data);
        });
        return deferred.promise;
    };


    MenuService.prototype.delete = function (menu) {
        var deferred;
        console.debug("MenuService.update()");
        deferred = $q.defer();
        $http.post("/deleteMenuInfo", menu).then(function (data) {
            console.info("Successfully delete menuInfo ");
            return deferred.resolve(data);
        }, function (data, status) {
            console.error("Failed to delete menuInfo - status " + status);
            return deferred.reject(data);
        });
        return deferred.promise;
    };
};
servicesModule.service('MenuService', MenuService);

