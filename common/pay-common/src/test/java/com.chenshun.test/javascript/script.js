var fun1 = function (name) {
    print('Hi there from Javascript, ' + name);
    return "greetings from javascript";
};

var fun2 = function (object) {
    print("JS Class Definition: " + Object.prototype.toString.call(object));
};

var fun3 = function () {
    var T1 = Java.type('com.chenshun.test.javascript.T1');
    print(T1.fun1('John Doe'));
};
