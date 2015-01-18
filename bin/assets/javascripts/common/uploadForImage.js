var ImageModalInstanceCtrl = function($scope, $log, $location, $modalInstance,FileUploader) {
    $scope.uploaderImage = new FileUploader({
        url: '/common/uploadImage',
        alias: 'image'
    });
    $scope.ok = function() {
        if ($scope.uploaderImage.queue.length !== 0) {
            $scope.row.image = $scope.uploaderImage.queue[$scope.uploaderImage.queue.length - 1]._file.name;
        }
        $scope.uploaderImage.clearQueue();
        $modalInstance.close($scope.row);
        return window.parent.location.reload();
    };
    $scope.cancel = function() {
        return $modalInstance.dismiss("cancel");
    };
};
controllersModule.controller("ImageModalInstanceCtrl", ImageModalInstanceCtrl);
