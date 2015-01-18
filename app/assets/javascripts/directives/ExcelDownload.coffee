###*
Created by wangjiaming on 2014/11/26.
###

# jQuery needed, uses Bootstrap classes, adjust the path of templateUrl
directivesModule.directive "excelDownload", ->
  restrict: "E"
  templateUrl: "/assets/faces/template/excelDownload.tpl.html"
  scope: true
  link: (scope, element, attr) ->
    anchor = element.children()[0]

    # When the download starts, disable the link
    scope.$on "download-start", ->
      $(anchor).attr "disabled", "disabled"
      return


    # When the download finishes, attach the data to the link. Enable the link and change its appearance.
    scope.$on "downloaded", (event, data) ->
      $(anchor).attr(
        href: "data:application/vnd.ms-excel;charset=UTF-8;base64," + data
        download: attr.filename
      ).removeAttr("disabled").text("Save").removeClass("btn-primary").addClass "btn-success"

      # Also overwrite the download pdf function to do nothing.
      scope.download = ->

      return

    return

  controller: [
    "$scope"
    "$attrs"
    "$http"
    ($scope, $attrs, $http) ->
      $scope.download = ->
        $scope.$emit "download-start"
        $http.get($attrs.url).then (response) ->
          $scope.$emit "downloaded", response.data
          return

        return
  ]
