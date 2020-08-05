/* 网站根目录 */
var _webroot = "http://" + location.host;
/* url参数 */
var _querystring = {};
if (location.search.length > 0) {
    var arr = location.search.substring(1).split('&');
    for (var item in arr) {
        var key = arr[item].split('=')[0];
        var value = arr[item].split('=')[1];
        _querystring[key] = value;
    }
}

function formatDateTime(val) {
    if (val === null || val === '')
        return '';
    var datetime = new Date(val);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
}

function formatDate(val) {
    if (val === null || val === '')
        return '';
    var datetime = new Date(val);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    return year + "-" + month + "-" + date;
}