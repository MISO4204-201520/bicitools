// Libraries
var gulp = require('gulp');
var uglify = require('gulp-uglify');
var expect = require('gulp-expect-file');
var concat = require('gulp-concat');
var sass = require('gulp-sass');
var minifyCss = require('gulp-minify-css');
var sourcemaps = require('gulp-sourcemaps');



// Tasks
var scripts = "scripts";
var styles = "styles";
var vendorStyles = 'vendor-styles';
var vendorScripts ='vendor-scripts';

// Paths
var scriptsPath = [
    'app/app.js',
    'app/controllers/**/*.js',
    'app/directives/sg-directive.js',
    'app/directives/sg-form/sg-form.js',
    'app/directives/**/*.js',
    'app/directives/**/*.js',
    'app/scripts/googlemaps.js'
];
var styleSheetsPath = [
    'app/styles/variables.scss',
    'app/styles/footer.css',
    'app/styles/**/*.scss'
];

var path = require('path');

gulp.task(styles, function () {
    gulp.src(styleSheetsPath)
    .pipe(concat('bicitools.css'))
    //.pipe(sass({outputStyle: 'compact'}).on('error', sass.logError))
    .pipe(sass().on('error', sass.logError))
    //.pipe(concat('bicitools.css'))
    .pipe(sourcemaps.init())
    //.pipe(minifyCss())
    .pipe(sourcemaps.write())
    .pipe(gulp.dest('app/styles/auto'));
});

gulp.task(scripts, function () {
    gulp.src(scriptsPath)
    .pipe(concat('bicitools-scripts.js'))
    //.pipe(uglify())
    .pipe(gulp.dest('app/scripts/auto'));
});

gulp.task(vendorStyles, function () {
    var files = [
        'bower_components/bootstrap/dist/css/bootstrap.min.css',
        'bower_components/bootstrap-social/bootstrap-social.css',
        'bower_components/angular-loading-bar/build/loading-bar.css',
        'bower_components/components-font-awesome/css/font-awesome.css',
        'app/styles/vendor/bootstrap-datepicker3.css'
    ];
    gulp.src(files)
    .pipe(expect(files))
    .pipe(concat('vendor-styles.css'))
    .pipe(gulp.dest('app/styles/auto'));
});

gulp.task(vendorScripts, function () {
    var files = [
        'bower_components/lodash/lodash.js',
        'bower_components/jquery/dist/jquery.js',
        'bower_components/bootstrap/dist/js/bootstrap.js',
        'bower_components/angular/angular.js',
        'bower_components/angular-simple-logger/dist/angular-simple-logger.js',
        'bower_components/angular-google-maps/dist/angular-google-maps.js',
        'bower_components/angular-loading-bar/build/loading-bar.js',
        //'bower_components/angular-bootstrap/ui-bootstrap.js',
        'bower_components/angular-ui-router/release/angular-ui-router.js',
        'bower_components/ngstorage/ngStorage.js',
        'app/scripts/vendor/bootstrap-datepicker.js'
    ];
    gulp.src(files)
    .pipe(expect(files))
    .pipe(concat('vendor-scripts.js'))
    .pipe(gulp.dest('app/scripts/auto'));

    files = 'app/scripts/modernizr-2.8.3-respond-1.4.2.min.js';
    gulp.src(files)
    .pipe(expect(files))
    .pipe(gulp.dest('app/scripts/auto'));
});

gulp.task('watch', function () {
    var scriptsFiles = [
        scriptsPath
    ];
    gulp.watch(scriptsFiles, [scripts]);

    var styleSheetsFiles = [
        styleSheetsPath
    ];
    gulp.watch(styleSheetsFiles, [styles]);
});

gulp.task('default', [vendorStyles, vendorScripts, styles, scripts]);
