var UserModalCtrl = function ($scope, row, myData, $modalInstance, UserService) {
    $scope.user = row.entity;
    $scope.update = function () {
        if (!$scope.user.userId) {
            $scope.user.userId = createUserId($scope.user);
            UserService.add($scope.user).then(function (data) {
                console.info("add to userInfo successfully : " + data);
                row.entity.id = data.data;
                myData.push(row.entity);
            }, function (error) {
                console.error("update to userInfo error : " + error.data);
            });
        } else {
            UserService.update($scope.user).then(function (data) {
                console.info("update to userInfo successfully : " + data);
            }, function (error) {
                console.error("update to userInfo error : " + error.data);
            });
        }

        $modalInstance.dismiss("cancel");
    };

    $scope.cancel = function () {
        $modalInstance.dismiss("cancel");
    };

    var createRandomNum = function () {
        var Num = "";
        for (var i = 0; i < 3; i++) {
            Num += Math.floor(Math.random() * 10);
        }
        return Num;
    };

    var createUserId = function (user) {
        var indexDot = user.email.indexOf('.');
        var indexOther = user.email.indexOf('@');
        return user.email.substring(indexDot + 1, indexOther) + user.email.substring(0, indexDot) + createRandomNum();
    };
};

controllersModule.controller('UserModalCtrl', UserModalCtrl);