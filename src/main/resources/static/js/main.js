
var errorHandler = (err) => { alert("Sorry, there was a problem. " + err); console.log(err) }

var HC = {
    getCategories: function() {
        jQuery.ajax({method: 'get', url: '/api/categories'}).done(
            function(data) {
                console.log("data=" + data);
                var list = data;
                var ul = jQuery('<ul class="categories btn-group"></ul>');
                list.forEach((cat) => {
                    ul.append('<li class="btn-link" onclick="HC.loadCategory(\'' + cat.id + '\'); return false">'
                    + cat.name + ': <i>' + cat.description + '</i></li>')
                });
                jQuery('#content').html(ul);
            }
        ).fail( errorHandler );
    },
    loadCourses: function() {
        jQuery.ajax({method: 'get', url: '/api/courses'}).done(
            function(data) {
                var list = data;
                var ul = jQuery('<ul class="courses btn-group"></ul>');
                list.forEach((crs) => {
                    ul.append('<li class="btn-link" onclick="HC.loadCourse(\''+crs.id+'\'); return false">'
                        + crs.name + ': <i>' + crs.description + '</i></li>')
                });
                jQuery('#content').html(ul);
            }
        ).fail( errorHandler );
    },
    loadCategory: function(id) {
        jQuery.ajax({method: 'get', url: '/api/category/'+id}).done(
            function(data) {
                console.log("data=" + data);
                var list = data.courses;
                var ul = jQuery('<ul class="courses btn-group"></ul>');
                list.forEach((course) => {
                    ul.append('<li class="btn-link" onclick="HC.loadCourse(' + course.id + ')">'
                        + course.name + ': <i>' + HC.toDollars(course.price) + '</i></li>')
                });
                jQuery('#content').html(ul);
            }
        ).fail( errorHandler );
    },
    toDollars: function(price) {
        return '$' + (price/ 100);
    },
    course: null,
    currentSegment: null,
    nextSegment: null,
    loadCourse: function(id) {
        jQuery.ajax({method: 'get', url: '/api/course/'+id}).done(
            function(course) {
                console.log("data=" + course);
                HC.course = course;
                var segments = course.segments;
                var ul = jQuery('<ul class="segments btn-group"></ul>');
                segments.forEach((segment) => {
                    ul.append('<li class="btn-link" onclick="HC.loadSegment(' + segment.id + ')">'
                        + segment.name + '</li>')
                });
                jQuery('h1').text(course.name);
                jQuery('#content').html(ul);
            }
        ).fail( errorHandler );
    },
    loadSegment: function(id) {
        if (!HC.course) {
            console.log("ERROR: course not available");
            return;
        } else {
            var ids = HC.course.segments.map(it => it.id);
            var index = ids.indexOf(id);
            HC.currentSegment = HC.course.segments[index];
            if (HC.course.segments.length > index) HC.nextSegment = HC.course.segments[index + 1];
            else HC.nextSegment = null;
            //TODO: improve styling of text
            jQuery('h1').text(HC.currentSegment.name);
            jQuery('#content').text(HC.currentSegment.body);
        }
    }
}

