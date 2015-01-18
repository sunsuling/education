var ExcelModalInstanceCtrl = function ($scope, $log, $location, $modalInstance,$rootScope,$timeout) {
    $scope.uploaderExcel  = $rootScope.uploaderExcel;
    $scope.alertFlag = true;

    $scope.ok = function () {
        if ($scope.uploaderExcel.queue.length !== 0) {
            $scope.row.excel = $scope.uploaderExcel.queue[$scope.uploaderExcel.queue.length - 1]._file.name;
        }
        $scope.uploaderExcel.clearQueue();
        $modalInstance.close($scope.row);
        return window.parent.location.reload();
    };

    $scope.removed = function (item) {
        $scope.alertFlag = true;
        item.remove();
    };

    $scope.cancel = function () {
        return $modalInstance.dismiss("cancel");
    };

    $scope.uploaderExcel.onSuccessItem = function (fileItem, response, status, headers) {
        console.info('onErrorItem', fileItem, response, status, headers);
    };
    $scope.uploaderExcel.onErrorItem = function (fileItem, response) {
        $scope.alertFlag = false;
        $scope.errorMessage = response;
        $timeout(function(){
            $scope.alertFlag = true;
        }, [2000], []);
    };

    $scope.removeAlert = function () {
        $scope.alertFlag = true;
    };
};
controllersModule.controller("ExcelModalInstanceCtrl", ExcelModalInstanceCtrl);
