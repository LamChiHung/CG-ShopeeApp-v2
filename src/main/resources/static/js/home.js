let id = setInterval(function () {
    let count = 0;
    let radio1 = document.querySelector("#radio1");
    let radio2 = document.querySelector("#radio2");
    let radio3 = document.querySelector("#radio3");
    let radio4 = document.querySelector("#radio4");
    if (radio1.checked) {
        count = 1;
    }
    if (radio2.checked) {
        count = 2;
    }
    if (radio3.checked) {
        count = 3;
    }
    if (radio4.checked) {
        count = 4;
    }
    if (count < 4) {
        count = count + 1;
        document.querySelector(`#radio${count}`).checked = true;

    } else {
        count = 1;
        document.querySelector(`#radio${count}`).checked = true;
    }
    return () => clearInterval(id);
}, 3000);

var requestAnimationFrame = window.requestAnimationFrame || window.mozRequestAnimationFrame || window.webkitRequestAnimationFrame || window.msRequestAnimationFrame;
var transforms = ["transform", "msTransform", "webkitTransform", "mozTransform", "oTransform"];
var transformProperty = getSupportedPropertyName(transforms);
var snowflakes = [];
var browserWidth;
var browserHeight;
var numberOfSnowflakes = 5;
var resetPosition = false;

function setup() {
    window.addEventListener("DOMContentLoaded", generateSnowflakes, false);
    window.addEventListener("resize", setResetFlag, false)
}

setup();

function getSupportedPropertyName(b) {
    for (var a = 0; a < b.length; a++) {
        if (typeof document.body.style[b[a]] != "undefined") {
            return b[a]
        }
    }
    return null
}

function Snowflake(b, a, d, e, c) {
    this.element = b;
    this.radius = a;
    this.speed = d;
    this.xPos = e;
    this.yPos = c;
    this.counter = 0;
    this.sign = Math.random() < 0.5 ? 1 : -1;
    this.element.style.opacity = 0.5 + Math.random();
    this.element.style.fontSize = 4 + Math.random() * 30 + "px"
}

Snowflake.prototype.update = function () {
    this.counter += this.speed / 5000;
    this.xPos += this.sign * this.speed * Math.cos(this.counter) / 40;
    this.yPos += Math.sin(this.counter) / 40 + this.speed / 30;
    setTranslate3DTransform(this.element, Math.round(this.xPos), Math.round(this.yPos));
    if (this.yPos > browserHeight) {
        this.yPos = -50
    }
};


function setTranslate3DTransform(a, c, b) {
    var d = "translate3d(" + c + "px, " + b + "px, 0)";
    a.style[transformProperty] = d
}


function generateSnowflakes() {
    var b = document.querySelector(".snowflake");
    var h = b.parentNode;
    browserWidth = document.documentElement.clientWidth;
    // browserWidth = window.innerWidth-500;
    browserHeight = document.documentElement.clientHeight;
    // browserHeight = window.innerHeight -500;
    for (var d = 0; d < numberOfSnowflakes; d++) {
        var j = b.cloneNode(true);
        h.appendChild(j);
        var e = getPosition(50, browserWidth);
        var a = getPosition(50, browserHeight);
        var c = 5 + Math.random() * 40;
        var g = 4 + Math.random() * 10;
        var f = new Snowflake(j, g, c, e, a);
        snowflakes.push(f)
    }
    h.removeChild(b);
    moveSnowflakes()
}


function moveSnowflakes() {
    for (var b = 0; b < snowflakes.length; b++) {
        var a = snowflakes[b];
        a.update()
    }
    if (resetPosition) {
        browserWidth = document.documentElement.clientWidth;
        browserHeight = document.documentElement.clientHeight;
        for (var b = 0; b < snowflakes.length; b++) {
            var a = snowflakes[b];
            a.xPos = getPosition(50, browserWidth);
            a.yPos = getPosition(50, browserHeight)
        }
        resetPosition = false
    }
    requestAnimationFrame(moveSnowflakes)
}

function getPosition(b, a) {
    return Math.round(-1 * b + Math.random() * (a + 2 * b))
}

function setResetFlag(a) {
    resetPosition = true
};

