    var exec = require('cordova/exec');

    var Bugly = function() {}

    Bugly.prototype.setTag = function(tag) {
        exec(null, null, "bugly", "setTag", [tag]);
    }

    Bugly.prototype.putUserData = function(userData) {
        exec(null, null, "bugly", "putUserData", [userData]);
    }

    Bugly.prototype.postCatchedException = function(throwable) {
        exec(null, null, "bugly", "postCatchedException", [throwable]);
    }

    Bugly.prototype.setUserId = function(userId) {
        exec(null, null, "bugly", "setUserId", [userId]);
    }

    module.exports = new Bugly();