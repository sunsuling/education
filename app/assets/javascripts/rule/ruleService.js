var RuleService = function ($http, $q) {
    console.debug("constructing RuleService");

    RuleService.headers = {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    };

    RuleService.defaultConfig = {
        headers: RuleService.headers
    };

    RuleService.prototype.findResult = function (pager) {
        var deferred;
        console.debug("RuleService()");
        deferred = $q.defer();
        $http.post("/findRule", pager).then(function (data, status) {
            console.info("Successfully find RuleResult - status " + data);
            return deferred.resolve(data);
        }, function (data, status) {
            console.error("Failed to find RuleResult - status " + status);
            return deferred.reject(data);
        });
        return deferred.promise;
    };


    RuleService.prototype.add = function (Rule) {
        var deferred;
        console.debug("UserService.add()");
        deferred = $q.defer();
        $http.post("/addRule", Rule).then(function (data) {
            console.info("Successfully add user");
            return deferred.resolve(data);
        }, function (data, status) {
            console.error("Failed to add user" + status);
            return deferred.reject(data);
        });
        return deferred.promise;
    };

    RuleService.prototype.update = function (Rule) {
        var deferred;
        console.debug("UserService.add()");
        deferred = $q.defer();
        $http.post("/updateRule", Rule).then(function (data) {
            console.info("Successfully add Rule");
            return deferred.resolve(data);
        }, function (data, status) {
            console.error("Failed to add Rule" + status);
            return deferred.reject(data);
        });
        return deferred.promise;
    };
};
servicesModule.service('RuleService', RuleService);

