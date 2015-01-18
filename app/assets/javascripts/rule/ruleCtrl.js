var RuleCtrl = function($modal,$scope,$http,$timeout,$location,RuleService) {
    $scope.columnDefs = [
        {field: 'ruleType', displayName: '规则类型'},
        {field: 'ruleKey', displayName: '键'},
        {field: 'ruleValue', displayName: '值'},
        {field: 'locked', displayName: '操作', cellTemplate: '/assets/faces/cell/userCell.html'}
    ];
    $scope.pageUrl = "/findRule";

    new PageService($scope, $http, $timeout);

    $scope.editRow = function (row) {
        $scope.ruleFlg="upd";
        $scope.open(row.entity);
    };
    $scope.deleteRow = function (row) {
        UserService.delete(row.entity).then(function (data) {
            console.info("delete to Rule successfully : " + data);
            $scope.myData.splice(row.rowIndex,1);
        }, function (error) {
            console.error("delete to Rule error : " + error.data);
        });
    };

    RuleCtrl.prototype.findResult = function() {
        console.debug("findResult()");
        return RuleService.findResult($scope.pager).then((function(data) {
            console.debug("Promise returned " + data.length + " banks");
            $scope.pager = data.data;
            $scope.results = data.data.list;
        }), function(error) {
            console.error("Unable to get activities: " + error);
            $scope.error = error;
        });
    };

    RuleCtrl.prototype.export = function() {
        console.debug("findResult()");
        return RuleService.export($scope.pager).then((function(data) {
            console.debug("Promise returned " + data.length + " banks");
        }), function(error) {
            console.error("Unable to get activities: " + error);
            $scope.error = error;
        });
    };

    $scope.addRow = function () {
        var rule = {
            ruleType:"",
            ruleKey:"",
            ruleValue:""
        };
        $scope.ruleFlg="add";
        $scope.open(rule);
    };

    $scope.open = function (row) {
        $modal.open({
            templateUrl: "RuleModalContent.html",
            controller: "RuleModalCtrl",
            resolve: {
                row: function(){
                    return row;
                },
                ruleFlg:function(){
                    return $scope.ruleFlg;
                },
                myData: function(){
                    return  $scope.myData;
                }
            }
        });
    };
};

controllersModule.controller('RuleCtrl', RuleCtrl);
