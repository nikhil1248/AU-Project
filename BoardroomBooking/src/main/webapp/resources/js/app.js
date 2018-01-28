(function() {
  var roomBookingApp = angular.module('roomBookingApp', ['ui.calendar', 'ui.bootstrap', 'ngRoute']);

  roomBookingApp.config(function ($routeProvider) {
    $routeProvider.when("/", {
      templateUrl : "index.html",
      controller : "MainCtrl"
    })
    .when("/login", {
      templateUrl : "resources/partials/login.html",
      controller  : "loginCtrl"
    })
    .when("/signup", {
      templateUrl : "resources/partials/signup.html",
      controller  : "signupCtrl"
    })
    .when("/calendar", {
      templateUrl : "resources/partials/dashboard.html",
      controller  : "CalendarCtrl"
    })
    .otherwise({
      redirectTo  : "/"
    })
  });

  /* Main Controller */
  roomBookingApp.controller('MainCtrl', function($scope, $location) {
    $scope.signUp = function () {
      $location.path("/signup");
    }

    $scope.login = function () {
      $location.path("/login")
    }

  });

  /* Login Controller */
  roomBookingApp.controller('loginCtrl', function($scope, $rootScope, $http, $location) {
    $scope.userId = null;
    $scope.password = null;
    $scope.error = "";

    $scope.user = {
      userId : null,
      username : null,
      locationId : null,
      password : null,
      isAdmin : false
    }

    var validateForm = function() {
      if(typeof $scope.userId != 'number') {
        $scope.error = "ID must be a number";
        return false;
      }
      $scope.error = "";
      return true;
    }

    $scope.login = function() {
      if(validateForm()) {
        var loginUrl = "http://localhost:8080/BoardroomBooking/api/user/login";
        $scope.user.userId = $scope.id;
        $scope.user.password = $scope.password;
        $http.post({
          url : loginUrl,
          data :  $scope.user,
        }).then(onSuccessfulLogin, onError);
      }
    }

    var onSuccessfulLogin = function(response) {
      $scope.user = response.data;
      $rootScope.user = $scope.user;
      $location.path("/calendar")
    }

    var onError = function(response) {
      console.log("Error while authenticating");
    }

  });

  /* Sign Up controller */
  roomBookingApp.controller('signupCtrl', function($scope, $rootScope, $http, $location) {
    $scope.userId = null;
    $scope.username = null;
    $scope.password = null;
    $scope.cnfrmPassword = null;
    $scope.locationId = null;

    $scope.error = "";
    var validateForm = function() {
      if(typeof $scope.userId != "number"){
        $scope.error = "User id must be a number";
        return false;
      }

      if($scope.password  != $scope.cnfrmPassword) {
        $scope.error = "Passwords don't match";
        return false;
      }
      $scope.error = "";
      return true;
    }

    $scope.signup = function() {
      if(validateForm()) {
        var signupUrl = "http://localhost:8080/BoardroomBooking/api/user/register";
        $scope.user = {
          userId : $scope.userId,
          username : $scope.username,
          locationId : $scope.locationId,
          password : $scope.password,
          isAdmin : false
        }
        $http.post({
          url : signupUrl,
          data : $scope.user
        }).then(onSuccessfulRegister, onError);
      }
    }

    var onSuccessfulRegister = function(response) {
      $rootScope.user = $scope.user;
      console.log("User registered Successfully");
      $location.path("/calendar");
    }

    var onError = function(response) {
      console.log("Error while registering the user");
    }

  });

  /* Calendar Ctrl */
  roomBookingApp.controller('CalendarCtrl',
     function($scope, $rootScope, $http, $compile, $timeout, uiCalendarConfig, $location) {
      var date = new Date();
      var d = date.getDate();
      var m = date.getMonth();
      var y = date.getFullYear();

      angular.element(document).ready(function() {
         fetchUser();
         fetchEvents($scope.user.locationId);
         fetchRooms($scope.user.locationId);
         fetchSpecialRequests($scope.user.locationId);
      });

      var fetchUser = function() {
        $scope.user = $rootScope.user;
      }

      var fetchEvents = function(locationId) {
        var url = "http://localhost:8080/BoardroomBooking/api/bookings/getallbookings?locationId=" + locationId;
        $http.get(url).then(function(response) {
          $scope.events = [];
          angular.forEach(response.data, function(event, key) {
            $scope.events.push(event);
          });
        },
        function(reponse) {
          console.log("Error while fetching events")
        });
      };

      var fetchRooms = function(locationId) {
        var roomsUrl = "http://localhost:8080/BoardroomBooking/api/room/getallrooms?locationId=" + locationId;
        $http.get(roomsUrl).then(function(response) {
          $scope.rooms = [];
          angular.forEach(response.data, function(room, key) {
              $scope.rooms.push(room);
          });
        }, function(response) {
            console.log("Error while fetching rooms")
        });
      };

      var fetchSpecialRequests = function(locationId) {
        var spReqUrl = "http://localhost:8080/BoardroomBooking/api/special/getallrequests?locationId=" + locationId;
        $http.get(spReqUrl).then(function(response) {
          $scope.specialRequests = [];
          angular.forEach(response.data, function(request, key) {
              $scope.specialRequests.push(request);
          })
        }, function(response) {
            console.log("Error while fetching requests")
        });
      };

      var saveEvent = function(event) {
        var saveEventUrl = "http://localhost:8080/BoardroomBooking/api/user/booking";
        $http.post({
          url : saveEventUrl,
          data : event
        }).then(function(response) {
          console.log("Successfully saved the event booking in the database");
        }, function(response) {
          console.log("Failed to save the event booking in the database");
        });
      };

      var saveRoom = function(room) {
        var saveRoomUrl = "http://localhost:8080/BoardroomBooking/api/admin/addroom";
        $http.post({
            url : saveRoomUrl,
            data : room
        }).then(function(response) {
          console.log("Successfully saved the room in the database");
        }, function(response) {
          console.log("Failed to save the room in the database");
        });
      };

      var changeRoom = function(roomId) {
        console.log(roomId)
        var changeRoomUrl = "http://localhost:8080/BoardroomBooking/api/admin/changeavail?id=" + roomId;
        $http.get(changeRoomUrl).then(function(response){
          console.log("Change Successful");
        }),
        function(response) {
          console.log("Error occurred while changing.");
        }
      }

      var acceptReq = function (event, index) {
        var acceptRequestUrl = "http://localhost:8080/BoardroomBooking/api/admin/acceptrequest";
        $http.post(acceptRequestUrl, event).then(function (response) {
          console.log("Successfully accepted the request");
          $scope.eventCount++;
          $scope.specialRequests[index].id = $scope.eventCount;
          $scope.events.push($scope.specialRequests[index]);
          $scope.specialRequests.splice(index, 1);
        },
        function (response) {
          console.log("Request couldn't be accepted");
        })
      }

      var rejectReq = function (event, index) {
        var rejectRequestUrl = "http://localhost:8080/BoardroomBooking/api/admin/rejectrequest";
        $http.post(rejectRequestUrl, event).then(function (response) {
          console.log("Successfully accepted the request");
          $scope.specialRequests.splice(index, 1);
          $scope.showReq = null;
        },
        function (response) {
          console.log("Request couldn't be accepted");
        })
      }

      $scope.logout = function () {
        console.log("Logged out");
        $scope.user = null;
        $location.path("/");
      }

      /* event source that pulls from google.com */
      $scope.eventSource = {
              url: "http://www.google.com/calendar/feeds/usa__en%40holiday.calendar.google.com/public/basic",
              className: 'gcal-event',           // an option!
              timezone: 'local' // an option!
      };
      /* event source that contains custom events on the scope */

      /* Event Constructor */
      function Event() {
        this.id = $scope.eventCount;
        this.title = $scope.eventTitle,
        this.roomId = $scope.roomId,
        this.start = $scope.start.toUTCString(),
        this.end = $scope.end.toUTCString(),
        this.description = $scope.description,
        this.locationId = $scope.user.locationId,
        this.userId = $scope.user.userId
      }

      $scope.events = [
        {
          title: 'All Day Event',
          roomId: 1,
          start: new Date(y, m, 1),
          end: new Date(y, m, 1),
          description: "New Event",
          userId : 1
        },
        {title: 'All Day Event', roomId : 1, start: new Date(y, m, 1), userId : 1},
        {title: 'Long Event',roomId : 1, start: new Date(y, m, d - 5),end: new Date(y, m, d - 2)},
        {id: 999,title: 'Repeating Event', roomId : 1, start: new Date(y, m, d - 3, 16, 0),allDay: false},
        {id: 999,title: 'Repeating Event', roomId : 1, start: new Date(y, m, d + 4, 16, 0),allDay: false},
        {title: 'Birthday Party', roomId : 1, start: new Date(y, m, d + 1, 19, 0),end: new Date(y, m, d + 1, 22, 30),allDay: false},
        {title: 'Click for Google', roomId : 1, start: new Date(y, m, 28),end: new Date(y, m, 29),url: 'http://google.com/'}
      ];

      /* rooms in the location */
      $scope.rooms = [
        {roomId: 1, locationId : 1, isAvaialable : true},
        {roomId: 2, locationId : 1, isAvaialable : true},
      ];

      $scope.specialRequests = [

      ];

      $scope.user = {
        userId : undefined,
        username : undefined,
        password : undefined,
        locationId : undefined,
        isAdmin : true
      };

      /* Local Variables */
      $scope.eventTitle = undefined;
      $scope.roomId = "";
      $scope.start = "";
      $scope.end = "";
      $scope.description = "";
      $scope.errormsg = "";
      $scope.roomErrorMsg = "";

      $scope.showReq = null;
      $scope.roomCount = $scope.rooms.length;
      $scope.eventCount = $scope.events.length;
      $scope.requestCount = $scope.specialRequests.length;

      $scope.showRequest = function(request) {
        $scope.showReq = request;
        $('#modalToggle').click();
      }

      var updateRequestCount = function() {
        $scope.eventCount = $scope.specialRequests.length;
      }

      $scope.acceptRequest = function() {
        var index = $scope.specialRequests.indexOf(showReq.id);
        $scope.showReq = null;
        acceptReq($scope.specialRequests[index], index);

        updateRequestCount();
      }

      $scope.rejectRequest = function() {
        var index = $scope.specialRequests.indexOf(showReq.id);
        rejectReq($scope.specialRequests[index], index)
        updateRequestCount();
      }

      $scope.addRoom = function() {
        console.log("Adding room");
        $scope.roomCount++;
        console.log($scope.roomCount);
        var room = {
          roomId : $scope.roomCount,
          locationId : 1,
          isAvaialable : true
        }
        console.log(room)
        $scope.rooms.push(room);
        saveRoom(room);
      }

      $scope.changeAvaialabilty = function(room) {
        for(var i = 0; i < $scope.rooms.length; i++) {
          if($scope.rooms[i].roomId == room.roomId) {
            $scope.rooms[i].isAvaialable = ($scope.rooms[i].isAvaialable) ? false : true;
              console.log("Changed room " + room.roomId + "\'s availability");
          }
        }
        console.log(room.roomId)
        changeRoom(room.roomId);
      }

      /* Booking form validations */
      var validateTitle = function() {
        if($scope.eventTitle != undefined){
          $scope.errormsg = "";
          console.log("Title is valid");
          return true;
        }
        else {
          $scope.errormsg = "Title cannot be empty";
          return false;
        }
      }

      var validateDates = function() {
        if($scope.start <= $scope.end) {
          $scope.errormsg = "";
          console.log("Dates are valid");
          return true;
        }
        else {
          $scope.errormsg = "Select the dates correctly";
          return false;
        }
      }

      var validateTimes = function() {
        console.log($scope.start.getTime());
        if($scope.start.getTime() < $scope.end.getTime()){
          $scope.errormsg = "";
          console.log("Time selection is valid");
          return true;
        }
        else {
          $scope.errormsg = "Select the time correctly";
          return false;
        }
      }

      var validateRoomId = function() {
        if(isNaN($scope.roomId)) {
          $scope.errormsg = "Select a room";
          return false;
        }
        console.log("Room is selected correctly");
        return true;
      }

      var isRoomAvaiable = function(id) {
        for(var i = 0; i < $scope.rooms.length; i++) {
          if($scope.rooms[i].roomId == id && $scope.rooms[i].isAvaialable){
            console.log("Room is available");
              return true;
          }
        }
        return false;
      }

      var checkRoomAvailability = function() {
        console.log("checking availability of the room");
        for(var i = 0; i < $scope.events.length; i++) {
          if(isRoomAvaiable($scope.events[i].roomId) && $scope.events[i].roomId === $scope.roomId) {
              var sd = new Date($scope.events[i].start);
              var ed = new Date($scope.events[i].end);
            if(sd <= $scope.start && $scope.start <= ed) {
              if(sd.getTime() <= $scope.start.getTime() && $scope.start.getTime() < ed.getTime()) {
                return false;
              }
            }
          }
        }
        return true;
      }

      /* add custom event*/
      $scope.addEvent = function() {
        var r = document.getElementById('roomId');
        $scope.roomId = parseInt(r.options[r.selectedIndex].value);
        var event = new Event();
        if(validateTitle() && validateRoomId() && validateDates() && validateTimes()) {
          if(!checkRoomAvailability()) {
            console.log("Room is not available");
            if(confirm("Selected room is not available for the chosen duration. Do you want to send a special request?")){
                $scope.requestCount++;
                event.id = $scope.requestCount;          
                console.log("Special Request Sent");
                $scope.specialRequests.push(event);
              }
            }else {
              console.log("Room is available");
              $scope.eventCount++;
              event.id = $scope.eventCount;
                $scope.events.push(event);
                console.log($scope.events);
            }
              $('#newEventModal').on('hidden.bs.modal', function(event) {
                  $(this).removeData();
              });
              resetValues();
              $('#newEventModal .close').click();
              saveEvent(event);
          }
      };

      var resetValues = function() {
          $scope.eventTitle = null;
          $scope.roomId = null;
          $scope.start = null;
          $scope.end = null;
          $scope.description = null;
      }

      /* event source that calls a function on every view switch */
      $scope.eventsF = function (start, end, timezone, callback) {
        var s = new Date(start).getTime() / 1000;
        var e = new Date(end).getTime() / 1000;
        var m = new Date(start).getMonth();
        var events = [{title: 'Feed Me ' + m,start: s + e + (50000),end: s + (100000),allDay: false, className: ['customFeed']}];
        callback(events);
      };

      $scope.calEventsExt = {
         color: '#f00',
         textColor: 'yellow',
         events: [
            {type:'party',title: 'Lunch',start: new Date(y, m, d, 12, 0),end: new Date(y, m, d, 14, 0),allDay: false},
            {type:'party',title: 'Lunch 2',start: new Date(y, m, d, 12, 0),end: new Date(y, m, d, 14, 0),allDay: false},
            {type:'party',title: 'Click for Google',start: new Date(y, m, 28),end: new Date(y, m, 29),url: 'http://google.com/'}
          ]
      };

      /* alert on eventClick */
      $scope.alertOnEventClick = function(date, jsEvent, view){
          $scope.alertMessage = ('Title: ' + date.title +
                                 '; Room: ' + date.roomId +
                                 '; Description: ' + date.description +
                                 '; Booked with user id: ' + date.userId);
      };
      /* alert on Drop */
       $scope.alertOnDrop = function(event, delta, revertFunc, jsEvent, ui, view){
         $scope.alertMessage = ('Event Dropped to make dayDelta ' + delta);
       };
      /* alert on Resize */
      $scope.alertOnResize = function(event, delta, revertFunc, jsEvent, ui, view ){
         $scope.alertMessage = ('Event Resized to make dayDelta ' + delta);
      };
      /* add and removes an event source of choice */
      $scope.addRemoveEventSource = function(sources,source) {
        var canAdd = 0;
        angular.forEach(sources,function(value, key){
          if(sources[key] === source){
            sources.splice(key,1);
            canAdd = 1;
          }
        });
        if(canAdd === 0){
          sources.push(source);
        }
      };

      /* remove event */
      $scope.remove = function(index) {
        $scope.events.splice(index,1);
      };
      /* Change View */
      $scope.changeView = function(view,calendar) {
        uiCalendarConfig.calendars[calendar].fullCalendar('changeView',view);
      };
      /* Change View */
      $scope.renderCalendar = function(calendar) {
        $timeout(function() {
          if(uiCalendarConfig.calendars[calendar]){
            uiCalendarConfig.calendars[calendar].fullCalendar('render');
          }
        });
      };
       /* Render Tooltip */
      $scope.eventRender = function( event, element, view ) {
        element.qtip({

          start : event.start,
          end   : event.end,
          content   : event.description,
          userId    : event.userId

        })
          $compile(element)($scope);
      };

      $scope.call = function(event, element, view) {
        console.log("Hovered over" + event.title);
      }

      /* config object */
      $scope.uiConfig = {
        calendar:{
          height: 500,
          editable: true,
          header:{
            left: 'title',
            center: '',
            right: 'today prev,next'
          },
          eventClick: $scope.alertOnEventClick,
          eventDrop: $scope.alertOnDrop,
          eventResize: $scope.alertOnResize,
          eventRender: $scope.eventRender,
      }
  };
      /* event sources array*/
      $scope.eventSources = [$scope.events, $scope.eventSource, $scope.eventsF];
      $scope.eventSources2 = [$scope.calEventsExt, $scope.eventsF, $scope.events];
  });
})();
