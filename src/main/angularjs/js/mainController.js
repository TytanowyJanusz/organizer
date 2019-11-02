app.controller('mainController',[
    '$scope',
    'mainService',
    '$window',
     function mainController($scope, mainService, $window){

        var controller = this;
        $scope.tasks;
        $scope.task ={
            content: '',
            importance: 7,
            urgency: 8,
            timeOfCreation: '2019-09-13',
            isDone: false,
        };
         var userid = 1;
        $scope.totalTasks ;
        $scope.addTaskOpened = false;
        $scope.getTasksForUser = function()
        {
            console.log('work');
            $scope.tasks = mainService.getTasksForUser(userid);
            $scope.tasks = [
                {
                    content: 'Zaliczyć programowanie aplikacji internetowych',
                    importance: 9,
                    urgency: 9,
                    timeOfCreation: '2019-09-13',
                    isDone: false,

                }, {
                    content: 'Świętować zaliczenie',
                    importance: 4,
                    urgency: 5,
                    timeOfCreation: '2019-09-13',
                    isDone: false,
                }, {
                    content: 'Przygotować scenariusz do RPG',
                    importance: 7,
                    urgency: 3,
                    timeOfCreation: '2019-08-26',
                    isDone: false,
                }
                , {
                    content: 'Spotkanie z Primigenius',
                    importance: 7,
                    urgency: 8,
                    timeOfCreation: '2019-09-12',
                    isDone: false,
                }
            ];
            $scope.totalTasks = $scope.tasks.length;
        }
        $scope.openAddTask = function()
        {
           $scope.addTaskOpened=true;


        }
        $scope.addTask = function()
        {
            $scope.task.timeOfCreation = '2019-09-13';
            $scope.task.isDone = false;
            task = $scope.task;
            $scope.tasks.push(task);
            task = null;
            $scope.addTaskOpened=false;
            $scope.totalTasks = $scope.tasks.length;

        }

    }
]);
