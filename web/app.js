// SET UP =======================================
var express = require('express');
var gulp = require('gulp');
require('./gulpfile.js');
var app = express();

// VARIABLES =======================================
var src = __dirname + '/app';
var port = 4204;

// FUNCTIONS ================================
function setFile(response, path){
	return response.sendFile(src + '/views/' + path + '.html');
}

// CONFIGURATION ================================
gulp.start('default');
gulp.start('watch');
app.use(express.static(src));
app.listen(port);
console.log('Bicitools en ejecución (Puerto ' + port + ')');


// ROUTES =======================================
app.all('/*', function(request, response, next) {
	response.sendFile(src + '/views/index.html');
	setFile(response, 'index');
});




// app.get('/products/:id', function(req, res, next){
// 	res.json({msg: 'This is CORS-enabled for all origins!'});
// });
// app.listen(8080, function(){
// 	console.log('CORS-enabled web server listening on port 80');
// });

// Just send the index.html for other files to support HTML5Mode
//res.sendFile('index.html', { root: __dirname });
