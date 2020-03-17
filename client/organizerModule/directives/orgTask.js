organizerApp.directive("orgTask", function(){
   return{
       restrict: 'E',
       scope:{
           taskObject: '='
       },
       templateUrl: 'templates/org-task.html'
   }
});
