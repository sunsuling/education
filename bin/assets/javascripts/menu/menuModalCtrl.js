var MenuModalCtrl = function ($scope, row, myData, $modalInstance, MenuService) {
    $scope.food = row.entity;
    $scope.update = function () {
        if (!$scope.food.foodId) {
            $scope.food.foodId = createRandomNum();
            MenuService.add($scope.food).then(function (data) {
                console.info("add to menuInfo successfully : " + data);
                row.entity.id = data.data;
                myData.push(row.entity );
            }, function (error) {
                console.error("update to menuInfo error : " + error.data);
            });
        } else {
            MenuService.update($scope.food).then(function (data) {
                console.info("update to menuInfo successfully : " + data);
            }, function (error) {
                console.error("update to menuInfo error : " + error.data);
            });
        }

        $modalInstance.dismiss("cancel");
    };

    $scope.cancel = function () {
        $modalInstance.dismiss("cancel");
    };

    var createRandomNum = function () {
        var Num = "";
        for (var i = 0; i < 4; i++) {
            Num += Math.floor(Math.random() * 10);
        }
        return Num;
    };
};

controllersModule.controller('MenuModalCtrl', MenuModalCtrl);