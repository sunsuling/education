var OrderService = function ($http, $q) {
    console.debug("constructing OrderService");

    OrderService.headers = {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    };

    OrderService.defaultConfig = {
        headers: OrderService.headers
    };


    OrderService.prototype.register = function (pager) {
        var deferred;
        console.debug("OrderService()");
        deferred = $q.defer();
        $http.post("/addUserInfo", pager).then(function (data, status) {
            console.info("Successfully find OrderResult - status " + data);
            return deferred.resolve(data);
        }, function (data, status) {
            console.error("Failed to find OrderResult - status " + status);
            return deferred.reject(data);
        });
        return deferred.promise;
    };

    OrderService.prototype.login = function (pager) {
        var deferred;
        console.debug("OrderService()");
        deferred = $q.defer();
        $http.post("/orderLogin", pager).then(function (data, status) {
            console.info("Successfully find OrderResult - status " + data);
            return deferred.resolve(data);
        }, function (data, status) {
            console.error("Failed to find OrderResult - status " + status);
            return deferred.reject(data);
        });
        return deferred.promise;
    };

    OrderService.prototype.findResult = function (pager) {
        var deferred;
        console.debug("OrderService()");
        deferred = $q.defer();
        $http.post("/findMenuInfo", pager).then(function (data, status) {
            console.info("Successfully find OrderResult - status " + data);
            return deferred.resolve(data);
        }, function (data, status) {
            console.error("Failed to find OrderResult - status " + status);
            return deferred.reject(data);
        });
        return deferred.promise;
    };


    OrderService.prototype.add = function (pager) {
        var deferred;
        console.debug("orderService.add()");
        deferred = $q.defer();
        $http.post("/addOrderInfo", pager).then(function (data) {
            console.info("Successfully add order");
            return deferred.resolve(data);
        }, function (data, status) {
            console.error("Failed to add order" + status);
            return deferred.reject(data);
        });
        return deferred.promise;
    };

    OrderService.prototype.update = function (order) {
        var deferred;
        console.debug("orderService.add()");
        deferred = $q.defer();
        $http.post("/updateOrderInfo", order).then(function (data) {
            console.info("Successfully add order");
            return deferred.resolve(data);
        }, function (data, status) {
            console.error("Failed to add order" + status);
            return deferred.reject(data);
        });
        return deferred.promise;
    };

    OrderService.prototype.myOrder = function () {
        var deferred;
        console.debug("orderService.myOrder()");
        deferred = $q.defer();
        $http.post("/findByUser").then(function (data) {
            console.info("Successfully findByUser");
            return deferred.resolve(data);
        }, function (data, status) {
            console.error("Failed to findByUser" + status);
            return deferred.reject(data);
        });
        return deferred.promise;
    };

    OrderService.prototype.delete = function (order) {
        var deferred;
        console.debug("orderService.delete()");
        deferred = $q.defer();
        $http.post("/deleteOrderInfo", order).then(function (data) {
            console.info("Successfully delete order");
            return deferred.resolve(data);
        }, function (data, status) {
            console.error("Failed to delete order" + status);
            return deferred.reject(data);
        });
        return deferred.promise;
    };

    OrderService.prototype.exportDailyTotal = function (pager) {
        var deferred;
        console.debug("OrderService()");
        deferred = $q.defer();
        $http.post("/exportDailyTotal", pager).then(function (data, status) {
            console.info("Successfully find OrderResult - status " + data);
            return deferred.resolve(data);
        }, function (data, status) {
            console.error("Failed to find OrderResult - status " + status);
            return deferred.reject(data);
        });
        return deferred.promise;
    };

    OrderService.prototype.exportDailyDetail = function (pager) {
        var deferred;
        console.debug("OrderService()");
        deferred = $q.defer();
        $http.post("/exportDailyDetail", pager).then(function (data, status) {
            console.info("Successfully find OrderResult - status " + data);
            return deferred.resolve(data);
        }, function (data, status) {
            console.error("Failed to find OrderResult - status " + status);
            return deferred.reject(data);
        });
        return deferred.promise;
    };
};
servicesModule.service('OrderService', OrderService);

