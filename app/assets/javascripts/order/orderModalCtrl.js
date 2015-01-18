var OrderModalCtrl = function ($scope, row, $modalInstance, OrderService) {
    $scope.order = row;
    $scope.update = function () {
        OrderService.update($scope.order).then(function (data) {
            console.info("add to userInfo successfully : " + data);
        }, function (error) {
            console.error("update to userInfo error : " + error.data);
        });
        $modalInstance.dismiss("cancel");
    };

    $scope.cancel = function () {
        $modalInstance.dismiss("cancel");
    };
};

controllersModule.controller('OrderModalCtrl', OrderModalCtrl);