
function submitForm() {
    document.getElementById("monthForm").submit();
}

let nameOfProduct1 = document.querySelector('[name="product-1"]').textContent;
let value1 = document.querySelector('[name="value-of-product-1"]').textContent

let nameOfProduct2 = document.querySelector('[name="product-2"]').textContent;
let value2 = document.querySelector('[name="value-of-product-2"]').textContent;

let nameOfProduct3 = document.querySelector('[name="product-3"]').textContent;
let value3 = document.querySelector('[name="value-of-product-3"]').textContent;

let nameOfProduct4 = document.querySelector('[name="product-4"]').textContent;
let value4 = document.querySelector('[name="value-of-product-4"]').textContent;

let nameOfProduct5 = document.querySelector('[name="product-5"]').textContent;
let value5 = document.querySelector('[name="value-of-product-5"]').textContent;

let total = parseInt(document.getElementById('total-quantity').textContent);

var titleStatistics1 = document.getElementById('title-1').textContent

google.charts.load("current", {packages: ["corechart"]});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
    var data = google.visualization.arrayToDataTable([
        ['Task', 'Hours per Day'],
        [nameOfProduct1, parseInt(value1) / total],
        [nameOfProduct2, parseInt(value2) / total],
        [nameOfProduct3, parseInt(value3) / total],
        [nameOfProduct4, parseInt(value4) / total],
        [nameOfProduct5, parseInt(value5) / total],
    ]);

    var options = {
        title: ' ',
        is3D: true,
    };

    var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
    chart.draw(data, options);
}


let jan = document.getElementById('Jan').textContent
let feb = document.getElementById('Feb').textContent
let mar = document.getElementById('Mar').textContent
let apr = document.getElementById('Apr').textContent
let may = document.getElementById('May').textContent
let jun = document.getElementById('Jun').textContent
let jul = document.getElementById('Jul').textContent
let aug = document.getElementById('Aug').textContent
let sep = document.getElementById('Sep').textContent
let oct = document.getElementById('Oct').textContent
let nov = document.getElementById('Nov').textContent
let dev = document.getElementById('Dev').textContent

let valueOfJan = parseInt(document.getElementById('value-1').textContent)
let valueOfFeb = parseInt(document.getElementById('value-2').textContent)
let valueOfMar = parseInt(document.getElementById('value-3').textContent)
let valueOfApr = parseInt(document.getElementById('value-4').textContent)
let valueOfMay = parseInt(document.getElementById('value-5').textContent)
let valueOfJun = parseInt(document.getElementById('value-6').textContent)
let valueOfJul = parseInt(document.getElementById('value-7').textContent)
let valueOfAug = parseInt(document.getElementById('value-8').textContent)
let valueOfSep = parseInt(document.getElementById('value-9').textContent)
let valueOfOct = parseInt(document.getElementById('value-10').textContent)
let valueOfNov = parseInt(document.getElementById('value-11').textContent)
let valueOfDev = parseInt(document.getElementById('value-12').textContent)
google.charts.load('current', {'packages': ['corechart']});
google.charts.setOnLoadCallback(drawVisualization);
function drawVisualization() {
    // Some raw data (not necessarily accurate)
    var data = google.visualization.arrayToDataTable([
        ['Tháng', 'doanh số', 'doanh số trung bình'],
        [jan, valueOfJan / 1000000, (valueOfJan / 1000000) / 2],
        [feb, valueOfFeb / 1000000, (valueOfFeb / 1000000) / 2],
        [mar, valueOfMar / 1000000, (valueOfMar / 1000000) / 2],
        [apr, valueOfApr / 1000000, (valueOfApr / 1000000) / 2],
        [may, valueOfMay / 1000000, (valueOfMay / 1000000) / 2],
        [jun, valueOfJun / 1000000, (valueOfJun / 1000000) / 2],
        [jul, valueOfJul / 1000000, (valueOfJul / 1000000) / 2],
        [aug, valueOfAug / 1000000, (valueOfAug / 1000000) / 2],
        [sep, valueOfSep / 1000000, (valueOfSep / 1000000) / 2],
        [oct, valueOfOct / 1000000, (valueOfOct / 1000000) / 2],
        [nov, valueOfNov / 1000000, (valueOfNov / 1000000) / 2],
        [dev, valueOfDev / 1000000, (valueOfDev / 1000000) / 2],
    ]);

    var options = {
        title: ' ',
        vAxis: {title: 'triệu đồng'},
        hAxis: {title: 'Tháng'},
        seriesType: 'bars',
        series: {0: {color: getRandomColor()}, 1: {type: 'line', color: '#dc3912'}},
    };
    function getRandomColor() {
        var letters = '0123456789ABCDEF';
        var color = '#';
        for (var i = 0; i < 6; i++) {
            color += letters[Math.floor(Math.random() * 16)];
        }
        return color;
    }

    var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
    chart.draw(data, options);
}

