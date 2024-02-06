var product1 = document.getElementById('product-1');
var nameOfProduct1 = product1.textContent;
var value1 = document.getElementById('value-of-product-1').textContent

var product2 = document.getElementById('product-2');
var nameOfProduct2 = product2.textContent;
var value2 = document.getElementById('value-of-product-2').textContent

var product3 = document.getElementById('product-3')
var nameOfProduct3 = product3.textContent;
var value3 = document.getElementById('value-of-product-3').textContent

var product4 = document.getElementById('product-4');
var nameOfProduct4 = product4.textContent;
var value4 = document.getElementById('value-of-product-4').textContent

var product5 = document.getElementById('product-5')
var nameOfProduct5 = product5.textContent;
var value5 = document.getElementById('value-of-product-5').textContent

var titleStatistics1 = document.getElementById('title-1').textContent

google.charts.load("current", {packages: ["corechart"]});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
    var data = google.visualization.arrayToDataTable([
        ['Task', 'Hours per Day'],
        [nameOfProduct1, parseInt(value1)],
        [nameOfProduct2, parseInt(value2)],
        [nameOfProduct3, parseInt(value3)],
        [nameOfProduct4, parseInt(value4)],
        [nameOfProduct5, parseInt(value5)]
    ]);

    var options = {
        title: ' ',
        is3D: true,
    };

    var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
    chart.draw(data, options);
}

var jan = document.getElementById('Jan').textContent
var feb = document.getElementById('Feb').textContent
var mar = document.getElementById('Mar').textContent
var apr = document.getElementById('Apr').textContent
var may = document.getElementById('May').textContent
var jun = document.getElementById('Jun').textContent
var jul = document.getElementById('Jul').textContent
var aug = document.getElementById('Aug').textContent
var sep = document.getElementById('Sep').textContent
var oct = document.getElementById('Oct').textContent
var nov = document.getElementById('Nov').textContent
var dev = document.getElementById('Dev').textContent
var valueOfJan = parseInt(document.getElementById('value-1').textContent)
var valueOfFeb = parseInt(document.getElementById('value-2').textContent)
var valueOfMar = parseInt(document.getElementById('value-3').textContent)
var valueOfApr = parseInt(document.getElementById('value-4').textContent)
var valueOfMay = parseInt(document.getElementById('value-5').textContent)
var valueOfJun = parseInt(document.getElementById('value-6').textContent)
var valueOfJul = parseInt(document.getElementById('value-7').textContent)
var valueOfAug = parseInt(document.getElementById('value-8').textContent)
var valueOfSep = parseInt(document.getElementById('value-9').textContent)
var valueOfOct = parseInt(document.getElementById('value-10').textContent)
var valueOfNov = parseInt(document.getElementById('value-11').textContent)
var valueOfDev = parseInt(document.getElementById('value-12').textContent)

google.charts.load('current', {'packages': ['corechart']});
google.charts.setOnLoadCallback(drawVisualization);
function drawVisualization() {
    // Some raw data (not necessarily accurate)
    var data = google.visualization.arrayToDataTable([
        ['Tháng', 'doanh số', 'dtb'],
        [jan, valueOfJan, valueOfJan / 2],
        [feb, valueOfFeb, valueOfFeb / 2],
        [mar, valueOfMar, valueOfMar / 2],
        [apr, valueOfApr, valueOfApr / 2],
        [may, valueOfMay, valueOfMay / 2],
        [jun, valueOfJun, valueOfJun / 2],
        [jul, valueOfJul, valueOfJul / 2],
        [aug, valueOfAug, valueOfAug / 2],
        [sep, valueOfSep, valueOfSep / 2],
        [oct, valueOfOct, valueOfOct / 2],
        [nov, valueOfNov, valueOfNov / 2],
        [dev, valueOfDev, valueOfDev / 2],
    ]);

    var options = {
        title: ' ',
        vAxis: { title: 'triệu đồng' },
        hAxis: { title: 'Tháng' },
        seriesType: 'bars',
        series: { 0: { color: getRandomColor() }, 1: { type: 'line', color: '#dc3912' } },
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

