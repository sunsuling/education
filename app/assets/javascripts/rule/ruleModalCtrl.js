var RuleModalCtrl = function ($scope, row,myData,ruleFlg, $modalInstance, RuleService) {
    $scope.rule = row;
    $scope.update = function () {
        if (ruleFlg === "add") {
            RuleService.add($scope.rule).then(function (data) {
                console.info("add to RuleInfo successfully : " + data);
                myData.push(row);
            }, function (error) {
                console.error("update to RuleInfo error : " + error.data);
            });
        } else {
            RuleService.update($scope.rule).then(function (data) {
                console.info("update to RuleInfo successfully : " + data);
            }, function (error) {
                console.error("update to RuleInfo error : " + error.data);
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

controllersModule.controller('RuleModalCtrl', RuleModalCtrl);