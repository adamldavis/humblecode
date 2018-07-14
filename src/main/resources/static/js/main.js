
function getCategories() {
    jQuery.ajax({method: 'get', url: '/api/categories'}).done(
        function(data) {
            console.log("data=" + data);
            var list = data;
            var ul = jQuery('<ul class="categories btn-group"></ul>');
            list.forEach((cat) => {
                ul.append('<li class="btn-link" onclick="loadCategory(\'' + cat.id + '\'); return false">'
                + cat.name + ': <i>' + cat.description + '</i></li>')
            });
            jQuery('#categories').html(ul);
        }
    ).fail( (err) => { alert("Sorry, there was a problem. " + err); console.log(err) } );
}

function loadCategory(id) {
    jQuery.ajax({method: 'get', url: '/api/category/'+id}).done(
        function(data) {
            console.log("data=" + data);
            var list = data.courses;
            var ul = jQuery('<ul class="courses btn-group"></ul>');
            list.forEach((course) => {
                ul.append('<li class="btn-link" onclick="loadCourse(' + course.id + ')">'
                    + course.name+': <i>'+course.description + '</i></li>')
            });
            jQuery('#categories').html(ul);
        }
    ).fail( (err) => { alert("Sorry, there was a problem. " + err); console.log(err) } );
}

