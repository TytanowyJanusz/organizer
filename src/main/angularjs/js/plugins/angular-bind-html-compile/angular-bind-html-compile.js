!function(a){"use strict";var b=a.module("angular-bind-html-compile",[]);b.directive("bindHtmlCompile",["$compile",function(a){return{restrict:"A",link:function(b,c,d){b.$watch(function(){return b.$eval(d.bindHtmlCompile)},function(e){c.html(e&&e.toString());var f=b;d.bindHtmlScope&&(f=b.$eval(d.bindHtmlScope)),a(c.contents())(f)})}}}])}(window.angular);
